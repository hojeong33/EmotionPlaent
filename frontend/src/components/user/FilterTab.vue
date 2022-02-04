<template>
  <div id="filter-container">
		<img id="planet_img" :src="require('@/assets/images/emotions/' + planet)">
		<select id="selectbox" v-model="filterValue">
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
        { id: 1, name: '행복행성', img: "1.png", color: '#ED5A8E' },
        { id: 2, name: '우울행성', img: "2.png", color: '#6BD9E8' },
        { id: 3, name: '중립행성', img: "3.png", color: '#C5D3DC' },
        { id: 4, name: '공포행성', img: "4.png", color: '#FEA95C' },
        { id: 5, name: '깜짝행성', img: "5.png", color: '#FB5D38' },
        { id: 6, name: '분노행성', img: "6.png", color: '#2A61F0' },
      ],
			filterValue: '0',
		}
	},
	props: {
		userMood: Number,
	},
	methods: {
		filtering: function () {
			this.$emit('filtering', this.filterValue)
		}
	},
	computed: {
    planet() {
			const idx = Number(this.filterValue)
			if (idx){
				return this.planetStyles[idx].img
			}
			return "3.png"
		}
  },
}
</script>

<style scoped>
	#filter-container {
		display: flex;
		justify-content: flex-end;
		align-items: center;
		align-self: flex-end;
		margin: 2rem;
	}

	#planet_img {
		width: 5%;
		height: 5%;
	}

	select { 
		/* width: 20%;
		height: 10%; */
		padding: 0.2rem 2rem 0;
		border: none;
		border-radius:0; /* 아이폰 사파리 보더 없애기 */ 
		-webkit-appearance:none; /* 화살표 없애기 for chrome*/ 
		-moz-appearance:none; /* 화살표 없애기 for firefox*/ 
		appearance:none; /* 화살표 없애기 공통*/ 
		background: url('../../assets/images/etc/rocket.png') no-repeat 97% 50%/2.2vh auto;
		font-size: 1.5rem;
		font-weight: bold;
		text-align: center;
		cursor: pointer;
	}

	select:focus {
		outline: none;
	}

	select::-ms-expand {
		display: none;
	}
</style>