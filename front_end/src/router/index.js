import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: () => import('../views/Manage.vue'),
    redirect: "/login",
    children: [
      { path: 'home', name: '首页', component: () => import('../views/home.vue')},
      { path: 'product', name: '产品管理', component: () => import('../views/product.vue')},
      { path: 'user', name: '客户管理', component: () => import('../views/User.vue')},
      { path: 'purchase', name: '产品申购', component: () => import('../views/purchase.vue')},
      { path: 'purchase_query', name: '申购业务查询', component: () => import('../views/purchase_query.vue')},
      { path: 'redeem_query', name: '赎回业务查询', component: () => import('../views/redeem_query.vue')},
      { path: 'bankcard_query', name: '银行卡流水查询', component: () => import('../views/bankcard_query.vue')},
      { path: 'clear', name: '业务清算-->更新工作日', component: () => import('../views/clear.vue')},
      { path: 'clear2', name: '业务清算-->行情导入', component: () => import('../views/clear2.vue')},
      { path: 'clear3', name: '业务清算-->数据导出', component: () => import('../views/clear3.vue')},
      { path: 'clear4', name: '业务清算-->处理确认', component: () => import('../views/clear4.vue')},
    ]
  },
  {
    path: '/about',
    name: 'About',
    component: () => import('../views/About.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  localStorage.setItem("currentPathName", to.name)  // 设置当前的路由名称，为了在Header组件中去使用
  store.commit("setPath")  // 触发store的数据更新
  next()  // 放行路由
})

export default router