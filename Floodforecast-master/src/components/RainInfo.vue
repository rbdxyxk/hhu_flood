<template>
  <div style="width: 600px; height:500px " id="main">

  </div>
</template>

<script>
import  "../assets/js/jquery.table2excel.min.js"
    export default {
        // name: "raininfo",
      props:["STNM"],
      components:{

      },
        data(){
          return{
            colors : ['#5470C6', '#d2d03f', '#91cc75'],

            myChart:null,
          }
        } ,

      methods:{
        initEcharts:function(){
          let _this=this;
          this.myChart=this.$echarts.init(document.getElementById("main"));
          let option = {
            title:{
              text:"测试站数据"
            },

            backgroundColor:'rgba(128, 128, 128, 0.5)',
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

                    $("#main").table2excel({           // 下载jquery.table2excel.js，引入，$("#main")是Echarts容器
                      exclude: ".noExl", //过滤位置的 css 类名， 有class = “noExl” 的行不被导出
                      filename: $("#STNM").val()+"站测量数据.xls", // 文件名称
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



                myMinus:{
                  show:true,
                    title:'缩小',
                    icon:'image://static/images/minus.png',
                    onclick:function(){

                    console.log("minus");

                      _this.myChart.dispose();//销毁echarts
                    console.log(this);
                    $("#main").html("<div class=\"myMinus\"><div id=\"ck\" style=\";\" > 测试窗口 <a onclick=\"magnify()\" id=\"magnify\"><img src=\"../static/images/magnify.png\" alt=\"口\" style=\"width: 21px;height: 21px ;\"></a></div></div>");

                  }
                },
                myDispose:{
                  show:true,
                    title:"擦除",
                    icon:'image://static/images/fork.png',
                    onclick:function(){
                      _this.myChart.dispose();//销毁echarts
                      _this.myChart=null;
                    // popup.close();
                  }
                }


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
              data:['降水量','流量','水位'],
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
                name:"降水量",
                min:0,
                max:50,

                position:"left",
                //data:[50,40,30,20,10,0],
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
                    color: "rgba(219,225,255,1)",
                  }
                },
                axisLabel: {
                  formatter: '{value}mm',
                  textStyle:{
                    fontsize:9,
                    //color:colors[0]
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
                  formatter: '{value}m3/s',
                  textStyle:{
                    fontsize:9,
                    color:this.colors[1]
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
                offset:40,
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
                name:"流速",
                type:"bar",
                yAxisIndex: 0,
                data:[]
              },
              {
                name:"水位",
                type:"line",
                yAxisIndex: 1,
                data:[]
              },
              {
                name:"流量",
                type:"line",
                yAxisIndex: 2,
                data:[]
              }
            ]
          };
          this.myChart.setOption(option);
        },
        //加载数据
        loadData:function(STNM) {
            this.axios.get("http://localhost:8083/STTest/info/"+STNM).then(
              response=>{
                console.log(response.data);
                let data = response.data;
                let date = [];//时间
                let Z=[];//水位
                let Q=[];//流量
                let DRP=[];//降水量

                for(let i  in data){

                  date.push(data[i].TM);//计算区域
                  Z.push(data[i].z)

                  Q.push(data[i].q);
                  DRP.push(data[i].drp);
                }

                this.myChart.setOption({
                  xAxis: {
                    data: date,

                  },
                  series:[
                    {
                      name:"流速",
                      data:DRP
                    },
                    {
                      name:"水位",
                      data:Z
                    },
                    {
                      name:"流量",
                      data:Q
                    }
                  ]

                });
              }
            );
        },
        loadData1(stationID){
          this.stationId=stationID
          this.loadData()
        },
        clearData:function(){


          this.myChart.setOption({
            xAxis: {
              data: [],

            },
            series: [
              {
                name:"降水量",

                data:[]
              },
              {
                name:"水位",
                data:[]
              },
              {
                name:"流量",

                data:[]
              }
            ]});



        },
        //销毁
        removeEchart:function () {
            this.myChart.dispose();
            this.myChart=null;
        },
        //移动相关
        fndown:function(event,main){
          let _this = this;
          event = event||window.event;
          let disX = event.clientX - main.offsetLeft,//记录鼠标相对于矩形的位置
            disY =event.clientY - main.offsetTop;
          document.onmousemove = function(event){
            event = event||window.event;
            _this.fnMove(event,disX,disY,main)
          };
          main.onmouseup = function(){//松开鼠标后无法拖动
            document.onmousemove = null;
            main.onmouseup = null ;
          }


        },
        fnMove:function(event,disX,disY,main){//鼠标移动事件
          let l = event.clientX - disX,//根据鼠标的位置改变，来改变矩形相对于body的位置
            t = event.clientY - disY;
          main.style.left=l+'px';
          main.style.top=t+'px';
        },
         magnify(){
              // $("#main").html("<div id=\"myName\"></div>")
              // myChart.setOption(option);
              // 基于准备好的dom，初始化echarts实例
              $("#main").html("");
             this.initEcharts();
             this.loadData(this.STNM);
            },


      },
      mounted() {
        this.initEcharts();
        this.loadData('福山');
        //移动效果
        let main =document.getElementById("main");
        // console.log(main);
        //var  body= document.body;
        let _this=this;
        main.onmousedown= function(event){
          _this.fndown(event,main);
        }
      },

    }
</script>

<style scoped>

</style>
