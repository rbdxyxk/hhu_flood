<template>
  <div style=" height:570px; width:895px" id="main">
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
            <el-col :span="17">
              <div class="mainContainer">
                <el-row :gutter="20">
                  <el-col :span="12">
                    <label > 河道测站:</label>
                    <el-select v-model="stationId" placeholder="请选择" size="mini" ref="s1">
                      <el-option
                        v-for="item in searchParameter.options"
                        :key="item.stcd"
                        :label="item.stnm"
                        :value="item.stcd">
                      </el-option>
                    </el-select>
                  </el-col>
                  <el-col :span="12">
                    <label > 堰闸测站:</label>
                    <el-select v-model="ddstationId" placeholder="请选择" size="mini" ref="s2">
                      <el-option
                        v-for="item in searchParameter.ddoptions"
                        :key="item.stcd"
                        :label="item.stnm"
                        :value="item.stcd">
                      </el-option>
                    </el-select>
                  </el-col>
                  <el-col :span="12">
                    <label>起始时间:</label>
                    <el-date-picker
                      size="mini"
                      v-model="searchParameter.startDateTime"
                      type="datetime"
                      placeholder="选择日期时间"
                      default-time="12:00:00">
                    </el-date-picker>
                  </el-col>
                  <el-col :span="12">
                    <label > 泵站测站:</label>
                    <el-select v-model="dpstationId" placeholder="请选择" size="mini" ref="s3">
                      <el-option
                        v-for="item in searchParameter.dpoptions"
                        :key="item.stcd"
                        :label="item.stnm"
                        :value="item.stcd">
                      </el-option>
                    </el-select>
                  </el-col>
                   <el-col :span="12">
                     <label>终止时间:</label>
                     <el-date-picker
                       size="mini"
                       v-model="searchParameter.endDateTime"
                       type="datetime"
                       placeholder="选择日期时间"
                       default-time="12:00:00">
                     </el-date-picker>
                   </el-col>
                    <el-col :span="12">
                      <el-button type="primary" size="mini" class="left" @click="loadData" icon="el-icon-search">河道查询</el-button>
                      <el-button type="primary" size="mini" class="middle" @click="loadData3" icon="el-icon-search">泵站查询</el-button>
                      <el-button type="primary" size="mini" class="right" @click="loadData2" icon="el-icon-search">堰闸查询</el-button>
                    </el-col>
                </el-row>
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
            <el-col :span="7">
              <div class="tableContainer" id="tableContainer">
                 <h10>{{st_txt}}</h10>
                <RiverInfoTable ref="RiverInfoTable" style="height: 100%;width: 100%" id="infoTable"/>
              </div>
            </el-col>
          </el-row>

        </div>
      </el-col>
    </el-row>

<!--  <chooser></chooser>-->
  </div>
</template>

