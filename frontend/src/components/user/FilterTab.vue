<template>
  <div id="container">
		<img id="planet_img" :src="require('@/assets/images/emotions/' + tmp.img)">
		<select id="selectbox" @change="filtering">
			<option value="0">전체</option>
			<option value="1">행복행성</option>
			<option value="2">우울행성</option>
			<option value="3">중립행성</option>
			<option value="4">공포행성</option>
			<option value="5">깜짝행성</option>
			<option value="6">분노행성</option>
		</select>
  </div>
</template>

<script>
export default {
	name:'FilterTab',
	data: function () {
		return {
			planetStyles: [
				{ id: 0, name: 'default'},
        { id: 1, name: '행복행성', img: "happy.png", color: '#ED5A8E' },
        { id: 2, name: '우울행성', img: "depressed.png", color: '#6BD9E8' },
        { id: 3, name: '중립행성', img: "neutral.png", color: '#C5D3DC' },
        { id: 4, name: '공포행성', img: "fear.png", color: '#FEA95C' },
        { id: 5, name: '깜짝행성', img: "suprised.png", color: '#FB5D38' },
        { id: 6, name: '분노행성', img: "rage.png", color: '#2A61F0' },
      ],
			filterValue: null,
			planetImg: null,
			mood: null,
		}
	},
	props: {
		userMood: Number,
	},
	methods: {
		filtering: function (onselect) {
			this.filterValue = onselect.target.value
			this.$emit('filtering', this.filterValue)
		}
	},
	created() {
		this.mood = this.userMood
	},
	computed: {
    tmp: function () {
      const mood = this.mood
      const style = this.planetStyles.find(el => el.id === mood) || {}
      return style
    }
  },
}
</script>

<style scoped>
#container {
	display: flex;
	justify-content: center;
}

#planet_img {
  width: 4vh;
  height: 4vh;
}

select { 
	width: 11.5vh;
	height: 3vh;
	padding-top: 4px;
	padding-right: 21px;
	/* padding-left: 5px; */
	border-radius:0; /* 아이폰 사파리 보더 없애기 */ 
	-webkit-appearance:none; /* 화살표 없애기 for chrome*/ 
	-moz-appearance:none; /* 화살표 없애기 for firefox*/ 
	appearance:none; /* 화살표 없애기 공통*/ 
	background: url('../../assets/images/etc/rocket.png') no-repeat 97% 50%/2.2vh auto;
	border-style: none;
	font-weight: bold;
	text-align: center;
}
select::-ms-expand {
	display: none;
}
</style>