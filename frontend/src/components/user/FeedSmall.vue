<template>
  <div class="feed-s">
    <img class="feed-s-thumbnail" :src="post.imgs[0].imgLink" :alt="this.$store.state.userInfo.username"
    @mouseover="hover = true" >
    <img class="feed-planet" :src="require(`@/assets/images/emotions/${planet}`)" id="planet" >
    <span class="feed-s-info" v-show="hover" @mouseleave="hover = false" @click="feeddetail()">
      <span class="info">
        <img src="@/assets/images/icons/heart.png" alt="heart">
        <p>{{ post.likes }}</p>
      </span>
      <span class="info">
        <img src="@/assets/images/icons/chat.png" alt="comment">
        <p>{{ post.comments.length }}</p>
      </span>
    </span>
  </div>
</template>

<script>
export default {
  data(){
    return {
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
    post: Object,
  },
    methods:{
    feeddetail() {
      this.$router.push({name:'FeedDetail', params:{feedNo:this.post.no}})
    },
  },
  computed: {
    planet() {
			const idx = this.post.tags[0].no
			if (idx){
				return this.planetStyles[idx].img
			}
			return "neutral.png"
		}
  },
  created () {
    console.log('이건 피드스몰', this.post)
  }
}
</script>

<style scoped>
  img {
    width: 20px;
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
    border: 1px solid;
    border-radius: 20px;
    width: 12rem;
    height: 12rem;
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