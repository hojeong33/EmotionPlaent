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
    recommendReload: 0,
  },
  mutations: {
<<<<<<< HEAD
    activateFeed: function (state) {
      console.log(state)
=======
    activateFeed: function(state){
      console.log(state.feedActive)
>>>>>>> 377fab15da4efef2469dcf0c15a403535bd8959e
      state.feedActive = !state.feedActive
      console.log(state.feedActive)
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
        url: 'http://13.125.47.126:8080/recommend/music/' + this.state.userEmotion,
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
        url: 'http://13.125.47.126:8080/recommend/movie/' + this.state.userEmotion,
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
        url: 'http://13.125.47.126:8080/recommend/activity/',
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
