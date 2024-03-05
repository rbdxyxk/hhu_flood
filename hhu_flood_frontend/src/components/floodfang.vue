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
                  <h1 style="text-align: center">防汛手册</h1>
                  <h4 style="text-align: left;text-indent: 2em;line-height: 21px" >暴雨是洪水产生的直接因素，近三十年来连云港暴雨日数整体上呈现出略微增加的变化趋势，气候倾向率是0.009d/10a，并未达到显著性检验水平，表明增加趋势并不显著。在这三十年间，连云港一共出现了104个暴雨日，三十年平均暴雨日数大约为3.47d；年暴雨日数出现最多的年份为2007年、2008年、2009年以及2013年，这四个年份出现暴雨日数均为6d，这四年因暴雨历时短、总雨量不强，这四年并未造成较大洪水灾害。年暴雨日数出现最少的年份为为2011年，无暴雨天气出现。由连云港暴雨天气5a滑动平均线分析能够获悉，连云港平均年暴雨日数呈波动性变化趋势。</h4>
                    <h4 style="text-align: left;text-indent: 2em;line-height: 21px" > 石梁河水库流域暴雨与连云港市暴雨具有同频特点，主要发生于每年的4-10月，特别是6-9月份暴雨日数最为集中，占全年暴雨总数的91.4%，暴雨天气出现频率最高的月份为7月份，历年累积暴雨日数为38d，月平均值为1.27d，占全年的36.6%。从10月份开始，暴雨日数便开始大幅减少，该月份暴雨出现频率仅为1.9%，11月~翌年3月在近三十年间无暴雨日数出现；连云港通常在每年的4月份开始进入至前汛期，暴雨天气也有所增多。从连云港暴雨日数季节分布情况来看，近三十年连云港春季（3~5月）暴雨日数占全年的6.7%，夏季（6~8月）占全年暴雨日数的77.0%；秋季（9~11月）占全年的6.3%，冬季（12~翌年2月）无暴雨日数出现。连云港一年四季中，出现暴雨频率最大的季节为夏季，秋季次之，春季较少，冬季为空白。连云港市夏季暴雨日数最多的原因是因为夏季江淮梅雨的雨带偏北，连云港受梅雨雨带的影响，故暴雨日数偏多。而秋季暴雨天气大部分是受台风亦或其外围影响造成的。</h4>


                </div></el-row>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="tableContainer" style="width: 360px" id="tableContainer">
<!--                <RiverInfoTable style="height: 100%;width: 100%" id="infoTable" ref="RiverInfoTable"/>-->
                <br><br><img src="/static/images/连云港暴雨%20(1).jpeg" alt="shui" height="200" width="320"><br><br><br><br>
                <img src="/static/images/连云港暴雨%20(2).jpeg" alt="shui" height="200" width="320"><br><br>
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
    width: 100%;
    height: 520px;
    display: inline-block;

  }
  .tableContainer{

    /*width: 270px;*/
    height: 525px;
    width: 100%;
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
