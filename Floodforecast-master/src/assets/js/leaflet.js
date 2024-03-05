export default function leaflet(viewer,handler3D){
  var removeHandler;
  var content;
  var autoInfoWindow;
  var infoDiv = '<div id="trackPopUp" style="display:none;">' +
    '<div id="trackPopUpContent" class="leaflet-popup" style="top:5px;left:0;">' +
    '<a class="leaflet-popup-close-button" href="#">×</a>' +
    '<div class="leaflet-popup-content-wrapper">' +
    '<div id="trackPopUpLink" class="leaflet-popup-content" style="max-width: 300px;"></div>' +
    '</div>' +
    '<div class="leaflet-popup-tip-container">' +
    '<div class="leaflet-popup-tip"></div>' +
    '</div>' +
    '</div>' +
    '</div>';
  $("#cesiumContainer"
).
append(infoDiv);

// var handler3D = new Cesium.ScreenSpaceEventHandler(scene.canvas);
// handler3D.setInputAction(function (movement) {
//
//   var pick = viewer.scene.pick(movement.position);
//   if (pick && pick.id) {
//     console.log(pick.id);
//     $('#trackPopUp').show();
//     var cartographic = Cesium.Cartographic.fromCartesian(movement.position);
//     var point = [cartographic.longitude / Math.PI * 180, cartographic.latitude / Math.PI * 180];
//     var destination = Cesium.Cartesian3.fromDegrees(point[0], point[1], 3000.0);
//     var id = pick.id._id.replace(/[^0-9]/ig, "");
//     content = "水情信息"
//     //  '<table><tbody><tr><th style="color:black;">'+pick.id._name+'</th><td><button style="color:black;" οnclick="updateValve('+id+')">确定</button></td><td><button οnclick="deleteValve('+id+')" style="color:black;">删除</button></td></tr>'+
//     //  '<tr><th style="color:black;">类型</th><td><input style="color:black;">水情信息</td></tr>'+
//     //  '<tr><th style="color:black;">类型</th><td><input style="color:black;" value='+station[id].stadianame+'></td></tr>'+
//     //  '<tr><th style="color:black;">经度</th><td><input id="x" style="color:black;" value='+station[id].x.toFixed(6)+'></td></tr>'+
//     //  '<tr><th style="color:black;">纬度</th><td><input id="y" style="color:black;" value='+station[id].y.toFixed(6)+'></td></tr>'+
//     //  '</tbody></table>'
//     var obj = {position: movement.position, destination: destination, content: content};
//     infoWindow(obj);
//
//     function infoWindow(obj) {
//       var picked = scene.pick(obj.position);
//       if (Cesium.defined(picked)) {
//         var id = Cesium.defaultValue(picked.id, picked.primitive.id);
//         if (id instanceof Cesium.Entity) {
//           $(".cesium-selection-wrapper").show();
//           $('#trackPopUpLink').empty();
//           $('#trackPopUpLink').append(obj.content);
//
//           function positionPopUp(c) {
//             var x = c.x - ($('#trackPopUpContent').width()) / 2;
//             var y = c.y - ($('#trackPopUpContent').height());
//             $('#trackPopUpContent').css('transform', 'translate3d(' + x + 'px, ' + y + 'px, 0)');
//           }
//
//           var c = new Cesium.Cartesian2(obj.position.x, obj.position.y);
//           $('#trackPopUp').show();
//           positionPopUp(c); // Initial position
//           // at the place item
//           // picked
//           removeHandler = viewer.scene.postRender.addEventListener(function () {
//             if (picked.id._polyline != null) {
//               var pos = {};
//               pos.x = (id._polyline._positions._value["0"].x + id._polyline._positions._value[1].x) / 2;
//               pos.y = (id._polyline._positions._value["0"].y + id._polyline._positions._value[1].y) / 2;
//               pos.z = (id._polyline._positions._value["0"].z + id._polyline._positions._value[1].z) / 2;
//               var changedC = Cesium.SceneTransforms.wgs84ToWindowCoordinates(viewer.scene, pos);
//             } else {
//               var changedC = Cesium.SceneTransforms.wgs84ToWindowCoordinates(viewer.scene, id._position._value);
//             }// If things moved, move the
//             // popUp too
//             if ((c.x !== changedC.x) || (c.y !== changedC.y)) {
//               positionPopUp(changedC);
//               c = changedC;
//             }
//           });
//           // PopUp close button event handler
//           $('.leaflet-popup-close-button').click(function () {
//             $('#trackPopUp').hide();
//             $('#trackPopUpLink').empty();
//             $(".cesium-selection-wrapper").hide();
//             removeHandler.call();
//             return false;
//           });
//           return id;
//         }
//       }
//     }
//   } else {
//     $('#trackPopUp').hide();
//
//   }
// }, Cesium.ScreenSpaceEventType.LEFT_CLICK);
}
