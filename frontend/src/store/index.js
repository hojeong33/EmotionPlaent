import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    feedActive: false,
    commentContent:null,
    comments:[],
    commentsList:[],
    nowFeedNo:0,
    user: null
  },
  mutations: {
    activateFeed: function(state){
      console.log(state)
      state.feedActive = !state.feedActive
    },
    GET_COMMENTS:function(state,data){
      state.comments=[]
      state.comments=data
      state.commentsList=data.slice(0,2)
    },
    
    userInfo: function(state, payload){
      console.log(payload)
      state.user = payload
    }
  },
  actions: {
    getComments:function({commit},feedNo){
      axios({
        method:'get',
        url:`http://13.125.47.126:8080/comments/${feedNo}`,
       })
       .then((res)=>{
         commit('GET_COMMENTS',res.data)
    
       })
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

  },
  modules: {
  }
})
