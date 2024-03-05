<template>
  <div style=" height:570px; width:980px" id="main">
    <el-row>
      <div class="el-header " id="windowHead" style="text-align: right">
        <el-button type="infer" size="mini"  icon="el-icon-minus" id="minusButton" @click="minus" circle></el-button>
        <el-button type="infer" size="mini"  icon="el-icon-rank" id="magnifyButton" @click="magnify" circle></el-button>
        <el-button type="danger" size="mini"  @click="closeChooser"  icon="el-icon-close" circle></el-button>
      </div>
    </el-row>
    <el-row>
      <el-col :span="24">
        <div class="el-container" >
          <el-row style="width: 100%">
            <el-col :span="15">
              <div class="mainContainer">
                <el-row><div class="dataContainer" id="dataContainer">
                  <h1 style="text-align: center">洪水复盘</h1>
                  <h4 style="text-align: left;text-indent: 2em;line-height: 19px" >1985 年暴雨洪水。石梁河水库上游及连云港市区，受地形影响，容易形成局部暴雨。1985 年 9 月 1 日夜，连云港港区发生了一场历史罕见的局部特大暴雨，连云区墟沟最大 24h 降水达 597.5mm，最大 7h降水量 536.5mm。此次降雨范围很小，约 14km2。临洪站距离墟沟站直线距离  km，降水量仅 6mm。2012 年暴雨与 1985 年暴雨成因类似，主要是东侧海上暖湿气流和冷空气交汇于连云港地区，受地形等影响，形成上干冷下暖湿的不稳定层结，产生强烈的上升运动所形成。</h4>
                  <h4 style="text-align: left;text-indent: 2em;line-height: 19px" >2000 年暴雨洪水。石梁河水库地处我国南北方气候过渡地带，天气现象变化剧烈，又易受台风影响，产生特大暴雨。2000 年 8 月 30 日，12 号强台风“派比安”登陆盐城市北部沿海，8 月 30 日 2 时～31 日 21 时响水县城（距离连云港城区 50km）降雨 830.9mm，最大 24h 降雨量达 824.7mm，为江苏省有雨量记录以来最大；连云港市城区临洪站最大 24h 降雨 239.5mm。2012 年，临洪站最大 24h 降雨量为 304.2mm，大于 2000 年。但 2012 年临洪站降水量远小于 2000 年响水站降水量。</h4>
                  <h4 style="text-align: left;text-indent: 2em;line-height: 19px" >2012年暴雨洪水。2012 年 7 月 8 日 4 时～9 日 4 时，受副热带高压外围强盛的西南暖湿气流和北方冷空气的共同影响，连云港市出现特大暴雨天气，暴雨中心位于连云港市城区附近，主城区平均降雨量为 327 mm，城区周边最大点雨量为凤凰嘴站 420mm。根据临洪站降雨资料分析，降雨自 7 月 8 日 4 时开始，6 时出现第一个雨峰，1 小时降雨量为 27mm。后降雨强度逐渐减弱，18 时降雨基本停止。但随后降雨强度加大，22 时、23时和 24 时是降雨强度最大的 3 个小时，3 小时累计降雨量为 150mm，其中 23 时降雨量达到59mm。之后降雨减弱，至 7 月 9 日 6 时降雨基本结束。24 小时降雨总量 304.2mm。
                    2012年7月8日~9日，连云港受灾人口154.62 万人，其中城区受灾人口 55 万人，被水围困人数 0.51 万人，紧急转移 1.12 万人；城区直接经济损失达 15.41 亿元，造成全市直接经济损失达 27.50 亿元。</h4>
                  <h4 style="text-align: left;text-indent: 2em;line-height: 19px" >2012 年暴雨特点主要表现为三点。一是暴雨范围小，自城区向周边递减较快；二是暴雨表现为两个雨峰，而且后一个雨峰远远大于前一个雨峰，这是对城市防洪最不利的组合；三是地形抬升对产生暴雨的作用明显，云台山主峰海拔 624.4m，周围平原区地面高程 5 米左右，暴雨中心在云台山。 根据连云港市区临洪站 1965～2012 年降雨资料计算 2012 年 7 月暴雨重现期。临洪站最大 24h 降雨量为 304.2mm，重现期为 67 年；最大 12h 降雨量为 196.6mm，重现期为 24 年；最大 6h 降雨量为 174.6mm，重现期为 25 年。
                    在临洪站 1965～2012 年降雨资料中，2012 年最大 24h 降水量排在历史第 1 位；最大 12h降雨量排在历史第 2 位；最大 6h 降雨量排在历史第 3 位。</h4>


                </div></el-row>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="tableContainer" style="width: 10px" id="tableContainer">
              </div>
            </el-col>
          </el-row>

        </div>
      </el-col>
    </el-row>


  </div>
