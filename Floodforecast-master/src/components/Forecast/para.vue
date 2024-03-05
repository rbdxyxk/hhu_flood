<template xmlns="http://www.w3.org/1999/html">
  <div style=" height:470px; width:895px" id="main">
    <el-row>
      <div class="el-header " id="windowHead" style="text-align: right">
      </div>
    </el-row>
    <el-row>
      <div style="text-align:center">
        <form id="form1" runat="server" oninit="FrmOninit">
          <div>
            <div style="z-index: 106; left: 43px; width: 1200px; position: absolute; top: 84px;
            height: 421px; border-right: blue 1px solid; border-top: blue 1px solid; border-left: blue 1px solid; border-bottom: blue 1px solid;">
              <select ID="DropDownList1" ref="dropdown1" runat="server" Style="position: relative; z-index: 100;" Width="259px" @change="checkValue()">
                <option v-for="item in ParaData.part" :value="item" :label="item"/>
              </select>
              <Label ID="Label1" runat="server" Text="参数方案">参数方案</Label>
              <Label ID="LabRegCalP12" runat="server" Style="left: 755px; position: absolute;
                top: 240px; z-index: 101;" Text="汇流系数X" Width="86px">汇流系数X</Label>
              <Label ID="LabRegCalP3" runat="server" Style="left: 426px; position: absolute;
                top: 94px; z-index: 102;" Text="计算时段步长(km)" Width="132px">计算时段步长(km)</Label>
              <Label ID="LabRegCalP2" runat="server" Style="left: 448px; position: absolute;
                top: 57px; z-index: 103;" Text="计算面积(km<sup>2</sup>)" Width="110px">计算面积(km<sup>2</sup>)</Label>
              <Label ID="LabRegCalP1" runat="server" Style="left: 409px; position: absolute;
                top: 131px; z-index: 104;" Text="蓄水容量分布指数B" Width="154px">蓄水容量分布指数B</Label>
              <Label ID="LabRegCalP4" runat="server" Style="left: 417px; position: absolute;
                top: 206px; z-index: 105;" Text="不透水面积比例IM" Width="139px">不透水面积比例IM</Label>
              <Label ID="LabRegCalP6" runat="server" Style="left: 411px; position: absolute;
                top: 279px; z-index: 106;" Text="地下水出流系数KG" Width="145px">地下水出流系数KG</Label>
              <Label ID="LabRegCalP5" runat="server" Style="left: 412px; position: absolute;
                top: 242px; z-index: 107;" Text="壤中流出流系数KI" Width="142px">壤中流出流系数KI</Label>
              <Label ID="LabRegCalP7" runat="server" Style="left: 384px; position: absolute;
                top: 167px; z-index: 108;" Text="自由水分布曲线指数EX" Width="168px">自由水分布曲线指数EX</Label>
              <Label ID="LabRegCalP8" runat="server" Style="left: 750px; position: absolute; top: 66px; z-index: 109;"
                         Text="壤中流消退系数CI" Width="134px">壤中流消退系数CI</Label>
              <Label ID="LabRegCalP9" runat="server" Style="left: 750px; position: absolute;
                top: 109px; z-index: 110;" Text="地下水消退系数CG" Width="138px">地下水消退系数CG</Label>
              <Label ID="LabRegCalP13" runat="server" Style="left: 755px; position: absolute;
                top: 279px; z-index: 110;" Text="槽蓄系数K" Width="138px">槽蓄系数K</Label>
              <input ID="TboxRegCalP13" :disabled="flag" v-model="ParaData.p13" runat="server" Style="left: 870px; position: absolute;
                top: 279px; z-index: 112;" Width="50px" ></input>
