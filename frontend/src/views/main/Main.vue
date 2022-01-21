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
                                {{ current.content }}
                                <recommend></recommend>
                                <feed></feed>
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
import Recommend from './Recommend.vue'
import Feed from './Feed.vue'
import Navigation from '../../components/Navigation.vue'
export default {
    components: { TabItem, Recommend, Feed, Navigation },
    // components: {  Navigation },
  data() {
     return {
      currentId: 1,
      list: [
        { id: 1, label: '추천', content: '콘텐츠1' },
        { id: 2, label: '피드', content: '콘텐츠2' },
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
    display: flexbox;
    border-left: 1px solid black;
    height: 100vh;
    /* margin-left: 25%; */
    }
    .v2 {
    display: flex;
    border-left: 1px solid black;
    height: 500px;
    margin-left: 70%;
    }
    .contents {
    position: relative;
    overflow: hidden;
    width: 100vh;
    height: 100vh;
    border: 2px solid #000;
    }
    .item {
    box-sizing: border-box;
    padding: 10px;
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


    