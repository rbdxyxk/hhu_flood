export default function FlyInAdvance(viewer) {
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
      url: "http://localhost:8083/Cesium_Apps/findRoadServlet",
      type:"post",
      data:{"curPage":currentPage},
      dataType:"json",//返回类型为json类型
      success : function (data){
        console.log('返回data');
        debugger;

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
            arr.push(parseInt(road_data[j]));
            arr.push(road_data[j+1]);
            arr.push(road_data[j+2]);
            arr.push(road_data[j+3]);

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
              '                        <button type="button" class="btn btn-primary"' +
              'value="'+
              i +
              '" onclick="getValue(this);">选择该线路</button>' +
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
    viewer.dataSources.add(Cesium.CzmlDataSource.load(czml)).then(function (ds) {
      viewer.trackedEntity = ds.entities.getById('entityFly_Advance');
    });

    //获取飞机模型实体
    entitie = viewer.entities.getById('entityFly');

    if (entitie != null){
      //添加时钟监听事件
      viewer.clock.onTick.addEventListener(myListener_Advance);
    }

  }

  /**
   * 退出飞行
   */
  function endFly_Advance() {
    //暂停时钟
    this.viewer.clock.shouldAnimate = false;
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
