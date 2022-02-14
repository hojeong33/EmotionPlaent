<template>
  <div id="search-result-container">
    <div id="search-taps">
      <h2 @click="tab = 'feed'" :class="{ 'active':tab == 'feed' }">피드</h2>
      <h2 @click="tab = 'pick'" :class="{ 'active':tab == 'pick' }">찜 목록</h2>
    </div>
    <div id="pick-container" v-if="tab == 'pick'">
			<div id="pick-tab">
				<h3 @click="pickTap = 1" :class="pickTap == 1 ? 'pick-active': ''">음악</h3>
				<h3 @click="pickTap = 2" :class="pickTap == 2 ? 'pick-active': ''">영화</h3>
				<h3 @click="pickTap = 3" :class="pickTap == 3 ? 'pick-active': ''">활동</h3>
			</div>
			<div id="picks"> 
				<pick-list v-for="(pick, idx) in filteredPicks" :key="idx" :pick="pick" />
			</div>
		</div>
    <feed-list v-if="tab=='feed'"/>
    <div id="no-result" v-if="(tab == 'feed' && !feeds.length)||(tab == 'pick' && !filteredPicks.length)">
			<img id="nothing" src="@/assets/images/etc/alien.png" alt="no result">
			<p v-if="tab == 'feed' && !filteredFeeds.length">게시글이 없어요...</p>
			<p v-if="tab == 'pick' && !filteredPicks.length">찜목록이 없어요...</p>
		</div>
  </div>
</template>

<script>
import FeedList from '@/components/Search/_SearchFeedList'
import PickList from '@/components/Search/_SearchPickList'

export default {
  data(){
    return {
      tab: 'feed',
      pickTap: 1
    }
  },
  components: {
    FeedList,
    PickList
  },
  computed: {
    filteredPicks(){
      const temp = []
      this.pickData.forEach(ele => {
        if (ele.no == this.pickTap){
          temp.push(ele)
        }
      })
      return temp
    },
  }
}
</script>

<style scoped>
  h2 {
    color: #777777;
    font-size: 1.5rem;
    margin: 0 1rem;
    cursor: pointer;
  }

  h3 {
    color: #777777;
    font-size: 1.25rem;
    font-weight: bold;
    margin: 0;
    cursor: pointer;
  }

  p {
    color: #777777;
    margin: 1rem;
    font-size: 1.125rem;
  }

  #search-result-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 50%;
    min-width: 700px;
    min-height: 100vh;
    border: 2px #cccccc solid;
    margin: 0 auto;
  }

  #search-taps {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    padding: 1rem;
    border-bottom: 1px #cccccc solid;
  }

  #pick-container {
    display: flex;
		flex-direction: column;
		align-items: center;
		width: 100%;
    margin-top: 3rem;
  }

  #pick-tab {
    display: flex;
    width: 40%;
    justify-content: space-evenly;
    align-items: center;
  }

  #picks {
    display: grid;
    justify-content: center;
    justify-items: start;
    align-items: baseline;
    grid-auto-rows: minmax(min-content, max-content);
    grid-template-columns: 1fr 1fr 1fr;
    width: 80%;
    gap: 2rem;
    margin: 5rem;
  }

  #no-result {
		display:flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		padding-top: 20vh;
	}

	#nothing {
    width: 10%;
    height: inherit;
    aspect-ratio: 1/1;
  }


  .active {
    color: #5e39b3;
    font-size: 1.75rem;
    font-weight: bold;
  }

  .pick-active {
    color: black;
    font-size: 1.4rem;
    font-weight: bold;
  }
</style>