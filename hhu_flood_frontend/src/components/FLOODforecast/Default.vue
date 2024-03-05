<template>
  <!--  <div style=" height:570px; width:980px" id="main">-->
  <div style="width: 45%;text-align:center" id="main">
    <el-row>
      <div class="el-header " id="windowHead" style="text-align: right">
        <el-button type="infer" size="mini"  icon="el-icon-minus" id="minusButton" @click="minus" circle></el-button>
        <el-button type="infer" size="mini"  icon="el-icon-rank" id="magnifyButton" @click="magnify" circle></el-button>
        <el-button type="danger" size="mini"  @click="closeChooser"  icon="el-icon-close" circle></el-button>
      </div>
    </el-row>
    <el-row>
      <el-col :span="24">
        <div class="el-container" >
          <el-row style="width: 100%">
            <el-col :span="15">
              <div class="mainContainer">
                <el-row><div class="dataContainer" id="dataContainer">

                  <form id="form1" runat="server">

                    <div>
                      <Label ID="Label1" runat="server" Style="left: 5%; position: absolute; top: 260px; z-index: 101;"
                                 Text="最近3日实测降雨(mm)" Width="186px" BackColor="White" Font-Size="Medium">最近3日实测降雨(mm)</Label>
                      <table id="Table1" rules="all" border="1" Style="left: 5%; position: absolute; top: 285px; z-index: 200;"
                             Text="最近3日实测降雨(mm)" width="600px"  Font-Size="Medium">
                        <tr align="center" style="background-color:LightBlue;">
                          <td>站名(站码)</td><td class="time">{{nowtime}}</td><td>{{nowtime1}}</td><td>{{nowtime2}}</td>
                        </tr><tr align="center">
                        <td>石门(51131350)</td><td>0</td><td>0</td><td>0</td>
                      </tr><tr align="center">
                        <td>临沭(51131300)</td><td>0</td><td>0</td><td>0</td>
                      </tr><tr align="center">
                        <td>张疃(51112500)</td><td>0</td><td>0</td><td>0</td>
                      </tr><tr align="center">
                        <td>石梁河水库(51112101)</td><td>0</td><td>0</td><td>0</td>
                      </tr><tr align="center">
                        <td>大兴镇(51112000)</td><td>0</td><td>0</td><td>0</td>
                      </tr>
                      </table>

                                      <div id = "a" style="z-index: 106; left: 5%; width: 50%; position: absolute; top: 425px;
                                  height: 115px">
                                        <input type="text" ID="TextBox1" runat="server" Style="z-index: 100; left: 60%; position: absolute;
                                  top: 18px;width: 88px;"  ForeColor="Black"  v-model="form.mi"></input>
                                        <Label ID="RvLab1" runat="server" Style="z-index: 101; left: 3%; position: absolute;
                                  top: 18px" Text="库水位：" Width="10%" Font-Bold="True">石梁河水库  库水位：</Label>
                                        <Label ID="RvLab2" runat="server" Style="z-index: 104; left: 30%; position: absolute;
                                  top: 43px" Text="（时间）" Width="10%" Font-Size="Small">（时间）</Label>
                                      </div>


                                              <div runat="server" style="z-index: 100; border-left-color: blue; left: 5%; border-bottom-color: blue;
                                    width: 113%; border-top-style: dotted; border-top-color: blue; border-right-style: dotted;
                                    border-left-style: dotted; position: absolute; top: 16px; height: 235px; background-color: transparent;
                                    border-right-color: blue; border-bottom-style: dotted" id="Div1">


                                                <Label ID="AutoLab1" runat="server" Style="left: 4px; position: absolute; top: 29px; z-index: 106;background-color: white;border-color: black" Text="自动预报状态："  BackColor="White" Font-Size="Large" BorderColor="#C0C0FF">自动预报状态：</Label>&nbsp;


                                                <Label ID="AutoLab2" runat="server" Style="left: 30%; position: absolute; top: 28px; z-index: 110;background-color: lightgreen;border-color: black;display:inline-block;" Text="开启状态" BackColor="LightGreen" BorderColor="#C0C0FF">开启状态</Label>
                                                <Label ID="AutoLab22" runat="server" Style="left: 30%; position: absolute; top: 28px; z-index: 110;background-color: lightgreen;border-color: black;display:none;" Text="关闭状态" BackColor="LightGreen" BorderColor="#C0C0FF">关闭状态</Label>


                                            <el-button id="Button1" runat="server" Style="left: 67%; position: absolute; top: 27px; z-index: 1;display:inline-block;" Text="开启自动预报"  @click="at()" Width="10%" Font-Bold="True" >

                                                开启自动预报

                                              </el-button>

                                                <el-button id="Autton1" runat="server" Style="left: 65%; position: absolute; top: 27px; z-index: 1;display:inline-block;" Text="关闭自动预报" @click="bt()" Width="15%" Font-Bold="True" >

                                                  关闭自动预报

                                              </el-button>


                                                <Label ID="AutoLab3" runat="server" Style="left: -391px; position: absolute; top: 88px; z-index: 108;" Text="最近自动预报时间："></Label>

                                                <Label ID="Label4" runat="server" Style="z-index: 106; left: 1%; position: absolute;background-color: lightgreen;border-color: black;
                                        top: 67px" Text="读取上次自动预报计算时间" Width="23%" BackColor="LightGreen" BorderStyle="Ridge" BorderColor="White" Font-Size="Small">{{time0}}</Label>

