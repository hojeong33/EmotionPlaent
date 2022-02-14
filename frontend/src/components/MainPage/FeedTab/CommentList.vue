<template>
  <div id="comments">
    <div id="form-commentInfo">
      <div id="comment-count"  @click="goToDetail">댓글 
          <span id="count">{{comments.length}}</span>
      </div>
      <comment v-for="(comment,index) in commentsList"
      :comment="comment"
      :key="index">
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
      comments:[],//번호 목록
      commentsList:[],//목록 더보기
      commentsData:[],
      commentContent:null,
      isShort:true,
      isAll:false,
      
    }
  },
  props:{
      feedNo:Number
  },
 
  methods:{
    goToDetail:function(){
      this.$router.push({name:'FeedDetail', params:{feedNo:this.feedNo}})
    },
    commentMore:function(){
      this.commentsList=this.commentsData
      this.isShort=false
      this.isAll=true

    },
    commentShort:function(){
      this.commentsList=this.commentsData.slice(0,2)
      this.isShort=true
      this.isAll=false
    },
    forceRerender(){
      this.getComments()
    },
    getComment:function(commentNo){

          let headers = {
        'at-jwt-access-token': session.getItem('at-jwt-access-token'),
        'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
        };
        axios({
            method: 'get',
            url:`http://13.125.47.126:8080/comment/${commentNo}`,
            headers: headers,  // 넣는거 까먹지 마세요
          }).then((res) => {
          this.$store.dispatch('accessTokenRefresh', res) // store아닌곳에서
          console.log('!!!!!!!!!!!!!!!!!!!댓글 하나 가져오기')
          console.log(res.data)
          this.commentsData.push(res.data)
          // this.getComments()
          }).catch((error) => {
            console.log(error);
          }).then(() => {
            console.log('댓글 하나 가져오기');
          });
    },
    getComments:function(){
      let headers = {
        'at-jwt-access-token': session.getItem('at-jwt-access-token'),
        'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
      };
      axios({
          method: 'get',
          url:`http://13.125.47.126:8080/comments/returnNo/${this.feedNo}`,
          headers: headers,  // 넣는거 까먹지 마세요
        }).then((res) => {
        this.$store.dispatch('accessTokenRefresh', res) // store아닌곳에서
        console.log('!!!!!!!!!!!!!!!!!!!댓글 여러개 가져오기')
        console.log(res.data)
        this.comments=res.data
        this.commentsData=[]
        for (let i=0; i<this.comments.length; i++){
          const commentNo=this.comments[i]
          this.getComment(commentNo)
        }
        this.commentsList=this.commentsData.slice(0,2)
        console.log(this.commentsList)
        
        }).catch((error) => {
          console.log(error);
        }).then(() => {
          console.log('댓글 목록 가져오기');
        });
    },
    createComment:function(){
      const userdata = JSON.parse(session.getItem('userInfo')) 
      const commentItem={
          descr:this.commentContent,
          author:userdata.no,
          feedNo:this.feedNo
        }
      if(commentItem.descr){
        this.commentContent=null
        let headers = {
          'at-jwt-access-token': session.getItem('at-jwt-access-token'),
          'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
        };
        axios({
            method: 'post',
            url:'http://13.125.47.126:8080/comments',
            data: commentItem, // post 나 put에 데이터 넣어 줄때
            headers: headers,  // 넣는거 까먹지 마세요
          })
          .then((res) => {
             this.$store.dispatch('accessTokenRefresh', res) // store아닌곳에서
            //  console.log(res.data)
            //  console.log('댓글 다시 가져옴!!!!!!!!!!!!!!!!!!!!!!')
             this.getComments()
          })
          .catch((error) => {
            console.log(error);
          })
          .then(() => {
            console.log('댓글 작성 완료');
          });

        
      }
      else{
        alert('내용을 채워주세요')
      }
    },
  },
  created(){
    this.getComments()
  },
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