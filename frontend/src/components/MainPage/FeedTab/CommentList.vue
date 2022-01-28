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
      <input id="comment-input" v-model.trim="commentContent" placeholder="댓글을 입력해 주세요."> 
      <button id="submit" @click="createComment">등록</button> 
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
        content:this.commentContent
      }
      if(commentItem.content){
        this.comments.push(commentItem.content)
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
#comments{
  text-align: left;
  margin:0rem 3rem;
}
#comment-count{
  color:gray;
}
#comment-input{
  width: 33rem;
  margin-bottom: 1rem;
}
#comment-more{
  margin-top: 1rem;
  font-size: 0.8rem;
  color:gray;
}

</style>