# 石梁河水库洪水预报系统
本项目用于石梁河水库的洪水预报，通过读取数据库中参数，利用新安江水文模型，根据雨量数据计算径流量，从而预报洪水的发生；此外还包含自动定时，三维巡航等功能，集成了Cesium三位地图，便于展示。

## 项目介绍
### 前端
#### （一）前端主页面为App.vue
   同其他vue项目一样，以main.js为启动文件，经该启动文件引入App.vue，以及router/index.js，在index.js中加入earth.Vue等组件。earth.Vue为本项目各功能菜单组织页。
   
   ![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/73c3b03c-767a-40fd-9e51-473b7ca7e683)
   
    首先引入cesium。 
    
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/d2b29eab-ab97-448a-a987-09363bec40d6)

第46行到第156行，对应页面图层功能，点击图层显示，显示十二种水利工程，此处以报讯站为例。

![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/a7435153-0873-4373-a75f-9ed2581b013b)

第174行到第187行，对应图层显示和隐藏。点击图层显示是显示十二个图标且按钮改为图层隐藏，点击图层隐藏，图层消失，按钮回到图层显示。

![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/af2d5da4-c621-45e5-9273-7925a34a24d9)
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/fa963bc4-16d8-4f0b-aeae-4be0c4530f8a)

第199行到第272行，对应页面上层菜单栏，功能分为放大，缩小，测距，测面，清空和全屏（退出全屏）。

![Uploading image.png…]()

从第284行开始对应页面左侧菜单栏。

 ![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/34d780eb-c658-46a1-a2be-9d52d2c7b50c)
 
从第305行到第338行对应菜单栏中雨水情功能，其中分为雨情信息，水情信息和水库水情。

![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/5cd29318-a071-4638-9bd7-87cc01c9241a)

从第340行到第383行对应菜单栏中洪水预报功能，其中分为预报首页，人工干预，预报结果，预报参数，自动定时和成果展示。

![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/19daf6f0-2579-4b83-ad8e-4a0d7255c84e)

从第385行到第419行对应菜单栏中工程安全功能，其中分为观测分析，结构分析和综合分析。

![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/1442bbe3-3b0d-45ef-ba2d-4445b1552856)

从第420行到第454行对应菜单栏中三位巡河功能，其中分为线路设置和沿程巡河。

 ![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/85e556dd-b51a-4ff1-b2a5-dcbbe859277f)
 
从第455行到第488行对应菜单栏中三位巡河功能，其中分为线路设置和沿程巡河。

![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/4223adaa-58a9-452c-9e8b-25f434e4b75c)

从第489行到第531行对应菜单栏中防汛工具箱功能，其中分为降雨量级，库容或流量，泄流量，产流量，纳雨能力和入库流量。

![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/c53ef218-cbb8-4c4f-ac73-7e94fc7af7b6)

从第532行到第588行对应菜单栏中防汛知识库功能，其中分为河流水系，水利工程，洪涝灾害，洪水复盘，防汛手册，应急预案与调度规则，法律法规与值班制度和防汛常用水文术语。

![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/87f75e3d-4448-4347-a42c-a288706ed010)

第620行开始显示雨情信息。

![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/b77cf14c-b9b4-4147-83ff-a545952a6644)

第726行开始水情信息的页面。

![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/08065708-94af-40ee-a2f1-f2f0bbe182ff)

第827行开始三维巡河线路设置菜单。

![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/9590eaed-e8e6-437b-b6bd-8035b54b92ac)

第1017行开始显示页面最下面一块区域的经纬度及视角高。

![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/137ad3c8-6fbf-4a97-9a7c-eaf5de59b04a)

第1031行开始飞行途径区域提示信息框。

![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/a49d6547-9290-46e0-ac3a-505bc433d3fc)

绑定左侧菜单栏中每个小菜单的弹窗。

弹窗首先在功能处加上标签，v-if或者v-for控制，再在vue下导入。

![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/b3757666-1f40-45cd-a3a4-92d233da5f77)

从第1245行开始进行cesium的基础设置。

