<template>
    <div class="comment">
      <hr>
      <router-link :to="{path:'/usermessage/'+ownerUser.uid}"  class="router">
        <p class="infoauthor">{{ownerUser.name}}:</p>
      </router-link>

      <router-link :to="{path:'/usermessage/'+targetUser.uid}" class="router">
        <p class="infoauthor">@{{targetUser.name}}</p>
      </router-link>

      <p class="content">{{secondComment.content}}</p>
      <br>
      <div class="secondComment">
      <span class="message">
      {{secondComment.createdAt}}</span>
      <a @click="reply" href="javascript:void (0);">
        <!--<img src="@/assets/images/reply.png" class="reply">-->
        <!--<el-collapse accordion>-->
        <span class="message"><i class="el-icon-edit-outline"></i>回复</span>
      </a>
      <el-row :gutter="20" v-show="!isShow">
        <el-col :xs="18" :sm="20" :md="20" :lg="20" :xl="21">
          <el-input  placeholder="请输入回复内容" v-model="content" ></el-input>
        </el-col>
        <el-col :xs="2" :sm="1" :md="2" :lg="2" :xl="2">
          <el-button type="danger"  @click="replyComment" >评论</el-button>
        </el-col>
      </el-row>
      </div>

    </div>
</template>

<script>
    export default {
      data() {
        return {
          ownerUser:[],
          targetUser:[],
          content:"",
          isShow:true
        }
      },
      methods: {
        reply(){
          this.isShow = !this.isShow;
        },
        replyComment(){
          this.axios.post("/Blog/intercepter/newChildComment", {
            content: this.content,
            parentId: this.secondComment.parentId,
            targetUserId: this.secondComment.ownerUserId
          }).then((response)=>{

              if (response.status != 201&&response!="") {
              this.$message({
                message: '回复成功',
                type: 'success'
              });
                this.$emit('refreshList');
                this.content="";
                this.isShow = !this.isShow;
            }
          });

        }
      },
      props:['secondComment'],
      mounted() {
        this.axios.post('/Blog/User/getUserMessageById',{
          userId:this.secondComment.ownerUserId
        })
          .then((response)=>{
            this.ownerUser = response.data;
          });
        this.axios.post('/Blog/User/getUserMessageById',{
          userId:this.secondComment.targetUserId
        })
          .then((response)=>{
            this.targetUser = response.data;
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
  .infoauthor {
    font-size: 13pt;
    /*white-space:nowrap;*/
  }

  .router {

    display:inline-block;
  }

  .content {
    display:inline-block;
    font-size: 13pt;
  }
  .reply {
    margin-left: 2%;
    width: 25px;
  }
  .message {
    margin-left: -1px;
    font-size: 10pt;
    margin-top: -10%;
  }

  .secondComment {
    margin-top: -3%;
  }

  .comment {
    margin-top: 0%;
    margin-bottom: -1%;
    margin-left: 2%;
  }
  a:visited {
    font-size: 12px;
    color: #3194d0;
    text-decoration: none;
  }
</style>
