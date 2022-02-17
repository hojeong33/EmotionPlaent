<template>
  <article id="list-container">
		<filter-tab :user-mood="userMood" @filtering="filtering" />

		<div id="feed-container" v-if="tab == 'feed'"> 
			<feed-list :feeds="feeds" :filter="filter" />
		</div>

		<div id="pick-container" v-if="tab == 'pick'">
			<div id="pick-tab">
				<h3 @click="pickTab = 0" :class="pickTab == 0 ? 'active': ''">음악</h3>
				<h3 @click="pickTab = 1" :class="pickTab == 1 ? 'active': ''">영화</h3>
				<h3 @click="pickTab = 2" :class="pickTab == 2 ? 'active': ''">활동</h3>
			</div>
			<div id="picks"> 
				<pick-list v-for="(pick, idx) in filteredPicks" :key="idx" :pick="pick"  />
			</div>
		</div>

		<!-- <div id="no-result" 
		v-if="(tab == 'feed' && !filteredFeeds.length)||(tab == 'pick' && !filteredPicks.length)">
			<img id="nothing" src="@/assets/images/etc/alien.png" alt="no result">
			<p v-if="tab == 'feed' && !filteredFeeds.length">게시글이 없어요...</p>
			<p v-if="tab == 'pick' && !filteredPicks.length">찜목록이 없어요...</p>
		</div> -->
		
	</article>
</template>

<script>
import FilterTab from '@/components/user/FilterTab'
import FeedList from '@/components/user/FeedList'
import PickList from '@/components/user/PickList'
import axios from 'axios'

const session = window.sessionStorage;

export default {
	name: 'UserFeed',
	data: function () {
		return {
			planetStyles: [
				{ id: 0, name: 'default'},
        { id: 1, name: '행복행성', img: "happy.png", color: '#ED5A8E' },
        { id: 2, name: '우울행성', img: "depressed.png", color: '#6BD9E8' },
        { id: 3, name: '심심행성', img: "neutral.png", color: '#C5D3DC' },
        { id: 4, name: '공포행성', img: "fear.png", color: '#FEA95C' },
        { id: 5, name: '깜짝행성', img: "suprised.png", color: '#FB5D38' },
        { id: 6, name: '분노행성', img: "rage.png", color: '#2A61F0' },
      ],
			filter: 0,
			pickTab: 0,
			feeds: null,
			picks: null,
		}
	},
	props: {
		tab: String,
		userId: Number
	},
	components: {
		FilterTab,
		FeedList,
		PickList
	},
	methods: {
		filtering(payload){
			this.filter = payload
		},
	},
	computed: {
		userMood(){
			return this.$store.state.userInfo.mood
		},
		filteredPicks(){
            const temp = []
            if (this.picks){
                this.picks.forEach(ele => {
					if(this.filter){
						if(this.filter==ele.tagNo && this.pickTab==ele.type){
							temp.push(ele)
						}
					}else{
						if(this.pickTab==ele.type){
							temp.push(ele)
						}
					}
                })
            }
            return temp
		}
		
	},
	created(){
		let user = JSON.parse(session.getItem('userInfo')).no
		let headers = {
			'at-jwt-access-token': session.getItem('at-jwt-access-token'),
			'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
		};

		if (this.$route.matched[0].path !== '/mypage'){
			user = this.userId
		}
		axios({
			method:'get',
			url:`/api/feeds/my/returnNo/${user}`,
			headers:headers,
		})
		.then((res) => {
			if(res.headers['at-jwt-access-token'] != session.getItem('at-jwt-access-token')){
				session.setItem('at-jwt-access-token', "");
				session.setItem('at-jwt-access-token', res.headers['at-jwt-access-token']);
				console.log("Access Token을 교체합니다!!!")
			}
			this.feeds=res.data
		})
		.catch((error) => {
			console.log(error);
		})
		.finally(() => {
			console.log('피드 가져오기 클리어');
		});
	},mounted(){
		let user = JSON.parse(session.getItem('userInfo')).no
		let headers = {
			'at-jwt-access-token': session.getItem('at-jwt-access-token'),
			'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
		};

		if (this.$route.matched[0].path !== '/mypage'){
			user = this.userId
		}
		axios({
			method:'get',
			url:`/api/picks/user/${user}`,
			headers:headers,
		})
		.then((res) => {
			if(res.headers['at-jwt-access-token'] != session.getItem('at-jwt-access-token')){
				session.setItem('at-jwt-access-token', "");
				session.setItem('at-jwt-access-token', res.headers['at-jwt-access-token']);
				console.log("Access Token을 교체합니다!!!")
			}
			this.picks=res.data
			console.log('픽!!!!!!!!!!!!!!!!')
			console.log(this.picks)
		})
		.catch((error) => {
			console.log(error);
		})
		.finally(() => {
			console.log('피드 가져오기 클리어');
		});

	}
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
    font-size: 1.5rem;
    font-weight: bold;
    margin: 2.5rem;
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
		padding-top: 1rem;
	}

	#nothing {
    width: 30%;
	height: 30%;
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