<template>
  <div ref="e1" style="width: 600px ; height: 400px" >

  </div >
</template>

<script>
    export default {
        name: "RainInfo",
      data:function(){
          return{
            colors : ['#5470C6', '#d2d03f', '#91cc75'],
            myChart:null,
          }
      },
      methods:{
        initEcharts:function(){
          let _this=this;
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

                  +'24小时降雨量级：'+_this.rainLevel(val[1].value)+'<br />';
              }
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
              data:['时段降雨量','日降雨量'],
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


            ],

            series: [

              {
                name:"时段降雨量",
                type:"bar",
                yAxisIndex: 0,
                data:[]
              },
              {
                name:"日降雨量",
                type:"line",
                yAxisIndex: 0,
                data:[]
              },

            ]
          };
          this.myChart.setOption(option);
        },
        loadDataForEchart:function(data){
              //时间数据
              let tms=[];
              //时段降雨量
              let drps=[];
              //日降雨量
              let dyps=[];
              for (let i in data){
                tms.push(data[i].tm);
                if(data[i].drp!=null){
                  drps.push(data[i].drp);
                }else{
                  drps.push(0);
                }

                if(data[i].dyp!=null){
                  dyps.push(data[i].dyp);
                }else{
                  dyps.push(0);
                }

              }

          this.myChart.setOption({
            xAxis: {
              data:tms
            }
            ,
            series: [

              {
                name:"时段降雨量",
                type:"bar",
                yAxisIndex: 0,
                data:drps
              },
              {
                name:"日降雨量",
                type:"line",
                yAxisIndex: 0,
                data:dyps
              },

            ]
          })
        } ,
        //判断降雨量级:
        rainLevel(value){
          if(value<0.1){
            return "晴朗";
          }
          if(value<=4.9){
            return "小雨";
          }
          if(value<=9.9){
            return "小雨.中雨";
          }
          if(value<=16.9){
            return "中雨"
          }
          if(value<=24.9){
            return "中雨.大雨";
          }
          if(value<=38.0){
            return "大雨";

          }
          if(value<=49.9){
            return "大雨.暴雨";
          }
          if(value<75.0){
            return "暴雨";
          }
          if(value<100.0){
            return "暴雨.大暴雨";
          }
          if(value<150.0){
            return "大暴雨";
          }
          if(value<250.0){
            return "大暴雨.特大暴雨";
          }
          return "特大暴雨";

        },
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
