// 戈智诚 7.5号
import {id, none} from "html-webpack-plugin/lib/chunksorter";
import {GeoJsonDataSource} from "cesium";

export default function ground(viewer, handler) {
  //图层管理
  $("#skyMap").on("click",function(){
    $('#tuceng').fadeIn();
    $('#skyMap1').css("display","inline-block");
    $('#skyMap').css("display","none");
  });
  $("#skyMap1").on("click",function(){
    $('#tuceng').fadeOut();
    $('#skyMap').css("display","inline-block");
    $('#skyMap1').css("display","none");
  });

  $("#hemian").click(function () {
    if ($('#imgtp10').css('display') == "none") {
      $('#imgtp10').css("display", "inline-block");

      viewer.dataSources.add(Cesium.GeoJsonDataSource.load('static/sajiawan/全省河道_面.json', {
         stroke: Cesium.Color.HOTPINK,
         fill: Cesium.Color.PINK,
         strokeWidth: 3,
         markerSymbol: 'circle',
         markerSize: '24',
         markerColor: Cesium.Color.YELLOW,
         // name:"abc",
         // id:"box"
      }));//geojson数据
    }
    else {
      $('#imgtp10').css("display", "none");
      var len =viewer.dataSources.length;
      if(len>0){
        for (var i=0;i<len;i++){
          var dataSource = viewer.dataSources.get(i);
              // if(dataSource.name === "全省河道_面.json")
             if(dataSource.show=true)
          {
            viewer.dataSources.remove(dataSource);
          }
        }
      }
    }

  });
  $("#baoxun").click(function () {
    if ($('#imgtp2').css('display')=="none") {
      $('#imgtp2').css("display", "inline-block");
      var geojsonOptions = {
        clampToGround: true //使数据贴地
      };
      var entities;
      var promise = Cesium.GeoJsonDataSource.load('static/sajiawan/报汛站.json', geojsonOptions);
      promise.then(async function (dataSource) {
        let data= await viewer.dataSources.add(dataSource);
        entities = dataSource.entities.values;
        for (var i = 0; i < entities.length; i++) {
          var entity = entities[i];
          entity.billboard.image = '/static/images/1.png'
          entity.billboard.width = 20
          entity.billboard.height = 20
        }
      });
    }
    else {
      $('#imgtp2').css('display', "none");
      var len1 =viewer.dataSources.length;
      if(len1>0){
        for (var i=0;i<len1;i++){
          var dataSource1 = viewer.dataSources.get(i);
           // if("报汛站.json" == dataSource1.name)
             if(dataSource1.show=true)
          {
            viewer.dataSources.remove(dataSource1);
          }
        }
      }
    }
    });

  $("#huku").click(function () {
    if ($('#imgtp9').css('display')=="none") {
      $('#imgtp9').css("display", "inline-block");
      var geojsonOptions = {
        clampToGround: true //使数据贴地
      };
      var entities;
      var promise = Cesium.GeoJsonDataSource.load('static/sajiawan/湖库站.json', geojsonOptions);
      promise.then(function (dataSource) {
        viewer.dataSources.add(dataSource);
        entities = dataSource.entities.values;
        for (var i = 0; i < entities.length; i++) {
          var entity = entities[i];
          entity.billboard.image = '/static/images/333.png'
          entity.billboard.width = 20
          entity.billboard.height = 20
        }
      });
    }
    else {
      $('#imgtp9').css('display', "none");
      var len2 =viewer.dataSources.length;
      if(len2>0){
        for (var i=0;i<len2;i++){
          var dataSource2 = viewer.dataSources.get(i);
          if(dataSource2.show=true){
            viewer.dataSources.remove(dataSource2);
          }
        }
      }
    }
  });

  $("#shuiwei").click(function () {
    if ($('#imgtp4').css('display')=="none") {
      $('#imgtp4').css("display", "inline-block");
      var geojsonOptions = {
        clampToGround: true //使数据贴地
      };
      var entities;
      var promise = Cesium.GeoJsonDataSource.load('static/sajiawan/水位站.json', geojsonOptions);
      promise.then(function (dataSource) {
        viewer.dataSources.add(dataSource);
        entities = dataSource.entities.values;
        for (var i = 0; i < entities.length; i++) {
          var entity = entities[i];
          entity.billboard.image = '/static/images/555.png'
          entity.billboard.width = 20
          entity.billboard.height = 20
        }
      });
    }
    else {
      $('#imgtp4').css('display',"none");
      var len3 =viewer.dataSources.length;
      if(len3>0){
        for (var i=0;i<len3;i++){
          var dataSource3 = viewer.dataSources.get(i);
          if(dataSource3.show=true){
            viewer.dataSources.remove(dataSource3);
          }
        }
      }
        }

  });

  $("#shuiwen").click(function () {
    if ($('#imgtp5').css('display')=="none") {
      $('#imgtp5').css("display", "inline-block");
      var geojsonOptions = {
        clampToGround: true //使数据贴地
      };
      var entities;
      var promise = Cesium.GeoJsonDataSource.load('static/sajiawan/水文站.json', geojsonOptions);
      promise.then(function (dataSource) {
        viewer.dataSources.add(dataSource);
        entities = dataSource.entities.values;
        for (var i = 0; i < entities.length; i++) {
          var entity = entities[i];
          entity.billboard.image = '/static/images/444.png'
          entity.billboard.width = 20
          entity.billboard.height = 20
        }
      });
    }
    else {
      $('#imgtp5').css('display',"none");
      var len4 =viewer.dataSources.length;
      if(len4>0){
        for (var i=0;i<len4;i++){
          var dataSource4 = viewer.dataSources.get(i);
          if(dataSource4.show=true){
            viewer.dataSources.remove(dataSource4);
          }
        }
      }
        }

  });

  $("#yuliang").click(function () {
    if ($('#imgtp3').css('display')=="none") {
      $('#imgtp3').css("display", "inline-block");
      var geojsonOptions = {
        clampToGround: true //使数据贴地
      };
      var entities;
      var promise = Cesium.GeoJsonDataSource.load('static/sajiawan/雨量站.json', geojsonOptions);
      promise.then(function (dataSource) {
        viewer.dataSources.add(dataSource);
        entities = dataSource.entities.values;
        for (var i = 0; i < entities.length; i++) {
          var entity = entities[i];
          entity.billboard.image = '/static/images/666.png'
          entity.billboard.width = 20
          entity.billboard.height = 20
        }
      });
    }
    else {
      $('#imgtp3').css('display',"none");
      var len5 =viewer.dataSources.length;
      if(len5>0){
        for (var i=0;i<len5;i++){
          var dataSource5 = viewer.dataSources.get(i);
          if(dataSource5.show=true){
            viewer.dataSources.remove(dataSource5);
          }
        }
      }
        }

  });

  $("#shuiku").click(function () {
    if ($('#imgtp12').css('display')=="none") {
      $('#imgtp12').css("display", "inline-block");
      viewer.dataSources.add(Cesium.GeoJsonDataSource.load('static/sajiawan/江苏大中水库.json', {
        stroke: Cesium.Color.blue,
        fill: Cesium.Color.blue,
        strokeWidth: 3,
        markerSymbol: 'circle',
        markerSize: '24',
        markerColor: Cesium.Color.blue
      }));//geojosn数据

    }
    else{
      $('#imgtp12').css('display',"none");
      var len6 =viewer.dataSources.length;
      if(len6>0){
        for (var i=0;i<len6;i++){
          var dataSource6 = viewer.dataSources.get(i);
          if(dataSource6.show=true){
            viewer.dataSources.remove(dataSource6);
          }
        }
      }
    }

  });

  $("#xian").click(function () {
    if ($('#imgtp11').css('display')=="none") {
      $('#imgtp11').css("display", "inline-block");
      viewer.dataSources.add(Cesium.GeoJsonDataSource.load('static/sajiawan/全省河道_线.json', {
        stroke: Cesium.Color.black,
        fill: Cesium.Color.black,
        strokeWidth: 3,
        markerSymbol: 'circle',
        markerSize: '24',
        markerColor: Cesium.Color.GREEN
      }));//geojosn数据
    }
    else {
      $('#imgtp11').css('display', "none");
      var len7 =viewer.dataSources.length;
      if(len7>0){
        for (var i=0;i<len7;i++){
          var dataSource7 = viewer.dataSources.get(i);
          if(dataSource7.show=true){
            viewer.dataSources.remove(dataSource7);
          }
        }
      }
    }
  });

  $("#hupo").click(function () {
    if ($('#imgtp13').css('display')=="none") {
      $('#imgtp13').css("display", "inline-block");
      viewer.dataSources.add(Cesium.GeoJsonDataSource.load('static/sajiawan/省属湖泊.json', {
        stroke: Cesium.Color.black,
        fill: Cesium.Color.black,
        strokeWidth: 3,
        markerSymbol: 'circle',
        markerSize: '24',
        markerColor: Cesium.Color.blue
      }));//geojosn数据
    }
    else {
      $('#imgtp13').css('display',"none");
      var len8 =viewer.dataSources.length;
      if(len8>0){
        for (var i=0;i<len8;i++){
          var dataSource8 = viewer.dataSources.get(i);
          if(dataSource8.show=true){
            viewer.dataSources.remove(dataSource8);
          }
        }
      }
    }
  });

  $("#handong").click(function () {
    if ($('#imgtp6').css('display')=="none") {
      $('#imgtp6').css("display", "inline-block");
      var geojsonOptions = {
        clampToGround: true //使数据贴地
      };
      var entities;
      var promise = Cesium.GeoJsonDataSource.load('static/sajiawan/涵洞.json', geojsonOptions);
      promise.then(function (dataSource) {
        viewer.dataSources.add(dataSource);
        entities = dataSource.entities.values;
        for (var i = 0; i < entities.length; i++) {
          var entity = entities[i];
          entity.billboard.image = '/static/images/111.png'
          entity.billboard.width = 20
          entity.billboard.height = 20
        }
      });
    }
    else{
      $('#imgtp6').css('display',"none");
      var len9 =viewer.dataSources.length;
      if(len9>0){
        for (var i=0;i<len9;i++){
          var dataSource9 = viewer.dataSources.get(i);
          if(dataSource9.show=true){
            viewer.dataSources.remove(dataSource9);
          }
        }
      }
    }
  });

  $("#beng").click(function () {
    if ($('#imgtp8').css('display')=="none") {
      $('#imgtp8').css("display", "inline-block");
      var geojsonOptions = {
        clampToGround: true //使数据贴地
      };
      var entities;
      var promise = Cesium.GeoJsonDataSource.load('static/sajiawan/泵站.json', geojsonOptions);
      promise.then(function (dataSource) {
        viewer.dataSources.add(dataSource);
        entities = dataSource.entities.values;
        for (var i = 0; i < entities.length; i++) {
          var entity = entities[i];
          entity.billboard.image = '/static/images/222.png'
          entity.billboard.width = 20
          entity.billboard.height = 20
        }
      });
    }
    else{
      $('#imgtp8').css('display',"none");
      var len10 =viewer.dataSources.length;
      if(len10>0){
        for (var i=0;i<len10;i++){
          var dataSource10 = viewer.dataSources.get(i);
          if(dataSource10.show=true)
          {
            viewer.dataSources.remove(dataSource10);
          }
        }
      }
    }
  });

  $("#shuizha").click(function () {
    if ($('#imgtp7').css('display')=="none") {
      $('#imgtp7').css("display", "inline-block");
      var geojsonOptions = {
        clampToGround: true //使数据贴地
      };
      var entities;
      var promise = Cesium.GeoJsonDataSource.load('static/sajiawan/水闸.json', geojsonOptions);
      promise.then(function (dataSource) {
        viewer.dataSources.add(dataSource);
        entities = dataSource.entities.values;
        for (var i = 0; i < entities.length; i++) {
          var entity = entities[i];
          entity.billboard.image = '/static/images/sz.png'
          entity.billboard.width = 20
          entity.billboard.height = 20
        }
      });
    }
    else{
     $('#imgtp7').css('display',"none");
      var len11 =viewer.dataSources.length;
      if(len11>0){
        for (var i=0;i<len11;i++){
          var dataSource11 = viewer.dataSources.get(i);
          if(dataSource11.show=true){
            viewer.dataSources.remove(dataSource11);
          }
        }
      }
    }
  });

  $("#map-yx").click(function () {
    viewer.scene.imageryLayers.addImageryProvider(new Cesium.WebMapTileServiceImageryProvider({
      url:'http://t0.tianditu.com/img_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=img&tileMatrixSet=w&TileMatrix={TileMatrix}&TileRow={TileRow}&TileCol={TileCol}&style=default&format=tiles&tk=c1e420f4c753b019d7398229519cb71d',
      layer: "tdtImgAnnoLayer",
      style: "default",
      format: "../assets/images/img_tdt_dx.jpeg",
      tileMatrixSetID: "GoogleMapsCompatible",
      show: false
    }))
    viewer.imageryLayers.addImageryProvider(new Cesium.WebMapTileServiceImageryProvider({
      url: "http://t0.tianditu.com/cia_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=cia&tileMatrixSet=w&TileMatrix={TileMatrix}&TileRow={TileRow}&TileCol={TileCol}&style=default&format=tiles&tk=c1e420f4c753b019d7398229519cb71d",
      layer: "tdtImgAnnoLayer",
      style: "default",
      format: "../assets/images/img_tdt_dx.jpeg",
      tileMatrixSetID: "GoogleMapsCompatible",
      show: true
    }))
  });

  $("#map-sl").click(function () {
    viewer.imageryLayers.addImageryProvider(new Cesium.WebMapTileServiceImageryProvider({
      url:'http://t0.tianditu.gov.cn/vec_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=vec&tileMatrixSet=w&TileMatrix={TileMatrix}&TileRow={TileRow}&TileCol={TileCol}&style=default&format=tiles&tk=c1e420f4c753b019d7398229519cb71d',
      layer: "tdtImgAnnoLayer",
      style: "default",
      format: "../assets/images/img_tdt_sl.jpeg",
      tileMatrixSetID: "GoogleMapsCompatible",
      show: false
    }))
    viewer.imageryLayers.addImageryProvider(new Cesium.WebMapTileServiceImageryProvider({
      url: "http://t0.tianditu.com/cia_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=cia&tileMatrixSet=w&TileMatrix={TileMatrix}&TileRow={TileRow}&TileCol={TileCol}&style=default&format=tiles&tk=c1e420f4c753b019d7398229519cb71d",
      layer: "tdtImgAnnoLayer",
      style: "default",
      format: "../assets/images/img_tdt_dx.jpeg",
      tileMatrixSetID: "GoogleMapsCompatible",
      show: false
    }))
  });

  $("#map-dx").click(function () {
    viewer.imageryLayers.addImageryProvider(new Cesium.WebMapTileServiceImageryProvider({
      url:'http://t0.tianditu.gov.cn/ter_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=ter&tileMatrixSet=w&TileMatrix={TileMatrix}&TileRow={TileRow}&TileCol={TileCol}&style=default&format=tiles&tk=c1e420f4c753b019d7398229519cb71d',
      layer:'USGSShadedReliefOnly',
      style: "default",
      format: "../assets/images/img_tdt_dx.jpeg",
      tileMatrixSetID: "GoogleMapsCompatible",
      show: false
    }))
    viewer.imageryLayers.addImageryProvider(new Cesium.WebMapTileServiceImageryProvider({
      url: "http://t0.tianditu.com/cia_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=cia&tileMatrixSet=w&TileMatrix={TileMatrix}&TileRow={TileRow}&TileCol={TileCol}&style=default&format=tiles&tk=c1e420f4c753b019d7398229519cb71d",
      layer: "tdtImgAnnoLayer",
      style: "default",
      format: "../assets/images/img_tdt_dx.jpeg",
      tileMatrixSetID: "GoogleMapsCompatible",
      show: false
    }))
  });


// //小淹没
//   var River3Point =[
//     118.635950,34.767694,
//     118.646679,34.773016,
//     118.656292,34.770699,
//     118.661785,34.766579,
//     118.664188,34.765720,
//     118.667879,34.765377,
//     118.673973,34.767351,
//     118.676291,34.767351,
//     118.679466,34.766150,
//     118.682213,34.763918,
//     118.685217,34.762802,
//     118.692684,34.764090,
//     118.695002,34.766922,
//     118.703241,34.765205,
//     118.704872,34.765377,
//     118.712769,34.769325,
//     118.715858,34.769754,
//     118.716974,34.769411,
//     118.728476,34.771557,
//     118.740234,34.762158,
//     118.747444,34.761858,
//     118.745599,34.767480,
//     118.746114,34.767737,
//     118.748345,34.765205,
//     118.751650,34.764304,
//     118.755341,34.765120,
//     118.759975,34.767351,
//     118.764095,34.770784,
//     118.770962,34.779797,
//     118.771949,34.780097,
//     118.772378,34.780998,
//     118.772292,34.782372,
//     118.773408,34.783401,
//     118.775640,34.783401,
//     118.776670,34.784474,
//     118.777184,34.788551,
//     118.780231,34.789109,
//     118.781176,34.787822,
//     118.783922,34.785891,
//     118.784952,34.785633,
//     118.785639,34.784560,
//     118.788943,34.783573,
//     118.789544,34.782157,
//     118.793149,34.781342,
//     118.793707,34.780741,
//     118.795381,34.780655,
//     118.797091,34.781642,
//     118.799500,34.781213,
//     118.806839,34.785075,
//     118.808298,34.787951,
//     118.806453,34.789367,
//     118.799801,34.790783,
//     118.797655,34.794903,
//     118.795169,34.797735,
//     118.796711,34.801683,
//     118.794308,34.808636,
//     118.800402,34.808636,
//     118.803492,34.803829,
//     118.809071,34.804087,
//     118.811474,34.809580,
//     118.813105,34.807949,
//     118.817139,34.807434,
//     118.819199,34.803829,
//     118.829498,34.804344,
//     118.833017,34.808722,
//     118.835936,34.808722,
//     118.835764,34.806662,
//     118.841257,34.805374,
//     118.841342,34.799280,
//     118.850098,34.796362,
//     118.851814,34.796877,
//     118.861256,34.795933,
//     118.866320,34.791298,
//     118.865891,34.788723,
//     118.872671,34.784346,
//     118.862543,34.766321,
//     118.861084,34.764347,
//     118.858252,34.762201,
//     118.852844,34.762802,
//     118.847265,34.768038,
//     118.842802,34.768982,
//     118.838596,34.766407,
//     118.832932,34.758253,
//     118.829498,34.758425,
//     118.828125,34.761944,
//     118.822374,34.762373,
//     118.819456,34.756451,
//     118.815165,34.753103,
//     118.810959,34.751472,
//     118.804350,34.756794,
//     118.800402,34.753618,
//     118.802118,34.758940,
//     118.799458,34.762716,
//     118.786411,34.762545,
//     118.779545,34.759026,
//     118.774395,34.753876,
//     118.764610,34.759970,
//     118.762035,34.759541,
//     118.760748,34.757051,
//     118.766241,34.753790,
//     118.765297,34.752331,
//     118.759031,34.754391,
//     118.746929,34.752417,
//     118.746758,34.754562,
//     118.740936,34.755905,
//     118.737300,34.756700,
//     118.737700,34.760700,
//     118.735428,34.763489,
//     118.731480,34.763060,
//     118.725643,34.763746,
//     118.726158,34.768295,
//     118.724356,34.768896,
//     118.709936,34.763575,
//     118.692856,34.762373,
//     118.690367,34.759884,
//     118.688135,34.759111,
//     118.676291,34.764175,
//     118.671999,34.764862,
//     118.667793,34.762888,
//     118.657923,34.766407,
//     118.655691,34.769154,
//     118.649940,34.770870,
//     118.644018,34.771385,
//     118.635950,34.767694,
//   ];
//   //河道1多边形
//   var polygon3 = new Cesium.PolygonGeometry({
//     polygonHierarchy : new Cesium.PolygonHierarchy(Cesium.Cartesian3.fromDegreesArray(River3Point)),
//     extrudedHeight:0,
//     height:0,
//     vertexFormat : Cesium.EllipsoidSurfaceAppearance.VERTEX_FORMAT,
//   });
//   var River3=new Cesium.Primitive({
//     geometryInstances : new Cesium.GeometryInstance({
//       geometry :polygon3
//     }),
//     appearance : new Cesium.EllipsoidSurfaceAppearance({
//       aboveGround : true,
//       fragmentShaderSource: 'varying vec3 v_positionMC;\n' +
//         'varying vec3 v_positionEC;\n' +
//         'varying vec2 v_st;\n' +
//         'void main()\n' +
//         '{\n' +
//         'czm_materialInput materialInput;\n' +
//         'vec3 normalEC = normalize(czm_normal3D * czm_geodeticSurfaceNormal(v_positionMC, vec3(0.0), vec3(1.0)));\n' +
//         '#ifdef FACE_FORWARD\n' +
//         'normalEC = faceforward(normalEC, vec3(0.0, 0.0, 1.0), -normalEC);\n' +
//         '#endif\n' +
//         'materialInput.s = v_st.s;\n' +
//         'materialInput.st = v_st;\n' +
//         'materialInput.str = vec3(v_st, 0.0);\n' +
//         'materialInput.normalEC = normalEC;\n' +
//         'materialInput.tangentToEyeMatrix = czm_eastNorthUpToEyeCoordinates(v_positionMC, materialInput.normalEC);\n' +
//         'vec3 positionToEyeEC = -v_positionEC;\n' +
//         'materialInput.positionToEyeEC = positionToEyeEC;\n' +
//         'czm_material material = czm_getMaterial(materialInput);\n' +
//         '#ifdef FLAT\n' +
//         'gl_FragColor = vec4(material.diffuse + material.emission, material.alpha);\n' +
//         '#else\n' +
//         'gl_FragColor = czm_phong(normalize(positionToEyeEC), material, czm_lightDirectionEC);\n' +
//         'gl_FragColor.a=0.85;\n' +
//         '#endif\n' +
//         '}\n'
//     }),
//     show : true
//   });
//   var River3_Material =new Cesium.Material({
//     fabric : {
//       type : 'Water',
//       uniforms : {
//         //baseWaterColor: new Cesium.Color("#008B45"),
//         blendColor: new Cesium.Color("#008B45"),
//         normalMap:'../assets/imageswaterNormals.jpg',
//         frequency: 50.0,
//         animationSpeed:0.01,
//         amplitude: 1000.0,
//         specularIntensity: 1,
//         fadeFactor: 100,
//       }
//     }
//   });
//   var scene = viewer.scene;
//   River3.appearance.material = River3_Material;
//   scene.primitives.add(River3);           //添加到场景
//
// // 下流
//   var River4Point =[
//     118.865801,34.764001,
//     118.876276,34.759026,
//     118.898506,34.732847,
//     118.926144,34.718342,
//     118.957086,34.696112,
//     118.976140,34.691091,
//     118.980732,34.691391,
//     119.010386,34.701133,
//     119.021587,34.701004,
//     119.046650,34.692764,
//     119.056649,34.681091,
//     119.059267,34.679589,
//     119.066434,34.676886,
//     119.091110,34.676714,
//     119.124327,34.685683,
//     119.157658,34.684138,
//     119.171801,34.685501,
//     119.176401,34.684001,
//     119.175701,34.682601,
//     119.172001,34.684702,
//     119.157615,34.682750,
//     119.124284,34.683881,
//     119.091926,34.675083,
//     119.066262,34.675255,
//     119.055662,34.679761,
//     119.045405,34.690919,
//     119.043517,34.691992,
//     119.021502,34.698944,
//     119.010215,34.699030,
//     118.976440,34.689202,
//     118.956699,34.694695,
//     118.924942,34.716625,
//     118.898249,34.730787,
//     118.875246,34.757395,
//     118.865301,34.763002,
//   ]
//   //河道1多边形
//   var polygon4 = new Cesium.PolygonGeometry({
//     polygonHierarchy : new Cesium.PolygonHierarchy(Cesium.Cartesian3.fromDegreesArray(River4Point)),
//     extrudedHeight:0,
//     height:0,
//     vertexFormat : Cesium.EllipsoidSurfaceAppearance.VERTEX_FORMAT,
//   });
//   var River4=new Cesium.Primitive({
//     geometryInstances : new Cesium.GeometryInstance({
//       geometry :polygon4
//     }),
//     appearance : new Cesium.EllipsoidSurfaceAppearance({
//       aboveGround : true,
//       fragmentShaderSource: 'varying vec3 v_positionMC;\n' +
//         'varying vec3 v_positionEC;\n' +
//         'varying vec2 v_st;\n' +
//         'void main()\n' +
//         '{\n' +
//         'czm_materialInput materialInput;\n' +
//         'vec3 normalEC = normalize(czm_normal3D * czm_geodeticSurfaceNormal(v_positionMC, vec3(0.0), vec3(1.0)));\n' +
//         '#ifdef FACE_FORWARD\n' +
//         'normalEC = faceforward(normalEC, vec3(0.0, 0.0, 1.0), -normalEC);\n' +
//         '#endif\n' +
//         'materialInput.s = v_st.s;\n' +
//         'materialInput.st = v_st;\n' +
//         'materialInput.str = vec3(v_st, 0.0);\n' +
//         'materialInput.normalEC = normalEC;\n' +
//         'materialInput.tangentToEyeMatrix = czm_eastNorthUpToEyeCoordinates(v_positionMC, materialInput.normalEC);\n' +
//         'vec3 positionToEyeEC = -v_positionEC;\n' +
//         'materialInput.positionToEyeEC = positionToEyeEC;\n' +
//         'czm_material material = czm_getMaterial(materialInput);\n' +
//         '#ifdef FLAT\n' +
//         'gl_FragColor = vec4(material.diffuse + material.emission, material.alpha);\n' +
//         '#else\n' +
//         'gl_FragColor = czm_phong(normalize(positionToEyeEC), material, czm_lightDirectionEC);\n' +
//         'gl_FragColor.a=0.85;\n' +
//         '#endif\n' +
//         '}\n'
//     }),
//     show : true
//   });
//   var River4_Material =new Cesium.Material({
//     fabric : {
//       type : 'Water',
//       uniforms : {
//         //baseWaterColor: new Cesium.Color("#008B45"),
//         blendColor: new Cesium.Color("#008B45"),
//         normalMap:'../assets/imageswaterNormals.jpg',
//         frequency: 50.0,
//         animationSpeed:0.01,
//         amplitude: 1000.0,
//         specularIntensity: 1,
//         fadeFactor: 100,
//       }
//     }
//   });
//   var scene = viewer.scene;
//   River4.appearance.material = River4_Material;
//   scene.primitives.add(River4);           //添加到场景


// // 影像数据
// var blackMarble = viewer.scene.imageryLayers.addImageryProvider(
//   new Cesium.UrlTemplateImageryProvider(
//     {
//       url:"/Apps/image_tiles/{z}/{x}/{y}.png",
//       minimumLevel: 0,
//       maximumLevel: 13
//     }
//   )
// )



// viewer.dataSources.add(Cesium.GeoJsonDataSource.load('static/sajiawan/新沭河下游t.json', {
//       stroke: Cesium.Color.BLACK,
//       fill:Cesium.Color.TRANSPARENT,
//       strokeWidth: 5.0,
//       markerSymbol: 'circle',
//       markerSize:'24',
//     //   markerColor:Cesium.Color.TRANSPARENT
// }));//geojosn数据


  // viewer.camera.flyTo({
  //   destination: Cesium.Cartesian3.fromDegrees(118.9809, 34.8503, 200000.0),
  //   duration: 0
  // });

//经纬度实时显示
  var longitude_show=document.getElementById('longitude_show');
  var latitude_show=document.getElementById('latitude_show');
  var altitude_show=document.getElementById('altitude_show');
  var canvas=viewer.scene.canvas;
//具体事件的实现
  var ellipsoid=viewer.scene.globe.ellipsoid;

  handler.setInputAction(function(movement){
    //捕获椭球体，将笛卡尔二维平面坐标转为椭球体的笛卡尔三维坐标，返回球体表面的点
    var cartesian=viewer.camera.pickEllipsoid(movement.endPosition, ellipsoid);
    if(cartesian){
      //将笛卡尔三维坐标转为地图坐标（弧度）
      var cartographic=viewer.scene.globe.ellipsoid.cartesianToCartographic(cartesian);
      //将地图坐标（弧度）转为十进制的度数
      var lat_String=Cesium.Math.toDegrees(cartographic.latitude).toFixed(4);
      var log_String=Cesium.Math.toDegrees(cartographic.longitude).toFixed(4);
      var alti_String=(viewer.camera.positionCartographic.height/1000).toFixed(2);
      longitude_show.innerHTML=log_String;
      latitude_show.innerHTML=lat_String;
      altitude_show.innerHTML=alti_String;
    }
  },Cesium.ScreenSpaceEventType.MOUSE_MOVE);



  //在console显示
  console.log(Cesium.Cartesian3.fromDegrees(118.7234,32.0969, 8.4))

}
