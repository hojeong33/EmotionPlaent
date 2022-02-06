<template>
  <div id="container">
    <p>{{ this.$store.state.words }}</p>
    <div id="results">
      <div v-for="result in this.$store.state.userSearch"
      :key="result.no"
      id="result">
        <img src="../../assets/images/icons/user.png" alt="" id="user">
        <div id="search">
          <span id="title">@{{ result.nickname }}</span>
          <span id="content">{{ result.email }}</span>
        </div>
      </div>
    </div>
    
  </div>
</template>

<script>
export default {
  name: 'SearchUser',
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
    align-items: center;
    margin: 0.5rem 1rem;
  }

  #search {
    display: flex;
    flex-direction: column;
    margin: 0.5rem 1rem;
  }

  #user {
    width: 6vh;
    height: 6vh;
    border-radius: 40%;
  }

  #title {
    font-size: 1.2rem;
    font-weight: bold;
  }
</style>