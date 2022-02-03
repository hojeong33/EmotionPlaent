<template>
  <div id="pick_container">
    <filter-tab 
      @filtering="filterPicks"
      id="filter"
      ></filter-tab>
    <div id="list_tab">
      <span id="music_tab">
        <p @click="toggleMusic" :class="onMusic ? 'active': 'inactive'">음악</p>
        <span :class="onMusic ? 'on': 'off'"></span>
      </span>
      <span id="movie_tab">
        <p @click="toggleMovie" :class="onMovie ? 'active': 'inactive'">영화</p>
        <span :class="onMovie ? 'on': 'off'"></span>
      </span>
      <span id="activity_tab">
        <p @click="toggleActivity" :class="onActivity ? 'active': 'inactive'">활동</p>
        <span :class="onActivity ? 'on': 'off'"></span>
      </span>
    </div>
    <music-pick 
      :music-list="musicList"
      :music-exist="musicExist"
      v-if="onMusic"></music-pick>
    <movie-pick 
      :movie-list="movieList"
      :movie-exist="movieExist"
      v-if="onMovie"></movie-pick>
    <activity-pick 
      :activity-list="activityList"
      :activity-exist="activityExist"
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
      
      onMusic: true,
      onMovie: false,
      onActivity: false,

      musicList: [],
      movieList: [],
      activityList: [],

			musicExist: false,
      movieExist: false,
      activityExist: false,
    }
  },
  components: {
    MusicPick,
    MoviePick,
    ActivityPick,
    FilterTab,
  },
  created() {
    this.sortPicks()
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
    sortPicks: function () {
      this.picks.forEach(element => {
        if (element.no === 1) {
          this.musicList = this.musicList.concat(element.contentsList)
        }
        else if (element.no === 2) {
          this.movieList = this.movieList.concat(element.contentsList)
        }      
        else if (element.no === 3) {
          this.activityList = this.activityList.concat(element.contentsList)
        } 
      })
      if (this.musicList.length != 0) {
        this.musicExist = true
      }
      if (this.movieList.length != 0) {
        this.movieExist = true
      }
      if (this.activityList.length != 0) {
        this.activityExist = true
      }
      console.log(this.musicList)
    },
    filterPicks: function (filterValue) {
      this.musicList = []
      this.movieList = []
      this.activityList = []
      this.musicExist = false
      this.movieExist = false
      this.activityExist = false
      
      if (filterValue != '0') {
				for (let pick of this.picks) {
					if ( pick.no === 1 && pick.tagNo === Number(filterValue)) {
						this.musicList = this.musicList.concat(pick.contentsList)
					}
					else if ( pick.no === 2 && pick.tagNo === Number(filterValue)) {
						this.movieList = this.movieList.concat(pick.contentsList)
					}
					else if ( pick.no === 3 && pick.tagNo === Number(filterValue)) {
						this.activityList = this.activityList.concat(pick.contentsList)
					}
				}
        if (this.musicList.length != 0) {
          this.musicExist = true
        }
        if (this.movieList.length != 0) {
          this.movieExist = true
        }
        if (this.activityList.length != 0) {
          this.activityExist = true
        }
      }
      else {
        this.sortPicks()
      }
		}
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
    width: 40%;
    margin-left: 13.3rem;
    margin-top: 2rem;
    margin-bottom: 2rem;
    justify-content: space-evenly;
    align-items: center;
  }

  #music_tab {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  
  #movie_tab {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  #activity_tab {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  p {
    color: black;
    font-size: 1.4rem;
    font-weight: bold;
    letter-spacing: 0.05rem;
  }

  .active {
    color: black;
    font-size: 1.4rem;
    font-weight: bold;
    margin-bottom: 0.5rem;
  }

  .inactive {
    color: gray;
    font-size: 1.2rem;
    font-weight: bold;
    margin-bottom: 0.5rem;
  }

  .on {
    width: 70%;
    border-bottom: 0.25rem solid #5E39B3;
    border-radius: 20px;
  }

  .off {
    width: 70%;
    border-bottom: 0.2rem solid #ffffff;
  }
</style>