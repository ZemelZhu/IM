<template>
  <div>
    <el-row>
      <el-col :xs="1" :sm="1" :md="1" :lg="6" :xl="7">
        <div><p></p></div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="10">
        <el-alert
          title="错误页面"
          type="error"
          :closable="false"
          description="访问服务器出错"
          show-icon>

        </el-alert>
        <!--<img src="@/assets/images/bo2.jpg">-->
        <div class="block">
          <!--<span class="demonstration">默认 Hover 指示器触发</span>-->
          <el-carousel >
            <el-carousel-item v-for="item in items" :key="item">
              <!--<h3>{{ item }}</h3>-->
              <img :src="item">
            </el-carousel-item>
          </el-carousel>
        </div>

      </el-col>
    </el-row>

  </div>
</template>

<script>
  export default {
    data() {
      return {
        items:[
          require('../assets/images/bo1.jpg'),
          require('../assets/images/bo2.jpg'),
          require('../assets/images/bo3.png')
        ]
      }
    }
  }
</script>

<style scoped>
  img {
    /*position: absolute;*/
    /*top: 0;*/
    width: 100%;
    height: 100%;
    border-radius: 3%;
  }

  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
