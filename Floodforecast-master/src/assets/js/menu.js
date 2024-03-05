import Cesium from "./Cesium-vendor";

export default function menu(viewer) {
// $(function () {
//   // do something
// });
// 功能
  let isclick = 0;

  $('#btn-minus').on('click', changeColor);
  // menuMinus;
  $('#menuMinus').on('click', function () {
    $('#sideMenu').css('display', 'none');
    $('#sideBar').css('display', 'block');
  }); //菜单折叠
  $('#spredBtn').on('click', function () {
    //菜单展开
    $('#sideBar').css('display', 'none');
    $('#sideMenu').css('display', 'block');
  });

  $('#magn').click(function () {
    // zoomIn(amount) amount Number    optional The amount to move.
    // 缩放距离amount，单位：米，沿摄像机的视角方向。
    // 缩放 镜头到视角方向地面 的直线距离的 0.2倍。
    viewer.camera.zoomIn((viewer.camera.positionCartographic.height / Math.abs(Math.sin(viewer.camera.pitch))) * 0.2);
  });
  $('#min').click(function () {
    viewer.camera.zoomOut((viewer.camera.positionCartographic.height / Math.abs(Math.sin(viewer.camera.pitch))) * 0.2);
  });

  $('#ju').click(function () {
    let handler = new Cesium.ScreenSpaceEventHandler(viewer.scene._imageryLayerCollection);
    let positions = [];
    let poly = null;
    let distance = 0;
    let cartesian = null;
    let floatingPoint;
    let labelPt;
    handler.setInputAction(function (movement) {
      let ray = viewer.camera.getPickRay(movement.endPosition);
      cartesian = viewer.scene.globe.pick(ray, viewer.scene);
      if (!Cesium.defined(cartesian))
        //跳出地球时异常
        return;
      if (positions.length >= 2) {
        if (!Cesium.defined(poly)) {
          poly = new PolyLinePrimitive(positions);
        } else {
          positions.pop();
          positions.push(cartesian);
        }
      }
    }, Cesium.ScreenSpaceEventType.MOUSE_MOVE);

    handler.setInputAction(function (movement) {
      let ray = viewer.camera.getPickRay(movement.position);
      cartesian = viewer.scene.globe.pick(ray, viewer.scene);
      if (!Cesium.defined(cartesian))
        //跳出地球时异常
        return;
      if (positions.length == 0) {
        positions.push(cartesian.clone());
      }
      positions.push(cartesian);
      //记录鼠标单击时的节点位置，异步计算贴地距离
      labelPt = positions[positions.length - 1];
      if (positions.length > 2) {
        getSpaceDistance(positions);
      } else if (positions.length == 2) {
        //在三维场景中添加Label
        floatingPoint = viewer.entities.add({
          name: '空间距离',
          position: labelPt,
          point: {
            pixelSize: 5,
            color: Cesium.Color.RED,
            outlineColor: Cesium.Color.WHITE,
            outlineWidth: 2,
          },
        });
      }
    }, Cesium.ScreenSpaceEventType.LEFT_CLICK);

    handler.setInputAction(function (movement) {
      handler.destroy(); //关闭事件句柄
      handler = undefined;
      positions.pop(); //最后一个点无效
      if (positions.length == 1) viewer.entities.remove(floatingPoint);
    }, Cesium.ScreenSpaceEventType.RIGHT_CLICK);

    let PolyLinePrimitive = (function () {
      function _(positions) {
        this.options = {
          name: '直线',
          polyline: {
            show: true,
            positions: [],
            material: Cesium.Color.CHARTREUSE,
            width: 5,
            clampToGround: true,
          },
        };
        this.positions = positions;
        this._init();
      }

      _.prototype._init = function () {
        let _self = this;
        let _update = function () {
          return _self.positions;
        };
        //实时更新polyline.positions
        this.options.polyline.positions = new Cesium.CallbackProperty(_update, false);
        let addedEntity = viewer.entities.add(this.options);
      };

      return _;
    })();
    //空间两点距离计算函数
    function getSpaceDistance(positions) {
      // console.log(22);
      //只计算最后一截，与前面累加
      //因move和鼠标左击事件，最后两个点坐标重复
      let i = positions.length - 3;
      let point1cartographic = Cesium.Cartographic.fromCartesian(positions[i]);
      let point2cartographic = Cesium.Cartographic.fromCartesian(positions[i + 1]);
      getTerrainDistance(point1cartographic, point2cartographic);
    }

    function getTerrainDistance(point1cartographic, point2cartographic) {
      let geodesic = new Cesium.EllipsoidGeodesic();
      geodesic.setEndPoints(point1cartographic, point2cartographic);
      let s = geodesic.surfaceDistance;
      let cartoPts = [point1cartographic];
      for (let jj = 1000; jj < s; jj += 1000) {
        //分段采样计算距离
        let cartoPt = geodesic.interpolateUsingSurfaceDistance(jj);
        cartoPts.push(cartoPt);
      }
      cartoPts.push(point2cartographic);
      //返回两点之间的距离
      let promise = Cesium.sampleTerrain(viewer.terrainProvider, 8, cartoPts);
      Cesium.when(promise, function (updatedPositions) {
        for (let jj = 0; jj < updatedPositions.length - 1; jj++) {
          let geoD = new Cesium.EllipsoidGeodesic();
          geoD.setEndPoints(updatedPositions[jj], updatedPositions[jj + 1]);
          let innerS = geoD.surfaceDistance;
          innerS = Math.sqrt(
            Math.pow(innerS, 2) + Math.pow(updatedPositions[jj + 1].height - updatedPositions[jj].height, 2)
          );
          distance += innerS;
        }
        //在三维场景中添加Label
        let lon1 = viewer.scene.globe.ellipsoid.cartesianToCartographic(labelPt).longitude;
        let lat1 = viewer.scene.globe.ellipsoid.cartesianToCartographic(labelPt).latitude;
        let lonLat = '(' + Cesium.Math.toDegrees(lon1).toFixed(2) + ',' + Cesium.Math.toDegrees(lat1).toFixed(2) + ')';
        let textDisance = distance.toFixed(2) + '米';
        if (distance > 10000) textDisance = (distance / 1000.0).toFixed(2) + '千米';
        floatingPoint = viewer.entities.add({
          name: '贴地距离',
          position: labelPt,
          point: {
            pixelSize: 5,
            color: Cesium.Color.RED,
            outlineColor: Cesium.Color.WHITE,
            outlineWidth: 2,
          },
          label: {
            text: lonLat + textDisance,
            font: '18px sans-serif',
            fillColor: Cesium.Color.GOLD,
            style: Cesium.LabelStyle.FILL_AND_OUTLINE,
            outlineWidth: 2,
            verticalOrigin: Cesium.VerticalOrigin.BOTTOM,
            pixelOffset: new Cesium.Cartesian2(20, -20),
          },
        });
      });
    }
  });

  $('#mian').click(function () {
    // 鼠标事件
    let handler = new Cesium.ScreenSpaceEventHandler(viewer.scene._imageryLayerCollection);
    let positions = [];
    let tempPoints = [];
    let polygon = null;
    let cartesian = null;
    let floatingPoint; //浮动点
    handler.setInputAction(function (movement) {
      let ray = viewer.camera.getPickRay(movement.endPosition);
      cartesian = viewer.scene.globe.pick(ray, viewer.scene);
      positions.pop(); //移除最后一个
      positions.push(cartesian);
      if (positions.length >= 2) {
        let dynamicPositions = new Cesium.CallbackProperty(function () {
          return new Cesium.PolygonHierarchy(positions);
          return positions;
        }, false);
        polygon = PolygonPrimitive(dynamicPositions);
      }
    }, Cesium.ScreenSpaceEventType.MOUSE_MOVE);

    handler.setInputAction(function (movement) {
      let ray = viewer.camera.getPickRay(movement.position);
      cartesian = viewer.scene.globe.pick(ray, viewer.scene);
      if (positions.length == 0) {
        positions.push(cartesian.clone());
      }
      positions.push(cartesian);
      //在三维场景中添加点
      let cartographic = Cesium.Cartographic.fromCartesian(positions[positions.length - 1]);
      let longitudeString = Cesium.Math.toDegrees(cartographic.longitude);
      let latitudeString = Cesium.Math.toDegrees(cartographic.latitude);
      let heightString = cartographic.height;
      let labelText = '(' + longitudeString.toFixed(2) + ',' + latitudeString.toFixed(2) + ')';
      tempPoints.push({ lon: longitudeString, lat: latitudeString, hei: heightString });
      floatingPoint = viewer.entities.add({
        name: '多边形面积',
        position: positions[positions.length - 1],
        point: {
          pixelSize: 5,
          color: Cesium.Color.RED,
          outlineColor: Cesium.Color.WHITE,
          outlineWidth: 2,
          heightReference: Cesium.HeightReference.CLAMP_TO_GROUND,
        },
        label: {
          text: labelText,
          font: '18px sans-serif',
          fillColor: Cesium.Color.GOLD,
          style: Cesium.LabelStyle.FILL_AND_OUTLINE,
          outlineWidth: 2,
          verticalOrigin: Cesium.VerticalOrigin.BOTTOM,
          pixelOffset: new Cesium.Cartesian2(20, -20),
        },
      });
    }, Cesium.ScreenSpaceEventType.LEFT_CLICK);
    handler.setInputAction(function (movement) {
      handler.destroy();
      positions.pop();
      let textArea = getArea(tempPoints) + '平方公里';
      viewer.entities.add({
        name: '多边形面积',
        position: positions[positions.length - 1],
        label: {
          text: textArea,
          font: '18px sans-serif',
          fillColor: Cesium.Color.GOLD,
          style: Cesium.LabelStyle.FILL_AND_OUTLINE,
          outlineWidth: 2,
          verticalOrigin: Cesium.VerticalOrigin.BOTTOM,
          pixelOffset: new Cesium.Cartesian2(20, -40),
          heightReference: Cesium.HeightReference.CLAMP_TO_GROUND,
        },
      });
    }, Cesium.ScreenSpaceEventType.RIGHT_CLICK);
    let radiansPerDegree = Math.PI / 180.0; //角度转化为弧度(rad)
    let degreesPerRadian = 180.0 / Math.PI; //弧度转化为角度
    //计算多边形面积
    function getArea(points) {
      let res = 0;
      //拆分三角曲面
      for (let i = 0; i < points.length - 2; i++) {
        let j = (i + 1) % points.length;
        let k = (i + 2) % points.length;
        let totalAngle = Angle(points[i], points[j], points[k]);
        let dis_temp1 = distance(positions[i], positions[j]);
        let dis_temp2 = distance(positions[j], positions[k]);
        res += dis_temp1 * dis_temp2 * Math.abs(Math.sin(totalAngle));
      }
      return (res / 1000000.0).toFixed(4);
    }

    /*角度*/
    function Angle(p1, p2, p3) {
      let bearing21 = Bearing(p2, p1);
      let bearing23 = Bearing(p2, p3);
      let angle = bearing21 - bearing23;
      if (angle < 0) {
        angle += 360;
      }
      return angle;
    }
    /*方向*/
    function Bearing(from, to) {
      let lat1 = from.lat * radiansPerDegree;
      let lon1 = from.lon * radiansPerDegree;
      let lat2 = to.lat * radiansPerDegree;
      let lon2 = to.lon * radiansPerDegree;
      let angle = -Math.atan2(
        Math.sin(lon1 - lon2) * Math.cos(lat2),
        Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2)
      );
      if (angle < 0) {
        angle += Math.PI * 2.0;
      }
      angle = angle * degreesPerRadian; //角度
      return angle;
    }

    function PolygonPrimitive(positions) {
      polygon = viewer.entities.add({
        polygon: {
          hierarchy: positions,
          material: Cesium.Color.GREEN.withAlpha(0.1),
        },
      });
    }

    function distance(point1, point2) {
      let point1cartographic = Cesium.Cartographic.fromCartesian(point1);
      let point2cartographic = Cesium.Cartographic.fromCartesian(point2);
      /**根据经纬度计算出距离**/
      let geodesic = new Cesium.EllipsoidGeodesic();
      geodesic.setEndPoints(point1cartographic, point2cartographic);
      let s = geodesic.surfaceDistance;
      //返回两点之间的距离
      s = Math.sqrt(Math.pow(s, 2) + Math.pow(point2cartographic.height - point1cartographic.height, 2));
      return s;
    }
  });

  $('#kong').click(function () {
    viewer.entities.removeAll();
    viewer.dataSources.removeAll();
  });

