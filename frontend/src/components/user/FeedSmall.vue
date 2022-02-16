<template>
  <div class="feed-s" v-if="feed" @click="feeddetail">
    <img class="feed-s-thumbnail" :src="feed.imgs[0].imgLink" :alt="this.$store.state.userInfo.username"
    @mouseover="hover = true">
    <img class="feed-planet" :src="require(`@/assets/images/emotions/${planet}`)" id="planet">
    <span class="feed-s-info" v-show="hover" @mouseleave="hover = false">
      <span class="info">
        <img src="@/assets/images/icons/heart.png" alt="heart">
        <p>{{ feed.likes.length }}</p>
      </span>
      <span class="info">
        <img src="@/assets/images/icons/chat.png" alt="comment">
        <p>{{ feed.comments.length }}</p>
      </span>
    </span>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data(){
    return {
      feed:null,
      hover: false,
      planetStyles: [
				{ id: 0, name: 'default'},
        { id: 1, name: '행복행성', img: "happy.png", color: '#6BD9E8' },
        { id: 2, name: '우울행성', img: "depressed.png", color: '#2A61F0' },
        { id: 3, name: '심심행성', img: "neutral.png", color: '#ABBECA' },
        { id: 4, name: '공포행성', img: "fear.png", color: '#ED5A8E' },
        { id: 5, name: '깜짝행성', img: "surprised.png", color: '#FEA95C' },
        { id: 6, name: '분노행성', img: "rage.png", color: '#FB5D38' },
      ],
    }
  },
  props: {
    idx: Number,
  },
    methods:{
    feeddetail() {
      this.$router.push({name:'FeedDetail', params:{feedNo:this.feed.no}})
    },
  },
  computed: {
    planet() {
			const idx = this.feed.tags[0].no
			if (idx){
				return this.planetStyles[idx].img
			}
			return "neutral.png"
		}
  },
  created(){
    const session = window.sessionStorage
    let headers = {
      'at-jwt-access-token': session.getItem('at-jwt-access-token'),
      'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
    };
    axios({
      method: 'get',
      url:`/api/feed/${this.idx}`,
      headers: headers,  // 넣는거 까먹지 마세요
    })
    .then((res) => {
      this.$store.dispatch('accessTokenRefresh', res) // store아닌곳에서
      console.log('!!!!!!!!!!!!!!!!!!!')
      console.log(res.data)
      this.feed=res.data
    })
    .catch((error) => {
      console.log(error);
    })
  }
}
</script>

<style scoped>
  img {
    width: 20px;
    height: inherit;
    aspect-ratio: 1/1;
  }

  p {
    color: white;
    font-size: 1.125rem;
    margin: 0 0.5rem;
  }

  .feed-s {
    position: relative;
    cursor: pointer;
    border: 1px #cccccc solid;
    border-radius: 20px;
    width: 100%;
    aspect-ratio: 1/1;
  }

  .feed-s-thumbnail {
    width: 100%;
    height: 100%;
    aspect-ratio: 1/1;
    border-radius: 20px;
    position: relative;
  }

  .feed-s-info {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-evenly;
    align-items: center;
    background-color: rgb(0, 0, 0, 0.5);
    position: absolute;
    top: 0;
    left: 0;
    z-index: 1;
    width: 100%;
    height: 100%;
    border-radius: 20px;
    padding: 1rem;
  }

  .info {
    display: flex;
    flex-wrap: nowrap;
    align-items: center;
  }

  .feed-planet {
		position: absolute;
		width: 25%;
		height: 25%;
		bottom: 5%;
    left: 5%;
		border-radius: 50%;
		border: 3px solid;
		border-color: white;
	}
</style>