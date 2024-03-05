export default function FlyInCzml(viewer, handler) {


  // 边界经纬度
  let left = 118.865;
  // let right = 119.172;
  let right = 118.902;
  // let top = 34.7610;
  let top = 34.7010;
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

        polygon: {
          hierarchy: Cesium.Cartesian3.fromDegreesArray([
            Number(x0),
            Number(y0),
            Number(x1),
            Number(y1),
            Number(x2),
            Number(y2),
            Number(x3),
            Number(y3),

          ]),
          material: Cesium.Color.RED.withAlpha(0.5),
          outline: true,
          height: 0,
          outlineColor: Cesium.Color.BLACK,
        },

      });

    }

  }

}
