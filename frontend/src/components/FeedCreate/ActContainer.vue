<template>
  <section id="act_container">
    <article id="category_container">
      <span v-for="(category, idx) in act_categories" :key="category" 
      class="categories" @click="check(category)" 
      :class="[chosen_category == category ? 'checked':'',
      {'top':idx==0}, {'bottom':idx==5}]">
        {{ category }}
      </span>
    </article>
    <article id="tag_container" v-for="category in act_categories" :key="category" v-show="chosen_category == category">
      <act-tag v-for="tag in act_details[category]" :key="tag" :tag="tag" />
    </article>
  </section>
</template>

<script>
import ActTag from '@/components/FeedCreate/ActTag'

export default {
  data: function(){
    return {
      chosen_category: '운동',
      act_categories: ['운동', '문화활동', '여가활동', '정리/휴식', '감정표현', '선물'],
      act_details: {
        '운동': ['했어요', '갔어요','뛰었어요', '움직였어요', 'ooo', 'ooo', 'ooo'], 
        '문화활동': ['봤어요', '했어요', '들었어요', '갔어요'],
        '여가활동': ['갔어요', '먹었어요', '마셨어요', '놀았어요'],
        '정리/휴식': ['했어요', '잤어요', '쉬었어요', '치웠어요'],
        '감정표현': ['울었어요', '싸웠어요', '소리 질렀어요', '웃었어요'],
        '선물': ['줬어요', '받았어요', '샀어요', '했어요']
      },
    }
  },
  components: {
    ActTag
  },
  methods: {
    check: function(category){
      this.chosen_category = category
    }
  }
}
</script>

<style scoped>
  #act_container {
    display: flex;
    border: 2px #cccccc solid;
    border-radius: 10px;
    width: 80%;
    margin: 0;
  }

  #category_container {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    border-right: 1px #cccccc solid;
    width: 20%;
    overflow: auto;
  }

  #tag_container {
    padding: 0.5rem;
    width: 80%;
    overflow: auto;
  }

  .categories {
    font-size: 1.25rem;
    font-weight: bold;
    width: 100%;
    margin: 0;
    padding: 0.25rem;
    text-align: start;
  }

  .checked {
    color: white;
    font-weight: bold;
    background-color: #5E39B3;
  }

  .top {
    border-top-left-radius: 0.5rem;
  }

  .bottom {
    border-bottom-left-radius: 0.5rem;
  }
</style>