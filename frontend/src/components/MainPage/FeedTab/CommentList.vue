<template>
  <div id="comments">
    <div id="form-commentInfo">
      <div id="comment-count">댓글 
          <span id="count">{{comments.length}}</span>
      </div>
      <comment v-for="comment in commentsList"
      :comment="comment"
      :key="commentsList.indexOf(comment)">
      </comment>
      <p v-if="isShort" id="comment-more" @click="commentMore">댓글 더보기</p>
      <p v-if="isAll" id="comment-more" @click="commentShort">댓글 닫기</p>
      <div id="comment_write">
        <input id="comment-input" @keyup.enter="createComment" v-model.trim="commentContent" placeholder="댓글을 입력해 주세요."> 
        <img id="submit" @click="createComment" src="@/assets/images/icons/write.png" alt="" style="width:1.6rem;height:1.6rem; margin-bottom:3px;">
      </div>
    </div>
  </div>
</template>

<script>
import Comment from './Comment.vue'
import axios from 'axios';
const session = window.sessionStorage;

export default {
  components: { Comment },
  name:'CommentList',
  data:function(){
    return{
      commentContent:null,
      isShort:true,
      isAll:false,
      
    }
  },
  props:{
      comments:Array,
      commentsList:Array,
      feedNo:Number
  },
 
  methods:{
    commentMore:function(){
      this.commentsList=this.comments
      this.isShort=false
      this.isAll=true

    },
    commentShort:function(){
      this.commentsList=this.comments.slice(0,2)
      this.isShort=true
      this.isAll=false
    },
    createComment:function(){
      const commentItem={
        descr:this.commentContent,
        author:3,
        feedNo:this.feedNo
      }
      if(commentItem.descr){
        this.commentContent=null
        let headers = {
          'at-jwt-access-token': session.getItem('at-jwt-access-token'),
          'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
        };
        axios({
        method:'post',
        url:'http://13.125.47.126:8080/comments',
        data:commentItem,
        headers:headers
        })
        .then((res)=>{
          console.log(res);
          console.log('response header', res.headers);
          if(res.headers['at-jwt-access-token'] != session.getItem('at-jwt-access-token')){
            session.setItem('at-jwt-access-token', "");
            session.setItem('at-jwt-access-token', res.headers['at-jwt-access-token']);
            console.log("Access Token을 교체합니다!!!")
            }
          axios({
          method:'get',
          url:`http://13.125.47.126:8080/comments/${this.feedNo}`,
          headers:headers,
          })
          .then((res) => {
            console.log(res);
            console.log('response header', res.headers);
            if(res.headers['at-jwt-access-token'] != session.getItem('at-jwt-access-token')){
              session.setItem('at-jwt-access-token', "");
              session.setItem('at-jwt-access-token', res.headers['at-jwt-access-token']);
              console.log("Access Token을 교체합니다!!!")
              }
              console.log(res.data)
              this.comments=res.data
              this.commentsList=res.data.slice(0,2)
            }).catch((error) => {
              console.log(error);
            }).then(() => {
              console.log('댓글 가져오기 클리어');

            });
        })

      }
      else{
        alert('내용을 채워주세요')
      }

      
    },
    getComments:function(){
    //   let headers = {
    //     'at-jwt-access-token': session.getItem('at-jwt-access-token'),
    //     'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
    //   };
    //   axios({
    //     method:'get',
    //     url:`http://13.125.47.126:8080/comments/${this.feedNo}`,
    //     headers:headers,
    //   })
    // .then((res) => {
    // console.log(res);
    // console.log('response header', res.headers);
    // if(res.headers['at-jwt-access-token'] != session.getItem('at-jwt-access-token')){
    //   session.setItem('at-jwt-access-token', "");
    //   session.setItem('at-jwt-access-token', res.headers['at-jwt-access-token']);
    //   console.log("Access Token을 교체합니다!!!")
    //   }
    //    this.comments.push()
    //    this.commentsList=this.comments.slice(0,2)
    // }).catch((error) => {
    //   console.log(error);
    // }).then(() => {
    //   console.log('댓글 가져오기 클리어');

    // });
  
  },
  created(){
    // this.commentsList=this.comments.slice(0,2)
    // let headers = {
    //     'at-jwt-access-token': session.getItem('at-jwt-access-token'),
    //     'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
    //   };
    //   axios({
    //     method:'get',
    //     url:`http://13.125.47.126:8080/comments/${this.feedNo}`,
    //     headers:headers,
    //   })
    // .then((res) => {
    // console.log(res);
    // console.log('response header', res.headers);
    // if(res.headers['at-jwt-access-token'] != session.getItem('at-jwt-access-token')){
    //   session.setItem('at-jwt-access-token', "");
    //   session.setItem('at-jwt-access-token', res.headers['at-jwt-access-token']);
    //   console.log("Access Token을 교체합니다!!!")
    //   }
    //   this.comments.push(res.data)
    //   this.commentsList=this.comments.slice(0,2)
    // }).catch((error) => {
    //   console.log(error);
    // }).then(() => {
    //   console.log('댓글 가져오기 클리어');

    // });
  }
  }
}
</script>

<style scoped>
#submit{
  border-style: none;
}
#comment_write{
  border:0.2rem solid gainsboro;
  border-radius: 10px;
  margin-bottom:1rem;
}
#comments{
  text-align: left;
  margin:0rem 3rem;
}
#comment-count{
  color:#777777;
  font-size:1.2rem;
}
#comment-input{
  width: 93%;
  padding:3px;
  height: 100%;
  margin-left:5px;
  border-style:none;
  margin-bottom: 0;
  outline: none;
  font-size: 1.5rem;
}
#comment-more{
  margin-top: 1rem;
  font-size: 1.2rem;
  color:#777777;
}

</style>