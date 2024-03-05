<template>
  <div ref="e1" style="width: 600px ; height: 400px" >

  </div >
</template>

<script>
  export default {
    name: "RsvrInfo",
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
            left:'8%',//设置偏移量

          },
          tooltip: {
            trigger:"axis"
          },

          dataZoom:[{
            type: 'inside',
            //show: true,
            xAxisIndex: [0],

            start: 1,
            end:100
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
            data:['库容','出库流量','入库流量'],
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
              name:"库容",
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
                formatter: '{value}10⁶m³',
                textStyle:{
                  fontsize:9,
                  color:this.colors[0]
                }
              }

            },
            {
              type: 'value',
              name:"流量",
              //是否倒置显示
              inverse:false,
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
                formatter: '{value}m³',
                textStyle:{
                  fontsize:9,
                  color:this.colors[0]
                }
              }

            },


          ],

          series: [

            {
              name:"库容",
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
              name:"出库流量",
              type:"line",
              yAxisIndex: 1,
              data:[]
            },

          ]
        };
        this.myChart.setOption(option);
      },
      loadDataForEchart:function(data){

        //时间数据
        let tms=[];
        //水库库容
        let ws=[];
        //入库流量
        let inqs=[];
        //出库流量
        let otqs=[];
        for (let i in data){
          tms.push(data[i].tm);
          if(data[i].w!=null){
            ws.push(data[i].w);
          }else{
            ws.push(0);
          }

          if(data[i].inq!=null){
            inqs.push(data[i].inq);
          }else{
            inqs.push(0);
          }
          otqs.push(data[i].otq);
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
              name:"库容",
              type:"bar",
              yAxisIndex: 0,
              data:ws
            },
            {
              name:"入库流量",
              type:"line",
              yAxisIndex: 1,
              data:inqs
            },
            {
              name:"出库流量",
              type:"line",
              yAxisIndex: 1,
              data:otqs
            }

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
