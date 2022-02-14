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
    const userdata = JSON.parse(session.getItem('userInfo')) 
    let headers = {
        'at-jwt-access-token': session.getItem('at-jwt-access-token'),
        'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
      };
      axios({
        method:'get',
        url:`http://13.125.47.126:8080/feeds/my/returnNo/${userdata.no}`,
        headers:headers,
       })
      .then((res) => {
      console.log('여기보세여영 피드피드')
      console.log(res.data);
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
  h3 {
    margin: 0 1rem;
  }

  button {
    width: 4vh;
    height: 4vh;
    background-size: cover;
    border: none;
    margin: 0.5rem;
    border-radius: 20%;
  }

  #feed-list {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
  }

  #show-btns {
    display: flex;
    align-items: center;
    align-self: flex-start;
    box-sizing: content-box;
    margin: 1rem;
  }

  #grid-container {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr;
    gap: 1rem;
    width: 100%;
    padding: 2rem;
  }

  #card-container {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .g {
    background-image: url('../../../assets/images/icons/grid.png');
  }

  .g-active {
    background-image: url('../../../assets/images/icons/grid-active.png');
  }

  .c {
    background-image: url('../../../assets/images/icons/card.png');
  }

  .c-active {
    background-image: url('../../../assets/images/icons/card-active.png');
  }
</style>