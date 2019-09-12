import Vue from 'vue'
import App from './App.vue'
import router from './router'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
//import {CldContext, CldImage, CldVideo, CldTransformation} from 'cloudinary-vue'
//import Cloudinary from 'cloudinary-core'

Vue.config.productionTip = false
Vue.use(BootstrapVue)

/* Vue.use(Cloudinary, {
  configuration: {
    cloudName: "tegram-mccool"
  }
}); */

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