![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/69738b12-152f-477d-aa32-164ab2edac9e)

对数据进行切片并利用影像图层加载的方式放在图层上，如若需要地形切片方法相同。

![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/7ce8e451-2da9-4972-a057-b4fc5568dce9)

设置页面初始视角。
#### （二）Src/assets/js
菜单上用到的各个功能在 Src/assets/js 中
1.	geomap.js
 ![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/47bb539f-9473-40dd-9411-7cf651fa608f)
设置图层显示和图层隐藏切换的代码，与earth.vue中对应对象进行功能相绑定。earth.vue中引用的对应代码为import geomap from “../assets/js/geomap”
十二种水利工程点击展示json或geojson格式的数据。
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/1e9803af-08e4-4238-9b74-3de1c794f9d9)
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/8e13babc-439b-4eb6-876a-b016540fdb26)
 以水闸为例，点击图标展示区域内部所有水闸测站，再点击一次测站消失，点击后展示效果如上图，点击图标会显示当前测站数据。与earth.vue中对应功能相绑定。
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/22d78221-a0e8-48ac-b72d-04f9f0c3735b)
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/1d0f2258-0ae8-4ff3-951a-cb6ab33631d6) 
图层上三种地图形式转换，map-yx对应影像图层，map-sl对应矢量图层，map-dx对应地形图层，在主页面的右下角显示。与earth.vue中对应功能相绑定。
2.	ground.js
水库区域的蓝色河道多边形覆盖，并设置上下游及水面流动形式。
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/cdd13b06-4cf7-4024-8b11-d7fa85259a8e)
分上流和下流两块，利用Cesium.PolygonGeometry，收集经纬度形成多边形，并设置参数，透明度等。
3.	Menu.js
页面上层菜单栏对应功能展示。
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/e14e1c7f-7c9c-47d6-bd91-ae516e803e6e)
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/e12d9307-441a-43a7-9f05-3ebf626a5f79)
第10行到第20行，控制上层菜单的显示和折叠，与earth.vue中对应功能相绑定。
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/b2e3509f-2bbf-4c09-9f63-8fdbee253b6f)
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/353e0a37-b1d1-4b8f-987a-a6e95f378138)
第22行到第30行，对应上层菜单的放大和缩小功能，与earth.vue中对应功能相绑定。
第32行到第181行，对应上层菜单的测距功能，与earth.vue中对应功能相绑定。左击鼠标选择两个点，鼠标右键确定，测的距离贴地且单位是千米。
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/1def5883-02b5-44ec-9be2-173e44abf0b7)
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/d9a77676-e801-43fa-bf66-4ba31b3fd7b0)
第183行到第323行，对应上层菜单的测面功能，与earth.vue中对应功能相绑定。左击鼠标选择多个点，鼠标右键确定，形成一个多边形面，测的距离贴地且单位是平方公里。
第325行到第328行，对应上层菜单的清空功能，与earth.vue中对应功能相绑定。removeall所有实体。
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/01ca076d-1fc7-4623-90a6-a529dd263372)
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/12bbeeb5-db8f-4623-87d7-280033720599)
第329行往后，对应上层菜单全屏和退出全屏功能，需要适配不同浏览器，与earth.vue中对应功能相绑定。
4.	Anaresult.js
配置echarts信息。
5.	Cesium-vendor.js
配置cesium信息。
6.	FlyInAdvance.js和FlyInCzml.js
三维巡河相关操作代码。FlyInAdvance对应左侧菜单线路设置，FlyInCzml对应左侧菜单沿程巡河。与earth.vue中对应功能相绑定。
7.	OutPutResult.js
水情信息模块相关展示和输出的代码。

#### （三）Src/assets/css样式表
该目录下给了各对象样式定义，如右下角地图选择按钮的样式设置，其样式定义在mneu1.css文件里，关键字为#mapType,并对其每个子对象的样式进行了定义，包括#mapType .vectorType、#mapType .imgType、#mapType .terType等子对象。
menu1.css、menu.css、menu3.css等样式在earth.vue下的<script> import进行了导入，应用earth.vue的各层、各容器、各对象等。
 ![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/8cb4de94-a15a-44e0-9e6e-89a13f242af8)