//解决办法 2.监听Document.onfullscreenchange
  document.onfullscreenchange = function () {
    if (document.webkitFullscreenElement || document.mozFullScreenElement || document.msFullscreenElement) {
      $('#fullScreenButton').css('display', 'none');
      $('#exitFullScreenButton').show();
    } else {
      $('#exitFullScreenButton').css('display', 'none');
      $('#fullScreenButton').show();
    }
  };
  document.onkeydown = function (event) {
    //     alert("按键按下");
    let e = event || window.event ;
    //|| arguments.callee.caller.arguments[0]
    if (e && e.keyCode == 122) {
      // 按 F11
      e.preventDefault();
      e.stopPropagation();
      if (document.body.requestFullscreen) {
        //解决兼容性问题
        document.body.requestFullscreen();
        $('#fullScreenButton').css('display', 'none'); //按钮显示调整
        $('#exitFullScreenButton').show();
      } else if (document.body.webkitRequestFullScreen) {
        //兼容谷歌 /Edge
        document.body.webkitRequestFullScreen();
        $('#fullScreenButton').css('display', 'none');
        $('#exitFullScreenButton').show();
      } else if (document.body.mozRequestFullScreen) {
        //Firefox
        document.body.mozRequestFullScreen();
        $('#fullScreenButton').css('display', 'none');
        $('#exitFullScreenButton').show();
      } else if (document.body.msRequestFullscreen) {
        //ie
        document.body.msRequestFullscreen();
        $('#fullScreenButton').css('display', 'none');
        $('#exitFullScreenButton').show();
      }
    }
  };

  $('#fullScreenButton').on('click', function (event) {
    //全屏效果
    event.preventDefault();
    event.stopPropagation();
    // Trigger fullscreen

    if (document.body.requestFullscreen) {
      //解决兼容性问题
      document.body.requestFullscreen();
      // $("#fullScreenButton").css("display","none");//按钮显示调整
      // $("#exitFullScreenButton").show();
    } else if (document.body.webkitRequestFullScreen) {
      //兼容谷歌 /Edge
      document.body.webkitRequestFullScreen();
      // $("#fullScreenButton").css("display","none");
      // $("#exitFullScreenButton").show();
    } else if (document.body.mozRequestFullScreen) {
      //Firefox
      document.body.mozRequestFullScreen();
      // $("#fullScreenButton").css("display","none");
      // $("#exitFullScreenButton").show();
    } else if (document.body.msRequestFullscreen) {
      //ie
      document.body.msRequestFullscreen();
      // $("#fullScreenButton").css("display","none");
      // $("#exitFullScreenButton").show();
    } else {
      alert('浏览器不兼容');
    }
  });
