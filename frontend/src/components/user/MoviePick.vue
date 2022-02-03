<template>
  <div id="item_container" 
    v-if="movieExist"
    :movieExist="movieExist">
    <div id="items">
      <span id="item" 
        v-for="movie in movieList"
        :movie="movie"
        :key="movieList.indexOf(movie)">
        <article>
          <img :src="movie.postImgLink" alt="" id="poster_img">
        </article>
        <article id="info">
          <p>{{ movie.name }}</p>
        </article>
      </span>
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
  #item_container {
    width: 80vh;
    margin: 1rem auto;
  }

  #items {
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
  }

  #item {
    display: flex;
    flex-direction: column;
    position: relative;
    margin: 0 1rem;
    box-shadow: 5px 1px 5px 5px rgb(220, 220, 220, 0.5);
    border-radius: 10px;
  }

  #poster_img {
    width: 22vh;
    height: 33vh;
    border-radius: 10px;
  }

  p {
    margin-left: 0.5rem;
  }

  #info {
    display: flex;
    font-size: 1.2rem;
    font-weight: bold;
    margin-top: 1rem;
    margin-left: 1.5rem;
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