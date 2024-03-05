<template>
  <div ref="e1" style="width: 600px ; height: 400px" >

  </div>
</template>

<script>
  export default {
    name: "RunoffGenData",
    data:function(){
      return{
        colors : ['#5470C6', '#d2d03f', '#91cc75'],
        myChart:null,
      }
    },
    methods:{
      initEcharts:function(){
        //let _this=this;
        this.myChart=this.$echarts.init(this.$refs.e1);
        let option = {
          title:{
            text:"产流数据"
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
                +val[1].marker+"  "+val[1].seriesName+":"+val[1].value+' mm'+'<br />'


            }
          },

          dataZoom:[
            {
              type: 'slider',
              show: true,
              xAxisIndex: [0],
              start: 1,
              end: 5
            }
          ],
          legend: {
            data:['产流深','雨量'],
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
              // formatter(val){
              //   //return moment(val).format("DD日");
              // }
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
              name:"雨量/产流深",
              //是否倒置显示
              inverse:false,
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
                formatter: '{value}mm',
                textStyle:{
                  fontsize:9,
                  color:this.colors[2]
                }
              }

            },



          ],

          series: [
            //data:['总过闸流量','闸上水位 ','闸下水位'],
            {
              name:"产流深",
              type:"bar",
              yAxisIndex: 0,
              data:[]
            },
            {
              name:"雨量",
              type:"bar",
              yAxisIndex: 0,
              data:[]
            },


          ]
        };
        this.myChart.setOption(option);
      },
      loadDataForEchart:function(RunoffGenDatas){

        //时间数据
        let tms=[];
        //雨量
        let rains=[];
        //产流量
        let rgs=[];

        for (let i in RunoffGenDatas) {
          let RunoffGenData = RunoffGenDatas[i];

          tms.push(RunoffGenData.date);
          rains.push(RunoffGenData.rain);
          rgs.push(RunoffGenData.runoffGen);


        }






        this.myChart.setOption({
          xAxis: {
            data:tms
          },
          dataZoom:[
            {
              type: 'slider',
              show: true,
              xAxisIndex: [0],
              start: 0,
              end: 1300/tms.length
            }
          ]
          ,
          series: [


            {
              name:"产流深",
              type:"bar",
              yAxisIndex: 0,
              data:rgs
            },
            {
              name:"雨量",
              type:"bar",
              yAxisIndex: 0,
              data:rains
            },

          ]
        })
      } ,
    },
    mounted() {
      this.initEcharts();
    }

  }
</script>

<style scoped>
  /*.chooser_form{*/
  /*  border-radius: 5px;*/
  /*}*/
</style>
