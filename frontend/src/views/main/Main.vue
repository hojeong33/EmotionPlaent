<template>
    <div>
        <div><navigation></navigation></div>
          <div class="container justify-content-center">
              <div class="v1"></div>
              <div class="example">
                  <div class="tabs">
                  <TabItem
                  v-for="item in list"
                  v-bind="item" :key="item.id"
                  v-model="currentId"/>
                  </div>
                  <div class="contents">
                      <transition>
                          <section class="item" :key="currentId">
                            <div v-if="current.content=='1'">
                              <recommend></recommend>
                            </div>
                            <div v-else>
                              <feed></feed>
                            </div>
                          </section>
                      </transition>
                  </div>
              </div>
              <div class="v1"></div>
          </div>
    </div>
 
</template>

<script>
import TabItem from './TabItem.vue'
import Recommend from '../../components/MainPage/RecommendTab/Recommend.vue'
import Feed from '../../components/MainPage/FeedTab/Feed.vue'
import Navigation from '../../components/Navigation.vue'
export default {
  name:'Main',
  components: { TabItem, Recommend, Feed, Navigation },
  data() {
     return {
      currentId: 1,
      list: [
        { id: 1, label: '추천', content: '1' },
        { id: 2, label: '피드', content: '2' },
      ]
    }
  },
  computed: {
    current() {
      return this.list.find(el => el.id === this.currentId) || {}
    }
  }
}
</script>

<style scoped>
    .v1 {
    display: flex;
    /* border-left: 1px solid gainsboro; */
    min-height: 100vh;
    /* margin-left: 25%; */
    }
    .contents {
    position: relative;
    overflow: hidden;
    width: 100vh;
    min-height: 100vh;
    border: 2px solid gainsboro;
    }
    .item {
    display:flex;
    flex-direction: column;
    /* box-sizing: border-box; */
    align-items: stretch;
    /* padding: 10px; */
    width: 100%;
    transition: all 0.8s ease;
    }
    /* 트랜지션 전용 스타일 */
    .v-leave-active {
    position: absolute;
    }
    .v-enter {
    transform: translateX(-100%);
    }
    .v-leave-to {
    transform: translateX(100%);
    }
</style>


    