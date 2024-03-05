export default function FlyInCzml(viewer, handler) {


  // 边界经纬度
  let left = 118.865;
  let right = 119.172;
  let top = 34.7610;
  let down = 34.6858;

  // 起始点经纬度
  var init_lon = 118.865;
  var init_lat = 34.761;

  // 定义单元间距 -- 米
  let interval = 50;
  // 一米代表的经纬度
  let convert = 1.0 / (112.4 * 1000);

  // 确定初始点
  var init_longitude = left;
  var init_latitude = down
  // 该多边形横向、纵向 ---> 多边形的长和高
  var long = Math.ceil((right - left) / convert);
  var height = Math.ceil((top - down) / convert);
  for (let i = 0; i <= long; i = i + interval) {
    for (let j = 0; j <= height; j = j + interval) {

      var x0 = (init_longitude + i * convert).toFixed(9);
      var y0 = (init_latitude + j * convert).toFixed(9);
      var x1 = (init_longitude + (i + interval) * convert).toFixed(9);
      var y1 = (init_latitude + j * convert).toFixed(9);
      var x2 = (init_longitude + (i + interval) * convert).toFixed(9);
      var y2 = (init_latitude + (j + interval) * convert).toFixed(9);
      var x3 = (init_longitude + i * convert).toFixed(9);
      var y3 = (init_latitude + (j + interval) * convert).toFixed(9);

      viewer.entities.add({
        name: 'ractangle area',
        // description:"水速：" + 123 + '\n' + "水向：" + 234,
        description: '\<span>' +
          '水速：' +
          i +
          '<br>' +
          '水向：' +
          j +
          '</span>',
        rectangle: {
          coordinates: Cesium.Rectangle.fromCartesianArray([
            Cesium.Cartesian3.fromDegrees(x0, y0, 0),
            Cesium.Cartesian3.fromDegrees(x1, y1, 0),
            Cesium.Cartesian3.fromDegrees(x2, y2, 0),
            Cesium.Cartesian3.fromDegrees(x3, y3, 0),
          ]),
          material: Cesium.Color.WHITE.withAlpha(0.5),
        }
      });

      }

    }


  // viewer.entities.add({
  //   name: 'ractangle area',
  //   // description:"水速：" + 123 + '\n' + "水向：" + 234,
  //   description: '\<span>' +
  //     '水速：' +
  //     123 +
  //     '<br>' +
  //     '水向：' +
  //     234 +
  //     '</span>',
  //   rectangle: {
  //     coordinates: Cesium.Rectangle.fromCartesianArray([
  //       Cesium.Cartesian3.fromDegrees(left, down, 0),
  //       Cesium.Cartesian3.fromDegrees(left, down + 50 * convert, 0),
  //       Cesium.Cartesian3.fromDegrees(left + 50 * convert, down, 0),
  //       Cesium.Cartesian3.fromDegrees(left + 50 * convert, down + 50 * convert, 0),
  //     ]),
  //     material: Cesium.Color.WHITE.withAlpha(0.5),
  //   }
  // });
  // viewer.entities.add({
  //   name: 'ractangle area',
  //   // description:"水速：" + 123 + '\n' + "水向：" + 234,
  //   description: '\<span>' +
  //     '水速：' +
  //     456 +
  //     '<br>' +
  //     '水向：' +
  //     789 +
  //     '</span>',
  //   rectangle: {
  //     coordinates: Cesium.Rectangle.fromCartesianArray([
  //       Cesium.Cartesian3.fromDegrees(left + 50 * convert, down + 50 * convert, 0),
  //       Cesium.Cartesian3.fromDegrees(left + 50 * convert, down + 100 * convert, 0),
  //       Cesium.Cartesian3.fromDegrees(left + 50 * convert, down + 50 * convert, 0),
  //       Cesium.Cartesian3.fromDegrees(left + 100 * convert, down + 100 * convert, 0),
  //     ]),
  //     material: Cesium.Color.RED.withAlpha(0.5),
  //   }
  // });


  // function drawUnit() {

  // const entities = viewer.entities;
  // // 每隔interval * convert绘制一条经度线
  // for (let lang = left; lang <= right; lang += interval * convert) {
  //   entities.add({
  //     position: Cesium.Cartesian3.fromDegrees(Number(lang.toFixed(9)), 0),
  //     polyline: {
  //       positions: Cesium.Cartesian3.fromDegreesArray([
  //         Number(lang),
  //         Number(top),
  //           Number(lang),
  //             Number(down),
  //       ]),
  //       width: 1.0,
  //       material: Cesium.Color.WHITE,
  //       clampToGround: true,
  //     },
  //
  //   });
  // }
  //
  // //纬度
  // let langS = [];
  // for (let lang = left; lang <= right; lang += interval * convert) {
  //   langS.push(Number(lang));
  // }
  // //每隔10读绘制一条纬度线和纬度标注,自己控制间隔
  // for (let lat = down; lat <= top + interval * convert; lat += interval * convert) {
  //
  //   entities.add({
  //     position: Cesium.Cartesian3.fromDegrees(0, Number(lat.toFixed(9))),
  //     polyline: {
  //       positions: Cesium.Cartesian3.fromDegreesArray(
  //         langS
  //           .map((long) => {
  //             return [long, lat].join(",");
  //           })
  //           .join(",")
  //           .split(",")
  //           .map((item) => Number(item))
  //       ),
  //       width: 1.0,
  //       material: Cesium.Color.WHITE,
  //       clampToGround: true,
  //     },
  //
  //   });
  // }


  // }


}
