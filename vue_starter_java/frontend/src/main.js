import Vue from 'vue'
import App from './App.vue'
import router from './router'
//import {CldContext, CldImage, CldVideo, CldTransformation} from 'cloudinary-vue'
//import Cloudinary from 'cloudinary-core'

Vue.config.productionTip = false

/* Vue.use(Cloudinary, {
  configuration: {
    cloudName: "tegram-mccool"
  }
}); */

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
