<template>
  <div>
    <navigation></navigation>
    <side-profile-card
      :user-info="userInfo"
    >
    </side-profile-card>
    
    <div id="container">
      <div id="profile_container">
        <img src="https://www.thesprucepets.com/thmb/meRd41is751DsQQjofaiKV_ZUBg=/941x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/cat-talk-eyes-553942-hero-df606397b6ff47b19f3ab98589c3e2ce.jpg" id="profile_img">
        <div id="profile_card">
          <div id="name_card">
            <h1>{{ userInfo.username }}</h1>
            <button>프로필 수정</button>
          </div>
          <div id="info_card">
            <h3>게시글 {{ userInfo.posts }}</h3>
            <h3>팔로워 {{ userInfo.followings }}</h3>
            <h3>팔로잉 {{ userInfo.followers }}</h3>
          </div>
        </div>
        
      </div>
      <div id="tab">
        <span id="dot">
        </span>
        <span id="tab_names">
          <p>게시글</p>
          <p>찜목록</p>
        </span>
      </div>
      <tabs></tabs>
    </div>
  </div>  
</template>

<script>
import SideProfileCard from '../../components/SideProfileCard.vue'
import Navigation from '../../components/Navigation.vue'
import Tabs from './Tabs.vue'
export default {
  name: 'Mypage',
  components: {SideProfileCard, Navigation, Tabs},
  data() {
    return {
      userInfo: {
      username: '최강상후',
      posts: 0,
      followings: 0,
      followers: 20100,
      },
      postlist: [],
      picklist: [],
    }
  }
}
</script>

<style scoped>
  #container {
    width: 100vh;
    min-height: 100vh;
    margin: auto;
    border-left: 0.1rem solid gainsboro;
    border-right: 0.1rem solid gainsboro;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start;
  }

  #profile_container {
    display: flex;
    flex-direction: row;
    justify-content: center;
    background-color: white;
    width: 100%;
    padding: 2rem;
  }

  #profile_card {
    width: 50%;
    margin-left: 1rem;
  }

  #name_card {
    display: flex;
    direction: row;
    justify-content: space-between;
    align-items: center;
  }
  
  #info_card {
    display: flex;
    direction: row;
    justify-content: space-between;
  }
  
  #card {
    display: flex;
    flex-direction: row;
    justify-content: center;
  }

  #profile_img {
    width: 12vh;
    height: 12vh;
    min-width: 50px;
    border-radius: 50%;
  }

  h1 {
  color: black;
  font-size: 2rem;
  font-weight: bold;
  }

  h3 {
  font-size: 1.25rem;
  font-weight: bold;
  letter-spacing: -1px;
  line-height: 75%;
  }

  label {
  color: #5E39B3;
  font-size: 1.125rem;
  font-weight: bold;
  letter-spacing: -0.5px;
  margin-left: 0.5rem;
  }

  button {
  background-color: #5E39B3;
  color: white;
  font-size: 1rem;
  font-weight: bold;
  border: 1px #5E39B3 solid;
  border-radius: 20px;
  padding: 0.5rem 0.5rem;
  margin-bottom: 1rem;
  cursor: pointer;
  line-height: 1rem;
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

  #tab {
    display: flex;
    justify-content: center;
    position: relative;
    border-top: 0.1rem solid gainsboro;
    width: 100%;
  }

  #dot {
    width: 25px;
    height: 25px;
    border-radius: 50%;
    position: absolute;
    top: -28%;
    left: 43%;
    background-color: #5E39B3;
  }

  #tab_names {
    margin-top: 2%;
    width: 17%;
    display: flex;
    direction: row;
    justify-content: space-between;
  }

  </style>