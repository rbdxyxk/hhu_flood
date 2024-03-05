<template>
  <div style=" height:470px; width:895px" id="main">
    <el-row>
      <div class="el-header " id="windowHead" style="text-align: right">
<!--        <el-button type="infer" size="mini"  icon="el-icon-minus" id="minusButton" @click="minus" circle></el-button>-->
<!--        <el-button type="infer" size="mini"  icon="el-icon-rank" id="magnifyButton" @click="magnify" circle></el-button>-->
        <el-button type="danger" size="mini"  @click="closeWindow"  icon="el-icon-close" circle></el-button>
      </div>
    </el-row>
    <el-row>
      <div style="text-align:center">
        <form id="form1"  oninit="FrmOninit">

          <div style="z-index: 106; left: 3%; width: 94%; position: absolute; top: 80px;
            height: 324px; border-right: blue 1px solid; border-top: blue 1px solid; border-left: blue 1px solid; border-bottom: blue 1px solid;">




            <label id="Label1" runat="server" Font-Bold="True" Style="left: 1px; position: absolute;
                top: -20px; z-index: 123;"  Width="20%">输入水文气象现状</label>
            <select id="DropDownList2" ref="dropdown1" Style="left: 1px; position: absolute;
            top: -55px; z-index: 100;height: 35px;width: 42%"  @change="checkValue()">
              <option v-for="item in FrmData.paraForecasts" :value="item" :label="item"/>
            </select>


            <label id="Label3" runat="server" Style="z-index: 122; left: 0%; position: absolute;
                top: 29px"  Width="20%">前1小时测站降雨(mm)</label>

            <label id="LabRegCalP10" runat="server" Style="left: 5%; position: absolute;
                    top: 256px; z-index: 100;"  Width="6%">大兴镇</label>



            <label id="LabRegCalP6" runat="server" Style="left: 7%; position: absolute;
                    top: 120px; z-index: 102;" Width="4%">临沭</label>

            <label id="LabRegCalP8" runat="server" Style="left: 7%; position: absolute; top: 165px; z-index: 104;"
                    Width="4%">张疃</label>

            <label id="LabRegCalP9" runat="server" Style="left: 3%; position: absolute;
                    top: 210px; z-index: 105;"  Width="9%">石梁河水库</label>

            <label id="Label2" runat="server" Style="left: 7%; position: absolute; top: 75px; z-index: 106;"
                    Width="4%">石门</label>




            <input type='text' v-model="FrmData.p1"  id="TboxRegCalP1" @change="check('TboxRegCalP1')" runat="server" BorderStyle="Outset" Style="z-index: 108;
                    left: 13%; position: absolute; top: 72px" Width="9%"    ></input type='text'>

            <input type='text' v-model="FrmData.p2" id="TboxRegCalP2" @change="check('TboxRegCalP2')"  runat="server" BorderStyle="Outset" Style="z-index: 109;
                    left: 13%; position: absolute; top: 117px" Width="9%"></input type='text'>

            <input type='text' v-model="FrmData.p3" id="TboxRegCalP3" @change="check('TboxRegCalP3')" runat="server" BorderStyle="Outset" Style="z-index: 110;
                    left: 13%; position: absolute; top: 162px" Width="9%" ></input type='text'>

            <input type='text' v-model="FrmData.p4" id="TboxRegCalP4" @change="check('TboxRegCalP4')"  runat="server" BorderStyle="Outset" Style="z-index: 111;
                    left: 13%; position: absolute; top: 207px" Width="9%" ></input type='text'>

            <input type='text' v-model="FrmData.p5" id="TboxRegCalP5" @change="check('TboxRegCalP5')"  runat="server" BorderStyle="Outset" Style="z-index: 112;
                    left: 13%; position: absolute; top: 253px" Width="9%" ></input type='text'>


            <div id="Panel1" runat="server" BorderColor="#8080FF" BorderStyle="Dotted"
                 Height="275px" Style="z-index: 118; left: 25%; position: absolute; top: 19px"
                 Width="13%">
            </div>



            <label id="Label6" runat="server" Style="left: 45%; position: absolute; top: 29px; z-index: 119;"
                   Width="14%">大官庄泄流(m<sup>3</sup>/s)</label>

            <label id="LabCalP1" runat="server" Style="left: 44%; position: absolute;
                top: 67px; z-index: 101;"  Width="14%">水库泄流(m<sup>3</sup>/s)</label>

            <label id="Label10" runat="server" Style="z-index: 101; left: 45%; position: absolute;
                top: 105px"  Width="14%">水库水位(m)</label>

            <label id="Label9" runat="server" Height="17px" Style="left: 45%; position: absolute;
                 top: 140px; z-index: 107;" Width="14%">土壤含水量(mm)</label>

            <label id="LabRegCalP5" runat="server" Style="left: 45%; position: absolute;
                top: 175px; z-index: 103;" Width="14%">流域产水基流(m<sup>3</sup>/s)</label>


            <label id="Label4" runat="server" Style="z-index: 124; left: 45%; position: absolute;
                top: 213px"  Width="14%">计算起始时间</label>

