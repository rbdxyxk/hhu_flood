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
                  <h1 style="text-align: center">应急预案与调度规则</h1>
                  <h4 style="text-align: left;text-indent: 2em;line-height: 30px" >长期以来水库洪水调度都是建立在“弃水为安”的思想上，即将洪水尽快送走，减轻防洪压力，以保一方安全。具体运行时，仅考虑水情信息，按事先制度的调度规则进行，常以水位控制泄量，兼顾下游防洪。结果是宝贵洪水资源没有得到有效调蓄利用、地下水没有得到有效补充，换来的是洪水过后的干旱、河道的干涸、地下水位的下降以及生态环境的破坏。
                    防洪标准与经济社会的发展密切相关，石梁河水库隶属连云港市直接管理。连云港城市发展迅速，防洪排涝设施相对滞后，而且多头管理，职责不够明确。在城市建设过程中，管网中断、河道改道淤塞等现象时有发生。相对于大江大河的洪水，城市洪水虽然较为平缓，但带来的损失却是惊人的，提高城市的防洪标准刻不容缓。水库防洪安全至关重要，在做好洪水调度、错峰削峰、保障下游行洪安全的同时，需要做好水库自身防洪安全。
                    加强非工程措施的建设。石梁河水库位于城区附近，城市的防洪关系到一个城市的命脉，要利用现代化的手段，加强水雨情观测设施的建设，为了对暴雨进行有效的监测，需要建设和管理好遥测雨量监测网络和河道流量监测系统。研究流域产汇流规律，进行重要河段的洪水预报预警，减少洪涝灾害损失。</h4>

                </div></el-row>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="tableContainer" style="width: 360px" id="tableContainer">
<!--                <RiverInfoTable style="height: 100%;width: 100%" id="infoTable" ref="RiverInfoTable"/>-->
               <br><br><img src="/static/images/防洪管理.jpeg" alt="shui" height="320" width="355"><br><br>
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
