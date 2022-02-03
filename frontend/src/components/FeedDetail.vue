<template>
	<div id="feed_detail">
		<img id="feedImg" :src="`${postImage}`" alt="">
		<div id="feed_text">
			<div id="text_head">
				<img id="profile_image" :src="`${userImage}`" alt="">
				{{ username }}
				<!-- 만약 다른 유저의 피드 디테일이라면 팔로우 버튼이 나타나게 -->
			</div>
			<hr>
			<div>
				<p>{{caption}}</p>
				<p>{{ tag }}</p>
				<p v-for="(comment, idx) in comments" :key="idx">{{comment}}</p>
			</div>
			<hr>
			<div>
				좋아요, 조회수, 업로드 시간 등
			</div>
			<hr>
			<div id="comment_create">
				<input type="text">
				<button type="submit">댓글달기</button>
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
			caption: "내 기분은 ☀️",
			comment_cnt:"2",
			comments:["반가워요","안녕하세요"],
			planet:"행복행성",
		}
	},
	// methods: 
}
</script>

<style scoped>
#feed_detail {
	width: 100vh;
	height: 80vh;
	display: flex;
	justify-content: center;
	align-items: center;
	margin: auto;
	border: 0.5px;
	border-style: solid;
	
}
#feedImg{
	width: 80vh;
	height: 80vh;
	border: 0.5px;
	border-style: solid;
}
#feed_text {
	display:flex;
	flex-direction: column;
}
#profile_image {
	width: 3vh;
	height: 3vh;
	border-radius: 20px;
}
#comment_create{
	display: flex;
	flex-direction: row;
}
input{
	width: 15vh;
	border-style: none;
}
button {
	border-style: none;
	color: blue;
	background-color: white;
}
</style>