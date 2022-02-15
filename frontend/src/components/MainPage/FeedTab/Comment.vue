<template>
  <div v-if="commentData">
    <div>
    <!-- <div style="position: absolute;"> -->
      <img id="comment_img" :src="commentData.userRequestDto.profileImg" alt="">
      <p id="username">{{commentData.userRequestDto.nickname}} </p> 
      <p id="user_comment">{{commentData.descr}} </p>
      <div id="comment_setting" v-if="isMine">
        <i @click="onCommentSetting" class="fas fa-ellipsis-v"></i>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
const session = window.sessionStorage;
export default {
  name:'Comment',
  props:{
      comment:Number
  },
  data(){
    return{
      isMine:false,
      commentData:null,
      isCommentSettingOpened:false,
    
    }
  },
  methods:{
    onCommentSetting:function(){
      this.$store.commit('commentSettingModalActivate')
      // if(this.isCommentSettingOpened){
      // 	this.isCommentSettingOpened=false
      // }else{
      // 	this.isCommentSettingOpened=true
      // }
    },
  },
  created(){
    let headers = {
      'at-jwt-access-token': session.getItem('at-jwt-access-token'),
      'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
  };
  axios({
      method: 'get',
      url:`http://13.125.47.126:8080/comment/${this.comment}`,
      headers: headers,  // 넣는거 까먹지 마세요
    }).then((res) => {
    this.$store.dispatch('accessTokenRefresh', res) // store아닌곳에서
    console.log('!!!!!!!!!!!!!!!!!!!')
    console.log(res.data)
    this.commentData=res.data
    this.isMine=res.data.owner
    // this.getComments()
    }).catch((error) => {
      console.log(error);
    })
  },
}
</script>

<style scoped>
#comment_img{
  width: 2rem;
  height: 2rem; 
  border-radius: 70%;
  overflow:hidden;
  margin:2px;

}

#setting{
  width: 10%;
  border:1px solid black;
  border-radius: 10px;
  text-align: center;
  display: flex;
  flex-direction:column;
  position: absolute;
  transform: translate(34rem,-10px);
  background-color: white;
}
</style>