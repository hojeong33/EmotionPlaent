<template>
  <div>
    <div class="container" id="create_container">
      <div id="create">
        <div id="create_header">
          <img src="../../assets/images/icons/return.png" alt="" id="back" @click="$router.go(-1)">
          <h3 id="title">글 쓰기</h3>
        </div>
        <hr>
        <div class="container" id="img_upload">
          <img src="../../assets/images/icons/image.png" alt="" id="imgfile">
          <p id="img_text">이미지를 올려주세요</p>
        </div>
        <br>
        <div id="footer">
          <button id="btn_upload">이미지 업로드</button>
          <button id="btn_next" @click="$router.push({name: 'CreateTag'})">다음</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
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
  margin: auto;
}
#back{
  width: 30px;
  height: 30px;
}
#title{
  margin: auto;
}
h3{
  font-weight: bold;
  font-size: 20px;
}
#img_upload{
  display: flex;
  flex-direction: column;
  width: 60vh;
  height: 47vh;
  background-color: lightgray;
  border-radius: 20px;
  margin: auto;
  justify-content: center;
  align-items: center;
}
#imgfile{
  width: 30px;
  height: 30px;
}
#img_text{
  color: gray;
}
#footer{
  display:flex;
  flex-direction: row;
  flex-wrap: nowrap;
  justify-content: space-evenly;
}
#btn_upload{
  border-radius: 30px;
  width: 150px;
  height: 30px;
  background-color: rgb(143, 141, 141);
  color: white;
  font-style: border;
}
#btn_next{
  border-radius: 30px;
  width: 70px;
  height: 30px;
  background-color: #5E39B3;
  color: white;
  font-style: border;
}
</style>