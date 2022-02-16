<template>
  <article id="list-container">
		<filter-tab :user-mood="userMood" @filtering="filtering" />

		<search-feed-list v-if="tab == 'feed'" :feeds="filteredFeeds" />
		<search-pick-list v-if="tab == 'pick'" :picks="filteredPicks" />
		<div id="no-result" 
		v-if="(tab == 'feed' && !filteredFeeds.length)||(tab == 'pick' && !filteredPicks.length)">
			<img id="nothing" src="@/assets/images/etc/alien.png" alt="no result">
			<p v-if="tab == 'feed' && !filteredFeeds.length">게시글이 없어요...</p>
			<p v-if="tab == 'pick' && !filteredPicks.length">찜목록이 없어요...</p>
		</div>
	</article>
</template>

<script>
import FilterTab from '@/components/user/FilterTab'
import SearchFeedList from '@/components/Search/SearchResult/SearchFeedList'
import SearchPickList from '@/components/Search/SearchResult/SearchPickList'
import feedData from '@/assets/data/userFeed'
import pickData from '@/assets/data/pickData'

export default {
	data(){
		return {
			feedData,
			pickData,
			filter: 0
		}
	},
	props: {
		userMood: Number,
		tab: String
	},
	components: {
		FilterTab,
		SearchFeedList,
		SearchPickList
	},
	methods: {
		filtering(payload){
      this.filter = payload
    }
	},
	computed: {
		filteredFeeds(){
			if (this.filter){
				const temp = []
				this.feedData.forEach(feed => {
					if (feed.planet == this.filter){
						temp.push(feed)
					}
				});
				return temp
			}
			return this.feedData
		},
		filteredPicks(){
			if (this.filter){
				const temp = []
				this.pickData.forEach(pick => {
					if (pick.planet == this.filter){
						temp.push(pick)
					}
				});
				return temp
			}
			return this.pickData
    },
	},
}
</script>

<style scoped>
	h3 {
    color: #777777;
    font-size: 1.2rem;
    font-weight: bold;
    margin-bottom: 0.5rem;
    cursor: pointer;
  }
  
  p {
    color: #777777;
    font-size: 1.25rem;
    font-weight: bold;
    margin: 3rem;
  }

	#list-container {
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
		align-items: center;
		width: 100%;
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

	#filter {
		display: flex;
	}
</style>