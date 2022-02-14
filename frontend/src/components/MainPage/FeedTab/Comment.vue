<template>
  <div>
    <div>
    <!-- <div style="position: absolute;"> -->
      <img id="comment_img" :src=commentData.userRequestDto.profileImg alt="">
      <span style="font-weight:bold; font-size:1.2rem;">{{commentData.userRequestDto.nickname}} </span> <span style="font-size:1.2rem">{{commentData.descr}} </span>
    </div>
    <div  v-if="isMine" style="text-align:right; margin-bottom:1rem">
      <i @click="onSetting" class="fas fa-ellipsis-v"></i>
      <div id="setting" v-if="isOpend">
        <p style="margin-bottom:0; margin-top:1rem;">수정</p>
        <hr>
        <p @click="deleteComment">삭제</p>
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
        isMine:this.comment.owner,
        commentData:null,
      }
    },
    methods:{
      onSetting:function(){
        // console.log(this.isMine)
        if(this.isOpend){
          this.isOpend=false
        }else{
          this.isOpend=true
        }
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
      // this.getComments()
      }).catch((error) => {
        console.log(error);
      }).then(() => {
        console.log('댓글 하나 가져오기');
      });
    },
    // watch:{
    //   comment:function(){
        
    //   }
    // }

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