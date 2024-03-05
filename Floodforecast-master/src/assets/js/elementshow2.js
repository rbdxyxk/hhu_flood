import "./jquery.table2excel.min"
export default function elementShow(viewer,handler) {

  let entities;
//绑定点击事件
  $("#gridShowData").on("click",function () {

    $("#myButtons").css('display','block');
    //设置擦除

    viewer.camera.flyTo({
      destination: Cesium.Cartesian3.fromDegrees(119.093413331388,34.704087757320444, 50000.0),
      duration: 0
    });
    //设置handler

    handler.setInputAction(getSingleElementData, Cesium.ScreenSpaceEventType.LEFT_CLICK);
    let gridFlag =true;
    let datasource = Cesium.GeoJsonDataSource.load("../../../static/gejson/ploygon2.geojson",
      {
        stroke: Cesium.Color.WHITE,
        fill: Cesium.Color.BLUE.withAlpha(0.1), //注意：颜色必须大写，即不能为blue
        strokeWidth: 5
      }).then(function (ds) {
      viewer.dataSources.add(ds);
      entities = ds.entities.values;


      // entity.billboard = undefined;
    });
// var ds = viewer.dataSources.add(datasource);
    $("#drawGrid").on('click',function () {
      if(gridFlag){
        console.log(1);
        for (let i in entities) {
          let entity = entities[i];
          entity.billboard=undefined;
          entity.polygon.outlineColor=Cesium.Color.BLUE.withAlpha(0.0);

        }




        gridFlag = false;

      }else {
        // alert("a");
        console.log(2);





        // console.log(datasource);
        for (let i in entities) {
          let entity = entities[i];
          entity.billboard=undefined;
          entity.polygon.outlineColor=Cesium.Color.WHITE;

        }
        gridFlag=true;
      }
    });
  })



  let  popup = null;

  let colors = ['#5470C6', '#d2d03f', '#91cc75'];
  let showElements = {
    title:{
      text:"输出数据"
    },

    backgroundColor:'rgba(128, 128, 128, 0.5)',
    textStyle: {
      color:'rgba(0, 0, 0, 1)',
      // fontSize:3000,
      fontWeight:'bolder',
      fontFamily:'Arial',
    },

    grid:{
      left:'8%',//设置偏移量
      right:'15%'
    },
    tooltip: {
      trigger:"axis"
    },
    toolbox: {
      show: true,
      showTitle:true,
      // shadowColor: 'rgba(255,255,255,1)' ,
      // textStyle:{
      //        color:'rgba(0,0,255,1)'
      // },
      iconStyle:{
        // color:'rgba(255,0,0,1)',
        borderColor:'rgba(0,0,0,1)',
      },
      feature: {
        saveAsImage: {},
        dataView: {
          show: true,
          title: '表格数据',
          lang: ['表格数据：', '关闭', '导出Excel'],    // 按钮
          //icon:"image://image/excel.png",             // ‘数据视图’按钮自定义img
          contentToOption: function (opt) {

            $("#main").table2excel({           // 下载jquery.table2excel.js，引入，$("#main")是Echarts容器
              exclude: ".noExl", //过滤位置的 css 类名， 有class = “noExl” 的行不被导出
              filename: "输出结果数据.xls", // 文件名称
              name: "Excel Document Name.xls",
              exclude_img: true,// 是否导出图片 false导出
              exclude_links: true,//是否导出链接 false 导出
              exclude_inputs: true// 是否导出输入框的值 true导出
            });
          },
          //数据视图展示为table
          optionToContent: function (opt) {
            var axisData = opt.xAxis[0].data;
            var series = opt.series;
            // tableName = opt.title[0].text;
            console.log(opt);
            console.log(series);
            let num =1;
            var table = '<table style="width:100%;text-align:center"><tbody><tr>'
              +'<td>序号</td>'+ '<td>单元</td>';
            for(var k in series){
              table+='<td>' + series[k].name + '</td>'

            }

            table  += '</tr>';
            for (var i = 0, l = axisData.length; i < l; i++) {
              table += '<tr>'
                +'<td>' + (num)  +'</td>' + '<td>' + axisData[i]  +'</td>';
              num+=1;
              for(var k in series){
                table+='<td>' + series[k].data[i] + '</td>';
              }

              table += '</tr>';
            }
            table += '</tbody></table>';
            return table;

          }
        },



        myMinus:{
          show:true,
          title:'缩小',
          icon:'image://../static/images/minus.png',
          onclick:function(){

            console.log("minus");
            // $("#main").children().width(50).height(50);
            // $("#main").children().children().width(50).height(50);
            //$("#main").width(100).height(30);
            myChart.dispose();//销毁echarts
            console.log(this);
            $("#main").html("<div class=\"myMinus\"><div id=\"ck\" style=\";\" > 测试窗口 <a onclick=\"magnify()\" id=\"magnify\"><img src=\"../static/images/magnify.png\" alt=\"口\" style=\"width: 21px;height: 21px ;\"></a></div></div>");

          }
        },
        myDispose:{
          show:true,
          title:"擦除",
          icon:'image://../static/images/fork.png',
          onclick:function(){
            // myChart.dispose();//销毁echarts
            popup.close();//销毁echarts
            popup=null;
          }
        }


      }
    },
    //  dataZoom:[
    //   //{
    //   //     type: 'inside',
    //   //     //show: true,
    //   //     xAxisIndex: [0],
    //   //     start: 15,
    //   //     end: 16
    //   // },
    //   // {
    //   //     type: 'slider',
    //   //     show: true,
    //   //     xAxisIndex: [0],
    //   //     start: 15,
    //   //     end: 16
    //   // }
    // ],
    legend: {
      data:['水深','水位','流速'],
      textStyle: {

        fontSize: 21

      }
    },
    xAxis: {

      data: [],
      //name:"时间",
      axisLine: {//x轴线的颜色以及宽度
        show: true,

        lineStyle: {
          color: "rgba(0,0,0,1)",
          width: 1,
          type: "solid"
        }
      },

      axisLabel: {//x轴文字的配置
        show: true,
        textStyle: {
          color: "rgba(0,0,0,1)",
          fontSize: 14
        }
      },
      splitLine: {//分割线配置
        show:false,
        lineStyle: {
          color: "rgba(219,225,255,1)",
        }
      }

    },
    yAxis: [
      {
        type: 'value',
        name:"水深",
        min:0,
        max:50,

        position:"left",
        //data:[50,40,30,20,10,0],
        // nameTextStyle:{//y轴上方单位的颜色
        //     color:'#151515'
        // },
        axisTick: {
          show:true,
        },
        axisLine: {
          show: true,
          lineStyle: {
            color: colors[0]
          }
        },
        splitLine: {//分割线配置
          show:false,
          lineStyle: {
            color: "rgba(219,225,255,1)",
          }
        },
        axisLabel: {
          formatter: '{value}m',
          textStyle:{
            fontsize:9,
            //color:colors[0]
          }
        }
      },
      {
        type: 'value',
        name:"水位",
        position:"right",
        max:14,
        // nameTextStyle:{//y轴上方单位的颜色
        //     color:'#151515'
        // },
        axisTick: {
          show:true,
        },
        axisLine: {
          show: true,
          lineStyle: {
            color: colors[1]
          }
        },

        splitLine: {//分割线配置
          show:false,
          lineStyle: {
            color: colors[1],
          }
        },
        axisLabel: {
          formatter: '{value}m',
          textStyle:{
            fontsize:9,
            color:colors[1]
          }
        }

      },
      {
        type: 'value',
        name:"流速",
        position:"right",
        max:14,
        // nameTextStyle:{//y轴上方单位的颜色
        //     color:'#151515'
        // },
        axisTick: {
          show:true,
        },
        axisLine: {
          show: true,
          lineStyle: {
            color: colors[2]
          }
        },
        offset:40,
        splitLine: {//分割线配置
          show:false,
          lineStyle: {
            color: colors[2],
          }
        },
        axisLabel: {
          formatter: '{value}m/s',
          textStyle:{
            fontsize:9,
            color:colors[2]
          }
        }
      }
    ],
    dataZoom:[
      {
        type: 'slider',
        show: true,
        xAxisIndex: [0],
        start: 1,
        end: 35
      },
    ],

    series: [
      {
        name:"水深",
        type:"bar",
        yAxisIndex: 0,
        data:[]
      },
      {
        name:"水位",
        type:"bar",
        yAxisIndex: 1,
        data:[]
      },
      {
        name:"流速",
        type:"line",
        yAxisIndex: 2,
        data:[]
      }
    ]
  };
  let option = {
    title:{
      text:"输出数据"
    },
    backgroundColor:'rgba(128, 128, 128, 0.5)',
    textStyle: {
      color:'rgba(0, 0, 0, 1)',
      // fontSize:3000,
      fontWeight:'bolder',
      fontFamily:'Arial',
    },


    toolbox: {
      show: true,
      showTitle:true,
      // shadowColor: 'rgba(255,255,255,1)' ,
      // textStyle:{
      //        color:'rgba(0,0,255,1)'
      // },
      iconStyle:{
        // color:'rgba(255,0,0,1)',
        borderColor:'rgba(0,0,0,1)',
      },
      feature: {
        saveAsImage: {},
        dataView: {
          show: true,
          title: '表格数据',
          lang: ['表格数据：', '关闭', '导出Excel'],    // 按钮
          //icon:"image://image/excel.png",             // ‘数据视图’按钮自定义img
          contentToOption: function (opt) {

            $("#main").table2excel({           // 下载jquery.table2excel.js，引入，$("#main")是Echarts容器
              exclude: ".noExl", //过滤位置的 css 类名， 有class = “noExl” 的行不被导出
              filename: "输出结果数据.xls", // 文件名称
              name: "Excel Document Name.xls",
              exclude_img: true,// 是否导出图片 false导出
              exclude_links: true,//是否导出链接 false 导出
              exclude_inputs: true// 是否导出输入框的值 true导出
            });
          },
          //数据视图展示为table
          optionToContent: function (opt) {
            var axisData = opt.xAxis[0].data;
            var series = opt.series;
            // tableName = opt.title[0].text;
            // console.log(opt);
            // console.log(series);
            let num = 1 ;
            var table = '<table style="width:100%;text-align:center"><tbody><tr>'
              + '<td>单元</td>';

            for(var k in series){
              table+='<td>' + series[k].name + '</td>'

            }

            table  += '</tr>';
            for (var i = 0, l = axisData.length; i < l; i++) {
              table += '<tr>'
                + '<td>' + axisData[i]  +'</td>';
              for(var k in series){
                table+='<td>' + series[k].data[i] + '</td>';
              }

              table += '</tr>';
            }
            table += '</tbody></table>';
            return table;

          }
        },



        myMinus:{
          show:true,
          title:'缩小',
          icon:'image://../static/images/minus.png',
          onclick:function(){

            console.log("minus");
            // $("#main").children().width(50).height(50);
            // $("#main").children().children().width(50).height(50);
            //$("#main").width(100).height(30);
            myChart.dispose();//销毁echarts
            console.log(this);
            $("#main").html("<div class=\"myMinus\"><div id=\"ck\" style=\";\" > 测试窗口 <a onclick=\"magnify()\" id=\"magnify\"><img src=\"../static/images/magnify.png\" alt=\"口\" style=\"width: 21px;height: 21px ;\"></a></div></div>");

          }
        },
        myDispose:{
          show:true,
          title:"擦除",
          icon:'image://../static/images/fork.png',
          onclick:function(){
            popup.close();//销毁echarts
            popup=null;
          }
        }


      }
    },

    legend: {
      data:['数据'],
      textStyle: {

        fontSize: 21

      }
    },
    xAxis: {

      data: [],
      //name:"时间",
      axisLine: {//x轴线的颜色以及宽度
        show: true,

        lineStyle: {
          color: "rgba(0,0,0,1)",
          width: 1,
          type: "solid"
        }
      },

      axisLabel: {//x轴文字的配置
        show: true,
        textStyle: {
          color: "rgba(0,0,0,1)",
          //fontSize: 14
        }
      },


    },
    yAxis: {

    },
    // yAxis: [
    //     {
    //         type: 'value',
    //         name:"水深",
    //         min:0,
    //         max:50,
    //
    //         position:"left",
    //         //data:[50,40,30,20,10,0],
    //         // nameTextStyle:{//y轴上方单位的颜色
    //         //     color:'#151515'
    //         // },
    //         axisTick: {
    //             show:true,
    //         },
    //         axisLine: {
    //             show: true,
    //             lineStyle: {
    //                 color: colors[0]
    //             }
    //         },
    //         splitLine: {//分割线配置
    //             show:false,
    //             lineStyle: {
    //                 color: "rgba(219,225,255,1)",
    //             }
    //         },
    //         axisLabel: {
    //             formatter: '{value}m',
    //             textStyle:{
    //                 fontsize:9,
    //                 //color:colors[0]
    //             }
    //         }
    //     },
    //     {
    //         type: 'value',
    //         name:"水位",
    //         position:"right",
    //         max:14,
    //         // nameTextStyle:{//y轴上方单位的颜色
    //         //     color:'#151515'
    //         // },
    //         axisTick: {
    //             show:true,
    //         },
    //         axisLine: {
    //             show: true,
    //             lineStyle: {
    //                 color: colors[1]
    //             }
    //         },
    //
    //         splitLine: {//分割线配置
    //             show:false,
    //             lineStyle: {
    //                 color: colors[1],
    //             }
    //         },
    //         axisLabel: {
    //             formatter: '{value}m',
    //             textStyle:{
    //                 fontsize:9,
    //                 color:colors[1]
    //             }
    //         }
    //
    //     },
    //     {
    //         type: 'value',
    //         name:"流速",
    //         position:"right",
    //         max:14,
    //         // nameTextStyle:{//y轴上方单位的颜色
    //         //     color:'#151515'
    //         // },
    //         axisTick: {
    //             show:true,
    //         },
    //         axisLine: {
    //             show: true,
    //             lineStyle: {
    //                 color: colors[2]
    //             }
    //         },
    //         offset:40,
    //         splitLine: {//分割线配置
    //             show:false,
    //             lineStyle: {
    //                 color: colors[2],
    //             }
    //         },
    //         axisLabel: {
    //             formatter: '{value}m/s',
    //             textStyle:{
    //                 fontsize:9,
    //                 color:colors[2]
    //             }
    //         }
    //     }
    // ],

    series: [
      {
        name:"数据",
        type:"bar",
        // yAxisIndex: 0,
        data:[]
      }
    ]
  };







// let longitudeH = 118;
// let longitudeL = 110 ;
// let latitudeH = 38 ;
// let latitudeL = 30 ;
// Double longitudeX1 = 118.86426717449807;
// Double latitudeX1 = 34.771934788782254;
// Double longitudeX2 = 118.85150634464433 ;
// Double latitudeX2 = 34.713883113334944 ;
// Double longitudeX3 = 119.16374583477337;
// Double latitudeX3 = 34.64524710969765;
// Double X4lon =119.1765066646271;
// Double X4lat = 34.70329878514496;
  let Positions = [[118.86426717449807,34.771934788782254],
    [118.85150634464433,34.713883113334944],
    [119.16374583477337,34.64524710969765],
    [119.19556,34.604079008746346]
  ];


  $(document).ready(function () {
    $.get(
      {
        url: 'http://localhost:8081/line/getMaxInterval',
        success: function (data){
          console.log(data);
          for (let i = 1; i<=data ; i++){
            $('#TimeInterval').append( new Option('   '+i,i))
          }
        }
      }
    );
  });


// 创建弹窗对象的方法
  let Popup = function(info){
    //this表示当前对象，如果在全局作用范围内使用this，则指代当前页面对象window；
    // 如果在函数中使用this，则this指代什么是根据运行时此函数在什么对象上被调用。
    this.constructor(info);
  }
//在执行比较时，相等操作符(==)将尝试使数据类型相同，然后再继续。另一方面，标识操作符(===)要求这两种数据类型都是相同的，作为先决条件

  Popup.prototype.constructor = function(info){

    let _this = this;
    _this.viewer = info.viewer;//弹窗创建的viewer
    _this.geometry = info.geometry;//弹窗挂载的位置
    //_this.id ="popup_" +_this.__proto__.id++ ;
    _this.ctn = $("<div class='bx-popup-ctn' id =  '"+_this.id+"'>");//创建dom元素

    // console.log(_this.viewer.container);
    _this.ctn.append('<div id = "main" style="width:'+ info.width+'px;height:'+info.height+'px;"></div>');
    $(_this.viewer.container).append( _this.ctn);

    //_this.ctn.append('<div id = "main" style="width: 100px;height:100px;">');
    _this.myChart = echarts.init(document.getElementById('main'));


    // 使用刚指定的配置项和数据显示图表。
    _this.myChart.setOption(option);

    _this.render(_this.geometry);

    _this.eventListener = _this.viewer.clock.onTick.addEventListener(function(clock) {
      _this.render(_this.geometry);
    })
  }
// 实时刷新
  Popup.prototype.render = function(geometry){

    var _this = this;
    //SceneTransform 用于在与渲染相关的坐标系之间进行场景相关转换的功能
    //static方法 将WGS84坐标中的位置转换为窗口坐标。这通常用于放置与场景中的对象位于同一屏幕位置的HTML元素。
    var position = Cesium.SceneTransforms.wgs84ToWindowCoordinates(_this.viewer.scene,geometry)

    //HTMLElement.offsetWidth 是一个只读属性，返回一个元素的布局宽度。
    //console.log(_this.ctn);
    _this.ctn.css("left",position.x- _this.ctn.get(0).offsetWidth/2);//get(x)取得所有匹配的 DOM 元素集合。
    _this.ctn.css("top",position.y- _this.ctn.get(0).offsetHeight-10);
  }

// 关闭弹窗按钮
  Popup.prototype.close=function(){
    var _this = this;
    _this.ctn.remove();
    _this.myChart.dispose();
    _this.viewer.clock.onTick.removeEventListener( _this.eventListener );
  }



// 点击获取一个单元的元素
  function getSingleElementData(click){

    if(popup!=null){
      popup.close();
      popup = null;
    }
    var pick= new Cesium.Cartesian2(click.position.x,click.position.y);
    if(pick){
      // 获取点击位置坐标
      var cartesian = viewer.scene.globe.pick(viewer.camera.getPickRay(pick), viewer.scene);
      //通过camera中的getPickRay获取ray（射线），然后通过globel中的pick方法，获取世界坐标，如下面的地形坐标的获取；


      // var earthPosition  = viewer.camera.pickEllipsoid(click.position,viewer.scene.globe.ellipsoid);
      // var cartographic1 = Cesium.Cartographic.fromCartesian(earthPosition, viewer.scene.globe.ellipsoid, new Cesium.Cartographic());
      // var lat=Cesium.Math.toDegrees(cartographic1.latitude);
      // var lng=Cesium.Math.toDegrees(cartographic1.longitude);
      // var height=cartographic.height;
      // console.log("[Lng=>"+lng+",Lat=>"+lat+",H=>"+height+"]");



      if(cartesian){
        let cartographic = Cesium.Cartographic.fromCartesian(cartesian);
        let x=Cesium.Math.toDegrees(cartographic.longitude);
        let y=Cesium.Math.toDegrees(cartographic.latitude);
        console.log(x,y);


        let TimeInterval = $("#TimeInterval").val();
        let sendData={
          'positions':Positions.toString(),
          'x':x,
          'y':y,
          'TimeInterval':TimeInterval
        }
        console.log(sendData);
        $.ajax({
          type: "POST",
          url: 'http://localhost:8081/line/oneOutput',
          data:sendData,
          success:function(data){
            console.log(data);
            let dataX = [];
            let dataY = [];
            // data= JSON.parse(data);
            popup = new Popup({
              viewer:viewer,
              geometry:cartesian,
              width:400,
              height:400,
            })
            for (let item in data){
              if(data[item]!=null){
                dataX.push(item);
                dataY.push(data[item]);
              }
            }
            console.log(dataY);

            popup.myChart.setOption({
              xAxis: {
                data: dataX,

              },
              series: [{
                name: "数据" ,
                data:dataY
              }]
            });
            // 调用弹窗方法

            //设置鼠标移动效果
            let main =document.getElementById("main");


            main.onmousedown= function(event){

              fndown(event,main);
            }

          }
        });



      }
    }
  }

//用于存储坐标
  let positions=[];
  let redLine = {}
  let hasline = false;
//点击画线
  $("#writeLine").on('click',function(){

    if(hasline){

      handler.removeInputAction(Cesium.ScreenSpaceEventType.LEFT_CLICK);
      handler.removeInputAction(Cesium.ScreenSpaceEventType.MOUSE_MOVE);

    }

    handler.removeInputAction(Cesium.ScreenSpaceEventType.LEFT_CLICK);

    handler.setInputAction(function(click){

      if(hasline){

        redLine.endPoint = viewer.entities.add({
          name:"point",
          position:positions[positions.length-1],
          point:{
            pixelSize: 5,
            color: Cesium.Color.RED,
            outlineColor: Cesium.Color.WHITE,
            outlineWidth: 2,
          }
        })
        handler.removeInputAction(Cesium.ScreenSpaceEventType.LEFT_CLICK);
        handler.removeInputAction(Cesium.ScreenSpaceEventType.MOUSE_MOVE);
        //获取画线坐标，传到后端
        let TimeInterval = $("#TimeInterval").val();
        let myData = {
          'positions':Positions.toString(),
          'TimeInterval':TimeInterval,

          'startX':Cesium.Math.toDegrees(Cesium.Cartographic.fromCartesian(positions[0]).longitude),
          'startY':Cesium.Math.toDegrees(Cesium.Cartographic.fromCartesian(positions[0]).latitude),
          'endX':Cesium.Math.toDegrees(Cesium.Cartographic.fromCartesian(positions[1]).longitude),
          'endY':Cesium.Math.toDegrees(Cesium.Cartographic.fromCartesian(positions[1]).latitude),

        };

        console.log(myData);
        $.ajax({
          type: "POST",
          url: 'http://localhost:8081/line/data2',
          data:myData,
          success:function(data) {
            console.log(data);
            let element = [];//时间
            let bed = [];//水深
            let water = [];//水位
            let flow1 = [];//流速
            let start = 0;
            let end = 100;

            // if(10<length<=20){
            //     end = 50;
            // }

            // data = JSON.parse(data);
            for (let i in data) {

              element.push(data[i].element);//计算区域
              bed.push(data[i].bed);
              water.push(data[i].water);
              flow1.push(data[i].flow1);
            }
            let length = element.length;

            end = 800/length;
            if(end>100){
              end = 100 ;
            }

            console.log(bed);
            popup = new Popup({
              viewer:viewer,
              geometry:positions[1],
              width:600,
              height:500,

            })

            //设置鼠标移动效果
            let main =document.getElementById("main");


            main.onmousedown= function(event){

              fndown(event,main);
            }

            showElements.dataZoom[0].start=start;
            showElements.dataZoom[0].end=end;
            popup.myChart.clear()
            popup.myChart.setOption(showElements);
            popup.myChart.setOption({
              xAxis: {
                data: element,

              },

              series: [
                {
                  name: "水深",
                  data: bed
                },
                {
                  name: "水位",
                  data: water
                },
                {
                  name: "流速",
                  data: flow1
                }
              ],

            });
            console.log(popup.myChart)

          }
        });

        return ;//返回
      }
      let pick= new Cesium.Cartesian2(click.position.x,click.position.y);
      if(pick) {
        // 获取点击位置坐标
        var cartesian = viewer.scene.globe.pick(viewer.camera.getPickRay(pick), viewer.scene);
        //通过camera中的getPickRay获取ray（射线），然后通过globel中的pick方法，获取世界坐标，如下面的地形坐标的获取；


        // var earthPosition  = viewer.camera.pickEllipsoid(click.position,viewer.scene.globe.ellipsoid);
        // var cartographic1 = Cesium.Cartographic.fromCartesian(earthPosition, viewer.scene.globe.ellipsoid, new Cesium.Cartographic());
        // var lat=Cesium.Math.toDegrees(cartographic1.latitude);
        // var lng=Cesium.Math.toDegrees(cartographic1.longitude);
        // var height=cartographic.height;
        // console.log("[Lng=>"+lng+",Lat=>"+lat+",H=>"+height+"]");


        if (cartesian&&Cesium.defined(cartesian)) {
          //将笛卡尔坐标转换为地理坐标
          let cartographic = Cesium.Cartographic.fromCartesian(cartesian);
          let x = Cesium.Math.toDegrees(cartographic.longitude);
          let y = Cesium.Math.toDegrees(cartographic.latitude);
          positions.push(cartesian);
          /*console.log(positions);*/
          //redLine=null;
          redLine.startPoint = viewer.entities.add({
            name:"point",
            position:positions[positions.length-1],
            point: {
              pixelSize: 5,
              color: Cesium.Color.RED,
              outlineColor: Cesium.Color.WHITE,
              outlineWidth: 2,
            }
          })
          redLine.line=viewer.entities.add({
            name:"RLine",
            positions: [],
            polyline:{
              show: true,

              material: Cesium.Color.CHARTREUSE,
              width: 3,
              clampToGround: true,
            }



          });
          console.log(redLine);
          handler.setInputAction(function(moment) {

            if(positions.length>1){

              positions.pop();
            }
            let p= new Cesium.Cartesian2(moment.endPosition.x,moment.endPosition.y);
            //defined如果定义了对象，则返回true，否则返回false。
            if(p&&Cesium.defined(p)){
              let endPoint=viewer.scene.globe.pick(viewer.camera.getPickRay(p), viewer.scene);

              positions.push(endPoint);
              if(positions.length>1){
                // console.log(redLine);
                // redLine.line.polyline.positions=positions;
                //折线的动态绘制通过CallbackProperty属性绑定positions属性实现。
                // CallbackProperty是一个类，其值由回调函数延迟计算。也就是说它在不断地自我调用，每当期返回的对象有改变，就抛出改编后的值。
                // 利用这种特性，我们就可以在定义材质时，用CallbackProperty生成动态的对象赋值给材质参数，就可以得到动态材质的效果
                redLine.line.polyline.positions = new Cesium.CallbackProperty(function(){
                  return positions;
                }, false);
                hasline=true;
              }

              //console.log(positions[1],positions[0]);

            }


          },Cesium.ScreenSpaceEventType.MOUSE_MOVE);
        }

      }

    },Cesium.ScreenSpaceEventType.LEFT_CLICK);
  });

// function  clearLine() {
//     // if(hasline){
//
//     // }
//     // viewer.entities.remove(redLine.line);
//     viewer.entities.remove(redLine.endPoint);
//     viewer.entities.remove(redLine.startPoint);
//     positions=[];
//     hasline=false;
//     //设置echarts
//     popup.close();//销毁echarts
//     popup=null;
//     // 对handler 进行操作,设置单机显示一个单元的数据
//     handler.setInputAction(getSingleElementData, Cesium.ScreenSpaceEventType.LEFT_CLICK);
// }
  function clearLine(){

    viewer.entities.remove(redLine.endPoint);
    viewer.entities.remove(redLine.startPoint);
    positions=[];
    hasline=false;
    //设置echarts
    if(popup!=null){
      popup.close();//销毁echarts
      popup=null;
    }

    // 对handler 进行操作,设置单机显示一个单元的数据
    handler.setInputAction(getSingleElementData, Cesium.ScreenSpaceEventType.LEFT_CLICK);

  }
//清除
  $("#clearLine").on("click",clearLine);

// let datasource = Cesium.GeoJsonDataSource.load("../static/ploygon2.geojson",
//     {
//         stroke: Cesium.Color.WHITE,
//         fill: Cesium.Color.BLUE.withAlpha(0.1), //注意：颜色必须大写，即不能为blue
//         strokeWidth: 5
//     });
// var dataSources=viewer.dataSources.add(datasource);
  $("#forkImg").on('click',function () {

    clearLine();
    $("#myButtons").css('display','none');
    // 对handler 进行操作,设置单机显示一个单元的数据
    handler.removeInputAction( Cesium.ScreenSpaceEventType.LEFT_CLICK);
    for (let i in entities) {
      let entity = entities[i];
      entity.billboard=undefined;
      entity.polygon.outlineColor=Cesium.Color.BLUE.withAlpha(0.0);
      entity.polygon.fill= Cesium.Color.BLUE.withAlpha(0.0);

    }
  })
//鼠标拖动效果
// window.onload= function(){
//     //loadEcharts("福山");
//
//     let main =document.getElementById("main");
//     // console.log(main);
//     //var  body= document.body;
//
//     main.onmousedown= function(event){
//
//         fndown(event,main);
//     }
// }
  function fndown(event,main){

    event = event||window.event;
    var disX = event.clientX - main.offsetLeft,//记录鼠标相对于矩形的位置
      disY =event.clientY - main.offsetTop;
    document.onmousemove = function(event){
      event = event||window.event;
      fnMove(event,disX,disY,main)
    };
    main.onmouseup = function(){//松开鼠标后无法拖动
      document.onmousemove = null;
      main.onmouseup = null ;
    }


  }
  function fnMove(event,disX,disY,main){//鼠标移动事件
    var l = event.clientX - disX,//根据鼠标的位置改变，来改变矩形相对于body的位置
      t = event.clientY - disY;
    main.style.left=l+'px';
    main.style.top=t+'px';
  }
}
