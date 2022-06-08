import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './assets/global.css'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import request from "@/utils/request";
import store from './store'
//引入 echarts
import * as echarts from 'echarts'
//注册组件
Vue.prototype.$echarts = echarts

Vue.config.productionTip = false
Vue.use(ElementUI, { size: "small" });
Vue.prototype.request=request

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
