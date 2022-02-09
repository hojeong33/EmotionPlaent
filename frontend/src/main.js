import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

import GAuth from 'vue-google-oauth2'

const gauthOption = {
  clientId: '836392631111-1o8o4qhl1ctgnooodk59ctv5im6djme1.apps.googleusercontent.com',
  scope: 'profile email',
  prompt: 'select_account'
}
Vue.use(GAuth, gauthOption)