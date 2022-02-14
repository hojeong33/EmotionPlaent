<template>
  <div id="comment">
    <img id="comment_img" :src=comment.userImage alt="">
    <span style="font-weight:bold; margin-left:">{{comment.username}} </span> {{comment.commentText}}
    <div id="comment_setting">
      <i @click="onCommentSetting" class="fas fa-ellipsis-v"></i>
      <!-- 댓글 하나하나에 유저데이터가 들어가서 해당 유저의 댓글이 지워져야 함-->							
    </div>
  </div>
</template>

<script>

export default {
    name:'Comment',
    props:{
        comment:Object
    },
    data(){
      return{
      }
    },
    methods:{
      onCommentSetting:function(){
        this.$store.commit('commentSettingModalActivate')

      }
    }

}
</script>

<style scoped>
#comment {
  margin-top: 0.2rem;
  margin-bottom: 0.2rem;
}
#comment_img{
  width: 2rem;
  height: 2rem; 
  border-radius: 70%;
  overflow:hidden;
  margin:2px;

}
#comment_setting{
  float:right;
  width: 10%;
  border-radius: 10px;
  text-align: center;
  margin-left: auto;
}
</style>