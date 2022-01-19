<template>
  <div id="background">
    <div class="container" id="login">
      <div class="container" id="content">
        <div>
          <h4>어서오세요</h4> 
          <h4>오늘은 어떤 이야기를</h4>
          <h4>들려주실건가요?</h4>
        </div>
        <div>
          <label for="email"></label>
          <input type="text"
          id="email"
          v-model="credentials.email"
          placeholder="email">
        </div>
        <div>
          <label for="pw"></label>
          <input type="password" 
          id="pw"
          v-model="credentials.pw"
          placeholder="passowrd">
        </div>
        <div>Don't have an ID?</div>
        <router-link :to="{ name: 'Signup' }"
        class="gosignup">Create one now.</router-link>
        <br>
        <button @click="login">로그인</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Login',
  data: function () {
    return {
      credentials: {
        email: null, 
        pw: null,
      },
    }
  },
  methods: {
    login: function () {
      axios({
        method: 'post',
        url:'http://localhost:8080/login',
        data: this.credentials
      })
      .then(()=>{
        alert("로그인 성공")
        this.$emit('login') 
      })
      .catch(err=> {
        alert(err.response.data.message) // 서버측에서 넘어온 오류 메시지 출력.
      })
      this.credentials.email = "";
      this.credentials.pw ="";
    },
  }
}
</script>

<style scoped>
/* landscape-green-sunlight-sky-atmosphere-universe-1428591-pxhere 1 */
#background{
  background-image: url("~@/assets/background.png");
  /* border: 1px solid #000000; */
  box-sizing: border-box;
  min-height: 100vh;
  background-size: cover;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
#login{
  border: 2px black;
  width: 500px;
  height: 600px;
  background-color: white;
  border-radius: 10px;
}
/* #content{
  margin: 50px;
} */
/* button{

} */
</style>