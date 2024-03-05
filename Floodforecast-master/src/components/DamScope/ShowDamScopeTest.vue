<template>
  <div>
      <div class = "test">
        <button @click="test">test</button>
      </div>
  </div>
</template>

<script>
import Cesium from  'cesium/Build/Cesium';
import "cesium/Build/Cesium/Widgets/widgets.css";

export default {

  name: "ShowDamScope",
  components:{

  },
  data(){

  },
  methods:{
      ShowDamScope(){

      },
      test(){
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



          }, Cesium.ScreenSpaceEventType.MOUSE_MOVE); //ScreenSpaceEventType
        }, Cesium.ScreenSpaceEventType.LEFT_CLICK); //ScreenSpaceEventType

      }
  }
}
</script>

<style scoped>
  .test{
    width: 50%;
    height: 50%;
    background-color: red;
  }
</style>
