<template>
  <el-form ref="form" class="form-mini-bottom" label-position="right" size="mini" :model="ce_tandt" label-width="138px" style="width:450px;">
    <el-form-item label="模拟时段长度(h)" required>
      <el-input v-model="ce_tandt.STIME"></el-input>
    </el-form-item>

    <el-form-item label="时间步长(min)" required>
      <el-input v-model="ce_tandt.DTIME"></el-input>

    </el-form-item>
    <el-form-item label="识别完全干旱单元的水深阈值(m)" required>
      <el-input v-model="ce_tandt.HTOL1"></el-input>
    </el-form-item>
    <el-form-item label="识别完全湿润单元的水深阈值(m)" required>
      <el-input v-model="ce_tandt.HTOL2"></el-input>
    </el-form-item>
    <el-form-item label="相邻单元底高程差阈值(m)" required>
      <el-input v-model="ce_tandt.HTOL3" ></el-input>
    </el-form-item>
    <el-form-item label="X方向粘滞系数" required>
      <el-input v-model="ce_tandt.EFX"></el-input>
    </el-form-item>
    <el-form-item label="Y方向粘滞系数" required>
      <el-input v-model="ce_tandt.EFY"></el-input>
    </el-form-item>
    <el-form-item label="底高程修正系数" required>
      <el-input v-model="ce_tandt.THETA"></el-input>
    </el-form-item>

  </el-form>
</template>

<script>
  export default {
    name: "Form1",
    data() {
      return {
        ce_tandt: {
          STIME:2,
          DTIME:2,
          HTOL1:0.1,
          HTOL2:0.3,
          HTOL3:0.1,
          EFX:0.03,
          EFY:0.034,
          THETA:0.976,
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

