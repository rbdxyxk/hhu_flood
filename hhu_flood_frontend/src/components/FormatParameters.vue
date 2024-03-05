<template>
  <el-form ref="form" class="form-mini-bottom" label-position="right" size="mini" :model="cf_gfandnf" label-width="138px" style="width:450px;">

    <!--    <el-form-item label="模拟时段总数">-->
    <!--      <el-input v-model="cf_gfandnf.NST"></el-input>-->
    <!--    </el-form-item>-->
    <!--    <el-form-item label="模拟时段" required>-->
    <!--      <el-col :span="11">-->
    <!--        <el-form-item prop="date1">-->
    <!--          <el-date-picker type="date" placeholder="选择日期" v-model="cf_gfandnf.date1" style="width: 100%;"></el-date-picker>-->
    <!--        </el-form-item>-->
    <!--      </el-col>-->
    <!--      <el-col class="line" :span="2">-</el-col>-->
    <!--      <el-col :span="11">-->
    <!--        <el-form-item prop="date2">-->
    <!--          <el-time-picker placeholder="选择时间" v-model="cf_gfandnf.date2" style="width: 100%;"></el-time-picker>-->
    <!--        </el-form-item>-->
    <!--      </el-col>-->
    <!--    </el-form-item>-->
    <el-form-item label="使用二阶函数参数" required>
      <el-input v-model="cf_gfandnf.LIMTER"></el-input>

    </el-form-item>
    <el-form-item label="计算精度(阶)" required>
      <el-input v-model="cf_gfandnf.BETA"></el-input>
    </el-form-item>
    <el-form-item label="模拟区的纬度(°)" required>
      <el-input v-model="cf_gfandnf.PP"></el-input>
    </el-form-item>
    <el-form-item label="风速(m/s)" required>
      <el-input v-model="cf_gfandnf.WSP" ></el-input>
    </el-form-item>
    <el-form-item label="风向角(°)" required>
      <el-input v-model="cf_gfandnf.WINC"></el-input>
    </el-form-item>
    <el-form-item label="闸下出流计算公式" required>
      <el-select v-model="cf_gfandnf.MG" placeholder="请选择">
        <el-option
          label="使用USACE的公式"
          value='1'>
        </el-option>
        <el-option

          label="使用经验公式"
          value="2">
        </el-option>
        <el-option

          label="使用SFWMD子程序"
          value="3">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="计算上下游边界处的闸下出流" required>
      <el-select v-model="cf_gfandnf.MGS"  placeholder="请选择">
        <el-option

          label="使用USACE的公式"
          value="1">
        </el-option>
        <el-option

          label="使用经验公式"
          value="2">
        </el-option>
        <el-option

          label="使用SFWMD子程序"
          value="3">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="法向通量计算格式" required>
      <el-select v-model="cf_gfandnf.MCS"  placeholder="请选择">
      <el-option

        label="应用Osher格式"
        value="1">
      </el-option>
      <el-option

        label="应用FVS格式"
        value="2">
      </el-option>
      <el-option

        label="应用FDS格式"
        value="3">
      </el-option>
      </el-select>
    </el-form-item>
        <el-form-item label="单元间底高程相差较大时处理方式">
          <el-select v-model="cf_gfandnf.MTS"  placeholder="请选择">
          <el-option

            label="使用堰流处理"
            value="1">
          </el-option>
          <el-option

            label="使用积分处理"
            value="2">
          </el-option>

          </el-select>
        </el-form-item>



    <!--    <el-form-item size="large">-->
    <!--     -->
    <!--    </el-form-item>-->
  </el-form>
</template>

<script>
  export default {
    name: "Form1",
    data() {
      return {
        cf_gfandnf: {
          MG:'1',
          MGS:'1',
          MCS:'1',
          MTS:'1',
          LIMTER:2,
          BETA:2,
          PP:0,
          WSP:0.2,
          WINC:35,

        }
      };
    },
    methods: {
      onSubmit() {
        console.log('submit!');
      }
    }

  };
</script>

<style scoped>
  .mini-width{
    width: 200px;
  }
  .form-mini-bottom > *{
    margin-bottom: 5px;
  }
</style>

