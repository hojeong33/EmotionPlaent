<template>
  <div id="pick_container">
    <filter-tab @filtering="filterFeed" id="filter"></filter-tab>
    <div id="list_tab">
      <p @click="toggleMusic">음악</p>
      <p @click="toggleMovie">영화</p>
      <p @click="toggleActivity">활동</p>
    </div>
    <music-pick 
      :music-list="musicList"
      v-if="onMusic" ></music-pick>
    <movie-pick 
      :movie-list="movieList"
      v-if="onMovie"></movie-pick>
    <activity-pick 
      :activity-list="activityList"
      v-if="onActivity"></activity-pick>
  </div>
</template>

<script>
import MusicPick from './MusicPick.vue'
import MoviePick from './MoviePick.vue'
import ActivityPick from './ActivityPick.vue'
import FilterTab from './FilterTab.vue'
import picks from '../../assets/data/picks'

export default {
  name: 'PickList',
  data() {
    return {
      picks,
      filtering: null,
      onMusic: true,
      onMovie: false,
      onActivity: false,
      musicList: [],
      movieList: [],
      ActivityList: [],
    }
  },
  components: {
    MusicPick,
    MoviePick,
    ActivityPick,
    FilterTab,
  },
  created() {
    const music = this.picks.find(el => el.no === 1) || {}
    this.musicList.push(music)

    const movie = this.picks.find(el => el.no === 2) || {}
    this.movieList.push(movie)
    
    const activity = this.picks.find(el => el.no === 3) || {}
    this.activityList.push(activity)
  },
  methods: {
    toggleMusic: function () {
      this.onMusic = true
      this.onMovie = false
      this.onActivity = false
    },
    toggleMovie: function () {
      this.onMusic = false
      this.onMovie = true
      this.onActivity = false
    },
    toggleActivity: function () {
      this.onMusic = false
      this.onMovie = false
      this.onActivity = true
    },
    filterFeed: function (filterValue) {
      console.log(filterValue)
    },
  },
}
</script>

<style scoped>
  #pick_container {
    width: 100%;
    min-width: 700px;
    min-height: 100vh;
    padding-top: 1rem;
    margin: 2rem auto;
    display: flex;
    flex-direction: column;
    /* border: 0.1rem solid gainsboro; */
  }

  #filter {
    display: flex;
    justify-content: right;
  }

  #list_tab {
    display: flex;
    justify-content: center;
    width: 40%;
    margin-left: 13.3rem;
    margin-top: 2rem;
    margin-bottom: 2rem;
    justify-content: space-evenly;
  }

  p {
    color: black;
    font-size: 1.4rem;
    font-weight: bold;
    letter-spacing: 0.05rem;
  }
</style>