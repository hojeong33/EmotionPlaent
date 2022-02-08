import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    feedActive: false,
    user: null,
    commentSettingModalActive: false,
    logoutModalActive: false,
    userFeedSettingModalActive: false,
    profileImgChangeModalActive: false,
    loginConfirmModalActive: false,
    signupConfirmModalActive: false,
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
    },
    commentSettingModalActivate: function (state) {
      state.commentSettingModalActive = !state.commentSettingModalActive
      console.log(state.commentSettingModalActive)
    },
    logoutModalActivate: function (state) {
      state.logoutModalActive = !state.logoutModalActive
      console.log(state.logoutModalActive)
    },
    userFeedSettingModalActivate: function (state) {
      state.userFeedSettingModalActive = !state.userFeedSettingModalActive
      console.log(state.userFeedSettingModalActive)
    },
    profileImgChangeModalActivate: function (state) {
      state.profileImgChangeModalActive = !state.profileImgChangeModalActive
      console.log(state.profileImgChangeModalActive)
    },
    loginConfirmModalActivate:function (state) {
      state.loginConfirmModalActive = !state.loginConfirmModalActive
      console.log(state.loginConfirmModalActive)
    },
    signupConfirmModalActivate:function (state) {
      state.signupConfirmModalActive = !state.signupConfirmModalActive
      console.log(state.signupConfirmModalActive)
    }
  },
  actions: { 
  },
  modules: {
  }
})
