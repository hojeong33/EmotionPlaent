<template>
  <div>
    <div class="container" id="create_container">
      <div id="create">
        <div id="create_header">
          <img src="../../assets/images/icons/return.png" alt="" id="back" @click="$router.go(-1)">
          <h3 id="title">글 쓰기</h3>
        </div>
        <hr>
        <div id="text_header">
          <h3>이제 자유태그와 글을 작성할 수 있습니다!</h3>
          <h3>여러분의 하루를 공유해주세요</h3> 
        </div>
        <div>
          <input type="text" id="tagInput">
          <input type="text" id="textInput">
        </div>
        <br>
        <div id="footer">
          <div id="secret">
            <input type="checkbox" id="checkbox" v-model="checkedSecret">
            <p id="p">비밀글</p>
          </div>
          <button id="btn_next" type="submit">작성</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'CreateText',
  data: function () {
    return {
      isChecked: false
    }
  },
  methods: {
    checkedSecret: function () {
      this.isChecked = !this.isChecked
      console.log(this.isChecked)
    }
  }
}
</script>

<style scoped>
#create_container{
  display:flex;
  width: 70vh;
  height: 67vh;
  background-color: white;
  border-radius: 20px;
  border: thin black;
  border-style: solid; 
  justify-content: center;
  align-items: center;
}
#create_header{
  display:flex;
  flex-direction: row;
  flex-wrap: nowrap;
  justify-content: space-between;
}
#back{
  width: 30px;
  height: 30px;
}
#title{
  margin: auto;
}
#text_header{
  text-align: left;
}
h3{
  font-weight: bold;
  font-size: 20px;
}
#tagInput{
  width: 60vh;
}
#textInput{
  width: 60vh;
  height: 30vh;
}
#footer{
  display:flex;
  flex-direction: row;
  justify-content: space-evenly;
}
#secret{
  display:flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
#btn_next{
  border-radius: 30px;
  width: 70px;
  height: 30px;
  background-color: #5E39B3;
  color: white;
  font-weight: bold;
}
</style>