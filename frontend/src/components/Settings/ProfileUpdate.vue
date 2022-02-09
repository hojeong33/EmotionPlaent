<template>
  <div id="pu_container">
    <section id="pu_header">
      <h1>프로필 변경</h1>
    </section>
    <section id="pu_body">
			<article id="profile_head">
				<img id="profile_img" :src="this.$store.state.userInfo.profileImg" alt="">
        <p id="user_id">{{ this.$store.state.userInfo.nickname }}</p>
        <div id="profile_img_change">
          <button id="profile_img_change_button" @click="profileImgChangeModal">사진 변경</button>
        </div>
			</article>
			<br>
			<article id="pu_form">
        <label for="username">닉네임</label>
        <input 
        id="nickname"
        v-model="credentials.beforeNick"
        @input="checkNickname">
			</article>
			<br>
			<article id="pu_form">
				<p id="introduce">소개</p>
        <textarea  
        id="short_comment"
        placeholder="대충디폴트소개글"></textarea>
			</article>
			
			<br>
      <article id="pu_form">
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
			<br>
      <article id="pu_form">
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
      <!-- <article id="pin-form">
        <span>
          <label for="pin">PIN 번호</label>
          <img src="../../assets/images/icons/help.png" alt="help" id="help"
          @mouseover="help = true" @mouseout="help = false">
          <p v-show="help" id="help-message">
            PIN 번호는 회원 가입시 설정한 추가 비밀번호입니다.
            PIN 번호를 분실하였을 경우 고객센터 문의를 이용해주세요.
          </p>
        </span>
        <input type="password" id="pin"
        v-model="credentials.pin"
        placeholder="등록하신 PIN 번호를 입력해주세요.">
      </article> -->
      <a @click="pwFind">비밀번호를 잊었나요?</a>
      <br>
      <article id="pu_form_radio">
        <label for="">계정 공개 여부</label>
        <div id="check_radio">
          <div id="on">
            <input type="radio" id="show_all" style="width:20px;height:20px;border:1px;"
            :checked="this.publish == 1 ? 'checked': false" readonly>
            <h5>모두에게 공개</h5>
          </div>
          <div id="off">
            <input type="radio" id="show_followers" style="width:20px;height:20px;border:1px;"
            :checked="this.publish == 2 ? 'checked': false" readonly>
            <h5>팔로워에게 공개</h5>
          </div>
        </div>
        <br>
      </article>
      <article id="pu_buttons">
        <button id="pu_button">변경하기</button>
        <button id="pu_button" @click="go_to_back">뒤로가기</button>
      </article>
    </section>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data: function(){
    return {
      credentials : {
        beforeNick: null,
        beforeIntro: null,
        beforePw: null,
        nextPw: null,
        pwConf: null
      },
      isValid: {
        validateNextPw: false,
        validatePwConf: false,
      },
      help: false
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
    pwFind(){
      this.$router.push('/setting/password-find')
    },
    go_to_back: function(){
      this.$router.go(-1)
    },
    profileImgChangeModal:function(){
      this.$store.commit('profileImgChangeModalActivate')
		},
    checkNickname: function(el){
      this.credentials.beforeNick = el.target.value
      if (this.credentials.beforeNick !== this.$store.state.userInfo.nickname) {
        if (this.credentials.beforeNick.length >= 2 && this.credentials.beforeNick.length <= 10) {
          // this.credentials.beforeNick = el.target.value // 한글 입력 이슈 해결하기 위해 사용. 한박자 느린거?
          axios({
            method: 'get',
            url: 'http://13.125.47.126:8080/users/checkByNickname/' + this.credentials.beforeNick,
            })
            .then(() => { //중복 닉네임 없는 경우
              // this.isValid.validateNicknamecheck = true
              console.log('중복없다~')
            })
            .catch((el) => { //중복 닉네임 있는 경우
              // this.isValid.validateNicknamecheck = false
              console.log(el.response)
              console.log('중복있어')
          })
        }
        else {
          // p태그 만들어서 보여주고, 밑에 else에서 지우기
          alert('닉네임 길이는 2자 이상 10자 이하로 만들어주세요')
        }
      }
    },
    tel_helper: function(event){
      const nums = this.credentials.tel.length
      const n = this.credentials.tel.charCodeAt(nums-1)
      const poss = ['010', '011', '012', '013', '014',
                    '015', '016', '017', '018', '019']
      console.log(nums)
      if (event.inputType == 'deleteContentBackward'){
        if (nums == 3 || nums == 8){
          this.credentials.tel = this.credentials.tel.slice(0, nums - 1)
        }
        return
      }
      if (n > 47 && n < 58){
        if (nums == 3 || nums == 8){
          this.credentials.tel += '-'
        }
      }
      else {
        this.credentials.tel = this.credentials.tel.slice(0, nums - 1)
      }
      if (nums == 13 && poss.indexOf(this.credentials.tel.slice(0,3)) > -1){
        console.log(poss.indexOf(this.credentials.tel.slice(0,3)))
        console.log(nums)
        this.telCheck()
      }
      else {
        this.isValid.validateTel = false
      }
    },
    telCheck: function(){
      axios({
        method: 'get',
        url: 'http://13.125.47.126:8080/users/checkByTel/' + this.credentials.tel
      })
      .then(res => {
        console.log(res)
        if (res.data){
          this.isValid.validateTel = true
        }
        else {
          this.isValid.validateTel = false
        }
      })
    },
  }, 
  created() {
    this.credentials.beforeNick = this.$store.state.userInfo.nickname
  }
}
</script>

