<template>
  <div style=" border-radius: 10px;">
    <div class="el-header " id="rainWindowHead" style="text-align: right">

      <el-button type="danger" size="mini"  @click="$emit('closewindow')"  icon="el-icon-close" circle></el-button>

    </div>
    <el-tabs type="card" >
      <el-tab-pane >
        <span slot="label"><i class="el-icon-date"></i>数据统计</span>
        <div id="echartsContainer" style="width: 600px ; height: 400px"></div>
      </el-tab-pane>
      <el-tab-pane v-for="(item,index) in stations" :label="item.stnm">
        <!--      {{item.stcd}}-->

        <RainInfo  :ref="`echart${item.stcd}`" :index="index">

        </RainInfo>
        <!--      <component is="">-->
        <!--  -->
        <!--      </component>-->
      </el-tab-pane>
      <!--    <el-button type="danger" size="mini"  @click="closeWindow"  icon="el-icon-close" circle></el-button>-->
    </el-tabs>
  </div>
</template>

<script>
  import RainInfo from "./Echarts/RainInfo";
    export default {
        name: "RainDataShow",
        components:{
          RainInfo,

        },
        data(){
          return{
            stations:[],
            myChart:null,
            dataNao:null,
            colors : ['#5470C6', '#d2d03f', '#91cc75'],
          }
        },
      methods:{
        initEcharts:function(){
          let _this=this;
          this.myChart=this.$echarts.init(document.getElementById("echartsContainer"));
          let option = {
            title:{
              text:"雨情信息"
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
              left:'19%',//设置偏移量

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
                    +val[0].marker+"  "+val[0].seriesName+":"+val[0].value+' mm'+'——'+_this.rainLevel12(val[0].value)+'<br />'
                    +val[1].marker+"  "+val[1].seriesName+":"+val[1].value+' mm'+'——'+_this.rainLevel24(val[1].value)+'<br />'
                    +val[2].marker+"  "+val[2].seriesName+":"+val[2].value+' mm'+'——'+_this.rainLevel72(val[2].value)+'<br />';
                   // +'24小时降雨量级：'+_this.rainLevel(val[1].value)+'<br />';
                }
              },

            dataZoom:[{
              type: 'inside',
              //show: true,
              xAxisIndex: [0],

              start: 1,
              end: 65
            },
              {
                type: 'slider',
                show: true,
                xAxisIndex: [0],
                start: 1,
                end: 65
              }
            ],
            legend: {
              data:['12小时降雨量','24小时降雨量','72小时降雨量'],
              textStyle: {

                fontSize: 15

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
                //   return moment(val).format("DD日HH:mm");
                // }
              },
              splitLine: {//分割线配置
                show:false,
                lineStyle: {
                  color: "rgba(219,225,255,1)",
                }
              }

            },
            yAxis:[
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
                name:"12小时降雨量",
                type:"bar",
                 yAxisIndex: 0,
                data:[]
              },
              {
                name:"24小时降雨量",
                type:"bar",
                yAxisIndex: 0,
                data:[]
              },
              {
                name:"72小时降雨量",
                type:"bar",
                yAxisIndex: 0,
                data:[]
              },

            ]
          };
          console.log(this.myChart)
          this.myChart.setOption(option);
        },
        //将数据加载到tabs的各个标签中
        initTabEcharts:function(Data){
            for(let i in Data){
              let name='echart'+i;
              // console.log(name);
              // console.log(this.$refs)
              let a=this.$refs[name];
              // console.log(a[0])
              //调用子组件方法填充数据
              //loadData
              a[0].loadDataForEchart(Data[i]);
            }
        },
        //加载数据到本地
          loadData: function (rainData) {
              console.log(rainData);
              let {DataMap,...RainDataByDay} =rainData;
              console.log(RainDataByDay);
              let data12 = [];
              let data24 = [];
              let data72 = [];
              let stcd=[];
              for(let i in RainDataByDay){
                  //如果没有数据则weinull

                  if(RainDataByDay[i]!=null){
                    data12.push(RainDataByDay[i]['12hour']);
                    data24.push(RainDataByDay[i]['24hour']);
                    data72.push(RainDataByDay[i]['72hour']);
                  }

                  for(let item in this.stations){
                    let st =this.stations[item]
                    if(st.stcd==i){
                      stcd.push(st.stnm);
                      break;
                    }

                  }
              }

            this.myChart.setOption({
              xAxis:{
                data:stcd,
              },
              series: [

                {
                  name:"12小时降雨量",
                  type:"bar",
                  yAxisIndex: 0,
                  data:data12
                },
                {
                  name:"24小时降雨量",
                  type:"bar",
                  //yAxisIndex: 0,
                  data:data24
                },
                {
                  name:"72小时降雨量",
                  type:"bar",
                 // yAxisIndex: 0,
                  data:data72
                },

              ]
              }

            );
            this.dataMap=DataMap;
          },
        //判断降雨量级:
        rainLevel24(value){
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
        rainLevel12(value){
          if(value<0.1){
            return "晴朗";
          }
          if(value<3.0){
            return "小雨";
          }
          if(value<5.0){
            return "小雨.中雨";
          }
          if(value<10.0){
            return "中雨"
          }
          if(value<15.0){
            return "中雨.大雨";
          }
          if(value<23.0){
            return "大雨";

          }
          if(value<30.0){
            return "大雨.暴雨";
          }
          if(value<50.0){
            return "暴雨";
          }
          if(value<70.0){
            return "暴雨.大暴雨";
          }
          if(value<105.0){
            return "大暴雨";
          }
          if(value<140.0){
            return "大暴雨.特大暴雨";
          }
          return "特大暴雨";

        },
        rainLevel72(value){
          if(value<0.1){
            return "晴朗";
          }
          if(value<13.0){
            return "小雨";
          }
          if(value<27.0){
            return "小雨.中雨";
          }
          if(value<55.0){
            return "中雨"
          }
          if(value<85.0){
            return "中雨.大雨";
          }
          if(value<130.0){
            return "大雨";

          }
          if(value<170.0){
            return "大雨.暴雨";
          }
          if(value<240.0){
            return "暴雨";
          }
          if(value<310.0){
            return "暴雨.大暴雨";
          }
          if(value<380.0){
            return "大暴雨";
          }
          if(value<480.0){
            return "大暴雨.特大暴雨";
          }
          return "特大暴雨";

        },

      },
      mounted() {
          this.initEcharts();

      },
      updated() {
        this.initTabEcharts(this.dataMap);
      }


    }
</script>

<style scoped>

</style>
