<template>
  <div style=" height:570px; width:980px" id="main">
    <el-row>
      <div class="el-header " id="windowHead" style="text-align: right">
        <el-button type="infer" size="mini"  icon="el-icon-minus" id="minusButton" @click="minus" circle></el-button>
        <el-button type="infer" size="mini"  icon="el-icon-rank" id="magnifyButton" @click="magnify" circle></el-button>
        <el-button type="danger" size="mini"  @click="closeChooser"  icon="el-icon-close" circle></el-button>
      </div>
    </el-row>
    <el-row>
      <el-col :span="24">
        <div class="el-container" >
          <el-row style="width: 100%">
            <el-col :span="15">
              <div class="mainContainer">
                <el-row><div class="dataContainer" id="dataContainer">
                  <h1 style="text-align: center">防汛常用水文术语</h1>
                  <h4 style="text-align: left;line-height: 26px" >
                    防汛：汛期防止洪水成灾的各项修守工作。如对堤防、闸、坝、铁路、桥梁等所进行的防守和险情抢修。防汛要本着“以防为主，防重于抢”的原则，把各项防汛工作做好，主要是：汛前检查，报汛，组织抢险队伍，物料准备和技术准备。<br>
                    防洪：研究洪水规律与洪灾特点，并采取各种对策，以尽量减轻或防止洪水危害的一项水利工作。防洪工作的内容，主要有：防洪规划与建设，防洪设施的管理与运用，汛期防守与抢险，洪水预报与调度，灾后工作等。还包括防凌工作。<br>
                    枯水期：又叫枯季。指流域内地表水流枯竭，主要依靠地下水补给水源的时期。枯水期的历时随自然地理及气象条件而定。<br>
                    丰水期：指江河水流主要依靠降雨或融雪补给的时期。一般是在雨季或春季气温持续升高的时期，这时河中水量丰富，延续时间长。<br>
                    径流：由于降水而从流域内地面与地下汇集到河沟，并沿河槽下泄的水流的统称。可分地面径流、地下径流两种。径流引起江河、湖泊水情的变化，是水文循环和水量平衡的基本要素。表示径流大小的方式有流量、径流总量、径流深、径流模数等。<br>
                    地面径流：指降水后除直接蒸发、植物截留、渗入地下、填充洼地外，其余经流域地面汇入河槽，并沿河下泄的水流。地面径流又由于降水形态的不同，可分为雨洪径流与融雪径流。前者是由降雨形成的，后者是由融雪产生的。它们的性质和形成过程是有所不同的。<br>
                    流域平均雨量：又叫面雨量。水文工作中常需推求整个流域面上的平均降雨量。最常用的方法是算术平均法和垂直平分法（又叫做泰森多边形法），也有用绘制等雨量线图来推求的。<br>
