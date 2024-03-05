<template>

  <el-form :inline="true"  style="width: 350px"  class="chooser_form" size="medium"  label-width="80px">

    <el-form-item  label="起始时间:">

      <el-date-picker

        v-model="searchParameter.startDateTime"
        type="datetime"
        placeholder="选择日期时间"
        default-time="12:00:00">
      </el-date-picker>
    </el-form-item>
    <el-form-item  label="结束时间:">

      <el-date-picker

        v-model="searchParameter.endDateTime"
        type="datetime"
        placeholder="选择日期时间"
        default-time="12:00:00">
      </el-date-picker>
    </el-form-item>
    <!--      <el-form-item label="终止时间:">-->
    <!--        <el-date-picker-->

    <!--          v-model="searchParameter.endDateTime"-->
    <!--          type="datetime"-->
    <!--          placeholder="选择日期时间"-->
    <!--          default-time="12:00:00">-->
    <!--        </el-date-picker>-->
    <!--      </el-form-item>-->


    <el-form-item label="水库:">
      <el-select v-model="stationId" placeholder="请选择" >
        <el-option
          v-for="item in searchParameter.options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>



    <el-form-item label="水库测站:">
      <el-select  v-model="checked_stations" collapse-tags multiple  placeholder="请选择">
        <el-option
          v-for="item in stations"
          :key="item.stcd"
          :label="item.stnm"
          :value="item.stcd">
        </el-option>
      </el-select>
    </el-form-item>


    <el-form-item  style="text-align: center">




      <el-button type="primary"  style="margin-left: 60px"   @click="getRainData" icon="el-icon-search">查询</el-button>



      <el-button type="danger"   @click="$emit('closechooser')" icon="el-icon-search">关闭</el-button>



    </el-form-item>

  </el-form>
</template>

<script>


  export default {
    name: "rsvrChooser",
    components:{

    },
    data() {
      return {
        searchParameter:{
          startDateTime:'2006-05-5 18:00:00',
          //'2013-07-27 08:00:00',
          endDateTime:'2006-08-5 18:00:00',
          options: [{
            value: 41814801,
            label: '石梁河水库'
          }
            // , {
            //   value: 41816401,
            //   label: '团旺',
            // }, {
            //   value: 41814701,
            //   label: '福山'
            // }
          ],

        },

        stationId:41814801,

        checkAll:false,
        isIndeterminate:true,
        stations:[],
        checked_stations:[],
        myChart:null,
        colors : ['#5470C6', '#d2d03f', '#91cc75'],
        checkedAll: false,
        // 设置选项复选框初始状态为未选中,由于是一个数组,所以值为[],即为空
        checkedItem: [],
        // 设置选中全选复选框后重新给选项复选框赋值,即选中所有的选项复选框
        checkedAttr: ["1", "2", "3", "4", "5"],
      }
    },
    methods:{

      //关闭选择框打开展示页面
      openRsvrDataShow:function(data){
        // console.log(data);
        this.$emit('openRsvrDataShow',this.checked_stations,this.stations,data)
      },
      // //全选
      // handleCheckAllChange:function(val){
      //
      //   this.checked_rain_stations = val ? this.rain_stations : [];
      //
      //   this.isIndeterminate=false;
      // },
      // //选择变化
      // handleCheckedStationChange:function(value){
      //   let checkedCount = value.length;
      //   console.log(this.checked_rain_stations)
      //   this.checkAll = checkedCount === this.rain_stations.length;
      //   this.isIndeterminate = checkedCount > 0 && checkedCount < this.rain_stations.length;
      // },

      //加载雨量站
      loadRainStations:function(){

        this.axios.get("http://localhost:8083/waterInfo/RSVR/ST").then(
          response=>{
            console.log(response.data);
            this.stations=response.data;

          }
        );
      },
      //获取所有的雨量数据
      getRainData(){
        this.axios.get("http://localhost:8083/RSVR/"+moment(this.searchParameter.startDateTime).format('YYYY-MM-DD HH:mm:ss')+"/"+moment(this.searchParameter.endDateTime).format('YYYY-MM-DD HH:mm:ss')+"/querries?list="+this.checked_stations).then(
          response=>{
            this.openRsvrDataShow(response.data);
          }
        )
      },



      // //移动相关
      // fndown:function(main,event,container,){
      //   let _this = this;
      //   event = event||window.event;
      //   let disX = event.clientX - main.offsetLeft,//记录鼠标相对于矩形的位置
      //     disY =event.clientY - main.offsetTop;
      //   document.onmousemove = function(event){
      //
      //     event = event||window.event;
      //     _this.fnMove(event,disX,disY,main)
      //   };
      //   container.onmouseup = function(){//松开鼠标后无法拖动
      //
      //     document.onmousemove = null;
      //     container.onmouseup = null ;
      //   }
      //
      //
      // },
      // fnMove:function(event,disX,disY,main){//鼠标移动事件
      //   let l = event.clientX - disX,//根据鼠标的位置改变，来改变矩形相对于body的位置
      //     t = event.clientY - disY;
      //   main.style.left=l+'px';
      //   main.style.top=t+'px';
      // },


    },
    mounted() {

      this.loadRainStations();
      let _this =this;
      // let main = document.getElementById("main");
      // // let dataContainer = document.getElementById("dataContainer");
      // let head= document.getElementById("windowHead");
      // dataContainer.onmousedown= function(event){
      //
      //   _this.fndown(main,event,dataContainer);
      // }
      // head.onmousedown= function(event){
      //
      //   _this.fndown(main,event,head);
      // }

    },
    // watch: {
    //   // 监听复选框项是否全部选中
    //   checkedItem() {
    //     // 判断选中选项复选框的值数组的长度是否与设定的长度相等，如果相等则所有的选项复选框被选中，否则没有被选中。
    //     if (this.checkedItem.length == this.checkedAttr.length) {
    //       // 如果所有复选框项全部被选中,则全选复选框也被勾选
    //       this.checkedAll = true;
    //     } else {
    //       // 否则复选框不勾选
    //       this.checkedAll = false;
    //     }
    //   },
    // },
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
  .chooser_form>*{
    margin-top: 9px;
    margin-bottom: 3px;
  }
  .chooser_form{
    border-radius: 5px;
  }
</style>