<!--                                                <Label ID="Label4" runat="server" Style="z-index: 112; left: 23%; position: absolute;background-color: lightgreen;border-color: black;-->
<!--                                        top: 67px" Text="读取上次自动预报计算时间" Width="23%" BackColor="LightGreen" BorderStyle="Ridge" BorderColor="White" Font-Size="Small">读取上次自动预报计算时间</Label>-->

                                                <Label ID="Label2" runat="server" Style="z-index: 108; left: 4px; position: absolute;background-color: white;border-color: black;
                                        top: 141px" Text="当前计算方案参数：" BackColor="White" Font-Size="Large" BorderColor="#C0C0FF">当前计算方案参数：</Label>

                                                <router-link to="/Forecast/para">
                                                <el-button ID="Button3" runat="server" Style="left: 65%; position: absolute; top:141px; z-index: 109;" Text="查看参数详细信息" @click="closeChooser()" Width="133px" Font-Bold="True">查看参数详细信息</el-button>
                                                </router-link>

<!--                                                <Label ID="Label3" runat="server" Style="z-index: 112; left: 23%; position: absolute;background-color: lightgreen;border-color: black;-->
<!--                                        top: 140px" Text="预报参数" Width="24%" BackColor="LightGreen" BorderStyle="Ridge" BorderColor="#C0C0FF">预报参数</Label>   -->
<!--                                                -->
                                                <Label ID="Label3" runat="server" Style="z-index: 112; left: 30%; position: absolute;background-color: lightgreen;border-color: black;
                                        top: 140px" Text="预报参数" Width="24%" BackColor="LightGreen" BorderStyle="Ridge" BorderColor="#C0C0FF">开发模型调试参数方案</Label>
                                              </div>

                                              <Table ID="Table1" runat="server" GridLines="Both" Height="62px" Style="left: 5%;background-color: white;border-color: black ;
                                    position: absolute; top: 304px; z-index: 102;" Width="50%" BackColor="White" BorderColor="ActiveCaption">
                                              </Table>

                                            </div>


                                            <div id="photo" style="width: 80%;height:500px;left:50px;top:500px"></div>


                                          </form>

                                        </div></el-row>
                                      </div>
                                    </el-col>
<!--                                    <el-col :span="8">-->
<!--                                      <div class="tableContainer" style="width: 360px" id="tableContainer">-->
                        <!--                <img ID="Image1" runat="server" Height="484px" src="/static/images/shiLiuyuS.JPG" alt="Image1"-->
<!--                     Style="left: 60%; position: absolute; top: 11px; z-index: 100;" Width="30%" BorderColor="#C0C0FF" BorderStyle="Solid" BorderWidth="1px" />-->

<!--                <div style="z-index: 106; left: 57%; width: 50%; position: absolute; top: 394px;-->
<!--            height: 115px">-->
<!--                  <input type="text" ID="TextBox1" runat="server" Style="z-index: 100; left: 61%; position: absolute;-->
<!--            top: 18px;width: 50px;"  ForeColor="Black"></input>-->
<!--                  <Label ID="RvLab1" runat="server" Style="z-index: 101; left: 40%; position: absolute;-->
<!--            top: 18px" Text="库水位：" Width="10%" Font-Bold="True">石梁河水库  库水位：</Label>-->
<!--                  <Label ID="RvLab2" runat="server" Style="z-index: 104; left: 53%; position: absolute;-->
<!--            top: 43px" Text="（时间）" Width="10%" Font-Size="Small">（时间）</Label>-->
<!--                </div>-->

<!--              </div>-->
<!--            </el-col>-->
          </el-row>

        </div>
      </el-col>
    </el-row>

  </div>

</template>