//退出全屏模式
  $('#exitFullScreenButton').on('click', function (event) {
    if (document.exitFullscreen) {
      document.exitFullscreen();
      // $("#exitFullScreenButton").css("display","none");
      // $("#fullScreenButton").show();
    } else if (document.mozCancelFullScreen) {
      document.mozCancelFullScreen();
      // $("#exitFullScreenButton").css("display","none");
      // $("#fullScreenButton").show();
    } else if (document.webkitExitFullscreen) {
      document.webkitExitFullscreen();
      // $("#exitFullScreenButton").css("display","none");
      // $("#fullScreenButton").show();
    } else if (document.msExitFullscreen) {
      document.msExitFullscreen();
      // $("#exitFullScreenButton").css("display","none");
      // $("#fullScreenButton").show();
    } else {
      alert('浏览器不兼容');
    }
  });

  $('#map-menu-minus').on('click', function () {
    //地图菜单折叠
    $('#map-menu>button').css('display', 'none');
    $('#map-menu-magnify').css('display', 'inline-block');
  });
  $('#map-menu-magnify').on('click', function () {
    //地图菜单展开
    $('#map-menu>button').css('display', 'inline-block');
    $('#map-menu-magnify').css('display', 'none');
  });
  function asideMin() {}
//变色
  let mythis = null;
  function changeColor() {
    $('.btn-minus').css('background-color', 'rgb(0, 120, 212)');

    $('.btn-minus>svg').css('fill', 'white');
    if (this != mythis) {
      //设计二次点击取消样式
      mythis = this;
      $(this).css('background-color', 'white');
      $(this).children('svg').css('fill', 'rgba(5, 112, 189, 1)');
    } else {
      mythis = null;
    }
    //    Object.defineProperties($(this),"isclick",{value:1});
    //    this.prototype.isclick=1;
    // console.log($(this));
  }