<!--                    降雨强度：单位时段内的降雨量。以毫米/分或毫米/时计。我国气象部门一般采用的降雨强度标准为：小雨：12小时内雨量小于0.2-5毫米，或24小时内雨量小于10毫米；中雨：12小时内雨量为5-15毫米，或24小时内雨量为10.1-25毫米；大雨：12小时内雨量为15.1-30毫米，或24小时内雨量为25.1-50毫米。-->
<!--                    暴雨：一般指势急量大的降雨。是一种灾害性天气。暴雨的定量标准，各地并不一致，视具体情况而定。气象上大致规定暴雨按强度分三级：-->
<!--                    暴雨：12小时雨量等于和大于30.1-70毫米，或24小时雨量等于和大于50.1-100毫米；-->
<!--                    大暴雨：12小时雨量等于和大于70.1-140毫米，或24小时雨量等于和大于100.1-200毫米；-->
<!--                    特大暴雨：12小时雨量等于和大于140毫米，或24小时雨量大于200毫米。-->
<!--                    洪水预报：是指上游出现雨情后，预测将来可能发生的水情变化，以指导防洪工程的调度运用，做到及时蓄水、泄水、确保安全的目的。-->
<!--                    堤：沿江、河、湖、海的边岸修筑的拦水设施。它的作用是防止洪水泛滥，保障工农业生产和人民的安全。建在江、河两岸的叫“江堤”或“河堤”；建在海边的叫“海堤”或“海塘”。渠道两边的渠帮，也常称为“渠堤”。-->
<!--                    堤防工程：在平原地区采用沿河两岸修筑堤坝的办法来挡御洪水泛滥而造成灾害。它是一种挡水工程设施。堤防工程包括：江堤、河堤、海堤、圩堤等。-->
<!--                    护坡：在河道岸坡上用块石或砼铺砌以保护河岸的建筑物为护坡。护坡又可为砼、浆砌块石、干砌块石坡等。按下坎是否有桩可分为下有桩、无桩护坡。-->
<!--                    高桩承台驳岸：一般称基础前（后）排有板桩，后（前）排有桩，其上有钢筋砼承台，且承台设臵比较高驳岸为高桩承台驳岸。-->
<!--                    重力式驳岸：依靠墙身自重来保证墙身稳定的驳岸称为重力式驳岸。它又可分成好多类，如：基础有桩、无桩（砼或浆砌块石）重力式驳岸，后倾重力式驳岸等。-->
<!--                    板桩式驳岸：板桩式驳岸又称拉锚板桩式驳岸，顾名思义，此类驳岸由垂直打入土中板桩（钢或钢筋砼板桩）和水平张拉粗钢筋及锚碇系统组成。-->
<!--                    驳岸：沿河地面以下，保护河岸（阻止河岸崩塌或冲刷）的建筑物为驳岸（护坡）。-->
<!--                    水位：河流或其他水体的自由面，距离某一基准面零点以上的高程。我市常用的基准面为吴淞基面。其他还有56年黄海基面、85年黄海基面，废黄河基面等。-->
<!--                    保证水位：又称最高防洪水位或危害水位。系指堤防设计水位或历史上防御过的最高水位。-->
<!--                    警戒水位：当水位持续上涨达到某一水位，防洪堤防随时可能出险，此时应严加防守，随时准备投入抢险，这一水位定为警戒水位。-->
<!--                    防汛抢险有关术语-->
<!--                    汛：是指江、河、湖等水域在年内定期性涨水的现象。而汛期是根据季节的不同分为：春汛（又称桃花汛），伏汛、秋汛、凌汛（又称冬汛）。-->
<!--                    洪：是指降雨、融雪、冰凌及热带气旋风暴、山洪、泥石流而引起巨大暴涨的水流，使江、河、湖水位上升到一定位臵，将会给人民生产、生活带来灾害的现象。-->
<!--                    防洪技术：是水利工程管理技术在洪水条件下的综合运用，以避免或减轻洪水危害，其内容有洪水测报、预报、警报、洪水调度、工程抢险、分滞洪区的运用、防洪风险分析和防洪保险等。-->
<!--                    防汛抗洪：是指国家动员和组织全社会力量，为了避免或减轻洪水给社会带来的灾害损失所进行各项活动的总称。-->
<!--                    洪水预报：根据洪水形成和运动规律，利用前期和现时出现的水文气象因素对未来一定时段内的洪水情况的科学预测。-->
<!--                    水库：把坝上游集水面积内的雨水拦蓄起来，以调节天然径流，为防洪、灌溉、供水、发电等服务的工程。水库根据蓄水量分为大型水库（总库容超过1亿立方米）、中型水库（总库容1千万立方米—1亿立方米）、小型水库（总库容1百万立方米—1千万立方米）。-->
<!--                    土坝：是指坝体由单种或多种土料压实填筑而成的挡水建筑物。-->
<!--                    均质土坝：是指坝体由透水性较小，性质基本相同的粘性土料筑成。通常用壤土或砂质粘土。-->
<!--                    堤防：是指一般用土料压实筑成的挡水建筑物，建在江两岸的叫江堤，河两岸叫河堤、湖洼周边的叫湖堤，海边的叫海堤或海塘，圩洼周边的叫圩堤。-->
<!--                    险情：是在大汛期或平时高水位时，水压力、流速和风浪加大，各类水工建筑物均有可能因高度、强度不足或存在隐患和缺陷而出现危及建筑物安全的现象。-->
<!--                    剪切破坏：土体内部都有一个滑动面，沿这个滑动面，一部分土体（滑动体）相对于另一部分土体发生滑动，而使建筑物遭到破坏。形成土的抗剪破坏能力的因素，包括土的内摩擦力和凝聚力。-->
<!--                    内磨擦力：是指土的固体颗粒相互滑动的阻力，包括土料之间的表面摩擦力和颗粒间犬牙交错的咬合力。-->
<!--                    凝聚力：指土的粘性来源于土粒间粘聚在一起而不散开的粘聚力。-->
<!--                    渗透：在水压力的作用下，水在土体孔隙中的运动称为渗透。-->
<!--                    渗透破坏：指土层在渗透水压力作用下发生的变形和破坏现象。-->
<!--                    浸润线：渗透水流水面与主坝横部面的交线叫浸润线，浸润线又是坝体内渗透水流使坝体内形成上干下湿的分界线。-->
<!--                    散浸：坝体背水坡因渗透土体过份湿润或发软，甚至会有水不断地逸出的现象。-->
<!--                    管涌：是在一定的水力梯度的渗透作用，砂砾土中的细颗粒在孔隙的孔道中发生移动，并被水流带出基础以外的现象。-->
<!--                    流土：是粘性土或非粘性土在渗透水流的水力梯度进一步增大，使坝基的局部主体表面隆起或大块土体松动而随渗水流失的现象。-->
<!--                    漏洞：在高水位情况下，坝的背水坡及坡脚附近出现横贯坝、堤身或基础的渗流孔洞，称为漏洞。-->
<!--                    裂缝：是指坝体的表面或内部出现开裂现象。按其走向可分为横向裂缝、纵向裂缝、龟纹裂缝；按其成因可分为：不均匀沿陷裂缝、滑坡裂缝、干缩裂缝、冰冻裂缝和振动裂缝，其中以横向裂缝和滑坡裂缝危害性较大。-->
<!--                    塌坑：在持续高水位情况下，在坝的顶部、迎水坡、背水坡及其坡脚附近突然发生局部下陷而成的险情。-->
<!--                    坍塌：是指堤坝临水面土体因供水流暴涨骤降或水位冲刷而成的土体崩落的重要险情。-->
<!--                    滑坡：主要指坝体边坡失稳，土体的下滑力超过了抗滑力而造成的主体滑动的险情。-->
<!--                    反起层：指在背水坡脚用1-3层不同粒径的非粘土，通常用砂、砾、碎石等构成，其粒径随渗流方向而增大，其目的是防止堤坝坡脚处发生管涌或水流冲刷破坏。反滤层必须符合下列条件：（1）某一层的颗粒，不应穿过粒径较大一层的孔隙；（2）每一层内颗粒不该发生移动；（3）反滤层不应被淤塞。-->
<!--                    溢洪道：是指用以宣泄超过水库调蓄能力的洪水，以保证挡水建筑物的安全。-->
<!--                    非常溢洪道：是指出现超过校核频率的洪水，为保证挡水建筑物的安全，以便在非常情况下加大泄洪能力而设臵的溢洪道。-->
                  </h4>


                </div></el-row>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="tableContainer" style="width: 360px" id="tableContainer">
