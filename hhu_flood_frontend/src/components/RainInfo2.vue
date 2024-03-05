<template>
  <div style=" height:570px; width:950px" id="mainRain">
    <div class="el-header " id="rainWindowHead" style="text-align: right">
      <el-button type="infer" size="mini"  icon="el-icon-minus" id="minusButton" @click="minus" circle></el-button>
      <el-button type="infer" size="mini"  icon="el-icon-rank" id="magnifyButton" @click="magnify" circle></el-button>
      <el-button type="danger" size="mini"  @click="closeWindow"  icon="el-icon-close" circle></el-button>

    </div>
    <div class="el-container">
      <div class="mainContainer">
        <div class="dataContainer" id="rainDataContainer">
<!--          <div>-->
<!--            <div style="display: inline-block">-->
<!--              <label > 雨情测站:</label>-->
<!--              <el-select v-model="stationId" placeholder="请选择" size="mini">-->
<!--                <el-option-->
<!--                  v-for="item in searchParameter.options"-->
<!--                  :key="item.stcd"-->
<!--                  :label="item.stnm"-->
<!--                  :value="item.stcd">-->
<!--                </el-option>-->
<!--              </el-select>-->

<!--            </div>-->
<!--            <div style="display: inline-block" >-->
<!--              <label > 水库测站:</label>-->
<!--              <el-select v-model="stationId" placeholder="请选择" size="mini">-->
<!--                <el-option-->
<!--                  v-for="item in searchParameter.options"-->
<!--                  :key="item.stcd"-->
<!--                  :label="item.stnm"-->
<!--                  :value="item.stcd">-->
<!--                </el-option>-->
<!--              </el-select>-->

<!--            </div>-->
<!--          </div>-->
          <el-row :gutter="20">
            <el-col :span="12">
              <label > 雨情测站:</label>
              <el-select v-model="stationId" placeholder="请选择" size="mini">
                <el-option
                  v-for="item in searchParameter.options"
                  :key="item.stcd"
                  :label="item.stnm"
                  :value="item.stcd">
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="12">
              <label > 河道测站:</label>
              <el-select v-model="riverStationId" placeholder="请选择" size="mini">
                <el-option
                  v-for="item in searchParameter.riverOptions"
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
                style="width: 200px"
                v-model="searchParameter.startDateTime"
                type="datetime"
                placeholder="选择日期时间"
                default-time="12:00:00">
              </el-date-picker>
            </el-col>
            <el-col :span="12">
              <label > 水库测站:</label>
              <el-select v-model="rsvrStationId" placeholder="请选择" size="mini">
                <el-option
                  v-for="item in searchParameter.rsvrOptions"
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
                style="width: 200px"
                v-model="searchParameter.endDateTime"
                type="datetime"
                placeholder="选择日期时间"
                default-time="12:00:00">
              </el-date-picker>
            </el-col>
            <el-col :span="12">
              <el-button type="primary" size="mini" class="left" @click="loadData" icon="el-icon-search">水库站对比</el-button>
              <el-button type="primary" size="mini" class="right" @click="loadData2" icon="el-icon-search">河道站对比</el-button>
            </el-col>
          </el-row>
<!--          <div class="block">-->
<!--            <label>起始时间:</label>-->
<!--            <el-date-picker-->
<!--              size="mini"-->
<!--              v-model="searchParameter.startDateTime"-->
<!--              type="datetime"-->
<!--              placeholder="选择日期时间"-->
<!--              default-time="12:00:00">-->
<!--            </el-date-picker>-->
<!--          </div>-->

