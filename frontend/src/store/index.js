import Vue from 'vue'
import Vuex from 'vuex'
// import axios from 'axios'

Vue.use(Vuex)


// const session = window.sessionStorage;
// const jwt = require('jsonwebtoken');

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
    // GET_COMMENTS:function(state,data){
    //   state.comments.push()
    //   state.commentsList=data.slice(0,2)
    // },
    
    userInfo: function(state, payload){
      console.log(payload)
      state.user = payload
    }
  },
  actions: {
    // getComments:function({commit},feedNo){
    //   let headers = {
    //     'at-jwt-access-token': session.getItem('at-jwt-access-token'),
    //     'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
    //   };
    //   axios({
    //     method:'get',
    //     url:`http://13.125.47.126:8080/comments/${feedNo}`,
    //     headers:headers,
    //    })
    //   .then((res) => {
    //   console.log(res);
    //   console.log('response header', res.headers);
    //   if(res.headers['at-jwt-access-token'] != session.getItem('at-jwt-access-token')){
    //     session.setItem('at-jwt-access-token', "");
    //     session.setItem('at-jwt-access-token', res.headers['at-jwt-access-token']);
    //     console.log("Access Token을 교체합니다!!!")
    //   }
    //   console.log('여기여기')
    //   console.log(res.data)
    //   commit('GET_COMMENTS',res.data) 

    // }).catch((error) => {
    //   console.log(error);
    // }).then(() => {
    //   console.log('댓글 가져오기 클리어');
    //   // console.log(this.state.comments)
    //   // console.log(this.state.commentsList)

    // });
    },
    // postComment:function({commit}){
    //   axios({
    //     method:'post',
    //     url:'http://13.125.47.126:8080/comments',
    //     data:commentItem
    //   })
    //   .then(()=>{
    //     this.commentContent=null
    //   })
    // },

    // updateComment:function({commit}){

    // },
    // deleteComment:function({commit}){
    //   axios({
    //     method:'delete',
    //     url:`http://13.125.47.126:8080/comments/${this.comment.no}`,
    //   })
    //   .then(()=>{
    //     console.log('바이')
    //   })
    // }
  modules: {
  }
})
