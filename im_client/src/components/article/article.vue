<template>
<div>
  <big-img  v-if="showImg" @clickit="viewImg" :imgSrc="imgSrc"></big-img>

  <el-row>

    <el-col :xs="1" :sm="1" :md="1" :lg="6" :xl="6">
      <div><p></p></div>

    </el-col>

    <el-col :xs="24" :sm="22" :md="22" :lg="12" :xl="12">


        <p class="title">{{article.title}}</p>
      <div class="author">
        <router-link :to="{path:'/usermessage/'+author.uid}"  target = _blank>
          <el-tooltip :content="author.state" placement="top" effect="light">
        <img  :src="author.avatar" class="authorAvatar" >

          </el-tooltip>
        <p class="infoauthor">{{author.name}}</p>
        </router-link>

        <br>
        <el-tag size="medium">{{article.time}}</el-tag>
        <el-badge :value=article.viewCount class="item">
        <el-button size="small">阅读量</el-button>
        </el-badge>

        <el-badge :value=article.commentNumber class="item">
        <el-button size="small">评论</el-button>
        </el-badge>

      </div>
        <br>
        <div class="ql-container ql-snow">
          <div v-html=article.content class="ql-editor" @click="articleClick($event)"></div>
        </div>
        <!--<el-button @click="articleDelete" type="warning" round class="delete">删除</el-button>-->
      <br>
    <Comment ></Comment>


    </el-col>
  </el-row>
</div>
</template>

<script>
  import Comment from './comment';
  import BigImg from '../imageDetail';
  export default {
    data() {
      return {
        showImg:false,
        imgSrc: '',
        article: {},
        author: {
          uid:'',
          name:'',
          avatar:'',
          state:''
        },
      }
    },
    components: {
      Comment,
      'big-img':BigImg
    },

    methods: {
      articleClick(event) {
        if(event.target.nodeName=='IMG') {
          if(window.screen.width<window.screen.height) {
            this.$message({
              message: '查看图片请用pc查看或者下载APP',
              type: 'warning'
            });
            return ;
          }
          this.showImg = true;
          // 获取当前图片地址
          this.imgSrc = event.target.currentSrc;
        }
      },

      viewImg(){
        this.showImg = false;
      },
      articleDelete() {
        let lid = this.$route.params.id;

        this.axios.post('/Blog/intercepter/deleteArticleById',
          {
            id: lid
          }).then((response) => {
            if(response!="") {
              this.$message({
                message: '删除成功',
                type: 'success'
              });
              this.$router.push({path: '/noteList'});
            }
            else
            {
              this.$message({
                message: '请先登录',
                type: 'warning'
              });
            }



        }).catch((error) => {
          this.$router.push({path: '/error'});
        });;

      }
    },

    created() {


    },
    mounted() {




      let lid = this.$route.params.id;
      this.axios.post('/Blog/Article/getArticleById', {
        id: lid
      }).then((response) => {



        if (response.data != "") {
          this.$nextTick( () =>{


          document.title = response.data.title;
          this.article = response.data;
          let authorId = this.article.author;
          this.axios.post('/Blog/User/getUserMessageById', {
            userId: authorId
          })
            .then((response) => {
              this.author = response.data;
            });
        });

        }
        else {
          this.$alert('不存在该文章', '错误', {
            confirmButtonText: '确定',
            showClose: false,
            roundButton: true,

            type: 'warning'

          }).then(() => {
            this.$router.push({path: '/noteList'});
          });
        }

      }).catch((error) => {
        this.$router.push({path: '/error'});
      });
    }
  }
</script>

<style scoped>

  .item {
    /*margin-top: 10px;*/
    /*margin-right: 40px;*/
  }



  .ql-container {
    border-style: none;
    background-color: #eee;
    border-radius: 1%;
  }

  .title {
    text-align: center;
    font-size: 34px;
    font-weight: 400;

  }

  /*.article {*/
    /*font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;*/
  /*}*/

  .delete {
    margin-top: 1%;
    margin-left: 40%;
    /*margin-bottom: 1%;*/
    /*width: 20%;*/
  }
  .authorAvatar{
    border-radius:50%;
    height: 40px;
    width: 40px;
    margin-bottom: -10px;
  }
  .infoauthor{
    display: inline;
    text-align:center;
    font-size: 15pt;
  }

  .author {
    margin-left: 10%;
  }

</style>