//   let model = '3d';
//   $('#senceImg').on('click', function () {

//     //地图3d转2d
//     if (viewer.scene.mode === Cesium.SceneMode.SCENE3D) {

//       viewer.scene.mode = Cesium.SceneMode.SCENE2D; //2维模式
//       $('#senceImg').attr('src', '../static/images/3d.png');

//       $('#imgtp').html('三维');
//     } else {

//       viewer.scene.mode = Cesium.SceneMode.SCENE3D; //3维模式
//       $('#senceImg').attr('src', '../static/images/2d.png');
//       $('#imgtp').html('二维');
//       viewer.camera.flyTo({
//         destination: Cesium.Cartesian3.fromDegrees(118.9809, 34.8503, 200000.0),
//         duration: 0,
//       });
//     }
//   });

  $('.btn-minus').on('click', changeColor);
  // menuMinus;
  $('#menuMinus').on('click', function () {
    $('#sideMenu').css('display', 'none');
    $('#sideBar').css('display', 'block');
  }); //菜单折叠
  $('#spredBtn').on('click', function () {
    //菜单展开
    $('#sideBar').css('display', 'none');
    $('#sideMenu').css('display', 'block');
  });

  $('#map-menu-minus').on('click', function () {
    //地图菜单折叠
    $('#map-menu>button').css('display', 'none');
    $('#map-menu-magnify').css('display', 'inline-block');
  });
  $('#map-menu-magnify').on('click', function () {
    //地图菜单展开
    $('#map-menu>button').css('display', 'inline-block');
    $('#map-menu-magnify').css('display', 'none');
  });
  $('#fullScreenButton').on('click', function (event) {
    //全屏效果
    event.preventDefault();
    event.stopPropagation();
    // Trigger fullscreen

    if (document.body.requestFullscreen) {
      //解决兼容性问题
      document.body.requestFullscreen();
      // $("#fullScreenButton").css("display","none");//按钮显示调整
      // $("#exitFullScreenButton").show();
    } else if (document.body.webkitRequestFullScreen) {
      //兼容谷歌 /Edge
      document.body.webkitRequestFullScreen();
      // $("#fullScreenButton").css("display","none");
      // $("#exitFullScreenButton").show();
    } else if (document.body.mozRequestFullScreen) {
      //Firefox
      document.body.mozRequestFullScreen();
      // $("#fullScreenButton").css("display","none");
      // $("#exitFullScreenButton").show();
    } else if (document.body.msRequestFullscreen) {
      //ie
      document.body.msRequestFullscreen();
      // $("#fullScreenButton").css("display","none");
      // $("#exitFullScreenButton").show();
    } else {
      alert('浏览器不兼容');
    }
  });
