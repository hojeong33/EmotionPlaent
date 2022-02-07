<template>
  <article id="pick-container">
    <div id="pick-lists">
      <div id="list-tab">
        <h3 @click="tap = 1" :class="tap == 1 ? 'active': ''">음악</h3>
        <h3 @click="tap = 2" :class="tap == 2 ? 'active': ''">영화</h3>
        <h3 @click="tap = 3" :class="tap == 3 ? 'active': ''">활동</h3>
      </div>
      <div id="picks" v-if="filteredPicks.length">
        <picks v-for="(pick, idx) in filteredPicks" :key="idx" :pick="pick" />
      </div>
      <div v-else id="no-result">
        <img id="nothing" src="@/assets/images/etc/alien.png" alt="no result">
        <p>찜 목록이 없어요...</p>
      </div>
    </div>
    <div id="list-items">

    </div>
  </article>
</template>

<script>
import Picks from '@/components/user/Picks'
import pickData from '../../assets/data/pickData'

export default {
  name: 'PickList',
  data() {
    return {
      pickData,
      tap: 1,
    }
  },
  props: {
    userMood: Number,
    filter: Number
  },
  components: {
    Picks
  },
  methods: {
  },
  computed: {
    filteredPicks(){
      const temp = []
      this.pickData.forEach(ele => {
        if (!this.filter && ele.no == this.tap){
          temp.push(ele)
        }
        else if (ele.no == this.tap && ele.tagNo == this.filter){
          temp.push(ele)
        }
      })
      return temp
    },
  }
}
</script>

<style scoped>
  h3 {
    color: #777777;
    font-size: 1.2rem;
    font-weight: bold;
    margin-bottom: 0.5rem;
    cursor: pointer;
  }
  
  p {
    color: #777777;
    font-size: 1.25rem;
    font-weight: bold;
    margin: 3rem;
  }

  #pick-container {
    display: flex;
		flex-direction: column;
		align-items: center;
		width: 100%;
  }

  #pick-lists {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%;
  }

  #list-tab {
    display: flex;
    width: 40%;
    justify-content: space-evenly;
    align-items: center;
  }

  #picks {
    display: grid;
    justify-content: center;
    justify-items: start;
    align-items: baseline;
    grid-auto-rows: minmax(min-content, max-content);
    grid-template-columns: 1fr 1fr 1fr;
    width: 80%;
    gap: 2rem;
    margin: 5rem;
  }

  #no-result {
		display:flex;
		flex-direction: column;
		align-items: center;
		padding-top: 16.4vh;
	}

  #nothing {
    width: 10%;
    height: inherit;
    aspect-ratio: 1/1;
  }

  .active {
    color: black;
    font-size: 1.4rem;
    font-weight: bold;
    margin-bottom: 0.5rem;
  }

  .on {
    width: 70%;
    border-bottom: 0.25rem solid #5E39B3;
    border-radius: 20px;
  }

  .off {
    width: 70%;
    border-bottom: 0.2rem solid #ffffff;
  }
</style>