<template>
	<div id="feed_detail" v-if="feed">
		<div id="img_box">
      <div v-for="(img, idx) in feed.imgs" :key="idx"><img  id="feedImg" :src="img.imgLink" alt=""></div>
			<p class="overlay_content" >{{feed.authorDetail.nickname}} <img id="planet_img" :src="require('@/assets/images/emotions/happy.png')" style="width:1.2rem;height:1.2rem; margin-bottom:3px">에 있어요</p>
		</div>
		<div id="feed_text">
			<div id="text_head">
				<img id="profile_image" :src="feed.authorDetail.profileImg" alt="">
				<div id="profile_info">
					<p id="username">{{feed.authorDetail.nickname }}</p>
					<p id="upload_date">{{feed.date}}</p>
				</div>
				<div id="setting">
					<i @click="onModalFeed" class="fas fa-ellipsis-v" style="color:black"></i>
					<user-feed-setting v-if="isUserFeedSettingOpened" @cancel="isUserFeedSettingOpened=false"></user-feed-setting>
				</div>
				<!-- 만약 다른 유저의 피드 디테일이라면 팔로우 버튼이 나타나게 -->
			</div>
			<hr>
			<div id="text_body">
				<div>
					<p id="caption">{{feed.descr}}</p>
				</div>
				<br>
				<div id="tags">
					<p id="tag" v-for="(t, idx) in feed.tags" :key="idx">#{{ t['name'] }}</p>
				</div>
				<br>
				<div id="comments">
					<div id="comment" v-for="(comment, idx) in commentsData" :key="idx">
						<img id="profile_image" :src="comment.userRequestDto.profileImg" alt="">
						<p id="username">{{comment.userRequestDto.nickname}}</p>
						<p id="user_comment">{{comment.descr}}</p>
						<div id="comment_setting">
              <i @click="onModalComment" class="fas fa-ellipsis-v" style="color:black"></i>
							<!-- 댓글 하나하나에 유저데이터가 들어가서 해당 유저의 댓글이 지워져야 함-->							
							<!-- <comment-setting v-if="isCommentSettingOpened" @cancel="isCommentSettingOpened=false"></comment-setting> -->
						</div>
					</div>
				</div>
			</div>
			<hr>
			<div id="likes">
        <div id="heart">
          <i class="far fa-heart fa-lg" :class="{'fas': this.feed.like}"  @click="like"></i>
        </div>
        <p id="feed_likes" v-for="(like, idx) in feed.likes" :key="idx"></p>
        <p class="likes">{{feed.likes.length}} likes</p>
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
import axios from 'axios';
const session = window.sessionStorage;

export default {
	data: function () {
		return {
      isMineFeed:false,
      isMineComment:false,
			feed:null,
      comments:[],
      commentsData:[],
			commentContent:null,
			isCommentSettingOpened:false,
			isUserFeedSettingOpened: false,
		}
	},
	props:{
		feedNo:Number,
	},
	methods: {
    getComment:function(commentNo){
      let headers = {
        'at-jwt-access-token': session.getItem('at-jwt-access-token'),
        'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
        };
        axios({
          method: 'get',
          url:`http://13.125.47.126:8080/comment/${commentNo}`,
          headers: headers,  // 넣는거 까먹지 마세요
          }).then((res) => {
            this.$store.dispatch('accessTokenRefresh', res) // store아닌곳에서
            this.commentsData.push(res.data)
            this.isMineComment=res.data.owner
            // this.getComments()
          }).catch((error) => {
            console.log(error);
          }).then(() => {
            console.log('댓글 하나 가져오기');
          });
        },
    getComments:function(){
      let headers = {
        'at-jwt-access-token': session.getItem('at-jwt-access-token'),
        'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
      };
      axios({
          method: 'get',
          url:`http://13.125.47.126:8080/comments/returnNo/${this.feedNo}`,
          headers: headers,  // 넣는거 까먹지 마세요
          }).then((res) => {
          this.$store.dispatch('accessTokenRefresh', res) // store아닌곳에서
          this.comments=res.data.reverse()
          this.commentsData=[]
          for (let i=0; i<this.comments.length; i++){
            const commentNo=this.comments[i]
            this.getComment(commentNo)
          }
          }).catch((error) => {
            console.log(error);
          }).then(() => {
            console.log('댓글 목록 가져오기');
          });
        },
    createComment:function(){
      const userdata = JSON.parse(session.getItem('userInfo')) 
      const commentItem={
          descr:this.commentContent,
          author:userdata.no,
          feedNo:this.feedNo
        }
      if(commentItem.descr){
        this.commentContent=null
        let headers = {
          'at-jwt-access-token': session.getItem('at-jwt-access-token'),
          'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
        };
        axios({
            method: 'post',
            url:'http://13.125.47.126:8080/comments',
            data: commentItem, // post 나 put에 데이터 넣어 줄때
            headers: headers,  // 넣는거 까먹지 마세요
          })
          .then((res) => {
             this.$store.dispatch('accessTokenRefresh', res) // store아닌곳에서
            //  console.log(res.data)
            //  console.log('댓글 다시 가져옴!!!!!!!!!!!!!!!!!!!!!!')
             this.getComments()
             let body = {
               receiver: this.feed.author,
               feedno: this.feedNo,
               commentno: res.data, // 이부분 백 수정하고 테스트해야함
             }
             this.$store.dispatch('comment',body)
          })
          .catch((error) => {
            console.log(error);
          })
          .then(() => {
            console.log('댓글 작성 완료');
          });

        
      }
      else{
        this.$store.commit('commentNeedContentModalActivate')
      }
    },
		getFeed:function(){
		let headers = {
			'at-jwt-access-token': session.getItem('at-jwt-access-token'),
			'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
			};
			axios({
				method: 'get',
				url:`http://13.125.47.126:8080/feed/${this.feedNo}`,
				headers: headers,  // 넣는거 까먹지 마세요
			}).then((res) => {
			this.$store.dispatch('accessTokenRefresh', res) // store아닌곳에서
			this.feed=res.data
			console.log(this.feed)
      this.getComments()
			this.isMineFeed=res.data.owner
			}).catch((error) => {
				console.log(error);
			}).then(() => {
				console.log('피드 하나 가져오기');
			});
		},
    like:function(){
      this.feed.like ? this.cancelLike(): this.doLike();
      this.feed.like= !this.feed.like;
    },
    doLike:function(){
		console.log(this.feedNo)
      let el = {
        receiver : this.feed.author,
        feedno : this.feedNo,
      }
      this.$store.dispatch('addfeedlike',el)
    },
    cancelLike:function(){
      this.$store.dispatch('deletefeedlike',this.feedNo)
    },
    onModalFeed:function(){
      if(this.isMineFeed){
        this.onCommentSetting()
      }
      else{
        this.onUserFeedSetting2
      }
    },
    onModalComment:function(){
      if(this.isMineComment){
        this.onCommentSetting()
      }
      else{
        this.onUserFeedSetting2
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
		onUserFeedSetting2:function(){
			this.$store.commit('userFeedSettingModalActivate2')
			// if(this.isUserFeedSettingOpened){
			// 	this.isUserFeedSettingOpened=false
			// }else{
			// 	this.isUserFeedSettingOpened=true
			// }
		}
	},
	created(){
		this.getFeed()
		
	}
}
</script>

<style scoped>
.fas{
    color: crimson;
  }
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