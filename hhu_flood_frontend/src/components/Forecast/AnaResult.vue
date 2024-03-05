<template>
  <div>

    <div style="padding-left: 50px">
      <h3><span class="label label-default">预报结果分析</span></h3>
    </div>

    <div id="table1" style="border: 1px black solid;width: 1000px;margin: auto;padding-left: 15px;margin-top: 10px;">

      <div id="AutoOrMan" style="display: inline-block;width: 43%;margin-left: 30px">
        <!--            <label for="btnDownSelect1" >人工干预预报计算成果名称</label><br>-->
        <div style="width: 100px;height: 30px">
          <span class="label label-info" style="font-size: 100%">人工干预预报计算成果名称</span>
        </div>
        <div class="dropdown">

          <div class="inlineDiv">
            <input type="radio" id="btnDownMenu1" v-model="selectMenu" @change="getOtherData" name="selectMenu" value="auto" checked="checked" />

          </div>
          <div class="inlineDiv">
            <select name="auto" id="btnDownSelect1"
                    v-model="selectedId_top"
                    @change="getOtherData" class="form-control"  style="width: 250px">
              <option v-for="(id, index) in select_top"
                      :key="index"
              :value="id">
                {{id}}
              </option>


            </select>
          </div>
        </div>

        <div style="width: 100px;height: 30px;margin-top: 10px">
          <span class="label label-info" style="font-size: 100%">自动预报计算成果名称</span>
        </div>
        <!--            <label for="btnDownSelect2">自动预报计算成果名称</label><br>-->
        <div class="dropdown">
          <div class="inlineDiv">
            <input type="radio" id="btnDownMenu2" v-model="selectMenu" @change="getOtherData" name="selectMenu" value="man" />

          </div>
          <div class="inlineDiv">
            <select name="man" id="btnDownSelect2"
                    v-model="selectedId_down"
                    @change="getOtherData" class="form-control" style="width: 250px">
              <option v-for="(id, index) in select_down"
                      :key="index"
              :value="id">
                {{id}}
              </option>

            </select>
          </div>

        </div>

      </div>

      <div id="Calculate" style="display: inline-block;margin-top: 30px;width: 48%;margin-left: 30px">
        <div  class="input-group col-lg-7" style="padding-bottom: 5px">
          <span  class="input-group-addon">计算起始时间</span>
          <input type="text" id="startTime" class="form-control" v-model="obj.start">
        </div>

        <div class="input-group col-lg-7" style="padding-bottom: 5px">
          <span class="input-group-addon">计算总时长（小时）</span>
          <input type="text" id="sumTime" class="form-control" v-model="obj.calTimeLong">
        </div>

        <div class="input-group col-lg-7" style="padding-bottom: 5px">
          <span class="input-group-addon">计算结束时间</span>
          <input type="text" id="endTime" class="form-control" v-model="obj.calculateDate">
        </div>
      </div>

      <div id="selectRuOrShui" style="display: inline-block;width: 48%;padding-top: 20px;margin-left: 30px">
        <span>输出指定时段</span>
        <div class="dropdown_2">
          <div class="inlineDiv">
            <label for="time111">从</label>
          </div>
          <div class="inlineDiv">
            <select v-model="selectedStartTime" name="fromTime" id="time111" class="form-control">
              <option v-for="(number,index) in obj.calTimeLong"
                      :key="index"
                      :value="number">{{number}}</option>

            </select>
          </div>
        </div>

        <div class="dropdown_2">
          <div class="inlineDiv">
            <label for="time111">至</label>

          </div>

          <div class="inlineDiv">
            <select v-model="selectedEndTime" name="endTime" id="time222" class="form-control">
              <option v-for="(number,index) in obj.calTimeLong"
                      :key="index"
                      :value="number">{{number}}</option>

            </select>
          </div>
        </div>
        <br>
        <div class="radio" >
          <label>
            <input type="radio" id="select1" v-model="selectRuOrShuiOrFenqu" name="selectRuOrShuiOrFenqu" value="ru" checked="checked"/>
            入库流量、水量过程
          </label>
        </div>
        <div class="radio">
          <label>
            <input type="radio" id="select2" v-model="selectRuOrShuiOrFenqu" name="selectRuOrShuiOrFenqu" value="shui"/>
            水库水位、蓄量过程
          </label>
        </div>
        <div class="radio">
          <label>
            <input type="radio" id="select3" v-model="selectRuOrShuiOrFenqu" name="selectRuOrShuiOrFenqu" value="fenqu"/>
            查询计算分区产流过程
          </label>
          <div class="inlineDiv">
            <select name="endTime" id="shilianghe_value" class="form-control">
              <option value="shiliang1">石梁河临沭分区</option>
              <option value="shiliang2">石梁河石门分区</option>
              <option value="shiliang3">石梁河大兴镇分区</option>
              <option value="shiliang4">石梁河张疃分区</option>
              <option value="shiliang5">石梁河库区周边分区</option>

            </select>
          </div>
        </div>

      </div>


      <div id="btns" style="display: inline-block;width: 46%;margin-top: 30px;float: right">
        <button class="btn btn-default" type="submit" @click="getPicture">搜索</button>

      </div>

    </div>


  <div>
    <div id="echartsPhoto" style="display:none;margin:0 auto;width: 1000px;height:500px;border:1px black solid">

    </div>
  </div>

  </div>

