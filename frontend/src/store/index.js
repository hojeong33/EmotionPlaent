import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    feedActive: false,
    user: null,
    userEmotion: 3,
    recommendType: 1,
    recommendMusic: [],
    recommendMovie: [],
    recommendActivity: [],
  },
  mutations: {
    activateFeed: function (state) {
      console.log(state)
      state.feedActive = !state.feedActive
    },
    userInfo: function (state, payload) {
      console.log(payload)
      state.user = payload
    },
  },
  actions: {
    recommendMusic() {
			axios({
        method: 'get',
        url: 'http://13.125.47.126:8080/recommend/music/' + this.state.userEmotion + '/' + this.state.recommendType,
      })
      .then((res) => {
        console.log(res)
				this.state.recommendMusic = res.data
      })
      .catch(() => alert('잘못된 요청입니다'))
		},
		recommendMovie() {
			axios({
        method: 'get',
        url: 'http://13.125.47.126:8080/recommend/movie/' + this.state.userEmotion + '/' + this.state.recommendType,
      })
      .then((res) => {
        console.log(res)
				this.state.recommendMovie = res.data
      })
      .catch(() => alert('잘못된 요청입니다'))
		},
		recommendActivity() {
			axios({
        method: 'get',
        url: 'http://13.125.47.126:8080/recommend/activity/' + this.state.recommendType,
      })
      .then((res) => {
        console.log(res)
				this.state.recommendActivity = res.data
      })
      .catch(() => alert('잘못된 요청입니다'))
		},
  },
  modules: {
  }
})
