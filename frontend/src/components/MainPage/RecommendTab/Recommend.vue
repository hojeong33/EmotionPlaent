<template>
  <div>
    <div>
        <div class="container"> 
          <div>
            <img class="planet" src="../../../assets/images/depressed.png" alt="">
          </div>
          <div>
            <h1>어서오세요</h1>
            <h1>우울행성입니다.</h1>
          </div>
        </div>
    </div>
    <div class="tab">
      <button>기분 전환</button>
      <button>기분 즐기기</button>
    </div>
    <music-list></music-list>
    <movie-list></movie-list>
    <active-list></active-list>
  </div>
</template>

<script>
import MusicList from './MusicList.vue'
import ActiveList from './ActiveList.vue'
import MovieList from './MovieList.vue'
export default {
  components: {MusicList,MovieList, ActiveList},

}
</script>

<style scoped>
.planet{
    width: 50px;
    height:50px;
}
.container{
  /* border: 2px solid black; */
  width: 100%;
  padding-top:1rem;
  background-color: gainsboro;
}
.tab{
  display: flex;
  justify-content:center;
}
</style>