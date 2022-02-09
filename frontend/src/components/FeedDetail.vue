<template>
	<div id="feed_detail">
		<div id="img_box">
			<img id="feedImg" :src="`${postImage}`" alt="">
			<p class="overlay_content" >조은누리님은 행복행성 <img id="planet_img" :src="require('@/assets/images/emotions/happy.png')" style="width:1.2rem;height:1.2rem; margin-bottom:3px">에 있어요</p>
		</div>
		<div id="feed_text">
			<div id="text_head">
				<img id="profile_image" :src="`${userImage}`" alt="">
				<div id="profile_info">
					<p id="username">{{ username }}</p>
					<p id="upload_date">date</p>
				</div>
				<div id="setting">
					<i @click="onUserFeedSetting" class="fas fa-ellipsis-v"></i>
					<user-feed-setting v-if="isUserFeedSettingOpened" @cancel="isUserFeedSettingOpened=false"></user-feed-setting>
				</div>
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
						<div id="comment_setting">
							<i @click="onCommentSetting" class="fas fa-ellipsis-v"></i>
							<!-- 댓글 하나하나에 유저데이터가 들어가서 해당 유저의 댓글이 지워져야 함-->							
							<!-- <comment-setting v-if="isCommentSettingOpened" @cancel="isCommentSettingOpened=false"></comment-setting> -->
						</div>
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
			<div id="comment_write">
        <input id="comment-input" @keyup.enter="createComment" v-model.trim="commentContent" placeholder="댓글을 입력해 주세요."> 
        <img id="submit" @click="createComment" src="@/assets/images/icons/write.png" alt="" style="width:1.4rem;height:1.4rem;">
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
			commentContent:null,
			isCommentSettingOpened:false,
			isUserFeedSettingOpened: false,
		}
	},
	methods: {
		like: function () {
			this.liked = !this.liked
			console.log(this.liked)
		},
		createComment:function(){
      const commentItem={
        comment:this.commentContent,
        username:'default',
        userImage:'https://cdn.indiepost.co.kr/uploads/images/2018/12/11/VDbIX3-600x338.png'
      }
      if(commentItem.comment){
        this.comments.push(commentItem)
        this.commentContent=null

      }
      else{
        // alert('내용을 채워주세요')
		this.$store.commit('commentNeedContentModalActivate')
      }
		},
		onCommentSetting:function(){
			this.$store.commit('commentSettingModalActivate')
			// if(this.isCommentSettingOpened){
			// 	this.isCommentSettingOpened=false
			// }else{
			// 	this.isCommentSettingOpened=true
			// }
		},
		onUserFeedSetting:function(){
			this.$store.commit('userFeedSettingModalActivate')
			// if(this.isUserFeedSettingOpened){
			// 	this.isUserFeedSettingOpened=false
			// }else{
			// 	this.isUserFeedSettingOpened=true
			// }
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
	border: 3px solid rgb(94, 57, 179);
	border-style: solid;
	border-radius: 10px;
}
#feedImg{
	position: relative;
	width: 79.55vh;
	height: 79.55vh;
	border-right: 1.5px solid;
	border-left: none;
	border-top: none;
	border-bottom: none;
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
	height: 8vh;
	margin-left: 1rem;
}
#profile_image {
	width: 5vh;
	height: 5vh;
	border-radius: 5vh;
	margin-right: 0.5rem;
}
#profile_info {
	padding-top:auto;
	padding-bottom: auto;
}
#username {
	font-weight: bold;
	margin: 0rem;
	font-size: 1.2rem;
}
#upload_date {
	text-align: left;
	font-size: 1rem;
	margin: 0rem;
}
#setting {
	margin-left:auto;
	margin-right: 1rem;
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
	text-align: left;
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
	margin-left: 0.5rem;
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
.overlay_content {
	position: absolute;
	background-color: white;
	border-radius: 10px;
	top: 88.5vh;
	margin-left: 0.3rem;
}
#comment_write{
  border:0.2rem solid gainsboro;
  border-radius: 10px;
	margin: 0.5rem;
}
#comment-input{
  width: 80%;
  margin-bottom: 1rem;
  margin-left:3px;
  border-style:none;
  margin-bottom: 0;
  outline: none;
}
#comment_setting {
	margin-left: auto;
	margin-right: 1rem;
}
</style>