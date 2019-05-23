<template>
  <div>
    <el-row>
      <el-col :xs="4" :sm="12" :md="4" :lg="6" :xl="6">
        <div class="card2">
          <a v-for="(user,index) in userList" :key="index" v-on:click="toggle(user)" >
              <friend :user="user" :me="messageList[index]" :sid="sid"></friend>
          </a>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :md="12" :lg="18" :xl="18">
        <chat-page :content-list="chatBox" :my="my" :it="it"></chat-page>
      </el-col>

    </el-row>
  </div>
</template>

<script>
  import chatPage from './chatPage';
  import msg from '@/proto/CommonMsgPB_pb';
  import friend from './friendList'
  import navBar from '../page/navbar';

  export default {
    data() {
      return {

        my: '',
        it: '',
        msg: '',
        items: ["3", 666],
        mp: new Map(),
        meindex: new Map(),
        userList: [],
        chatBox: [],
        messageList: [],
        sid:''
      }
    },
    components: {
      chatPage,
      friend,
      navBar
    },
    mounted() {
      this.initSocket();
    },
    methods: {
      initSocket() {
        this.axios.post('/Blog/Login/intercepter/getLogin'
        ).then((response) => {
          if (response.data != "") {
            this.socketApi.initWebSocket(response.data);
            this.my = response.data;
            this.socketApi.setController(this.controller);
            this.socketApi.setdisConnection(this.disconnect);
            this.getFriendsList();
          }
          else {
            this.$router.push({path: '/sign/in'});
          }
        });
      },
      controller(e) {
        let bb = new msg.CommonMsgPB.deserializeBinary(e);
        switch (bb.getType()) {
          case 0:
            this.analyzeOneMessage(bb);
            break;
          case 3:
            this.getFriendsList();
            break;
          case 4:
            console.log("disconnect");
            this.disconnect();
            break;
        }
      },
      getFriendsList() {
        this.axios.get('/Blog/Message/AllRelation'
        ).then((response) => {
          this.mp.clear();
          this.userList = [];
          for (let u of response.data) {
            this.analyzeMeeage(u);
          }
        });
      },
      disconnect() {
        this.$router.push("/sign/in");
      },
      analyzeOneMessage(m) {
        let me = new Object();
        me.fromId = m.getFromid();
        me.toId = m.getToid();
        me.content = m.getContent();
        me.time = m.getTime();
        me.type = m.getContenttype();
        let id = me.fromId;
        if (id == this.my.uid)
          id = me.toId
        let list = this.mp.get(id);
        console.log(list);
        list.push(me);
        let index = this.meindex.get(id);
        this.messageList[index] = me;
      },
      analyzeMeeage(m) {
        let u = new Object();
        u.id = m.id;
        u.name = m.name;
        u.avatar = m.avatar;
        this.userList.push(u);

        if (m.mList != null) {
          this.mp.set(m.id, m.mList);
          let messageMap = m.mList;
          this.messageList.push(messageMap[messageMap.length - 1]);
          this.meindex.set(m.id, this.messageList.length - 1);
          if(this.sid=='')
          {
            this.sid = m.id;
            this.chatBox = m.mList;
            this.it = u;
          }
        }
      },

      toggle(user) {
        this.sid = user.id;
        let index = user.id;
        this.it = user;
        this.chatBox = this.mp.get(index);
      }
    }
  }
</script>

<style scoped>
  .card2 {
    height: 610px;
    overflow: auto;
  }
</style>
