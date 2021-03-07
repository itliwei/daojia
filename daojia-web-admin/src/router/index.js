import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'Dashboard', icon: 'example' }
    }]
  },

  {
    path: '/sys',
    component: Layout,
    redirect: '/sys/sysUser',
    name: '权限管理',
    meta: { title: '权限管理', icon: 'example' },
    children: [
      {
        path: 'sysUser',
        name: '系统用户',
        component: () => import('@/views/sys/SysUser/index'),
        meta: { title: '系统用户', icon: 'table' }
      },
      {
        path: 'authority',
        name: '角色管理',
        component: () => import('@/views/sys/Role/index'),
        meta: { title: '角色管理', icon: 'table' }
      },
      {
        path: 'resource',
        name: '资源管理',
        component: () => import('@/views/sys/Resource/index'),
        meta: { title: '资源管理', icon: 'table' }
      },
      {
        path: 'menu',
        name: '菜单管理',
        component: () => import('@/views/sys/Menu/index'),
        meta: { title: '菜单管理', icon: 'table' }
      }
    ]
  },

  {
    path: '/sysbase',
    component: Layout,
    redirect: '/sysbase/dictionary',
    name: '系统管理',
    meta: { title: '系统管理', icon: 'example' },
    children: [
      {
        path: 'dictionary',
        name: '字典管理',
        component: () => import('@/views/sys/Dictionary/index'),
        meta: { title: '字典管理', icon: 'table' }
      },
      {
        path: 'area',
        name: '城市管理',
        component: () => import('@/views/sys/Area/index'),
        meta: { title: '城市管理', icon: 'table' }
      },
      {
        path: 'tenant',
        name: '租户管理',
        component: () => import('@/views/sys/tenant/index'),
        meta: { title: '租户管理', icon: 'table' }
      }
    ]
  },


]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
