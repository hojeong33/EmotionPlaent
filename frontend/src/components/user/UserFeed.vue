<template>
  <div>
		<div v-if="isExist">
			<p>전체</p>
			<br>
			<div v-for="(feed, idx) in feedList" :key="idx">
				<img id="feed_img" :src="feed.feedImg" alt="">
				<img id="feed_planet" :src="feed.feedPlanet" alt=""> 
			</div>
		</div>
		<div v-else id="body">	
			<img id="nothing" src="@/assets/images/etc/alien.png" alt="">
			<p>게시글이 없어요...</p>
		</div>
	</div>
</template>

<script>
export default {
	name: 'UserFeed',
	data: function () {
		return {
			isExist: true,
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
					planet:"행복행성"
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
					planet:"우울행성"
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
					planet:"우울행성"
				}
			],
			planetStyles: [
        { id: 1, name: '공포행성', img: "fear.png", color: '#ED5A8E' },
        { id: 2, name: '행복행성', img: "@/assets/images/emotions/happy.png", color: '#6BD9E8' },
        { id: 3, name: '중립행성', img: "@/assets/images/emotions/neutral.png", color: '#C5D3DC' },
        { id: 4, name: '놀라움행성', img: "@/assets/images/emotions/surprised.png", color: '#FEA95C' },
        { id: 5, name: '분노행성', img: "rage.png", color: '#FB5D38' },
        { id: 6, name: '우울행성', img: "@/assets/images/emotions/depressed.png", color: '#2A61F0' },
      ],
			feedList: [],
		}
	},
	created: function () {
		for (let feed of this.user_feed) {
			for (let emotion of this.planetStyles) {
				if (feed.planet === emotion.name)
				console.log(emotion.img)
				this.feedList.push({'feedImg': feed.postImage, 'feedPlanet': feed.planet}) 
			}
		}
		console.log(this.feedList)
	}
	// methods: function () {
	// 	// axios로 서버에서 데이터 받아오기
	// 	if 
	// }
}
</script>

<style scoped>
#body {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}
#nothing {
	width: 10vh;
	height: 10vh;
	margin-bottom: 2vh;
}
p {
	font-size: 1.5rem;
	color: gray;
}
#feed_img {
	position: relative;
	width: 20vh;
	height: 20vh;
}
#feed_planet {
	position: absolute;
	width: 10vh;
	height: 10vh;
}
</style>