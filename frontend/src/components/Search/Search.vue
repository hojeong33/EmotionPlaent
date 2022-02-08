<template>
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
      this.$emit('cancel')
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
  #search_body {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background-color: white;
  }

  #search_header {
    z-index: 10;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: white;
    width: 100%;
    min-width: 300px;
    height: 10%;
    min-height: 55px;
    border-top: 0.2rem solid gainsboro;
    border-left: 0.2rem solid gainsboro;
    border-right: 0.2rem solid gainsboro;
  }
  
  #search_content {
    overflow: auto;
    display: flex;
    background-color: white !important;
    width: 100%;
    min-width: 300px;
    height: 80%;
    min-height: 300px;
    border: 0.2rem solid gainsboro;
  }
  
  #search_footer {
    display: flex;
    justify-content: center;
    background-color: white;
    width: 100%;
    min-width: 300px;
    height: 10%;
    min-height: 60px;
    border-bottom: 0.2rem solid gainsboro;
    border-left: 0.2rem solid gainsboro;
    border-right: 0.2rem solid gainsboro;
  }

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
    padding: 0 2rem;
    margin: 0.4rem;
    cursor: pointer;
    line-height: 2rem;
  }

  #tabs {
    width: 100%;
    display: flex;
    justify-content: space-evenly;
  }

</style>