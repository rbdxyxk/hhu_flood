export default function OutPutResult() {
  //获取选项参数
  var id;
  var status;
  var startTime;
  var endTime;
//时间间隔数组
  var timeArr = [];
//定义图例信息
  var legend_data = [];

  /**
   * 准备下拉框中的数据
   */
  function prepare() {

    //获取选项选中的参数
    getOptions();

    var str_top = "";
    var str_down = "";

    $.ajax({
      url: "../prepareServlet",
      data: {
        "id": id,
      },
      dataType: "json",
      success: function (data) {

        console.log('success')
        console.log("data = " + data)

        for (let i = 0; i < data.length; i++) {
          if (data[i].id.indexOf("自动预报") > 0) {
            str_down += '<option>' +
              data[i].id +
              '</option>';

          } else {
            str_top += '<option>' +
              data[i].id +
              '</option>';
          }
        }

        //获取div'向里面填充代码
        var select_top = document.getElementById("btnDownSelect1");
        var select_down = document.getElementById("btnDownSelect2");

        //填入代码
        select_top.innerHTML = str_top;
        select_down.innerHTML = str_down;

        //获取当前选中参数的计算值
        insert_data();

      },

    });
  }

  window.onload = prepare;

  /**
   * 插入当前选中选项的起始时间，结束时间，总时长的数据
   */
  function insert_data() {

    //获取选中的参数
    getOptions();


    $.ajax({
      url: "../insertDataServlet",
      data: {
        "id": id,
      },
      dataType: "json",
      success: function (data) {
        console.log('success')
        console.log("data = " + data)

        console.log(data[0].start)
        console.log(data[0].calTimeLong)
        console.log(data[0].calculateDate)

        console.log("插入数据")
        //填入数据
        $('#startTime').val(data[0].start)
        $('#sumTime').val(data[0].calTimeLong)

        $('#endTime').val(data[0].calculateDate)

        console.log("插入option")

        // 插入对应时段数的option


        for (let i = 0; i < parseInt(data[0].calTimeLong); i++) {
          $('#btnDown1').append('<option value=\"' +
            i +
            '\">' +
            i +
            '</option>')
          $('#btnDown2').append('<option value=\"' +
            i +
            '\">' +
            i +
            '</option>')

        }

      },

    });

  }


  /**
   * 获取选项参数，并获取数据
   */
  function getOptions() {
    //清空数据
    id = '';
    status = 1;
    startTime = 0;
    endTime = startTime + 1;
    timeArr = [];
    legend_data = [];

    /*------判断radio是否有选中，获取选中的值--------*/
    var autoOrMan = $('input[name="selectMenu"]:checked').val();

    //获取单选框被选中的选项的id
    if (autoOrMan == "auto") {
      id = $("#btnDownSelect1 option:selected").text(); //获取选中的项
    } else {
      id = $("#btnDownSelect2 option:selected").text();
    }

    //判断选择的是入库流量还是库水位
    var shuiOrRu = $('input[name="selectRuOrShui"]:checked').val();
    if (shuiOrRu == "ru") {
      status = 1;
      legend_data = ['入库流量', '入库水量'];
    } else {
      status = 2;
      legend_data = ['库水位', '库蓄量'];
    }

    startTime = $("#btnDown1").val(); //获取选中的项
    endTime = $("#btnDown2").val(); //获取选中的项

    //准备图例x轴数据，为画图做准备
    for (let i = startTime, temp = 0; i <= endTime; i++) {
      timeArr[temp++] = i;
    }

    console.log("timeArr = " + timeArr);

    console.log("id=" + id)
    console.log("status=" + status)
    console.log("startTime=" + startTime)
    console.log("endTime=" + endTime)
  }

//存放数据的数组
  var data1 = [];//y轴刻度
  var data2 = [];//y轴刻度
  var data3 = [];//表格的入库日期
  var data4 = [];//时间戳pdnum
  var data5 = [];//展示数据
  var data6 = [];//展示数据
  var data7 = [];//x轴刻度

  var max1;
  var max2;
  var min1;
  var min2;
  var interval1;
  var interval2;


  /**
   * 入口，提交请求
   */
  function commit() {
    //获取选项参数
    getOptions();

    //清空数据
    data1 = [];
    data2 = [];
    data3 = [];
    data4 = [];
    data5 = [];
    data6 = [];
    data7 = [];

    $.ajax({
      url: "../outPutResultServlet",
      data: {
        "id": id,
        "status": status,
        "startTime": startTime,
        "endTime": endTime,
      },
      dataType: "json",
      success: function (data) {
        console.log('success')
        console.log("data = " + data)

        for (let i = 0; i < data.length; i++) {
          data3[i] = data[i].flowtm;
          data4[i] = data[i].pdnum;
          //取datetime的时间部分
          data7[i] = data[i].flowtm.substring(11);
        }

        // //第一个刻度带日期
        // data7[0] = data3[0];

        max1 = -1;
        min1 = 999999;
        max2 = -1;
        min2 = 999999;


        if (status == 1) {


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
        } else if (status == 2) {
          for (let i = 0; i < data.length; i++) {
            console.log(data[i].zi);
            console.log(data[i].v.toFixed(2));

            data1[i] = parseInt(data[i].zi);
            data2[i] = parseInt(data[i].v);

            data5[i] = data[i].zi;
            data6[i] = data[i].v.toFixed(2);

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

        //插入图表
        insertTable();
        //画图
        drawPicture();

      },

    });
  }

  /**
   * 获取表格数据并显示
   */
  function insertTable() {

    console.log("insertTable~~")

    //获取div'向里面填充代码
    var tableDiv = document.getElementById("table2");
    //清空之前的代码
    tableDiv.innerHTML = '';

    var str_table = '<table class="table table-striped table-bordered table-hover" id="table_tr">';

    //根据选择插入表格头
    if (status == 1) {
      str_table += '<tr>\n' +
        '            <td>计算时段</td>\n' +
        '            <td>入库时间</td>\n' +
        '            <td>入库流量(m3/s)</td>\n' +
        '            <td>入库水量(万m³)</td>\n' +
        '            <td>编辑</td>\n' +
        '            <td>删除</td>\n' +
        '        </tr>';
    } else {
      str_table += '<tr>\n' +
        '            <td>计算时段</td>\n' +
        '            <td>入库时间</td>\n' +
        '            <td>库水位(m)</td>\n' +
        '            <td>库蓄量(万m³)</td>\n' +
        '            <td>编辑</td>\n' +
        '            <td>删除</td>\n' +
        '        </tr>';
    }

    for (let number = 0; number < data1.length; number++) {
      str_table += '<tr>\n' +
        '            <td>' +
        number +
        '</td>\n' +
        '            <td>' +
        data3[number] +
        '</td>\n' +
        '            <td>' +
        data5[number] +
        '</td>\n' +
        '            <td>' +
        data6[number] +
        '</td>\n' +
        '            <td><button class="btn btn-default" onclick="edit(this)" >编辑</button></td>\n' +
        '            <td><button class="btn btn-default" onclick="deleteData(this)">删除</button></td>\n' +
        '        </tr>';

    }

    str_table += '</table>';

    // console.log(str_table)
    tableDiv.innerHTML = str_table;

  }


  /**
   * 删除成果
   */
  function deleteResult(){

    //获取选中的参数
    getOptions();

    $.ajax({
      url : "../deleteResultServlet",
      data :{
        // uid : uid,   应该后端获取吧？
        "id" : id
      },
      dataType : "json",
      success : function(data){

        console.log('success')
        console.log(data)
        console.log(data.status)
        console.log(data.msg)

        alert(data.msg);

        //刷新页面
        window.location.reload();

      },

    });

  }

  var rowIndex;
  function edit(obj){

    var row = obj.parentNode.parentNode; //A标签所在行
    var tb = row.parentNode; //当前表格

    var data_1 = $(row).find('td').eq(2).text()
    var data_2 = $(row).find('td').eq(3).text()
    console.log("data_1 : " + data_1)
    console.log("data_2 : " + data_2)

    rowIndex = row.rowIndex; //A标签所在行下标

    console.log("rowIndex = " + rowIndex)

    var newTr=tb.insertRow(rowIndex+1);//表示在第rowIndex行后添加一行


    var new_str = '            <td></td>\n' +
      '            <td></td>\n' +
      '            <td><input type="text" name="data_1" placeholder="请输入修改的数值"></td>\n' +
      '            <td><input type="text" name="data_2" placeholder="请输入修改的数值"></td>\n' +
      '            <td><button class="btn btn-default" onclick="updateData(rowIndex)">修改</button></td>\n' +
      '            <td><button class="btn btn-default" onclick="delete_row(this)">取消</button></td>\n'

    newTr.innerHTML = new_str;

    // 将原来的值填入
    $('input[name="data_1"]').val(data_1);
    $('input[name="data_2"]').val(data_2);

  }

  /**
   * 修改数据
   */
  function updateData(rowIndex){

    //获取当前选中参数
    getOptions();

    //获取input中要修改值的数据
    var data1 = $('input[name="data_1"]').val();
    var data2 = $('input[name="data_2"]').val();

    $.ajax({
      url : "../updateDataServlet",
      data :{
        "id" : id,
        "flowtm" : data3[rowIndex-1],
        "data1" : data1,
        "data2" : data2,
        "status" : status
      },
      dataType : "json",
      success : function(data){

        console.log('success')
        console.log(data)
        console.log(data.status)
        console.log(data.msg)

        alert(data.msg);

        //修改完成后要修改页面当前显示的数据
        $("table tr:eq(" + rowIndex + ") td:eq(2)").text(data1)
        $("table tr:eq(" + rowIndex + ") td:eq(3)").text(data2)



      },

    });

  }


  /**
   * 删除某行
   */
  function delete_row(obj){


    var row = obj.parentNode.parentNode; //A标签所在行
    var tb = row.parentNode; //当前表格
    var rowIndex = row.rowIndex; //A标签所在行下标

    tb.deleteRow(rowIndex); //删除当前行

  }

  /**
   * 删除数据
   */
  function deleteData(obj){

    //获取选中的参数
    getOptions();

    var row = obj.parentNode.parentNode; //A标签所在行
    var tb = row.parentNode; //当前表格
    var rowIndex = row.rowIndex; //A标签所在行下标

    console.log("rowIndex = " + rowIndex)

    var text = $("table tr:eq(" + rowIndex + ") td:eq(1)").text()
    console.log("text = " + text)

    console.log("data3 = " + data3[rowIndex-1])

    tb.deleteRow(rowIndex); //删除当前行


    $.ajax({
      url : "../deleteResultServlet",
      data :{
        // uid : uid,   应该后端获取吧？
        "id" : id,
        "flowtm" : text
      },
      dataType : "json",
      success : function(data){

        console.log('success')
        console.log(data)
        console.log(data.status)
        console.log(data.msg)

        alert(data.msg);


      },

    });

  }


  var myChart;
  /**
   * 使用echart画图
   */
  function drawPicture() {

    console.log("drawPicture~~")

    //获取div的宽高
    $('#photo').width($('#photo').width());
    $('#photo').height($('#photo').height());


    if (myChart != null && myChart != "" && myChart != undefined) {
      myChart.dispose();//解决echarts dom已经加载的报错
    }

    // 基于准备好的dom，初始化echarts实例
    myChart = echarts.init(document.getElementById('photo'));

    //定义x轴刻度
    // var timeArr = ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'];

    // //定义图例信息
    // var legend_data = ['入库流量', '入库水量'];

    // 指定图表的配置项和数据
    var option = {
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
            //icon:"image://image/excel.png",             // ‘数据视图’按钮自定义img
            contentToOption: function (opt) {
              $('#photo').table2excel({
                // 下载jquery.table2excel.js，引入，$("#main")是Echarts容器
                exclude: '.noExl', //过滤位置的 css 类名， 有class = “noExl” 的行不被导出
                filename:  '测量数据.xls', // 文件名称
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
          data: data7,
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
            formatter: status == 1 ? '{value} m³/s' : '{value} m'
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
            formatter: '{value} 万m³'
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
    myChart.setOption(option);

    //展示图表
    $('#photo').show();

  }
}