</template>

<script>
  import  "../assets/js/jquery.table2excel.min.js"
  import RiverInfoTable from "./waterInfoTable/RiverInfoTable";
  export default {
    name: "waterInfo",
    components:{
      RiverInfoTable
    },
    methods:{
      loadSTInfo:function(){
        this.axios.get("http://localhost:8083/waterInfo/RSVR/ST").then(
          response=>{
            console.log(response.data);
            this.searchParameter.options=response.data;
          }
        );
      },
      //初始Echarts
      initEcharts:function(){
        let _this=this;
        this.myChart=this.$echarts.init(document.getElementById("echartsContainer"));
        this.myChart.setOption(option);
      },

      //关闭窗口
      closeChooser:function () {
        this.$emit('closeChooser')
      },
      //销毁
      removeEchart:function () {
        this.myChart.dispose();
        this.myChart=null;
      },

      //放大
      magnify:function () {
        let main =document.getElementById("main");

        main.style.height="100%";
        main.style.width="100%";
        main.style.left='0px';
        main.style.top='0px';
        document.getElementById("echartsContainer").style.height='500px';
        this.myChart.resize();
        document.getElementById("tableContainer").style.height='650px';
        document.getElementById("tableContainer").style.width='500px';
        document.getElementById("minusButton").style.display='inline-block';
        document.getElementById("magnifyButton").style.display='none';

      },
      //缩小
      minus:function () {
        let main =document.getElementById("main");

        main.style.height="570px";
        main.style.width="980px";
        main.style.left='260px';
        main.style.top='100px';
        document.getElementById("echartsContainer").style.height='390px';
        this.myChart.resize();
        document.getElementById("tableContainer").style.height='525px';
        document.getElementById("tableContainer").style.width='350px';
        document.getElementById("minusButton").style.display='none';
        document.getElementById("magnifyButton").style.display='inline-block';
      }
    },
    mounted() {
      this.loadSTInfo();
      this.initEcharts();
      this.loadData();
      let _this =this;
      let main = document.getElementById("main");
      let dataContainer = document.getElementById("dataContainer");
      let head= document.getElementById("windowHead");
      dataContainer.onmousedown= function(event){

        _this.fndown(main,event,dataContainer);
      }
      head.onmousedown= function(event){

        _this.fndown(main,event,head);
      }

    }
  }
</script>

<style scoped>
  .dataContainer{
    padding-left: 10px;
    /*padding-top: 15px;*/
  }
  .dataContainer>*{
    margin: 5px;

  }
  #echartsContainer{
    margin-left: 20px;
    margin-top: 5px;

    height:390px;
  }
  .mainContainer{
    position: relative;
    width: 150%;
    height: 520px;
    display: inline-block;

  }
  .tableContainer{
    height: 350px;
    width: 50%;
    display: inline-block;
    margin-bottom:10px ;
    padding-left: 5px;
  }
  .left{
    position: absolute;
    right: 5px;
  }
  .buttonContainer{
    position: relative;
    margin-top: 5px;
  }
  .right{
    position: relative;
    right: 5px;
  }
  #minusButton{
    display: none;
  }

</style>
