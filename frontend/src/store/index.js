import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    feedActive: false,
    searchActive: false,
    user: null,
    words: null,
    tagSearch: [],
    userSearch: [],
  },
  mutations: {
    activateFeed: function(state){
      console.log(state)
      state.feedActive = !state.feedActive
    },
    activateSearch: function(state){
      console.log(state)
      state.searchActive = true
    },
    deactivateSearch: function(state){
      console.log(state)
      state.searchActive = false
    },
    updateSearch: function(state, searchWords){
      console.log(state)
      state.words = searchWords
    },
    userInfo: function(state, payload){
      console.log(payload)
      state.user = payload
    }
  },
  actions: {
    searchTag() {
      axios({
        method: 'get',
        url:'http://13.125.47.126:8080/searchs/byTag/' + this.state.words,
      })
      .then((res)=>{
        this.state.tagSearch = res.data
        console.log('then')
        console.log(res.data)
      })
      .catch(err=> {
        console.log('catch')
        this.state.tagSearch = []
        console.log(err.response.data.message) // 서버측에서 넘어온 오류 메시지 출력.
      })
    },
    searchUser() {
      axios({
        method: 'get',
        url:'http://13.125.47.126:8080/searchs/byNickName/' + this.state.words,
      })
      .then((res)=>{
        this.state.userSearch = res.data
        console.log('then')
        console.log(res.data)
      })
      .catch(err=> {
        console.log('catch')
        this.state.userSearch = []
        console.log(err.response.data.message) // 서버측에서 넘어온 오류 메시지 출력.
      })
    },
  },
  modules: {
  }
})
