<template>
  <article id="list-container">
		<filter-tab :user-mood="userMood" @filtering="filtering" />

		<div id="feed-container" v-if="tap == 'feed'"> 
			<!-- <feed-item v-for="(feed, idx) in filteredFeeds" :key="idx" :feed="feed" /> -->
			<search-feed-list :feeds="filteredFeeds"/>
		</div>

		<div id="pick-container" v-if="tap == 'pick'">
			<div id="pick-tab">
				<h3 @click="pickTap = 1" :class="pickTap == 1 ? 'active': ''">음악</h3>
				<h3 @click="pickTap = 2" :class="pickTap == 2 ? 'active': ''">영화</h3>
				<h3 @click="pickTap = 3" :class="pickTap == 3 ? 'active': ''">활동</h3>
			</div>
			<div id="picks"> 
				<search-pick-item v-for="(pick, idx) in filteredPicks" :key="idx" :pick="pick" />
			</div>
		</div>

		<div id="no-result" 
		v-if="(tap == 'feed' && !filteredFeeds.length)||(tap == 'pick' && !filteredPicks.length)">
			<img id="nothing" src="@/assets/images/etc/alien.png" alt="no result">
			<p v-if="tap == 'feed' && !filteredFeeds.length">게시글이 없어요...</p>
			<p v-if="tap == 'pick' && !filteredPicks.length">찜목록이 없어요...</p>
		</div>
		
	</article>
</template>

<script>
import FilterTab from '@/components/user/FilterTab'
// import FeedItem from '@/components/user/FeedItem'
import SearchFeedList from '@/components/Search/SearchFeedList'
import SearchPickItem from '@/components/Search/SearchPickitem'
import feedData from '@/assets/data/userFeed'
import pickData from '../../assets/data/pickData'

export default {
	name: 'UserFeed',
	data: function () {
		return {
			feedData,
			pickData,
			planetStyles: [
				{ id: 0, name: 'default'},
        { id: 1, name: '행복행성', img: "happy.png", color: '#ED5A8E' },
        { id: 2, name: '우울행성', img: "depressed.png", color: '#6BD9E8' },
        { id: 3, name: '중립행성', img: "neutral.png", color: '#C5D3DC' },
        { id: 4, name: '공포행성', img: "fear.png", color: '#FEA95C' },
        { id: 5, name: '깜짝행성', img: "suprised.png", color: '#FB5D38' },
        { id: 6, name: '분노행성', img: "rage.png", color: '#2A61F0' },
      ],
			filter: 0,
			pickTap: 1,
		}
	},
	props: {
		userMood: Number,
		tap: String
	},
	components: {
		FilterTab,
		SearchFeedList,
		SearchPickitem
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
      const temp = []
      this.pickData.forEach(ele => {
        if (!this.filter && ele.no == this.pickTap){
          temp.push(ele)
        }
        else if (ele.no == this.pickTap && ele.tagNo == this.filter){
          temp.push(ele)
        }
      })
      return temp
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

	#feed-container {
		width: 100%;
		/* display: grid;
		grid-template-columns: 1fr 1fr 1fr 1fr;
		grid-auto-flow: row;
		justify-content: center;
		gap: 1rem; */
	}

	#pick-container {
    display: flex;
		flex-direction: column;
		align-items: center;
		width: 100%;
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

	#filter {
		display: flex;
	}

	.active {
    color: black;
    font-size: 1.4rem;
    font-weight: bold;
    margin-bottom: 0.5rem;
  }

</style>