<!--                <RiverInfoTable style="height: 100%;width: 100%" id="infoTable" ref="RiverInfoTable"/>-->
              </div>
            </el-col>
          </el-row>

        </div>
      </el-col>
    </el-row>


  </div>
</template>

<script>
  import  "../assets/js/jquery.table2excel.min.js"
  import RiverInfoTable from "./waterInfoTable/RiverInfoTable";
  export default {
    name: "waterInfo",
    components:{
      RiverInfoTable
    },
    methods:{
      loadSTInfo:function(){
        this.axios.get("http://localhost:8083/waterInfo/RSVR/ST").then(
          response=>{
            console.log(response.data);
            this.searchParameter.options=response.data;
          }
        );
      },
      //初始Echarts
      initEcharts:function(){
        let _this=this;
        this.myChart=this.$echarts.init(document.getElementById("echartsContainer"));
        this.myChart.setOption(option);
      },

      //关闭窗口
      closeChooser:function () {
        this.$emit('closeChooser')
      },
      //销毁
      removeEchart:function () {
        this.myChart.dispose();
        this.myChart=null;
      },

      //放大
      magnify:function () {
        let main =document.getElementById("main");

        main.style.height="100%";
        main.style.width="100%";
        main.style.left='0px';
        main.style.top='0px';
        document.getElementById("echartsContainer").style.height='500px';
        this.myChart.resize();
        document.getElementById("tableContainer").style.height='650px';
        document.getElementById("tableContainer").style.width='500px';
        document.getElementById("minusButton").style.display='inline-block';
        document.getElementById("magnifyButton").style.display='none';

      },
      //缩小
      minus:function () {
        let main =document.getElementById("main");

        main.style.height="570px";
        main.style.width="980px";
        main.style.left='260px';
        main.style.top='100px';
        document.getElementById("echartsContainer").style.height='390px';
        this.myChart.resize();
        document.getElementById("tableContainer").style.height='525px';
        document.getElementById("tableContainer").style.width='350px';
        document.getElementById("minusButton").style.display='none';
        document.getElementById("magnifyButton").style.display='inline-block';
      }
    },
    mounted() {
      this.loadSTInfo();
      this.initEcharts();
      this.loadData();
      let _this =this;
      let main = document.getElementById("main");
      let dataContainer = document.getElementById("dataContainer");
      let head= document.getElementById("windowHead");
      dataContainer.onmousedown= function(event){

        _this.fndown(main,event,dataContainer);
      }
      head.onmousedown= function(event){

        _this.fndown(main,event,head);
      }

    }
  }
</script>

<style scoped>
  .dataContainer{
    padding-left: 10px;
    /*padding-top: 15px;*/
  }
  .dataContainer>*{
    margin: 5px;

  }
  #echartsContainer{
    margin-left: 20px;
    margin-top: 5px;

    height:390px;
  }
  .mainContainer{
    position: relative;
    width: 150%;
    height: 520px;
    display: inline-block;

  }
  .tableContainer{

    /*width: 270px;*/
    height: 525px;
    width: 100%;
    display: inline-block;
    margin-bottom:10px ;
    padding-left: 5px;
  }
  .left{
    position: absolute;
    right: 5px;
  }
  .buttonContainer{
    position: relative;
    margin-top: 5px;
  }
  .right{
    position: relative;
    right: 5px;
  }
  #minusButton{
    display: none;
  }

</style>
