<template>
  <div id="signup_container">
    <section id="signup_header">
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
    </section>
    <section id="signup_form">
      <article id="email_form">
        <label for="email">이메일</label>
        <input type="email"
        id="email"
        v-model="credentials.email"
        placeholder="사용중인 이메일을 입력해주세요."
        autocomplete="off"
        @input = "validateEmail">
        <span v-if="credentials.email && !isValid.validateEmailcheck">
          <p v-if="isValid.validateEmail" class="warn">
            사용중인 이메일이에요.
          </p>
          <p v-if="!isValid.validateEmail" class="warn">
            이메일 형식에 맞춰주세요. 
          </p>
        </span>
        <span v-if="isValid.validateEmailcheck" class="collect">
          <p>
            사용가능한 이메일입니다.
          </p>
        </span>
      </article>
      <article id="nickname_form">
        <label for="nickname">닉네임</label>
        <input type="text"
        id="nickname"
        v-model="credentials.nickname"
        placeholder="닉네임은 2자 이상, 10자 이하입니다."
        autocomplete="off"
        @input= "checkNickname">
        <span v-if="credentials.nickname">
          <p v-if="!isValid.validateNicknamecheck" class="warn">
            사용중인 닉네임이에요.
          </p>
          <p v-if="isValid.validateNicknamecheck" class="collect">
           사용가능한 닉네임입니다.
          </p>
        </span>
      </article>
      <article id="pw_form">
        <label for="pw">비밀번호</label>
        <input type="password" 
        id="pw"
        v-model="credentials.pw"
        @change="pwCheck"
        placeholder="비밀번호는 8자 이상, 20자 이하입니다.">
      </article>
      <article id="passwordConfirmation_form">
        <label for="passwordConfirmation">비밀번호 확인</label>
        <input type="password" id="passwordConfirmation"
        v-model="credentials.passwordConfirmation"
        @change="pwConfirmCheck"
        placeholder="비밀번호를 다시 입력해주세요.">
      </article>
      <article id="signup_form_personal">
        <div id="personal_form">
          <label for="birth">생년월일</label>
          <input type="date" id="birth"
          v-model="credentials.birth"
          >
          <label for="image_upload">프로필 사진</label>
          <button id="image_upload">이미지 업로드</button>
        </div>
        <img src="../../assets/images/profile.png" alt="profile image">
      </article>
      <button @click="signup" id="signup_btn">시민권 등록하기</button>
      <button id="back_to_btn">다음에 할게요</button>
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
          validateEmail: false, // 이메일 형식 체크
          validateEmailcheck : false, // 중복 이메일 여부
          validateNicknamecheck : false, // 중복 닉네임 여부
          validatePw: false, // 비밀번호 길이 체크
          validatePwConf: false, // 비밀번호와 비밀번호 확인 일치 여부
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
      pwCheck: function(){
        if (this.pw && this.pw.length < 8 || this.pw.length > 20){
          this.isValid.validatePw = true
        }
        else {
          this.isValid.validatePw = false
        }
      },
      pwConfCheck: function(){
        if (this.passwordConfirmation && this.pw === this.passwordConfirmation){
          this.isValid.validatePwConf = true
        }
        else {
          this.isValid.validatePwConf = false
        }
      }
    },
  }
</script>
<style scoped>
  label {
    color: #5E39B3;
    font-weight: bold;
    margin-left: 0.5rem;
    font-size: 1.125rem;
  }

  input {
    border: 2px #5E39B3 solid;
    border-radius: 20px;
    width: 30vh;
    min-width: 300px;
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

  p {
    margin-left: 0.5rem;
    font-size: 0.8rem;
  }

  img {
    width: 12vh;
    min-width: 120px;
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

  .warn {
    color: red;
  }

  .collect {
    color: green;
  }

  #signup_container {
    background-color: white;
    width: 45vh;
    min-width: 450px;
    height: 80vh;
    min-height: 850px;
    margin: auto;
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
    border-radius: 20px;
    padding: 2rem;
  }

  #signup_header {
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-items: flex-start;
    align-self: flex-start;
  }

  #signup_header h1 {
    margin: 0;
    font-size: 2rem;
    font-weight: bold;
    letter-spacing: -1px;
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
    padding: 1.5rem;
    overflow: unset;
  }

  #signup_form h1, article {
    margin-bottom: 1.25rem;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
  }

  #signup_form_personal {
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: space-between;
    align-items: center;
    align-self: stretch;
  }

  #personal_form {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
  }

  #birth {
    width: 16vh;
    min-width: 150px;
    margin-bottom: 1.25rem;
  }

  #signup_btn {
    font-size: 1.35rem;
    padding: 0.5rem 2rem;
    width: 25vh;
    min-width: 250px;
  }

  #back_to_btn {
    background-color: #777777;
    font-size: 1.35rem;
    padding: 0.5rem 2rem;
    width: 25vh;
    min-width: 250px;
  }

  ::-webkit-calendar-picker-indicator {
    margin: 0;
  }
</style>