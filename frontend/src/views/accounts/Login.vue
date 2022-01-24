<template>
  <div id="login_container">
    <div id="login_header">
      <h1>어서오세요!</h1>
      <h1>오늘은 어떤 이야기를</h1>
      <h1>들려주실건가요? 😉</h1>
      <!-- <div>
        <img src="../../assets/images/sun.png" id="sun">
      </div> -->
    </div>
    <section id="login_body">
      <article id="email_form">
        <label for="email">이메일</label>
        <input type="text"
        id="email"
        v-model="credentials.email"
        placeholder="이메일을 입력해주세요"
        autocomplete="off">
      </article>
      <article id="password_form">
        <label for="pw">비밀번호</label>
        <input type="password" 
        id="pw"
        v-model="credentials.pw"
        placeholder="비밀번호를 입력해주세요">
      </article>
    </section>
    <article id="link">
      <a href="#">이메일 찾기</a>
      <a href="#">비밀번호 찾기</a>
      <router-link :to="{ name: 'Signup' }" class="gosignup">회원가입</router-link>
    </article>
    <article>
      <button id="login_btn" @click="login">로그인</button>
    </article>
    <button id="google" class="social_login">
      <img id="google" src="../../assets/images/Google__G__Logo.png">
      <p>Google로 로그인</p>
    </button>
    <article>
      <button id="kakao" class="social_login">
        <img id="kakao" src="../../assets/images/kakao.png">
        <p>Kakao로 로그인</p>
        </button>
    </article>
  </div>
</template>

<script>
import axios from 'axios'
export default {
   beforeCreate: function () {
    document.body.className = 'astro';
  },
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
  @import '../../assets/styles/globalstyle.css';
  label {
    color: #5E39B3;
    font-weight: bold;
    margin-left: 0.5rem;
    font-size: 1.125rem;
  }

  input {
    border: 2px #5E39B3 solid;
    border-radius: 20px;
    width: 35vh;
    min-width: 350px;
    height: 4vh;
    min-height: 40px;
    padding: 0.75rem;
    font-size: 1rem;
    font-weight: bold;
  }

  input:focus {
    outline: none;
    background-color: #afa0d6;
    color: white;
    text-shadow: 0 1px 2px rgb(0, 0, 0, 0.5);
  }

  input::placeholder {
    font-weight: initial;
    letter-spacing: -1px;
    text-shadow: none;
  }

  input:focus::placeholder {
    color: transparent;
  }

  a {
    color: black;
    text-decoration: none;
    font-size: 0.9rem;
    font-weight: bold;
    letter-spacing: -1px;
  }

  button {
    background-color: #5E39B3;
    color: white;
    font-size: 1.125rem;
    font-weight: bold;
    border: none;
    border-radius: 20px;
    padding: 0.4rem 1.125rem;
    margin-bottom: 1.125rem;
    cursor: pointer;
  }

  img {
    width: 3vh;
  }

  p {
    margin: 0;
  }

  #login_container{
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: white;
    width: 45vh;
    min-width: 450px;
    border-radius: 20px;
    margin: auto;
    padding: 2rem;
  }

  #login_header{
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    align-self: flex-start;
  }

  #login_header h1 {
    margin: 0;
    font-size: 2rem;
    font-weight: bold;
    letter-spacing: -1px;
  }

  #login_body{
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 1.5rem;
  }

  #login_body article {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
  }

  #login_body > *:first-child {
    margin-bottom: 1.25rem;
  }

  #link{
    width: 75%;
    display: flex;
    justify-content: space-evenly;
    margin-bottom: 1.25rem;
  }

  #login_btn{
    padding: 0.5rem 2rem;
    width: 15vh;
    min-width: 150px;
  }

  .social_login {
    display: flex;
    justify-content: space-evenly;
    align-items: center;
    background-color: #F0EDE7;
    color: black;
    width: 30vh;
    min-width: 300px;
    height: 4.5vh;
    min-height: 45px;
    margin: 0.75rem;
  }

  /* #kakao{
    width: 3.5vh;
  } */
</style>