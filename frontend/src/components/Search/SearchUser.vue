<template>
  <div id="container">
    <div id="results" v-if="this.$store.state.userSearch.length !== 0">
      <div v-for="result in this.$store.state.userSearch"
      :key="result.no"
      id="result">
        <img :src="result.profileImg" alt="" id="user">
        <div id="search">
          <span id="title">@{{ result.nickname }}</span>
          <!-- <span id="content">{{ result.email }}</span> -->
        </div>
        <img src="../../assets/images/icons/search_dark.png" 
        @click="getInfo(result.no)" alt="" id="go">
      </div>
    </div>
    <div v-else id="no_result">
      <img id="nothing" src="@/assets/images/etc/alien.png" alt="">
      <h3>찾는 여행자가 없어요...</h3>
    </div>  
  </div>
</template>

<script>
export default {
  name: 'SearchUser',
  methods: {
    async getInfo(el) {
      this.$store.state.searchUserNo = el
      await this.$store.dispatch('userSelect', el)
      await this.$store.dispatch('userfollowdate', el)
      await this.$store.dispatch("searchUserFeed", this.$store.state.searchUserNo)
      this.$router.push({ path: `/userpage/feed` })
      this.$store.state.searching = false
    }
  },
  created() {
    console.log(this.$store.state.words)
  }
}
</script>

<style scoped>
  h3 {
    font-size: 1.25rem;
    font-weight: bold;
    margin: 0;
  }

  #container {
    width: 100%;
    background-color: white;
  }

  #results {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: left;
    background-color: white;
    overflow: auto;
  }

  #result {
    display: flex;
    width: 85%;
    align-items: center;
    padding-left: 1rem;
    margin: 1rem 1rem;
    background-color: white;
    border-bottom: 0.1rem gainsboro;
  }

  #search {
    width: 70%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    padding-left: 0.5rem;
    padding-right: 2rem;
  }

  #title {
    font-size: 1.2rem;
    font-weight: bold;
  }

  #go {
    width: 3vh;
    height: 3vh;
    cursor: pointer;
  }

  #user {
    width: 4vh;
    height: 4vh;
    border-radius: 40%;
  }

  #no_result {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding-top: 11.5vh;
  }

  #nothing {
    width: 4vh;
    height: 4vh;
    margin-bottom: 2vh;
  }
</style>