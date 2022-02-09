<template>
  <div id="search-result-container">
    <div id="search-taps">
      <p @click="searchTab = 'feed'" :class="{ 'active':searchTab == 'feed' }">피드</p>
      <p @click="searchTab = 'recommend'" :class="{ 'active':searchTab == 'recommend' }">정보</p>
    </div>
    <search-result-rcd />
  </div>
</template>

<script>
import SearchResultRcd from '@/components/Search/SearchResultRcd'

export default {
  data(){
    return {
      searchTab: 'feed'
    }
  },
  components: {
    SearchResultRcd
  }
}
</script>

<style scoped>
  p {
    color: #777777;
    font-size: 2rem;
    margin: 0 1rem;
    cursor: pointer;
  }

  #search-result-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 50%;
    min-height: 100vh;
    border: 2px #cccccc solid;
    margin: 0 auto;
  }

  #search-taps {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    padding: 1rem;
    border-bottom: 1px #cccccc solid;
  }

  .active {
    color: #5e39b3;
    font-size: 2.25rem;
    font-weight: bold;
  }
</style>