<template>
	<div id="logout_modal">
		<div id="modal">
			<p>로그아웃 모달</p>
			<hr>
			<p style="color: red;">삭제</p>
			<hr>
			<p @click="cancel">취소</p>
		</div>
	</div>
</template>

<script>
export default {
	methods: {
		cancel: function () {
			this.$store.commit('logoutModalActivate')
		}
	}
}
</script>

<style scoped>
#logout_modal {
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
	color: black;
}
p {
	margin: auto;
	font-weight: bold;
	cursor: pointer;
}
</style>