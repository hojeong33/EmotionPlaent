<template>
	<div id="feed_detail">
		<img id="feedImg" :src="`${postImage}`" alt="">
		<div id="feed_text">
			<div id="text_head">
				<img id="profile_image" :src="`${userImage}`" alt="">
				<p id="username">{{ username }}</p>
				<!-- 만약 다른 유저의 피드 디테일이라면 팔로우 버튼이 나타나게 -->
			</div>
			<hr>
			<div id="text_body">
				<div>
					<p id="caption">{{caption}}</p>
				</div>
				<br>
				<div id="tags">
					<p id="tag" v-for="(t, idx) in tag" :key="idx">#{{ t }}</p>
				</div>
				<br>
				<div id="comments">
					<div id="comment" v-for="(comment, idx) in comments" :key="idx">
						<img id="profile_image" :src="`${comment.userImage}`" alt="">
						<p id="username">{{comment.username}}</p>
						<p id="user_comment">{{comment.comment}}</p>
					</div>
				</div>
			</div>
			<hr>
			<div id="likes">
				<div v-if="liked">
					<i id="heart" class="fas fa-heart fa-lg" style="color: crimson;" @click="like"></i>
				</div>
				<div v-else>
					<i id="heart" class="far fa-heart fa-lg" @click="like"></i>
				</div>
				&nbsp;
				<p id="like_count">{{ likes }} likes</p>
			</div>
			<hr>
			<div id="comment_create">
				<input type="text" placeholder="댓글달기...">
				<!-- <button @click="createComment">작성</button> -->
			</div>
		</div>
	</div>
</template>

<script>

export default {
	data: function () {
		return {
			username: "조은누리",
			date:"2022-01-01",
			userImage: "https://newspenguin.com/news/photo/202006/1837_5156_215.jpg",
			postImage:
				"http://cdn.catholicnews.co.kr/news/photo/202001/22135_43951_5525.jpg",
			tag:["우울행성","보았어요","밤하늘"],
			likes: 36,
			hasBeenLiked: false,
			caption: "안녕하세요~~~~~ 오늘 내 기분은 ☀️!!! 랄라라라라라라라라라라라라라",
			comment_cnt:"2",
			comments:[
				{
					userImage: "https://s3.ap-northeast-2.amazonaws.com/elasticbeanstalk-ap-northeast-2-176213403491/media/magazine_img/magazine_280/5-3-%EC%8D%B8%EB%84%A4%EC%9D%BC.jpg",
					username: '최강상후',
					comment: "반가워요",
				},
				{
					userImage: "https://i0.wp.com/dailypetcare.net/wp-content/uploads/2020/11/Screen-Shot-2020-11-24-at-9.10.35-PM-edited-e1606302091776.png?fit=1236%2C694&ssl=1",
					username: 'soonil',
					comment: "안녕하세요"
				}
				],
			planet:"행복행성",
			// commentContent: null
			liked: false,
		}
	},
	methods: {
		// createComment: function (input) {
		// 	let commentContent = 
		// 	}
		// }
		like: function () {
			this.liked = !this.liked
			console.log(this.liked)
		}
	}
}
</script>

<style scoped>
#feed_detail {
	width: 110vh;
	height: 80vh;
	display: flex;
	justify-content: center;
	align-items: center;
	margin-left: auto;
	margin-right: auto;
	margin-top:5vh;
	border: 2px purple;
	border-style: solid;
	border-radius: 10px;
}
#feedImg{
	width: 79.8vh;
	height: 79.8vh;
	border: 0.5px;
	border-style: solid;
	border-radius: 10px;
}
#feed_text {
	height: 80vh;
	display:flex;
	flex-direction: column;
}
#text_head {
	display: flex;
	flex-direction: row;
	justify-content: left;
	align-items: center;
	height: 5vh;
	margin-left: 0.4rem;
}
#profile_image {
	width: 3vh;
	height: 3vh;
	border-radius: 20px;
	margin-right:0.4rem;
}
#username {
	font-weight: bold;
	margin-top: auto;
	margin-bottom: auto;
	margin-right: 0.4rem;
}
#text_body {
	height: 65vh;
	overflow: auto;
	margin-left: 0.4rem;
	margin-right: 0.4rem;
	margin-top: 0.5rem;
}
#caption {
	font-size: 1rem;
}
#tags {
	display: flex;
	flex-direction: row;
	flex-wrap: wrap;
}
#tag {
	color: rgb(46, 46, 255);
	font-size: 0.8rem;
}
#comment {
	display: flex;
	flex-direction: row;
	justify-content: left;
	align-items: center;
	font-size: 1rem;
	margin-top: 0.5rem;
	margin-bottom: 0.5rem;
}
#user_comment {
	margin-top:auto;
	margin-bottom: auto;
}
#likes{
	display:flex;
	height: 5vh;
	margin-left: 0.4rem;
	margin-right: 0.4rem;
	margin-top: 0.5rem;
	justify-content: left;
	align-items: center;
}
#comment_create{
	display: flex;
	flex-direction: row;
	height: 8vh;
	align-items: center;
	margin-left: 0.4rem;
	margin-right: 0.4rem;
	/* border: 1px;
	border-style: solid;
	border-radius: 5px; */
}
input{
	width: 25.5vh;
	height: 3vh;
	border-style: none;
	outline: none;
}
button {
	border-style: none;
	color: blue;
	background-color: white;
	font-size: 0.7rem;
	margin-left: 0.5vh;

}
hr{
	margin: 0rem;
}
#like_count {
	font-weight: bold;
	font-size: 0.9rem;
	margin-top: auto;
	margin-bottom:auto;
}

</style>