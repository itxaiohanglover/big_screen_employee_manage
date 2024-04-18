import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [

  {
    path: '/login',
    name: 'login',
    component: () => import('../views/login.vue')
  },
  {
    path: "/",
    name: "layout",
    component: () => import('../layout/index.vue'),
    children: [
      {
        path: '/index',
        name: 'index',
        component: () => import('../views/index.vue')
      },
      {
        path: '/admin',
        name: 'admin',
        component: () => import('../views/employee/admin.vue')
      },
      {
        path: '/dept',
        name: 'dept',
        component: () => import('../views/employee/dept.vue')
      },
      {
        path: '/user',
        name: 'user',
        component: () => import('../views/employee/user.vue')
      },
      {
        path: '/userdept',
        name: 'userdept',
        component: () => import('../views/employee/userdept.vue')
      },
    ]
  },


]
const router = new VueRouter({
  mode: 'history', // 去掉url中的#
  routes: routes
})

// 注册一个全局前置守卫
router.beforeEach((to, from, next) => {
  // 登录及注册页面可以直接进入,而主页面需要分情况
  if (to.path == '/login') {
    if (localStorage.login === "true") {
      next('/');  // 用户已登录
    } else {
      next();
    }
  }
  else {
    if (from.path == "/login") // 从登录页面可以直接通过登录进入主页面
    {
      next();
    }
    else {
      // 从/进入,如果登录状态是true，则直接next进入主页面
      if (localStorage.login === "true") {
        next();
      }
      else { // 如果登录状态是false，那么跳转至登录页面,需要登录才能进入主页面
        next('/login');
      }
    }
  }
})
export default router