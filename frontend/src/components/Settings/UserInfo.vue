<template>
  <section id="us_container">
    <h1>계정 정보</h1>
    <article id="us_header">
      <div id="us_header_img">
        <img :src="this.$store.state.userInfo.profileImg" alt="" id="profileImg">
        <span id="opacity"></span>
      </div>
      <div id="us_header_info">
        <span>
          <h2>{{ this.$store.state.userInfo.nickname }}</h2>
        </span>
        <span id="change_btn">
          <!-- <button>프로필사진 변경</button> -->
          <button @click="go_to_profilupdate">시민권 변경</button>
        </span>
      </div>
    </article>
    <article id="us_body">
      <div id="us_email">
        <h3>이메일</h3>
        <p>{{ this.$store.state.userInfo.email }}</p>
      </div>
      <!-- <div id="us_password">
        <h3>비밀번호</h3>
        <a href="" @click="go_to_passwordchange">비밀번호 변경...</a>
      </div> -->
      <div id="short_comment">
        <h3>소개</h3>
        <p>{{ this.$store.state.userInfo.intro }}</p>
      </div>
      <div id="us_birth">
        <h3>생년월일</h3>
        <span>
          <p>{{ this.$store.state.userInfo.birth }}</p>
          <span>
            <input type="checkbox" id="show_birth" v-model="birthShow">
            <label for="show_birth">비공개</label>
          </span>
        </span>
      </div>
      <div id="us_show">
        <h3>계정 공개</h3>
        <span>
          <span>
            <input type="radio" id="show_all" onclick="return false;"
            :checked="this.publish == 1 ? 'checked': false" readonly>
            <label for="show_all">모두에게 공개</label>
          </span>
          <span>
            <input type="radio" id="show_followers" onclick="return false;"
            :checked="this.publish == 2 ? 'checked': false" readonly>
            <label for="show_followers">팔로워에게 공개</label>
          </span>
        </span>
      </div>
      <!-- <div id="us_history">
        <h3>활동 내역</h3>
        <a href="">자세히...</a>
      </div> -->
    </article>
    <button id="withdrawal" @click="go_to_withdrawal">회원 탈퇴</button>
  </section>
</template>

<script>
// import LogoutModal from '@/components/Modal/LogoutModal.vue'
const session = window.sessionStorage

export default {
  data: function(){
    return {
      birthShow: false,
      publish: 1,
      // modalActive: false,
    }
  },
  // components: {
  //   LogoutModal,
  // },
  methods: {
    go_to_passwordchange: function(){
      this.$router.push('/setting/password')
    },
    go_to_withdrawal: function(){
      this.$router.push('/setting/withdrawal')
    },
    go_to_profilupdate: function () {
      this.$router.push('/setting/profile-update')
    },
    logoutModal:function(){
      this.$store.commit('logoutModalActivate')
		},
    signOut() {
      const authInst = window.gapi.auth2.getAuthInstance();
      console.log('signout called', authInst)
      authInst.signOut()
      .then(() => {
        // eslint-disable-next-line
        console.log('User Signed Out!!!');
        authInst.disconnect();
        session.clear();
      })
      .then(() => {
        window.location.reload()
      })
      .catch(() => alert('fail'))
    },
    // onPublish() {
    //   this.showAll
    // }
  },
  created() {
    if (this.$store.state.userInfo.publish === 1) {
      this.showAll = true
      this.showFollow = false
    }
    else {
      this.showAll = false
      this.showFollow = true
    }
  }
}
</script>

<style scoped>
  h1 {
    margin: 0 1rem 1rem;
    font-size: 2rem;
    font-weight: bold;
  }

  h2 {
    margin: 0 1rem;
    font-size: 2.5rem;
    font-weight: bold;
  }

  h3 {
    margin: 0 1rem;
    font-size: 1.75rem;
    font-weight: bold;
    letter-spacing: 1px;
  }

  h4 {
    font-size: 1.25rem;
    margin: 0 1rem;
    letter-spacing: 1px;
  }

  a {
    text-decoration: none;
    color: rgb(85, 85, 255);
    font-size: 1.25rem;
    font-weight: bold;
  }

  p {
    margin: 0;
    font-size: 1.25rem;
    font-weight: bold;
  }

  label {
    font-size: 1.25rem;
    font-weight: bold;
  }

  button {
    background-color: #5E39B3;
    color: white;
    font-size: 1.125rem;
    font-weight: bold;
    border: none;
    border-radius: 20px;
    padding: 0.4rem 1.125rem;
    cursor: pointer;
  }

  #us_container {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
  }

  #us_header {
    display: flex;
    flex-wrap: wrap;
    width: 100%;
    height: 35vh;
    position: relative;
  }

  #opacity {
    width: 100%;
    height: 100%;
    background-color: rgb(0, 0, 0, 0.2);
    position: absolute;
    left: 0;
  }

  #us_header_img {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
    height: 100%;
    background-image: url('../../assets/images/emotions/cover.png');
    background-repeat: no-repeat;
    background-position: center;
    background-size: cover;
    position: absolute;
  }

  #us_header_info {
    display: flex;
    /* flex-direction: column; */
    justify-content: space-between;
    align-items: flex-end;
    width: 100%;
    color: white;
    z-index: 12;
    padding: 1rem 1.5rem;
  }

  #us_header_info > *:first-child {
    z-index:10;
    display: flex;
    align-items: center;
  }

  #us_header_info *:last-child {
    z-index: 10;
    display: flex;
    /* justify-content: space-between; */
    /* align-items: center; */
  }

  #us_body {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    padding: 3rem 4rem;
  }

  #us_body div {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    width: 100%;
    padding: 1rem 2rem;
  }

  #us_body div *:last-child {
    padding: 0.5rem 2rem;
  }

  #us_body div > span {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
  }

  #withdrawal {
    background-color: rgb(240, 90, 90);
    align-self: center;
  }

  #change_btn {
    display: flex;
    justify-self: right;
  }

  #profileImg {
    z-index: 10;
    width: 200px;
    height: 200px;
    border-radius: 50%;
  } 
</style>