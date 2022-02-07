<template>
  <div class="container">
		<filter-tab 
		:user-mood="mood" 
		@filtering="filterFeed" id="filter"></filter-tab>
		<br>
		<div v-if="isExist">
			<div @change="filterFeed">
				<div id="feeds" v-if="filteredFeed.length === 0 && filteredExist === true"> 
					<article id="feed" v-for="(feed, idx) in feedList" :key="idx">
						<img id="feed_img" :src="feed.feedImg" alt="">
						<img id="feed_planet" :src="require('@/assets/images/emotions/'+`${feed.feedPlanet}`)" alt=""> 
					</article>
				</div>
				<div id="feeds" v-else-if="filteredFeed && filteredExist === true">
					<article id="feed" v-for="(feed, idx) in filteredFeed" :key="idx">
						<img id="feed_img" :src="feed.feedImg" alt="">
						<img id="feed_planet" :src="require('@/assets/images/emotions/'+`${feed.feedPlanet}`)" alt=""> 
					</article>
				</div>
				<div id="no_result" v-else-if="filteredFeed.length === 0 && filteredExist ===false">
					<img id="nothing" src="@/assets/images/etc/alien.png" alt="">
					<p>게시글이 없어요...</p>
				</div>
			</div>
		</div>
		<div v-else id="no_result">
			<img id="nothing" src="@/assets/images/etc/alien.png" alt="">
			<p>게시글이 없어요...</p>
		</div>
	</div>
</template>

<script>
import FilterTab from '@/components/user/FilterTab.vue'

export default {
	name: 'UserFeed',
	data: function () {
		return {
			isExist: false,
			// 유저 데이터는 서버 또는 store에서 받아와야 한다. 지금은 임시 데이터
			user_feed: [
				{
					username: "조은누리",
					date:"2022-01-01",
					userImage: "https://newspenguin.com/news/photo/202006/1837_5156_215.jpg",
					postImage:
						"http://cdn.catholicnews.co.kr/news/photo/202001/22135_43951_5525.jpg",
					tag:["우울행성","보았어요","밤하늘"],
					likes: 36,
					hasBeenLiked: false,
					caption: "내 기분은 ☀️",
					comment_cnt:"2",
					comments:["반가워요","안녕하세요"],
					planet: 1
				},
				{
					username: "조은누리",
					date:"2022-01-01",
					userImage: "https://s3.ap-northeast-2.amazonaws.com/elasticbeanstalk-ap-northeast-2-176213403491/media/magazine_img/magazine_280/5-3-%EC%8D%B8%EB%84%A4%EC%9D%BC.jpg",
					postImage: "https://img.siksinhot.com/article/1595482500150524.jpg",
					tag:["우울행성","보았어요","밤하늘"],
					likes: 20,
					hasBeenLiked: false,
					caption: "맥주한잔생각나는밤이군",
					comment_cnt:"2",
					comments:["반가워요","안녕하세요"],
					planet: 1
				},
				{
					username: "조은누리",
					date:"2022-01-01",
					userImage:"https://i0.wp.com/dailypetcare.net/wp-content/uploads/2020/11/Screen-Shot-2020-11-24-at-9.10.35-PM-edited-e1606302091776.png?fit=1236%2C694&ssl=1",
					postImage:"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDTsmB9x2oiL4FX8nArPQ8YkocNQRGL2SeHg&usqp=CAU",
					tag:["우울행성","보았어요","밤하늘"],
					likes: 49,
					hasBeenLiked: false,
					caption: "멍멍🐶",
					comment_cnt:"2",
					comments:["반가워요","안녕하세요"],
					planet: 2
				}
			],
			planetStyles: [
				{ id: 0, name: 'default'},
        { id: 1, name: '행복행성', img: "happy.png", color: '#ED5A8E' },
        { id: 2, name: '우울행성', img: "depressed.png", color: '#6BD9E8' },
        { id: 3, name: '중립행성', img: "neutral.png", color: '#C5D3DC' },
        { id: 4, name: '공포행성', img: "fear.png", color: '#FEA95C' },
        { id: 5, name: '깜짝행성', img: "suprised.png", color: '#FB5D38' },
        { id: 6, name: '분노행성', img: "rage.png", color: '#2A61F0' },
      ],
			feedList: [],
			filteredFeed: [],
			filteredExist: true,
			mood: null,
		}
	},
	props: {
		userMood: Number,
	},
	components: {
		FilterTab
	},
	methods: {
		filterFeed: function (filterValue) {
			if (filterValue === '0') {
				//값 초기화
				this.filteredExist = true
				this.filteredFeed = []
				// console.log(this.feedList)
				// console.log(this.filteredExist)
			} else {
				// 값 초기화
				this.filteredFeed = []
				this.filteredExist = true

				for (let filteredEmotion of this.feedList) {
					if (filteredEmotion.planetId === Number(filterValue)) {
						this.filteredFeed.push(filteredEmotion)
						// console.log(this.filteredFeed)
					}
				}
				// 필터링 값이 없을 경우
				if (this.filteredFeed.length === 0) {
					this.filteredExist = false
					// console.log(this.filteredExist)
				}
			}
			// console.log(this.filteredFeed)
		}
	},
	created: function () {
		this.mood = this.userMood
		for (let feed of this.user_feed) {
			for (let emotion of this.planetStyles) {
				if (feed.planet === emotion.id) {
					// const planetImg = `../../assets/images/emotions/${emotion.img}`
					this.feedList.push({'feedImg': feed.postImage, 'planetId': emotion.id, 'feedPlanet': emotion.img}) 
				}
			}
		}
		// console.log(this.feedList)
		if (this.feedList) {
			this.isExist = true 
		}
		// console.log(this.isExist)
	}
	// methods: function () {
	// 	// axios로 서버에서 데이터 받아오기
	// 	if 
	// }
}
</script>

<style scoped>
/* 피드가 일정한 간격으로 왼쪽정렬로 해서 나와야 함 */
.container{
	width: 80vh;
	margin: auto;
}
#no_result {
	display:flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	padding-top: 5vh;
}
#nothing {
	width: 10vh;
	height: 10vh;
	margin-bottom: 2vh;
}
p{
	font-weight: bold;
}
#filter {
	display: flex;
	justify-content: right;
}
#feeds{
	display: flex;
}
#feed {
	display: flex;
	flex-direction: left;
	padding: 1rem;
	position: relative;
}
#feed_img {
	width: 22vh;
	height: 22vh;
	border-radius: 10px;
}
#feed_planet {
	position: absolute;
	width: 5vh;
	height: 5vh;
	top: 17vh;
	border-radius: 50%;
	border: 3px solid;
	border-color: white;
}
</style>