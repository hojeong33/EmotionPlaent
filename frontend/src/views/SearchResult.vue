<template>
  <div>
    <side-profile-card
      :user-info="userInfo"
    >
    </side-profile-card>
    <div class="container justify-content-center">
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
								<search-feed-list></search-feed-list>
							</div>
							<div v-else>
								<search-info></search-info>
							</div>
						</section>
					</transition>
				</div>
			</div>
		</div>
  </div>
</template>

<script>
import SideProfileCard from '@/components/SideProfileCard.vue'
import SearchFeedList from '@/components/Search/SearchFeedList.vue'
import SearchInfo from '@/components/Search/SearchInfo.vue'
import TabItem from '@/views/main//TabItem.vue'

export default {
  name: 'SearchResult',
  components: {SideProfileCard, SearchFeedList, SearchInfo, TabItem},
  data() {
    return {
			currentId: 1,
			list: [
				{ id: 1, label: '피드', content: '1' },
				{ id: 2, label: '정보', content: '2' },
				],
      userInfo: {
      username: '최강상후',
      mood: 3,
      posts: 0,
      followings: 0,
      followers: 20100,
      },
      onFeed: true,
      onInfo: false,
      postlist: [],
      infolist: [],
    }
  },
  methods: {
    toggleFeed: function () {
      this.onFeed = true
      this.onInfo = false
    },
    toggleInfo: function () {
      this.onInfo = true
      this.onFeed = false
    },
  },
  computed: {
		current() {
			return this.list.find(el => el.id === this.currentId) || {}
		}
	},
}
</script>

<style scoped>
.container {
  width: 50vw;
  min-width: 700px;
  min-height: 92.5vh;
  margin: auto;
  border-left: 0.1rem solid gainsboro;
  border-right: 0.1rem solid gainsboro;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  }
	.contents {
	position: relative;
	overflow: hidden;
	width: 50vw;
  min-width: 700px;
  min-height: 92.5vh;
	/* 메인 피드 크기 -> 100vh-> 센터 */
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

	.v-leave-active {
	position: absolute;
	}
	.v-enter {
	transform: translateX(-100%);
	}
	.v-leave-to {
	transform: translateX(100%);
	}
	.tabs{
		display:flex;
    justify-content:center;
	}
</style>