import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import error from '@/components/error'
import mainBody from '@/components/chat/mainBody'
import sign from '@/components/login/sign'
import sign_up from '@/components/login/sign_up'
import sign_in from '@/components/login/sign_in'
import message from '@/components/page/message'
import friendManagement from '@/components/friend/friendManagement'
import chat from '@/components/chatIndex'
import noteList from '@/components/noteList/noteList'
import article from '@/components/article/article'
import editor from '@/components/editor/editor'
import userMessage from '@/components/user/usermessage'
import comment from '@/components/article/userComment'
Vue.use(Router)

export default new Router({
  routes: [

    {
      path: '/',
      redirect: '/sign/in',
      component: HelloWorld
    },
    {
      path: '/error',
      component: error
    },
    {
      path: '/chat',
      component: chat,
      redirect: '/chat/mainBody',
      children: [
        {//designed for comment
          path: 'comment/:id', component: comment,

        },
        {
          path: 'message',
          component: message
        },
        {
          path: 'mainBody',
          component: mainBody,
          meta: {
            title: '文章列表'
          }
        },
        {
          path:'friendManagement',
          component:friendManagement
        },
        {
          path:'noteList',
          component:noteList
        },
        {
          path: 'article/:id',
          component: article,
          meta: {
            title: '文章'
          }
        },
        {
          path: 'editor',
          component: editor,
          meta: {
            title: '编辑文章'
            // requireAuth: true
          }
        },
        {
          path: '/usermessage/:id', component: userMessage
        },
      ]
    },
    {
      path: '/sign/',
      component: sign,
      children:[
        {
          path: 'in',
          component: sign_in,
          meta: {
            title: 'Blog登陆'
          }
        },
        {
          path: 'up',
          component: sign_up,
          meta: {
            title: 'Blog注册'
          }
        }

      ]
    }
  ]
})
