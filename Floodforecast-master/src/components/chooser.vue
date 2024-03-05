<template>
  <el-form :model="selectForm" label-width="100px" class="demo-dynamic">
    <el-form-item

      label="计算项目:"
      style="margin-top: 20px"
    >
      <el-select v-model="selectForm.onSelectID" placeholder="请选择" >
            <el-option v-for="(item,index1) in selectData "
                       :label="item.nt+' '+item.id"
                       :value="item.id"
                       :key="index1"
            > </el-option>
      </el-select>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="getDataById(selectForm.onSelectID)">提交</el-button>
      <el-button @click="newNT">新增项目</el-button>

    </el-form-item>
<!--    {{parameter}}-->
  </el-form>
</template>

<script>
    export default {
      name: "chooser",
      // props: {
      //   chooser:String,
      //   parameter:Boolean
      // },
      components:{},
      data() {
        return {
          selectForm:{
            onSelectID:1,
          },
          selectData:[

          ],

        }
      },
      mounted: function () {

        this.axios.get("http://localhost:8083/CAPD/getAll").then(
          response=>{
            // console.log(this.props)

            this.selectData=response.data;
          }
        );
      },
      methods:{
        getDataById(ID){
          this.$emit('changeChooser',false);
          this.$emit('changeParameters',true,ID)
        },
        newNT(){

          this.$emit('changeChooser',false);
          this.$emit('changeParameters',true)
        },


      }
    }
</script>

<style scoped>

</style>
