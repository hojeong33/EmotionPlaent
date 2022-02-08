import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

const session = window.sessionStorage;
// const jwt = require('jsonwebtoken');

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    feedActive: false,
    //검색부분
    words: null,
    tagSearch: [],
    userSearch: [],
    //메인 추천탭 부분
    userEmotion: null,
    recommendType: 1,
    recommendMusic: [],
    recommendMovie: [],
    recommendActivity: [],
    recommendReload: 0,
    userInfo: null,
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
    commentSettingModalActive: false,
    logoutModalActive: false,
    userFeedSettingModalActive: false,
    profileImgChangeModalActive: false,
    loginConfirmModalActive: false,
    signupConfirmModalActive: false,
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
    //검색부분
    updateSearch: function(state, searchWords){
      console.log(state)
      state.words = searchWords
    },
    userUpdate(state, payload){
      if (!session.getItem('userInfo')){
        session.setItem('userInfo', JSON.stringify(payload))
      }
      const userdata = JSON.parse(session.getItem('userInfo'))
      if(typeof(payload) == 'number'){
        userdata.mood = payload
        session.setItem('userInfo', JSON.stringify(userdata))
      }
      state.userInfo = userdata
    },
    feedOut({ navActive }){
      Vue.set(navActive, 0, false)
    },
  },
  actions: {
    //여기 검색부분입니다
    searchTag() {
      let headers = {
        'at-jwt-access-token': session.getItem('at-jwt-access-token'),
        'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
      };
      axios({
        method: 'get',
        url:'http://13.125.47.126:8080/searchs/byTag/' + this.state.words,
        headers: headers,
      }).then(res => {
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
      let headers = {
        'at-jwt-access-token': session.getItem('at-jwt-access-token'),
        'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
      };
      axios({
        method: 'get',
        url:'http://13.125.47.126:8080/searchs/byNickName/' + this.state.words,
        headers: headers,
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
    //검색 끝 추천탭 시작입니다
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
    // userInfo: function(state, payload){
    //   console.log(payload)
    //   state.user = payload
    // },
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