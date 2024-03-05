import FlyInCzml from "./FlyInCzml";
export default FloodForecast
// FloodForecast.AAA();
 function FloodForecast() {

//以下属性先不用管，等我们在下面定义方法的时候，需要用到什么属性，在来定义全局变量（属性）就可以了
  var startX;
  var startY;
  var moveSwitch = false;
  var currentLeft;
  var currentTop;
  var loginTag;

  function mouseWith(){
    loginTag = document.getElementById("frame_div");

    loginTag.onmousemove = function(e){　　　//定义一个方法，用来捕捉鼠标的坐标位置
      if(moveSwitch){　　　　　　　　　//类似于if(true);　　　　　　　　　　　　　　　
        var x = e.clientX;　　　　　　　　//e.clientX是一个触摸事件，即是鼠标点击时的X轴上的坐标
        var y = e.clientY;　　　　　　　　//e.clientY是一个触摸事件，即是鼠标点击时的Y轴上的坐标
        var distanceX = x-startX;　　　　//X轴上获得移动的实际距离
        var distanceY = y-startY;　　　　　//Y轴上获得移动的实际距离
        loginTag.style.left = (distanceX+currentLeft)+"px";　　//currentLeft下面的方法会有解释，需要注意最后要添加PX单位，如果给left赋值会破坏文档流，不加单位就会无效
        loginTag.style.top = (distanceY+currentTop)+"px";　　//
      }
    }
  }

  function mouseDown(e){　　　　//鼠标按下事件
    e = e?e:window.event;　　　　//因为兼容问题，event可能在隐藏参数中，如果隐藏参数没有event事件，则可以使用全局的事件window.event（大家记住写法就可以了）
    moveSwitch = true;
    startX = e.clientX;
    startY = e.clientY;
    currentLeft = loginTag.offsetLeft;　　//通过对象获取对象的坐标
    currentTop = loginTag.offsetTop;
  }
  function mouseUp(){
    moveSwitch = false;
  }

// 关闭/显示窗口
  $('#hideFrame').click(function () {

    $('#frame_div').hide();


  });
  // function AAA() {
  //  alert("AAA");
  // }

  function end() {
    // FlyInCzml.endFly_Czml();
    // FlyInCzml.endFly_Advance();

    // 退出栅格
    $("#forkImg").click();

    // 隐藏水情信息
    $("#closeInfo").click();

    // 隐藏溃坝
    $('#ContolDataForm').css('display','none');
  }
  $('#Forecast_Index').click(function () {
    console.log('Forecast_Index~~');

    end();
    $('#frame_div').show();
    mouseWith()

    $('#frame').attr("src", "http://localhost:9999/default.aspx");
  })
  // $('#Forecast_Index').click(function () {
  //   console.log('Forecast_Index~~');
  //   alert("a")
  //   end();
  //   $('#frame_div').show();
  //   mouseWith()
  //
  //   $('#frame').attr("src", "http://localhost:9999/default.aspx");
  // });

  $('#Forecast_Manual').click(function () {
    console.log('Forecast_Manual()~~');
    end();

    $('#frame_div').show();    mouseWith()
    $('#frame').attr("src", "http://localhost:9999/Forecast_manual.aspx");
  });

  $('#Forecast_AnaResult').click(function () {
    console.log('Forecast_AnaResult()~~');
    end();
    $('#frame_div').show();
    mouseWith()
    $('#frame').attr("src", "http://localhost:8080/Cesium_Apps/page/AnaResult.html");
  });
  $('#Forecast_Para').click(function () {
    console.log('Forecast_Para()~~');
    end();
    $('#frame_div').show();    mouseWith()
    $('#frame').attr("src", "http://localhost:9999/Para_manage.aspx");
  });

  $('#Forecast_AutoRunCal').click(function () {
    console.log('Forecast_AutoRunCal()~~');
    end();
    $('#frame_div').show();    mouseWith()
    $('#frame').attr("src", "http://localhost:9999/AutoRunCal.aspx");
  });

  $('#Forecast_OutPutResult').click(function () {
    console.log('Forecast_OutPutResult()~~');
    end();
    $('#frame_div').show();    mouseWith()
    $('#frame').attr("src", "http://localhost:8080/Cesium_Apps/page/OutPutResult.html");
  });

}
