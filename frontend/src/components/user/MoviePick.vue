<template>
  <div>
    <div v-if="movieExist" id="movie-container">
      <div
        v-for="movie in movieList"
        :movie="movie" class="movies"
        :key="movieList.indexOf(movie)">
          <img :src="movie.postImgLink" alt="" id="poster_img">
          <p id="info">{{ movie.name }}</p>
      </div>
    </div>
    <div v-else id="no_result">
      <img id="nothing" src="@/assets/images/etc/alien.png" alt="">
      <p>찜한 영화가 없어요...</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MoviePick',
  props: {
    movieList: Array,
    movieExist: Boolean,
  },
}
</script>

<style scoped>
  #movie-container {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr;
    grid-auto-flow: dense;
    justify-content: center;
    align-items: baseline;
    /* grid-template-rows: min-content; */
    justify-items: center;
    gap: 1rem;
    width: 100%;
    padding: 1rem;
  }

  .movies {
    /* grid-area: content; */
    width: 100%;
    display: flex;
    flex-direction: column;
    border: solid 1px;
    margin: 1rem 1.5rem;
    border-radius: 10px;
  }

  #poster_img {
    width: 100%;
    height: 30vh;
    border-radius: 10px;
  }

  #info {
    font-size: 1.2rem;
    font-weight: bold;
    text-align: center;
    margin: 1rem auto;
  }

  #no_result {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding-top: 5vh;
  }

  #nothing {
    width: 10vh;
    height: 10vh;
    margin-bottom: 2vh;
  }
</style>