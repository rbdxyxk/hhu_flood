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
                  <h1 style="text-align: center">河流水系</h1>
                  <h3 style="text-align: left;text-indent: 2em;line-height: 35px" >新沭河是沂沭泗河洪水东调南下工程的重要组成部分，西起大官庄枢纽新沭河泄洪闸，东至临洪口入海，全长约80km，其中山东境内从大官庄到石梁河水库长19.97km；
                    江苏境内石梁河库区段长约15km；石梁河水库大坝至入海口段长44.69km。
                    新沭河的洪水特征取决于沂河、沭河暴雨洪水特征以及大官庄枢纽的控制运用，因此新沭河洪水受到人为控制、调度运用的影响，而且这种影响将越来越大。
                    石梁河水库位于新沐河中游、连云港市的东海县、赣榆县交界处，是江苏省内最大的人工水库，主要承泄新沐河上游和沂河、沭河部分来水，
                    是沂沭泗洪水东调南下工程的重要组成部分，具有防洪、蓄水、灌溉、发电、养殖等多项功能。</h3>


                </div></el-row>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="tableContainer" style="width: 360px" id="tableContainer">
<!--                <RiverInfoTable2 style="height: 100%;width: 100%" id="infoTable" ref="RiverInfoTable2"/>-->
                <img src="/static/images/新沭河水系%20(1).jpeg" alt="shui" height="160" width="300"><br><br>
                <img src="/static/images/新沭河水系%20(2).jpeg" alt="shui" height="160" width="300"><br><br>
                <img src="/static/images/河流水系.png" alt="shui" height="160" width="300">
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
  import RiverInfoTable2 from "./waterInfoTable/RiverInfoTable2";
  export default {
    name: "waterInfo",
    components:{
      RiverInfoTable2
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

    width: 35%;
    height: 525px;
    display: inline-block;
    margin-bottom:10px ;
    margin-left: 5px;
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