//退出全屏模式
  $('#exitFullScreenButton').on('click', function (event) {
    if (document.exitFullscreen) {
      document.exitFullscreen();
      // $("#exitFullScreenButton").css("display","none");
      // $("#fullScreenButton").show();
    } else if (document.mozCancelFullScreen) {
      document.mozCancelFullScreen();
      // $("#exitFullScreenButton").css("display","none");
      // $("#fullScreenButton").show();
    } else if (document.webkitExitFullscreen) {
      document.webkitExitFullscreen();
      // $("#exitFullScreenButton").css("display","none");
      // $("#fullScreenButton").show();
    } else if (document.msExitFullscreen) {
      document.msExitFullscreen();
      // $("#exitFullScreenButton").css("display","none");
      // $("#fullScreenButton").show();
    } else {
      alert('浏览器不兼容');
    }
  });
  function asideMin() {}
//变色
//   let mythis = null;
//   function changeColor() {
//     $('.btn-minus').css('background-color', 'rgb(0, 120, 212)');
//
//     $('.btn-minus>svg').css('fill', 'white');
//     if (this != mythis) {
//       //设计二次点击取消样式
//       mythis = this;
//       $(this).css('background-color', 'white');
//       $(this).children('svg').css('fill', 'rgba(5, 112, 189, 1)');
//     } else {
//       mythis = null;
//     }
//     //    Object.defineProperties($(this),"isclick",{value:1});
//     //    this.prototype.isclick=1;
//     console.log($(this));
//   }
  // let model = '3d';
  // $('footer').on('click', function () {
  //   //地图3d转2d
  //   if (viewer.scene.mode === Cesium.SceneMode.SCENE3D) {
  //     viewer.scene.mode = Cesium.SceneMode.SCENE2D; //2维模式
  //     $('#senceImg').attr('src', '../static/images/3d.png');
  //     $('#imgtp').html('三维');
  //   } else {
  //     viewer.scene.mode = Cesium.SceneMode.SCENE3D; //2维模式
  //     $('#senceImg').attr('src', '../static/images/2d.png');
  //     $('#imgtp').html('二维');
  //   }
  // });

