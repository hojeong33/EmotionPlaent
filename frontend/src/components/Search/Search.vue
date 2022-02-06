<template>
  <div id="search_container">
    <div id="search_body">
      <section id="search_header">
        <div id="tabs">
          <p @click="toggleTag" :class="onTag ? 'active': 'inactive'">태그</p>
          <p @click="toggleUser" :class="onUser ? 'active': 'inactive'">계정</p>
        </div>
      </section>
      <section id="search_content">
        <search-tag v-if="onTag"></search-tag>
        <search-user v-if="onUser"></search-user>
      </section>
      <section id="search_footer">
        <button @click="cancel">X</button>
      </section>
    </div>
  </div>
</template>

<script>
import SearchTag from './SearchTag.vue'
import SearchUser from './SearchUser.vue'

export default {
  name: 'Search',
  components: { SearchTag, SearchUser },
  data() {
    return {
      onTag: true,
      onUser: false,
    }
  },
  methods: {
    cancel() {
      this.$store.commit('deactivateSearch')
    },
    toggleTag: function () {
      this.onTag = true
      this.onUser = false
    },
    toggleUser: function () {
      this.onUser = true
      this.onTag = false
    },
  }
}
</script>

<style scoped>
  h3 {
    font-size: 1.25rem;
    font-weight: bold;
    margin: 0;
  }

  p {
    font-size: 1.5rem;
    font-weight: bold;
    margin: 0;
  }

  .inactive {
    color: #777777;
    font-size: 1.2rem;
    font-weight: bold;
  }

  .active {
    border-bottom: 0.3rem solid #5E39B3;
  }

  button {
    background-color: #5E39B3;
    color: white;
    font-size: 1.125rem;
    font-weight: bold;
    border: none;
    border-radius: 20px;
    padding: 0.5rem 2rem;
    margin: 1rem;
    cursor: pointer;
    line-height: 2rem;
  }

  #search_container {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    width: 30vw;
    height: 30vh;
    top: 13rem;
    left: 35rem;
    background-color: rgb(0, 0, 0, 0.1);
  }

  #search_body {
    display: flex;
    width: 99%;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background-color: white;
    border-bottom: 0.2rem solid gainsboro;
  }

  #search_header {
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: white;
    width: 100%;
    min-width: 300px;
    height: 15%;
    min-height: 80px;
    border-bottom: 0.2rem solid gainsboro;
  }
  
  #search_content {
    display: flex;
    background-color: white;
    width: 100%;
    min-width: 300px;
    height: 80%;
    min-height: 400px;
    border-bottom: 0.2rem solid gainsboro;
  }
  
  #search_footer {
    display: flex;
    justify-content: center;
    background-color: white;
    border-radius: 20px;
    width: 40%;
    min-width: 300px;
    height: 15%;
    min-height: 80px;
  }

  #tabs {
    width: 100%;
    display: flex;
    justify-content: space-evenly;
  }

</style>