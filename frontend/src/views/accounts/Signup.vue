<template>
  <div id="signup_container">
    <article id="signup_header">
      <h1>
        어서오세요!
      </h1>
      <span id="signup_header_title">
        <h1 style="color: #5E39B3">
          이모션 플래닛
        </h1>
        <h1>
          에
        </h1>
      </span>
      <h1>
        시민권을 등록해볼까요?👽
      </h1>
    </article>
    <section id="signup_form">
      <article id="email_form">
        <label for="email" style="color: #5E39B3">이메일</label>
        <input type="email"
        id="email"
        v-model="credentials.email"
        placeholder="e-mail"
        @input = "validateEmail">
        <span v-if="credentials.email && !isValid.validateEmailcheck">
          <p v-if="isValid.validateEmail" style="font-size:10px; color: red;">
            사용중인 이메일입니다.
          </p>
          <p v-if="!isValid.validateEmail" style="font-size:10px; color: red;">
            이메일 형식에 맞춰주세요. 
          </p>
        </span>
        <span v-if="isValid.validateEmailcheck" style="font-size:10px; color: green;">
          <p>
            사용가능한 이메일입니다.
          </p>
        </span>
      </article>
      <article id="nickname_form">
        <label for="nickname" style="color: #5E39B3">닉네임</label>
        <input type="text"
        id="nickname"
        v-model="credentials.nickname"
        placeholder="nickname"
        @input= "checkNickname">
        <span v-if="credentials.nickname">
          <p v-if="!isValid.validateNicknamecheck" style="font-size:10px; color: red;">
            사용중인 닉네임입니다.
          </p>
          <p v-if="isValid.validateNicknamecheck" style="font-size:10px; color: green;">
           사용가능한 닉네임입니다.
          </p>
        </span>
      </article>
      <div>
        <label for="pw" style="color: #5E39B3">password</label>
        <input type="password" 
        id="pw"
        v-model="credentials.pw"
        placeholder="password">
      </div>
      <div>
        <label for="passwordConfirmation" style="color: #5E39B3">비밀번호 확인</label>
        <input type="password" id="passwordConfirmation"
        v-model="credentials.passwordConfirmation"
        placeholder="passwordConfirmation">
      </div>
      <div>
        <label for="birth" style="color: #5E39B3">생년월일</label>
        <input type="date" id="birth"
        v-model="credentials.birth"
        >
      </div>
      <button @click="signup"> signup </button>
    </section>
  </div>
</template>


<script>
  import axios from 'axios'
  export default {
    name: 'Signup',
    data: function () {
      return {
        credentials: {
          email: null,
          nickname: null,
          pw: null,
          passwordConfirmation: null,
          birth: null,
        },
        isValid: {
          validateEmail: false, //이메일 형식 체크
          validateEmailcheck : false, // 중복 이메일 여부
          validateNicknamecheck : false, // 중복 닉네임 여부
        }
      }
    },
    methods: {
       validateEmail(){
        let regexp =
        /^[0-9a-zA-Z]([-.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
        this.isValid.validateEmail = regexp.test(this.credentials.email) ? true : false; //이메일 형식에 맞으면 true 아니면 false
        if(this.isValid.validateEmail){ //현재 이메일이 이메일 형식에 맞다면 중복검사 하러감
          this.checkEmail()
        }else{ //현재 이메일이 이메일 형식에 맞지 않다면 중복 이메일 여부를 false로 둠 => true값으로 변경됬을경우를 대비
          this.isValid.validateEmailcheck = false
        }
      },

      signup: function () {
        if (this.credentials.pw === this.credentials.passwordConfirmation){ // 입력 비밀번호가 일치하면 회원가입 (나중에 여기다 벨류체크)
          axios({
            method: 'post',
            url: 'http://127.0.0.1:8080/users/',
            data: this.credentials
          })
          .then( () => {
            if (confirm('가입이 완료되었습니다.')){
              this.$router.push({name:'Login'}) // 가입 완료시 로그인 페이지로 이동
            }
          })
          .catch(res => {
            alert(res.response.data.message) // 서버측에서 넘어온 오류 메시지 출력.
          })
        }
        else {
          alert('비밀번호를 확인해주세요.')
        }
        this.credentials.email ="";
        this.credentials.nickname="";
        this.credentials.pw = "";
        this.credentials.passwordConfirmation="";
        this.credentials.birth ="";
      },

      checkNickname(el){
        this.credentials.nickname = el.target.value // 한글 입력 이슈 해결하기 위해 사용. 한박자 느린거?
        axios({
            method: 'get',
            url: 'http://127.0.0.1:8080/users/checkByNickname/' + this.credentials.nickname,
          })
          .then(() => { //중복 닉네임 없는 경우
            this.isValid.validateNicknamecheck = true
          })
          .catch(() => { //중복 닉네임 있는 경우
            this.isValid.validateNicknamecheck = false
          })
      },

      checkEmail(){
          axios({
            method: 'get',
            url: 'http://127.0.0.1:8080/users/checkByEmail/' + this.credentials.email,
          })
          .then(() => { //중복 이메일 없는 경우
            this.isValid.validateEmailcheck = true
          })
          .catch(()=> { //중복 이메일 있는 경우
            this.isValid.validateEmailcheck = false
          })
      },
    },
  }
</script>
<style scoped>
  #signup_container {
    background-color: white;
    width: 40vh;
    min-width: 300px;
    height: 60vh;
    min-height: 700px;
    margin: auto;
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: center;
    align-items: center;
  }

  #signup_header {
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    align-items: flex-start;
  }

  #signup_header h1 {
    margin: 0;
  }

  #signup_header_title {
    display: flex;
    flex-wrap: nowrap;
  }

  #signup_form {
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
    padding: 2rem;
    overflow: unset;
  }

  #signup_form h1, div, article {
    margin-bottom: 2rem;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
  }

  #signup_form input {
    border: 1px #5E39B3 solid;
    border-radius: 10px;
    width: 15rem;
    min-width: 200px;
    height: 2rem;
    min-height: 30px;
    padding: 0.5rem;
    font-size: 0.9rem;
  }

</style>