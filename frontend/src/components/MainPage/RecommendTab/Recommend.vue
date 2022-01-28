<template>
  <div>
    <div>
        <div id="container"> 
          <div>
            <img class="planet" src="../../../assets/images/emotions/depressed.png" alt="">
          </div>
          <div id="header">
            <h1>어서오세요</h1>
            <h1 id="planet_name">우울행성입니다</h1>
          </div>
        </div>
    </div>
    <div class="tab">
      <p @click="Refresh" :class="onRefresh ? 'active': 'inactive'">&nbsp;&nbsp;&nbsp;기분 전환 </p>
      <p @click="Feel" :class="onFeel ? 'active': 'inactive'">기분 즐기기</p>
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
  data:function(){
    return{
      onRefresh: true,
      onFeel: false,
    }
  },
  methods: {
    Refresh: function () {
      this.onRefresh = true
      this.onFeel = false
      // console.log('전환')
    },
    Feel: function () {
      this.onRefresh = false
      this.onFeel = true
      // console.log('즐겨즐겨')
    },
  }
}
</script>

<style scoped>
p {
  margin:5px;
  text-align: center;
}
.planet{
    width: 6rem;
    height:6rem;
}
#planet_name{
  color:rgba(45, 80, 235, 0.952);
}
#container{
  /* border: 2px solid black; */
  display: flex;
  width: 100%;
  padding-top:1rem;
  background-color: gainsboro;
  padding:2rem;
  justify-content: center;
}
#header{
  text-align: left;
}
.tab{
  display: flex;
  justify-content:center;
  font-size: 1.2rem;
  font-weight: bold;
  color:rgb(117, 116, 116);
}
.active {
  color:  rgb(94, 57, 179);
 }
</style>