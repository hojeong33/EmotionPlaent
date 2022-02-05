import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    feedActive: false,
    userInfo: {
      no: 4,
      email: 'test@email.com',
      pw: '1234',
      nickname: 'test',
      birth: '1996-11-11',
      profileImg: 'https://emotionplanet.s3.ap-northeast-2.amazonaws.com/59c864d5-515e-4cee-b93e-85f05876579c.PNG',
      tel: '010-0000-0000',
      mood: 3,
      publish: false,
    },
    user: null,
    userEmotion: 3, //나중에 userInfo에 있는 mood랑 합칠 것.
    recommendType: 1,
    recommendMusic: [],
    recommendMovie: [],
    recommendActivity: [],
  },
  mutations: {
    activateFeed: function (state) {
      console.log(state)
      state.feedActive = !state.feedActive
    },
    userInfo: function (state, payload) {
      console.log(payload)
      state.user = payload
    },
  },
  actions: {
    // 추천피드 시작점
    recommendMusic() {
			axios({
        method: 'get',
        url: 'http://13.125.47.126:8080/recommend/music/' + this.state.userEmotion + '/' + this.state.recommendType,
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
        url: 'http://13.125.47.126:8080/recommend/movie/' + this.state.userEmotion + '/' + this.state.recommendType,
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
        url: 'http://13.125.47.126:8080/recommend/activity/' + this.state.recommendType,
      })
      .then((res) => {
        console.log(res)
				this.state.recommendActivity = res.data
      })
      .catch(() => alert('잘못된 요청입니다'))
		},
    // 추천피드 끝
    
    // 비밀번호 변경 시작
    changePw: function (nextPw) {
      const param = { no: this.state.userInfo.no, pw: nextPw }
      axios({
        method: 'put',
        url:'http://13.125.47.126:8080/users/',
        data: param,
      })
      .then(()=>{
        alert("변경 성공")
        this.$store.state.userInfo.pw = nextPw
        location.reload()
      })
      .catch(err=> {
        console.log(err)
        alert(err.response.data.message) // 서버측에서 넘어온 오류 메시지 출력.
      })
    },
  },
  modules: {
  }
})
