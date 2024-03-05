<template>
  <div>
    <el-tabs v-model="activeName" type="card" background-color="#fff" @tab-click="handleClick">
      <el-tab-pane label="控制参数" name="first">

        <ControlParameters ref="ControlParameters"></ControlParameters>

      </el-tab-pane>
      <el-tab-pane label="时间及阈值参数" name="third">
        <TimeParameters ref="TimeParameters">

        </TimeParameters>
      </el-tab-pane>
      <el-tab-pane label="过程线参数" name="second">
        <ProcessParameters ref="ProcessParameters">

        </ProcessParameters>
      </el-tab-pane>

      <el-tab-pane label="公式及数据格式参数" name="fourth">
        <FormatParameters ref="FormatParameters">

        </FormatParameters>
      </el-tab-pane>
    </el-tabs>

      <div class="buttons">
        <el-button type="primary" @click="onSubmit">提交</el-button>
        <el-button type="danger" @click="closeForm">关闭</el-button>
      </div>

  </div>
</template>
<script>
  import ControlParameters from "./ControlParameters";
  //过程线参数
  import ProcessParameters from "./ProcessParameters";
  import TimeParameters from "./TimeParameters";
  import FormatParameters from "./FormatParameters";
  export default {
    components:{
      ControlParameters,
      ProcessParameters,
      TimeParameters,
      FormatParameters
    },
    data() {
      return {
        activeName: 'first'
      };
    },
    methods: {
      setParameters(ID){

        this.axios.get("http://localhost:8083/parameters/CAPD/"+ID).then(
          response=>{
            console.log(response.data);
            ///alert()
            this.$refs.ControlParameters.setData(response.data.ControlParameters);
            this.$refs.ProcessParameters.setData(response.data.checked,response.data.cd_pline);
          }
        );


      },

      handleClick(tab, event) {
        console.log(tab, event);
      },
      closeForm(){
        this.$emit('changeParameters',false);
      },
      onSubmit(){
        const ControlParameters = this.$refs.ControlParameters.Form1;
        const Checked = this.$refs.ProcessParameters.Checked;
        const ProcessParameters = this.$refs.ProcessParameters.Form2;
        const CE_TANDT = this.$refs.TimeParameters.ce_tandt;
        const CF_GFANDNF = this.$refs.FormatParameters.cf_gfandnf;
        // console.log(ControlParameters.ControlParameters);
        // console.log(ProcessParameters);
        // console.log(TimeParameters);
        // console.log(FormatParameters);
        //console.log(this.$refs.ControlParameters)
        const data = {
          CA_PD:ControlParameters.ca_pd,
          CB_NREAD: ControlParameters.cb_nread,
          CC_CP: ControlParameters.cc_cp,
          CD_PLINE: ProcessParameters.cd_pline,
          CE_TANDT,
          CF_GFANDNF,
          Checked

        };
        console.log(data);
        this.axios.post("http://localhost:8083/parameters/addAll",data).then(
          response=>{
            console.log(response.data);
            this.$emit('changeParameters',false);
          }
        );
      }
    }
  };
</script>
<style scoped>
  .buttons{
    margin-top: 5px;
    margin-left: 35%;
  }
</style>
