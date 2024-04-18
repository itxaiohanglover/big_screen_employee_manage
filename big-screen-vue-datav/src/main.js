import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import dataV from '@jiaminghi/data-view';
// 引入vue-cropper
import VueCropper from 'vue-cropper'
// 封装axios
import httpRequest from '@/utils/httpRequest' 
Vue.prototype.$http = httpRequest // ajax请求方法
// 引入ElementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/scss/tables.scss'
// 引入全局css
import './assets/scss/style.scss';
// 按需引入vue-awesome图标
import Icon from 'vue-awesome/components/Icon';
import 'vue-awesome/icons/chart-bar.js';
import 'vue-awesome/icons/chart-area.js';
import 'vue-awesome/icons/chart-pie.js';
import 'vue-awesome/icons/chart-line.js';
import 'vue-awesome/icons/align-left.js';

//引入echart
//4.x 引用方式
import echarts from 'echarts'
//5.x 引用方式为按需引用
//希望使用5.x版本的话,需要在package.json中更新版本号,并切换引用方式
//import * as echarts from 'echarts'
Vue.prototype.$echarts = echarts
Vue.config.productionTip = false;

// 全局注册
Vue.component('icon', Icon);
Vue.use(dataV);
Vue.use(ElementUI);
Vue.use(VueCropper);

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
