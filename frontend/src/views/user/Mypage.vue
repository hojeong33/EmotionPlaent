<template>
  <div>
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
            <h3>팔로우 {{ userInfo.followings }}</h3>
            <h3>팔로워 {{ userInfo.followers }}</h3>
          </div>
        </div>
      </div>
      <div id="tab">
        <span id="dot1" v-if="onFeed">
        </span>
        <span id="dot2" v-if="onPick">
        </span>
        <span id="tab_names">
          <p @click="toggleFeed" :class="onFeed ? 'active': 'inactive'">게시글</p>
          <p @click="togglePick" :class="onPick ? 'active': 'inactive'">찜 목록</p>
        </span>
      </div>
      <div v-if="onFeed === true">
        <user-feed></user-feed>
      </div>
      <div v-else-if="onPick === true">
        <pick-list></pick-list>
      </div>
    </div>
  </div>  
</template>

<script>
import SideProfileCard from '@/components/SideProfileCard.vue'
import PickList from '@/components/user/PickList.vue'
import UserFeed from '@/components/user/UserFeed.vue'

export default {
  name: 'Mypage',
  components: {SideProfileCard, PickList, UserFeed},
  data() {
    return {
      userInfo: {
      username: '최강상후',
      mood: 3,
      posts: 0,
      followings: 0,
      followers: 20100,
      },
      onFeed: true,
      onPick: false,
      postlist: [],
      picklist: [],
    }
  },
  methods: {
    toggleFeed: function () {
      this.onFeed = true
      this.onPick = false
    },
    togglePick: function () {
      this.onPick = true
      this.onFeed = false
    },
  },
}
</script>

<style scoped>
  #mypage {
    width: 100vw;
  }

  #container {
    width: 50%;
    min-width: 700px;
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
    padding: 2rem 1rem;
  }

  #profile_card {
    width: 45%;
    margin-left: 1.2rem;
    margin-top: 1.1rem;
  }

  #name_card {
    display: flex;
    direction: row;
    justify-content: space-between;
    align-items: center;
    margin-top: 0.2rem;
    margin-bottom: 1.2rem;
    width: 100%;
  }
  
  #info_card {
    display: flex;
    direction: row;
    justify-content: space-between;
    align-items: center;
    width: 100%;
  }
  
  #card {
    display: flex;
    flex-direction: row;
    justify-content: center;
  }

  #profile_img {
    width: 14vh;
    height: 14vh;
    min-width: 50px;
    border-radius: 50%;
  }

  h1 {
    color: black;
    font-size: 2rem;
    font-weight: bold;
  }

  h3 {
    color: black;
    font-size: 1.2rem;
    font-weight: bold;
    line-height: 100%;
    letter-spacing: 0.05rem;
    margin: 0;
  }

  p {
    color: black;
    font-size: 1.4rem;
    font-weight: bold;
  }
  
  .inactive {
    color: gray;
    font-size: 1.2rem;
    font-weight: bold;
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
    padding: 0.41rem 0.45rem;
    margin-bottom: 0.7rem;
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

  #dot1 {
    width: 18px;
    height: 18px;
    border-radius: 50%;
    position: absolute;
    top: -17%;
    left: 42%;
    background-color: #5E39B3;
  }
  
  #dot2 {
    width: 18px;
    height: 18px;
    border-radius: 50%;
    position: absolute;
    top: -17%;
    left: 56%;
    background-color: #5E39B3;
  }

  #tab_names {
    margin-top: 1%;
    width: 22%;
    display: flex;
    direction: row;
    justify-content: space-between;
  }

  </style>