<!--              <Label ID="Label2" runat="server" Style="left: 27px; position: absolute; top: 45px; z-index: 111;"-->
<!--                         Text="是否设为当前运行方案">是否设为当前运行方案</Label>-->
              <input ID="input1" :disabled="flag" v-model="ParaData.p14" runat="server" Style="left: 203px; position: absolute;
                top: 93px; z-index: 112;" Width="50px" ></input>
              <Label ID="LabRegCalP10" runat="server" Style="left: 750px; position: absolute; top: 152px; z-index: 113;"
                         Text="坡面汇流系数CS" Width="122px" >坡面汇流系数CS</Label>
              <input ID="input2" :disabled="flag" v-model="ParaData.p15" runat="server" Style="left: 203px;
                    position: absolute; top: 136px; z-index: 114;" Width="50px"></input>
              <Label ID="LabRegCalP11" runat="server"   Style="left: 750px; position: absolute; top: 195px; z-index: 115;" Text="汇流滞后时段数L" Width="127px">汇流滞后时段数L</Label>
              <input ID="input3" :disabled="flag" v-model="ParaData.p16" runat="server" Style="left: 203px; position: absolute; top: 179px; z-index: 116;" Width="50px" ></input>
              <Label ID="Label5" runat="server" Height="17px" Style="left: 12px; position: absolute;
                                top: 181px; z-index: 117;" Text="下层WL(土壤张力水容量)">下层WL(土壤张力水容量)</Label>
              <input ID="input4" :disabled="flag" v-model="ParaData.p17" runat="server" Style="left: 203px; position: absolute;
                top: 222px; z-index: 118;" Width="50px"></input>
              <Label ID="Label9" runat="server" Height="17px" Style="left: 79px; position: absolute;
                top: 272px; z-index: 119;" Text="深层散发系数C">深层散发系数C</Label>
              <input ID="TboxRegCalP1" :disabled="flag" v-model="ParaData.p1" runat="server" BorderStyle="Outset" Style="z-index: 120;
                left: 563px; position: absolute; top: 55px" Width="50px"></input>
              <input ID="TboxRegCalP2" :disabled="flag" v-model="ParaData.p2" runat="server" BorderStyle="Outset" Style="z-index: 121;
                left: 563px; position: absolute; top: 92px" Width="50px"></input>
              <input ID="TboxRegCalP3" :disabled="flag" v-model="ParaData.p3" runat="server" BorderStyle="Outset" Style="z-index: 122;
                left: 563px; position: absolute; top: 129px" Width="50px" ></input>
              <input ID="TboxRegCalP4" :disabled="flag" v-model="ParaData.p4" runat="server" BorderStyle="Outset" Style="z-index: 123;
                left: 563px; position: absolute; top: 166px" Width="50px" ></input>
              <input ID="TboxRegCalP5" :disabled="flag" v-model="ParaData.p5" runat="server" BorderStyle="Outset" Style="z-index: 124;
                left: 563px; position: absolute; top: 204px" Width="50px" ></input>
              <input ID="TboxRegCalP6" :disabled="flag" v-model="ParaData.p6" runat="server" BorderStyle="Outset" Style="z-index: 125;
                left: 563px; position: absolute; top: 239px" Width="50px" ></input>
              <input ID="TboxRegCalP7" :disabled="flag" v-model="ParaData.p7" runat="server" BorderStyle="Outset" Style="z-index: 126;
                left: 563px; position: absolute; top: 275px" Width="50px" ></input>
              <input ID="TboxRegCalP8" :disabled="flag" v-model="ParaData.p8" runat="server" BorderStyle="Outset" Style="z-index: 127;
                left: 870px; position: absolute; top: 61px" Width="50px" ></input>
              <input ID="TboxRegCalP9" :disabled="flag" v-model="ParaData.p9" runat="server" BorderStyle="Outset" Style="z-index: 128;
                left: 870px; position: absolute; top: 105px" Width="50px" ></input>
              <input ID="TboxRegCalP12" :disabled="flag" v-model="ParaData.p12" runat="server" BorderStyle="Outset" Style="z-index: 129;
                left: 870px; position: absolute; top: 240px" Width="50px" ></input>
              <Panel ID="Panel1" runat="server" BorderColor="#8080FF" BorderStyle="Dotted"
                         Height="40px" Style="z-index: 130; left: 193px; position: absolute; top: 336px"
                         Width="610px">
              </Panel>
              <RadioButton ID="RadioButton1" runat="server" Height="20px" Style="z-index: 131;
                left: 260px; position: absolute; top: 47px" Text="否" Width="39px" AutoPostBack="True" />
              <RadioButton ID="RadioButton2" runat="server" Style="z-index: 132; left: 203px;
                position: absolute; top: 46px" Text="是" Width="43px" AutoPostBack="True" />

              <Label ID="Label3" runat="server" Height="17px" Style="z-index: 117; left: 51px;
                position: absolute; top: 92px" Text="表层自由水容量SM" Width="149px">表层自由水容量SM</Label>
              <Label ID="Label4" runat="server" Height="17px" Style="z-index: 117; left: 11px;
                position: absolute; top: 226px" Text="深层WD(土壤张力水容量)">深层WD(土壤张力水容量)</Label>
              <Label ID="Label6" runat="server" Height="17px" Style="z-index: 117; left: 12px;
                position: absolute; top: 136px" Text="上层WM(土壤张力水容量)">上层WM(土壤张力水容量)</Label>
              <input ID="input5" :disabled="flag" v-model="ParaData.p18" runat="server" Style="z-index: 118; left: 204px; position: absolute;
                top: 269px" Width="80px"></input>
              <input ID="TboxRegCalP10" :disabled="flag" v-model="ParaData.p10" runat="server" BorderStyle="Outset" Style="z-index: 128;
                left: 870px; position: absolute; top: 149px" Width="50px"></input>
              <input ID="TboxRegCalP11" :disabled="flag" v-model="ParaData.p11" runat="server" BorderStyle="Outset" Style="z-index: 128;
                left: 870px; position: absolute; top: 193px" Width="50px"></input>

            </div>

          </div>
          <div style="z-index: 108; left: 42px; width: 997px; position: absolute; top: 21px;
            height: 54px">
            <Label ID="Label8" runat="server" Style="left: 7px; position: absolute; top: 7px; z-index: 102;"
                       Text="预报计算参数方案列表">预报计算参数方案列表</Label>
            <select ID="DropDownList2" runat="server" ref="dropdown2" Style="left: 1px; position: absolute;
            top: 30px; z-index: 100;" Width="420px" AutoPostBack="True"   @change="checkValue()">
              <option v-for="item in ParaData.paraForecasts" :value="item" :label="item"/>
            </select>
            <button ID="Button4" runat="server" :disabled="flag" Style="left: 663px; position: absolute; top: 22px; z-index: 104;"Width="148px" @click="save()">保存为当前参数方案</button>
            <button ID="Button3" runat="server"  :disabled="flag0" Style="left: 440px; position: absolute; top: 22px; z-index: 105;" Text="修改参数" @click="edit()">修改参数</button>
            <button ID="Button5" runat="server" :disabled="flag" Style="left: 819px; position: absolute; top: 22px; z-index: 104;" Text="另存参数方案"  @click="saveAs()">另存参数方案</button>
            <button ID="Button1" runat="server" :disabled="flag" Style="left: 526px; position: absolute; top: 22px; z-index: 104;" Text="设为当前运行参数" @click="setOnline()" Width="129px">设为当前运行参数</button>
            <button ID="Button2" runat="server" :disabled="flag" Style="left: 949px; position: absolute; top: 22px; z-index: 105;" Text="删除" @click="del()">删除</button>
          </div>

        </form>
      </div>
    </el-row>

    <!--  <chooser></chooser>-->
  </div>

