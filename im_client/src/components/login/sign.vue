<template>
  <div class = "sign_back">
  <el-row class="el-row">
    <el-col :xs="0" :sm="7" :md="8" :lg="9" :xl="9">
      <div  >
        <p></p>
        <!--<img src="@/assets/IM.png" class="img">-->
      </div>
    </el-col>
    <el-col :xs="24" :sm="12" :md="9" :lg="7" :xl="6">

      <el-card class="box-card">
        <img src="@/assets/IM.png" class="img">
        <div class="login">
          <el-radio  v-model="radio" label="1" >登陆</el-radio>
          <el-radio  v-model="radio" label="2" >注册</el-radio>

        </div>
        <router-view></router-view>
        <!--<router-link to="/noteList">-->
      </el-card>
    </el-col>
  </el-row>
  </div>

</template>

<script>
    export default {
      data() {
        return {
          radio: ''
        }
      },
      mounted() {
        let lid = this.$route.fullPath;
        if(lid=="/sign/up")
          this.radio = '2';
        else if(lid=="/sign/in")
          this.radio = '1';
      },
      watch:{
        radio() {
          // alert(this.radio);
          if(this.radio==1) {
            this.$router.push({path: '/sign/in'});
          }
          else {
            this.$router.push({path: '/sign/up'});
          }
        },
        '$route' (to, from) {
          let lid = this.$route.fullPath;
          if(lid=="/sign/up")
            this.radio = '2';
          else if(lid=="/sign/in")
            this.radio = '1';
        }
      }

    }
</script>

<style scoped>
  .img{
    margin-left: 25%;
  }
  .box-card {
    margin-top: 20%;


  }
  .login {
    margin-bottom:3%;
    margin-left: 30%;
  }
</style>
