import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    feedActive: false
  },
  mutations: {
    activateFeed: function(state){
      console.log(state)
      state.feedActive = !state.feedActive
    }
  },
  actions: {
  },
  modules: {
  }
})
