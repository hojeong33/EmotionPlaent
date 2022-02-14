<template>
	<div id="following_list">
		<div id="modal">
			<div id="following_list_header">
				<div id="title_container">
					<p id="title">팔로잉</p>
				</div>
				<div id="cancel">
					<i @click="goBack" class="fa-solid fa-x" style="font-size: 1.3rem; cursor: pointer;"></i>
				</div>
			</div>
			<hr>
			<div id="no_result" v-if="myFollowingInfo.length === 0">
				<img id="nothing" src="@/assets/images/etc/alien.png" alt="no result">
				<p id="no_following">팔로잉 목록이 없습니다...</p>
			</div>
			<div id="my_following_list" v-else>
				<div id="userInfo" v-for="(followingInfo, index) in myFollowingInfo" :key="index">
					{{followingInfo}}
					<!-- <img id="profile_img" src="@/assets/images/icons/profileimg.png" alt="">
					<p id="username">아이디</p> -->
				</div>
				<div id="follow_cancel">
					<button id="follow_cancel">취소</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	data () {
		return {
			myFollowingInfo: []
		}
	},
	methods: {
		goBack: function () {
			// console.log('여기옴')
			// console.log(this.$store.state.userInfo)
			this.$store.commit('userpagefollowingListActivate')
		}
	},
	created () {
		this.myFollowingInfo = this.$store.state.userFollowInfo.userFollowing
	}
}
</script>

<style scoped>
#following_list {
	display: flex;
	justify-content: center;
	align-items: center;
	width: 100%;
	height: 100%;
	background-color: rgb(0, 0, 0, 0.5);
	position: fixed;
	top: 0;
	left: 0;
	z-index: 999;
}
#modal {
	background-color: white;
	border-radius: 20px;
	width: 25vw;
	min-width: 350px;
	height: 45vh;
	min-height: 450px;
}
#following_list_header {
	display: flex;
	flex-direction: row;
	justify-content: center;
	padding-top:1rem;
	padding-bottom: 1rem;
}
#title {
	margin-top: auto;
	margin-bottom: auto;
	font-size: 1.2rem;
	font-weight: bold;
}
#title_container{
	width: 58%;
	display: flex;
	justify-content: right;
}
#cancel {
	width: 42%;
	display: flex;
	justify-content: right;
	margin-right: 1rem;
	margin-top: 0.3rem;
}
#no_result {
	display:flex;
	flex-direction: column;
	align-items: center;
	margin-top: 25%
}
#nothing {
	width: 5rem;
	height: 5rem;
	margin-bottom: 0.5rem;
}
#no_following {
	font-weight: bold;
}
#my_following_list{
	display: flex;
	flex-direction: row;
	justify-content: left;
	margin: 1rem;
	overflow-y: scroll;
}
#my_following_list{
	display: flex;
	flex-direction: row;
	justify-content: left;
	margin: 1rem;
	overflow-y: scroll;
}
#userInfo{
	display: flex;
	flex-direction: row;
}
#profile_img {
	width: 2rem;
	height: 2rem;
	border-radius: 50%;
	margin-right: 0.5rem;
	margin-top: auto;
	margin-bottom: auto;
}
#username {
	font-weight: bold;
	margin-right: 0.5rem;
	margin-top: auto;
	margin-bottom: auto;
}
#follow_cancel{
	margin-left: auto;
	margin-top: auto;
	margin-bottom: auto;
	background-color: #5E39B3;
	color: white;
	border-radius: 10px;
	font-size: 0.8rem;
	padding:0.5px;
}
hr {
	margin: 0;
}

</style>