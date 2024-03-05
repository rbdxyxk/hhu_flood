<template>
  <el-table
    ref="myTable"
    :data="tableData"
    height="530"
    border
    style="width: 100%">
    <el-table-column
      v-for="(name,index) in names"
      :prop="name"
      :label="name"
      :key="index"
      >
    </el-table-column>

  </el-table>
</template>

<script>
  export default {
    data() {
      return {
        tableData: [],
        names:[],
        date:[]
      }
    },
    methods:{
      //重置表格
      doLayout(){
        this.$refs.myTable.doLayout();

      },
      // //重置大小
      // reSize(size){
      //
      // },
      //表格数据加载
      loadData:function(names,...args){
        //将名称对应
        this.names=names;

        //填入数据
        this.tableData=[];
        if(args.length>0){
          for(let  i=0 ; i <args[0].length; i++){
              //存储单元格
              let dataCell={};
              for(let j in args ){
                dataCell[names[j]]=args[j][i];
              }
              this.tableData.push(dataCell);
          }
        }

      },
      exportCsv:function (filename) {
        //存储输出的数据
          let str='';
          //加入表头
          for(let i=0,length=this.names.length;i<length;i++){
            str += this.names[i]+',';
          }
          str+='\r\n';
          //加入数据
        for(let i=0,length=this.tableData.length;i<length;i++){

          for(let j=0,length=this.names.length;j<length;j++){
            str += this.tableData[i][this.names[j]]+',';
          }
          str+='\r\n';
        }
        console.log(str);
        // 保存为csv文件并添加下载按钮
        str = "data:application/csv," + encodeURIComponent(str);
        let btn = document.createElement('a');
        btn.setAttribute("href", str);
        btn.setAttribute("download", filename+".csv");
        //模拟点击
        // document.body.appendChild(btn)
        btn.click()
        // document.body.removeChild(btn)

      }
    },
    mounted() {
    }
  }
</script>
