<template>
  <div>
    <br>
    <el-tabs v-model="tabsIndex" tab-position="left" style="height: 580px;">
      <el-tab-pane label="好友管理" name="first">
        <el-row>
          <el-col :xs="3" :sm="3" :md="3" :lg="2" :xl="2">
            <p></p>
          </el-col>
          <el-col :xs="18" :sm="18" :md="18" :lg="20" :xl="20">
            <el-table
              :data="friendList"
              border
              style="width: 100%">
              <el-table-column
                label="好友ID"
                width="180">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.uid }}</span>
                </template>
              </el-table-column>
              <el-table-column
                label="昵称"
                style="width: 100%">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.name }}</span>
                </template>
              </el-table-column>
              <el-table-column label="操作" style="width: 100%">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="primary"
                    @click="handleEdit(scope.$index, scope.row)">查看详细信息
                  </el-button>

                  <el-button
                    size="mini"
                    type="danger"
                    @click="handleDelete(scope.$index, scope.row)">删除好友
                  </el-button>
                  <el-button
                    size="mini"
                    type="success"
                    @click="readMessage(scope.$index, scope.row)">查看消息记录
                  </el-button>
                  <router-link :to="{path:'/usermessage/'+scope.row.uid}">
                    <el-button
                      size="mini"
                      type="info"
                    >
                      查看个人空间
                    </el-button>
                  </router-link>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-row>
        <br>
        <el-pagination
          class="page"
          background
          layout="prev, pager, next"
          @current-change="handleCurrentChange1"
          :total="friendPage">
        </el-pagination>

      </el-tab-pane>
      <el-tab-pane label="文章管理" name="second">

        <el-row>
          <el-col :xs="3" :sm="3" :md="3" :lg="2" :xl="2">
            <p></p>
          </el-col>
          <el-col :xs="18" :sm="18" :md="18" :lg="20" :xl="20">
            <el-table
              :data="noteList"
              border
              style="width: 100%">
              <el-table-column
                label="文章ID"
                width="180">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.id }}</span>
                </template>
              </el-table-column>
              <el-table-column
                label="标题"
                style="width: 100%">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.title }}</span>
                </template>
              </el-table-column>
              <el-table-column label="操作" style="width: 100%">
                <template slot-scope="scope">
                  <router-link :to="{path:'/chat/article/'+scope.row.id}">
                  <el-button
                    size="mini"
                    type="primary"
                   >查看
                  </el-button>
                  </router-link>
                  <el-button
                    size="mini"
                    type="danger"
                    @click="handleArticleDelete(scope.$index, scope.row)">删除
                  </el-button>
                  <el-button
                    size="mini"
                    type="info"
                    disabled
                  >修改
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-row>
        <br>
        <el-pagination
          class="page"
          background
          layout="prev, pager, next"
          @current-change="handleCurrentChange"
          :total=notePage>
        </el-pagination>
      </el-tab-pane>
    </el-tabs>


    <el-dialog title="消息记录" :visible.sync="dialogFormVisible"
               width="75%">
      <el-card class="mes">
        <message :content-list="contentList" :my="my" :it="it"></message>
      </el-card>
      <div slot="footer" class="dialog-footer">
      </div>
    </el-dialog>
    <el-dialog title="个人信息" :visible.sync="dialogFormVisible2"
               width="50%">
      <user-message :user="it"></user-message>
      <div slot="footer" class="dialog-footer">
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import message from '../chat/Message';
  import userMessage from './user-Message';

  export default {
    data() {
      return {
        friendList: [],
        dialogFormVisible: false,
        dialogFormVisible2: false,
        my: '',
        it: '',
        contentList: [],
        noteList:[],
        tabsIndex:'second',
        friendPage:10,
        notePage:10,
        currentPage:0
      }
    },
    watch:{
      'tabsIndex':(newVal)=>{
        if(newVal=="first")
          this.allPage = 20;
        else
          this.allPage = 40;
      }
    },
    components: {
      message,
      userMessage
    },
    methods: {

      handleEdit(index, row) {
        this.axios.post("/Blog/User/Message", {
          uid: row.uid
        }).then((response) => {
          this.it = response.data;
          this.dialogFormVisible = false;
          this.dialogFormVisible2 = true;
        });
      },
      handleArticleDelete(index, row){
        this.axios.post("/Blog/Article/deleteArticleById", {
          id: row.id
        }).then((response) => {
          let status = response.data;
          if (status == "success") {
            this.$message({
              message: '删除成功',
              type: 'success'
            });
            this.noteList.splice(index, 1);
          } else {
            this.$message({
              message: '删除失败',
              type: 'error'
            });
          }
        });
      },
      handleDelete(index, row) {
        this.axios.post("/Blog/Friend/deleteFriend", {
          fid: row.uid
        }).then((response) => {
          let status = response.data;
          if (status == "success") {
            this.$message({
              message: '删除成功',
              type: 'success'
            });
            this.friendList.splice(index, 1);
          } else {
            this.$message({
              message: '删除失败',
              type: 'error'
            });
          }
        });
      },
      readMessage(index, row) {
        this.axios.post('/Blog/Message/record', {
          fid: row.uid
        }).then((response) => {
          let m;
          for (let u of response.data) {
            m = u;
            break;
          }
          let u = new Object();
          u.id = m.id;
          u.name = m.name;
          u.avatar = m.avatar;
          this.it = u;
          this.contentList = m.mList;
          this.dialogFormVisible2 = false;
          this.dialogFormVisible = true;
        });
      },
      handleCurrentChange(val) {
        let id = this.my.uid;
        this.axios.post("/Blog/Note/getNoteByUserIdAndPage",{
          userId:id,
          page:val-1
        }).then((response) => {
          this.noteList = response.data;
        });
      },
      handleCurrentChange1(val){
        this.axios.post("/Blog/Friend/AllFriedByPage",{
          page:val-1
        }).then((response) => {
          this.friendList = response.data;
        });
      }
    },

    mounted() {
      this.axios.post('/Blog/Login/intercepter/getLogin'
      ).then((response) => {
        console.log(response);
        if (response.data != "") {
          this.my = response.data;
          let id = this.my.uid;
          this.axios.post("/Blog/Note/getNoteByUserIdAndPage",{
            userId:id,
            page:0
          }).then((response) => {
            this.noteList = response.data;
          });
        }
        else {
          this.$router.push({path: '/sign/in'});
        }
      });
      this.axios.post("/Blog/UserInfo/userInfo").then((response) => {
          console.log(response.data);

        this.notePage = response.data.noteCount;
        this.friendPage = response.data.friendCount;
        }

      );
      this.axios.post("/Blog/Friend/AllFriedByPage",{
        page:0
      }).then((response) => {
        this.friendList = response.data;
      });


    }
  }
</script>
<style scoped>
  .mes {
    background-color: #E9EBE9;
  }
  .page{
    margin-left: 40%;
  }
</style>
