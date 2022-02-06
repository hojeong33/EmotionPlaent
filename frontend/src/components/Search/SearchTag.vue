<template>
  <div id="container">
    
    <div id="results">
      <div v-for="result in this.$store.state.tagSearch"
      :key="result.no"
      id="result">
        <img src="../../assets/images/icons/hashtag.png" alt="" id="hashtag">
        <div id="search">
          <span id="title">#{{ result.name }}</span>
          <span id="content">운석: {{ result.count }}개</span>
        </div>
      </div>
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

<style>
  #results {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  #result {
    display: flex;
    justify-content: left;
    align-items: center;
    margin: 0.5rem 1rem;
  }

  #search {
    display: flex;
    flex-direction: column;
    margin: 0.5rem 1rem;
  }

  #hashtag {
    width: 6vh;
    height: 6vh;
    border-radius: 40%;
  }

  #title {
    width: 100%;
    font-size: 1.2rem;
    font-weight: bold;
  }
</style>