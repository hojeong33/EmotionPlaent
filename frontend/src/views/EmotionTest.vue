<template>
  <div id="test_container">
    <section id="test_header">
      <h1>감정테스트를 시작할게요</h1>
      <h3>지금 느끼는 감정을 모두 체크해보세요.</h3>
      <h3>오늘의 감정에 맞는 행성으로 데려다드려요!</h3>
    </section>
    <section id="selected_keywords_container">
      <label for="selected_keywords">지금까지 고른 키워드</label>
      <article id="selected_keywords">
        <selected-keyword v-for="(keyword, idx) in selected" :key="idx"
        :keyword="keyword" @delete_keyword="deleteKeyword" />
      </article>
    </section>
    <section id="test_keywords_container">
      <transition-group id="test_keywords" :name="page - before_page > 0 ? 'slide':'slide-reverse'">
        <article class="test_page" v-show="page == 1" key="page_1">
          <test-keyword v-for="keyword in keywords.slice(0, 8)" :key="keyword" 
          :keyword="keyword" @checked="check" :ref="keyword"/>
        </article>
        <article class="test_page" v-show="page == 2" key="page_2">
          <test-keyword v-for="keyword in keywords.slice(12, 24)" :key="keyword" 
          :keyword="keyword" @checked="check" :ref="keyword"/>
        </article>
        <article class="test_page" v-show="page == 3" key="page_3">
          <test-keyword v-for="keyword in keywords.slice(24, )" :key="keyword" 
          :keyword="keyword" @checked="check" :ref="keyword"/>
        </article>
      </transition-group>
      <article id="paginator_container">
        <button @click="before_page = page; page = 1" class="paginator" 
        :class="{ 'active_paginator' : page == 1 }"></button>
        <button @click="before_page = page; page = 2" class="paginator" 
        :class="{ 'active_paginator' : page == 2 }"></button>
        <button @click="before_page = page; page = 3" class="paginator" 
        :class="{ 'active_paginator' : page == 3 }"></button>
      </article>
    </section>
    <section id="buttons">
      <button id="submit_test_btn">다 골랐어요</button>
      <button id="return_to_btn">아직 안할래요</button>
    </section>
  </div>
</template>

<script>
  import TestKeyword from '@/components/EmotionTest/EmotionKeyword'
  import SelectedKeyword from '@/components/EmotionTest/SelectedKeyword'

  export default {
    beforeCreate: function () {
      document.body.className = 'astro';
    },
    data: function(){
      return {
        page: 1,
        before_page: null,
        keywords: [
          '겁나다', '긴박하다', '긴장되다', '두렵다', '무섭다', '무시무시하다',
          '가뿐하다', '감개무량하다', '감격하다', '감동하다', '감미롭다', '감복하다',
          '겸연쩍다', '노파심', '머쓱하다', '멋쩍다', '무안하다', '쭈뼛거리다',
          '갑작스럽다', '경악하다', '경이롭다', '급작스럽다', '기겁하다', '기막히다',
          '삐지다', '격분하다', '격앙되다', '괘씸하다', '굴욕적이다', '비아냥대다',
          '가련하다', '가슴아프다', '걱정하다', '그리워하다', '낙심하다', '막막하다'
        ],
        selected: []
      }
    },
    components: {
      TestKeyword,
      SelectedKeyword
    },
    methods: {
      check: function(keyword){
        const idx = this.selected.indexOf(keyword)

        if (idx != -1){
          this.selected.splice(idx, 1)
        }
        else {
          this.selected.push(keyword)
        }
      },
      deleteKeyword: function(keyword){
        const idx = this.selected.indexOf(keyword)

        if (idx != -1){
          this.selected.splice(idx, 1)
        }
        this.$refs[keyword][0].isChecked = false
        console.log(this.selected)
      }
    },
    computed: {
      keywords_info: function(){
        console.log('hi')
        let informs = []

        this.keywords.forEach(keyword => {
          informs.push([keyword, false])
        })
        return informs
      },
    },
    created: function(){
      this.keywords = this.keywords.sort(() => Math.random() - 0.5)
    }
  }
</script>

<style scoped>
  h1 {
    color: #5E39B3;
    font-size: 2rem;
    font-weight: bold;
    margin-bottom: 2rem;
  }

  h3 {
    font-size: 1.25rem;
    font-weight: bold;
    letter-spacing: -1px;
    line-height: 75%;
  }

  label {
    color: #5E39B3;
    font-size: 1.125rem;
    font-weight: bold;
    letter-spacing: -0.5px;
    margin-left: 0.5rem;
  }

  button {
    background-color: #5E39B3;
    color: white;
    font-size: 1.125rem;
    font-weight: bold;
      border: 3px #5E39B3 solid;
    border-radius: 20px;
    padding: 0.4rem 1.125rem;
    margin-bottom: 1.125rem;
    cursor: pointer;
      line-height: 2rem;
  }

  #test_container {
    background-color: white;
    width: 75vh;
    min-width: 750px;
    margin: 2rem auto;
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
    border-radius: 20px;
    padding: 3rem 4rem 1.5rem;
    }

  #test_header {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    align-self: flex-start;
    margin-bottom: 2rem;
  }

  #selected_keywords_container {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    align-self: stretch;
    margin-bottom: 1rem;
  }

  #selected_keywords {
    width: 100%;
    height: 5vh;
    padding: 0.5rem;
    border-bottom: 1px #5E39B3 solid;
    display: flex;
    flex-wrap: nowrap;
  }

  #test_keywords_container {
    width: 100%;
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    padding: 1rem;
    border-bottom: 1px #5E39B3 solid;
    margin-bottom: 2rem;
  }

  #test_keywords {
    width: 100%;
    height: 24vh;
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    padding: 1rem;
    position: relative;
    overflow: hidden;
  }

  .test_page {
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    position: absolute;
    width: 100%;
  }

  @keyframes slide-in {
    from { right: -100% }
    to { right: 0 }
  }

  @keyframes slide-out {
    from { right: 0 }
    to { right: 100% }
  }

  .slide-enter-active {
    animation: slide-in 1s ease-in-out;
  }

  .slide-leave-active {
    animation: slide-out 1s ease-in-out;
  }

  .slide-reverse-enter-active {
    animation: slide-out 1s ease-in-out reverse;
  }

  .slide-reverse-leave-active {
    animation: slide-in 1s ease-in-out reverse;
  }

  #paginator_container {
    width: 100%;
    margin-top: 1.5rem;
  }

  .paginator{
    background-color: white;
    border: 2px rgb(128, 128, 128, 0.75) solid;
    width: 1.5vh;
    height: 1.5vh;
    padding: 0;
    margin: 1rem 1rem 0;
  }

  #buttons {
    width: 50%;
    display: flex;
    justify-content: space-between;
  }

  #return_to_btn {
    background-color: #777777;
    border: 3px #777777 solid;
  }

  .active_paginator {
    background-color: #777777;
  }
</style>