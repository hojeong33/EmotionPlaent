<template>
  <div id="pc_container">
    <section id="pc_header">
      <h1>비밀번호 변경</h1>
      <h3>계정 보호를 위해 주기적으로</h3>
      <h3>비밀번호를 교체해주세요</h3>
    </section>
    <section id="pc_body">
      <article id="before_pw_form">
        <label for="before_pw">비밀번호</label>
        <input type="password" 
        id="before_pw"
        v-model="credentials.beforePw"
        placeholder="사용중인 비밀번호를 입력해주세요.">
        <!-- <span v-if="credentials.beforePw">
          <p v-if="!isValid.validatePw" class="warn">
            사용할 수 없는 비밀번호에요.
          </p>
          <p v-if="isValid.validatePw" class="collect">
            사용할 수 있는 비밀번호입니다.
          </p>
        </span> -->
      </article>
      <article id="next_pw_form">
        <label for="next_pw">변경할 비밀번호</label>
        <input type="password" 
        id="next_pw"
        v-model="credentials.nextPw"
        @input="pwCheck"
        placeholder="비밀번호는 8자 이상, 20자 이하입니다.">
        <span v-if="credentials.nextPw">
          <p v-if="!isValid.validateNextPw" class="warn">
            사용할 수 없는 비밀번호에요.
          </p>
          <p v-if="isValid.validateNextPw" class="collect">
            사용할 수 있는 비밀번호입니다.
          </p>
        </span>
      </article>
      <article id="pw_confirmation_form">
        <label for="pw_confirmation">비밀번호 확인</label>
        <input type="password" id="pw_confirmation"
        v-model="credentials.pwConfirmation"
        @input="pwConfCheck"
        placeholder="비밀번호를 다시 입력해주세요.">
        <span v-if="credentials.pwConfirmation">
          <p v-if="!isValid.validatePwConf" class="warn">
            비밀번호가 맞지 않아요.
          </p>
          <p v-if="isValid.validatePwConf" class="collect">
            비밀번호가 일치합니다.
          </p>
        </span>
      </article>
      <article id="pc_buttons">
        <button>확인</button>
        <button>뒤로가기</button>
      </article>
      <a href="">비밀번호를 잊었나요?</a>
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
        pwConfirmation: null
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
      if (this.credentials.pwConfirmation && this.credentials.nextPw === this.credentials.pwConfirmation){
        this.isValid.validatePwConf = true
      }
      else {
        this.isValid.validatePwConf = false
      }
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
    font-size: 1.5rem;
    font-weight: bold;
  }

  input:focus {
    outline: none;
    background-color: #afa0d6;
    color: white;
    text-shadow: 0 1px 2px rgb(0, 0, 0, 0.5);
  }

  input::placeholder {
    font-size: 1.125rem;
    font-weight: initial;
    letter-spacing: -1px;
    text-shadow: none;
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
    margin-bottom: 0;
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
    color: red;
  }

  .collect {
    color: green;
  }

  #pc_container {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  #pc_header {
    display: flex;
    flex-direction: column;
    align-self: flex-start;
    align-items: flex-start;
    margin: 3rem 2rem;
  }

  #pc_body {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 80%;
    margin: 1rem;
  }

  #pc_body article {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    padding: 1rem 3rem;
    width: 100%;
  }

  #pc_body a {
    align-self: flex-start;
    text-decoration: none;
    color: rgb(85, 85, 255);
    font-size: 1.125rem;
    font-weight: bold;
    margin: 1rem 3rem;
  }

  #pc_buttons {
    display: flex;
    flex-direction: row !important;
    justify-content: flex-start;
    margin: 1rem;
  }

  #pc_buttons > *:last-child {
    background-color: #777777;
  }
</style>