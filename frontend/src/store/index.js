import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

const session = window.sessionStorage;
// const jwt = require('jsonwebtoken');

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
    activateFeed: function(state){
      console.log(state.feedActive)
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
      let headers = {
        'at-jwt-access-token': session.getItem('at-jwt-access-token'),
        'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
      };

			axios.get('http://13.125.47.126:8080/recommend/music/' + this.state.userEmotion, {
          headers: headers,
        }).then((res) => {
          this.state.recommendMusic = res.data
          console.log(res);
          console.log('response header', res.headers);
          if(res.headers['at-jwt-access-token'] != session.getItem('at-jwt-access-token')){
            session.setItem('at-jwt-access-token', "");
            session.setItem('at-jwt-access-token', res.headers['at-jwt-access-token']);
            console.log("Access Token을 교체합니다!!!")
          }
        
        }).catch((error) => {
          console.log(error);
        }).then(() => {
          console.log('getQSSList End!!');
        });
      },
    recommendMovie() {
      let headers = {
        'at-jwt-access-token': session.getItem('at-jwt-access-token'),
        'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
      };

			axios.get('http://13.125.47.126:8080/recommend/movie/' + this.state.userEmotion, {
          headers: headers,
        }).then((res) => {
          this.state.recommendMovie = res.data
          console.log(res);
          console.log('response header', res.headers);
          if(res.headers['at-jwt-access-token'] != session.getItem('at-jwt-access-token')){
            session.setItem('at-jwt-access-token', "");
            session.setItem('at-jwt-access-token', res.headers['at-jwt-access-token']);
            console.log("Access Token을 교체합니다!!!")
          }
        
        }).catch((error) => {
          console.log(error);
        }).then(() => {
          console.log('getQSSList End!!');
        });
      },
    recommendActivity() {
      let headers = {
        'at-jwt-access-token': session.getItem('at-jwt-access-token'),
        'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
      };

			axios.get('http://13.125.47.126:8080/recommend/activity/', {
          headers: headers,
        }).then((res) => {
          this.state.recommendActivity = res.data
          console.log(res);
          console.log('response header', res.headers);
          if(res.headers['at-jwt-access-token'] != session.getItem('at-jwt-access-token')){
            session.setItem('at-jwt-access-token', "");
            session.setItem('at-jwt-access-token', res.headers['at-jwt-access-token']);
            console.log("Access Token을 교체합니다!!!")
          }
        
        }).catch((error) => {
          console.log(error);
        }).then(() => {
          console.log('getQSSList End!!');
        });
      },
  },
  modules: {
  }
})
