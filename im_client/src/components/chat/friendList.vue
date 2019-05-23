<template>
  <div>
    <el-card v-if="sid==user.id" class="el-card friend2">
      <el-row>
        <el-col :xs="8" :sm="8" :md="8" :lg="6" :xl="6">
          <img :src="user.avatar" class="image">
        </el-col>
        <el-col :xs="4" :sm="4" :md="12" :lg="14" :xl="14" >
          <div >
            <p class="name" v-if="user.name!=null">{{user.name}}</p>
            <p v-if="me.content!=null">{{me.content}}</p><br>
          </div>
        </el-col>
        <el-col :xs="1" :sm="1" :md="1" :lg="1" :xl="1">
          <p class="time" v-if="me.time!=null">{{me.time}}</p>
        </el-col>
      </el-row>
    </el-card>
    <el-card v-else class="el-card ">
      <el-row>
        <el-col :xs="8" :sm="8" :md="8" :lg="6" :xl="6">
          <img :src="user.avatar" class="image">
        </el-col>
        <el-col :xs="4" :sm="4" :md="12" :lg="14" :xl="14" >
          <div >
            <p class="name" v-if="user.name!=null">{{user.name}}</p>
            <p v-if="me.content!=null">{{me.content}}</p><br>
          </div>
        </el-col>
        <el-col :xs="1" :sm="1" :md="1" :lg="1" :xl="1">
          <p class="time" v-if="me.time!=null">{{me.time}}</p>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
  export default {
    props: ['user',"me","sid"]
  }
</script>

<style scoped>
  .friend2{
    background-color: #c2e7b0;
  }
  .el-card {
    margin-bottom: 10px;
    height: 90px;
    /*width: 80%;*/
  }
  .infoauthor{
    display: inline;
    /*text-align:center;*/
    font-size: 15pt;
  }
  .authorAvatar{
    border-radius:50%;
    height: 25px;
    width: 25px;
    margin-bottom: -10px;
    display: block;
  }
  .iconfont {
    /*padding-top: 3%;*/
  }
  .image {
    width: 93%;
    height: 200%;
    border-radius: 10%;
    margin-top: -10%;
  }
  h5 {
    display: inline;
    font-size: 15pt;
  }
  .time {
    margin-top: -27%;
    /*font-size: 13pt;*/
  }
  .name{
    margin-top: -6%;
    font-size: 13pt;
  }
  .content{
    margin-top: -4%;
    font-size: 10pt;
  }
  .message {
    font-size: 10pt;
  }
</style>
