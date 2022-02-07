import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    feedActive: false,
    user: null
  },
  mutations: {
    activateFeed: function(state){
      console.log(state.feedActive)
      state.feedActive = !state.feedActive
      console.log(state.feedActive)
    },
    userInfo: function(state, payload){
      console.log(payload)
      state.user = payload
    }
  },
  actions: {
  },
  modules: {
  }
})
