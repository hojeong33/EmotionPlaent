import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    feedActive: false,
    user: null,
    userEmotion: null,
  },
  mutations: {
    activateFeed: function(state){
      console.log(state)
      state.feedActive = !state.feedActive
    },
    userInfo: function(state, payload){
      console.log(payload)
      state.user = payload
    },
  },
  actions: {
  },
  modules: {
  }
})
