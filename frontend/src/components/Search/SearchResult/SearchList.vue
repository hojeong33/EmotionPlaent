<template>
  <article id="list-container">
		<filter-tab v-if="tab == 'feed'" :user-mood="userMood" @filtering="filtering" />
		<filter-tab v-if="tab == 'pick'" style="visibility:hidden;" />

		<search-feed-list v-if="tab == 'feed'" :feeds="filteredFeeds" />
		<search-pick-list v-if="tab == 'pick'" :picks="this.filteredPick" />
		<div id="no-result" 
			v-if="(tab == 'feed' && this.filteredFeed.length === 0)||(tab == 'pick' && this.filteredPick.length === 0)">
			<img id="nothing" src="@/assets/images/etc/alien.png" alt="no result">
			<p v-if="tab == 'feed' && this.filteredFeed.length === 0">게시글이 없어요...</p>
			<p v-if="tab == 'pick' && this.filteredPick.length === 0">찜목록이 없어요...</p>
		</div>
	</article>
</template>

<script>
import FilterTab from '@/components/user/FilterTab'
import SearchFeedList from '@/components/Search/SearchResult/SearchFeedList'
import SearchPickList from '@/components/Search/SearchResult/SearchPickList'

export default {
	data(){
		return {
			feedData: null,
			filter: 0,
			filteredFeed: [],
			filteredPick: [],
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
			this.filteredFeed = []
    }
	},
	mounted: function() {
		this.feedData = this.$store.state.tagSearchResult
		if(this.$store.state.searchPickList !== null){
			this.filteredPick = this.$store.state.searchPickList
		}
		console.log(this.filteredPick)
	},
	computed: {
		filteredFeeds(){
			if (this.filter){
				const temp = []
				this.feedData.forEach(feed => {
					if (feed.tagno == this.filter){
						temp.push(feed)
					}
				});
				return temp
			}
			return this.feedData
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