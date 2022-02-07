<template>
  <div>
    <side-profile-card
      :user-info="userInfo"
    >
    </side-profile-card>

    <div id="container">
      <div id="tab">
        <span id="tab_names">
            <p @click="toggleFeed" :class="onFeed ? 'active': 'inactive'">피드</p>
            <p @click="toggleInfo" :class="onInfo ? 'active': 'inactive'">정보</p>
        </span>
        <span id="underline1" v-if="onFeed">
        </span>
        <span id="underline2" v-if="onInfo">
        </span>
      <search-feed></search-feed>
      <search-info></search-info>
      </div>
    </div>
  </div>
</template>

<script>
import SideProfileCard from '@/components/SideProfileCard.vue'
import SearchFeed from '@/components/Search/SearchFeed.vue'
import SearchInfo from '@/components/Search/SearchInfo.vue'

export default {
  name: 'SearchResult',
  components: {SideProfileCard, SearchFeed, SearchInfo},
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
      onInfo: false,
      postlist: [],
      infolist: [],
    }
  },
  methods: {
    toggleFeed: function () {
      this.onFeed = true
      this.onInfo = false
    },
    toggleInfo: function () {
      this.onInfo = true
      this.onFeed = false
    },
  },
}
</script>

<style scoped>
  #searchresult {
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

  #tab_names {
    margin-top: 1%;
    width: 22%;
    display: flex;
    direction: row;
    justify-content: space-between;
  }

  p {
    color: black;
    font-size: 1.4rem;
    font-weight: bold;
  }
  
  .inactive {
    color: #777777;
    font-size: 1.2rem;
    font-weight: bold;
  }

   #tab {
    display: flex;
    justify-content: center;
    position: relative;
    border-top: 0.1rem solid gainsboro;
    width: 100%;
  }

  #underline1 {
    width: 30px;
    height: 4px;
    position: absolute;
    top: 30%;
    left: 42%;
    background-color: #5E39B3;
  }
  
  #underline2 {
    width: 18px;
    height: 18px;
    border-radius: 50%;
    position: absolute;
    top: -17%;
    left: 56%;
    background-color: #5E39B3;
  }
</style>