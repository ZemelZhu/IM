<template>
    <el-row>
      <el-col :xs="0" :sm="3" :md="3" :lg="3" :xl="3">
        <p></p>
      </el-col>
      <el-col :xs="24" :sm="18" :md="18" :lg="18" :xl="18">
          <el-row :gutter="20">
            <el-col :xs="8" :sm="6" :md="5" :lg="4" :xl="2">
              <img :src="userMessage.avatar" class="image">
            </el-col>

            <el-col :xs="16" :sm="18" :md="19" :lg="20" :xl="22">

            <p v-text="userMessage.name" class="userName"></p>
            <p v-text="userMessage.uid" class="userState"></p>
            <p v-text="userMessage.email" class="userState"></p>

            </el-col>
          </el-row>

        <el-alert

          type="warning"
          :closable="false">
          个人介绍：
          {{userMessage.state}}
        </el-alert>

        <el-tabs v-model="activeName2"   class="el-tabs">
          <el-tab-pane label="文章" name="first">
            <Card v-for="note in noteList" :key="note.id" :note="note" :url="articleUrl"></Card>
            <el-button @click="loadMore" type="info" round class="loadMore">加载更多</el-button>
          </el-tab-pane>
          <el-tab-pane label="评论" name="second">
            <userComment :user="userMessage"></userComment>
          </el-tab-pane>

        </el-tabs>

      </el-col>

    </el-row>
</template>
<style scoped>
  .loadMore {
    margin-top: 1%;
    /*margin-left: 40%;*/
    width: 100%;
  }


  .box-card {
    margin-top: 10%;
  }
  .image {
    width: 100%;

    border-radius: 10%;
  }

  .userName {
    font-size: 20pt;
    margin-top: -0.1%;
  }



  .userState {
    font-size: 15pt;
    margin-top: -2%;
  }
</style>
<script>
  import Card from '../noteList/Card'
  import userComment from './userComment'

  export default {
    data() {
      return {
        activeName2: 'first',
        userMessage:{
          uid:'',
          avatar: '',
          name:'',
          state:'',
          email:''
        },
        page:0,
        noteList:[],
        articleUrl:'chat/article'


      };
    },
    methods:{
      loadMore() {
        let lid = this.$route.params.id;

        this.axios.post('/Blog/Note/getNoteByUserIdAndPage',{
          page: this.page,
          userId:lid
        }).then((response) => {
          if(response.data.length==0) {
            this.$message({
              message: '已经没有更多文章了',
              type: 'warning'
            });
            return ;
          }
          for(let note of response.data) {
            this.noteList.push(note);
          }
          this.page++;
        });
      }
    },
    components: {
      Card,
      userComment
    },
    created() {
      var sUserAgent=navigator.userAgent;

      var mobileAgents=['Android','iPhone','Symbian','WindowsPhone','iPod','BlackBerry','Windows CE'];

      var goUrl = 0;

      for( var i=0;i<mobileAgents.length;i++){

        if(sUserAgent.indexOf(mobileAgents[i]) > -1){

          goUrl = 1;

          break;

        }

      }

      if (goUrl == 1){

        this.$router.replace({path: '/usermessageByPhone/'+this.$route.params.id});

      }
    },
    mounted() {

      let lid = this.$route.params.id;
      if(lid ==-1) {
        this.$alert('请先登录', '注意', {
          confirmButtonText: '登陆',
          showClose:false

        }).then(
          ()=>{
            this.$router.push({path: '/sign/in'});
          }
        );
        return ;
      }
      this.axios.post('/Blog/User/getUserMessageById',
        {
          userId:lid
        })
        .then(response => {
          if(response.data=='') {
            this.$alert('没有此用户', '注意', {
              confirmButtonText: '返回主页',
              showClose:false

            }).then(
              ()=>{
                this.$router.push({path: '/noteList'});
              }
            );
          }
          else {
            document.title = response.data.name+"的主页";
            this.userMessage = response.data;

          }
        });
        this.loadMore();

    }
  }
</script>
