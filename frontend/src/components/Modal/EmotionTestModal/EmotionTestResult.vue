<template>
	<div id="emotiontest_pick_more">
    <div id="modal">
			<h4>당신은 
				{{planetName}}
				<img id="planet_img" :src="require('@/assets/images/emotions/' + planetImg)" alt="">
				입니다.
			</h4>
			<hr>
			<p @click="goMain">확인</p>
    </div>
	</div>
</template>

<script>
export default {
	data() {
		return {
			planetStyles: [
        { id: 1, name: '행복행성', img: "happy.png", color: '#6BD9E8' },
        { id: 2, name: '우울행성', img: "depressed.png", color: '#2A61F0' },
        { id: 3, name: '중립행성', img: "neutral.png", color: '#ABBECA' },
        { id: 4, name: '공포행성', img: "fear.png", color: '#ED5A8E' },
        { id: 5, name: '깜짝행성', img: "surprised.png", color: '#FEA95C' },
        { id: 6, name: '분노행성', img: "rage.png", color: '#FB5D38' },
      ],
			planetName: null,
			planetImg: null,
		}
	},
	methods: {
		goMain: function () {
			// console.log('여기옴')
			console.log(this.$store.state.userInfo)
			this.$store.commit('emotionTestResultModalActivate')
			this.$router.push({name: 'Main'})

		}
	},
	created: function () {
		let mood = this.$store.state.userInfo.mood
		let planetstyle = this.planetStyles.find(el => el.id === mood) || {}
		this.planetImg = planetstyle.img
		this.planetName = planetstyle.name
	}
}
</script>

<style scoped>
#emotiontest_pick_more {
	display: flex;
	justify-content: center;
	align-items: center;
	width: 100vw;
	height: 100vh;
	background-color: rgb(0, 0, 0, 0.5);
	position: fixed;
	top: 0;
	left: 0;
	z-index: 9999;
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
}
#planet_img {
	width: 2rem;
	height: 2rem;
}
</style>