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
                  <h1 style="text-align: center">洪涝灾害</h1>
                  <h4 style="text-align: left;text-indent: 2em;line-height: 30px">暴雨、尤其是大暴雨和连续性暴雨，常常会造成洪涝灾害和地质灾害，一旦出现暴雨洪涝灾害天气，会产生极其严重的危害，给农业经济、工业发展和城市交通干线等均会带来极其重大的破坏，严重还会造成人员伤亡。
                    灾害的特点是成灾时间短。暴雨强度大、雨量集中，洪水涨势猛。在降水的主雨峰开始后 3 小时左右，雨区滞涝水位全部达到最高，具有突然性、突发性。</h4>
                  <h4 style="text-align: left;text-indent: 2em;line-height: 30px"> 例如，2012年7月8日03时到9日凌晨，受北方冷空气以及西南暖湿气流的共同影响，连云港市普降暴雨，局部大暴雨，平均降雨量达170.4mm。市区部分地区最大降水量高达421mm，为1951年连云港市有气象记录以来的24h最大降雨量，实测点雨量超过百年一遇。由于降水强度较大，雨量多，部分河道河水漫道，导致城区被淹，约80%的区域出现积水。据相关资料统计显示，连云港市在此次暴雨天气中共有新浦区、海州区、赣榆县、灌南县等4个县(市、区)受灾。受灾人口高达52.7万人，紧急转移1200人，因洪涝灾害带来的直接经济损失达4.652亿元。连云港市三大水厂之一的海州水厂泵房以及整个厂区全部被淹，导致西部城区供水中断，城区部分地区供电中断，给国家以及广大群众带来巨大的经济损失。</h4>


                </div></el-row>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="tableContainer" style="width: 360px" id="tableContainer">
<!--                <RiverInfoTable style="height: 100%;width: 100%" id="infoTable" ref="RiverInfoTable"/>-->
                <img src="/static/images/洪涝灾害%20(1).jpeg" alt="shui" height="160" width="300"><br><br>
                <img src="/static/images/洪涝灾害%20(2).jpeg" alt="shui" height="160" width="300"><br><br>
                <img src="/static/images/洪涝灾害%20(3).jpeg" alt="shui" height="160" width="300">
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
