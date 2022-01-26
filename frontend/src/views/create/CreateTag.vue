<template>
  <div>
    <div class="container" id="create_container">
      <div id="create">
        <div id="create_header">
          <img src="../../assets/images/icons/return.png" alt="" id="back" @click="$router.go(-1)">
          <h3 id="title">글 쓰기</h3>
        </div>
        <hr>
        <div class="container" id="tag_body">
          <div id="tag_header">
            <h3 id="tag_header_title">활동 태그를 선택해주세요</h3>
            <h4 id="tag_header_content">활동 태그 하나는 필수에요!</h4>
            <h4 id="tag_header_content">알맞는 키워드를 넣어주세요.</h4>
          </div>
        </div>
        <br>
        <div class="container" id="tag_search">
          <!-- <form action="">
            <input type="search" placeholder="검색" id="search_bar">
          </form> -->
          <div id="tags">
            <div id="act_categorys">
              <act-category v-for="(category, idx) in act_categorys" :key="idx" :category="category" :isChecked="isChecked" :index="idx" id="categorys"></act-category>
            <!-- <div id="categorys"> -->
              <!-- <p v-for="(keyword,idx) in act_keywords" :key="idx" @click="selectAct" id="category">
                {{ keyword }}
              </p> -->
            </div>
            <div>
              <act-tags :selectedAct="selectedAct" id="acts"></act-tags>
            </div>
          </div>
        </div>
        <br>
        <br>
        <div id="footer">
          <button id="btn_next" @click="$router.push({name:'CreateText'})">다음</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ActTags from '@/components/FeedCreate/ActTags'
import ActCategory from '@/components/FeedCreate/ActCategory'

export default {
  name: 'Create_tag',
  data: function () {
    return {
      act_categorys: [
        '운동', '문화활동', '여가활동', '정리/휴식', '감정표현', '선물', '기타 등등'
      ],
      act_details: {
        '운동': ['했어요', '갔어요','뛰었어요', '움직였어요', 'ooo', 'ooo', 'ooo'], 
        '문화활동': ['봤어요', '했어요', '들었어요', '갔어요'],
        '여가활동': ['갔어요', '먹었어요', '마셨어요', '놀았어요'],
        '정리/휴식': ['했어요', '잤어요', '쉬었어요', '치웠어요'],
        '감정표현': ['울었어요', '싸웠어요', '소리 질렀어요', '웃었어요'],
        '선물': ['줬어요', '받았어요', '샀어요', '했어요']
      },
      isChecked: [false, false, false, false, false, false, false],
      selectedKeyword: '',
      selectedAct: []
    }
  },
  components: {
    ActTags,
    ActCategory
  },
  methods: {
    selectAct: function(actKeyword) {
      // console.log
      // this.isChecked = !this.isChecked
      // console.log(this.isChecked)
      // console.log(actKeyword.target.id)
      // document.getElementById(actKeyword.target.id).className = 'checked_category'
      if (document.getElementById(actKeyword.target.textContent).className === 'checked_category') {
        console.log((actKeyword.target.textContent).className)
        document.getElementById(actKeyword.target.id).className = 'unchecked_category'
      } else {
        document.getElementById(actKeyword.target.id).className = 'checked_category'
      }
      this.selectedKeyword = actKeyword.target.textContent.trim()
      for (let key in this.act_details) {
        if (key === this.selectedKeyword) {
          this.selectedAct = this.act_details[key]
        }
      }
    }
  }

}
</script>

<style scoped>
#create_container{
  display:flex;
  width: 70vh;
  height: 67vh;
  background-color: white;
  border-radius: 20px;
  border: thin black;
  border-style: solid; 
  justify-content: center;
  align-items: center;
}
#create_header{
  display:flex;
  flex-direction: row;
  flex-wrap: nowrap;
  justify-content: space-between;
}
#back{
  width: 30px;
  height: 30px;
}
#title{
  margin: auto;
}
h3{
  font-weight: bold;
  font-size: 20px;
}
#tag_header{
  text-align: left;
  font-weight: bold;
  font-size: 2rem;
}
#tag_header_title{
  margin-bottom: 2rem;
}
#tag_header_content{
  font-size: 1.25rem;
}
/* #search_bar{
  width: 60vh;
  border-radius: 5px;
  background-color: #E7E7E7;
} */
#tags{
  display: flex;
  flex-direction: row;
  width:60vh;
  border-radius: 5px;
  border: thin black;
  border-style: solid; 
}
#act_categorys{
  display:flex;
  flex-direction: column;
  text-align: left;
  padding-left: 3px;
  overflow-y: scroll; height: 20vh;
  width: 12vh;
  color: black;
  font-weight: bold;
}
#acts{
  display:flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  align-content: center;
  overflow-y: scroll; width: 48vh;
  margin-top:5px;
  margin-bottom: 5px;
}
#footer{
  display:flex;
  flex-direction: row;
  justify-content: right;
  margin-right: 1rem
}
#btn_next{
  border-radius: 30px;
  width: 70px;
  height: 30px;
  background-color: #5E39B3;
  color: white;
  font-weight: bold;
}
</style>