<template>
  <el-card class="el-card">
    <el-row>
      <el-col :xs="24" :sm="15" :md="15" :lg="15" :xl="15" >
        <div >
          <router-link :to="{path:'/usermessage/'+author.uid}">
            <el-tooltip :content="author.state" placement="top" effect="light">
            <img :src="author.avatar" class="authorAvatar">
            </el-tooltip>
            <p class="infoauthor">{{author.name}}</p>
          </router-link>
            <p class="time">{{note.time}}</p><br>
          <router-link :to="{path:'/'+url+'/'+note.articleId}" >
          <h5>{{note.title}}</h5>
          </router-link>
          <br>
          <p class="content">{{note.abstractContent}}</p><br>
          <div class="iconfont">
            <el-row>
              <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                <!--<span class="message"><i class="el-icon-edit-outline"></i>回复</span>-->
              </el-col>
            </el-row>
          </div>
        </div>
      </el-col>
      <el-col :xs="0" :sm="8" :md="8" :lg="8" :xl="6" :offset="1">
        <div>
     <router-link :to="{path:'/'+url+'/'+note.articleId}"  >
       <img :src="note.imgUrl" class="image">
     </router-link>
        </div>

      </el-col>
      <el-col :xs="1" :sm="1" :md="1" :lg="1" :xl="1">
        <p></p>
      </el-col>
    </el-row>
  </el-card>

</template>
<script>
  export default {

    data() {

      return {
        author: {
          uid:1,
          name:'',
          avatar:'',
          state:''
        },
      };
    },
    props: ['note','url'],
    created:function () {
      this.axios.post('/Blog/User/getUserMessageById',{
        userId:this.note.authorId
      })
        .then((response)=>{
          this.author = response.data;
        });
    }
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
