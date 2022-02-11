import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

const session = window.sessionStorage;
const jwt = require('jsonwebtoken');

Vue.use(Vuex)
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";

export default new Vuex.Store({
  state: {
    // feedActive: false,
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
    userFollowInfo : {
      //나를 팔로우 하는 사람(팔로워)
      userFollow :[],
      // 내가 팔로우 하는 사람(팔로잉)
      userFollowing : [],
    },
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
    // 피드작성
    feedCreateData: [
      {descr: null, author:null, tags: []},
      {image: []},
    ],
    //이미지
    rawImg: [],
    
    // 모달 데이터
    commentSettingModalActive: false,
    logoutModalActive: false,
    userFeedSettingModalActive: false,
    profileImgChangeModalActive: false,
    loginConfirmModalActive: false,
    signupConfirmModalActive: false,
    emotionTestTooMuchPickModalActive: false,
    firstEmotionTestConfirmModalActive: false,
    emotionTestErrorActive: false,
    emotionTestPickMoreModalActive: false,
    emotionTestResultModalActive: false,
    ReturnToLoginModalActive: false,
    loginFailModalActive: false,
    signupFailModalActive1: false,
    signupFailModalActive2: false,
    commentNeedContentModalActive:false,
    moreInfoConfirmModalActive: false,
    serverErrorMessage: null,
    loginErrorMessage: null,

    // 알림 부분
    alarm: [], 
    lookuser : 2,
    alarmcheck : 0, // 이걸로 알람온거 체크할수 있을거 같은데...
    socketcount : 0, // 소켓 연결 일정시간 이상 안되면 재로그인 시키기
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
      console.log("userUpdate 접근 =====")
      const userdata = JSON.parse(session.getItem('userInfo')) 
      if (!session.getItem('userInfo')){
        session.setItem('userInfo', JSON.stringify(payload)) //토큰값으로 들어오면 
      }
      
      else if(typeof(payload) == 'number'){ // 감테하고 넘길때
        userdata.userInfo.mood = payload
        session.setItem('userInfo', JSON.stringify(userdata.userInfo))
      } 
      
      else if (session.getItem('userInfo')){
        session.setItem('userInfo', JSON.stringify(payload)) 
      }
      console.log('userUpdate 완료 ======' + session.getItem('userInfo'))
      state.userInfo = userdata
      return userdata
    },

    tokenTest(){
      console.log("test")
    },
    feedOut({ navActive }){
      Vue.set(navActive, 0, false)
    },
    // 피드작성 부분
    feedUserData: function (state, feedDto) {
      state.feedCreateData[0].author = feedDto.author
      state.feedCreateData[0].tags[0] = feedDto.tags[0]
      console.log(state.feedCreateData[0])
    },
    feedImg: function (state, imgs) {
      state.rawImg =imgs
      console.log(state.feedCreateData)
    },
    rawImg: function (state, imgs) {
      state.feedCreateData[1].image =imgs
      console.log(state.feedCreateData)
    },
    feedTag: function(state, feedtag) {
      state.feedCreateData[0].tags[1] = feedtag
      // state.feedCreateData[0].tags.push({name: `${feedtag}`, type: 0})
      console.log(state.feedCreateData[0].tags)
    },
    
    // 모달부분입니다
    commentSettingModalActivate: function (state) {
      state.commentSettingModalActive = !state.commentSettingModalActive
      console.log(state.commentSettingModalActive)
    },
    userFeedSettingModalActivate: function (state) {
      state.userFeedSettingModalActive = !state.userFeedSettingModalActive
      console.log(state.userFeedSettingModalActive)
    },
    logoutModalActivate: function (state) {
      state.logoutModalActive = !state.logoutModalActive
      console.log(state.logoutModalActive)
    },
    profileImgChangeModalActivate: function (state) {
      state.profileImgChangeModalActive = !state.profileImgChangeModalActive
      console.log(state.profileImgChangeModalActive)
    },
    loginConfirmModalActivate:function (state) {
      state.loginConfirmModalActive = !state.loginConfirmModalActive
      console.log(state.loginConfirmModalActive)
      // return state.loginConfirmModalActive
    },
    signupConfirmModalActivate:function (state) {
      state.signupConfirmModalActive = !state.signupConfirmModalActive
      console.log(state.signupConfirmModalActive)
    },
    emotionTestTooMuchPickModalActivate: function (state) {
      state.emotionTestTooMuchPickModalActive = !state.emotionTestTooMuchPickModalActive
      console.log(state.emotionTestTooMuchPickModalActive)
    },
    firstEmotionTestConfirmModalActivate: function (state) {
      state.firstEmotionTestConfirmModalActive = !state.firstEmotionTestConfirmModalActive
      console.log(state.firstEmotionTestConfirmModalActive)
    },
    emotionTestErrorModalActivate: function (state) {
      state.emotionTestErrorActive = !state.emotionTestErrorActive
      console.log(state.emotionTestErrorActive)
    },
    emotionTestPickMoreModalActivate: function (state) {
      state.emotionTestPickMoreModalActive = !state.emotionTestPickMoreModalActive
      console.log(state.emotionTestPickMoreModalActive)
    },
    emotionTestResultModalActivate: function (state) {
      state.emotionTestResultModalActive = !state.emotionTestResultModalActive
      console.log(state.emotionTestResultModalActive)
    },
    ReturnToLoginModalActivate: function (state) {
      state.ReturnToLoginModalActive = !state.ReturnToLoginModalActive
      console.log(state.ReturnToLoginModalActive)
    },
    loginFailModalActivate: function (state, errormessage) {
      state.loginErrorMessage = errormessage 
      state.loginFailModalActive = !state.loginFailModalActive
      console.log(state.loginFailModalActive)
    },
    signupFailModalActivate1: function (state, errormessage) {
      state.signupFailModalActive1 = !state.signupFailModalActive1
      state.serverErrorMessage = errormessage
      console.log(state.signupFailModalActive1)
    },
    signupFailModalActivate2: function (state) {
      state.signupFailModalActive2 = !state.signupFailModalActive2
      console.log(state.signupFailModalActive2)
    },
    commentNeedContentModalActivate: function (state) {
      state.commentNeedContentModalActive = !state.commentNeedContentModalActive
      console.log(state.commentNeedContentModalActive)
    },
    moreInfoConfirmModalActivate: function (state) {
      state.moreInfoConfirmModalActive = !state.moreInfoConfirmModalActive
      console.log(state.moreInfoConfirmModalActive)
    },
  },
  actions: {
    // 여기는 팔로우 부분 입니다.
    userfollowdate(state, el) {
      let headers = {
          'at-jwt-access-token': session.getItem('at-jwt-access-token'),
          'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
        };
      axios.get('http://13.125.47.126:8080/follows/'+this.state.userInfo.no+"/"+el, {
        headers: headers, 
        }).then((res) => {
          console.log("데이터 갱신 성공")
          console.log(res.data)
          this.state.userFollowInfo.userFollow = res.data.follower
          this.state.userFollowInfo.userFollowing = res.data.following
          console.log(this.state.userFollowInfo.userFollow.length)
          console.log(this.state.userFollowInfo.userFollowing.length)
        this.dispatch('accessTokenRefresh', res) // store에서
        }).catch((error) => {
          console.log("데이터 갱신 실패")
          console.log(error);
        }).then(() => {
          console.log('getQSSList End!!');
        });
      },

    // 여기는 알림 시작 --------------------------------------------------------
    follow() { //팔로우 알림 보내는 부분
      console.log("팔로우 알림");
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          sender: this.state.userInfo.no,
          receiver: this.state.lookuser,
          type: 1, 
        };
        this.stompClient.send(
          "/alarm/send/" + this.state.lookuser,
          JSON.stringify(msg),
          {}
        );
      }
    },

    comment() { // 댓글달면 누가 댓글달았는지 알려주는 부분
      console.log("댓글 알림");
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          sender: this.state.userInfo.no,
          receiver: this.state.lookuser,
          feedno: 1, // 여기 나중에 수정해야함
          commentno: 2,
          type: 2, 
        };
        this.stompClient.send(
          "/alarm/send/" + this.state.lookuser,
          JSON.stringify(msg),
          {}
        );
      }
    },

    feedlike() { //피드 좋아요누르면 누가 좋아요 눌렀는지 알려주는 부분
      console.log("좋아요 알림");
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          sender: this.state.userInfo.no,
          receiver: this.state.lookuser,
          feedno: 1, // 여기 나중에 수정해야함
          type: 3, 
        };
        this.stompClient.send(
          "/alarm/send/" + this.state.lookuser,
          JSON.stringify(msg),
          {}
        );
      }
    },

    picklike() { //피드 좋아요누르면 누가 좋아요 눌렀는지 알려주는 부분
      console.log("좋아요 알림");
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          sender: this.state.userInfo.no,
          receiver: this.state.lookuser,
          pickno: 1, // 여기 나중에 수정해야함
          type: 4, 
        };
        this.stompClient.send(
          "/alarm/send/" + this.state.lookuser,
          JSON.stringify(msg),
          {}
        );
      }
    },

    connect() { // 웹 소켓 연결하는 부분.
      const serverURL = "http://13.125.47.126:8080";
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);
      this.stompClient.connect(
        {},
        (frame) => {
          // 소켓 연결 성공
          this.connected = true;
          console.log("소켓 연결 성공",frame);
          // 서버의 메시지 전송 endpoint를 구독합니다.
          // 이런형태를 pub sub 구조라고 합니다.
          this.stompClient.subscribe(`/alarm/receive/${this.state.userInfo.no}`, (res) => {
            console.log("---------------------------------")
            const obj = JSON.parse(res.body);
            console.log("보낸사람 아이디 " + obj.sender)
            console.log("보낸사람 닉네임 " + obj.senderNickname)
            console.log("보낸사람 프로필 " + obj.senderImg)
            console.log("피드 번호 " + obj.feedno)
            console.log("댓글 번호 " + obj.commentno)
            console.log("알림 날짜 " + obj.date)
            console.log("알림 타입 " + obj.type)
            console.log("알림 내용 " + obj.message)
            alert(obj.message)
            console.log("---------------------------------")
            this.state.alarmcheck++; // 이거 실시간 알람오는거 증가시켜서 알림보여주기 알림보면 0으로 초기화?
            console.log(this.state.alarmcheck)
          });
        },
        (error) => {
          // 소켓 연결 실패
          console.log("소켓 연결 실패", error);
          this.connected = false;
          this.dispatch('connect'); // 소켓 재연결 시도
        }
      );
    },

    // 이부분은 나중에 알림 부분에서 사용할 예정
    alarmselect(){ // 디비에 있는 알림 가져오기
      axios({
        method: 'get',
        url:'http://13.125.47.126:8080/alarm/' + this.state.userInfo.no,
      })
      .then((res)=>{
        this.dispatch('accessTokenRefresh', res)
        console.log('알림 가져오기 성공')
        console.log(res.data)
      })
      .catch(err=> {
        console.log('알림 가져오기 실패')
        console.log(err.response.data.message) // 서버측에서 넘어온 오류 메시지 출력.
      })
    },
    
    alarmdelete(state ,el){ // 읽은 알림 지우기=> 알림번호 넘겨주는거 생각해야함
      axios({
        method: 'delete',
        url:'http://13.125.47.126:8080/alarm/' + el, // 여기 알림번호 넘겨줘야한다.
      })
      .then((res)=>{
        this.dispatch('accessTokenRefresh', res)
        console.log('알림 삭제 성공')
        console.log(res.data)
      })
      .catch(err=> {
        console.log('알림 삭제 실패')
        console.log(err.response.data.message) // 서버측에서 넘어온 오류 메시지 출력.
      })
    },
    // 여기는 알림 끝 -----------------------------------------------------


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
        this.dispatch('accessTokenRefresh', res)
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
        this.dispatch('accessTokenRefresh', res)
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
      
			setTimeout(() => {
        axios.get('http://13.125.47.126:8080/recommend/music/' + this.state.userInfo.mood, {
          headers: headers,
        }).then((res) => {
          this.state.recommendMusic = res.data
          this.dispatch('accessTokenRefresh', res)
        
        }).catch((error) => {
          console.log(error);
        }).then(() => {
          console.log('getQSSList End!!');
        });
      }, 1000);
    },
    recommendMovie() {
      let headers = {
        'at-jwt-access-token': session.getItem('at-jwt-access-token'),
        'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
      };
      setTimeout(() => {
			axios.get('http://13.125.47.126:8080/recommend/movie/' + this.state.userInfo.mood, {
          headers: headers,
        }).then((res) => {
          this.state.recommendMovie = res.data
          this.dispatch('accessTokenRefresh', res)
        
        }).catch((error) => {
          console.log(error);
        }).then(() => {
          console.log('getQSSList End!!');
        });
      }, 2000);
    },
    recommendActivity() {
      let headers = {
        'at-jwt-access-token': session.getItem('at-jwt-access-token'),
        'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
      };
      setTimeout(() => {
			axios.get('http://13.125.47.126:8080/recommend/activity/', {
          headers: headers,
        }).then((res) => {
          this.state.recommendActivity = res.data
          this.dispatch('accessTokenRefresh', res)
        
      }).catch((error) => {
        console.log(error);
      }).then(() => {
        console.log('getQSSList End!!');
      });
    }, 3000);
  },

    accessTokenRefresh({commit}, res) {
      console.log("accesstoken : " + res.headers)
      if(res.headers['at-jwt-access-token'] != session.getItem('at-jwt-access-token')){
        session.setItem('at-jwt-access-token', "");
        session.setItem('at-jwt-access-token', res.headers['at-jwt-access-token']);
        console.log("Access Token을 교체합니다!!!")
      }
      commit('tokenTest')
    },

    allTokenRefreshOnUserInfo({commit},res){ // 유저 정보 갱신할때 사용
      console.log("allTokenRefreshOnUserInfo : " + res.headers)
      session.setItem('at-jwt-access-token', res.headers['at-jwt-access-token']);
      const decodeAccessToken = jwt.decode(res.headers['at-jwt-access-token']);
      console.log('decodeAccessToken data', decodeAccessToken);
      commit('userUpdate', decodeAccessToken.userInfo)
    },

    allTokenRefresh({commit},res){
      console.log("alltoken : " + res.headers)
      session.setItem('at-jwt-access-token', res.headers['at-jwt-access-token']);
      session.setItem('at-jwt-refresh-token', res.headers['at-jwt-refresh-token']);

      const decodeAccessToken = jwt.decode(res.headers['at-jwt-access-token']);
      console.log('decodeAccessToken data', decodeAccessToken);
      commit('userUpdate', decodeAccessToken)
    },
    // userInfo: function(state, payload){
    //   console.log(payload)
    //   state.user = payload
    // },

    //유저정보 수정부분
    async updateuser(state ,el) {
      const body = {
        no: this.state.userInfo.no,
        nickname: this.state.userInfo.nickname,
        intro: this.state.userInfo.intro,
        pw: el, //null 해야지 데이터 안넘어감
        publish: this.state.userInfo.publish,
      };
      let headers = {
        'at-jwt-access-token': session.getItem('at-jwt-access-token'),
        'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
        };
      await axios({
        method: "put",
        url: "http://13.125.47.126:8080/users/update",
        data: body,
        headers: headers,
      })
        .then((res) => {
          console.log(body)
          console.log("업데이트 성공")
          console.log(res);
          this.dispatch('allTokenRefreshOnUserInfo', res)
          // console.log(this.state.userInfo)
          location.reload()
        })
        .catch((err) => {
          console.log("업데이트 실패")
          console.log(err);
        });
    },

    updateimg(state , el) {
      // const attachFiles = document.querySelector("#inputFileUploadInsert"); // 파일 인풋에 id값 지정하기
      // const body = {
      //   no: 2,
      //   file : el,
      // };
      let headers = {
        'at-jwt-access-token': session.getItem('at-jwt-access-token'),
        'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
        "Content-Type": "multipart/form-data",
        };
      axios({
        method: "post",
        url: "http://localhost:8080/s3/users/img",
        data: el,
        headers: headers,
      })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },

  },
  modules: {
    
  }
})