<template>
  <div id="pf-container">
    <section id="pf-header">
      <h1>비밀번호 찾기</h1>
      <h3>비밀번호를 잊으셨군요</h3>
      <h3>임시 비밀번호를 보내드릴게요</h3>
    </section>
    <section id="pf_-body">
      <article id="email-form">
        <span id="email-form-header">
          <label for="email">이메일</label>
          <span>
            <input type="checkbox" id="checkbox">
            <label for="checkbox">여기로 받을게요</label>
          </span>
        </span>
        <input type="email" 
        id="email"
        v-model="credentials.email"
        placeholder="등록하신 이메일을 입력해주세요.">
        <!-- <span v-if="credentials.beforePw">
          <p v-if="!isValid.validatePw" class="warn">
            사용할 수 없는 비밀번호에요.
          </p>
          <p v-if="isValid.validatePw" class="collect">
            사용할 수 있는 비밀번호입니다.
          </p>
        </span> -->
      </article>
      <article id="te-form">
        <label for="target-email">전달받을 이메일</label>
        <input type="text" 
        id="phone"
        v-model="credentials.tel"
        @input="pwCheck"
        placeholder="등록하신 휴대전화를 입력해주세요.">
        <span v-if="credentials.nextPw">
          <p v-if="!isValid.validateNextPw" class="warn">
            사용할 수 없는 비밀번호에요.
          </p>
          <p v-if="isValid.validateNextPw" class="collect">
            사용할 수 있는 비밀번호입니다.
          </p>
        </span>
      </article>
      <article id="p_conf_form">
        <label for="pw_conf">비밀번호 확인</label>
        <input type="password" id="pw_conf"
        v-model="credentials.pwConf"
        @input="pwConfCheck"
        placeholder="비밀번호를 다시 입력해주세요.">
        <span v-if="credentials.pwConf">
          <p v-if="!isValid.validatePwConf" class="warn">
            비밀번호가 맞지 않아요.
          </p>
          <p v-if="isValid.validatePwConf" class="collect">
            비밀번호가 일치합니다.
          </p>
        </span>
      </article>
      <a href="">비밀번호를 잊었나요?</a>
      <article id="pc_buttons">
        <button>변경하기</button>
        <button @click="backTo">뒤로가기</button>
      </article>
    </section>
  </div>
</template>

<script>
export default {
  data: function(){
    return {
      credentials : {
        beforePw: null,
        nextPw: null,
        pwConf: null
      },
      isValid: {
        validateNextPw: false,
        validatePwConf: false,
      }
    }
  },
  methods: {
    pwCheck: function(){
      if (this.credentials.nextPw && this.credentials.nextPw.length >= 8 && this.credentials.nextPw.length <= 20){
        this.isValid.validateNextPw = true
      }
      else {
        this.isValid.validateNextPw = false
      }
    },
    pwConfCheck: function(){
      if (this.credentials.pwConf && this.credentials.nextPw === this.credentials.pwConf){
        this.isValid.validatePwConf = true
      }
      else {
        this.isValid.validatePwConf = false
      }
    },
    backTo: function(){
      this.$router.push('/setting')
    }
  }
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
    border-radius: 30px;
    width: 100%;
    min-width: 300px;
    height: 5vh;
    min-height: 40px;
    padding: 0.75rem;
    font-size: 4rem;
    letter-spacing: -1px;
    font-weight: bold;
  }

  input:focus {
    outline: none;
    background-color: #afa0d6;
    color: white;
    text-shadow: 0 1px 2px rgb(0, 0, 0, 0.5);
  }

  input::placeholder {
    font-size: 1.25rem !important;
    font-weight: initial;
    text-shadow: none;
    position: absolute;
    top: 18%;
  }

  input:focus::placeholder {
    color: transparent;
  }

  h1 {
    font-size: 2.5rem;
    font-weight: bold;
    margin-bottom: 4rem;
  }

   h3 {
    font-size: 1.5rem;
    font-weight: bold;
    margin: 0;
  }

  p {
    margin: 0 0.5rem;
  }

  button {
    background-color: #5E39B3;
    color: white;
    font-size: 1.125rem;
    font-weight: bold;
    border: none;
    border-radius: 20px;
    padding: 0.4rem 1.5rem;
    margin: 1rem 2rem 1rem 0;
    cursor: pointer;
    line-height: 2rem;
  }

  .warn {
    color: rgb(240, 90, 90);
  }

  .collect {
    color: green;
  }

  #pf-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 65%;
    margin: 0 auto;
  }

  #pf-header {
    display: flex;
    flex-direction: column;
    align-self: flex-start;
    align-items: flex-start;
    margin: 2rem 1rem;
  }

  #pf-body {
    display: flex;
    flex-direction: column;
    /* align-items: center; */
    margin: 1rem;
  }

  #pf-body article {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    padding: 1rem 0;
    width: 100%;
  }

  #email-form-header {
    display: flex;
    justify-content: space-between;
    width: 100%;
  }

  #pf-body a {
    align-self: flex-end;
    text-decoration: none;
    color: rgb(85, 85, 255);
    font-size: 1.125rem;
    font-weight: bold;
    margin: 1rem 0;
  }

  #pf-buttons {
    display: flex;
    flex-direction: row !important;
    justify-content: center;
    margin: 1rem;
  }

  #pf-buttons > *:last-child {
    background-color: #777777;
  }
</style>