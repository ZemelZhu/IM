# Blog-springboot
## 详细
 * 该项目实现了一个可以写文章，注册登录，并具有评论点赞的博客系统
 * 该项目采用后端采用springBoot+springDataJPA，[前端](https://github.com/ZemelZhu/blog-vue-cli)由vue-cli构建项目，开发时前后端跨域由axios处理，发行时由nginx处理跨域问题！前端ui为elementUI
 * 文件上传和邮箱服务依赖自己的另外一个[springboot项目](https://github.com/ZemelZhu/FileupLoad)（该项目接受文件上传到第三方图床七牛云并返回外链，邮箱使用自己的qq邮箱代理转发）
 * 文章编辑采用第三方vue quill editor富文本编辑器，实现图文混排
 * 该项目同时还有Android版本，项目采用okhttp-OkGo+AgentWeb+android-Ultra-Pull-To-Refresh+fresco等第三方库，实现web同样的功能
## 预览（web）
 * [http://120.78.164.110](http://120.78.164.110)
 
## 运行
 * 导入项目，在修改application.yml下的数据库连接，创建数据库即可

