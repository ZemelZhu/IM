import global_variale from '../api/global_variale'

let websock = null;
let controll = null;
let disconn = null;
let data = null;
let timer = null;
let ping = 0;
import msg from '@/proto/CommonMsgPB_pb';

function getWebIP() {
  var curIP = window.location.hostname;
  return curIP;
}

function sendLogin() {
  let a = new msg.CommonMsgPB();
  let b = new msg.User();
  b.setUid(data.uid);
  b.setPassword(data.password);
  a.setType(msg.MessageType.LOGIN);
  a.setUser(b);
  sendSock(a);
  console.log("登陆发送成功" + b.getUid());
}

function initWebSocket(e) { //初始化weosocket
  //ws地址
  data = e;
  var wsuri = "ws://" + global_variale.host + ":5000/websocket";
  if (websock == null || websock.readyState != websock.OPEN) {
    websock = new WebSocket(wsuri);
    if (timer != null)
      clearInterval(timer);
    timer = setInterval(() => {
      ping++;
      if (ping >= 10) {
        let a = new msg.CommonMsgPB();
        a.setType(msg.MessageType.PING);
        console.log("发送心跳");
        sendSock(a);
      }

    }, global_variale.pong);

    console.log(wsuri + "连接成功");
  }
  websock.onmessage = function (e) {
    websocketonmessage(e);
  }
  websock.onclose = function (e) {
    websocketclose(e);
  }
  websock.onopen = function () {
    websocketOpen();
  }
  websock.onerror = function (e) {
    websocketclose(e);
  }
  //连接发生错误的回调方法
  websock.onerror = function () {
    console.log("WebSocket连接发生错误");
  }
}

// 实际调用的方法
function sendSock(agentData) {
  // global_callback = callback;
  if (websock == null) {
    alert("websocket没有建立");
    return;
  }
  if (websock.readyState === websock.OPEN) {
    //若是ws开启状态
    websocketsend(agentData);
  } else if (websock.readyState === websock.CONNECTING) {
    // 若是 正在开启状态，则等待1s后重新调用
    setTimeout(function () {
      sendSock(agentData);
    }, 1000);
  } else {
    alert("发送失败");
    disconn();
  }
}

//数据接收
function websocketonmessage(e) {
  var reader = new FileReader();
  reader.readAsArrayBuffer(e.data);
  reader.onload = function (ee) {
    var buf = new Uint8Array(reader.result);
    controll(buf);
  }
}

//数据发送
function websocketsend(data) {
  ping = 0;
  websock.send(data.serializeBinary());
}

//关闭
function websocketclose(e) {
  if (websock != null) {
    console.log("socket断开连接");
    clearInterval(timer);
    websock.close();
    disconn();
  }

}

function websocketOpen() {
  console.log("连接成功");
  sendLogin();
  // alert("登陆成功");
}

function setController(controller) {
  controll = controller;
}

function setdisConnection(disconnect) {
  disconn = disconnect;
}

export {sendSock, setController, setdisConnection, initWebSocket, websock, websocketclose}
