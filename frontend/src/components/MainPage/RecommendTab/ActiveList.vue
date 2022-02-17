<template>
  <div>
    <h3>{{ tmp.name }}을 탐사하기</h3>
    <div class="card-carousel-wrapper">
        <div class="card-carousel--nav__left" @click="moveCarousel(-1)" :disabled="atHeadOfList"></div>
        <div class="card-carousel">
            <div class="card-carousel--overflow-container">
                <div v-if="this.$store.state.recommendType === 1" class="card-carousel-cards" :style="{ transform: 'translateX' + '(' + currentOffset + 'px' + ')' }">
                    <div id="post_img" class="card-carousel--card" v-for="item in this.actData" :key="item.index">
                        <img @click="addPlayList(item)" id="goldstar" src="@/assets/images/icons/goldstar.png" alt="">
                        <img id="active_img" :src="item.imgLink"/>
                        <div class="card-carousel--card--footer">
                            <p>{{ item.title }}</p>
                            <p class="tag" v-for="(tag, index) in item.tag" :key="index" :class="index &gt; 0 ? 'secondary' : ''" >{{ tag }}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card-carousel--nav__right" @click="moveCarousel(1)" :disabled="atEndOfList"></div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

const session = window.sessionStorage

export default {
    name:'ActiveList',
    data() {
        return {
        act: null,
        currentOffset: 0,
        windowSize: 3,
        paginationFactor: 220,
        type:2
        }
    },
    props: {
      tab: Number
    },
    computed: {
        tmp: function () {
        const mood = this.$store.state.userInfo.mood
        const style = this.$store.state.planetStyles.find(el => el.id === mood) || {}
        return style
        },
        atEndOfList() {
        return this.currentOffset <= (this.paginationFactor * -1) * (this.$store.state.recommendActivity.length - 5*this.windowSize);
        },
        atHeadOfList() {
        return this.currentOffset === 0;
        },
        actData(){
          if (this.act){
            return this.act.slice(10 * this.tab, 10 * (this.tab+1))
          }
          return 0
      },
    },
    methods: {
        moveCarousel(direction) {
        if (direction === 1 && !this.atEndOfList) {
            this.currentOffset -= this.paginationFactor;
        } else if (direction === -1 && !this.atHeadOfList) {
            this.currentOffset += this.paginationFactor;
        }
        },
        addPlayList:function(item){
          this.sendData=[this.type,item]
          this.$store.commit('addPlayListActive',this.sendData)
          // console.log(this.$store.state.recommendMusic)
        }
    },
    created(){
      let headers = {
        'at-jwt-access-token': session.getItem('at-jwt-access-token'),
        'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
      };
			axios.get('/api/recommend/activity', {
          headers: headers,
        }).then((res) => {
          this.act = res.data
          this.$store.dispatch('accessTokenRefresh', res)
        this.$emit('comp')
      }).catch((err) => {
        console.log('err act', err);
      }).then(() => {
        console.log('get act data End!!');
      });
  },
}
</script>

<style scoped>
  img{
    width:10rem;
    height:10rem;
}
.card-carousel-wrapper {
	display: flex;
	align-items: center;
	justify-content: center;
	margin: 20px 0 50px;
	color: #666a73;
}

.card-carousel {
display: flex;
justify-content: center;
width: 85%;
}
.card-carousel--overflow-container {
overflow: hidden;
}
.card-carousel--nav__left, .card-carousel--nav__right {
display: inline-block;
width: 15px;
height: 15px;
padding: 10px;
box-sizing: border-box;
border-top: 2px solid #5E39B3;
border-right: 2px solid #5E39B3;
cursor: pointer;
margin: 0 20px;
transition: transform 150ms linear;
}
.card-carousel--nav__left[disabled], .card-carousel--nav__right[disabled] {
opacity: 0.2;
border-color: black;
}
.card-carousel--nav__left {
transform: rotate(-135deg);
}
.card-carousel--nav__left:active {
transform: rotate(-135deg) scale(0.9);
}
.card-carousel--nav__right {
transform: rotate(45deg);
}
.card-carousel--nav__right:active {
transform: rotate(45deg) scale(0.9);

}

.card-carousel-cards {
display: flex;
transition: transform 150ms ease-out;
transform: translatex(0px);
}
.card-carousel-cards .card-carousel--card {
margin: 0 10px;
/* cursor: pointer; */
box-shadow: 0 4px 15px 0 rgba(40, 44, 53, 0.06), 0 2px 2px 0 rgba(40, 44, 53, 0.08);
background-color: #fff;
border-radius: 4px;
z-index: 3;
margin-bottom: 2px;
border: 1px solid gainsboro;
}
.card-carousel-cards .card-carousel--card:first-child {
margin-left: 0;
}
.card-carousel-cards .card-carousel--card:last-child {
margin-right: 0;
}
.card-carousel-cards .card-carousel--card img {
vertical-align: bottom;
border-top-left-radius: 4px;
border-top-right-radius: 4px;
transition: opacity 150ms linear;
user-select: none;
}
.card-carousel-cards .card-carousel--card img:hover {
/* opacity: 0.5; */
}
.card-carousel-cards .card-carousel--card--footer {
border-top: 0;
padding: 7px 15px;
}
.card-carousel-cards .card-carousel--card--footer p {
padding: 3px 0;
margin: 0;
margin-bottom: 2px;
font-size: 1.2rem;
font-weight: bold;
color: #2c3e50;
user-select: none;
}
.card-carousel-cards .card-carousel--card--footer p.tag {
font-size: 11px;
font-weight: 300;
padding: 4px;
background: rgba(40, 44, 53, 0.06);
display: inline-block;
position: relative;
margin-left: 4px;
color: #666a73;
}
.card-carousel-cards .card-carousel--card--footer p.tag:before {
content: "";
float: left;
position: absolute;
top: 0;
left: -12px;
width: 0;
height: 0;
border-color: transparent rgba(40, 44, 53, 0.06) transparent transparent;
border-style: solid;
border-width: 8px 12px 12px 0;
}
.card-carousel-cards .card-carousel--card--footer p.tag.secondary {
margin-left: 0;
border-left: 1.45px dashed white;
}
.card-carousel-cards .card-carousel--card--footer p.tag.secondary:before {
display: none !important;
}
.card-carousel-cards .card-carousel--card--footer p.tag:after {
content: "";
position: absolute;
top: 8px;
left: -3px;
float: left;
width: 4px;
height: 4px;
border-radius: 2px;
background: white;
box-shadow: 0px 0px 0px #004977;
}
h3{
    text-align: left;
    margin-left: 3rem;
    font-weight: bold;
    font-size: 2rem;
}

  #post_img {
    position: relative;
  }
  #goldstar{
    position: absolute;
    cursor: pointer;
    width: 2rem;
    height: 2rem;
    right: 1%;
    top:52%;
  }
  #active_img{
      /* width: 8rem;
      height: 8rem; */
      padding: 1rem;
  }

</style>