</template>

<script>
import axios from "axios";

export default {
  name: "AnaResult",
  data(){
    return{
      selectedId_top:"",// 上面下拉框选择的id
      selectedId_down:"",// 下面下拉框选择的id
      selectMenu:"",// 当前选择哪个下拉框
      select_top:[], // 上面下拉框的数据
      select_down:[],// 下面下拉框的数据
      id:"", // 最终查询的id
      selectedStatus:1,// 最终哪个状态
      selectRuOrShuiOrFenqu:"",// 选择哪个查询状态
      obj:{
        start:"",// 计算起始时间
        calTimeLong:"",// 计算总时长
        calculateDate:"",// 计算结束时间
      },
      CalRegCD:"",//选择哪个分区的产流
      selectedStartTime:"",
      selectedEndTime:"",
      photoShow:false,
      myChart:null,
    }
  },
  methods:{


    /**
     * 查询所选数据  起始时间等
     */
     async getOtherData(){
      console.log("getOtherData.....")
      console.log("id....." + this.id)
      console.log("topId....." + this.selectedId_top)
      console.log("downId....." + this.selectedId_down)
      console.log("selectMenu....." + this.selectMenu)

      if (this.selectMenu == "auto")
        this.id = this.selectedId_top
      else if (this.selectMenu == "man")
        this.id = this.selectedId_down

      console.log("当前id = " + this.id)
      await axios.post('http://localhost:8083/anaResult/findById?id=' + this.id)
        .then(res => {

          console.log(res.data)
          var cur = res.data;

          this.obj.start = cur.start;
          this.obj.calTimeLong = cur.calTimeLong;
          this.obj.calculateDate = cur.calculateDate;

        });
      this.selectedStartTime = 1
      this.selectedEndTime = 1
    },
    /**
     * 准备候选数据
     */
    async prepare(){

      await axios.post('http://localhost:8083/anaResult/findAll' )
        .then(res => {
          alert(res.data);
          console.log("res.data = " + res.data)
          for (let i = 0; i < res.data.length; i++) {
            var cur = res.data[i];
            console.log("cur = " + cur)
            if (cur.id.indexOf("自动预报") > 0) {
              this.select_down.push(cur.id);
              if (this.selectedId_down.length == 0)
                this.selectedId_down = cur.id
            } else {
              this.select_top.push(cur.id);
              if (this.selectedId_top.length == 0){
                this.selectMenu = "auto"
                this.selectedId_top = cur.id
              }
            }
          }

          console.log("length = " + this.select_top.length)
          for (let i = 0; i < this.select_top.length; i++) {
            console.log(this.select_top[i]);
          }
          this.id = this.select_top[0];
          console.log("id = " + this.id);
          console.log("status = " + this.selectedStatus);

          this.selectRuOrShuiOrFenqu = "ru";

          // var cur = res.data[0];
          // console.log("cur.id = " + cur)
          // this.obj.start = cur.start;
          // this.obj.calTimeLong = cur.calTimeLong;
          // this.obj.calculateDate = cur.calculateDate;
          // console.log(this.obj.start)
          // console.log(this.obj.calculateDate)
          // console.log(this.obj.calTimeLong)
          this.getOtherData()

        });

    },


    /**
     * 查询图表
     */
    async getPicture(){
      //存放数据的数组
      let data1 = [];//y轴刻度
      let data2 = [];//y轴刻度
      let data3 = [];//表格的入库日期
      let data4 = [];//时间戳pdnum
      let data5 = [];//展示数据
      let data6 = [];//展示数据
      let data7 = [];//x轴刻度
      let legend_data = [];
      let max1;
      let max2;
      let min1;
      let min2;
      let interval1;
      let interval2;
      let timeArr = []

      // var start=$("#time111 option:selected").val(); //获取选中的项
      // var end=$("#time222 option:selected").val(); //获取选中的项
      var start=this.selectedStartTime; //获取选中的项
      var end=this.selectedEndTime; //获取选中的项
      console.log("start = " + start)
      console.log("end = " + end)

      //准备图例x轴数据，为画图做准备
      for (let i = start, temp = 0; i <= end; i++) {
        timeArr[temp++] = '时段 ' + i;
      }
      for (let i =0; i <timeArr.length; i++) {
        console.log("timeadd = " + timeArr[i])
      }

      if (this.selectRuOrShuiOrFenqu == "ru"){
        this.selectedStatus = 1;
        legend_data = ['入库流量(m³/s)', '入库水量(m)'];
      }else if (this.selectRuOrShuiOrFenqu == "shui"){
        this.selectedStatus = 2;
        legend_data = ['库水位(m)', '库蓄量(万m³)'];
      }else {
        this.selectedStatus = 3;
        legend_data = ['产流量(m)', '降雨量(万m³)'];
      }
      var CalRegCD = $("#shilianghe_value").val() == undefined ? "shiliang1" :$("#shilianghe_value").val();
      var status = this.selectedStatus;
      console.log("status = " + this.selectedStatus)
      await axios.post('http://localhost:8083/anaResult/findByStatus?id='
        + this.id
        + '&status=' + this.selectedStatus
        + '&startTime=' + start
        + '&endTime=' + end
        + '&CalRegCD='+ CalRegCD)
        .then(res => {
          console.log("res = " + res)
          console.log("res.data = " + res.data)
          console.log("res.data.length = " + res.data.length)
          var data = res.data;
          for (let i = 0; i < data.length; i++) {
            console.log("data = " + data[i])
            console.log("data = " + data[i].fLOWTM)
            console.log("data = " + data[i].pDNUM)
            data3[i] = data[i].fLOWTM;
            data4[i] = data[i].pDNUM;
            //取datetime的时间部分
            data7[i] = data[i].fLOWTM.substring(11);
            // data7[i] = data[i].flowtm;
          }

          // //第一个刻度带日期
          // data7[0] = data3[0];

          max1 = -1;
          min1 = 999999;
          max2 = -1;
          min2 = 999999;

          console.log("curStatus = " + this.selectedStatus)
          if (this.selectedStatus == 1) {


            for (let i = 0; i < data.length; i++) {
              console.log(data[i].q);
              console.log(data[i].w);

              data1[i] = parseInt(data[i].q);
              data2[i] = parseInt(data[i].w);
              data5[i] = data[i].q;
              data6[i] = data[i].w;


              //获取最大值
              if (max1 < data1[i]) {
                max1 = parseInt(data1[i]);
              }
              if (max2 < data2[i]) {
                max2 = parseInt(data2[i]);
              }

              // 获取最小值
              if (min1 > data1[i]) {
                min1 = parseInt(data1[i]);
              }
              if (min2 > data2[i]) {
                min2 = parseInt(data2[i]);
              }

            }
          } else if (this.selectedStatus == 2) {
            for (let i = 0; i < data.length; i++) {
              console.log(data[i].zI);
              console.log(data[i].v);

              data1[i] = parseInt(data[i].zI);
              data2[i] = parseInt(data[i].v);

              data5[i] = data[i].zI;
              data6[i] = data[i].v;

              //获取最大值
              if (max1 < data1[i]) {
                max1 = parseInt(data1[i]);
              }
              if (max2 < data2[i]) {
                max2 = parseInt(data2[i]);
              }

              // 获取最小值
              if (min1 > data1[i]) {
                min1 = parseInt(data1[i]);
              }
              if (min2 > data2[i]) {
                min2 = parseInt(data2[i]);
              }

            }
          } else {
            for (let i = 0; i < data.length; i++) {
              console.log(data[i].q.toFixed(2));
              console.log(data[i].p);

              data1[i] = parseInt(data[i].q);
              data2[i] = parseInt(data[i].p);

              data5[i] = data[i].q.toFixed(2);
              data6[i] = data[i].p;

              //获取最大值
              if (max1 < data1[i]) {
                max1 = parseInt(data1[i]);
              }
              if (max2 < data2[i]) {
                max2 = parseInt(data2[i]);
              }

              // 获取最小值
              if (min1 > data1[i]) {
                min1 = parseInt(data1[i]);
              }
              if (min2 > data2[i]) {
                min2 = parseInt(data2[i]);
              }

            }
          }

          console.log("data1 = " + data1);
          console.log("data2 = " + data2);
          console.log("data3 = " + data3);

          //求组距
          interval1 = parseInt((max1 - min1) / data1.length) == 0 ? 1 : parseInt((max1 - min1) / data1.length);
          interval2 = parseInt((max2 - min2) / data2.length) == 0 ? 1 : parseInt((max2 - min2) / data2.length);

          console.log("max:" + data1[0]);
          console.log("type:" + typeof (data1[0]))

          console.log("max1:" + max1);
          console.log("type:" + typeof (max1))

          console.log("interval-1:" + interval1);
          console.log("interval-2:" + interval2);
          console.log("type:" + typeof (interval1))

          min1 = min1 - interval1 < 0 ? 0 : min1 - interval1
          min2 = min2 - interval2 < 0 ? 0 : min2 - interval2

          max1 = max1 + interval1;
          max2 = max2 + interval2;

          console.log("max1 = " + max1)
          console.log("max2 = " + max2)
          console.log("min1 = " + min1)
          console.log("min2 = " + min2)

          console.log("data3[0] = " + data3[0])
          console.log("typeof:data3[0] = " + typeof (data3[0]))
          console.log("data1 = " + data1);
          console.log("data2 = " + data2);
          console.log("data3 = " + data3);
          console.log("data4 = " + data4);
          console.log("data5 = " + data5);
          console.log("data6 = " + data6);
          console.log("data7 = " + data7);


          // //获取div的宽高
          $('#echartsPhoto').width($('#echartsPhoto').width());
          $('#echartsPhoto').height($('#echartsPhoto').height());
          // $('#photo').width('500px');
          // $('#photo').height('500px');

          console.log("width = " + $('#echartsPhoto').width())
          console.log("width = " + $('#echartsPhoto').height())

          if (this.myChart != null && this.myChart != "" && this.myChart != undefined) {
            this.myChart.dispose();//解决echarts dom已经加载的报错
            this.myChart = null;
          }

          // 基于准备好的dom，初始化echarts实例
          this.myChart=this.$echarts.init(document.getElementById("echartsPhoto"));
          console.log(this.myChart)

          // myChart = echarts.init(document.getElementById('photo'));

          //定义x轴刻度
          // var timeArr = ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'];

          // //定义图例信息
          // var legend_data = ['入库流量', '入库水量'];

          // 指定图表的配置项和数据
          let option = {
            tooltip: {
              trigger: 'axis',
              axisPointer: {
                type: 'cross',
                crossStyle: {
                  color: '#999'
                }
              }
            },
            // 操作栏
            toolbox: {
              feature: {
                //数据视图
                dataView: {
                  show: true,
                  // readOnly: false,
                  title: '表格数据',
                  lang: ['表格数据：', '关闭', '导出Excel'], // 按钮
                  icon:"image://image/excel.png",             // ‘数据视图’按钮自定义img
                  contentToOption: function (opt) {
                    $('#echartsPhoto').table2excel({
                      // 下载jquery.table2excel.js，引入，$("#main")是Echarts容器
                      exclude: '.noExl', //过滤位置的 css 类名， 有class = “noExl” 的行不被导出
                      filename: '测量数据.xls', // 文件名称
                      name: 'Excel Document Name.xls',
                      exclude_img: true, // 是否导出图片 false导出
                      exclude_links: true, //是否导出链接 false 导出
                      exclude_inputs: true, // 是否导出输入框的值 true导出
                    });
                  },
                  //数据视图展示为table
                  optionToContent: function (opt) {
                    var axisData = opt.xAxis[0].data;
                    var series = opt.series;
                    // tableName = opt.title[0].text;
                    console.log(opt);
                    console.log(series);
                    var table = '<table style="width:100%;text-align:center"><tbody><tr>' + '<td>时间</td>';
                    for (var k in series) {
                      table += '<td>' + series[k].name + '</td>';
                    }

                    table += '</tr>';
                    for (var i = 0, l = axisData.length; i < l; i++) {
                      table += '<tr>' + '<td>' + axisData[i] + ':00:00' + '</td>';
                      for (var k in series) {
                        table += '<td>' + series[k].data[i] + '</td>';
                      }

                      table += '</tr>';
                    }
                    table += '</tbody></table>';
                    return table;
                  },
                },
                //转换展示形式
                magicType: {
                  show: true,
                  type: ['line', 'bar']
                },
                //还原最初形式
                restore: {
                  show: true
                },
                //保存图片
                saveAsImage: {
                  show: true
                }
              }
            },
            //图例
            legend: {
              data: legend_data
            },
            xAxis: [
              {
                type: 'category',
                // data: this.selectedStatus == 3 ? timeArr : data7,
                data: timeArr,
                axisPointer: {
                  type: 'shadow'
                }
              }
            ],
            yAxis: [
              //设置y轴属性
              {
                type: 'value',
                name: legend_data[0],
                min: min1 - interval1 < 0 ? 0 : min1 - interval1,
                max: max1 + interval1,
                interval: interval1,
                axisLabel: {
                  formatter: this.selectedStatus == 1 ? '{value} m³/s' : '{value} m'
                }
              },
              //设置y轴属性
              {
                type: 'value',
                name: legend_data[1],
                min: min2 - interval2 < 0 ? 0 : min2 - interval2,
                max: max2 + interval2,
                interval: interval2,
                axisLabel: {
                  formatter: this.selectedStatus == 3 ? '{value} mm' : '{value} 万m³'
                }
              }
            ],
            //展示数据
            series: [
              {
                name: legend_data[0],
                type: 'bar',
                // data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
                data: data5
              },
              {
                name: legend_data[1],
                type: 'line',
                yAxisIndex: 1,
                // data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
                data: data6
              }
            ]
          };

          // 使用刚指定的配置项和数据显示图表。
          this.myChart.setOption(option);

          //展示图表
          // this.photoShow = true;
          $('#echartsPhoto').show();
          console.log("showPhoto")

        });
    },

  },
  beforeMount() {
    this.prepare();

  },
  mounted() {

  }
}
</script>

<style scoped>
.dropdown_2{
  display: inline-block;
}

.inlineDiv{
  display: inline-block;
}
</style>