//解决办法 2.监听Document.onfullscreenchange
  document.onfullscreenchange = function () {
    if (document.webkitFullscreenElement || document.mozFullScreenElement || document.msFullscreenElement) {
      $('#fullScreenButton').css('display', 'none');
      $('#exitFullScreenButton').show();
    } else {
      $('#exitFullScreenButton').css('display', 'none');
      $('#fullScreenButton').show();
    }
  };
  document.onkeydown = function (event) {
    //     alert("按键按下");
    let e = event || window.event ;
    //|| arguments.callee.caller.arguments[0]
    if (e && e.keyCode == 122) {
      // 按 F11
      e.preventDefault();
      e.stopPropagation();
      if (document.body.requestFullscreen) {
        //解决兼容性问题
        document.body.requestFullscreen();
        $('#fullScreenButton').css('display', 'none'); //按钮显示调整
        $('#exitFullScreenButton').show();
      } else if (document.body.webkitRequestFullScreen) {
        //兼容谷歌 /Edge
        document.body.webkitRequestFullScreen();
        $('#fullScreenButton').css('display', 'none');
        $('#exitFullScreenButton').show();
      } else if (document.body.mozRequestFullScreen) {
        //Firefox
        document.body.mozRequestFullScreen();
        $('#fullScreenButton').css('display', 'none');
        $('#exitFullScreenButton').show();
      } else if (document.body.msRequestFullscreen) {
        //ie
        document.body.msRequestFullscreen();
        $('#fullScreenButton').css('display', 'none');
        $('#exitFullScreenButton').show();
      }
    }
  };

//以上为解决问题的三种方法

//使用须知弹出
//   $(function () {
//     $('[data-toggle="tooltip"]').tooltip();
//     // $('#myModal').modal('toggle')
//   });

}
