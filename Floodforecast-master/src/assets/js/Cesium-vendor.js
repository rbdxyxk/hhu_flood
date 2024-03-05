
window.CESIUM_BASE_URL = '/';
import * as Cesium from 'cesium';
// Cesium.Ion.defaultAccessToken = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiIzN2IxMTI4ZC1jYjBkLTQwMDEtYTIzMi0zY2RkMzNkMjhiODAiLCJpZCI6NTU5MzcsImlhdCI6MTYyMTA2NDY0NX0.XYQntRk0Zl4DO2MQOl4yDqpcQa9m9VWwtGpe1Q0ntro';
// let tdtLayer2 = new Cesium.UrlTemplateImageryProvider({
//   // url: "http://webrd02.is.autonavi.com/appmaptile?lang=zh_cn&size=1&scale=1&style=8&x={x}&y={y}&z={z}",//高德标记图图
//   url: "https://webst02.is.autonavi.com/appmaptile?style=6&x={x}&y={y}&z={z}",//高德影像图
//   //url: "http://webst02.is.autonavi.com/appmaptile?x={x}&y={y}&z={z}&lang=zh_cn&size=1&scale=1&style=8",//高德标记图
//   minimumLevel: 3,
//   maximumLevel: 18
// })
// let viewer = new Cesium.Viewer("cesiumContainer",{
//
//   terrainExaggeration: 4.0,
//   homeButton: false, //是否显示Home按钮
//   animation: false, //是否显示动画控件
//   timeline: false, //是否显示时间线控件
//   imageryProvider: tdtLayer2 ,
//   fullscreenButton: false,
//   baseLayerPicker: false, //是否显示图层选择控件
//   sceneModePicker: true, //是否显示投影方式控件
//   navigationHelpButton: false, //是否显示帮助信息控件
//   geocoder: false, //是否显示地名查找控件
//   infoBox:false,
//   // sceneModePicker: false, //是否显示3D/2D选择器
//
// });
// let blackMarble = this.viewer.scene.imageryLayers.addImageryProvider(
//   new Cesium.UrlTemplateImageryProvider({
//     url: 'static/image_tiles/{z}/{x}/{y}.png',
//     minimumLevel: 0,
//     maximumLevel: 13,
//   })
// );
//
// viewer.dataSources.add(
//   Cesium.GeoJsonDataSource.load('static/sajiawan/水文站.json', {
//     stroke: Cesium.Color.HOTPINK,
//     fill: Cesium.Color.PINK,
//     strokeWidth: 3,
//     markerSymbol: 'circle',
//     markerSize: '24',
//     markerColor: Cesium.Color.YELLOW,
//   })
// ); //geojosn数据
//
// viewer.dataSources.add(
//   Cesium.GeoJsonDataSource.load('static/sajiawan/省属湖泊.json', {
//     stroke: Cesium.Color.HOTPINK,
//     fill: Cesium.Color.PINK,
//     strokeWidth: 3,
//     markerSymbol: 'circle',
//     markerSize: '24',
//     markerColor: Cesium.Color.YELLOW,
//   })
// ); //geojosn数据
//
// //geojson
// var geojsonOptions = {
//   //贴地
//   clampToGround: true
// };
//
// // var iframe = document.getElementsByClassName('cesium-infoBox-iframe')[0];
// //
// // iframe.setAttribute('sandbox', 'allow-same-origin allow-scripts allow-popups allow-forms');
// // iframe.setAttribute('src', ''); //必须设置src为空 否则不会生效
//
//
// //设置初始视角
// viewer.camera.flyTo({
//   destination: Cesium.Cartesian3.fromDegrees(118.9809, 34.8503, 200000.0),
//   duration: 0,
// });
window.Cesium=Cesium;
// window.viewer=viewer;

export default Cesium;
// export  {
//   viewer,
//   Cesium
// }
