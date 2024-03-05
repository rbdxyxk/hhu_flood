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
      <el-tab-pane >
        <span slot="label"><i class="el-icon-date"></i>平均值</span>
        <RunoffGenData  ref="avg" />
      </el-tab-pane>
      <el-tab-pane v-for="(item,index) in stations" :label="item.stnm">
        <!--      {{item.stcd}}-->

        <RunoffGenData  :ref="`echart${item.stcd}`" :index="index">

        </RunoffGenData>
        <!--      <component is="">-->
        <!--  -->
        <!--      </component>-->
      </el-tab-pane>
      <!--    <el-button type="danger" size="mini"  @click="closeWindow"  icon="el-icon-close" circle></el-button>-->
    </el-tabs>
  </div>
</template>

<script>
  import RunoffGenData from "./Echarts/RunoffGenData";
  export default {
    name: "RunoffGenDataShow",
    components:{
      RunoffGenData,

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

      //将数据加载到tabs的各个标签中
      initTabEcharts:function(Data){

        //const  {RunoffGenData,rainData} = Data;
        const {avg,...rest}=Data;
        console.log(avg)
        console.log(rest)
        let avgcontainer=this.$refs["avg"];
        //console.log(this.$refs["avg"]);
        //console.log(this.$refs["avg"]);
        avgcontainer.loadDataForEchart(avg);
        for(let i in rest){
          let name='echart'+i;
          // console.log(name);
          // console.log(this.$refs)
          let a=this.$refs[name];
           //console.log(a)
          //调用子组件方法填充数据
          //loadData
          a[0].loadDataForEchart(rest[i]);
        }
      },
      //加载数据到本地
      loadData: function (Data) {

        this.dataMap=Data;
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
