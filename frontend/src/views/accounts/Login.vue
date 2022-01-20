<template>
  <div id="login_container">
    <div id="login">
      <div id="login_header">
        <div>
          <h3>어서오세요!</h3>
          <h3>오늘은 어떤 이야기를</h3>
          <h3>들려주실건가요?</h3>
        </div>
        <img src="../../assets/images/sun.png" id="earth">
      </div>
      <div id="login_body">
        <div id="email">
          <p id="email_label">email</p>
          <label for="email"></label>
          <input type="text"
          id="email"
          v-model="credentials.email"
          placeholder=" type your email">
        </div>
        <br>
        <div id="password_label">
          <p id="password">password</p>
          <label for="pw"></label>
          <input type="password" 
          id="pw"
          v-model="credentials.pw"
          placeholder=" type your password">
        </div>
      </div>
      <br>
      <div id="link">
        <a href="#">이메일 찾기 </a> &nbsp;&nbsp;&nbsp;
        <a href="#">비밀번호 찾기 </a> &nbsp;&nbsp;&nbsp;
        <router-link :to="{ name: 'Signup' }" class="gosignup">회원가입</router-link>
      </div>
      <br>

      <div>
        <button id="login_button" @click="login">로그인</button>
      </div>
      <br>

      <button id="google_button">
        <img id="google" src="../../assets/images/Google__G__Logo.png">
        <p>Google로 로그인</p>
      </button>
      <br>
      <div>
        <button id="kakao_button">
          <img id="kakao" src="../../assets/images/kakao.png">
          <p id="kakao_text">Kakao로 로그인 &nbsp;</p>
          </button>
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
        this.$router.push({name: 'Test1st'})
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
@import '../../assets/styles/astrostyle.css';
#login_container{
  display: flex;
  justify-content: center;
  border: 1px;
  background-color: white;
  width: 360px;
  height: 470px;
  border-radius: 10px;
  margin: auto;
}
#login{
  padding: 20px 10px 20px;
}
#login_header{
  padding-bottom:20px;
  display: flex;
  justify-content: space-between;
}
#email_label{
  text-align: left;
  color: #5E39B3;
  font-size: 15px;
}
#password_label{
  text-align: left;
  color: #5E39B3;
  font-size: 15px;
}
input{
  width: 250px;
  height: 30px;
  border-radius: 5px;
  border-color: #5E39B3;
}
#link{
  font-size: 13px;
}
#login_button{
  background-color: #5E39B3;
  border-radius: 20px;
  width: 120px;
  height: 40px;
  font-size: 14px;
  font-weight: bold;
  color: white;
}
h3{
  text-align: left;
}
#google_button{
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  background-color: #F0EDE7;
  border-radius: 20px;
  width: 220px;
  height: 40px;
  font-size: 14px;
  font-weight: bold;
  color: black;
  margin: auto;
  padding: 10px;
}
#kakao_button{
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  background-color: #F0EDE7;
  border-radius: 20px;
  width: 220px;
  height: 40px;
  font-size: 14px;
  font-weight: bold;
  color: black;
  margin: auto;
  padding: 8px;
}
img{
  width: 90px;
  height: 90px;
}
#kakao{
  width:22px;
  height: 22px;
  align-content: center;
}
#google{
  width:18px;
  height: 18px;
}
#earth{
  margin-top: 10px;
  margin-bottom: 10px;
  width: 55px;
  height: 55px;
}
</style>