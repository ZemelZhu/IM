<template>
  <div>
    <big-img v-if="showImg" @clickit="viewImg" :imgSrc="imgSrc" class="imgBig"></big-img>
    <div v-for="(mes,index) in contentList" :key="index">
      <el-row v-if="mes.toId==my.uid && mes.fromId==it.id" class="mes">
        <el-col :xs="3" :sm="1" :md="1" :lg="1" :xl="1">
          <img :src="it.avatar" class="authorAvatar">
          <br>
        </el-col>
        <el-col :xs="19" :sm="23" :md="23" :lg="22" :xl="22">
        <span class="test-div" v-if="mes.type!=1">
            {{mes.content}}
        </span>
          <span v-else class="imgDiv">
            <img :src="mes.content" class="contentImg" @click="imgClickShow(mes.content)">
            <br>
          </span>
        </el-col>
      </el-row>

      <el-row v-else-if="mes.fromId==my.uid && mes.toId==it.id" class="mes">
        <el-col :xs="17" :sm="23" :md="23" :lg="23" :xl="23">
        <span class="test2-div" v-if="mes.type!=1">
            {{mes.content}}
        </span>
          <span v-else class="imgDiv2">
            <img :src="mes.content" class="contentImg" @click="imgClickShow(mes.content)">
          </span>
        </el-col>
        <el-col :xs="3" :sm="1" :md="1" :lg="1" :xl="1">
          <img :src="my.avatar" class="authorAvatar" >
        </el-col>
      </el-row>
      <div v-else>
        {{mes}}
      </div>
    </div>

  </div>
</template>

<script>
  import BigImg from '../imageDetail';

  export default {
    data() {
      return {
        imgSrc: '',
        showImg:false,
      }
    },
    methods: {
      imgClickShow(url)
      {
        this.showImg = true;
        this.imgSrc = url;
      },
      viewImg() {
        this.showImg = false;
      },
    },
    props: ["contentList", "my", "it"],
    components: {
      'big-img': BigImg
    },
  }
</script>

<style scoped>
  .mes {
    /*height: 45px;*/
  }
  .imgBig{
  }
  .test-div {
    position: relative; /*日常相对定位*/
    border-radius: 4px;
    display: inline-block;
    padding: 5px;
    border: 1px solid #FEFFFE;
    background: #FEFFFE;
    max-width: 93%;
    word-break: break-all;
  }

  .test-div:before, .test-div:after {
    content: ""; /*:before和:after必带技能，重要性为满5颗星*/
    display: block;
    position: absolute; /*日常绝对定位*/
    top: 8px;

    border: 6px solid transparent;
    /*border-left-color: black;*/
  }

  .test-div:before {
    left: -11px;
    border-right-color: #FEFFFE;
    z-index: 1
  }

  .test-div:after {
    left: -12px;
    border-right-color: #FEFFFE;
    z-index: 0
  }

  .authorAvatar {
    border-radius: 50%;
    height: 40px;
    width: 40px;
    margin-bottom: 1px;
  }

  .test2-div {
    float: right;
    margin-right: 7px;
    position: relative; /*日常相对定位*/
    border-radius: 4px;
    display: inline-block;
    padding: 5px;
    border: 1px solid #9FE759;
    background: #9FE759;
    max-width: 93%;
    word-break: break-all;
  }

  .test2-div:before, .test2-div:after {
    content: ""; /*:before和:after必带技能，重要性为满5颗星*/
    display: block;
    position: absolute; /*日常绝对定位*/
    top: 8px;

    border: 6px solid transparent;
    border-left-color: #9FE759;
    width: 0px;
    height: 0px;
  }

  .test2-div:before {
    right: -11px;
    border-left-color: #9FE759;
    z-index: 1
  }

  .test2-div:after {
    right: -12px;
    border-left-color: #9FE759;
    z-index: 0
  }

  .imgDiv2 {
    float: right;
    margin-bottom: 20px;
    margin-top: 10px;
  }

  .imgDiv {
    float: left;
    margin-bottom: 20px;
    margin-top: 10px;
  }

  .contentImg {
    border-radius: 5%;
    height: 120px;
    width: 120px;
    margin-bottom: -10px;
  }
</style>
