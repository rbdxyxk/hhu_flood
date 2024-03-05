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
                  <h1 style="text-align: center">石梁河水库概况</h1>
                  <h4 style="text-align: left;text-indent: 2em;line-height: 20px">石梁河水库位于新沭河干流中游，地处江苏省赣榆、东海两县南北边界的结合部，其上游与山东省临沭县接壤。水库工程于1958年12月动工兴建，1962年12月竣工，水库集水面积5783平方公里，其中大官庄至水库区间976平方公里来水全部汇入水库，沭河大官庄以上4807平方公里。沂沭河洪水东调工程实施后，将增加沂河部分洪水经分沂入沭水道和新沭河汇入水库。</h4>
                    <h4 style="text-align: left;text-indent: 2em;line-height: 20px">石梁河水库处于半湿润的暖温带季风气侯区。库区年平均气温在13.2~14.0℃，一月份最冷，平均气温-0.2~1.0℃，七月份最热，平均气温26.5~27.0℃，年极端最高气温40℃，年极端最低气温-21.7℃。
                    库区的年最大风速、常见风向：最多风向为北风，次多风向为偏东风。全年平均风速3.1~3.6米／秒，最大风速为15.4~23.0米／秒，年瞬时最大风速为24．2米／秒，风向为北风。
                    库区多年平均降水量937毫米，年降水时段主要集中在6~9月份，其间，累计平均降水量640毫米，约占总降水量的72％。
                    库区无霜期平均为205~225天。冬季最早降雪期始于12月中旬，最晚终雪期在3月中旬，历年中最大积雪厚度为28cm。
                    库区年蒸发量为1570～1780毫米，以5、6月份最大，平均分别达226.5毫米和229.0毫米；1月份最少，不足50毫米。年蒸发比（蒸发量与降水量之比）为1.79，年蒸发量远大于降水量。</h4>
                    <h4 style="text-align: left;text-indent: 2em;line-height: 20px">石梁河水库总库容5.31亿立方米，其中防洪库容为2.34亿立米(近期3.23亿立米)，兴利库容为3.35亿立米(近期2.65亿立米)，死库容0.32亿立米，设计洪水位27.65米，校核洪水位28.0 米，汛限水位25.0米，兴利水位26米，死水位18.50米。
                    水库主要由主坝、副坝、泄洪闸及两侧涵洞、南干渠电站涵洞、孟曹埠涵洞、北干渠进水闸、副坝涵洞、南干渠退水闸等建筑组成，是一座以防洪为主、结合灌溉和发电的综合利用的大型水库。
                    </h4>


                </div></el-row>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="tableContainer" style="width: 360px" id="tableContainer">
<!--                <RiverInfoTable style="height: 100%;width: 100%" id="infoTable" ref="RiverInfoTable"/>-->
                <br><br><img src="/static/images/石梁河水库%20(1).jpeg" alt="shui" height="200" width="310"><br><br>
                <img src="/static/images/石梁河水库%20(2).jpeg" alt="shui" height="200" width="310"><br><br>
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
