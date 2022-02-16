<template>
	<div id="user_feed_setting_modal2">
		<div id="modal">
			<p @click="feedUpdateActivate">수정</p>
			<hr>
			<p style="color: red;" @click="feedDelete">삭제</p>
			<hr>
			<p @click="cancel">취소</p>
			<!-- 유저가 나인지 다른 유저인지에 따라서 신고, 차단이 나오거나 수정, 삭제가 나와야 한다-->
		</div>
	</div>
</template>

<script>
import axios from 'axios'

const session = window.sessionStorage; 

export default {
	data: function () {
		return {
			feedNum: 165,

		}
	},
	methods: {
		cancel: function () {
			this.$store.commit('userFeedSettingModalActivate2')
		},
		feedUpdateActivate: function () {
			this.$store.commit('userFeedSettingModalActivate2')
			this.$store.commit('feedUpdateActivate')
		},
		feedDelete: function () {
			let headers = {
				'at-jwt-access-token': session.getItem('at-jwt-access-token'),
				'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
			};

			axios({
				method: 'delete',
				url: '/api/feeds/' + this.feedNum,
				headers: headers,  // 넣는거 까먹지 마세요
				}).then((res) => {

				this.$store.dispatch('accessTokenRefresh', res) // store아닌곳에서
				this.dispatch('accessTokenRefresh', res) // store에서

				}).catch((error) => {
					console.log(error);
				}).then(() => {
					console.log('getQSSList End!!');
				})
			this.$store.commit('userFeedSettingModalActivate2')
		}
	}
}
</script>

<style scoped>
#user_feed_setting_modal2 {
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
	display: flex;
	flex-direction: column;
	background-color: white;
	border-radius: 20px;
	width: 12vw;
	height: 20vh;
	text-align: center;
}
hr {
	margin: 0rem;
}
p {
	margin: auto;
	font-weight: bold;
	cursor: pointer;
}
</style>