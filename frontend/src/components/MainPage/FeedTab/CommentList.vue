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
        <img id="submit" @click="createComment" src="@/assets/images/icons/write.png" alt="" style="width:1.4rem;height:1.4rem;">
      </div>
    </div>
  </div>
</template>

<script>
// import axios from 'axios'
import Comment from './Comment.vue'
export default {
  components: { Comment },
  name:'CommentList',
  data:function(){
    return{
      commentContent:null,
      commentsList:this.comments.slice(0,2),
      isShort:true,
      isAll:false
    }
  },
  props:{
      comments:Array,
  },
  methods:{
    createComment:function(){
      const commentItem={
        commentText:this.commentContent,
        username:'default',
        userImage:'https://cdn.indiepost.co.kr/uploads/images/2018/12/11/VDbIX3-600x338.png'
      }
      if(commentItem.commentText){
        this.comments.push(commentItem)
        this.commentContent=null

      }
      else{
        alert('내용을 채워주세요')
      }
    },
    commentMore:function(){
      this.commentsList=this.comments
      this.isShort=false
      this.isAll=true

    },
    commentShort:function(){
      this.commentsList=this.comments.slice(0,2)
      this.isShort=true
      this.isAll=false
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
}
#comment-input{
  width: 95%;
  height: 100%;
  margin-bottom: 1rem;
  margin-left:3px;
  border-style:none;
  margin-bottom: 0;
  outline: none;
}
#comment-more{
  margin-top: 1rem;
  font-size: 0.8rem;
  color:#777777;
}

</style>