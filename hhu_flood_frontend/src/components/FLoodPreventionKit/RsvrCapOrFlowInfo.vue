<template>
  <div style=" border-radius: 10px;">
    <div class="el-header " id="rainWindowHead" style="text-align: right">

      <el-button type="danger" size="mini"  @click="$emit('closewindow')"  icon="el-icon-close" circle></el-button>

    </div>
    <el-tabs type="card" >
<!--      <el-tab-pane >-->
<!--        <span slot="label"><i class="el-icon-date"></i>数据统计</span>-->
<!--        <div id="echartsContainer" style="width: 600px ; height: 400px"></div>-->
<!--      </el-tab-pane>-->
      <el-tab-pane v-for="(item,index) in stations" :label="item.stnm">
        <!--      {{item.stcd}}-->

        <RsvrInfo  :ref="`echart${item.stcd}`" :index="index">

        </RsvrInfo>
        <!--      <component is="">-->
        <!--  -->
        <!--      </component>-->
      </el-tab-pane>
      <!--    <el-button type="danger" size="mini"  @click="closeWindow"  icon="el-icon-close" circle></el-button>-->
    </el-tabs>
  </div>
</template>

<script>
  import RsvrInfo from "./Echarts/RsvrInfo";
  export default {
    name: "RainDataShow",
    components:{
      RsvrInfo,

    },
    data(){
      return{
        stations:[],
        myChart:null,
        dataMap:null,
        colors : ['#5470C6', '#d2d03f', '#91cc75'],
      }
    },
    methods:{
      // initEcharts:function(){
      //   //let _this=this;
      //   this.myChart=this.$echarts.init(document.getElementById("echartsContainer"));
      //   let option = {
      //     title:{
      //       text:"雨情信息"
      //     },
      //     borderWidth:3,
      //     borderColor:'rgb(44,70,226)',
      //     backgroundColor:'rgb(255,255,255)',
      //     textStyle: {
      //       color:'rgba(0, 0, 0, 1)',
      //       // fontSize:3000,
      //       fontWeight:'bolder',
      //       fontFamily:'Arial',
      //     },
      //
      //     grid:{
      //       left:'19%',//设置偏移量
      //
      //     },
      //     tooltip: {
      //       trigger:"axis"
      //     },
      //
      //     dataZoom:[{
      //       type: 'inside',
      //       //show: true,
      //       xAxisIndex: [0],
      //
      //       start: 1,
      //       end: 65
      //     },
      //       {
      //         type: 'slider',
      //         show: true,
      //         xAxisIndex: [0],
      //         start: 1,
      //         end: 65
      //       }
      //     ],
      //     legend: {
      //       data:['12小时降雨量','24小时降雨量','72小时降雨量'],
      //       textStyle: {
      //
      //         fontSize: 15
      //
      //       }
      //     },
      //     xAxis: {
      //
      //       data: [],
      //       //name:"时间",
      //       axisLine: {//x轴线的颜色以及宽度
      //         show: true,
      //         // position: "top",
      //         lineStyle: {
      //           color: "rgba(0,0,0,1)",
      //           width: 1,
      //           type: "solid"
      //         },
      //
      //
      //       },
      //
      //       axisLabel: {//x轴文字的配置
      //         show: true,
      //         textStyle: {
      //           color: "rgba(0,0,0,1)",
      //           fontSize: 13
      //         },
      //         // formatter(val){
      //         //   return moment(val).format("DD日HH:mm");
      //         // }
      //       },
      //       splitLine: {//分割线配置
      //         show:false,
      //         lineStyle: {
      //           color: "rgba(219,225,255,1)",
      //         }
      //       }
      //
      //     },
      //     yAxis:[
      //       {
      //         type: 'value',
      //         name:"雨量",
      //         //是否倒置显示
      //         inverse:false,
      //         position:"left",
      //         // nameTextStyle:{//y轴上方单位的颜色
      //         //     color:'#151515'
      //         // },
      //         axisTick: {
      //           show:true,
      //         },
      //         axisLine: {
      //           show: true,
      //           lineStyle: {
      //             color: this.colors[0]
      //           }
      //         },
      //
      //         splitLine: {//分割线配置
      //           show:false,
      //           lineStyle: {
      //             color: this.colors[0],
      //           }
      //         },
      //         axisLabel: {
      //           formatter: '{value}mm',
      //           textStyle:{
      //             fontsize:9,
      //             color:this.colors[0]
      //           }
      //         }
      //
      //       },
      //     ],
      //
      //
      //
      //
      //
      //
      //     series: [
      //
      //       {
      //         name:"12小时降雨量",
      //         type:"bar",
      //         yAxisIndex: 0,
      //         data:[]
      //       },
      //       {
      //         name:"24小时降雨量",
      //         type:"bar",
      //         yAxisIndex: 0,
      //         data:[]
      //       },
      //       {
      //         name:"72小时降雨量",
      //         type:"bar",
      //         yAxisIndex: 0,
      //         data:[]
      //       },
      //
      //     ]
      //   };
      //   console.log(this.myChart)
      //   this.myChart.setOption(option);
      // },
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
      loadData: function (rsvrData) {
        //console.log(rainData);
        //let {DataMap,...RainDataByDay} =rainData;
       // console.log(RainDataByDay);
       //  let data12 = [];
       //  let data24 = [];
       //  let data72 = [];
       //  let stcd=[];
       //  for(let i in RainDataByDay){
       //    //如果没有数据则weinull
       //
       //    if(RainDataByDay[i]!=null){
       //      data12.push(RainDataByDay[i]['12hour']);
       //      data24.push(RainDataByDay[i]['24hour']);
       //      data72.push(RainDataByDay[i]['72hour']);
       //    }
       //
       //    for(let item in this.stations){
       //      let st =this.stations[item]
       //      if(st.stcd==i){
       //        stcd.push(st.stnm);
       //        break;
       //      }
       //
       //    }
       //  }
       //
       //  this.myChart.setOption({
       //      xAxis:{
       //        data:stcd,
       //      },
       //      series: [
       //
       //        {
       //          name:"12小时降雨量",
       //          type:"bar",
       //          yAxisIndex: 0,
       //          data:data12
       //        },
       //        {
       //          name:"24小时降雨量",
       //          type:"bar",
       //          //yAxisIndex: 0,
       //          data:data24
       //        },
       //        {
       //          name:"72小时降雨量",
       //          type:"bar",
       //          // yAxisIndex: 0,
       //          data:data72
       //        },
       //
       //      ]
       //    }
       //
       //  );
        this.dataMap=rsvrData;
      }

    },
    mounted() {
      //this.initEcharts();

    },
    updated() {
      this.initTabEcharts(this.dataMap);
    }


  }
</script>

<style scoped>

</style>
