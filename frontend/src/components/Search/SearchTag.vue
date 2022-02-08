<template>
  <div id="container">
    
    <div id="results" v-if="this.$store.state.tagSearch.length !== 0">
      <div v-for="result in this.$store.state.tagSearch"
        :key="result.no"
        id="result">
        <img src="../../assets/images/icons/hashtag.png" alt="" id="hashtag">
        <div id="search">
          <span id="title">#{{ result.name }}</span>
          <span id="content">이야기: {{ result.count }}개</span>
        </div>
        <img src="../../assets/images/icons/search_dark.png" alt="" id="go">
      </div>
    </div>
    <div v-else id="no_result">
      <img id="nothing" src="@/assets/images/etc/alien.png" alt="">
      <p>찾는 이야기가 없어요...</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SearchTag',
  methods: {
    
  },
  created() {
    console.log(this.$store.state.words)
  }
}
</script>

<style scoped>
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
    width: 90%;
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
    padding-left: 1rem;
    padding-right: 2rem;
  }

  #title {
    font-size: 1.2rem;
    font-weight: bold;
  }

  #hashtag {
    width: 4vh;
    height: 4vh;
    border-radius: 40%;
  }
  
  #go {
    width: 3vh;
    height: 3vh;
    cursor: pointer;
  }

  #no_result {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding-top: 8vh;
  }

  #nothing {
    width: 6vh;
    height: 6vh;
    margin-bottom: 2vh;
  }
</style>