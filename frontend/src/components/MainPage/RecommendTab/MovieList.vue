<template>
  <div>
    <h3>영화</h3>
    <div class="card-carousel-wrapper">
        <div class="card-carousel--nav__left" @click="moveCarousel(-1)" :disabled="atHeadOfList"></div>
        <div class="card-carousel">
            <div class="card-carousel--overflow-container">
                <div class="card-carousel-cards" :style="{ transform: 'translateX' + '(' + currentOffset + 'px' + ')' }">
                    <div class="card-carousel--card" v-for="item in items" :key="item.index"><img src="../../../assets/images/default_image.png"/>
                        <div class="card-carousel--card--footer">
                            <p>{{ item.name }}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card-carousel--nav__right" @click="moveCarousel(1)" :disabled="atEndOfList"></div>
    </div>
    <p style="text-align:right; margin-right:5rem;">더보기</p>   
  </div>
</template>

<script>
export default {
    name:'MovieList',
    data() {
        return {
        currentOffset: 0,
        windowSize: 3,
        paginationFactor: 220,
        items: [
            {name: '제목'},
            {name: '제목'},
            {name: '제목'},
            {name: '제목'},
            {name: '제목'},
            {name: '제목'},
            {name: '제목'},
            {name: '제목'},
            {name: '제목'}
        ]
        }
    },
    computed: {
        atEndOfList() {
        return this.currentOffset <= (this.paginationFactor * -1) * (this.items.length - this.windowSize);
        },
        atHeadOfList() {
        return this.currentOffset === 0;
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
    }
}
</script>

<style scoped>
  @import '../../../assets/styles/carousel.css';
</style>