图 mapType对应的样式
#### （四）Src/components
1.	FLoodPreventionKit/echarts/riverinfoechart
echart图表代码，可自行编写echart结构。
2.	FLoodPreventionKit/RainDataShow.vue
雨量信息图表展示方法。
3.	FLoodPreventionKit/RainStChooser.vue
水库和雨量站关系echart展示选择方法，对应在雨水情表单中。
4.	waterInfoTable/RiverInfoTable.vue
    对应以雨水情界面为例，显示在弹窗的右侧，可以展示图表数据或折线数据。
5.	waterInfoTable/RiverInfoTable2.vue
和上页面效果相同，还未修改，显示在弹窗的右侧，拟展示文字格式。
6.	chooser.vue
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/3c31a181-f7c2-4199-a5aa-49c51fc8a6c0)
以溃坝分析举例，提供提交和新增项目的选择。
7.	emergencyplan.vue
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/df9472ae-de8b-40f9-b383-a32d9c5489af)
对应防汛知识库中的应急预案与调度规则。
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/39e4f662-67af-4801-8825-f4569ee7a19a)
通过closechooser 来和earth.vue中的页面绑定。绑定为如下顺序：
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/b12ed139-ce40-4162-a4fc-c56c1f8660d9)
先在earth中为指定区域绑定页面，设置为true。
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/b6150c79-15b5-48cb-8cb8-aacac7c0dc2e)
设置绑定的参数，点击控制显隐。
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/62293b0c-6208-4232-8fcd-4b424a4f228d)
导入。
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/4ff10069-6d9f-4582-8c17-41a77c7324ca)
设置完毕。
 ![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/59114f75-f629-416b-b54a-673f0cdebca5)
页面上的关闭以及放大缩小等控制操作。
8.	floodfang.vue
   ![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/622eb7d6-c49e-4024-8461-ff4d1e6d8d38)
对应防汛知识库中的防汛手册。页面上操作与其他防汛知识库页面相同，以7为例。
9.	floodfu.vue
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/0d9ea848-3480-4171-8d85-c2b4d68fd41b)
对应防汛知识库中的洪水复盘。页面上操作与其他防汛知识库页面相同，以7为例。
10.	Floods.vue
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/4504eb2f-bf1a-462d-bcf9-e89933a75f49)
对应防汛知识库中的洪涝灾害。页面上操作与其他防汛知识库页面相同，以7为例。
11.	Hydterms.vue
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/233458f7-31e3-4000-b551-4b3933b6449e)
对应防汛知识库中的防汛常用水文术语。页面上操作与其他防汛知识库页面相同，以7为例。
12.	Laws.vue
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/007b200e-71e8-4741-931f-3117acf97e22)
对应防汛知识库中的法律法规与值班制度。页面上操作与其他防汛知识库页面相同，以7为例。
13.	rainwater.vue
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/ac74b922-d9de-48d7-8507-4c0151616381)
对应防汛知识库中的河流水系。页面上操作与其他防汛知识库页面相同，以7为例。
14.	waterku.vue
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/dbb39595-d5e2-4ff1-b539-964003308a0f)
对应防汛知识库中的水利工程。页面上操作与其他防汛知识库页面相同，以7为例。
15.	parameters.vue
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/1be0d77f-8ee8-40a9-a0d0-13bf3790c963)
控制溃坝分析里面的四个小窗口，绑定好Controlparameters.vue，Formatparameters.vue，Processparameters.vue，Timeparameters.vue四个页面。
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/d71d3ee9-e849-4089-aee5-e5ed942edd7c)
16.	Controlparameters.vue
 ![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/3ff14ffd-ffa1-46ca-ae1e-c29a9467af1b)
对应溃坝分析里控制参数窗口，其中各个数值。
17.	Formatparameters.vue
 ![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/2210e3a6-6ae9-41eb-821a-96c262758ea1)
