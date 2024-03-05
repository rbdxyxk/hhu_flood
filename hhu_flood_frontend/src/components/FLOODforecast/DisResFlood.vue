<template>
<!--  <div style=" height:570px; width:980px" id="main">-->
            <div style="width: 80%;text-align:center" id="main">
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
                          <el-row>
                            <div class="dataContainer" id="dataContainer">
                             <form id="form1">
                              <div style="z-index: 101; left: 13%; width: 80%; position: absolute; top: 60px; height: 484px">
                                <label ID="Label1" runat="server" Style="left: 413px; position: absolute; top: 19px" Text="当前库水位" Width="119px" Font-Bold="True">当前库水位</label>
                                <Input type="text"  ID="TextInput3" v-model="DisResData.p3" runat="server" Style="left: 544px; position: absolute; top: 15px" Width="163px" ref="input3"></Input>
                                <Label ID="Label2" runat="server" Style="left: 32px; position: absolute; top: 15px" Text="大官庄闸来水流量(m<sup>3</sup>/s)" Font-Bold="True">大官庄闸来水流量(m<sup>3</sup>/s)</Label>
                                <Input type="text" ID="TextInput1" v-model="DisResData.p1" runat="server" Style="left: 219px; position: absolute; top: 15px" @change="txt2Changed()" ref="input1"></Input>
                                <Label ID="Label3" runat="server" Style="left: 52px; position: absolute; top: 110px" Text="洪水级别">洪水级别</Label>

<!--                                        <RadioButtonList ID="RadioButtonList1" runat="server" Style="left: 127px; position: absolute;-->
<!--                                                top: 103px;text-align: left;" AutoPostBack="True">-->
<!--                                          <ListItem Value="0">设计标准以下</ListItem>-->
<!--                                          <ListItem Value="1">设计标准洪水(二十年一遇)</ListItem>-->
<!--                                          <ListItem Value="2">超标准洪水</ListItem>-->
<!--                                        </RadioButtonList>-->
                                <label ID="RadioButtonList1" runat="server" Style="left: 157px; position: absolute;
                                     top: 103px;text-align: left;">设计标准以下</label>
                                <Input type="radio" ID="RadioButtonList1" runat="server" Style="left: 127px; position: absolute;
                                     top: 103px;text-align: left;"  value="0"></Input>
                                <label ID="RadioButtonList2" runat="server" Style="left: 157px; position: absolute;
                                     top: 133px;text-align: left;">设计标准洪水(二十年一遇)</label>
                                <Input type="radio" ID="RadioButtonList2" runat="server" Style="left: 127px; position: absolute;
                                     top: 133px;text-align: left;"   value="1"></Input>
                                <label ID="RadioButtonList3" runat="server" Style="left: 157px; position: absolute;
                                     top: 163px;text-align: left;">超标准洪水</label>
                                <Input type="radio" ID="RadioButtonList3" runat="server" Style="left: 127px; position: absolute;
                                     top: 163px;text-align: left;"   value="2"></Input>

                                                  <el-button ID="Button1" runat="server" Style="left: 129px; position: absolute; top: 247px" Text="洪水调度计算"  @click="but1CalDispatch" Height="34px" Width="131px" >洪水调度计算</el-button>
                                                  <Label ID="Label1_1" runat="server" Style="left: 449px; position: absolute; top: 50px"
                                                             Text="日期2014-6-26 15:20:00" Width="176px">日期2014-6-26 15:20:00</Label>
                                                  <Table ID="Table1" runat="server" Height="35px" Style="left: 466px; position: relative;
                                                          top: 118px" Width="540px" GridLines="Horizontal">
                                                  </Table>
                                                  <Label ID="Label10" runat="server" Font-Bold="True" Style="left: 65px; position: absolute;
                                                          top: 47px" Text="区间来水流量(m<sup>3</sup>/s)">区间来水流量(m<sup>3</sup>/s)</Label>
                                                  <Input type="text" ID="TextInput2" v-model="DisResData.p2" runat="server" Style="left: 218px; position: absolute; top: 50px" ref="input2"></Input>
                                                  <Label ID="Label5" runat="server" Font-Bold="True" Style="left: 743px; position: absolute;
                                                              top: 15px" Text="出库流量(m<sup>3</sup>/s)">出库流量(m<sup>3</sup>/s)</Label>
                                                  <Input type="text" ID="TextInput4" v-model="DisResData.p4" runat="server" Style="left: 862px; position: absolute; top: 15px" ref="input4"></Input>
                                                  <Label ID="Label7" runat="server" Font-Bold="True" Style="left: 107px; position: absolute;
                                                          top: 383px" Text="北闸泄洪流量(m<sup>3</sup>/s)">北闸泄洪流量(m<sup>3</sup>/s)</Label>
                                                  <Input type="text" ID="TextOutput1" v-model="DisResData.north" runat="server" Style="left: 256px; position: absolute; top: 383px" ref="output1" :value="DisResData.north"></Input>
                                                  <Label ID="Label8" runat="server" Font-Bold="True" Style="left: 507px; position: absolute;
                                                          top: 383px" Text="南闸泄洪流量(m<sup>3</sup>/s)">南闸泄洪流量(m<sup>3</sup>/s)</Label>
                                                  <Input type="text" ID="TextOutput2" v-model="DisResData.south" runat="server" Style="left: 659px; position: absolute; top: 383px" ref="output2" :value="DisResData.south"></Input>
                                                  <Label ID="Label9" runat="server" Font-Bold="True" Style="left: 107px; position: absolute; background-color:red;
                                                          top: 346px" Text="建议1小时后各水闸泄洪流量" BackColor="#FF8080"  Font-Size="Large">建议{{h}}小时后各水闸泄洪流量</Label>
                                                  <Label ID="Label11" runat="server" Font-Bold="True" Style="left: 53px; position: absolute;
                                                          top: 201px" Text="计算时长">计算时长</Label>
                                                  <Input type="text" ID="TextInput5" v-bind="DisResData.p5" runat="server"  @change="txtChange()" ref="input5" Style="left: 131px; position: absolute; top: 199px"></Input>
                                                  <Label ID="Label12" runat="server" Font-Bold="True" Style="left: 315px; position: absolute;
                                                          top: 203px" Text="小时">小时</Label>
                                                  <Label ID="Label6" runat="server" Font-Bold="True" Style="left: 112px; position: absolute;
                                                          top: 307px" Text="小时后库水位(m)" Width="131px">{{h}}小时后库水位(m)</Label>
                                                  <Input type="text" ID="TextOutput3" v-model="DisResData.water" runat="server" Style="left: 251px; position: absolute;top: 306px" Width="163px" ref="output3" :value="DisResData.water"></Input>
                                                  <Label ID="Label4" runat="server" Font-Bold="True" Font-Size="Large" ForeColor="Black" Style="left: 605px; position: absolute; top: 104px; z-index: 101; width: 258px; height: 22px;" Text="洪水调度南北闸泄量分配方案">洪水调度南北闸泄量分配方案</Label>
                                <table id="Table1" rules="rows" border="1" style="left: 560px; position: absolute; top: 130px; z-index: 188; width: 720px; height: 220px;">
                                  <tr align="center" style="background-color:LightSkyBlue;">
                                    <td>洪水标准</td><td>闸上水位(m)</td><td>北闸泄量(m<sup>3</sup>/s)</td><td>南闸泄量(m<sup>3</sup>/s)</td>
                                  </tr><tr align="center" style="background-color:LightPink;">
                                  <td></td><td>24.00</td><td>2500</td><td>2500</td>
                                </tr><tr align="center" style="background-color:LightBlue;">
                                  <td>50年一遇</td><td>26.08</td><td>2500</td><td>3500</td>
                                </tr><tr align="center" style="background-color:LightPink;">
                                  <td>100年一遇</td><td>26.81</td><td>3000</td><td>4000</td>
                                </tr><tr align="center" style="background-color:LightBlue;">
                                  <td>2000年一遇</td><td>27.95</td><td>5000</td><td>5131</td>
                                </tr>
                                </table>
                              </div>

                            </form>
                            </div>
                          </el-row>
                        </div>
                      </el-col>
                      <el-col :span="8">
                        <div class="tableContainer" style="width: 360px" id="tableContainer">

                        </div>
                      </el-col>
                    </el-row>

                  </div>
                </el-col>
              </el-row>

            </div>