<script>
import  "../../assets/js/jquery.table2excel.min.js"
import  "../../assets/js/jquery-1.8.2.min.js"
import  "../../assets/js/echarts.js"
import RiverInfoTable from "../waterInfoTable/RiverInfoTable";
export default {
  name: "waterInfo",
  data(){
    return {
          form: {
            mi:"24.48米",
            },
          currentTime: new Date().toLocaleString(),
          nowtime:"",
          nowtime1:"",
          nowtime2:"",
          time0:"",
         };
  },

  components:{
    RiverInfoTable
  },
  methods:{
    //Default
    selfrefresh:function(){
      window.self.location.reload();
    },

    fetchData: function(){
      this.axios.get("http://localhost:8083/anaResult/findAuto").then(
        response=>{
          this.time0=response.data;
        }
      );
    },
    // addTimer: function() {
    //   let  date = new Date();
    //   if (date.getMinutes === 5) {
    //     setInterval(() => {
    //       this.axios.get("http://localhost:8083/anaResult/findAuto").then(
    //         response=>{
    //             this.time0=response.data;
    //         }
    //       );
    //     }, 3900000)
    //   }
    // },

    freshTest:function(){
      setTimeout("selfrefresh()",299600);//1000为1秒
    },
//     if(vvv[0] != null){
//   drawPicture();
//
//  }

    time:function(){
     new Date().getDate();
    },

    bt:function(){
      // $("#Autton1").on("click",function(){
        $('#Button1').css("display","inline-block");
        $('#Autton1').css("display","none");
        $('#AutoLab2').css("display","none");
       $('#AutoLab22').css("display","inline-block");
       $('#Table1').css("display","none");
      // });
    },

    at:function(){
      // $("#Button1").on("click",function(){
        $('#Autton1').css("display","inline-block");
        $('#Button1').css("display","none");
       $('#AutoLab22').css("display","none");
      $('#AutoLab2').css("display","inline-block");
      $('#Table1').css("display","");
      // });
    },

// var myChart;
    drawPicture:function(){
      //获取div的宽高
      $('#photo').width($('#photo').width());
      $('#photo').height($('#photo').height());

      let myChart;
      // 基于准备好的dom，初始化echarts实例
      myChart = echarts.init(document.getElementById('photo'));


      // 指定图表的配置项和数据
      var option = {
        title: {
          text: '上游站来水流量及库水位过程线'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['上游站来水流量(立方米每秒)', '水库水位(米)']
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          // data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sua','Sux','Suz']
          data: x1
        },
        yAxis: [
          //设置y轴属性
          {
            type: 'value',
            name: '上游站来水流量(立方米每秒)',
            min: min1-10 < 0 ? 0 : min1 - 10,
            max: max1+10,
            interval: interval1,
            axisLabel: {
              formatter: '{value} m3/s'
            }
          },
          //设置y轴属性
          {
            type: 'value',
            name: '水库水位(米)',
            min: min2-10 < 0 ? 0 : min2 - 10,
            max: max2+10,
            interval: interval2,
            axisLabel: {
              formatter: '{value} m'
            }
          }
        ],
        //展示数据
        series: [
          {
            name: '上游站来水流量(立方米每秒)',
            type: 'line',
            stack: 'Total',
            //data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
            data: vvv

          },
          {
            name: '水库水位(米)',
            type: 'line',
            stack: 'Total',
            //data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
            data: vvv2

          }
        ]
      };

      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);

    },
    //RSVR
    loadSTInfo:function(){
      this.axios.get("http://localhost:8083/waterInfo/RSVR/ST").then(
        response=>{
          console.log(response.data);
          this.searchParameter.options=response.data;
        }
      );
    },
    //初始Echarts
    initEcharts:function(){
      let _this=this;
      this.myChart=this.$echarts.init(document.getElementById("echartsContainer"));
      this.myChart.setOption(option);
    },

    //关闭窗口
    closeChooser:function () {
      this.$emit('closeChooser')
    },
    //销毁
    removeEchart:function () {
      this.myChart.dispose();
      this.myChart=null;
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
      document.getElementById("tableContainer").style.width='500px';
      document.getElementById("minusButton").style.display='inline-block';
      document.getElementById("magnifyButton").style.display='none';

    },
    //缩小
    minus:function () {
      let main =document.getElementById("main");

      main.style.height="570px";
      main.style.width="980px";
      main.style.left='260px';
      main.style.top='100px';
      document.getElementById("echartsContainer").style.height='390px';
      this.myChart.resize();
      document.getElementById("tableContainer").style.height='525px';
      document.getElementById("tableContainer").style.width='350px';
      document.getElementById("minusButton").style.display='none';
      document.getElementById("magnifyButton").style.display='inline-block';
    },

    getNowDate(){
      var year = new Date().getFullYear()
      var month = new Date().getMonth()+1;
      var day = new Date().getDate();
      this.nowtime=year+"/"+month+"/"+(day-3);
      this.nowtime1=year+"/"+month+"/"+(day-2);
      this.nowtime2=year+"/"+month+"/"+(day-1);
    }
  },
  created() {
    // 初次请求
    this.fetchData();
    // 设置整点定时发送请求
    setInterval(() => {
      const now = new Date();
      if (now.getMinutes() === 0 && now.getSeconds() === 0) {
        this.fetchData();
      }
      this.currentTime = now.toLocaleString();
    }, 1000);
  },
  mounted() {
    this.getNowDate();
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
    //设置定时任务，没到整点的零五分的时候，调用后端接口完成计算任务
    // let timer = setInterval(() => {
    //   let date = new Date();
    //   if (date.getMinutes === 5) {
    //     clearInterval(timer)
    //   } else {
    //     this.addTimer()
    //   }
    // });

  }
}
</script>

<style scoped>
.dataContainer{
  padding-left: 10px;
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
