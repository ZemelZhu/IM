<template>
    <div>
      <router-link :to="{path:'/usermessage/'+user.uid}">
          <img :src="user.avatar" class="authorAvatar">
        <p class="infoauthor">{{user.name}}</p>
      </router-link>
      <p class="infoauthor">
      评论了文章
      </p>
        <router-link :to="{path:'/chat/article/'+article.id}" >
      <p class="infoauthor">{{article.title}}</p>
    </router-link>
      <router-link :to="{path:'/chat/comment/'+comment.id}" >
      <el-card shadow="always" class="card">

        <p>{{comment.content}} </p>

      </el-card>
      </router-link>
      <p class="time">{{comment.createdAt}}</p><br>
      </div>
</template>

<script>
    export default {
      props: ['comment','user'],
      data() {
        return {
          article:[]
        }
      },
      mounted() {
        // let articleId = comment.parentId;

        this.axios.post("/Blog/getArticleByComment",{
          commentId:this.comment.id
        }).then(response=>{
          this.article = response.data;
        });
      }
    }
</script>

<style scoped>
  .authorAvatar {
    border-radius: 50%;
    height: 40px;
    width: 40px;
    margin-bottom: -10px;
  }

  .infoauthor {
    margin-top: 100px;
    display: inline;
    font-size: 15pt;
    margin-right: 0.1%;
  }
  .time {
    margin-left: 10px;
    display: inline;
    font-size: 10pt;
  }
  a:visited {
    font-size: 12px;
    color: #3194d0;
    text-decoration: none;
  }



  .card {
    /*padding-bottom: -50%;*/
  }
</style>
