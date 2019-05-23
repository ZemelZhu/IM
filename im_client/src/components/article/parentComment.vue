<template>
  <div>
    <router-link :to="{path:'/usermessage/'+author.uid}">
      <el-tooltip :content="author.state" placement="top" effect="light">
        <img :src="author.avatar" class="authorAvatar">
      </el-tooltip>
      <p class="infoauthor">{{author.name}}</p>

    </router-link>
    <p class="time">{{parentComment.createdAt}}</p><br>
    <p class="content">{{parentComment.content}}</p>
    <div class="parentComment">
      <a @click="praise" href="javascript:void (0);">
        <!--<img src="@/assets/images/praise.png" class="praise">-->
        <span class="message"><i class="el-icon-star-on"></i>
          <span>{{parentComment.likeCount}}
        </span>人赞</span>
      </a>
      <a @click="reply" href="javascript:void (0);">
        <!--<img src="@/assets/images/reply.png" class="reply">-->
        <!--<el-collapse accordion>-->
        <span class="message"><i class="el-icon-edit-outline"></i>回复</span>
      </a>
      <el-row :gutter="20" v-show="!isShow">

        <el-col :xs="18" :sm="20" :md="20" :lg="20" :xl="21">
          <el-input placeholder="请输入回复内容" v-model="content"></el-input>
        </el-col>
        <el-col :xs="2" :sm="1" :md="2" :lg="2" :xl="2">
          <el-button type="info" @click="replyComment">评论</el-button>
        </el-col>
      </el-row>
    </div>
    <secondComment v-for="second in secondComments"
                   :key="second.id" :secondComment="second"
                   @refreshList="refreshList">

    </secondComment>
    <hr >
  </div>
</template>

<script>
  import secondComment from './secondComment'

  export default {
    data() {
      return {
        author: {},
        isShow: true,
        content: "",
        secondComments: []
      }
    },
    components: {
      secondComment
    },
    methods: {
      refreshList() {
        this.axios.post('/Blog/getCommentListById', {
          commentId: this.parentComment.id
        }).then((response) => {
          this.secondComments = response.data;
        });
      },
      praise() {
        this.axios.post("./Blog/intercepter/commentAddPraise",
          {
            commentId: this.parentComment.id
          })
          .then((response) => {
            // alert(response.data);
            if (response.data == "1")
              this.parentComment.likeCount++;
            else
              this.parentComment.likeCount--;
            // console.log(response.data+"==?");
          });
      },
      reply() {
        // let i =this.
        this.isShow = !this.isShow;
      },
      replyComment() {
        this.axios.post("/Blog/intercepter/newChildComment", {
          content: this.content,
          parentId: this.parentComment.id,
          targetUserId: this.parentComment.ownerUserId
        }).then((response) => {
          if (response.status !=201 &&response!="") {
            this.$message({
              message: '回复成功',
              type: 'success'
            });


            this.refreshList();
            this.content="";
            this.isShow = !this.isShow;
          }
        });

      }
    },
    props: ['parentComment'],
    mounted() {
      this.axios.post('/Blog/User/getUserMessageById', {
        userId: this.parentComment.ownerUserId
      })
        .then((response) => {
          this.author = response.data;
        });
      // alert(this.parentComment.id);
      this.axios.post('/Blog/getCommentListById', {
        commentId: this.parentComment.id
      }).then((response) => {
        this.secondComments = response.data;
      });

    }
  }
</script>

<style scoped>
  hr{
    height:1px;
    border:none;
    border-top:1px dashed #eee;
  }
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
  }

  .time {
    margin-left: 10px;
    display: inline;
    font-size: 10pt;
  }

  .content {
    font-size: 15pt;
    margin-top: 1%;
  }

  .praise {
    width: 15px;

  }

  .message {
    margin-left: 1px;
    font-size: 10pt;
    margin-top: -10%;
  }

  .reply {
    margin-left: 2%;
    width: 25px;
  }

  .parentComment {
    margin-top: -2%;
  }
  a:hover {
    font-size: 12px;
    color: #999999;
    text-decoration: none;
  }
</style>
