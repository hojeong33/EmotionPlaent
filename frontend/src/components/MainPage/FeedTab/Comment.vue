<template>
  <div>
    <span style="position: absolute;">
      <img id="comment_img" :src=comment.userImage alt="">
      <span style="font-weight:bold; margin-left:">{{comment.username}} </span> {{comment.commentText}}
    </span>
    <div style="text-align:right; margin-bottom:1rem">
      <i @click="onSetting" class="fas fa-ellipsis-v"></i>
      <div id="setting" v-if="isOpend">
        <p>수정</p>
        <hr>
        <p>삭제</p>
      </div>
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
        isOpend:false,
      }
    },
    methods:{
      onSetting:function(){
        if(this.isOpend){
          this.isOpend=false
        }else{
          this.isOpend=true
        }

      }
    }

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
  float:right;
  width: 10%;
  border:1px solid black;
  border-radius: 10px;
  text-align: center;
  margin-left: auto;
  position: absolute;
}
</style>