对应溃坝分析里公式及数据格式参数窗口，其中各个数值。
18.	Processparameters.vue
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/75db5d5e-30a2-4b8f-a269-166d485d6ce4)
对应溃坝分析里过程线参数窗口，其中各个数值。
19.	Timeparameters.vue 
对应溃坝分析里时间及阈值参数窗口，其中各个数值。
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/9ef63bdc-c1ee-4829-b8f5-bcc3b476e017)
20.	RainInfo.vue
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/4b68c7ca-7954-4714-8e5c-17bdb0988553)
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/68e4ce32-0c32-4780-b7c1-f393b9a9c0fd)
在earth.vue上绑定好，对应左侧菜单雨水情中的雨情信息。
选择雨情测站有对应的水库测站。选择起始时间和终止时间，点击查询可以看到图。下面划分了一个区域用echart表示雨量和库上水位及库下水位。可以单独点击雨量和库上水位及库下水位控制数据显隐，展示方式为折线和柱状图。可以导出也可以关闭页面。右上角按钮也可以全屏或者关闭页面。
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/d8d5a53b-c73a-4248-96f8-7ea6fe301a5a)
控制页面窗口的移动，按住左键可拖动窗口，松开后无法拖动。
21.	RiverInfo.vue
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/30ed01d6-36fc-4c0d-81d2-d150ffe5cbe4)
选择测站，选择起始时间和终止时间，点击查询可以看到图。下面划分了一个区域用echart表示测试站的数据，有流量和水位的对应信息。可以单独点击流量或者水位控制数据显隐，展示方式为折线和柱状图。可以导出也可以关闭页面。右上角按钮也可以全屏或者关闭页面。其余功能与20相似。
22.	RsvrInfo.vue
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/8ca3ccd2-ac3e-4fb2-8cdd-ef8d06d09708)
选择测站，选择起始时间和终止时间，点击查询可以看到图。下面划分了一个区域用echart表示测试站的数据，有库上水位，入库流量，蓄水量，库下水位和出库流量的对应信息。可以单独点击库上水位，入库流量，蓄水量，库下水位和出库流量控制数据显隐，展示方式为折线和柱状图。可以导出也可以关闭页面。右上角按钮也可以全屏或者关闭页面。其余功能与20相似。
#### （五）Static
1.	image_tiles
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/1096d868-9925-41ee-a1b6-d9940f189258)
利用cesiumlab处理的切片数据。可以看到地形的起伏，配置方式和加载形式见cesium的viewer.scene.imageryLayers.addImageryProvider。
2.	Images
所有图片数据。
3.	sajiawan
处理好的json和geojson数据文件，以及区域案例的shp等格式数据。
   
#### （六）idea开发环境配置说明
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/bf72bf8f-9057-40b3-b002-13066811a105)
前端页面需要node.js并使用webpack方式打包；从gitte仓库中下载Floodforecast项目，安装配置
运行的端口、IP，起始页（或启动文件调用过程），见package.json，使用本地主机IP访问语句为host 0.0.0.0；访问端口设置于文件config\index.js
在idea启动方式：在控制台（terminal）执行命令npm run dev

#### （七）系统环境配置
java8,mysql8及以上
使用springboot框架，前端使用vue结合js和css，后端数据端使用mybatis-plus。

