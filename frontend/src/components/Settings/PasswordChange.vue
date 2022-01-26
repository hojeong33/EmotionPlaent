<template>
  <div id="password_change_container">
    <section id="pc_header">
      <h1>비밀번호 변경</h1>
      <h2>계정 보호를 위해 주기적으로</h2>
      <h2>비밀번호를 교체해주세요</h2>
    </section>
    <section id="pc_body">
      <article id="before_pw_form">
        <label for="before_pw">비밀번호</label>
        <input type="password" 
        id="before_pw"
        v-model="credentials.beforePw"
        placeholder="사용중인 비밀번호를 입략해주세요.">
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
        <label for="next_pw">비밀번호</label>
        <input type="password" 
        id="next_pw"
        v-model="credentials.nextPw"
        @input="pwCheck"
        placeholder="비밀번호는 8자 이상, 20자 이하입니다.">
        <span v-if="credentials.nextPw">
          <p v-if="!isValid.next_pw" class="warn">
            사용할 수 없는 비밀번호에요.
          </p>
          <p v-if="isValid.validatePw" class="collect">
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
        validatePw: false,
        validatePwConf: false,
      }
    }
  },
  methods: {
    pwCheck: function(){
      if (this.credentials.pw && this.credentials.pw.length >= 8 && this.credentials.pw.length <= 20){
        this.isValid.validatePw = true
      }
      else {
        this.isValid.validatePw = false
      }
    },
    pwConfCheck: function(){
      if (this.credentials.passwordConfirmation && this.credentials.pw === this.credentials.passwordConfirmation){
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
    border-radius: 20px;
    width: 30vh;
    min-width: 300px;
    height: 4vh;
    min-height: 40px;
    padding: 0.75rem;
    font-size: 1.125rem;
    font-weight: bold;
  }

  input:focus {
    outline: none;
    background-color: #afa0d6;
    color: white;
    text-shadow: 0 1px 2px rgb(0, 0, 0, 0.5);
  }

  input::placeholder {
    font-size: 0.9rem;
    font-weight: initial;
    letter-spacing: -1px;
    text-shadow: none;
  }

  input:focus::placeholder {
    color: transparent;
  }

  .warn {
    color: red;
  }

  .collect {
    color: green;
  }
</style>