<script>
  import  "../assets/js/jquery.table2excel.min.js"

  import RiverInfoTable from "./waterInfoTable/RiverInfoTable";
  import Chooser from "./chooser";

  export default {
    name: "waterInfo",
    components:{
      Chooser,
      RiverInfoTable,

    },
    data() {
      return {
        searchParameter:{
          startDateTime:'2006-08-12 18:00:00',
          endDateTime:'2010-09-14 06:00:00',
          options: [
          //   {
          //   value: 41814801,
          //   label: '臧格庄'
          // }, {
          //   value: 41816401,
          //   label: '团旺',
          // }, {
          //   value: 41814701,
          //   label: '福山'
          // }
          ],
          ddoptions: [

          ],
          dpoptions: [

          ],

        },
        st_txt:'大兴镇河道测站',
        stationId:51112000,
        ddstationId:51111911,
        dpstationId:51112150,
        myChart:null,
        colors : ['#5470C6', '#d2d03f', '#91cc75','#4B0082','#DDA0DD'],

      }
    },
    methods:{
      //加载测站数据
      loadSTInfo:function() {
        this.axios.get("http://localhost:8083/waterInfo/river/ST").then(
          response => {
            console.log(response.data);
            this.searchParameter.options = response.data;
          }
        );
        this.axios.get("http://localhost:8083/waterInfo/dd/ST").then(
          response => {
            console.log(response.data);
            this.searchParameter.ddoptions = response.data;
          }
        );
        this.axios.get("http://localhost:8083/waterInfo/dp/ST").then(
          response => {
            console.log(response.data);
            this.searchParameter.dpoptions = response.data;
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
            left:'8%',//设置偏移量
            right:'15%'
          },
          tooltip: {
            trigger:"axis"
          },
          toolbox: {
            show: true,
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
            end: 35
          },
            {
              type: 'slider',
              show: true,
              xAxisIndex: [0],
              start: 1,
              end: 35
            }
          ],
          legend: {
            data:['流量','水位','闸上水位','闸下水位','站上水位','站下水位'],
            textStyle: {

              fontSize: 21

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
              name:"流量",
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
                formatter: '{value}m³/s',
                textStyle:{
                  fontsize:9,
                  color:this.colors[0]
                }
              }

            },
            {
              type: 'value',
              name:"水位",
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
                formatter: '{value}m',
                textStyle:{
                  fontsize:9,
                  color:this.colors[2]
                }
              }
            }
          ],

          series: [
            {
              name:"流量",
              type:"line",
              yAxisIndex: 0,
              data:[]
            },
            {
              name:"水位",
              type:"line",
              yAxisIndex: 1,
              data:[]
            },

          ]
        };
        this.myChart.setOption(option);
      },
      loadData1(stationID){
        this.stationId=stationID
        this.loadData()
      },
      //加载数据
      loadData:function() {
        this.axios.get("http://localhost:8083/waterInfo/river/"+this.stationId
          +"/" +moment(this.searchParameter.startDateTime).format('YYYY-MM-DD HH:mm:ss')
          +"/"+moment(this.searchParameter.endDateTime).format('YYYY-MM-DD HH:mm:ss')
        ).then(
          response=>{
            this.st_txt=this.$refs.s1.selectedLabel+"河道站"
            console.log(response.data);
            // alert(data[0].Z);
            let maxZ=0;
            let maxQ=0;
            let data = response.data;
            let date = [];//时间
            let Z=[];//水位
            let Q=[];//流量
            // alert(data[0].TM);
            //日期格式
            // Date.prototype.Format = function (fmt) { //author: meizz
            //   var o = {
            //     "M+": this.getMonth() + 1, //月份
            //     "d+": this.getDate(), //日
            //     "h+": this.getHours(), //小时
            //     "m+": this.getMinutes(), //分
            //     "s+": this.getSeconds(), //秒
            //     "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            //     "S": this.getMilliseconds() //毫秒
            //   };
            //   if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            //   for (var k in o)
            //     if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            //   return fmt;
            // }


            for(let i  in data){
              // new Date(data[i].tm).Format("yyyy-MM-dd hh")
              date.push(moment(data[i].tm).format('MM-DD HH:mm:ss'));//计算区域
              if(data[i].z!=null){
                Z.push(data[i].z);
                if(maxZ<data[i].z){
                  maxZ=data[i].z;
                }
              }else{
                Z.push(0);
              }
              if(data[i].q!=null){
                Q.push(data[i].q);
                if(maxZ<data[i].q){
                  maxZ=data[i].q;
                }
              }else{
                Q.push(0);
              }

            }
            // for(let i in date){
            //   date[i]=moment(date[i]).format('YYYY-MM-DD');
            // }
            this.$refs.RiverInfoTable.loadData(["日期","流量","水位"],date,Q,Z);
            this.myChart.setOption({
              title:{
                text:this.$refs.s1.selectedLabel
              },
              xAxis: {
                data: date,

              },
              yAxis: [

                {
                  type: 'value',
                  name:"流量",
                  //是否倒置显示
                  // inverse:true,
                  position:"left",
                  // nameTextStyle:{//y轴上方单位的颜色
                  //     color:'#151515'
                  // },
                  min: 0,
                  max: maxQ+maxQ*0.2,
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
                    formatter: '{value}m³/s',
                    textStyle:{
                      fontsize:9,
                      color:this.colors[0]
                    }
                  }

                },
                {
                  type: 'value',
                  name:"水位",
                  position:"right",
                  min: 0,
                  max: 2*maxZ,
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

                  splitLine: {//分割线配置
                    show:false,
                    lineStyle: {
                      color: this.colors[1],
                    }
                  },
                  axisLabel: {
                    formatter: '{value}m',
                    textStyle:{
                      fontsize:9,
                      color:this.colors[1]
                    }
                  }
                },
              ],
              dataZoom:[
                {
                  type: 'slider',
                  show: true,
                  xAxisIndex: [0],
                  start: 0,
                  end: 1300/date.length
                }
              ],
              series:[
                {
                  name:"流量",
                  data:Q,
                  type:"line",
                  yAxisIndex: 1,
                },
                {
                  name:"水位",
                  data:Z,
                  type:"line",
                  yAxisIndex: 1,
                }
              ]

            });
          }
        );
      },
      loadData2:function() {
        this.axios.get("http://localhost:8083/waterInfo/was/getWasInfo/"+this.ddstationId
          +"/" +moment(this.searchParameter.startDateTime).format('YYYY-MM-DD HH:mm:ss')
          +"/"+moment(this.searchParameter.endDateTime).format('YYYY-MM-DD HH:mm:ss')
        ).then(
          response=>{
            this.st_txt=this.$refs.s2.selectedLabel+"堰闸站"
            console.log(response.data);
            let data = response.data;
            // alert(data[0].Z);
            let date = [];//时间
            let DZ=[];//闸下水位
            let Z=[];//水位
            let Q=[];//流量
            let maxZ = 0;
            //最大水位
            let maxDZ =0;
            let maxQ =0;
            //日期格式
            // Date.prototype.Format = function (fmt) { //author: meizz
            //   var o = {
            //     "M+": this.getMonth() + 1, //月份
            //     "d+": this.getDate(), //日
            //     "h+": this.getHours(), //小时
            //     "m+": this.getMinutes(), //分
            //     "s+": this.getSeconds(), //秒
            //     "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            //     "S": this.getMilliseconds() //毫秒
            //   };
            //   if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            //   for (var k in o)
            //     if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            //   return fmt;
            // }


            for(let i  in data){
              // date.push(moment(data[i].TM).format('YYYY-MM-DD HH:mm:ss'));
              // new Date(data[i].tm).Format("yyyy-MM-dd hh")
              // alert(response.data[i].UPZ);
              // alert(response.data[i].DWZ);
              // alert(response.data[i].TGTQ);
              // alert(data[i].TM);
              date.push(moment(data[i].tm).format('MM-DD HH:mm:ss'));//计算区域
              if(data[i].upz!=null){
                Z.push(data[i].upz);
                if(maxZ<data[i].upz){
                  maxZ=data[i].upz;
                }
              }else{
                Z.push(0);
              }
              if(data[i].dwz!=null){
                DZ.push(data[i].dwz);
                if(maxDZ<data[i].dwz){
                  maxDZ=data[i].dwz;
                }
              }else{
                DZ.push(0);
              }
              if(data[i].tgtq!=null){
                Q.push(data[i].tgtq);
                if(maxQ<data[i].tgtq){
                  maxQ=data[i].tgtq;
                }
              }else{
                Q.push(0);
              }

            }
            if(maxZ<maxDZ)maxZ=maxDZ;
            else{
              maxZ=maxZ;
            }
            // alert(date);
            // alert(Z);
            // alert(DZ);
            // alert(Q);

            this.$refs.RiverInfoTable.loadData(["日期","流量","闸上水位","闸下水位"],date,Q,Z,DZ);
            this.myChart.setOption({
              title:{
                text:this.$refs.s2.selectedLabel
              },
              xAxis: {
                data: date,

              },
              yAxis: [

                {
                  type: 'value',
                  name:"流量",
                  //是否倒置显示
                  // inverse:true,
                  position:"left",
                  // nameTextStyle:{//y轴上方单位的颜色
                  //     color:'#151515'
                  // },
                  min: 0,
                  max: maxQ+0.2*maxQ,
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
                    formatter: '{value}m³/s',
                    textStyle:{
                      fontsize:9,
                      color:this.colors[0]
                    }
                  }

                },
                {
                  type: 'value',
                  name:"水位",
                  position:"right",
                  min: 0,
                  max: maxZ+maxZ*0.2,
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

                  splitLine: {//分割线配置
                    show:false,
                    lineStyle: {
                      color: this.colors[1],
                    }
                  },
                  axisLabel: {
                    formatter: '{value}m',
                    textStyle:{
                      fontsize:9,
                      color:this.colors[1]
                    }
                  }

                },
                // {
                //   type: 'value',
                //   name:"闸下水位",
                //   position:"right",
                //   min: 0,
                //   max: 2*maxDZ,
                //   // nameTextStyle:{//y轴上方单位的颜色
                //   //     color:'#151515'
                //   // },
                //   axisTick: {
                //     show:true,
                //   },
                //   axisLine: {
                //     show: true,
                //     lineStyle: {
                //       color: this.colors[2]
                //     }
                //   },
                //
                //   splitLine: {//分割线配置
                //     show:false,
                //     lineStyle: {
                //       color: this.colors[2],
                //     }
                //   },
                //   axisLabel: {
                //     formatter: '{value}m',
                //     textStyle:{
                //       fontsize:9,
                //       color:this.colors[2]
                //     }
                //   }
                //
                // },

              ],
              dataZoom:[
                {
                  type: 'slider',
                  show: true,
                  xAxisIndex: [0],
                  start: 0,
                  end: 1300/date.length
                }
              ],
              series:[
                {
                  name:"流量",
                  data:Q,
                  type:"line",
                  yAxisIndex: 1,
                },
                {
                  name:"闸上水位",
                  data:Z,
                  type:"line",
                  yAxisIndex: 1,
                },
                {
                  name:"闸下水位",
                  data:DZ,
                  type:"line",
                  yAxisIndex: 1,
                },
              ]

            });
          }
        );
      },
      loadData3:function() {
        this.axios.get("http://localhost:8083/waterInfo/pump/getPumpInfo/"+this.dpstationId
          +"/" +moment(this.searchParameter.startDateTime).format('YYYY-MM-DD HH:mm:ss')
          +"/"+moment(this.searchParameter.endDateTime).format('YYYY-MM-DD HH:mm:ss')
        ).then(
          response=>{
            this.st_txt=this.$refs.s3.selectedLabel+"泵站"
            console.log(response.data);
            let data = response.data;
            // alert(data[0].Z);
            let date = [];//时间
            let DZ=[];//闸下水位
            let Z=[];//水位
            let Q=[];//流量
            let maxZ = 0;
            //最大水位
            let maxDZ =0;
            let maxQ =0;
            //日期格式
            // Date.prototype.Format = function (fmt) { //author: meizz
            //   var o = {
            //     "M+": this.getMonth() + 1, //月份
            //     "d+": this.getDate(), //日
            //     "h+": this.getHours(), //小时
            //     "m+": this.getMinutes(), //分
            //     "s+": this.getSeconds(), //秒
            //     "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            //     "S": this.getMilliseconds() //毫秒
            //   };
            //   if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            //   for (var k in o)
            //     if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            //   return fmt;
            // }


            for(let i  in data){
              // date.push(moment(data[i].TM).format('YYYY-MM-DD HH:mm:ss'));
              // new Date(data[i].tm).Format("yyyy-MM-dd hh")
              // alert(response.data[i].UPZ);
              // alert(response.data[i].DWZ);
              // alert(response.data[i].TGTQ);
              // alert(data[i].TM);
              date.push(moment(data[i].tm).format('MM-DD HH:mm:ss'));//计算区域
              if(data[i].ppupz!=null){
                Z.push(data[i].ppupz);
                if(maxZ<data[i].ppupz){
                  maxZ=data[i].ppupz;
                }
              }else{
                Z.push(0);
              }
              if(data[i].ppdwz!=null){
                DZ.push(data[i].ppdwz);
                if(maxDZ<data[i].ppdwz){
                  maxDZ=data[i].ppdwz;
                }
              }else{
                DZ.push(0);
              }
              if(data[i].pmpq!=null){
                Q.push(data[i].pmpq);
                if(maxQ<data[i].pmpq){
                  maxQ=data[i].pmpq;
                }
              }else{
                Q.push(0);
              }

            }
            if(maxZ<maxDZ)maxZ=maxDZ;
            else{
              maxZ=maxZ;
            }
            // alert(date);
            // alert(Z);
            // alert(DZ);
            // alert(Q);

            this.$refs.RiverInfoTable.loadData(["日期","流量","站上水位","站下水位"],date,Q,Z,DZ);
            this.myChart.setOption({
              title:{
                text:this.$refs.s3.selectedLabel
              },
              xAxis: {
                data: date,

              },
              yAxis: [

                {
                  type: 'value',
                  name:"流量",
                  //是否倒置显示
                  // inverse:true,
                  position:"left",
                  // nameTextStyle:{//y轴上方单位的颜色
                  //     color:'#151515'
                  // },
                  min: 0,
                  max: maxQ+0.2*maxQ,
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
                    formatter: '{value}m³/s',
                    textStyle:{
                      fontsize:9,
                      color:this.colors[0]
                    }
                  }

                },
                {
                  type: 'value',
                  name:"水位",
                  position:"right",
                  min: 0,
                  max: maxZ+0.2*maxZ,
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

                  splitLine: {//分割线配置
                    show:false,
                    lineStyle: {
                      color: this.colors[1],
                    }
                  },
                  axisLabel: {
                    formatter: '{value}m',
                    textStyle:{
                      fontsize:9,
                      color:this.colors[1]
                    }
                  }

                },
                // {
                //   type: 'value',
                //   name:"闸下水位",
                //   position:"right",
                //   min: 0,
                //   max: 2*maxDZ,
                //   // nameTextStyle:{//y轴上方单位的颜色
                //   //     color:'#151515'
                //   // },
                //   axisTick: {
                //     show:true,
                //   },
                //   axisLine: {
                //     show: true,
                //     lineStyle: {
                //       color: this.colors[2]
                //     }
                //   },
                //
                //   splitLine: {//分割线配置
                //     show:false,
                //     lineStyle: {
                //       color: this.colors[2],
                //     }
                //   },
                //   axisLabel: {
                //     formatter: '{value}m',
                //     textStyle:{
                //       fontsize:9,
                //       color:this.colors[2]
                //     }
                //   }
                //
                // },

              ],
              dataZoom:[
                {
                  type: 'slider',
                  show: true,
                  xAxisIndex: [0],
                  start: 0,
                  end: 1300/date.length
                }
              ],
              series:[
                {
                  name:"流量",
                  data:Q,
                  type:"line",
                  yAxisIndex: 1,
                },
                {
                  name:"站上水位",
                  data:Z,
                  type:"line",
                  yAxisIndex: 1,
                },
                {
                  name:"站下水位",
                  data:DZ,
                  type:"line",
                  yAxisIndex: 1,
                },
              ]

            });
          }
        );
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
          if(item.value==stationId){
            this.$refs.RiverInfoTable.exportCsv(item.label+"河道信息");
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
        document.getElementById("minusButton").style.display='inline-block';
        document.getElementById("magnifyButton").style.display='none';

      },
      //缩小
      minus:function () {
        let main =document.getElementById("main");

        main.style.height="570px";
        main.style.width="895px";
        main.style.left='260px';
        main.style.top='100px';
        document.getElementById("echartsContainer").style.height='390px';
        this.myChart.resize();
        document.getElementById("tableContainer").style.height='525px';
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
    /*height: 80%;*/
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