<!--          <div class="block">-->
<!--            <label>终止时间:</label>-->
<!--            <el-date-picker-->
<!--              size="mini"-->
<!--              v-model="searchParameter.endDateTime"-->
<!--              type="datetime"-->
<!--              placeholder="选择日期时间"-->
<!--              default-time="12:00:00">-->
<!--            </el-date-picker>-->
<!--            <el-button type="primary" size="mini" class="left" @click="loadData" icon="el-icon-search">水库站对比</el-button>-->
<!--            <el-button type="primary" size="mini" class="right" @click="loadData2" icon="el-icon-search">河道站对比</el-button>-->
<!--          </div>-->
        </div>
        <div id="rainEchartsContainer"  >

        </div >
        <div  class="buttonContainer" style="text-align: right">
          <el-button type="success"   size="mini"  @click="EchartData2CsvFile" icon="el-icon-document">导出</el-button>
          <el-button type="danger"   size="mini"  @click="closeWindow" icon="el-icon-delete">关闭</el-button>
        </div>
      </div>
      <div class="tableContainer" id="tableContainer">
        <RiverInfoTable ref="RainInfoTable" style="height:100% "/>
      </div>
    </div>

  </div>
</template>

<script>
  import  "../assets/js/jquery.table2excel.min.js"
  import RiverInfoTable from "./waterInfoTable/RiverInfoTable";
  export default {
    name: "RainInfo2",
    components:{
      RiverInfoTable
    },
    data() {
      return {
        searchParameter:{
          startDateTime:'2006-08-12 18:00:00',
          endDateTime:'2008-09-14 06:00:00',
          options: [],
          rsvrOptions:[],
          riverOptions:[],

        },
        stationId:51131350,
        rsvrStationId:51112101,
        riverStationId:51112000,
        myChart:null,
        colors : ['#5470C6', '#d2d03f', '#91cc75','#4B0082','#DDA0DD'],

      }
    },
    methods:{
      //加载测站数据
      loadSTInfo:function(){
        this.axios.get("http://localhost:8083/waterInfo/rain/ST").then(
          response=>{
            console.log(response.data);
            this.searchParameter.options=response.data;
          }
        );
        this.axios.get("http://localhost:8083/waterInfo/RSVR/ST").then(
          response=>{
            console.log(response.data);
            this.searchParameter.rsvrOptions=response.data;
          }
        );
        this.axios.get("http://localhost:8083/waterInfo/river/ST").then(
          response=>{
            // alert(response.data);
            console.log(response.data);
            this.searchParameter.riverOptions=response.data;
          }
        );
      },
      //初始Echarts
      initEcharts:function(){
        let _this=this;
        this.myChart=this.$echarts.init(document.getElementById("rainEchartsContainer"));
        let option = {
          title:{
            text:"雨量数据"
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
            left:'12%',//设置偏移量
            //height:'50%',
          },
          tooltip: {
            trigger:"axis",
            //formatter:'{b0}<br />{a}.marker {a0}: {c0}m<br />{a1}: {c1}m<br />{a2}: {c2}mm',
            formatter:function (val) {
              //console.log(_this.myChart);
                //console.log(_this.axis[val[0].axisIndex]);
                //let str = val[0].name+'<br />';
                // for(let i in val){
                //   str+=val[i].marker+"  "+val[i].name
                // }

                return val[0].name+'<br />'
                  +val[0].marker+"  "+val[0].seriesName+":"+val[0].value+' mm'+'<br />'
                  +val[1].marker+"  "+val[1].seriesName+":"+val[1].value+' m'+'<br />'
                  +val[2].marker+"  "+val[2].seriesName+":"+val[2].value+' m'+'<br />';
            }
          },
          // toolbox: {
          //   show: true,
          //   showTitle:true,
          //   // shadowColor: 'rgba(255,255,255,1)' ,
          //   // textStyle:{
          //   //        color:'rgba(0,0,255,1)'
          //   // },
          //   iconStyle:{
          //     // color:'rgba(255,0,0,1)',
          //     borderColor:'rgba(0,0,0,1)',
          //   },
          //   feature: {
          //     saveAsImage: {},
          //     dataView: {
          //       show: true,
          //       title: '表格数据',
          //       lang: ['表格数据：', '关闭', '导出Excel'],    // 按钮
          //       //icon:"image://image/excel.png",             // ‘数据视图’按钮自定义img
          //       contentToOption: function (opt) {
          //
          //         $("#main").table2excel({           // 下载jquery.table2excel.js，引入，$("#main")是Echarts容器
          //           exclude: ".noExl", //过滤位置的 css 类名， 有class = “noExl” 的行不被导出
          //           filename: $("#STNM").val()+"站测量数据.xls", // 文件名称
          //           name: "Excel Document Name.xls",
          //           exclude_img: true,// 是否导出图片 false导出
          //           exclude_links: true,//是否导出链接 false 导出
          //           exclude_inputs: true// 是否导出输入框的值 true导出
          //         });
          //       },
          //       //数据视图展示为table
          //       optionToContent: function (opt) {
          //         var axisData = opt.xAxis[0].data;
          //         var series = opt.series;
          //         // tableName = opt.title[0].text;
          //         console.log(opt);
          //         console.log(series);
          //         var table = '<table style="width:100%;text-align:center"><tbody><tr>'
          //           + '<td>时间</td>';
          //         for(var k in series){
          //           table+='<td>' + series[k].name + '</td>'
          //
          //         }
          //
          //         table  += '</tr>';
          //         for (var i = 0, l = axisData.length; i < l; i++) {
          //           table += '<tr>'
          //             + '<td>' + axisData[i] +':00:00' +'</td>';
          //           for(var k in series){
          //             table+='<td>' + series[k].data[i] + '</td>';
          //           }
          //
          //           table += '</tr>';
          //         }
          //         table += '</tbody></table>';
          //         return table;
          //
          //       }
          //     },
          //
          //
          //
          //     myMinus:{
          //       show:true,
          //       title:'缩小',
          //       icon:'image://static/images/minus.png',
          //       onclick:function(){
          //
          //         console.log("minus");
          //
          //         _this.myChart.dispose();//销毁echarts
          //         console.log(this);
          //         $("#main").html("<div class=\"myMinus\"><div id=\"ck\" style=\";\" > 测试窗口 <a onclick=\"magnify()\" id=\"magnify\"><img src=\"../static/images/magnify.png\" alt=\"口\" style=\"width: 21px;height: 21px ;\"></a></div></div>");
          //
          //       }
          //     },
          //     myDispose:{
          //       show:true,
          //       title:"擦除",
          //       icon:'image://static/images/fork.png',
          //       onclick:function(){
          //         _this.myChart.dispose();//销毁echarts
          //         _this.myChart=null;
          //         // popup.close();
          //       }
          //     }
          //
          //
          //   }
          // },
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
            data:['雨量','库上水位','出库流量','水位','流量'],
            textStyle: {

              fontSize: 21

            }
          },
          xAxis: {

            data: [],
            //name:"时间",
            axisLine: {//x轴线的颜色以及宽度
              show: true,
              // position: "top",
              lineStyle: {
                color: "rgba(0,0,0,1)",
                width: 1,
                type: "solid"
              },


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
              name:"雨量",
              //是否倒置显示
              inverse:true,
              position:"left",
              // nameTextStyle:{//y轴上方单位的颜色
              //     color:'#151515'
              // },
              axisTick: {
                show:true,
              },
              nameLocation:'center',
              nameGap:45,
              // axisLabel:{
              //
              // },
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
                formatter: '{value}mm',
                //align:'center',
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
              nameLocation:'center',
              nameGap:40,
              // nameTextStyle:{
              //   verticalAlign:'bottom',
              // },
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

          series: [
            {
              name:"雨量",
              type:"bar",
              yAxisIndex: 0,
              data:[]
            },
            {
              name:"库上水位",
              type:"bar",
              yAxisIndex: 1,
              data:[]
            },
            {
              name:"出库流量",
              type:"bar",
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
        this.axios.get("http://localhost:8083/waterInfo/rain/getRainInfo/"+this.stationId
          +"/"+this.rsvrStationId
          +"/" +moment(this.searchParameter.startDateTime).format('YYYY-MM-DD HH:mm:ss')
          +"/"+moment(this.searchParameter.endDateTime).format('YYYY-MM-DD HH:mm:ss')
        ).then(
          response=>{


            let data = response.data;
            console.log(data)
            let date = [];//时间
            let Z=[];//降雨量
            let RZ=[];//库上水位

            let OTQ=[];//出库流量
            //日期格式

            // //最大降雨量
            let maxZ = 0;
            //最大水位
            let maxWater =0
            for(let i  in data){

              date.push(moment(data[i].TM).format('YYYY-MM-DD HH:mm:ss'));
              if(data[i].DRP!=null){
                Z.push(data[i].DRP);
                if(maxZ<data[i].DRP){
                  maxZ=data[i].DRP;
                }
              }else{
                Z.push(0);
              }
              if(data[i].RZ!=null){
                RZ.push(data[i].RZ)
                if(maxWater<data[i].RZ){
                  maxWater=data[i].RZ;
                }
              }else{
                RZ.push(0);
              }

              if(data[i].OTQ!=null){
                OTQ.push(data[i].OTQ);
                if(maxWater<data[i].OTQ){
                  maxWater=data[i].OTQ;
                }
              }
              else {
                OTQ.push(0);
              }





            }
            // //最大降雨量
            // let maxZ =Math.max(...Z)+1;
            // //最大水位
            // let maxOTQ =Math.max(...OTQ);
            // let maxRZ =Math.max(...RZ);
            // let maxWater =Math.max(maxOTQ,maxRZ)

            this.myChart.setOption({
              xAxis: {
                data: date,

              },
              yAxis: [

                {
                  type: 'value',
                  name:"雨量",
                  //是否倒置显示
                  inverse:true,
                  position:"left",
                  // nameTextStyle:{//y轴上方单位的颜色
                  //     color:'#151515'
                  // },
                  min: 0,
                  max: 2*maxZ,
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
                    formatter: '{value}mm',
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
                  max: 2*maxWater,
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
              ]
              ,
              series:[
                {
                  name:"雨量",
                  data:Z
                },
                {
                  name:"库上水位",
                  type:"line",
                  yAxisIndex: 1,
                  data:RZ
                },
                {
                  name:"出库流量",
                  type:"line",
                  yAxisIndex: 1,
                  data:OTQ
                },


              ]

            });
            for(let i in date){
              date[i]=moment(date[i]).format('YYYY-MM-DD');
            }
            this.$refs.RainInfoTable.loadData(["日期","雨量/mm","库上水位/m","出库流量/m³/s"],date,Z,RZ,OTQ);
          }
        );
      },
      loadData2:function() {
        this.axios.get("http://localhost:8083/waterInfo/rain/getRainInfo2/"+this.stationId
          +"/"+this.riverStationId
          +"/" +moment(this.searchParameter.startDateTime).format('YYYY-MM-DD HH:mm:ss')
          +"/"+moment(this.searchParameter.endDateTime).format('YYYY-MM-DD HH:mm:ss')
        ).then(
          response=>{

            let data = response.data;
            // alert(data[0].Z);
            console.log(data)
            let date = [];//时间
            let Z=[];//降水
            let RZ=[];//水位
            let QQ=[];//流量
            // let RZ=[];//库上水位

            // let OTQ=[];//出库流量
            //日期格式

            // //最大降雨量
            let maxZ = 0;
            //最大水位
            let maxWater =0;
            let maxQ =0;
            for(let i  in data){
              // alert(data[i].TM);
              date.push(moment(data[i].TM).format('YYYY-MM-DD HH:mm:ss'));
              if(data[i].DRP!=null){
                Z.push(data[i].DRP);
                if(maxZ<data[i].DRP){
                  maxZ=data[i].DRP;
                }
              }else{
                Z.push(0);
              }
              if(data[i].Z!=null){
                RZ.push(data[i].Z)
                if(maxWater<data[i].Z){
                  maxWater=data[i].Z;
                }
              }else{
                RZ.push(0);
              }

              if(data[i].Q!=null){
                QQ.push(data[i].Q);
                if(maxQ<data[i].Q){
                  maxQ=data[i].Q;
                }
              }
              else {
                QQ.push(0);
              }





            }
            // //最大降雨量
            // let maxZ =Math.max(...Z)+1;
            // //最大水位
            // let maxOTQ =Math.max(...OTQ);
            // let maxRZ =Math.max(...RZ);
            // let maxWater =Math.max(maxOTQ,maxRZ)

            this.myChart.setOption({
              xAxis: {
                data: date,

              },
              yAxis: [

                {
                  type: 'value',
                  name:"雨量",
                  //是否倒置显示
                  inverse:true,
                  position:"left",
                  // nameTextStyle:{//y轴上方单位的颜色
                  //     color:'#151515'
                  // },
                  min: 0,
                  max: 2*maxZ,
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
                    formatter: '{value}mm',
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
                  max: 2*maxWater,
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
                {
                  type: 'value',
                  name:"流量",
                  //是否倒置显示
                  inverse:true,
                  position:"right",
                  // nameTextStyle:{//y轴上方单位的颜色
                  //     color:'#151515'
                  // },
                  min: 0,
                  max: 2*maxQ,
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
                  name:"雨量",
                  data:Z
                },
                {
                  name:"水位",
                  type:"line",
                  yAxisIndex: 1,
                  data:RZ
                },
                {
                  name:"流量",
                  type:"line",
                  yAxisIndex: 1,
                  data:QQ
                },


              ]

            });
            for(let i in date){
              date[i]=moment(date[i]).format('YYYY-MM-DD');
            }
            // alert(RZ);
            this.$refs.RainInfoTable.loadData(["日期","雨量/mm","水位/m","流量/m³/s"],date,Z,RZ,QQ);
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

          if(item.stcd==stationId){

            this.$refs.RainInfoTable.exportCsv(item.stnm+"降雨信息");
            break;
          }
        }


      },
      //放大
      magnify:function () {
        let main =document.getElementById("mainRain");

        main.style.height="100%";
        main.style.width="100%";
        main.style.left='0px';
        main.style.top='0px';
        document.getElementById("rainEchartsContainer").style.height='500px';
        this.myChart.resize();
        document.getElementById("tableContainer").style.height='650px';
        document.getElementById("minusButton").style.display='inline-block';
        document.getElementById("magnifyButton").style.display='none';

      },
      //缩小
      minus:function () {
        let main =document.getElementById("mainRain");

        main.style.height="570px";
        main.style.width="895px";
        main.style.left='260px';
        main.style.top='100px';
        document.getElementById("rainEchartsContainer").style.height='390px';
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
      this.loadData2();
      let _this =this;
      let mainRain=document.getElementById("mainRain");
      let rainDataContainer=document.getElementById("rainDataContainer");
      let rainWindowHead = document.getElementById("rainWindowHead")
      rainDataContainer.onmousedown= function(event){
        _this.fndown(mainRain,event,rainDataContainer);
      }
      rainWindowHead.onmousedown= function(event){
        _this.fndown(mainRain,event,rainWindowHead);
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
  #rainEchartsContainer{
    margin-left: 20px;
    margin-top: 5px;
    width: auto;
    height:390px;
  }
  .mainContainer{
    position: relative;
    width: 62%;
    height: 520px;
    display: inline-block;

  }
  .tableContainer{

    width: 35%;
    height: 525px;
    display: inline-block;
    margin-bottom:10px ;
    margin-left: 20px;
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
