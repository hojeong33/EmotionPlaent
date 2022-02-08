import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

const session = window.sessionStorage;
// const jwt = require('jsonwebtoken');

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    feedActive: false,

    userEmotion: null,
    recommendType: 1,
    recommendMusic: [],
    recommendMovie: [],
    recommendActivity: [],
    recommendReload: 0,
    userInfo: [],
    planetStyles: [
      { id: 1, name: '행복행성', img: "happy.png", color: '#6BD9E8' },
      { id: 2, name: '우울행성', img: "depressed.png", color: '#2A61F0' },
      { id: 3, name: '중립행성', img: "neutral.png", color: '#ABBECA' },
      { id: 4, name: '공포행성', img: "fear.png", color: '#ED5A8E' },
      { id: 5, name: '깜짝행성', img: "surprised.png", color: '#FEA95C' },
      { id: 6, name: '분노행성', img: "rage.png", color: '#FB5D38' },
    ],
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
    recommendMusic() {
      let headers = {
        'at-jwt-access-token': session.getItem('at-jwt-access-token'),
        'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
      };

			axios.get('http://13.125.47.126:8080/recommend/music/' + this.state.userInfo.mood, {
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

			axios.get('http://13.125.47.126:8080/recommend/movie/' + this.state.userInfo.mood, {
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
  },
})
