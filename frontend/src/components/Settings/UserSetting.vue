<template>
  <div id="user_setting_container">
    <h1>계정 정보</h1>
    <section id="us_header">
      <article id="us_header_img">
        <span id="opacity"></span>
      </article>
      <article id="us_header_info">
        <span>
          <h2>나는멋쟁이</h2>
          <button>로그아웃</button>
        </span>
        <span>
          <h4>가입일 1234.56.78</h4>
          <button>프로필사진 변경</button>
        </span>
      </article>
    </section>
    <section id="us_body">
      <article id="us_email">
        <h3>이메일</h3>
        <p>test@ssafy.com</p>
      </article>
      <article id="us_password">
        <h3>비밀번호</h3>
        <a href="#" @click="passwordChange">비밀번호 변경...</a>
      </article>
      <article id="us_birth">
        <h3>생년월일</h3>
        <span>
          <p>1994.11.07</p>
          <span>
            <input type="checkbox" id="show_birth">
            <label for="show_birth">비공개</label>
          </span>
        </span>
      </article>
      <article id="us_show">
        <h3>계정 공개</h3>
        <span>
          <span>
            <input type="radio" id="show_all">
            <label for="show_all">모두에게 공개</label>
          </span>
          <span>
            <input type="radio" id="show_followers">
            <label for="show_followers">팔로워에게 공개</label>
          </span>
        </span>
      </article>
      <article id="us_history">
        <h3>활동 내역</h3>
        <a href="#">자세히...</a>
      </article>
    </section>
    <button id="user_delete">회원 탈퇴</button>
  </div>
</template>

<script>
export default {
  methods: {
    passwordChange: function(){
      this.$emit('password-change')
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

  #user_setting_container {
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
    background-color: rgb(0, 0, 0, 0.5);
    position: absolute;
    left: 0;
  }

  #us_header_img {
    width: 100%;
    height: 100%;
    background-image: url('../../assets/images/KakaoTalk_20220112_170830193.jpg');
    background-repeat: no-repeat;
    background-position: center;
    background-size: cover;
    position: absolute;
  }

  #us_header_info {
    z-index: 1;
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
    width: 100%;
    color: white;
    padding: 1rem 1.5rem;
  }

  #us_header_info > *:first-child {
    display: flex;
    align-items: center;
  }

  #us_header_info > *:first-child > button {
    background-color: rgb(200, 100, 100);
  }

  #us_header_info *:last-child {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  #us_body {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    padding-top: 1rem;
  }

  #us_body article {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    width: 100%;
    padding: 1rem 2rem;
  }

  #us_body article *:last-child {
    padding: 0.5rem 2rem;
  }

  #us_body article > span {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
  }

  #user_delete {
    background-color: rgb(200, 100, 100);
    align-self: center;
    margin-top: rem;
  }
</style>