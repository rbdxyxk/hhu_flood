<template>
  <!--洪水预报的frame-->
  <div id="frame_div" v-show="hasShow">
<!--    <button type="button" class="btn btn-primary"-->
<!--            onmousedown="mouseDown()" onmouseup="mouseUp()">按住移动-->
<!--    </button>-->
<!--    <button  type="button" class="btn btn-primary">关闭窗口</button>-->
    <button type="button" class="btn btn-primary" id="toMouse">按住移动
    </button>
    <button  type="button" class="btn btn-primary"
            @click="closeWindow">关闭窗口</button>
    <router-view v-if="justShow"></router-view>
<!--    <iframe id="frame" src="" style="width: 80%;height: 80%;"></iframe>-->
  </div>
</template>

<script>
export default {
  name: "ForecastContainer",
  data(){
    return{
      hasShow: true,
      justShow:true
    }
  },
  methods:{
    // 为了刷新组件
    controlRouterLink(){
      this.justShow = false;
      setTimeout(() => {
        this.justShow = true;
      })
    },
    // 关闭
    closeWindow(){
      this.$router.push('/');
      this.hasShow = false;
    },


    //移动相关
    fndown:function(main,event,container,){
      let _this = this;
      event = event||window.event;
      let disX = event.clientX - main.offsetLeft,//记录鼠标相对于矩形的位置
        disY =event.clientY - main.offsetTop;
      document.onmousemove = function(event){

        event = event||window.event;
        _this.fnMove(event,disX,disY,main)
      };
      container.onmouseup = function(){//松开鼠标后无法拖动

        document.onmousemove = null;
        container.onmouseup = null ;
      }


    },
    fnMove:function(event,disX,disY,main){//鼠标移动事件
      let l = event.clientX - disX,//根据鼠标的位置改变，来改变矩形相对于body的位置
        t = event.clientY - disY;
      main.style.left=l+'px';
      main.style.top=t+'px';
    },
  },
  mounted() {
    let _this =this;
    let mainRain=document.getElementById("frame_div");
    let rainDataContainer=document.getElementById("toMouse");
    rainDataContainer.onmousedown= function(event){
      _this.fndown(mainRain,event,rainDataContainer);
    }

    this.$bus.$on('refreshComponent',this.controlRouterLink)

  }

}
</script>

<style scoped>
#frame_div{
  position: fixed;
  top: 17%;
  left: 15%;
  margin: auto;
  width: 85%;
  height: 83%;
  display: block;
  background-color: white;
  overflow-y: scroll;
}

</style>