</template>

<script>
import axios from "axios";

export default {
  name: "manualforecast",
  data:function(){
    return{
      flag:true,
      flag0:false,
      ParaData:{
        paraForecasts:[],
        part:[],
        p1:'',
        p2:'',
        p3:'',
        p4:'',
        p5:'',
        p6:'',
        p7:'',
        p8:'',
        p9:'',
        p10:'',
        p11:'',
        p12:'',
        p13:'',
        p14:'',
        p15:'',
        p16:'',
        p17:'',
        p18:'',


        riverInfo:'',
        rsvrInfo:'',
        date:'',
        hour:'',
        checked:'',
        checked0:'',
      }
    }
  },
  methods:{
    save:function (){
      this.axios.post("http://localhost:8083/save",this.ParaData).then(
        response=>{
          alert(response.data);
        }
      ).catch(function (error) {
        // 处理错误情况
        alert("保存未成功！");
      });
    },
    edit:function (){
      this.flag=false;
      this.flag0=true;
    },
    saveAs:function (){
      this.axios.post("http://localhost:8083/saveAs",this.ParaData).then(
        response=>{
          alert(response.data);
        }
      ).catch(function (error) {
        // 处理错误情况
        alert("另存未成功！");
      });
    },
    setOnline:function (){
      this.axios.post("http://localhost:8083/setOnline",this.ParaData).then(
        response=>{
          alert(response.data);
        }
      ).catch(function (error) {
        // 处理错误情况
        alert("设置未成功！");
      });
    },
    del:function (){
      this.axios.post("http://localhost:8083/del",this.ParaData).then(
        response=>{
          alert(response.data);
        }
      ).catch(function (error) {
        // 处理错误情况
        alert("删除未成功！");
      });
    },
    checkValue:function () {
      // alert(this.$refs.dropdown1.options.selectedIndex);
      this.ParaData.checked0=this.ParaData.part[this.$refs.dropdown1.options.selectedIndex];
      this.ParaData.checked=this.ParaData.paraForecasts[this.$refs.dropdown2.options.selectedIndex];

      this.axios.post("http://localhost:8083/checkValue",this.ParaData).then(
        response=>{
          console.log(response.data);
          for(let i in response.data){
            this.ParaData[i]=response.data[i];
          }

        }).catch(function (error) {
        // 处理错误情况
        alert("没有对应分区，重新选择！");
      });
    },

  },
  mounted() {
    //设置检查
    let _this = this;
    //移动
    let main=document.getElementById("main")
    //let rainWindowHead = document.getElementById("rainWindowHead")
    // main.onmousedown= function(event){
    //   _this.fndown(main,event,main);
    // }

    this.axios.get("http://localhost:8083/ParaInit").then(
      response=>{
        console.log(response.data);
        for(let i in response.data){
          this.ParaData[i]=response.data[i];
        }
      }
    );

  }
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
</style>
