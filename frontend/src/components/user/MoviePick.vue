<template>
  <div 
    v-if="movieExist"
    :movieExist="movieExist"
    id="contain">
    <div
      v-for="movie in movieList"
      :movie="movie"
      :key="movieList.indexOf(movie)">
        <div id="item_container">
          <img :src="movie.postImgLink" alt="" id="poster_img">
          <p id="info">{{ movie.name }}</p>
        </div>
    </div>
  </div>
  <div v-else id="no_result">
		<img id="nothing" src="@/assets/images/etc/alien.png" alt="">
		<p>찜한 영화가 없어요...</p>
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
  #contain {
    display: flex;
    justify-content: left;
    flex-wrap: wrap;
    margin: auto;
  }

  #item_container {
    width: 20.3vh;
    display: flex;
    flex-direction: column;
    border: solid 1px;
    margin: 1rem 1.5rem;
    border-radius: 10px;
  }

  #poster_img {
    width: 20vh;
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