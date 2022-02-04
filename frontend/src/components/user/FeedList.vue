<template>
  <article id="feed-container">
		<filter-tab :user-mood="userMood" 
		@filtering="filterCheck" />
		<div id="feeds" v-if="filteredFeeds"> 
			<feed-item v-for="(feed, idx) in filteredFeeds" :key="idx" :feed="feed" />
		</div>
		<div v-else id="no-result">
			<img id="nothing" src="@/assets/images/etc/alien.png" alt="">
			<p>게시글이 없어요...</p>
		</div>
	</article>
</template>

<script>
import FilterTab from '@/components/user/FilterTab.vue'
import FeedItem from '@/components/user/FeedItem'

export default {
	name: 'UserFeed',
	data: function () {
		return {
			userFeed: [
				{
					username: "조은누리",
					date:"2022-01-01",
					userImage: "https://newspenguin.com/news/photo/202006/1837_5156_215.jpg",
					postImage: "http://cdn.catholicnews.co.kr/news/photo/202001/22135_43951_5525.jpg",
					tag: ["우울행성","보았어요","밤하늘"],
					likes: 36,
					hasBeenLiked: false,
					caption: "내 기분은 ☀️",
					comment_cnt:"2",
					comments: ["반가워요","안녕하세요"],
					planet: 1
				},
				{
					username: "조은누리",
					date:"2022-01-01",
					userImage: "https://s3.ap-northeast-2.amazonaws.com/elasticbeanstalk-ap-northeast-2-176213403491/media/magazine_img/magazine_280/5-3-%EC%8D%B8%EB%84%A4%EC%9D%BC.jpg",
					postImage: "https://img.siksinhot.com/article/1595482500150524.jpg",
					tag: ["우울행성","보았어요","밤하늘"],
					likes: 20,
					hasBeenLiked: false,
					caption: "맥주한잔생각나는밤이군",
					comment_cnt:"2",
					comments: ["반가워요","안녕하세요"],
					planet: 1
				},
				{
					username: "조은누리",
					date:"2022-01-01",
					userImage:"https://i0.wp.com/dailypetcare.net/wp-content/uploads/2020/11/Screen-Shot-2020-11-24-at-9.10.35-PM-edited-e1606302091776.png?fit=1236%2C694&ssl=1",
					postImage:"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDTsmB9x2oiL4FX8nArPQ8YkocNQRGL2SeHg&usqp=CAU",
					tag: ["우울행성","보았어요","밤하늘"],
					likes: 49,
					hasBeenLiked: false,
					caption: "멍멍🐶",
					comment_cnt:"2",
					comments: ["반가워요","안녕하세요"],
					planet: 2
				},
				{
					username: "조은누리",
					date:"2022-01-01",
					userImage:"https://i0.wp.com/dailypetcare.net/wp-content/uploads/2020/11/Screen-Shot-2020-11-24-at-9.10.35-PM-edited-e1606302091776.png?fit=1236%2C694&ssl=1",
					postImage:"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDTsmB9x2oiL4FX8nArPQ8YkocNQRGL2SeHg&usqp=CAU",
					tag: ["우울행성","보았어요","밤하늘"],
					likes: 49,
					hasBeenLiked: false,
					caption: "멍멍🐶",
					comment_cnt:"2",
					comments: ["반가워요","안녕하세요"],
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
			filter: '0',
		}
	},
	props: {
		userMood: Number,
	},
	components: {
		FilterTab,
		FeedItem
	},
	methods: {
		filterCheck: function (filterValue) {
			console.log(filterValue)
			this.filter = String(filterValue)
		}
	},
	computed: {
		filteredFeeds(){
			if (this.filter == 0){
				return this.userFeed
			}
			else {
				const temp = []
				this.userFeed.forEach(feed => {
					if (feed.planet == this.filter){
						temp.push(feed)
					}
				});
				return temp
			}
		}
	},
}
</script>

<style scoped>
	p {
		font-weight: bold;
	}

	#feed-container {
		display: flex;
		flex-direction: column;
		align-items: center;
		width: 100%;
		padding: 1rem;
	}

	#no-result {
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

	#filter {
		display: flex;
		justify-content: right;
	}

	#feeds {
		display: grid;
		grid-template-columns: 1fr 1fr 1fr;
		place-items: center;
		gap: 2rem;
	}
</style>