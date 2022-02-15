<template>
  <div>
    <div>
      <div id="container">
        <div>
          <img
            id="planet"
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
      <div id="tab_names">
        <p @click="Refresh" :class="onRefresh ? 'active' : 'inactive'" style="margin-right:1rem; margin-left:2rem;">떠나기</p>
        <p @click="Feel" :class="onFeel ? 'active' : 'inactive'" style="margin-left:1rem;">탐험하기</p>
      </div>
      <div id="refresh">
        <p>좀 더 놀기</p>
        <img src="@/assets/images/icons/refresh.png" @click="reload" id="refresh_btn">
      </div>
    </div>
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
        { id: 3, name: "심심행성", img: "neutral.png", color: "#ABBECA" },
        { id: 4, name: "공포행성", img: "fear.png", color: "#ED5A8E" },
        { id: 5, name: "깜짝행성", img: "surprised.png", color: "#FEA95C" },
        { id: 6, name: "분노행성", img: "rage.png", color: "#FB5D38" },
        { id: 7, name: '떠돌이행성', img: "spaceship.png", color: '#FCBB74' },
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
#planet {
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
  border-bottom: 0.15rem solid gainsboro;
}
#header {
  text-align: left;
  margin-left: 5px;
}
.tab {
  display: flex;
  flex-direction: column;
  font-size: 1.2rem;
  font-weight: bold;
  color: #777777;
}

#tab_names {
  display: flex;
  justify-content: center;
  margin-top: 2rem;
  font-size: 1.5rem;
  font-weight: bold;
  color: #777777;
}
.active {
  color: rgb(94, 57, 179);
}

#refresh {
  display: flex;
  width: 90%;
  justify-content: right;
  align-items: center;
  margin: 1rem 1rem 3rem 4rem;
}
#refresh_btn {
  width: 3rem;
  height: 3rem;
  cursor: pointer;
}
</style>