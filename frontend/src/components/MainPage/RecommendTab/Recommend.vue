<template>
  <div>
    <div>
      <div id="container">
        <div>
          <img
            id="planet_img"
            :src="require('@/assets/images/emotions/' + tmp.img)"
          />
        </div>
        <div id="header">
          <h1 style="font-size: 2.5rem">어서오세요</h1>
          <span id="planet_name" :style="{ color: tmp.color }">{{
            tmp.name
          }}</span>
          <span style="font-size: 2.5rem">입니다</span>
        </div>
      </div>
    </div>
    <div class="tab">
      <p @click="Refresh" :class="onRefresh ? 'active' : 'inactive'">
        &nbsp;&nbsp;&nbsp; 떠나기
      </p>
      <p @click="Feel" :class="onFeel ? 'active' : 'inactive'">탐험하기</p>
    </div>
    <button @click="reload">좀 더 놀기</button>
    <music-list></music-list>
    <movie-list></movie-list>
    <active-list></active-list>
  </div>
</template>

<script>
import MusicList from "./MusicList.vue";
import ActiveList from "./ActiveList.vue";
import MovieList from "./MovieList.vue";
export default {
  components: { MusicList, MovieList, ActiveList },
  props: {
    userInfo: Object,
  },
  data: function () {
    return {
      planetStyles: [
        { id: 1, name: "행복행성", img: "happy.png", color: "#6BD9E8" },
        { id: 2, name: "우울행성", img: "depressed.png", color: "#2A61F0" },
        { id: 3, name: "중립행성", img: "neutral.png", color: "#ABBECA" },
        { id: 4, name: "공포행성", img: "fear.png", color: "#ED5A8E" },
        { id: 5, name: "깜짝행성", img: "surprised.png", color: "#FEA95C" },
        { id: 6, name: "분노행성", img: "rage.png", color: "#FB5D38" },
      ],
      onRefresh: true,
      onFeel: false,
    };
  },
  computed: {
    tmp: function () {
      const mood = this.$store.state.userInfo.mood;
      const style = this.planetStyles.find((el) => el.id === mood) || {};
      return style;
    },
  },
  methods: {
    Refresh: function () {
      this.onRefresh = true;
      this.onFeel = false;
      this.$store.state.recommendType = 1;
    },
    Feel: function () {
      this.onRefresh = false;
      this.onFeel = true;
      this.$store.state.recommendType = 0;
    },
    reload() {
      this.$store.dispatch('recommendMusic')
			this.$store.dispatch('recommendMovie')
			this.$store.dispatch('recommendActivity')
    },
  },
};
</script>

<style scoped>
p {
  margin: 5px;
  text-align: center;
}
.planet {
  width: 6rem;
  height: 6rem;
}
#planet_name {
  font-size: 3rem;
  font-weight: bold;
}
#container {
  /* border: 2px solid black; */
  display: flex;
  width: 100%;
  padding-top: 1rem;
  background-color: rgb(216, 216, 216, 0.2);
  padding: 2rem;
  justify-content: center;
}
#header {
  text-align: left;
  margin-left: 5px;
}
.tab {
  display: flex;
  justify-content: center;
  font-size: 1.2rem;
  font-weight: bold;
  color: #777777;
}
.active {
  color: rgb(94, 57, 179);
}
</style>