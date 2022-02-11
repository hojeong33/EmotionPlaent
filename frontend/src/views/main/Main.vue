<template>
  <div>
    <side-profile-card :user-info="userInfo"> </side-profile-card>
    <!-- <button @click="follow">팔로우 신청</button>
    <button @click="comment">댓글 알림</button>
    <button @click="feedlike">피드 좋아요</button>
    <button @click="picklike">찜 좋아요</button>
    <button @click="alarmdelete(1)">알림 삭제하기</button>
    <button @click="alarmselect">알림 가져오기</button>
    <button @click="socketconnect">소켓 재연결</button> -->
    <div class="container justify-content-center">
      <div class="example">
        <div class="tabs">
          <TabItem
            v-for="item in list"
            v-bind="item"
            :key="item.id"
            v-model="currentId"
          />
        </div>
        <div class="contents">
          <transition>
            <section class="item" :key="currentId">
              <div v-if="current.content == '1'">
                <recommend :user-info="userInfo" />
              </div>
              <div v-else>
                <feed-list :posts="posts" />
              </div>
            </section>
          </transition>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import TabItem from "./TabItem.vue";
import Recommend from "../../components/MainPage/RecommendTab/Recommend.vue";
import FeedList from "../../components/MainPage/FeedTab/FeedList.vue";
import posts from "../../assets/data/posts.js";
import SideProfileCard from "@/components/SideProfileCard.vue";

const session = window.sessionStorage;

export default {
  name: "Main",
  components: { TabItem, Recommend, FeedList, SideProfileCard },
  data() {
    return {
      posts,
      currentId: 1,
      list: [
        { id: 1, label: "놀거리", content: "1" },
        { id: 2, label: "이야기", content: "2" },
      ],
      userInfo: {
        username: "최강상후",
        mood: null,
        posts: 0,
        followings: 0,
        followers: 20100,
      },
    };
  },
  computed: {
    current() {
      return this.list.find((el) => el.id === this.currentId) || {};
    },
  },
  created() {
    console.log("유저인포");
    console.log(this.$store.state.userInfo);
    console.log(session.userInfo)
    if (this.$store.state.recommendReload === 0) {

        this.$store.dispatch("recommendMusic");
				this.$store.dispatch("recommendMovie");
				this.$store.dispatch("recommendActivity");

      this.$store.state.recommendReload = 1;
    }
    this.$store.dispatch("userfollowdate", this.$store.state.userInfo.no);
    this.$store.dispatch("alarmselect")
    this.$store.dispatch("connect")
  },
  methods: {

    follow() {
      this.$store.dispatch("follow");
    },
    comment() {
      this.$store.dispatch("comment");
    },
    feedlike() {
      this.$store.dispatch("feedlike");
    },
    picklike() {
      this.$store.dispatch("picklike");
    },
    alarmdelete(el) {
      this.$store.dispatch("alarmdelete", el);
    },
    alarmselect() {
      this.$store.dispatch("alarmselect");
    },
    socketconnect() {
      this.$store.dispatch("connect");
    },
  },
};
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
  display: flex;
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
  transform: translateX(100%);
}
.v-leave-to {
  transform: translateX(100%);
}
.tabs {
  display: flex;
  justify-content: center;
}
</style>


		