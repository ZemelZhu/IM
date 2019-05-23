<template>


  <el-row>
    <el-col :xs="1" :sm="1" :md="4" :lg="4" :xl="4">
      <div><p></p></div>
    </el-col>
    <el-col :xs="24" :sm="24" :md="15" :lg="15" :xl="15">
      <div>
        <card v-for="note in noteList" :key="note.id" :note="note" :url="articleUrl"></card>
        <el-button @click="loadMore" type="info" round class="loadMore">加载更多</el-button>
      </div>
    </el-col>
  </el-row>


</template>

<script>
  import Card from './Card'
  export default {
    data() {
      return {
        noteList: [],
        articleUrl:'chat/article',
        page:0
      }
    },
    components: {
      Card
    },
    methods:{
      loadMore() {
        this.axios.post('/Blog/Note/getNoteByPage',{
          page: this.page
        }).then((response) => {
          if(response.data.length==0) {
            this.$message({
              message: '已经没有更多文章了',
              type: 'warning'
            });
            return ;
          }
          for(let note of response.data) {
            this.noteList.push(note);
          }
          this.page++;
        });
      }
    },
    created: function () {
      this.loadMore();
    }

  }
</script>

<style scoped>

  .loadMore {
    margin-top: 1%;
    /*margin-left: 40%;*/
    width: 100%;
  }
</style>
