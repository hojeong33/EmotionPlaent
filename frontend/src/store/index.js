import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    navActive: [false, false, false, false, false],
    user: null,
  },
  mutations: {
    navActivate: function({ navActive }, payload){
      if (payload == -1){
        navActive.forEach((ele, idx) => {
          Vue.set(navActive, idx, false)
        })
        return
      }
      if (payload == 0 || payload == 3){
        Vue.set(navActive, payload, !navActive[payload])
        return 0
      }
      navActive.forEach((ele, idx) => {
        navActive[idx] = false
      })
      Vue.set(navActive, payload, true)
    },
    
    feedOut({ navActive }){
      Vue.set(navActive, 0, false)
    },

    userInfo: function(state, payload){
      state.user = payload
    },
  },
  actions: {
  },
  modules: {
  },
})
