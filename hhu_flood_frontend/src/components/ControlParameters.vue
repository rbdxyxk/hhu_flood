<template>
  <el-form ref="form" class="form-mini-bottom" label-position="right" size="mini" :model="Form1" label-width="138px" style="width:450px;">
    <el-form-item label="项目说明" required>
      <el-input v-model="Form1.ca_pd.NT"></el-input>
    </el-form-item>
    <el-form-item label="模拟时段总数">
      <el-input v-model="Form1.cc_cp.NST"></el-input>
<!--      <el-date-picker-->
<!--        v-model="value1"-->
<!--        type="datetimerange"-->
<!--        range-separator="至"-->
<!--        start-placeholder="开始日期"-->
<!--        end-placeholder="结束日期">-->
<!--      </el-date-picker>-->
    </el-form-item>
<!--    <el-form-item label="模拟时段" required>-->
<!--      <el-col :span="11">-->
<!--        <el-form-item prop="date1">-->
<!--          <el-date-picker type="date" placeholder="选择日期" v-model="Form1.cc_cp.date1" style="width: 100%;"></el-date-picker>-->
<!--        </el-form-item>-->
<!--      </el-col>-->
<!--      <el-col class="line" :span="2">-</el-col>-->
<!--      <el-col :span="11">-->
<!--        <el-form-item prop="date2">-->
<!--          <el-time-picker placeholder="选择时间" v-model="Form1.cc_cp.date2" style="width: 100%;"></el-time-picker>-->
<!--        </el-form-item>-->
<!--      </el-col>-->
<!--    </el-form-item>-->
    <el-form-item label="给定边界总数" required>
    <el-input v-model="Form1.cc_cp.NBC"></el-input>

    </el-form-item>
    <el-form-item label="打印频率" required>
      <el-input v-model="Form1.cc_cp.IPRINT"></el-input>
    </el-form-item>
    <el-form-item label="指定单元边总数" required>
      <el-input v-model="Form1.cc_cp.NOUT"></el-input>
    </el-form-item>
    <el-form-item label="降水文件参数" required>
      <el-input v-model="Form1.cc_cp.IRAIN" ></el-input>
    </el-form-item>
    <el-form-item label="模拟水质参数" required>
      <el-input v-model="Form1.cc_cp.IQUA"></el-input>
    </el-form-item>
    <el-form-item label="点源污染文件参数" required>
    <el-input v-model="Form1.cc_cp.INPP"></el-input>
    </el-form-item>
    <el-form-item label="湖床污染文件" required>
      <el-input v-model="Form1.cc_cp.IBDP"></el-input>
    </el-form-item>
<!--    <el-form-item>-->
<!--      <label >是否热启动</label>-->
<!--      <el-select v-model="value1"  placeholder="请选择">-->
<!--        <el-option-->

<!--          label="是"-->
<!--          value="1">-->
<!--        </el-option>-->
<!--        <el-option-->

<!--          label="否"-->
<!--          value="0">-->
<!--        </el-option>-->
<!--      </el-select>-->

<!--      <label >使用单位</label>-->
<!--      <el-select v-model="value1" multiple placeholder="请选择">-->
<!--        <el-option-->

<!--          label="公制单位"-->
<!--          value="1">-->
<!--        </el-option>-->
<!--        <el-option-->

<!--          label="英制单位"-->
<!--          value="0">-->
<!--        </el-option>-->
<!--      </el-select>-->
<!--    </el-form-item>-->
    <el-form-item label="是否热启动" required>
      <el-radio-group v-model="Form1.cb_nread.NREAD">
        <el-radio :label="1" >热启动</el-radio>
        <el-radio :label="0" >正常启动</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="使用单位" required>
      <el-radio-group v-model="Form1.cc_cp.IUNITS">
        <el-radio :label="1" border >公制单位</el-radio>
        <el-radio :label="0" border>英制单位</el-radio>
      </el-radio-group>
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
              Form1: {

                // date1:Date.now(),
                // date2:Date.now(),

                ca_pd:{
                  NT:'泄流分析'
                },
                cb_nread:{
                  NREAD:0,

                },
                cc_cp:{
                  NBC:2,
                  IPRINT:2,
                  NOUT:2,
                  IRAIN:0,
                  IQUA:0,
                  INPP:0,
                  IBDP:0,
                  NST:720,
                  IUNITS:0
                }

              }
            };
          },
      methods: {
        onSubmit() {
          console.log('submit!');
        },
        setData(data){

          this.Form1=data;
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