<!--            <el-date-picker-->
<!--              -->
<!--              Style="z-index: 124; left: 45%; position: absolute;-->
<!--            top: 213px"-->
<!--              id="Label4"-->
<!--              type="datetime"-->
<!--              format="yyyy-MM-dd HH"-->
<!--              placeholder="选择日期时间"-->
<!--              default-time="12:00:00">-->
<!--            </el-date-picker>-->

            <label id="Label7" runat="server" Style="left: 45%; position: absolute; top: 251px; z-index: 120;"
                   Width="14%">计算时长(小时)</label>

            <input type="submit" id="Button1" Style="left: 77%; position: absolute; top: 283px; z-index: 121;"
                   value="开始计算" Width="14%"  @click="btn1click" />



            <input type='text' v-model="FrmData.riverInfo" id="TboxRegCalP6" @change="check('TboxRegCalP6')"  runat="server" BorderStyle="Outset" Style="z-index: 113;
                left: 60%; position: absolute; top: 29px" Width="9%" ></input type='text'>

            <input type='text' id="TboxRegCalP7" v-model="FrmData.p7" @change="check('TboxRegCalP7')"  runat="server" BorderStyle="Outset" Style="z-index: 114;
                left: 60%; position: absolute; top: 66px"   Width="9%" ></input type='text'>

            <input type='text' id="TboxRegCalP8" v-model="FrmData.p8" @change="check('TboxRegCalP8')"  runat="server" BorderStyle="Outset" Style="z-index: 115;
                left: 60%; position: absolute; top: 140px" Width="9%" ></input type='text'>

            <input type='text' id="TboxRegCalP9" v-model="FrmData.p9" @change="check('TboxRegCalP9')"  runat="server" BorderStyle="Outset" Style="z-index: 116;
                left: 60%; position: absolute; top: 177px" Width="9%" ></input type='text'>

            <input type='text' id="TboxRegCalP10" v-model="FrmData.p10" @change="check('TboxRegCalP10')"  runat="server" BorderStyle="Outset" Style="z-index: 117;
                left: 60%; position: absolute; top: 251px" Width="9%" ></input type='text'>

            <input type='text' id="TboxRegCalP11" @change="check('TboxRegCalP11')"  v-model="FrmData.rsvrInfo" runat="server" BorderStyle="Outset" Style="z-index: 114;
                left: 60%; position: absolute; top: 103px" Width="9%"></input type='text'>


            <input type='text' id="TextBox1" runat="server" BorderStyle="Outset" Style="z-index: 116;
                left: 60%; position: absolute; top: 214px" Width="9%" v-model="FrmData.date"></input type='text'>

            <input type='text' id="TextBox2" runat="server" BorderStyle="Outset" Style="z-index: 116;
                left: 82%; position: absolute; top: 214px;width: 4%" Width="2%" v-model="FrmData.hour" ></input type='text'>

            <label id="Label5" runat="server" Style="z-index: 124; left: 86%; position: absolute;
                top: 216px" Width="2%">时</label>


          </div>


          <div style="z-index: 108; left: 10%; width: 942px; position: absolute; top: 45px;
            height: 54px">

            <label id="Label8" runat="server" Style="left: -58px; position: absolute; top: -40px; z-index: 102;"
                   >选择预报计算参数方案</label>



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
              FrmData:{
                  paraForecasts:[],
                  p1:'',
                  p2:'',
                  p3:'',
                  p4:'',
                  p5:'',
                  p7:'',
                  p8:'',
                  p9:'',
                  p10:'',


                  riverInfo:'',
                  rsvrInfo:'',
                  date:'',
                  hour:'',
                  checked:'',
              }
          }
        },
      methods:{

          checkValue:function () {
            // alert(this.$refs.dropdown1.options.selectedIndex);
            this.FrmData.checked=this.FrmData.paraForecasts[this.$refs.dropdown1.options.selectedIndex];
          },
         //关闭窗口
          closeWindow:function () {
            this.$emit('closeWindow')
          },
          //检查
          check:function(objNM)
    { let objTxt = document.getElementById(objNM);
      let str=objTxt.value;

      let bt=str.match("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
      if (bt==null)
      {

        objTxt.style.backgroundColor = '#f6c9c4' ;
        alert(str+",不是数字！请填写完整内容" );
        return false;
      }
      else {//alert(str+",填写对了"+bt);
        objTxt.style.backgroundColor = '' ;
        return false; }

    },


    getCookie:function(c_name)
    {　if (document.cookie.length>0)
    {　　//先查询cookie是否为空，为空就return ""
      c_start=document.cookie.indexOf(c_name + "=")　　//通过String对象的indexOf()来检查这个cookie是否存在，不存在就为 -1　
      if (c_start!=-1)
      {
        c_start=c_start + c_name.length+1　　//最后这个+1其实就是表示"="号啦，这样就获取到了cookie值的开始位置　
        c_end=document.cookie.indexOf(";",c_start)　　//其实我刚看见indexOf()第二个参数的时候猛然有点晕，后来想起来表示指定的开始索引的位置...这句是为了得到值的结束位置。因为需要考虑是否是最后一项，所以通过";"号是否存在来判断　
        if (c_end==-1) c_end=document.cookie.length
        // alert("c_start="+c_start+' c_name.length='+c_name.length+'c_end='+c_end+'tt'+document.cookie);
        return unescape(document.cookie.substring(c_start,c_end))　　//通过substring()得到了值。想了解unescape()得先知道escape()是做什么的，都是很重要的基础，想了解的可以搜索下，在文章结尾处也会进行讲解cookie编码细节　
      }
    }
      return ""
    },
    //对二维数组型的cookie处理
          getCookieVaL:function (str,c_name)
    {　if (str.length>0) {　　//先查询是否为空，为空就return ""
      c_start=str.indexOf(c_name + "=")　　//通过String对象的indexOf()来检查这个cookie是否存在，不存在就为 -1　
      if (c_start!=-1)
      {
        c_start=c_start + c_name.length+1　　//最后这个+1其实就是表示"="号啦，这样就获取到了cookie值的开始位置　
        c_end=str.indexOf("&",c_start)　　//其实我刚看见indexOf()第二个参数的时候猛然有点晕，后来想起来表示指定的开始索引的位置...这句是为了得到值的结束位置。因为需要考虑是否是最后一项，所以通过";"号是否存在来判断　
        if (c_end==-1) c_end=str.length
        // alert("c_start="+c_start+' c_name.length='+c_name.length+'c_end='+c_end+'tt'+c_name);
        return unescape(str.substring(c_start,c_end))　　//通过substring()得到了值。想了解unescape()得先知道escape()是做什么的，都是很重要的基础，想了解的可以搜索下，在文章结尾处也会进行讲解cookie编码细节　
      }
    }
      return ""
    },
     indexChange:function(odjNM)
    {   objDrop = document.getElementById(odjNM);
      let strT=objDrop.value;//选了列表值
      // alert(",填写对了"+odjNM+'dd'+objDrop.value);
      // i=strT.length-1;j=strT.length;

      s=strT.substring(strT.length-1,strT.length);
      i=parseInt(s)-1;
      //alert(objDrop.value+",填写对了"+strT+'ww'+i+'pp'+s);

      if (document.getElementById('Button3').value == '修改参数')
      {     document.getElementById('TboxRegCalP1').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL0');
        document.getElementById('TboxRegCalP2').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL1');
        document.getElementById('TboxRegCalP3').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL2');
        document.getElementById('TboxRegCalP4').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL3');
        document.getElementById('TboxRegCalP5').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL4');
        document.getElementById('TboxRegCalP6').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL5');
        document.getElementById('TboxRegCalP7').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL6');
        document.getElementById('TboxRegCalP8').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL7');
        document.getElementById('TboxRegCalP9').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL8');
        document.getElementById('TboxRegCalP10').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL9');

        document.getElementById('TextBox1').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL10');
        document.getElementById('TextBox2').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL11');
        document.getElementById('TextBox3').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL12');
        document.getElementById('TextBox4').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL13');
      }
      else
      {
        // int j = 0;
//            ///处理修改过的参数
        cookieSelID =getCookie('curSelID');//will disappear number of cookie
        j = parseInt(cookieSelID);//will disappear number
        name='cookieParaGp'+j;
        strvaL='paraCaL0'+'='+document.getElementById('TboxRegCalP1').value;
        strvaL=strvaL+'&'+'paraCaL1'+'='+document.getElementById('TboxRegCalP2').value;
        strvaL=strvaL+'&'+'paraCaL2'+'='+document.getElementById('TboxRegCalP3').value;
        strvaL=strvaL+'&'+'paraCaL3'+'='+document.getElementById('TboxRegCalP4').value;
        strvaL=strvaL+'&'+'paraCaL4'+'='+document.getElementById('TboxRegCalP5').value;
        strvaL=strvaL+'&'+'paraCaL5'+'='+document.getElementById('TboxRegCalP6').value;
        strvaL=strvaL+'&'+'paraCaL6'+'='+document.getElementById('TboxRegCalP7').value;
        strvaL=strvaL+'&'+'paraCaL7'+'='+document.getElementById('TboxRegCalP8').value;
        strvaL=strvaL+'&'+'paraCaL8'+'='+document.getElementById('TboxRegCalP9').value;
        strvaL=strvaL+'&'+'paraCaL9'+'='+document.getElementById('TboxRegCalP10').value;
        strvaL=strvaL+'&'+'paraCaL10'+'='+document.getElementById('TextBox1').value;
        strvaL=strvaL+'&'+'paraCaL11'+'='+document.getElementById('TextBox2').value;
        strvaL=strvaL+'&'+'paraCaL12'+'='+document.getElementById('TextBox3').value;
        strvaL=strvaL+'&'+'paraCaL13'+'='+document.getElementById('TextBox4').value;


        ///处理即要显示的参数
        document.getElementById('TboxRegCalP1').value = this.getCookieVaL(this.getCookie('cookieParaGp'+i),'paraCaL0');
        document.getElementById('TboxRegCalP2').value = this.getCookieVaL(this.getCookie('cookieParaGp'+i),'paraCaL1');
        document.getElementById('TboxRegCalP3').value = this.getCookieVaL(this.getCookie('cookieParaGp'+i),'paraCaL2');
        document.getElementById('TboxRegCalP4').value = this.getCookieVaL(this.getCookie('cookieParaGp'+i),'paraCaL3');
        document.getElementById('TboxRegCalP5').value = this.getCookieVaL(this.getCookie('cookieParaGp'+i),'paraCaL4');
        document.getElementById('TboxRegCalP6').value = this.getCookieVaL(this.getCookie('cookieParaGp'+i),'paraCaL5');
        document.getElementById('TboxRegCalP7').value = this.getCookieVaL(this.getCookie('cookieParaGp'+i),'paraCaL6');
        document.getElementById('TboxRegCalP8').value = this.getCookieVaL(this.getCookie('cookieParaGp'+i),'paraCaL7');
        document.getElementById('TboxRegCalP9').value = this.getCookieVaL(this.getCookie('cookieParaGp'+i),'paraCaL8');
        document.getElementById('TboxRegCalP10').value = this.getCookieVaL(this.getCookie('cookieParaGp'+i),'paraCaL9');
        document.getElementById('TextBox1').value = this.getCookieVaL(this.getCookie('cookieParaGp'+i),'paraCaL10');
        document.getElementById('TextBox2').value = this.getCookieVaL(this.getCookie('cookieParaGp'+i),'paraCaL11');
        document.getElementById('TextBox3').value = this.getCookieVaL(this.getCookie('cookieParaGp'+i),'paraCaL12');
        document.getElementById('TextBox4').value = this.getCookieVaL(this.getCookie('cookieParaGp'+i),'paraCaL13');


        ///写入小甜饼，小甜饼好吃，不易吃着,一定要在此处，不能提前哦
        document.cookie =name+'='+strvaL; //write disappear value,don't exchange line


        //   alert(",填写对了SelID"+name+'='+strvaL);
      }
      document.cookie ='curSelID='+i;
      return false;
    },
     btn3Click:function()
    {
      //alert(document.getElementById('Button3').Text+",填写对了SelID"+document.getElementById('Button3').value);
      if (document.getElementById('Button3').value == "修改参数")
      {
        document.getElementById('TboxRegCalP1').disabled = false; document.getElementById('TboxRegCalP2').disabled = false;
        document.getElementById('TboxRegCalP3').disabled = false; document.getElementById('TboxRegCalP4').disabled = false;
        document.getElementById('TboxRegCalP5').disabled = false; document.getElementById('TboxRegCalP6').disabled = false;
        document.getElementById('TboxRegCalP7').disabled = false; document.getElementById('TboxRegCalP8').disabled = false;
        document.getElementById('TboxRegCalP9').disabled = false; document.getElementById('TboxRegCalP10').disabled = false;
        document.getElementById('TextBox1').disabled = false;document.getElementById('TextBox2').disabled = false;
        document.getElementById('TextBox3').disabled = false;document.getElementById('TextBox4').disabled = false;

        this.setcookieGp();//cookieGP赋原值
        document.getElementById('Button3').value ="放弃修改";//document.getElementById('Button2').disabled=false;
        document.getElementById('Button4').disabled=false;document.getElementById('Button5').disabled=false;

      }
      else
      {
        document.getElementById('TboxRegCalP1').disabled = true; document.getElementById('TboxRegCalP2').disabled = true;
        document.getElementById('TboxRegCalP3').disabled = true; document.getElementById('TboxRegCalP4').disabled = true;
        document.getElementById('TboxRegCalP5').disabled = true; document.getElementById('TboxRegCalP6').disabled = true;
        document.getElementById('TboxRegCalP7').disabled = true; document.getElementById('TboxRegCalP8').disabled = true;
        document.getElementById('TboxRegCalP9').disabled = true; document.getElementById('TboxRegCalP10').disabled = true;
        document.getElementById('TextBox1').disabled = true;document.getElementById('TextBox2').disabled = true;
        document.getElementById('TextBox3').disabled = true;document.getElementById('TextBox4').disabled = true;
        document.getElementById('Button3').value ="修改参数";//document.getElementById('Button2').disabled=true;

        i =this.getCookie('curSelID');
        document.getElementById('TboxRegCalP1').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL0');
        document.getElementById('TboxRegCalP2').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL1');
        document.getElementById('TboxRegCalP3').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL2');
        document.getElementById('TboxRegCalP4').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL3');
        document.getElementById('TboxRegCalP5').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL4');
        document.getElementById('TboxRegCalP6').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL5');
        document.getElementById('TboxRegCalP7').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL6');
        document.getElementById('TboxRegCalP8').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL7');
        document.getElementById('TboxRegCalP9').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL8');
        document.getElementById('TboxRegCalP10').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL9');

        document.getElementById('TextBox1').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL10');
        document.getElementById('TextBox2').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL11');
        document.getElementById('TextBox3').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL12');
        document.getElementById('TextBox4').value = this.getCookieVaL(this.getCookie('ckParaGpSor'+i),'paraCaL13');
        this.setcookieGp();//cookieGP恢复原值
        document.getElementById('Button4').disabled=true;document.getElementById('Button5').disabled=true;

      }

    },
    setcookieGp:function()
    {
      for(i=0;i<5;i++)
      {
        name='cookieParaGp'+i;
        strvaL=getCookie('ckParaGpSor'+i);
        document.cookie =name+'='+strvaL;
      }
    },

     btn2meanRain:function()
    {
// int i; int j;

      rainV= document.getElementById('TextBox1').value;
      // rainV =Math.Round(rainV ,2);
//        for (i = 0; i < 5; i++)
//        {
//            cookieParaGp[i]["paraCaL10"] = rainV.ToString (); cookieParaGp[i]["paraCaL11"] = rainV.ToString ();
//            cookieParaGp[i]["paraCaL12"] = rainV.ToString();
//            Response.Cookies.Add(cookieParaGp[i]);
//        }
      alert(",填写对了"+rainV );
    },
     btn5Click:function()
    {

      window.open ("InputSavePara.aspx", "newwindow", "height=100, width=400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
      alert("填写对了");

    },
          // //放大
          // magnify:function () {
          //   let main =document.getElementById("mainRain");
          //
          //   main.style.height="100%";
          //   main.style.width="100%";
          //   main.style.left='0px';
          //   main.style.top='0px';
          //   document.getElementById("rainEchartsContainer").style.height='500px';
          //   this.myChart.resize();
          //   document.getElementById("tableContainer").style.height='650px';
          //   document.getElementById("minusButton").style.display='inline-block';
          //   document.getElementById("magnifyButton").style.display='none';
          //
          // },
          // //缩小
          // minus:function () {
          //   let main =document.getElementById("mainRain");
          //
          //   main.style.height="570px";
          //   main.style.width="895px";
          //   main.style.left='260px';
          //   main.style.top='100px';
          //   document.getElementById("rainEchartsContainer").style.height='390px';
          //   this.myChart.resize();
          //   document.getElementById("tableContainer").style.height='525px';
          //   document.getElementById("minusButton").style.display='none';
          //   document.getElementById("magnifyButton").style.display='inline-block';
          // }
          //移动相关
          fndown:function(main,event,container){
            let _this = this;
            event = event||window.event;
            let disX = event.clientX - main.offsetLeft,//记录鼠标相对于矩形的位置
              disY =event.clientY - main.offsetTop;
            document.onmousemove = function(event){

              event = event||window.event;
              _this.fnMove(event,disX,disY,main)
            };
            container.onmouseup = function(){//松开鼠标后无法拖动

              document.onmousemove = null;
              container.onmouseup = null ;
            }


          },
          fnMove:function(event,disX,disY,main){//鼠标移动事件
            let l = event.clientX - disX,//根据鼠标的位置改变，来改变矩形相对于body的位置
              t = event.clientY - disY;
            main.style.left=l+'px';
            main.style.top=t+'px';
          },
          btn1click:function (event) {
            event.preventDefault();
            //alert("cc");
            this.axios.post("http://localhost:8083/btn1Click",this.FrmData).then(
              response=>{
                alert(response.data);

              }).catch(function (error) {
              // 处理错误情况
              alert(error);
            });

          },
        },
        mounted() {
          //设置检查
          let _this = this;
          //移动
          let main=document.getElementById("main")
          //let rainWindowHead = document.getElementById("rainWindowHead")
          main.onmousedown= function(event){
            _this.fndown(main,event,main);
          }

          this.axios.get("http://localhost:8083/FrmInit").then(
            response=>{
              console.log(response.data);
              for(let i in response.data){
                this.FrmData[i]=response.data[i];
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
