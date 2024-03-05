<template>
  <div style=" height:570px; width:1040px" id="main">
    <el-row>
      <div class="el-header " id="windowHead" style="text-align: right">
        <el-button type="infer" size="mini"  icon="el-icon-minus" id="minusButton" @click="minus" circle></el-button>
        <el-button type="infer" size="mini"  icon="el-icon-rank" id="magnifyButton" @click="magnify" circle></el-button>
        <el-button type="danger" size="mini"  @click="closeWindow"  icon="el-icon-close" circle></el-button>
      </div>
    </el-row>
    <el-row>
      <el-col :span="24">
        <div class="el-container" >
          <el-row style="width: 100%">
            <el-col :span="15">
              <div class="mainContainer">
                <el-row><div class="dataContainer" id="dataContainer">
                  <div>
                    <label > 选择测站:</label>
                    <el-select v-model="stationId" placeholder="请选择" size="mini">
                      <el-option
                        v-for="item in searchParameter.options"
                        :key="item.stcd"
                        :label="item.stnm"
                        :value="item.stcd">
                      </el-option>
                    </el-select>

                  </div>
                  <div class="block">
                    <label>起始时间:</label>
                    <el-date-picker
                      size="mini"
                      v-model="searchParameter.startDateTime"
                      type="datetime"
                      placeholder="选择日期时间"
                      default-time="12:00:00">
                    </el-date-picker>
                  </div>

                  <div class="block">
                    <label>终止时间:</label>
                    <el-date-picker
                      size="mini"
                      v-model="searchParameter.endDateTime"
                      type="datetime"
                      placeholder="选择日期时间"
                      default-time="12:00:00">
                    </el-date-picker>
                    <el-button type="primary" size="mini" class="left" @click="loadData" icon="el-icon-search">查询</el-button>
                  </div>
                </div></el-row>
                <el-row>
                  <div id="echartsContainer"  >

                  </div >
                </el-row>
                <el-row>
                  <div  class="buttonContainer" style="text-align: right">
                    <el-button type="success"   size="mini"  @click="EchartData2CsvFile" icon="el-icon-document">导出</el-button>
                    <el-button type="danger"   size="mini"  @click="closeWindow" icon="el-icon-delete">关闭</el-button>
                  </div>
                </el-row>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="tableContainer" style="width: 360px" id="tableContainer">
                <RiverInfoTable style="height: 100%;width: 100%" id="infoTable" ref="RiverInfoTable"/>
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
    data() {
      return {
        searchParameter:{
          startDateTime:'2006-08-12 18:00:00',
          endDateTime:'2010-09-14 06:00:00',
          options: [],

        },
        stationId:51112101,
        myChart:null,
        colors : ['#5470C6', '#d2d03f', '#91cc75','#d20828','#070fe3'],

      }
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
        let option = {
          title:{
            text:"测试站数据"
          },
          borderWidth:3,
          borderColor:'rgb(44,70,226)',
          backgroundColor:'rgb(255,255,255)',
          textStyle: {
            color:'rgba(0, 0, 0, 1)',
            // fontSize:3000,
            fontWeight:'bolder',
            fontFamily:'Arial',
          },

          grid:{
            left:'5%',//设置偏移量
            right:'18%'
          },
          tooltip: {
            trigger:"axis"
          },
          toolbox: {
            show: false,
            showTitle:true,
            // shadowColor: 'rgba(255,255,255,1)' ,
            // textStyle:{
            //        color:'rgba(0,0,255,1)'
            // },
            iconStyle:{
              // color:'rgba(255,0,0,1)',
              borderColor:'rgba(0,0,0,1)',
            },
            feature: {
              saveAsImage: {},
              dataView: {
                show: true,
                title: '表格数据',
                lang: ['表格数据：', '关闭', '导出Excel'],    // 按钮
                //icon:"image://image/excel.png",             // ‘数据视图’按钮自定义img
                contentToOption: function (opt) {

                  $("#echartsContainer").table2excel({           // 下载jquery.table2excel.js，引入，$("#main")是Echarts容器
                    exclude: ".noExl", //过滤位置的 css 类名， 有class = “noExl” 的行不被导出
                    filename: _this.stationId+"站测量数据.xls", // 文件名称
                    name: "Excel Document Name.xls",
                    exclude_img: true,// 是否导出图片 false导出
                    exclude_links: true,//是否导出链接 false 导出
                    exclude_inputs: true// 是否导出输入框的值 true导出
                  });
                },
                //数据视图展示为table
                optionToContent: function (opt) {
                  var axisData = opt.xAxis[0].data;
                  var series = opt.series;
                  // tableName = opt.title[0].text;
                  console.log(opt);
                  console.log(series);
                  var table = '<table style="width:100%;text-align:center"><tbody><tr>'
                    + '<td>时间</td>';
                  for(var k in series){
                    table+='<td>' + series[k].name + '</td>'

                  }

                  table  += '</tr>';
                  for (var i = 0, l = axisData.length; i < l; i++) {
                    table += '<tr>'
                      + '<td>' + axisData[i] +':00:00' +'</td>';
                    for(var k in series){
                      table+='<td>' + series[k].data[i] + '</td>';
                    }

                    table += '</tr>';
                  }
                  table += '</tbody></table>';
                  return table;

                }
              },



              // myMinus:{
              //   show:true,
              //   title:'缩小',
              //   icon:'image://static/images/minus.png',
              //   onclick:function(){
              //
              //     console.log("minus");
              //
              //     _this.myChart.dispose();//销毁echarts
              //     console.log(this);
              //     $("#main").html("<div class=\"myMinus\"><div id=\"ck\" style=\";\" > 测试窗口 <a onclick=\"magnify()\" id=\"magnify\"><img src=\"../static/images/magnify.png\" alt=\"口\" style=\"width: 21px;height: 21px ;\"></a></div></div>");
              //
              //   }
              // },
              // myDispose:{
              //   show:true,
              //   title:"擦除",
              //   icon:'image://static/images/fork.png',
              //   onclick:function(){
              //     _this.myChart.dispose();//销毁echarts
              //     _this.myChart=null;
              //     // popup.close();
              //   }
              // }


            }
          },
          dataZoom:[{
            type: 'inside',
            //show: true,
            xAxisIndex: [0],
            start: 1,
            end: 5
          },
            {
              type: 'slider',
              show: true,
              xAxisIndex: [0],
              start: 1,
              end: 5
            }
          ],
          legend: {
            data:['库上水位','入库流量','蓄水量','库下水位','出库流量'],
            selected:{
              库上水位:true,
              入库流量:false,
              蓄水量:true,
              库下水位:false,
              出库流量:false,
            },
            textStyle: {

              fontSize: 10

            }
          },
          xAxis: {

            data: [],
            //name:"时间",
            axisLine: {//x轴线的颜色以及宽度
              show: true,

              lineStyle: {
                color: "rgba(0,0,0,1)",
                width: 1,
                type: "solid"
              }
            },

            axisLabel: {//x轴文字的配置
              show: true,
              textStyle: {
                color: "rgba(0,0,0,1)",
                fontSize: 13
              },
              formatter(val){
                return moment(val).format("DD日HH:mm");
              }
            },
            splitLine: {//分割线配置
              show:false,
              lineStyle: {
                color: "rgba(219,225,255,1)",
              }
            }

          },
          yAxis: [

            {
              type: 'value',
              name:"水位",
              position:"left",
              // nameTextStyle:{//y轴上方单位的颜色
              //     color:'#151515'
              // },
              axisTick: {
                show:true,
              },
              axisLine: {
                show: true,
                lineStyle: {
                  color: this.colors[0]
                }
              },

              splitLine: {//分割线配置
                show:false,
                lineStyle: {
                  color: this.colors[0],
                }
              },
              axisLabel: {
                formatter: '{value}m',
                textStyle:{
                  fontsize:9,
                  color:this.colors[0]
                }
              }

            },
            {
              type: 'value',
              name:"流量",
              position:"right",
              // nameTextStyle:{//y轴上方单位的颜色
              //     color:'#151515'
              // },
              axisTick: {
                show:true,
              },
              axisLine: {
                show: true,
                lineStyle: {
                  color: this.colors[2]
                }
              },

              splitLine: {//分割线配置
                show:false,
                lineStyle: {
                  color: this.colors[2],
                }
              },
              axisLabel: {
                formatter: '{value}m³/s',
                textStyle:{
                  fontsize:9,
                  color:this.colors[2]
                }
              }
            },
            {
              type: 'value',
              name:"蓄水量",
              position:"right",
              // nameTextStyle:{//y轴上方单位的颜色
              //     color:'#151515'
              // },
              axisTick: {
                show:true,
              },
              axisLine: {
                show: true,
                lineStyle: {
                  color: this.colors[1]
                }
              },
              offset:40,
              splitLine: {//分割线配置
                show:false,
                lineStyle: {
                  color: this.colors[1],
                }
              },
              axisLabel: {
                formatter: '{value}*10⁶m³',
                textStyle:{
                  fontsize:9,
                  color:this.colors[1]
                }
              }

            },
            // {
            //   type: 'value',
            //   name:"库下水位",
            //   position:"left",
            //   // nameTextStyle:{//y轴上方单位的颜色
            //   //     color:'#151515'
            //   // },
            //   axisTick: {
            //     show:true,
            //   },
            //   axisLine: {
            //     show: true,
            //     lineStyle: {
            //       color: this.colors[0]
            //     }
            //   },
            //
            //   splitLine: {//分割线配置
            //     show:false,
            //     lineStyle: {
            //       color: this.colors[0],
            //     }
            //   },
            //   axisLabel: {
            //     formatter: '{value}m',
            //     textStyle:{
            //       fontsize:9,
            //       color:this.colors[0]
            //     }
            //   }
            //
            // },
            // {
            //   type: 'value',
            //   name:"出库流量",
            //   position:"right",
            //   // nameTextStyle:{//y轴上方单位的颜色
            //   //     color:'#151515'
            //   // },
            //   axisTick: {
            //     show:true,
            //   },
            //   axisLine: {
            //     show: true,
            //     lineStyle: {
            //       color: this.colors[0]
            //     }
            //   },
            //
            //   splitLine: {//分割线配置
            //     show:false,
            //     lineStyle: {
            //       color: this.colors[0],
            //     }
            //   },
            //   axisLabel: {
            //     formatter: '{value}m³/s',
            //     textStyle:{
            //       fontsize:9,
            //       color:this.colors[0]
            //     }
            //   }
            //
            // },
          ],

          series: [
            //['库上水位 ','入库流量 ','蓄水量 ','库下水位','出库流量']
            {
              name:"库上水位",
              type:"bar",
              yAxisIndex: 0,
              data:[]
            },
            {
              name:"入库流量",
              type:"line",
              yAxisIndex: 1,
              data:[]
            },
            {
              name:"蓄水量",
              type:"bar",
              yAxisIndex: 2,
              data:[]
            },
            {
              name:"库下水位",
              type:"bar",
              yAxisIndex: 0,
              data:[]
            },
            {
              name:"出库流量",
              type:"line",
              yAxisIndex: 1,
              data:[]
            },
          ]
        };
        this.myChart.setOption(option);
      },
      //加载数据
      loadData:function() {


        this.axios.get("http://localhost:8083/waterInfo/RSVR/"+this.stationId
          +"/" +moment(this.searchParameter.startDateTime).format('YYYY-MM-DD HH:mm:ss')
          +"/"+moment(this.searchParameter.endDateTime).format('YYYY-MM-DD HH:mm:ss')
        ).then(
          response=>{
            console.log(response.data);
            let data = response.data;
            let date = [];//时间
            let RZ=[];//库上水位
            let INQ=[];//入库流量
            let W=[];//蓄水量
            let OTQ=[];//库下水位
            let INQDR=[];//出库流量



            for(let i  in data){
              // new Date(data[i].tm).Format("yyyy-MM-dd hh")
              date.push(moment(data[i].tm).format('YYYY-MM-DD HH:mm:ss'));//计算区域
              RZ.push(data[i].rz)
              if(data[i].inq!=null) {
                INQ.push(data[i].inq);
              }
            else {
                INQ.push(0);
              }
              if(data[i].w!=null) {
                W.push(data[i].w);
              }
              else {
                W.push(0);
              }
              if(data[i].otq!=null){
                OTQ.push(data[i].otq);
              }
              else {
                OTQ.push(0);
              }
              if(data[i].inqdr!=null){
                INQDR.push(data[i].inqdr);
              }
              else {
                INQDR.push(0);
              }

            }
            this.$refs.RiverInfoTable.loadData(["日期","库上水位","入库流量","蓄水量","库下水位","出库流量"],date,RZ,INQ,W,OTQ,INQDR);
            this.myChart.setOption({
              xAxis: {
                data: date,

              },
              dataZoom:[
                {
                  type: 'slider',
                  show: true,
                  xAxisIndex: [0],
                  start: 0,
                  end: 1300/date.length
                }
              ]
              ,
              series:[
                {
                  name:"库上水位",
                  type:"bar",
                  yAxisIndex: 0,
                  data:RZ
                },
                {
                  name:"入库流量",
                  type:"line",
                  yAxisIndex: 1,
                  data:INQ
                },
                {
                  name:"蓄水量",
                  type:"bar",
                  yAxisIndex: 2,
                  data:W
                },
                {
                  name:"库下水位",
                  type:"bar",
                  yAxisIndex: 0,
                  data:OTQ
                },
                {
                  name:"出库流量",
                  type:"line",
                  yAxisIndex: 1,
                  data:INQDR
                },

              ]

            });
          }
        );
      },
      //设置访问的测站并加载数据
      loadData1:function(stationID){
        this.stationId=stationID
        this.loadData()
      },
      //关闭窗口
      closeWindow:function () {
        this.$emit('closeWindow')
      },
      //销毁
      removeEchart:function () {
        this.myChart.dispose();
        this.myChart=null;
      },
      //移动相关
      fndown:function(main,event,container,){
        let _this = this;
        event = event||window.event;
        let disX = event.clientX - main.offsetLeft,//记录鼠标相对于矩形的位置
          disY =event.clientY - main.offsetTop;
        document.onmousemove = function(event){

          event = event||window.event;
          _this.fnMove(event,disX,disY,main)
        };
        container.onmouseup = function(){//松开鼠标后无法拖动

          document.onmousemove = null;
          container.onmouseup = null ;
        }


      },
      fnMove:function(event,disX,disY,main){//鼠标移动事件
        let l = event.clientX - disX,//根据鼠标的位置改变，来改变矩形相对于body的位置
          t = event.clientY - disY;
        main.style.left=l+'px';
        main.style.top=t+'px';
      },
      //导出csv
      EchartData2CsvFile:function () {
        let stationId=this.stationId;
        for(let item of this.searchParameter.options){
          if(item.stcd==stationId){
            this.$refs.RiverInfoTable.exportCsv(item.stnm+"水库信息");
            break;
          }
        }


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
    padding-left: 15px;
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
