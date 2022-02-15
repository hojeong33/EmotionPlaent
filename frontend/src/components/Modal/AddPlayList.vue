<template>
	<div id="likes_list">
		<div id="modal">
		<div id="likes_list_header">
      <div id="title_container">
        <p id="title">추가하기 </p>
      </div>
      <div id="cancel">
        <i @click="goBack" class="fa-solid fa-x" style="font-size: 1.3rem; cursor: pointer;"></i>
      </div>
    </div>
    <hr>
    <div id="no_result" v-if="feedLikesList.length === 0">
      <img id="nothing" src="@/assets/images/etc/alien.png" alt="no result">
      <p id="no_likes">플레이리스트 목록이 없습니다...</p>
    </div>
    <div id="feed_likes_list" v-else>
      <div v-for="(liker, index) in feedLikesList" :key="index">
        <div id="userInfo">
          <img id="profile_img" :src="liker.profileImg" alt="">
          <p id="username">{{liker.nickname}}</p>
          <!-- <button id="follow_cancel">취소</button> -->
        </div>
      </div>
    </div>
    <hr>
    <div id="plus_container" v-if="isClick">
      <img id="plus_icon" @click="addList" src="@/assets/images/icons/more_selected.png" alt="">
      <p id="plus_text">새 플레이리스트 만들기</p>
    </div>
    <div id="new_container" v-else>
      <p id="plus_text">이름</p>
      <input id="playlist_input"  v-model.trim="listName" placeholder="플레이리스트 이름을 입력해주세요">
      <div id="uploading">
      <p id="plus_text" style="margin-top:0.5rem;">이미지 업로드</p>
      <input type="file" id="file" accept="image/*" @change="imgUpload" ref="feedImg" />
    </div>
      <button id="pu_button" @click="createList">만들기</button>
    </div>
  </div>
</div>
</template>

<script>
import axios from 'axios'
const session = window.sessionStorage;
const userdata = JSON.parse(session.getItem('userInfo')) ;
export default {
	data () {
		return {
			feedLikesList: [],//좋아요 누른 사람 목록
      isClick:true,
      images:null,
      listName:'',
      listData:{
        name:'',//플레이리스트 이름
        type:null,//음악 :0 영화:1 활동:2
        tagNo:null,//행성
        userNo:null,
      },
      forderlists:[],
		}
	},
	methods: {
		goBack: function () {
			this.$store.commit('addPlayListActive',this.type)
      this.isClick=true
		},
    addList:function(){
      this.isClick=false
      console.log('여기')
      console.log(this.isClick)

    },
    imgUpload() {
      this.images = null;
      this.images = URL.createObjectURL(this.$refs.feedImg.files[0]);
    },
    getPlayList:function(){

    },
    createList:function () {
      this.listData['name']=this.listName
      if(this.listName){

        let headers = {
          // 'Content-Type': 'multipart/form-data',
          'at-jwt-access-token': session.getItem('at-jwt-access-token'),
          'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
        };
  
        const formData2 = new FormData();
        // formData2.append("multipartFile", this.$refs.feedImg.files[0]);
        formData2.append(
          "data",
          new Blob([JSON.stringify(this.listData)], { type: "application/json" })
        );
        let body={userNo:1, type:1, tagNo:1, name:this.listData.name}
        axios({
          method: 'post',
          url: 'http://13.125.47.126:8080/picks',
          data: body, // post 나 put에 데이터 넣어 줄때
          headers: headers,  // 넣는거 까먹지 마세요
        }).then((res) => {
            console.log("피드 작성 : " + res.data)
            this.$store.dispatch('accessTokenRefresh', res) // store아닌곳에서
            this.isClick=true
        }).catch((error) => {
          console.log(error);
        }).then(() => {
          console.log('플레이리스트 생성하기');
        });
      }
      else{
        alert('제목을 입력해주세요')
      }
    },

	},
	created () {
		this.feedLikesList = this.$store.state.feedLikesInfo
    this.listData['type']=this.$store.state.type
    this.listData['tagNo']=userdata.mood
    this.listData['userNo']=userdata.no
    console.log(userdata)
	}
}
</script>

<style scoped>

#likes_list {
	display: flex;
	justify-content: center;
	align-items: center;
	width: 100%;
	height: 100%;
	background-color: rgb(0, 0, 0, 0.5);
	position: fixed;
	top: 0;
	left: 0;
	z-index: 999;
}
#modal {
	background-color: white;
	border-radius: 20px;
	width: 25vw;
	min-width: 350px;
	/* height: 45vh; */
	min-height: 450px;
}
#likes_list_header {
	display: flex;
	flex-direction: row;
	justify-content: center;
	padding-top:1rem;
	padding-bottom: 1rem;
}
#title {
	margin-top: auto;
	margin-bottom: auto;
	font-size: 1.2rem;
	font-weight: bold;
}
#title_container{
	width: 58%;
	display: flex;
	justify-content: right;
}
#cancel {
	width: 42%;
	display: flex;
	justify-content: right;
	margin-right: 1rem;
	margin-top: 0.3rem;
}
#no_result {
	display:flex;
	flex-direction: column;
	align-items: center;
	margin-top: 25%;
	margin-bottom: 25%;
}
#nothing {
	width: 5rem;
	height: 5rem;
	margin-bottom: 0.5rem;
}
#no_likes {
	font-weight: bold;
}
#feed_likes_list{
	margin-left: 1rem;
	overflow-y: scroll;
	height: 82%;
}
#userInfo{
	display: flex;
	flex-direction: row;
	margin-bottom:1rem;
	margin-top: 1rem;
}

#profile_img {
	width: 2rem;
	height: 2rem;
	border-radius: 50%;
	margin-right: 0.5rem;
	margin-top: auto;
	margin-bottom: auto;
}
#username {
	font-weight: bold;
	margin-right: 0.5rem;
	margin-top: auto;
	margin-bottom: auto;
}
#following_check{
	color: blue;
	font-size: 0.8rem;
	margin-top: auto;
	margin-bottom: auto;
}
#follow_cancel{
	margin-right: 0.5rem;
	margin-left: auto;
	margin-top: auto;
	margin-bottom: auto;
	background-color: #5E39B3;
	color: white;
	border-radius: 10px;
	font-size: 0.8rem;
	padding: 0.1rem;
}
hr {
	margin: 0;
}
#new_container{
  margin-left: 6%;
  margin-top: 0.5rem;
  /* margin-bottom: auto; */
	width: 100%;
	/* display: flex; */
}
#plus_container{
  margin-top: 0.5rem;
  /* margin-bottom: auto; */
  display: flex;
	width: 100%;
	/* justify-content: center; */
}
#plus_text{
	margin-top:auto;
	margin-bottom: auto; 
	margin-right: auto;
	font-size: 1.2rem;
	font-weight: bold;
}
#plus_icon{
	margin-left: auto;
	width: 50px;
	height: 50px;
	padding: 10px;
  cursor: pointer;
}
input:focus {
    outline-color: #5E39B3;
  }
#playlist_input{
  border-radius: 10px;
  padding: 0.3rem;
  margin-top:0.1rem;
}
#pu_button {
  background-color: #5E39B3;
  color: white;
  font-size: 1.125rem;
  font-weight: bold;
  border: none;
  border-radius: 20px;
  padding: 0.4rem 1.5rem;
  margin: 1rem 2rem 1rem 0;
  cursor: pointer;
  line-height: 2rem;
  margin-left: 60%;
}
</style>