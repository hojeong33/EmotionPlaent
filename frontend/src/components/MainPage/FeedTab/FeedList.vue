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
    axios({
      method:'get',
      url:'http://13.125.47.126:8080/feeds/my/4',
    })
    .then((res)=>{
      // console.log(res.data)
      this.posts=res.data
      console.log(this.posts)
    })
  }
};
</script>

<style scoped>


</style>