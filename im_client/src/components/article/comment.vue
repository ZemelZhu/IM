<template>
    <div>
      <el-row :gutter="20">
        <el-col :xs="18" :sm="20" :md="20" :lg="20" :xl="21">
      <el-input  placeholder="请输入评论内容"  v-model="myContent"></el-input>
        </el-col>
        <el-col :xs="2" :sm="1" :md="2" :lg="2" :xl="2">
      <el-button type="primary"  @click="submit">评论</el-button>

        </el-col>

      </el-row>
<hr>
      <parentComment v-for="pComment in comment" :key="pComment.id" :parentComment="pComment"></parentComment>
<br>
    </div>
</template>

<script>
  import parentComment from './parentComment'
    export default {
        data() {
          return {
            myContent:'',
            articleId:'',
            comment: []
          }
        },
      components:{
        parentComment
      },
      methods:{
          submit() {
            this.axios.post('/Blog/intercepter/newComment',{
              parentArticle:this.articleId,
              content: this.myContent

            }).then((response)=>{
              if(response.status!=201&&response!="") {
                // console.log(response.data);
                this.$message({
                  message: response.data,
                  type: 'info'
                });
                this.myContent='';
                this.axios.post("/Blog/getAllComment", {
                  articleId: this.articleId
                }).then((response) => {


                  this.comment = response.data;
                });
              }

            });
          }
      },

      mounted() {
        this.articleId = this.$route.params.id;

          this.axios.post("/Blog/getAllComment",{
            articleId:this.articleId
          }).then((response)=>{
              this.comment=response.data;
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
</style>
