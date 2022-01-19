<template>
  <div class="login_container">
    <div class="login">
      <h1>Login</h1>
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
      <button @click="login">LOGIN</button>
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
  @import '../../assets/styles/plainstyle.css';
</style>>