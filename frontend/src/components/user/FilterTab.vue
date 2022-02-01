<template>
  <div>
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
			filterValue: null,
		}
	},
	props: {
		planetStyles: Array,
	},
	methods: {
		filtering: function (onselect) {
			this.filterValue = onselect.target.value
			this.$emit('filtering', this.filterValue)
		}
	}
}
</script>

<style scoped>
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