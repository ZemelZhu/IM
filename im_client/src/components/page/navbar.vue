<template>
  <div>

    <el-menu :default-active="activeIndex"
             class="el-menu-demo"
             background-color="#3f3f3f"
             text-color="#fff"
             active-text-color="#ffd04b"
             mode="horizontal"
             @select="handleSelect">
      <el-row>
        <el-col :xs="0" :sm="4" :md="4" :lg="4" :xl="4">
          <p></p>
        </el-col>
        <el-col :xs="8" :sm="4" :md="3" :lg="3" :xl="2">
          <el-menu-item index="8">
            <img src="@/assets/IM.png" height="100%"/>
          </el-menu-item>
        </el-col>
        <el-col :xs="0" :sm="3" :md="2" :lg="2" :xl="2">
          <router-link to="/chat/friendManagement">
            <el-menu-item index="1" class="one">
              管理
            </el-menu-item>
          </router-link>
        </el-col>
        <el-col :xs="0" :sm="3" :md="2" :lg="2" :xl="2">
          <router-link to="/chat/mainBody">
            <el-menu-item index="2" class="one">消息</el-menu-item>
          </router-link>
        </el-col>
        <el-col :xs="0" :sm="3" :md="2" :lg="2" :xl="2">
          <router-link to="/chat/noteList">
            <el-menu-item index="3" class="one">文章</el-menu-item>
          </router-link>
        </el-col>
        <el-col :xs="7" :sm="2" :md="6" :lg="6" :xl="6">

          <p></p>
        </el-col>
        <el-col :xs="1" :sm="0" :md="0" :lg="0" :xl="0">
          <el-button type="success" class="write" @click="download">手机下载</el-button>

        </el-col>

        <el-col :xs="0" :sm="4" :md="4" :lg="4" :xl="3">
          <router-link to="/sign/in" v-show="isShow">
            <el-button type="success" class="write">登陆</el-button>
          </router-link>
          <span v-show="!isShow">
            <!--<el-dropdown>-->

            <!--<router-link to="/chat/message">-->

       <el-submenu index="4">

    <template slot="title">

      <img v-bind:src="avatarUrl" class="useravatar"/>
      {{userName}}
      </template>

         <router-link to="/chat/editor">
          <el-menu-item index="4-1">写文章</el-menu-item>
         </router-link>
         <span @click="dialogFormVisible = true">
    <el-menu-item index="4-2">添加好友</el-menu-item>
         </span>
         <router-link :to="{path:'/usermessage/'+userId}">
    <el-menu-item index="4-3">我的文章</el-menu-item>
         </router-link>
         <router-link to="/chat/message"><el-menu-item index="4-4">我的信息</el-menu-item></router-link>
<span @click="signUp">
  <el-menu-item index="4-5">注销</el-menu-item>
</span>
  </el-submenu>
            <!--</router-link>-->

          </span>

          <!--<el-button type="text" class="write"  size="medium" disabled="true">{{userName}}</el-button>-->
        </el-col>


      </el-row>
    </el-menu>


    <el-dialog title="添加好友" :visible.sync="dialogFormVisible">
      <el-form :rules="rules2" :model="ruleForm2" ref="ruleForm2">
        <el-form-item label="好友ID" label-width="120px" prop="number">
          <el-input v-model.number="ruleForm2.number" placeholder="请输入ID"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="warning" @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addNewFriend('ruleForm2')">确 定</el-button>
      </div>
    </el-dialog>


  </div>

</template>

<script>
  export default {
    data() {

      return {
        activeIndex: '2',
        avatarUrl: '',
        isShow: true,
        userId: '-1',
        userName: '',
        dialogFormVisible: false,
        ruleForm2: {
          number: ''
        },
        rules2: {
          number: [
            {required: true, message: 'ID不能为空'},
            {type: 'number', message: 'ID必须为数字值'},
          ]
        }
      };
    },

    beforeDestroy() {
      this.socketApi.websocketclose();
    },
    mounted() {
      this.axios.post('/Blog/Login/intercepter/getLogin')
        .then((response) => {
          // console.log("="+response.data+"=");
          if (response.data == "") {
            this.isShow = true;
            this.$router.push({path: '/sign/in'});
          }
          else {
            this.avatarUrl = response.data.avatar;
            this.userId = response.data.uid;
            this.userName = response.data.name;
            this.isShow = false;
          }

        }).catch((error) => {
        this.$router.push({path: '/error'});
      });

    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      download() {
        window.open("http://p7zfrx5uc.bkt.clouddn.com/BlogAndroid.apk");
        // this.$message({
        //   message: '等待开发',
        //   type: 'success'
        // });
      },
      signUp() {

        this.axios.post("/Blog/Login/offLine")
          .then(response => {

            this.$message({
              message: response.data,
              type: 'success'
            });
          });
      },
      addNewFriend(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.axios.post("/Blog/Relation/addNewFriend", {
              uid: this.ruleForm2.number
            })
              .then(response => {
                if (response.data == "success") {
                  this.$message({
                    message: "添加成功",
                    type: 'success'
                  });
                  this.dialogFormVisible = false;
                  this.ruleForm2.number = "";
                }
                else {
                  this.$message({
                    message: "添加失败",
                    type: 'warning'
                  });
                }
              });
          }
        })
      }


    }

  }
</script>

<style scoped>

  .one {
    /*font-size:20px;*/
    /*font-weight:400;*/
    /*text-decoration:none;*/
  }

  .el-menu-demo {
    z-index: 999;

    width: 100%;
    margin-top: -10px;
    margin-left: -8px;
    margin-right: -8px;
    border-bottom: solid 1px;
    position: fixed;

  }

  .box-card {
    width: 50%;
    margin-left: 15%;
    padding: 0px 0px 0px 0px;
    border-width: 0px;
  }

  .write {
    margin-top: 10px;
  }

  .useravatar {
    float: left;
    border-radius: 50%;
    width: 40px;
    height: 40px;
    /*margin-bottom: 15px;*/
    margin-top: 10px;
    margin-right: 20px;
  }
</style>
