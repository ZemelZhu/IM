// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import * as socketApi from './net/socket'
import axios from 'axios'
import  VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
Vue.use(VueQuillEditor)
Vue.prototype.axios = axios
Vue.use(ElementUI);
Vue.config.productionTip = false;
Vue.prototype.socketApi = socketApi;
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
  mounted() {
    // 请求拦截（配置发送请求的信息）
    axios.interceptors.request.use(function (config){
      // 处理请求之前的配置
      return config;
    }, function (error){
      // 请求失败的处理
      return Promise.reject(error);
    });


// 响应拦截（配置请求回来的信息）
    axios.interceptors.response.use(function (response){
      if(response.status==201) {
        console.log("session失效");
        // this.$message({
        //   message: '警告:登陆失效或未登录,请先登录',
        //   type: 'warning'
        // });
        router.push({path: '/sign/in'});
        return "";
      }
      else
      {
        return response;
      }
      // 处理响应数据

    }, function (error){
      // 处理响应失败
      router.push({path: '/error'});
      return Promise.reject(error);
    });

  }
})
