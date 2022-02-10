<template>
	<div id="login_confirm_modal">
    <div id="modal">
			<h4>여행을 시작하겠습니다</h4>
			<hr>
			<p @click="goEmotionTest">확인</p>
    </div>
	</div>
</template>

<script>
export default {
	methods: {
		// goToTest: function () {
		// 	this.$router.push({name: 'EmotionTest'})
		// },
		// userUpdate: function () {
		// 	// const user = this.$store.commit('loginConfirmModalActivate')
		// 	this.$store.commit('loginConfirmModalActivate')
		// 	// console.log(user)
		// 	return this.goToTest()
		// },
		// goEmotionTest: function () {
		// 	console.log('여기옴')
		// 	// console.log(this.$store.state.userInfo)
		// 	this.userUpdate()
		goEmotionTest: function () {
			this.$store.commit('loginConfirmModalActivate')
			console.log(this.$route.name)
			this.$router.push ({name: 'EmotionTest'})
		}
	}
}
</script>

<style scoped>
#login_confirm_modal {
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
	justify-content: center;
	background-color: white;
	border-radius: 20px;
	width: 20vw;
	height: 15vh;
}
p {
	margin: auto;
	font-weight: bold;
	cursor: pointer;
}
hr {
	margin: 0;
}
h4 {
	margin: auto;
	text-align: center;
	font-weight: bold;
}
</style>