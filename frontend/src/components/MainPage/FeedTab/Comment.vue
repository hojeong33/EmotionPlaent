<template>
  <div v-if="commentData">
    <div id="comments">
      <img id="comment_img" :src="commentData.userRequestDto.profileImg" alt="">
      <p id="username">{{commentData.userRequestDto.nickname}} </p> 
      <p id="user_comment">{{commentData.descr}} </p>
      <div id="comment_setting">
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
        isOpend:false,
        isMine: false,
        commentData:null,
      }
    },
    methods:{
      onCommentSetting:function(){
			this.$store.commit('commentSettingModalActivate', this.comment)
      },
    },
      deleteComment:function(){
        // console.log(this.comment.no)
        // console.log(this.comment.feedNo)
        
        // let headers = {
        //   'at-jwt-access-token': session.getItem('at-jwt-access-token'),
        //   'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
        // };
        // axios({
        // method:'delete',
        // url:`http://13.125.47.126:8080/comments/${this.comment}`,
        // headers:headers
        // })
        // .then((res)=>{
        //   console.log(res);
        //   console.log('response header', res.headers);
        //   if(res.headers['at-jwt-access-token'] != session.getItem('at-jwt-access-token')){
        //     session.setItem('at-jwt-access-token', "");
        //     session.setItem('at-jwt-access-token', res.headers['at-jwt-access-token']);
        //     console.log("Access Token을 교체합니다!!!")
        //     }
        //   axios({
        //   method:'get',
        //   url:`http://13.125.47.126:8080/comments/${this.comment.feedNo}`,
        //   headers:headers,
        //   })
        //   .then((res) => {
        //     console.log(res);
        //     console.log('response header', res.headers);
        //     if(res.headers['at-jwt-access-token'] != session.getItem('at-jwt-access-token')){
        //       session.setItem('at-jwt-access-token', "");
        //       session.setItem('at-jwt-access-token', res.headers['at-jwt-access-token']);
        //       console.log("Access Token을 교체합니다!!!")
        //       }
        //       console.log(res.data)
        //       this.comments=res.data
        //       this.commentsList=res.data.slice(0,2)
        //     }).catch((error) => {
        //       console.log(error);
        //     }).then(() => {
        //       console.log('댓글 가져오기 클리어');

        //     });
        // })
        

      },
      // updateComment:function(){
      //   axios({
      //     method:'put',
      //     url:'http://13.125.47.126:8080/comments',
      //   })
      //   .then((res)=>{
      //     // console.log(res.data)
      //   })
      // },
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
        // this.getComments()
        }).catch((error) => {
          console.log(error);
        })
    },
}
</script>

<style scoped>
#comments {
	display: flex;
	flex-direction: row;
	justify-content: left;
	align-items: center;
	font-size: 1rem;
	margin-top: 0.5rem;
	margin-bottom: 0.5rem;
}
#comment_img{
  width: 5vh;
	height: 5vh;
	border-radius: 5vh;
	margin-right: 0.5rem;
}
#user_comment {
	margin-top:auto;
	margin-bottom: auto;
	margin-left: 0.5rem;
}
#username {
	font-weight: bold;
	margin: 0rem;
	font-size: 1.2rem;
}
#comment_setting {
	margin-left: auto;
	margin-right: 1rem;
  cursor: pointer;
}
</style>