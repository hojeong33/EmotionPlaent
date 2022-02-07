<template>
  <div>
    <span style="position: absolute;">
      <!-- <img id="comment_img" :src=comment.userImage alt=""> -->
      <span style="font-weight:bold; font-size:1.2rem;">{{comment.author}} </span> <span style="font-size:1.2rem">{{comment.descr}} </span>
    </span>
    <div style="text-align:right; margin-bottom:1rem">
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
// import axios from 'axios'
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