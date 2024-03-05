// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from "axios";
import vueAxios from 'vue-axios'
import * as echarts from 'echarts'
// import cesium from 'cesium'
//
// Vue.use(cesium)
// 引入echarts

import moment from "moment"
Vue.prototype.$moment = moment;

window.echarts = require('echarts');
window.moment=moment;
Vue.prototype.$echarts = echarts


Vue.use(ElementUI)
Vue.use(vueAxios,axios)
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
  beforeCreate() {
    Vue.prototype.$bus = this
  }
})
