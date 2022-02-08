<template>
	<div id="comment_setting_modal">
		<div id="modal">
			<p>수정</p>
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
			this.$emit('cancel')
		}
	}
}
</script>

<style scoped>
#comment_setting_modal {
	display: flex;
	justify-content: center;
	align-items: center;
	position: absolute;
	width: 100vw;
	height: 100vh;
	top: 0;
	left: 0;
	background-color: rgb(0, 0, 0, 0.5);
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