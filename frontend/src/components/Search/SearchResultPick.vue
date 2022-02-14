<template>
  <div id="result-recommend-container">
    <div id="result-musics" class="results">
      <h2>음악</h2>
      <carousel v-if="dummy" class="result-items" :items="dummy" :name="'music'" />
      <div v-else class="no-result">
        <h3>검색 결과가 없습니다.</h3>
      </div>
    </div>
    <div id="result-movies" class="results">
      <h2>영화</h2>
      <carousel v-if="dummy2" class="result-items" :items="dummy2" :name="'movie'" />
      <div v-else class="no-result">
        <h3>검색 결과가 없습니다.</h3>
      </div>
    </div>
    <div id="result-acts" class="results">
      <h2>활동</h2>
      <carousel v-if="dummy3" class="result-items" :items="dummy3" :name="'act'" />
      <div v-else class="no-result">
        <h3>검색 결과가 없습니다.</h3>
      </div>
    </div>
  </div>
</template>

<script>
import Carousel from '@/components/Carousel'
// import dummy from '@/assets/data/dummy'
import dummy2 from '@/assets/data/dummy2'
import dummy3 from '@/assets/data/dummy3'

export default {
  data(){
    return {
      dummy : null,
      dummy2,
      dummy3,
    }
  },
  components: {
    Carousel
  },
  methods: {
  }
}
</script>

<style scoped>
  h2 {
    font-weight: bold;
    margin: 1rem 2rem;
  }

  h3 {
    color: #777777;
    font-size: 1.25rem;
    font-weight: normal;
    margin: 0;
  }

  #result-recommend-container {
    display: flex;
    flex-direction: column;
    width: 100%;
  }

  .results {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    width: 100%;
    padding: 2rem;
    border-bottom: 1px #cccccc solid;
  }

  .result-items {
    width: 100%;
    margin: auto;
  }

  .no-result {
    margin: 3rem;
  }
</style>