<template>
  <el-row>
    <el-col :xs="1" :sm="1" :md="1" :lg="6" :xl="6">
      <div><p></p></div>
    </el-col>
    <el-col :xs="24" :sm="22" :md="22" :lg="12" :xl="12">
  <parentComment v-if="flat" :parentComment="comment"></parentComment>
    </el-col>
  </el-row>
</template>

<script>
  import parentComment from './parentComment'
    export default {
      data() {
        return {
          comment:[],
          flat:false
        }
      },
      components:{
        parentComment
      },
        mounted() {
          let lid = this.$route.params.id;
          this.axios.post("/Blog/getParentCommentById",{
            commentId:lid
          }).then(response=>{
            this.comment = response.data;
            this.flat=true;
          });
        }
    }
</script>

<style scoped>

</style>