### 后端
#### （一）src/main/java/cn.hhu/Bean
bean包就是专门放置属性类的，比如说你在数据库中创建了一个表，那么你可以把这个表的各个字段，分别定义成属性放置在一个类里，并写明setter和getter方法和构造器等。
![image](https://github.com/rbdxyxk/hhu_flood/assets/97138889/7836ea3c-3495-4e63-a150-0a3320dede0b)
Bean下都是需要的实体类，有些类与数据库中一一对应。

#### （二）src/main/java/cn.hhu/config
放置配置信息。
Config文件是通过各种程序使用的通用配置文件。它包含的设置和配置信息以及不同的程序可以以不同的格式存储这些数据。

#### （三）src/main/java/cn.hhu/control
控制层负责接收参数，调用相关业务层，封装数据，把用户提交来的请求通过对URL的匹配，分配个不同的接收器，再进行处理，然后向用户返回结果。
#### 1.CA_PDController
与ca_pdService相呼应，对ca_pd里的数据进行处理。

```java
@Controller
@RequestMapping("/CAPD")

public class CA_PDController {
    @Autowired
    CA_PDService ca_pdService;

    @RequestMapping("getAll")
    @ResponseBody
    public List<CA_PD> getAll(){
       return ca_pdService.getAll();
    }
}
```

getAll对应chooser.vue中，获取所有测站。引入axios，通过get方法获取数据。

```js
 mounted: function () {

        this.axios.get("http://localhost:8083/CAPD/getAll").then(
          response=>{
            // console.log(this.props)

            this.selectData=response.data;
          }
        );
      },
```



 #### 2. ContrlParametersController

```java
@Controller
@RequestMapping("/parameters")
public class ContrlParametersController {

    @Autowired
    ControlParamterService cps;
    @PostMapping("addAll")
    @ResponseBody
    public String addAll(@RequestBody String s) {
        try {
            cps.insertAllParameters(s);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "true";

    }
    @GetMapping("CAPD/{ID}")
    @ResponseBody
    public String selectAllById(@PathVariable("ID") Integer ID){
        return cps.getAllParameterById(ID);
    }
}

```

addAll对应parameters.vue中addall，添加所有测站。

```js
   this.axios.post("http://localhost:8083/parameters/addAll",data).then(
          response=>{
            console.log(response.data);
            this.$emit('changeParameters',false);
          }
        );
```

CAPD/{ID}对应parameters.vue中CAPD+id，根据测站id选择测站。

```js
    this.axios.get("http://localhost:8083/parameters/CAPD/"+ID).then(
          response=>{
            console.log(response.data);
            ///alert()
            this.$refs.ControlParameters.setData(response.data.ControlParameters);
            this.$refs.ProcessParameters.setData(response.data.checked,response.data.cd_pline);
          }
        );
```



#### 3.getDataLineController

实现过程线数据展示。

```JAVA
@RestController
@RequestMapping("line")
public class getDataLineController {
    private static final Log log = LogFactory.getLog(getDataLineController.class);

    @Autowired
    ShowOutputService showOutputService;

    @RequestMapping("data2")
    //获取数据
    public List<Output> getData2(@RequestParam int TimeInterval,
                           @RequestParam String positions,
                           @RequestParam double startX,
                           @RequestParam double startY,
                           @RequestParam double endX,
                           @RequestParam double endY){
        List<Output> outputs = showOutputService.getOutputs(0, TimeInterval, positions, startX, startY, endX, endY,100,18);
        log.warn(outputs);
        return outputs;
    }

    @GetMapping("getMaxInterval")
    public Integer getMaxInterval(){
        return showOutputService.getMaxInterval(0);

    }

    @PostMapping("oneOutput")
    public Output oneOutput(@RequestParam int TimeInterval,
                          @RequestParam String positions,
                          @RequestParam double x,
                          @RequestParam double y){
        return showOutputService.getOutputByLongitudeAndLatitude(TimeInterval,positions, x ,  y);

    }
}
```



### 4.CruiseController



### 5.RainInfoController

```JAVA
@Controller
public class RainInfoController {
    @Autowired
    ST_PPTN_RService st_pptn_rService;
    //获取所有新的测站
    @GetMapping("/pptn/st")
    public List<ST_STINFO_B> getAllNewRainSTNMAndSTCD(){

        return st_pptn_rService.getAllSTCDAndSTNM();
    }
    @GetMapping("/pptn/{tm}/querries")
    @ResponseBody
    public Map getAllRainInfo(@RequestParam("list") String[] list,
                              @PathVariable("tm") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDateTime
                                 ){

        return st_pptn_rService.searchAllByStcdAndTm2Map2(list,startDateTime);
    }


}

```

对应FloodPreventionKit下面的Rainstchooser.vue页面中，pptn，获取雨量信息。

```js
//获取所有的雨量数据
getRainData(){
  this.axios.get("http://localhost:8083/pptn/"+moment(this.searchParameter.startDateTime).format('YYYY-MM-DD HH:mm:ss')+"/querries?list="+this.checked_rain_stations).then(
    response=>{
      this.openRainDataShow(response.data);
    }
  )
},
```

### 6.STTestInfoController

```JAVA
@RestController
@RequestMapping("STTest")
public class STTestInfoController {
    @Autowired
    STTestService sts;


    @GetMapping("info/{name}")
    public List<STTest> getByName(@PathVariable String name){
        return sts.getInfosByName(name);
    }
}
```

对应raininfo.vue中的方法，加载数据根据测站名加载指定测站。

```js
 loadData:function(STNM) {
            this.axios.get("http://localhost:8083/STTest/info/"+STNM).then(
              response=>{
                console.log(response.data);
```



### 7.WaterInfoController

```java
@Controller
@RequestMapping("waterInfo")
public class WaterInfoController {
    @Autowired
    STRiverRService STRRS;
    @Autowired
    ST_PPTN_RService st_pptn_rService;
    @Autowired
    ST_RSVR_RService st_rsvr_rService;
    //获取指定测站和时间的河道水情信息
    @ResponseBody
    @GetMapping("river/{STCD}/{startDateTime}/{endDateTime}")
    public List<ST_RIVER_R> getRiverInfo(@PathVariable String STCD,
                                         @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDateTime,
                                         @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDateTime){
        return STRRS.getDataByStcdAndDate(STCD,startDateTime,endDateTime);
    }
    //获取雨量站
    @ResponseBody
    @GetMapping("rain/ST")
    public List<ST_STINFO_B> getAllRainSTNMAndSTCD(){
        return st_pptn_rService.getAllSTCDAndSTNM();
    }
    @ResponseBody
    @GetMapping("rain/getRainInfo/{STCD}/{startDateTime}/{endDateTime}")
    public List<ST_PPTN_R> getRainInfo(@PathVariable String STCD,
                                       @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDateTime,
                                       @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDateTime){
        return st_pptn_rService.getRainInfo(STCD,startDateTime,endDateTime);
    }
    //获取所有河流测站编码
    @ResponseBody
    @GetMapping("RSVR/ST")
    public List<ST_STINFO_B> getAllRiverSTNMAndSTCD(){
        return st_rsvr_rService.getAllSTCDAndSTNM();
    }

    //水库水情
    @ResponseBody
    @GetMapping("river/ST")
    public List<ST_STINFO_B> getAllRsvrSTNMAndSTCD(){
        return STRRS.getAllSTCDAndSTNM();
    }

    @ResponseBody
    @GetMapping("RSVR/{STCD}/{startDateTime}/{endDateTime}")
    public List<ST_RSVR_R> getRSVRInfo(@PathVariable String STCD,
                                       @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDateTime,
                                       @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDateTime){
        return st_rsvr_rService.getRsvrInfo(STCD,startDateTime,endDateTime);
    }

    //获取部分水库水情信息
    @ResponseBody
    @GetMapping("rain/getRainInfo/{STCD}/{STCD1}/{startDateTime}/{endDateTime}")
```

对应raininfo2中的方法，rain/st加载指定雨量测站，rsvr/st加载河流测站。

```js
methods:{
      //加载测站数据
      loadSTInfo:function(){
        this.axios.get("http://localhost:8083/waterInfo/rain/ST").then(
          response=>{
            console.log(response.data);
            this.searchParameter.options=response.data;
          }
        );
        this.axios.get("http://localhost:8083/waterInfo/RSVR/ST").then(
          response=>{
            console.log(response.data);
            this.searchParameter.rsvrOptions=response.data;
          }
        );
      },
```

对应raininfo2中的方法，rain/getRainInfo/{STCD}/{STCD1}/{startDateTime}/{endDateTime}，获取部分水库水情信息，根据测站id选择出指定测站，并设置起始时间和终止时间，加载查询到的测站信息。

```js
 loadData:function() {
        this.axios.get("http://localhost:8083/waterInfo/rain/getRainInfo/"+this.stationId
          +"/"+this.rsvrStationId
          +"/" +moment(this.searchParameter.startDateTime).format('YYYY-MM-DD HH:mm:ss')
          +"/"+moment(this.searchParameter.endDateTime).format('YYYY-MM-DD HH:mm:ss')
        ).then(
          response=>{

            let data = response.data;
            console.log(data)
            let date = [];//时间
            let Z=[];//降雨量
            let RZ=[];//库上水位

            let OTQ=[];//库下水位
```

对应riverinfo中的方法，rain/st加载指定雨量测站

```js
 methods:{
      //加载测站数据
      loadSTInfo:function() {
        this.axios.get("waterInfo/river/ST").then(
          response => {
            console.log(response.data);
            this.searchParameter.options = response.data;
          }
        );
      },
```

对应riverinfo中的方法，river/{STCD}/{startDateTime}/{endDateTime}，根据河流测站id选择出指定测站，并设置起始时间和终止时间，加载查询到的测站信息。

```js
loadData:function() {
        this.axios.get("http://localhost:8083/waterInfo/river/"+this.stationId
          +"/" +moment(this.searchParameter.startDateTime).format('YYYY-MM-DD HH:mm:ss')
          +"/"+moment(this.searchParameter.endDateTime).format('YYYY-MM-DD HH:mm:ss')
        ).then(
          response=>{
            console.log(response.data);
            let data = response.data;
            let date = [];//时间
            let Z=[];//水位
            let Q=[];//流量
```

对应riverinfo中的方法，RSVR/ST，获取所有河流测站编码。

```js
 methods:{
      loadSTInfo:function(){
        this.axios.get("http://localhost:8083/waterInfo/RSVR/ST").then(
          response=>{
            console.log(response.data);
            this.searchParameter.options=response.data;
          }
        );
      },
```

对应riverinfo中的方法，RSVR/{STCD}/{startDateTime}/{endDateTime}，根据河流测站id选择出指定测站，并设置起始时间和终止时间，加载查询到的测站信息。

```js
 loadData:function() {


        this.axios.get("http://localhost:8083/waterInfo/RSVR/"+this.stationId
          +"/" +moment(this.searchParameter.startDateTime).format('YYYY-MM-DD HH:mm:ss')
          +"/"+moment(this.searchParameter.endDateTime).format('YYYY-MM-DD HH:mm:ss')
        ).then(
          response=>{
            console.log(response.data);
            let data = response.data;
            let date = [];//时间
            let RZ=[];//库上水位
            let INQ=[];//入库流量
            let W=[];//蓄水量
            let OTQ=[];//库下水位
            let INQDR=[];//出库流量
```

对应FloodPreventionKit下面的Rainstchooser.vue页面中，rain/st，获取雨量站信息。

```js
 //加载雨量站
      loadRainStations:function(){

        this.axios.get("http://localhost:8083/waterInfo/rain/ST").then(
          response=>{
            console.log(response.data);
            this.rain_stations=response.data;

          }
        );
      },
```

对应FloodPreventionKit下面的Rainstchooser.vue页面中，RSVR/ST，获取所有河流测站编码。

```js
 //加载雨量站
      loadRainStations:function(){

        this.axios.get("http://localhost:8083/waterInfo/RSVR/ST").then(
          response=>{
            console.log(response.data);
            this.stations=response.data;

          }
        );
      },
```

对应FloodPreventionKit下面的Rainstchooser.vue页面中，根据起始时间和终止时间，查询指定时间内的雨量数据。

```js
 //获取所有的雨量数据
      getRainData(){
        this.axios.get("http://localhost:8083/RSVR/"+moment(this.searchParameter.startDateTime).format('YYYY-MM-DD HH:mm:ss')+"/"+moment(this.searchParameter.endDateTime).format('YYYY-MM-DD HH:mm:ss')+"/querries?list="+this.checked_stations).then(
          response=>{
            this.openRsvrDataShow(response.data);
          }
        )
      },
```



```js
  //加载雨量站
      loadRainStations:function(){

        this.axios.get("http://localhost:8083/waterInfo/was/ST").then(
          response=>{
            console.log(response.data);
            this.stations=response.data;

          }
        );
      },
      //获取所有的雨量数据
      getRainData(){
        this.axios.get("http://localhost:8083/was/"+moment(this.searchParameter.startDateTime).format('YYYY-MM-DD HH:mm:ss')+"/"+moment(this.searchParameter.endDateTime).format('YYYY-MM-DD HH:mm:ss')+"/querries?list="+this.checked_stations).then(
          response=>{
            this.openDataShow(response.data);
          }
        )
      },

```

#### （四）src/main/java/cn.hhu/mapper
创建dao接口的实现类，并交给业务逻辑层调用，与mapper.xml相对应。
#### （五）src/main/java/cn.hhu/repository
Repository:最顶层的接口，一个空的接口，统一所有的Repository类型，并且能够让组件扫描的时候能够自动识别。

#### （六）src/main/java/cn.hhu/service
service层负责业务逻辑，通过dao接口来增删改查数据库中多个表的数据，从而完成一个功能。该包放置业务操作类，譬如用户服务类，一般情况将该用户操作类提取一个接口，然后在service包下生成一个impl包，在impl包中才放置用户操作接口的 实现类。
#### （七）src/main/java/cn.hhu/utils
该包中放置常用的一些工具集。
#### （八）src/main/resources/mapper
1.表所对应的实体类的类名+Mapper.xml

2.一个映射文件对应一个实体实体类，对应一张表的操作

3.MyBatis映射文件用于编写SQL，访问以及操作表中的数据

4.MyBatis映射文件存放的位置在src/main/resources/mappers目录下

#### 程序启动
开发过程，前端启动可直接点击执行按钮，后端idea环境下使用nmp run dev



## windows环境下后端配置
### 1.Jdk 安装配置
推荐博客：
https://blog.csdn.net/Saturn_Mentos/article/details/122310056?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522170968341916800182710688%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=170968341916800182710688&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-122310056-null-null.142^v99^pc_search_result_base5&utm_term=jdk%E5%AE%89%E8%A3%85&spm=1018.2226.3001.4187
### 2.MySQL 安装
推荐博客:
https://blog.csdn.net/weixin_47406082/article/details/131867849?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522170970449816800226534513%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=170970449816800226534513&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-2-131867849-null-null.142^v99^pc_search_result_base5&utm_term=MySql%E5%AE%89%E8%A3%85%E9%85%8D%E7%BD%AE&spm=1018.2226.3001.4187
### 3.IDEA 获取
推荐博客：
https://blog.csdn.net/beixishuo/article/details/104248231?utm_source=miniapp_weixin
### 4.Maven 安装
推荐博客：
https://blog.csdn.net/m0_63684495/article/details/129046405?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522170970460716800226580038%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=170970460716800226580038&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-129046405-null-null.142^v99^pc_search_result_base5&utm_term=Maven%E5%AE%89%E8%A3%85&spm=1018.2226.3001.4187
### 5.使用Springboot 
推荐博客：
https://blog.csdn.net/weixin_42029450/article/details/111905746?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522170970467116800192210767%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=170970467116800192210767&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-111905746-null-null.142^v99^pc_search_result_base5&utm_term=Idea%E4%BD%BF%E7%94%A8Springboot&spm=1018.2226.3001.4187

## windows环境下前端配置
### 1.VUE安装以及环境配置
推荐博客:
https://blog.csdn.net/qq_45637260/article/details/121676126?ops_request_misc=&request_id=&biz_id=102&utm_term=%20VUE%E9%A1%B9%E7%9B%AE%E6%90%AD%E5%BB%BA%E5%92%8C%E7%8E%AF%E5%A2%83%E9%85%8D%E7%BD%AE&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-3-121676126.142^v99^pc_search_result_base5&spm=1018.2226.3001.4187
