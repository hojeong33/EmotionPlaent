<template>
  <div id="feed-list">
    <div>
      <feed v-for="post in posts"
        :post="post"
        :key="posts.indexOf(post)">
        </feed>
    </div>
  </div>
</template>

<script>
import Feed from "./Feed.vue";
import axios from 'axios';
const session = window.sessionStorage;

export default {
  name: "FeedList",
  props: {
    // posts: Array,
  },
  data(){
    return{
      posts:[],
    }
  },
  components: {Feed,
  },
  created(){
    let headers = {
        'at-jwt-access-token': session.getItem('at-jwt-access-token'),
        'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
      };
      axios({
        method:'get',
        url:'http://13.125.47.126:8080/feeds/my/4',
        headers:headers,
       })
      .then((res) => {
      console.log(res);
      console.log('response header', res.headers);
      if(res.headers['at-jwt-access-token'] != session.getItem('at-jwt-access-token')){
        session.setItem('at-jwt-access-token', "");
        session.setItem('at-jwt-access-token', res.headers['at-jwt-access-token']);
        console.log("Access Token을 교체합니다!!!")
      }
      // console.log(res.data)
      this.posts=res.data
      console.log(this.posts)
    }).catch((error) => {
      console.log(error);
    }).then(() => {
      console.log('피드 가져오기 클리어');
    });
  }
};
</script>

<style scoped>


</style>