<style scoped>
  label {
    color: black;
    font-weight: bold;
    margin-left: 0.5rem;
    font-size: 1.5rem;
		margin-right: auto;
		margin-bottom: 0.5rem;
  }
	#introduce {
		color: black;
    font-weight: bold;
    margin-left: 0.5rem;
    font-size: 1.5rem;
		margin-right: auto;
		margin-bottom: 0.5rem;
	}

  #nickname {
    border: 2px #5E39B3 solid;
    border-radius: 30px;
    width: 95%;
    min-width: 300px;
    height: 5vh;
    min-height: 40px;
    padding: 0.75rem;
    font-size: 3.5rem;
    letter-spacing: -1px;
    font-weight: bold;
		margin-left: 1rem;
  }

  #intro {
    border: 2px #5E39B3 solid;
    border-radius: 30px;
    width: 95%;
    min-width: 300px;
    height: 5vh;
    min-height: 40px;
    padding: 0.75rem;
    font-size: 3.5rem;
    letter-spacing: -1px;
    font-weight: bold;
		margin-left: 1rem;
  }

  #password {
    border: 2px #5E39B3 solid;
    border-radius: 30px;
    width: 95%;
    min-width: 300px;
    height: 5vh;
    min-height: 40px;
    padding: 0.75rem;
    font-size: 3.5rem;
    letter-spacing: -1px;
    font-weight: bold;
		margin-left: 1rem;
  }

  #pw_conf {
    border: 2px #5E39B3 solid;
    border-radius: 30px;
    width: 95%;
    min-width: 300px;
    height: 5vh;
    min-height: 40px;
    padding: 0.75rem;
    font-size: 3.5rem;
    letter-spacing: -1px;
    font-weight: bold;
		margin-left: 1rem;
  }
  
  #next_pw {
    border: 2px #5E39B3 solid;
    border-radius: 30px;
    width: 95%;
    min-width: 300px;
    height: 5vh;
    min-height: 40px;
    padding: 0.75rem;
    font-size: 3.5rem;
    letter-spacing: -1px;
    font-weight: bold;
		margin-left: 1rem;
  }

  #nickname:focus {
    outline: none;
    background-color: #afa0d6;
    color: white;
    text-shadow: 0 1px 2px rgb(0, 0, 0, 0.5);
  }
  #intro:focus {
    outline: none;
    background-color: #afa0d6;
    color: white;
    text-shadow: 0 1px 2px rgb(0, 0, 0, 0.5);
  }
  #password:focus {
    outline: none;
    background-color: #afa0d6;
    color: white;
    text-shadow: 0 1px 2px rgb(0, 0, 0, 0.5);
  }
  #pw_conf:focus {
    outline: none;
    background-color: #afa0d6;
    color: white;
    text-shadow: 0 1px 2px rgb(0, 0, 0, 0.5);
  }
  #next_pw:focus {
    outline: none;
    background-color: #afa0d6;
    color: white;
    text-shadow: 0 1px 2px rgb(0, 0, 0, 0.5);
  }

  #nickname::placeholder {
    font-size: 1.1rem !important;
    font-weight: initial;
    text-shadow: none;
    position: absolute;
    top: 18%;
  }
  #nickname:focus::placeholder {
    color: transparent;
  }

  #intro::placeholder {
    font-size: 1.1rem !important;
    font-weight: initial;
    text-shadow: none;
    position: absolute;
    top: 18%;
  }
  #intro:focus::placeholder {
    color: transparent;
  }

  #password::placeholder {
    font-size: 1.1rem !important;
    font-weight: initial;
    text-shadow: none;
    position: absolute;
    top: 18%;
  }
  #password:focus::placeholder {
    color: transparent;
  }

  #pw_conf::placeholder {
    font-size: 1.1rem !important;
    font-weight: initial;
    text-shadow: none;
    position: absolute;
    top: 18%;
  }
  #pw_conf:focus::placeholder {
    color: transparent;
  }

  #next_pw::placeholder {
    font-size: 1.1rem !important;
    font-weight: initial;
    text-shadow: none;
    position: absolute;
    top: 18%;
  }
  #next_pw:focus::placeholder {
    color: transparent;
  }
	
	textarea {
		border: 2px #5E39B3 solid;
    border-radius: 20px;
    width: 95%;
    min-width: 300px;
    min-height: 40px;
    padding: 0.75rem;
    font-size: 1rem;
    letter-spacing: -1px;
    font-weight: bold;
		margin-left: 1rem;
	}
	textarea:focus {
    outline: none;
    background-color: #afa0d6;
    color: white;
    text-shadow: 0 1px 2px rgb(0, 0, 0, 0.5);
  }
	textarea::placeholder {
    font-size: 1.1rem !important;
    font-weight: initial;
    text-shadow: none;
    position: absolute;
    top: 18%;
  }

  textarea:focus::placeholder {
    color: transparent;
  }
	#nickname {
		font-size: 1rem;
		letter-spacing: -1px;
    font-weight: bold;
	}
  h1 {
    font-size: 2.5rem;
    font-weight: bold;
    margin-bottom: 4rem;
  }
	#profile_head{
		display: flex;
		flex-direction: row;
	}

	#profile_img {
		width:130px;
		height: 130px;
		border-radius: 130px;
    margin-left: 0.5rem;		
	}

	#user_id {
		text-align: right;
		font-size: 1.8rem;
		font-weight: bold;
		margin-top: auto;
		margin-bottom: auto;
		margin-left: 1rem;
	}
	#profile_img_change {
		margin-left:auto;
		margin-top: auto;
		margin-bottom: auto;
	}

	#profile_img_change_button {
		background-color: #5E39B3;
    color: white;
		width: 6.9rem;
		height: 2rem;
    margin-right: 1rem;
		font-size: 0.9rem;
		font-weight: bold;
		border-radius: 20px;
		border: none;
	}

  #pu_form {
    text-align: left;
  }
  
  #pu_form_radio {
    text-align: left;
  }

  #check_radio {
    display: flex;
    justify-content: space-evenly;
    width: 95%;
    min-width: 300px;
    height: 5vh;
    min-height: 40px;
    padding: 0.75rem;
    font-size: 3.5rem;
    letter-spacing: -1px;
    font-weight: bold;
		margin-left: 1rem;
  }

  #on {
    display: flex;
    width: 39%;
  }

  #off {
    display: flex;
    width: 39%;
  }

  h3 {
    font-size: 1.5rem;
    font-weight: bold;
    margin: 0;
  }

  h5 {
    font-size: 1.2rem;
  }

  p {
    margin: 0 0.5rem;
  }

  #pu_button {
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

  #pu_container {
    width: 80%;
    margin: 0 auto;
  }

  #pu_header {
		text-align: left;
    margin: 2rem 1rem;
  }

  #pu_body {
    width: 80%;
    margin: auto;
  }

  #pu_body a {
		display:flex;
		justify-content: right;
		margin-right: 0.5rem;
    text-decoration: none;
    color: rgb(85, 85, 255);
    font-size: 1.125rem;
    font-weight: bold;
    cursor: pointer;
  }

  #pu_buttons {
    display: flex;
    flex-direction: row !important;
    justify-content: center;
    margin: 1rem 4rem 1rem;
  }
  #pu_button {
    margin-left:auto;
    margin-right: auto;
  }

  #pu_buttons > *:last-child {
    background-color: #777777;
  }

  
</style>