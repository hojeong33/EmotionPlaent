<template>
	<div id="profile_img_change_modal">
		<div id="modal">
			<h3>프로필 사진 바꾸기</h3>
			<hr>
			<p @click="imgUpload" id="upload">사진 업로드</p>
			<input type="file" v-if="uploading">
			<p style="color: red;">현재 사진 삭제</p>
			<hr>
			<p v-if="uploading">확인</p>
			<p @click="cancel">취소</p>
		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {
			uploading: false,
		}
	},
	methods: {
		cancel: function () {
			this.$store.commit('profileImgChangeModalActivate')
		},
		imgUpload() {
			this.uploading = true
		}
	}
}
</script>

<style scoped>
#upload {
	cursor: pointer;
}

#profile_img_change_modal {
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
	width: 20vw;
	height: 50vh;
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