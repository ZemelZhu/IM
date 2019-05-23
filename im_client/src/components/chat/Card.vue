<template>
  <el-card class="el-card">
    <el-row>
      <el-col :xs="24" :sm="15" :md="15" :lg="15" :xl="15" >
   123
      </el-col>
      <el-col :xs="0" :sm="8" :md="8" :lg="8" :xl="6" :offset="1">

      </el-col>
      <el-col :xs="1" :sm="1" :md="1" :lg="1" :xl="1">
        <p></p>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
    export default {
        name: "Card"
    }
</script>

<style scoped>
  .el-card {
    margin-bottom: 10px;
  }
  .infoauthor{
    display: inline;
    /*text-align:center;*/
    font-size: 15pt;
  }
  .authorAvatar{
    border-radius:50%;
    height: 40px;
    width: 40px;
    margin-bottom: -10px;
    /*display: block;*/
  }
  .iconfont {
    /*padding-top: 3%;*/
  }
  .image {
    width: 80%;
    height: 200%;
    border-radius: 10%;
    margin-top: 15%;
  }
  h5 {
    display: inline;
    font-size: 15pt;
  }
  .time {
    display: inline;
    font-size: 10pt;
  }
  .content{
    display: inline;
    font-size: 15pt;
  }
  .message {
    font-size: 10pt;
  }
</style>
