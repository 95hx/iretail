import Vue from 'vue'
import App from './App.vue'
import Router from "vue-router";
import createRouter from "./router";
import AxiosConfig from "./config/axios.config";
import axios from "axios";
import Element from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import gu from './utils/globalUtil';
Vue.use(Element, {size: 'small', zIndex: 3000});
const router = createRouter();
const GlobalUtil = gu;
AxiosConfig.init();
router.beforeEach((to, from, next) => {
    if (to.meta.requireAuth) {
        if (GlobalUtil.validJwt()) {
            next();
        } else {
            next({
                path: "/oauth"
            });
        }
    } else {
        if (to.name === 'oauth' && GlobalUtil.validJwt()) {
            next({
                path: "/home"
            });
        } else {
            next();
        }
    }
});
Vue.config.productionTip = false;
Vue.use(Router);


//全局变量this.$设置
Vue.prototype.$axios = axios;
new Vue({
    router,
    render: h => h(App),
}).$mount('#app');
