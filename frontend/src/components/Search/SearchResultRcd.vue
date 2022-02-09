<template>
  <div id="result-recommend-container">
    <div id="result-musics" class="results">
      <h2>음악</h2>
      <carousel :data="dummy" />
    </div>
    <div id="result-movies" class="results">
      <h2>영화</h2>
    </div>
    <div id="result-acts" class="results">
      <h2>활동</h2>
    </div>
  </div>
</template>

<script>
import Carousel from '@/components/Carousel'
import dummy from '@/assets/data/dummy'

export default {
  data(){
    return {
      dummy
    }
  },
  components: {
    Carousel,
  }
}
</script>

<style scoped>
  #result-recommend-container {
    display: flex;
    flex-direction: column;
    width: 100%;
  }

  .results {
    display: flex;
    flex-direction: column;
    width: 100%;
    padding: 2rem;
    border-bottom: 1px #cccccc solid;
  }
</style>