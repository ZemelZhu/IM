<template>
  <div>
    <el-card id="chat" class="card1">
      <message :content-list="contentList" :my="my" :it="it"></message>
    </el-card>
    <el-card>
      <el-input type="textarea" :rows="2" placeholder="请输入回复内容" v-model="content"></el-input>

      <el-upload
        class=" fire"
        name="myFile"
        :action="url"
        :show-file-list ="false"
        :before-upload="beforeAvatarUpload"
        :on-success="handleAvatarSuccess"
      >
        <el-button type="primary ">发送图片</el-button>
      </el-upload>
      <el-button type="warning send" @click="sendMeeage">发送</el-button>
    </el-card>
  </div>
</template>

<script>
  import message from './Message';
  import msg from '@/proto/CommonMsgPB_pb';
  import global_variale from '@/api/global_variale'
  export default {
    name: "chatPage",
    data() {
      return {
        content: "",
        url:global_variale.fireUrl
      }

    },
    components: {
      message
    },
    props: ["contentList", "my", "it"],

    watch: {

      'content': 'scrollToBottom'
    },
    methods: {
      scrollToBottom: function () {

        this.$nextTick(() => {

          var div = document.getElementById('chat');

          div.scrollTop = div.scrollHeight
        })
      },
      sendMeeage() {
        this.send(0, this.content);
      },
      send(type, content) {
        let b = this.it.id;

        if (b == "" || b == null || b == undefined || b.length == 0||content=="") {
          this.$message({
            message: '警告:内容或发送人不能为空',
            type: 'warning'
          });
          return ;
        }
        let a = new msg.CommonMsgPB();
        a.setContenttype(type);
        a.setType(msg.MessageType.MSG);
        a.setToid(b);
        a.setFromid(this.my.uid);
        a.setContent(content);
        this.socketApi.sendSock(a);
        this.$nextTick(() => {
          this.content = "";
          var div = document.getElementById('chat');

          div.scrollTop = div.scrollHeight + 1000;
        })
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg'||file.type === 'image/png';
        const isLt5M = file.size / 1024 / 1024 < 5;
        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt5M) {
          this.$message.error('上传头像图片大小不能超过 5MB!');
        }
        return isJPG && isLt5M;
      },
      handleAvatarSuccess(res, file) {
        console.log("=>" + res.url);
        this.send(1, res.url);
      },
    }
  }
</script>

<style scoped>
  .card1 {
    height: 500px;
    overflow: auto;
    background-color: #E9EBE9;
  }

  .send {
    float: right
  }

  .fire {
    float: left;
  }
</style>
