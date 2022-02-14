<template>
  <section id="mypage-container">
    <side-profile-card :user-info="userInfo" />
    <article id="profile-container">
      <img id="profile-img"  :src="this.$store.state.userInfo.profileImg">
      <div id="profile-card">
        <div id="name-card">
          <h1>{{ this.$store.state.userInfo.nickname }}</h1>
          <button @click="$router.push({name: 'Setting'})">프로필 수정</button>
        </div>
        <div id="info">
          <h3>{{ this.$store.state.userInfo.intro }}</h3>
          <div id="info-card">
            <h3>이야기 {{ this.$store.state.userFeedInfo.length }}</h3>
            <h3 @click="showFollowerList" style="cursor: pointer;">팔로우 {{ this.$store.state.userFollowInfo.userFollow.length }}</h3>
            <h3 @click="showFollowingList" style="cursor: pointer;">팔로워 {{ this.$store.state.userFollowInfo.userFollowing.length }}</h3>
          </div>
        </div>
      </div>
    </article>
    <article id="tab">
      <span id="dot1" :class="myPageTab == 'feed' ? 'slide-out':'slide-in'" />
      <p @click="changeTab('feed')" :class="myPageTab == 'feed' ? 'activate': ''">이야기</p>
      <p @click="changeTab('pick')" :class="myPageTab == 'pick' ? 'activate': ''">찜 목록</p>
    </article>
    <article id="list-container">
      <router-view/>
    </article>
  </section>  
</template>

<script>
import SideProfileCard from '@/components/SideProfileCard.vue'

export default {
  name: 'Mypage',
  components: {SideProfileCard},
  data() {
    return {
      userInfo: {
      username: '최강상후',
      mood: 3,
      posts: 0,
      },
      myPageTab: 'feed',
      filter: 0
    }
  },
  methods: {
    changeTab(tap){
      this.myPageTab = tap
      this.$router.push({ path: `/mypage/${tap}` })
    },
    showFollowerList: function () {
      this.$store.commit('mypagefollowerListActivate')
    },
    showFollowingList: function () {
      this.$store.commit('mypagefollowingListActivate')
    }
  },
  computed:{
    to(){
      return this.$router.app._route.path
    }
  },
  created() {
    this.$store.dispatch("searchUserFeed", this.$store.state.userInfo.no)
  },
  mounted(){
    if (this.to.includes('item')){
      this.myPageTab = 'pick'
    }
  }
}
</script>

<style scoped>
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
    color: #777777;
    font-size: 1.2rem;
    font-weight: bold;
    margin: 0 1rem;
    cursor: pointer;
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
    margin-bottom: 0.1rem;
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

  #mypage-container {
    width: 50%;
    min-width: 700px;
    min-height: 100vh;
    margin: auto;
    border-left: 1px solid #cccccc;
    border-right: 1px solid #cccccc;
    background-color: white;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start;
  }

  #profile-container {
    display: flex;
    justify-content: space-evenly;
    align-items: center;
    width: 80%;
    padding: 2rem 1rem;
  }

  #profile-img {
    width: 20%;
    height: inherit;
    aspect-ratio: 1/1;
    border-radius: 50%;
  }

  #profile-card {
    width: 60%;
  }

  #name-card {
    display: flex;
    direction: row;
    justify-content: space-between;
    align-items: center;
    margin-top: 0.2rem;
    margin-bottom: 0rem;
    width: 100%;
  }
  
  #info-card {
    display: flex;
    direction: row;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    margin-top: 1.4rem;
  }
  
  #card {
    display: flex;
    flex-direction: row;
    justify-content: center;
  }

  #tab {
    display: flex;
    justify-content: center;
    align-items: flex-end;
    position: relative;
    border-top: 1px solid gainsboro;
    width: 100%;
    padding: 1rem;
  }

  #dot1 {
    width: 18px;
    height: 18px;
    border-radius: 50%;
    position: absolute;
    top: -15%;
    left: 44%;
    background-color: #5E39B3;
  }

  #list-container {
    width: 100%;
    display: flex;
    justify-content: center;
  }

  @keyframes slide-in {
    from {left: 44%;}
    to {left: 54%;}
  }

  @keyframes slide-out {
    from {left: 54%;}
    to {left: 44%;}
  }

  .slide-in {
    animation: slide-in 0.5s ease-out forwards;
  }
  #short_comment {
    text-align: left;
  }
  #my_comment {
    font-size: 1.1rem;
    font-weight: bold;
  }
  .slide-out {
    animation: slide-out 0.5s ease-out forwards;
  }

  .activate {
    color: black;
    font-size: 1.3rem;
  }

  #info {
    display: flex;
    flex-direction: column;
  }

</style>
