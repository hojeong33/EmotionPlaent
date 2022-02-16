<template>
  <div id="feed-list">
    <!-- <div id="show-btns">
      <h3>보기</h3>
      <button @click="showOption = 'grid'" :class="showOption == 'grid' ? 'g-active':'g' " />
      <button @click="showOption = 'card'" :class="showOption == 'card' ? 'c-active':'c' " />
    </div> -->
    <div v-if="showOption == 'grid'" id="grid-container">
      <feed-small v-for="feed in filteredFeeds"
        :idx="feed"
        :key="feed" />
    </div>
    <!-- <div v-if="showOption == 'card'" id="card-container">
      <feed v-for="feed in filteredFeeds"
        :idx="feed"
        :key="feed" />
    </div> -->
  </div>
</template>

<script>
// import Feed from "@/components/user/Feed.vue";
import FeedSmall from "@/components/user/FeedSmall.vue";
import axios from 'axios'

export default {
  name: "FeedList",
  data(){
    return {
      rawFeeds: null,
      showOption: 'grid'
    }
  },
  props: {
    feeds: Array,
    filter: Number
  },
  components: {
    // Feed,
    FeedSmall
  },
  computed: {
    filteredFeeds(){
      if (this.rawFeeds && this.filter){
        const temp = []
        this.rawFeeds.filter(feed => {
          if (feed.tags[0] == this.filter){
            temp.push(feed.no)
          }
        })
        console.log('나는 필터됐용',temp)
        return temp
      }
      return this.feeds
    }
  },
  created(){
    const session = window.sessionStorage
		const userdata = JSON.parse(session.getItem('userInfo'))

    let headers = {
      'at-jwt-access-token': session.getItem('at-jwt-access-token'),
      'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
    };
    axios({
      method:'get',
      url:`http://13.125.47.126:8080/feeds/my/${userdata.no}`,
      headers:headers,
    })
    .then((res) => {
      if(res.headers['at-jwt-access-token'] != session.getItem('at-jwt-access-token')){
        session.setItem('at-jwt-access-token', "");
        session.setItem('at-jwt-access-token', res.headers['at-jwt-access-token']);
        console.log("Access Token을 교체합니다!!!")
      }
      this.rawFeeds=res.data
      console.log('내는 피드리스트여', this.rawFeeds)
    })
    .catch((error) => {
      console.log('FeedList', error);
    })
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
    width: 100%;
    padding: 1rem;
  }

  .g {
    background-image: url('../../assets/images/icons/grid.png');
  }

  .g-active {
    background-image: url('../../assets/images/icons/grid-active.png');
  }

  .c {
    background-image: url('../../assets/images/icons/card.png');
  }

  .c-active {
    background-image: url('../../assets/images/icons/card-active.png');
  }
</style>