</template>

<script>

import  "../../assets/js/jquery.table2excel.min.js"
import RiverInfoTable from "../waterInfoTable/RiverInfoTable";
export default {
  name: "waterInfo",
  data:function(){
    return{
       h:'',
       DisResData:{
         p1:'',
         p2:'',
         p3:'',
         p4:'',
         p5:'',

         water:'',
         north:'',
         south:'',
       }
    };
  },
  components:{
    RiverInfoTable
  },
  methods:{
    but1CalDispatch:function (){
      // alert("111");
      this.axios.post("http://localhost:8083/but1CalDispatch",{p1:parseFloat(this.$refs.input1.value),p2:parseFloat(this.$refs.input2.value),p3:parseFloat(this.$refs.input3.value),p4:parseFloat(this.$refs.input4.value),p5:parseFloat(this.$refs.input5.value)}).then(
        response=>{
          for(let i in response.data){
            if(response.data[i]<0){
              alert("您输入库水位值不合理，请重新输入！");
              break;
            }
            this.DisResData[i]=response.data[i];
          }
        }).catch(function (error) {
        // 处理错误情况
        alert(error);
      });
      // this.$refs.output1.value=this.DisResData.north;
      // this.$refs.output2.value=this.DisResData.south;
      // this.$refs.output3.value=this.DisResData.water;
    },
    txtChange:function (){
      this.h=this.$refs.input5.value;
    },
    txt2Changed:function(){
      // alert(this.$refs.input1.value);
      if(this.$refs.input1.value<'3000'){
        // this.abc=true;
        $("input[value='0']").prop('checked',true);
        $("input[value='1']").removeAttr("checked");
        $("input[value='2']").removeAttr("checked");
      }
      if(this.$refs.input1.value>='3000'&&this.$refs.input1.value<'7500'){
        // this.abc=true;
        $("input[value='0']").removeAttr("checked");
        $("input[value='1']").prop('checked',true);
        $("input[value='2']").removeAttr("checked");
      }
      if(this.$refs.input1.value>='7500'){
        $("input[value='0']").removeAttr("checked");
        $("input[value='1']").removeAttr("checked");
        $("input[value='2']").prop('checked',true);
      }
    },
    //DisResFlood
    check:function(objNM){
      let objTxt = document.getElementById(objNM);
      let str;
      str=objTxt.value;
      let bt=str.match("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
      if (bt==null)
      {

        objTxt.style.backgroundColor = '#f6c9c4' ;
        if (objTxt.name !== "TextInput1")
        { alert(str + ",不是数字！请填写完整内容"); }
        return false;
      }
      else {//alert(str+",填写对了"+bt);
        objTxt.style.backgroundColor = '' ;
        return false; }
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
    }
  },
  mounted() {
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

</style>
