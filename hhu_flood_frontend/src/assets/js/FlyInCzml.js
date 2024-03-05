export default function FlyInCzml(viewer,handler){
   $('#controlFly').on('click',controlFly);
    $('#controlFly1').on('click',controlFly);
    $("#startFly").on('click',startFly);
    $("#endFlyAdvance").on('click',endFly_Advance);
    $("#endFlyCzml").on('click',endFly_Czml);
    $("#upSpeed").on('click',upSpeed);
    $("#downSpeed").on('click',downSpeed);
    $("#changePathLine").on('click',changePathLine);
    $("#upModelScale").on("click",upModelScale);
    $("#downModelScale").on("click",downModelScale);
    $("#firstSee").on('click',firstSee);

  $("#thirdSee").on('click',thirdSee);
  $("#btn_save").on('click',saveRoad);

  //绑定三维巡河-线路设置的点击事件
  $('#FlyInCzml').click(function () {
    // 显示操作界面
    $('#FlyMenu_1').show();
    //隐藏另一个界面
    $('#FlyMenu_2').hide();

    //退出沿城巡河
    endFly_Advance();

    //回到初始页的显示位置
    viewer.camera.flyTo({
      destination: Cesium.Cartesian3.fromDegrees(118.9809, 34.8503, 200000.0),
      duration: 0,
    });

    //清除其他事件

    //清除其他实体

  });



//定义一个空字符串存储坐标位置
  var str_data = '';
//存储分割后的字符串
  var str_array = [];
//用于保存线路数据的字符串
  let str_Advance = [];

//时间帧
  var timeInterval = 0;

//拼接实体id的计数
  let times = 0;

//开始时间停止时间
  let start;
  let stop;

  /**
   * 结束记录位置
   */
  function endMouse() {

    console.log("进入endMouse()~~~~~~~~")

    handler.removeInputAction(Cesium.ScreenSpaceEventType.LEFT_CLICK);
    handler.removeInputAction(Cesium.ScreenSpaceEventType.MOUSE_MOVE);

    //清空mousemove事件
    window.onmousemove = null;
  }


  /** 程序入口
   * 开始鼠标监听记录位置
   */
  $('#btn_get').click(function () {
    //设置鼠标单击事件，使鼠标移动事件开始记录
    //次数增加
    times++;
    //清空数据
    str_data = '';
    str_Advance = [];
    timeInterval = 0;

    //获取飞行高度
    let flyHeight = $('#flyHeight').val() == 0 ? 2500 : $('#flyHeight').val();

    //键盘监听事件
    window.onkeydown = endMouse;

    //鼠标单击事件
    handler.setInputAction(function (click) {

      //鼠标移动事件
      handler.setInputAction(function (movement) {
        //获取鼠标位置，camera.pickEllipsoid()返回一个cartesian类型位置
        let my_ellipsoid = viewer.scene.globe.ellipsoid;
        let click_position = viewer.scene.camera.pickEllipsoid(movement.endPosition, my_ellipsoid); //位置数据转换只地理数据类型
        let carto_position = my_ellipsoid.cartesianToCartographic(click_position); //cesium函数转换至地理数据类型的经纬度

        console.log("转换前：" + carto_position);

        //将地图坐标（弧度）转为十进制的度数
        var longitude_x = Cesium.Math.toDegrees(carto_position.longitude).toFixed(4);
        var longitude_y = Cesium.Math.toDegrees(carto_position.latitude).toFixed(4);
        var longitude_z = (viewer.camera.positionCartographic.height / 1000).toFixed(2);

        console.log(longitude_x);
        console.log(longitude_y);
        console.log(longitude_z);
        //存储设置线路所用数据
        // 将数据存储到字符串中，经度、纬度、高度、时间戳
        str_data += longitude_x + ',';
        str_data += longitude_y + ',';
        str_data += flyHeight + ',';
        str_data += timeInterval + ',';

        //存储保存线路所用数据
        // 将数据存储到数组中，时间戳、经度、纬度、高度
        //时间戳为数字，经纬度高度为字符串
        str_Advance.push(timeInterval);
        str_Advance.push(longitude_x);
        str_Advance.push(longitude_y);
        str_Advance.push(flyHeight);

        //时间戳自增
        timeInterval += 20;

        //记录鼠标位置。显示轨迹
        var nDiv = document.createElement('div')  //创建新的div
        nDiv.className = "MouseTrace"
        var e = event || window.event   //获取事件对象
        //设置div的样式(红色小圆点)和位置(鼠标当前位置)
        nDiv.style.cssText = "position:absolute; width:5px; height:5px; background-color:red; border-radius:50%"
        nDiv.style.left = e.pageX + "px"
        nDiv.style.top = e.pageY + "px"
        //把创建好的div添加到body里面
        document.body.appendChild(nDiv)

      }, Cesium.ScreenSpaceEventType.MOUSE_MOVE); //ScreenSpaceEventType
    }, Cesium.ScreenSpaceEventType.LEFT_CLICK); //ScreenSpaceEventType

  });


  /**
   * 飞行的入口.计算数据，开始飞行
   */

  $('#btn_remove').click(function () {

    //移除鼠标位置记录的轨迹
    $("div.MouseTrace").remove();


    if ("" == str_data) {
      alert("请先绘制路线");
      console.log("没有绘制路线就点击开始飞行");
    } else {
      //移除鼠标监听事件。
      handler.removeInputAction(Cesium.ScreenSpaceEventType.LEFT_CLICK);
      handler.removeInputAction(Cesium.ScreenSpaceEventType.LEFT_DOUBLE_CLICK);
      //字符串分隔成数组，并处理其格式。
      console.log('准备strToArray()~~~');
      //转换数据格式。准备飞行
      strToArray();
    }
  });

//该数组存放处理后的数据
  let data = [];

//将字符串分隔成数组，并处理其格式。
  function strToArray() {
    console.log('strToArray()~~~');

    //清空已有数据
    str_array = [];
    //分隔字符串
    str_array = str_data.split(',');
    console.log('str_array = ' + str_array);

    //清空数据
    data = [];
    //临时数组
    var data_obj = [];
    //遍历数组，生成对象，并对对象的属性进行赋值。
    for (let i = 0, j = 0; i < str_array.length - 1; i += 4, j++) {
      var obj = {};
      obj.longitude = Number(str_array[i]);
      //
      obj.dimension = Number(str_array[i + 1]);
      obj.height = Number(str_array[i + 2]);
      obj.time = Number(str_array[i + 3]);
      data_obj[j] = obj;
    }
    data[0] = data_obj;
    flyInCzml();
  }

//路线信息。全局变量方便保存线路
  let property;
//获取div以显示经纬度高度
  var longitude_show;
  var latitude_show;
  var altitude_show;
//时钟监听事件
  let myListener_Czml;
//飞机实体
  let entityFly;

  function flyInCzml() {
    console.log('fly()~ start~');
    viewer.scene.globe.enableLighting = true;
    // 起始时间
    // start = Cesium.JulianDate.fromDate(new Date(2017, 7, 10,12,0,0));
    start = Cesium.JulianDate.fromDate(new Date());

    // 结束时间
    stop = Cesium.JulianDate.addSeconds(start, 3600 * 7, new Cesium.JulianDate());

    // 设置始时钟始时间
    viewer.clock.startTime = start.clone();
    // 设置时钟当前时间
    viewer.clock.currentTime = start.clone();
    // 设置始终停止时间
    viewer.clock.stopTime = stop.clone();
    // 时间速率，数字越大时间过的越快
    viewer.clock.multiplier = 3;
    // // 时间轴
    // viewer.timeline.zoomTo(start, stop);
    // 循环执行,即为2，到达终止时间，重新从起点时间开始
    // viewer.clock.clockRange = Cesium.ClockRange.LOOP_STOP;
    // viewer.clock.clockRange = Cesium.ClockRange.CLAMPED;;

    for (let j = 0; j < data.length; j++) {
      //计算路线
      property = computeFlight(data[j]);
      console.log("property" + property.toString());

      //添加实体属性
      entityFly = viewer.entities.add({
        //实体id
        id: 'entityFly' + times,
        //可用性
        availability: new Cesium.TimeIntervalCollection([
          new Cesium.TimeInterval({
            start: start,
            stop: stop,
          }),
        ]),
        //位置
        position: property,
        //指定实体方向的属性
        orientation: new Cesium.VelocityOrientationProperty(property),
        //路径
        path: {
          show: true,
          resolution: 1,
          width: 5,
          material: new Cesium.PolylineGlowMaterialProperty({
            glowPower: 0.8,
            color: Cesium.Color.BROWN,
          }),
        },
        //模型
        model: {
          scale: 0.05,
          // uri: times % 2 == 0 ? '../static/models/CesiumAir/Cesium_Air.glb' : '../static/models/CesiumDrone/CesiumDrone.glb',
          uri: 'static/models/CesiumAir/Cesium_Air.glb',
          show: true,
          runAnimations: true,
        },
      });

      //追踪的轨迹为实体路径
      viewer.trackedEntity = entityFly;

      //设定视角高度
      // var seeHeight = $('#seeHeight').val() == 0 ? 10 : $('#seeHeight').val();
      // var seeHeight = 10;
      // entityFly.viewFrom = new Cesium.Cartesian3(0,0,seeHeight);

      // 直接开始运行
      viewer.clock.shouldAnimate = true;

      //经纬度实时显示
      longitude_show = document.getElementById('longitude_show');
      latitude_show = document.getElementById('latitude_show');
      altitude_show = document.getElementById('altitude_show');


      /**
       *  时钟监听事件
       * @param clock
       */
      // if (entityFly != null) {
      //     //添加时钟监听事件
      //     viewer.clock.onTick.addEventListener(myListener_Czml = function (clock) {
      //         //获取当前时间
      //         var curtime = viewer.clock.currentTime;
      //         //笛卡尔坐标
      //         // var pos = entitie.position.getValue(curtime, null); //返回类型为Cartesian3
      //         // var pos = entitie.position.getValue(new Date(), null); //返回类型为Cartesian3
      //         // var pos = entityFly.position.getValue(new Date(), null); //返回类型为Cartesian3
      //         var pos = entityFly.position.getValue(curtime, null); //返回类型为Cartesian3
      //         var cartographic = Cesium.Ellipsoid.WGS84.cartesianToCartographic(pos);
      //         //经度
      //         var lon = Cesium.Math.toDegrees(cartographic.longitude);
      //         //纬度
      //         var lat = Cesium.Math.toDegrees(cartographic.latitude);
      //         //高度
      //         var height = cartographic.height; //不需要转换。其单位就是m
      //
      //         var info = lon + ',' + lat + ',' + height;
      //         console.log('获取entity坐标:' + info);
      //
      //         longitude_show.innerHTML = lon.toFixed(2);
      //         latitude_show.innerHTML = lat.toFixed(2);
      //         altitude_show.innerHTML = height.toFixed(2);
      //
      //         console.log("开始遍历地区")
      //
      //         var flag = 0;
      //         for (var i = 0; i < neighborhoodEntities.length; i++) {
      //             // console.log('i:' + i);
      //             //遍历每个地区实体
      //             var entity = neighborhoodEntities[i];
      //
      //             //重新转换为笛卡尔坐标系的点
      //             let pointPosition = Cesium.Cartesian3.fromDegrees(lon, lat, height);
      //             let endPosition = [pointPosition.x, pointPosition.y];
      //
      //             let shuzu = [];
      //             shuzu = Cesium.Cartesian3.packArray(
      //                 entity.polygon.hierarchy.getValue(Cesium.JulianDate.now()).positions,
      //                 shuzu
      //             );
      //
      //             let polygonPointsArray = [];
      //             var count = 0;
      //
      //             for (var j = 0; j < shuzu.length; j += 3) {
      //                 var obj = [];
      //                 var temp = 0;
      //                 obj[temp++] = shuzu[j];
      //                 obj[temp++] = shuzu[j + 1];
      //                 polygonPointsArray[count++] = obj;
      //             }
      //
      //             //根据是否在多边形内部
      //             if (isInPolygon(endPosition, polygonPointsArray)) {
      //                 console.log('当前位置：' + endPosition + ',经过：' + entity.name);
      //                 $('#alertDiv').show();
      //                 $('#alertText').text(entity.name);
      //                 flag = 1;
      //             }
      //
      //         }
      //         //如果当前位置没有遍历到任何一个水库。再隐藏提示信息
      //         if (flag == 0) {
      //             $('#alertDiv').hide();
      //
      //         }
      //
      //     });
      // }
    }
    console.log('fly()~ for end~');
  }

  /**
   *保存线路
   */
  function saveRoad() {

    console.log('str_Advance:' + str_Advance)

    if (str_Advance == null || str_Advance.length == 0){
      alert("请先绘制路线");
    }else {
      var info_name = $('#recipient-name').val();
      var info_data = str_Advance.toString();

      $.ajax({
        url: "http://localhost:8083/cruise/addRoad",
        data: {
          // uid : uid,   应该后端获取吧？
          "info_name": info_name,
          "info_data": info_data
        },
        dataType: "json",
        success: function (data) {

          console.log('success')
          console.log(data)
          console.log(data.status)
          console.log(data.msg)

          //隐藏模态框
          $('#myModal').modal('hide')
          alert(data.msg);

        },

      });
    }

  }

  /**
   * 退出飞行
   */
  function endFly_Czml() {

    //暂停时钟
    viewer.clock.shouldAnimate = false;
    // //获取实体
    let entitie_Fly = viewer.entities.getById('entityFly' + times);
    // 清除实体
    var remove = viewer.entities.remove(entitie_Fly);
    console.log('remove:' + remove);

    // viewer.entities.removeAll();

    //清除最终线路
    viewer.trackedEntity = undefined;

    //移除鼠标位置记录的轨迹
    $("div.MouseTrace").remove();

    // 清除clock监听事件
    //viewer.clock.onTick.removeEventListener(myListener_Czml);

    //去除位置提示信息
    $('#alertDiv').hide();
    //隐藏菜单
    $('#FlyMenu_1').hide();

    console.log('退出飞行Czml')
  }


  /**
   * 计算飞行
   * @param source 数据坐标
   * @returns {SampledPositionProperty|*}
   */
  function computeFlight(source) {
    console.log('computeFlight()~~~');
    // 取样位置 相当于一个集合
    let propertys = new Cesium.SampledPositionProperty();

    let temp = 0;
    for (let i = 0; i < source.length; i++, temp++) {
      let time = Cesium.JulianDate.addSeconds(start, source[i].time, new Cesium.JulianDate());
      let position = Cesium.Cartesian3.fromDegrees(source[i].longitude, source[i].dimension, source[i].height);
      // 添加位置，和时间对应
      propertys.addSample(time, position);
    }

    //固定飞行结束位置（但是时间久了仍可能会报错）
    for (let i = 0; i < source.length * 5; i++) {
      let time = Cesium.JulianDate.addSeconds(start, source[source.length - 1].time + (i + 1) * 20, new Cesium.JulianDate());
      let position = Cesium.Cartesian3.fromDegrees(source[source.length - 1].longitude, source[source.length - 1].dimension, source[source.length - 1].height);
      // 添加位置，和时间对应
      propertys.addSample(time, position);
    }

    // //循环飞行1次(会从结束位置飞到起始位置，不合理              )
    // for (let i = 0; i < source.length;i++){
    //   let time = Cesium.JulianDate.addSeconds(start, source[source.length-1].time + temp++ * 20, new Cesium.JulianDate());
    //   let position = Cesium.Cartesian3.fromDegrees(source[i].longitude, source[i].dimension, source[i].height);
    //   // 添加位置，和时间对应
    //   propertys.addSample(time, position);
    // }

    return propertys;
  }


  /**
   * 检测点是否在多边形内部，返回T/F
   * @param {*} checkPoint 模型坐标
   * @param {*} polygonPoints 判断的多边形
   * @returns T：在内部
   */
  function isInPolygon(checkPoint, polygonPoints) {
    var counter = 0;
    var i;
    var xinters;
    var p1, p2;
    var pointCount = polygonPoints.length;
    p1 = polygonPoints[0];

    for (i = 1; i <= pointCount; i++) {
      p2 = polygonPoints[i % pointCount];
      if (checkPoint[0] > Math.min(p1[0], p2[0]) && checkPoint[0] <= Math.max(p1[0], p2[0])) {
        if (checkPoint[1] <= Math.max(p1[1], p2[1])) {
          if (p1[0] != p2[0]) {
            xinters = ((checkPoint[0] - p1[0]) * (p2[1] - p1[1])) / (p2[0] - p1[0]) + p1[1];
            if (p1[1] == p2[1] || checkPoint[1] <= xinters) {
              counter++;
            }
          }
        }
      }
      p1 = p2;
    }
    if (counter % 2 == 0) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * 加速
   */
  function upSpeed() {
    console.log("当前速度：" + viewer.clock.multiplier)
    viewer.clock.multiplier += 10;
  }

  /**
   * 减速
   */
  function downSpeed() {
    console.log("当前速度：" + viewer.clock.multiplier)
    if (viewer.clock.multiplier >= 1) {
      viewer.clock.multiplier -= 1;
    }
  }


  /**
   *  路线的显示与隐藏
   */
  function changePathLine() {
    // entitie = viewer.entities.getById('entityFly');
    // entitie.path.show = !entitie.path.show;
    console.log('路线显示反转前' + entityFly.path.show);
    if (entityFly.path.show == true) {
      entityFly.path.show = false;
    } else if (entityFly.path.show == false) {
      entityFly.path.show = true;
    }
    console.log('路线显示反转后' + entityFly.path.show);
  }

  /**
   *  放大模型
   */
  function upModelScale() {
    // entitie = viewer.entities.getById('entityFly');
    entityFly.model.scale += 0.005;
    console.log('当前大小' + entityFly.model.scale);
  }

  /**
   *  缩小模型
   */
  function downModelScale() {
    // entitie = viewer.entities.getById('entityFly');
    entityFly.model.scale -= 0.005;
    console.log('当前大小' + entityFly.model.scale);
  }

  /**
   *  开始飞行
   */
  function startFly() {
  viewer.clock.shouldAnimate = true;

  }

  /**
   *  继续、暂停飞行
   */
  function controlFly() {
    if (viewer.clock.shouldAnimate == true) {
     viewer.clock.shouldAnimate = false;
    } else if (viewer.clock.shouldAnimate == false) {
      viewer.clock.shouldAnimate = true;
    }
  }

  /**
   *第一人称视角
   */
  function firstSee() {
    viewer.scene.screenSpaceCameraController.enableTilt = false;
    viewer.scene.screenSpaceCameraController.enableRotate = false;
    console.log('firstSee~')
  }

  /**
   *第三人称视角
   */
  function thirdSee() {
    viewer.scene.screenSpaceCameraController.enableTilt = true;
    viewer.scene.screenSpaceCameraController.enableRotate = true;
    console.log('thirdSee~')

  }


  // 原本FLyADvance
  $('#FlyInAdvance').click(function () {
    console.log('FlyInAdvance()~');

    //隐藏另一个界面
    $('#FlyMenu_1').hide();

    //放到加载数据时再显示，要不然会先出现界面后填充数据
    // //显示操作界面
    // $('#FlyMenu_2').show();

    //退出线路设置页面
    endFly_Czml();

    //加载线路信息
    load(1);



    //
    // //加载模型线路
    // flyInAdvance(1);

    //清除其他事件

  });

//存储road数据的数组
  let roadArr = [];
//用于存放中间数据
  let road_data = [];
  /**
   * 加载线路信息
   * @param currentPage 分页获取数据  当前页面是哪一页
   */
  function load(currentPage){
    console.log("load~~");
    console.log("currentPage" +currentPage)
    $.ajax({
      url: "http://localhost:8083/cruise/list",
      type:"post",
      data:{"curPage":currentPage},
      dataType:"json",//返回类型为json类型
      success : function (data){
        console.log('返回data');
        //debugger;

        //获取div'向里面填充代码
        var infoList = document.getElementById("roadInfo");
        //清空之前的代码
        infoList.innerHTML = '';

        var str_info = '';

        //清空之前的数据
        roadArr = [];
        road_data = [];


        //转换成数组形式存储.时间戳为数字，经纬度高度为字符串
        for (let k = 0;k<data.pageData.length;k++){
          //将字符串分隔成数组
          if (data.pageData[k].info_data != null){
            road_data = data.pageData[k].info_data.split(',');
          }

          //将每个线路的信息重新处理放到一个数组中
          var arr = [];
          for (let j = 0;j < road_data.length;j+=4){
            //分隔后的元素每个都为字符串。但是需要格式只有第一个为数字，后三个为字符串。
            arr.push(Number(road_data[j]));
            arr.push(Number(road_data[j+1]));
            arr.push(Number(road_data[j+2]));
            arr.push(Number(road_data[j+3]));

          }
          //将线路信息放到数组中，每个元素为一个数组，即一条线路
          roadArr.push(arr);
        }

        console.log('roadArr:'+roadArr)

        //显示操作界面
        $('#FlyMenu_2').show();

        //添加线路信息
        for (let i = 0; i < data.pageData.length; i++) {
          //若为有效数据
          if (data.pageData[i].expired != 1){
            str_info += '<div class="controlSpan" style="padding-top: 20px;height: 50px">' +
              '                    <div class="col-lg-2" style="text-align: center;width:113px;right: 260px;position: absolute;">' +
              '                        <span>线路' +
              data.pageData[i].id +
              // (i+1) +
              '</span>' +
              '                    </div>' +
              '                    <div class="col-lg-6" style="text-align: center;width:113px;right: 150px;position: absolute;">' +
              '                        <span>' +
              data.pageData[i].info_name +
              '</span>' +
              '                    </div>' +
              '                    <div class="col-lg-4" style="text-align: center;width:113px;right: 35px;position: absolute;">' +
              '                        <button type="button" id="getValue" class="getValue" class="btn btn-primary"' +
              'value="'+ i +
              '">选择该线路</button>' +
              '                    </div>' +
              '                </div>';
          }
        }

        //添加分页信息
        str_info += '<div class="col-lg-12">' +
          '                    <nav style="padding-left: 50px">' +
          '                        <ul class="pagination pagination-sm">';
        if(data.currentPage == 1){
          str_info += '                            <li class="disabled">';
        }else {
          str_info += '                            <li>';
        }
        str_info +=
          '                                <a href="#" onclick="javascript:getText(' +
          (data.currentPage - 1) +
          ');return false;" aria-label="Previous">' +
          '                                    <span aria-hidden="true">&laquo;</span>' +
          '                                </a>' +
          '                            </li>';

        //遍历页数
        for (let m = 0; m < data.totalPage; m++){
          //若是当前页，则显示选中状态
          if (m == (data.currentPage-1)){
            str_info += '                            <li class="active"><a href="#" onclick="javascript:getText(' +
              (m+1) +
              ');return false;">' +
              (m+1) +
              '</a></li>';
          }else {
            str_info += '                            <li><a href="#" onclick="javascript:getText(' +
              (m+1) +
              ');return false;">' +
              (m+1) +
              '</a></li>';
          }

        }

        if (data.currentPage == data.totalPage){
          str_info += '                            <li class="disabled">';
        }else {
          str_info += '                            <li>';
        }
        str_info +=
          '                                <a href="#" onclick="javascript:getText(' +
          (data.currentPage + 1) +
          ');return false;" aria-label="Next">' +
          '                                    <span aria-hidden="true">&raquo;</span>' +
          '                                </a>' +
          '                            </li>' +
          '                        </ul>' +
          '                    </nav>' +
          '                </div>';

        infoList.innerHTML = str_info;

        console.log('添加data')

        $(".getValue").on('click',getValue);

        //加载模型线路。默认加载第一条线路
        flyInAdvance(0);
      }
    });
  }

  /**
   * 获取当前按钮的value值，用于判断那条线路，并加载选择的线路
   * @param obj
   */
  function getValue(obj) {

    console.log(obj.value)
    //加载线路
    flyInAdvance(obj.value);
  }

  /**
   * 获取当前按钮的value值，用于判断那条线路，并加载选择的线路
   * @param currentPage
   */
  function getText(currentPage) {
    console.log("超链接中的数据"+currentPage);

    //加载分页
    load(currentPage);
    // //加载线路
    // flyInAdvance(obj.value);
  }

  /**
   * 选择路线进行飞行
   * @param routeID 线路的id   id = routeID + 1
   *              线路对应数据放入数组中索引会-1
   */
  function flyInAdvance(routeID) {

    //清除实体
    viewer.dataSources.removeAll();

    //定义数据
    let czml = [
      {
        id: 'document',
        name: 'CZML Path',
        version: '1.0',
        clock: {
          interval: '2021-07-08T10:00:00Z/2021-07-08T12:00:20Z',
          currentTime: '2021-07-08T10:00:00Z',
          multiplier: 3,
        },
      },
      {
        id: 'entityFly_Advance',
        availability: '2021-07-08T10:00:00Z/2021-07-08T12:00:20Z',
        path: {
          material: {
            polylineOutline: {
              color: {
                rgba: [255, 0, 255, 255],
              },
              outlineColor: {
                rgba: [0, 255, 255, 255],
              },
              outlineWidth: 5,
            },
          },
          width: 8,
          leadTime: 10,
          resolution: 1,
        },
        billboard: {
          image:
            'data:image/png;base64,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',
          scale: 1.5,
          eyeOffset: {
            cartesian: [0.0, 0.0, -10.0],
          },
        },
        position: {
          epoch: '2021-07-08T10:00:00Z',
          //以数组形式。存放顺序为时间、经度、纬度、高度。时间是数字，后三者为字符串。
          cartographicDegrees: roadArr[routeID],
        },
      },
    ];

    //添加czml
    viewer.dataSources.add(Cesium.CzmlDataSource.load(czml))
      .then(function (ds) {

      viewer.trackedEntity = ds.entities.getById('entityFly_Advance');
    });

    //获取飞机模型实体
    var entitie = viewer.entities.getById('entityFly');
    // // alert("a")
    // // if (entitie != null){
    // //   //alert("b")
    // //   //添加时钟监听事件
    // //   viewer.clock.onTick.addEventListener(myListener_Advance);
    // // }
    //viewer.clock.onTick.addEventListener(myListener_Advance);

  }

  /**
   * 退出飞行
   */
  function endFly_Advance() {
    //暂停时钟
    viewer.clock.shouldAnimate = false;
    //清除实体
    viewer.dataSources.removeAll();
    //去除位置提示信息
    $('#alertDiv').hide();
    //隐藏菜单
    $('#FlyMenu_2').hide();

  }

  /**
   *  时钟监听事件
   * @param clock
   */
  let myListener_Advance = function (clock) {
    //获取当前时间
    var curtime = viewer.clock.currentTime;
    //笛卡尔坐标
    var pos = entitie.position.getValue(curtime, null); //返回类型为Cartesian3
    var cartographic = Cesium.Ellipsoid.WGS84.cartesianToCartographic(pos);
    //经度
    var lon = Cesium.Math.toDegrees(cartographic.longitude);
    //纬度
    var lat = Cesium.Math.toDegrees(cartographic.latitude);
    //高度
    var height = cartographic.height; //不需要转换。其单位就是m

    var info = lon + ',' + lat + ',' + height;
    console.log('获取entity坐标:' + info);

    longitude_show.innerHTML = lon.toFixed(2);
    latitude_show.innerHTML = lat.toFixed(2);
    altitude_show.innerHTML = height.toFixed(2);

    //判断当前经过什么地区
    if (lon > 118 && lon < 119 && lat > 33 && lat < 35) {
      // console.log('我來了我來了我來了！！！');
      $('#alertDiv').show();
    } else {
      $('#alertDiv').hide();
    }
  }

}
