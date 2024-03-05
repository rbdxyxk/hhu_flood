<template>
  <div>
    <el-form ref="form" class="form-mini-bottom" label-position="right" size="mini" :model="Form2" label-width="180px"
             style="width:500px;">
      <el-form-item label="流量过程线总数"  class="">

        <el-input v-model="Form2.cd_pline.NQC"  class="inline-input"></el-input>
        <el-button type="primary" style="display:inline-block"  @click="Checked.NQC.Dialog = true">选择</el-button>

      </el-form-item>
      <el-form-item label="流速过程线总数" >
        <el-input v-model="Form2.cd_pline.NVC" class="inline-input"></el-input>
        <el-button type="primary" style="display:inline-block"  @click="Checked.NVC.Dialog= true">选择</el-button>
      </el-form-item>
      <el-form-item label="水位过程线总数" >
        <el-input v-model="Form2.cd_pline.NZC" class="inline-input"></el-input>
        <el-button type="primary" style="display:inline-block"  @click="Checked.NZC.Dialog= true">选择</el-button>
      </el-form-item>
      <el-form-item label="闸门过程线总数" >
        <el-input v-model="Form2.cd_pline.NGC" class="inline-input"></el-input>
        <el-button type="primary" style="display:inline-block"  @click="Checked.NGC.Dialog= true">选择</el-button>
      </el-form-item>
      <el-form-item label="水位流量关系曲线总数" >
        <el-input v-model="Form2.cd_pline.NHC" class="inline-input"></el-input>
        <el-button type="primary" style="display:inline-block"  @click="Checked.NHC.Dialog= true">选择</el-button>
      </el-form-item>
      <el-form-item label="水位流量关系曲线上点距数" v-show="false">
        <el-input v-model="Form2.cd_pline.NQH"></el-input>
      </el-form-item>
      <el-form-item label="上下游边界的闸门过程总数">
        <el-input v-model="Form2.cd_pline.NSC" class="inline-input"></el-input>
        <el-button type="primary" style="display:inline-block"  @click="Checked.NSC.Dialog= true">选择</el-button>
      </el-form-item>
      <el-form-item label="湿地支流入流过程线总数" >
        <el-input v-model="Form2.cd_pline.NQLC" class="inline-input"></el-input>
        <el-button type="primary" style="display:inline-block"  @click="Checked.NQLC.Dialog= true">选择</el-button>

      </el-form-item>
      <el-form-item label="降雨过程线总数">
        <el-input v-model="Form2.cd_pline.NRC"></el-input>
      </el-form-item>
      <el-form-item label="急流边界过程线总数" >
        <el-input v-model="Form2.cd_pline.NSPC" class="inline-input"></el-input>
        <el-button type="primary" style="display:inline-block"  @click="Checked.NSPC.Dialog= true">选择</el-button>
      </el-form-item>
      <el-form-item label="上下游边界处的堰" >
        <el-input v-model="Form2.cd_pline.NWC" class="inline-input"></el-input>
        <el-button type="primary" style="display:inline-block"  @click="Checked.NWC.Dialog= true">选择</el-button>
      </el-form-item>
      <el-form-item label="抽水过程线总数" >
        <el-input v-model="Form2.cd_pline.NPMC" ></el-input>
<!--        <el-button type="primary" style="display:inline-block"  @click="">选择</el-button>-->
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


      <!--    <el-form-item size="large">-->
      <!--     -->
      <!--    </el-form-item>-->
    </el-form>
<!--    <el-dialog-->
<!--      title="请选择测站"-->
<!--      :visible.sync="dialogVisible"-->
<!--      width="30%"-->
<!--      :before-close="handleClose">-->



<!--        <el-checkbox-group v-model="Checked.QChecked">-->
<!--        <el-checkbox label="美食/餐厅线上活动"></el-checkbox>-->
<!--        <el-checkbox label="地推活动" ></el-checkbox>-->
<!--        <el-checkbox label="线下主题活动" ></el-checkbox>-->
<!--      </el-checkbox-group>-->

<!--      <span slot="footer" class="dialog-footer">-->
<!--    <el-button @click="dialogVisible = false">取 消</el-button>-->
<!--    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>-->
<!--      </span>-->
<!--    </el-dialog>-->
    <el-dialog
      v-for="(item,index) in Checked"
      :key="index"
      title="请选择测站"

      :visible.sync="item.Dialog"
      width="35%"
      :before-close="handleClose" >

<!--      {{item}}{{index}}-->

      <el-checkbox-group v-model="item.Checked" :key="index">
        <div class="checkBox" v-for="(station,index1) in stations[item.station]" :key="index1">
          {{index1}}
          <el-checkbox  :key="index1" :label="station.STCD" style="width: 80px">
            {{station.name}}
          </el-checkbox>
<!--          <el-date-picker class="timeSelect"-->
<!--            v-model="item.date[index1]"-->
<!--            type="datetimerange"-->
<!--            range-separator="至"-->
<!--            start-placeholder="开始日期"-->
<!--            end-placeholder="结束日期"-->
<!--          >-->
<!--          </el-date-picker>-->
          <el-date-picker
            v-model="item.date[index1]"
            type="datetime"
            format="yyyy-MM-dd HH:mm:ss"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择日期时间"
            >
          </el-date-picker>
        </div>
      </el-checkbox-group>


      <span slot="footer" class="dialog-footer">
    <el-button @click="closeDialog(item,index)">取 消</el-button>
    <el-button type="primary" @click="sureDialog(item,index)">确 定</el-button>
      </span>
    </el-dialog>
  </div>

</template>

<script>
  export default {
    name: "Form2",
    data() {
      return {
        Form2: {
          cd_pline:{
            NQC:2,
            NFC:2,
            NVC:2,
            NZC:2,
            NGC:2,
            NHC:2,
            NQH:72,
            NSC:2,
            NQLC:2,
            NRC:7,
            NSPC:1,
            NPMC:0,
            NWC:2,
          },

        },
        dialogVisible:false,
        Checked: {
          NQC:{
            Dialog:false,
            station:"river",
            Checked:['51113800','51113801'],
            date:['2001-01-01 01:00:00','2001-01-01 01:00:00','2001-01-01 01:00:00','2007-06-26 06:00:00','2007-06-26 06:00:00','2007-06-26 06:00:00'],
            //date:[['2007-06-26 06:00:00','2007-08-11 06:00:00'],['2007-06-26 06:00:00','2007-08-11 06:00:00'],['2007-06-26 06:00:00','2007-08-11 06:00:00']],
          },
          NZC:{
            Dialog:false,
            station:"river",
            Checked:['41814701','41814801'],
            date:['2001-01-01 01:00:00','2001-01-01 01:00:00','2001-01-01 01:00:00','2007-06-26 06:00:00','2007-06-26 06:00:00','2007-06-26 06:00:00'],
            //date:[['2007-06-26 06:00:00','2007-08-11 06:00:00'],['2007-06-26 06:00:00','2007-08-11 06:00:00'],['2007-06-26 06:00:00','2007-08-11 06:00:00']],
          },
          NVC:{
            Dialog:false,
            station:"river",
            Checked:['41814701','41814801'],
            //date:['2007-06-26 06:00:00','2007-06-26 06:00:00','2007-06-26 06:00:00'],
            date:['2001-01-01 01:00:00','2001-01-01 01:00:00','2001-01-01 01:00:00','2007-06-26 06:00:00','2007-06-26 06:00:00','2007-06-26 06:00:00'],
            //date:[['2007-06-26 06:00:00','2007-08-11 06:00:00'],['2007-06-26 06:00:00','2007-08-11 06:00:00'],['2007-06-26 06:00:00','2007-08-11 06:00:00']],
          },
          NGC:{
            Dialog:false,
            station:"gate",
            Checked:['51112101','51113900'],
            date:['2001-01-01 01:00:00','2001-01-01 01:00:00','2001-01-01 01:00:00','2007-08-12 18:00:00','2010-09-14 06:00:00','2001-01-01 03:00:00'],
            //date:[['2007-08-12 18:00:00','2007-08-13 14:30:00'],['2010-09-14 06:00:00','2010-09-22 06:00:00'],['2010-08-25 06:00:00','2010-08-28 06:00:00'],['2008-05-08 10:00:00','2008-05-20 08:00:00']],
          },
          NHC:{
            Dialog:false,
            station:"river",
            Checked:['41814701','41814801'],
            //date:['2007-06-26 06:00:00','2007-06-26 06:00:00','2007-06-26 06:00:00'],
            date:['2001-01-01 01:00:00','2001-01-01 01:00:00','2001-01-01 01:00:00','2007-06-26 06:00:00','2007-06-26 06:00:00','2007-06-26 06:00:00'],
            //date:[['2007-06-26 06:00:00','2007-08-11 06:00:00'],['2007-06-26 06:00:00','2007-08-11 06:00:00'],['2007-06-26 06:00:00','2007-08-11 06:00:00']],
          },
          NQLC:{
            Dialog:false,
            station:"gate",
            Checked:['41814900','41814488'],
            date:['2001-01-01 01:00:00','2001-01-01 01:00:00','2001-01-01 01:00:00','2007-08-12 18:00:00','2010-09-14 06:00:00','2001-01-01 03:00:00'],
            //date:['2007-08-12 18:00:00','2010-09-14 06:00:00','2010-08-25 06:00:00','2008-05-08 10:00:00'],
            //date:[['2007-08-12 18:00:00','2007-08-13 14:30:00'],['2010-09-14 06:00:00','2010-09-22 06:00:00'],['2010-08-25 06:00:00','2010-08-28 06:00:00'],['2008-05-08 10:00:00','2008-05-20 08:00:00']],
          },
          NSPC:{//急流过程线
            Dialog:false,
            station:"gate",
            Checked:['41814900','41814488'],
            date:['2001-01-01 01:00:00','2001-01-01 01:00:00','2001-01-01 01:00:00','2007-08-12 18:00:00','2010-09-14 06:00:00','2001-01-01 03:00:00'],
            //date:['2007-08-12 18:00:00','2010-09-14 06:00:00','2010-08-25 06:00:00','2008-05-08 10:00:00'],
            //date:[['2007-08-12 18:00:00','2007-08-13 14:30:00'],['2010-09-14 06:00:00','2010-09-22 06:00:00'],['2010-08-25 06:00:00','2010-08-28 06:00:00'],['2008-05-08 10:00:00','2008-05-20 08:00:00']],
          },
          NSC:{//闸
            Dialog:false,
            station:"gate",
            Checked:['41814900','41814488'],
            date:['2001-01-01 01:00:00','2001-01-01 01:00:00','2001-01-01 01:00:00','2007-08-12 18:00:00','2010-09-14 06:00:00','2001-01-01 03:00:00'],
            //date:['2007-08-12 18:00:00','2010-09-14 06:00:00','2010-08-25 06:00:00','2008-05-08 10:00:00'],
            //date:[['2007-08-12 18:00:00','2007-08-13 14:30:00'],['2010-09-14 06:00:00','2010-09-22 06:00:00'],['2010-08-25 06:00:00','2010-08-28 06:00:00'],['2008-05-08 10:00:00','2008-05-20 08:00:00']],
          },
          NWC:{//堰
            Dialog:false,
            station:"gate",
            Checked:['41814900','41814488'],
            date:['2001-01-01 01:00:00','2001-01-01 01:00:00','2001-01-01 01:00:00','2007-08-12 18:00:00','2010-09-14 06:00:00','2001-01-01 03:00:00'],
            //date:['2007-08-12 18:00:00','2010-09-14 06:00:00','2010-08-25 06:00:00','2008-05-08 10:00:00'],
            //date:[['2007-08-12 18:00:00','2007-08-13 14:30:00'],['2010-09-14 06:00:00','2010-09-22 06:00:00'],['2010-08-25 06:00:00','2010-08-28 06:00:00'],['2008-05-08 10:00:00','2008-05-20 08:00:00']],
          }
        },


        stations:{
          river:[
            {
              name :'临洪',
              STCD:'51113800'
            },
            {
              name :'临洪（东）',
              STCD:'51113801'
            }
            ,
            {
              name :'大兴镇',
              STCD:'51112000'
            },
            {
              name:'福山',
              STCD:'41814701'
            },
            {
              name: '臧格庄',
              STCD: '41814801'
            },
            {
              name: '团旺',
              STCD: '41816401'
            }
          ],
          gate:[
            {
              name:"石梁河水库",
              STCD:"51112101"
            },
            {
              name :'临洪闸',
              STCD:'51113900'
            }
            ,
            {
              name :'太平庄闸',
              STCD:'51112040'
            },
            {
              name: "平山",
              STCD: "41814488"
            },
            {
              name:"门楼",
              STCD:"41814900"
            },
            {
              name: "大官庄闸(新)(闸上)",
              STCD: "51111911"
            },
            // {
            //   name:"留架",
            //   STCD: "41814288"
            // },
            // {
            //   name:"王屋",
            //   STCD: "41814100"
            // }

          ],
          rain:[
            {
              name:'福山',
              STCD:'41814701'
            },
            {
              name: '臧格庄',
              STCD: '41814801'
            },
            {
              name: '团旺',
              STCD: '41816401'
            },

            {
              name:"王屋",
              STCD: "41814100"
            },
            {
              name:"门楼",
              STCD:"41814900"
            },
          ]
        },

        //存储选项上一次的值用于重置
        onChecked: {
          NQC:{
            Dialog:false,
            station:"river",
            Checked:['51113800','51113801'],
            date:['2001-01-01 01:00:00','2001-01-01 01:00:00','2001-01-01 01:00:00','2007-06-26 06:00:00','2007-06-26 06:00:00','2007-06-26 06:00:00'],
            //date:['2007-06-26 06:00:00','2007-06-26 06:00:00','2007-06-26 06:00:00'],
            //date:[['2007-06-26 06:00:00','2007-08-11 06:00:00'],['2007-06-26 06:00:00','2007-08-11 06:00:00'],['2007-06-26 06:00:00','2007-08-11 06:00:00']],
          },
          NZC:{
            Dialog:false,
            station:"river",
            Checked:['41814701','41814801'],
            date:['2001-01-01 01:00:00','2001-01-01 01:00:00','2001-01-01 01:00:00','2007-06-26 06:00:00','2007-06-26 06:00:00','2007-06-26 06:00:00'],
            //date:['2007-06-26 06:00:00','2007-06-26 06:00:00','2007-06-26 06:00:00','2007-08-11 06:00:00']
            //date:[['2007-06-26 06:00:00','2007-08-11 06:00:00'],['2007-06-26 06:00:00','2007-08-11 06:00:00'],['2007-06-26 06:00:00','2007-08-11 06:00:00']],
          },
          NVC:{
            Dialog:false,
            station:"river",
            Checked:['41814701','41814801'],
            date:['2001-01-01 01:00:00','2001-01-01 01:00:00','2001-01-01 01:00:00','2007-06-26 06:00:00','2007-06-26 06:00:00','2007-06-26 06:00:00'],
            //date:['2007-06-26 06:00:00','2007-06-26 06:00:00','2007-06-26 06:00:00'],
            //date:[['2007-06-26 06:00:00','2007-08-11 06:00:00'],['2007-06-26 06:00:00','2007-08-11 06:00:00'],['2007-06-26 06:00:00','2007-08-11 06:00:00']],
          },
          NGC:{
            Dialog:false,
            station:"gate",
            Checked:['51112101','51113900'],
            date:['2001-01-01 01:00:00','2001-01-01 01:00:00','2001-01-01 01:00:00','2007-08-12 18:00:00','2010-09-14 06:00:00','2001-01-01 03:00:00'],
            //date:['2007-08-12 18:00:00','2010-09-14 06:00:00','2010-08-25 06:00:00','2008-05-08 10:00:00'],
            //date:[['2007-08-12 18:00:00','2007-08-13 14:30:00'],['2010-09-14 06:00:00','2010-09-22 06:00:00'],['2010-08-25 06:00:00','2010-08-28 06:00:00'],['2008-05-08 10:00:00','2008-05-20 08:00:00']],
          },
          NHC:{
            Dialog:false,
            station:"river",
            Checked:['41814701','41814801'],
            date:['2001-01-01 01:00:00','2001-01-01 01:00:00','2001-01-01 01:00:00','2007-06-26 06:00:00','2007-06-26 06:00:00','2007-06-26 06:00:00'],
            //date:['2007-06-26 06:00:00','2007-06-26 06:00:00','2007-06-26 06:00:00'],
            //date:[['2007-06-26 06:00:00','2007-08-11 06:00:00'],['2007-06-26 06:00:00','2007-08-11 06:00:00'],['2007-06-26 06:00:00','2007-08-11 06:00:00']],
          },
          NQLC:{
            Dialog:false,
            station:"gate",
            Checked:['41814900','41814488'],
            date:['2001-01-01 01:00:00','2001-01-01 01:00:00','2001-01-01 01:00:00','2007-08-12 18:00:00','2010-09-14 06:00:00','2001-01-01 03:00:00'],
            //date:['2007-08-12 18:00:00','2010-09-14 06:00:00','2010-08-25 06:00:00','2008-05-08 10:00:00'],
            //date:[['2007-08-12 18:00:00','2007-08-13 14:30:00'],['2010-09-14 06:00:00','2010-09-22 06:00:00'],['2010-08-25 06:00:00','2010-08-28 06:00:00'],['2008-05-08 10:00:00','2008-05-20 08:00:00']],
          },
          NSPC:{//急流过程线
            Dialog:false,
            station:"gate",
            Checked:['41814900','41814488'],
            date:['2001-01-01 01:00:00','2001-01-01 01:00:00','2001-01-01 01:00:00','2007-08-12 18:00:00','2010-09-14 06:00:00','2001-01-01 03:00:00'],
            //date:['2007-08-12 18:00:00','2010-09-14 06:00:00','2010-08-25 06:00:00','2008-05-08 10:00:00'],
            //date:[['2007-08-12 18:00:00','2007-08-13 14:30:00'],['2010-09-14 06:00:00','2010-09-22 06:00:00'],['2010-08-25 06:00:00','2010-08-28 06:00:00'],['2008-05-08 10:00:00','2008-05-20 08:00:00']],
          },
          NSC:{//闸
            Dialog:false,
            station:"gate",
            Checked:['41814900','41814488'],
            date:['2001-01-01 01:00:00','2001-01-01 01:00:00','2001-01-01 01:00:00','2007-08-12 18:00:00','2010-09-14 06:00:00','2001-01-01 03:00:00'],
            //date:['2007-08-12 18:00:00','2010-09-14 06:00:00','2010-08-25 06:00:00','2008-05-08 10:00:00'],
            //date:[['2007-08-12 18:00:00','2007-08-13 14:30:00'],['2010-09-14 06:00:00','2010-09-22 06:00:00'],['2010-08-25 06:00:00','2010-08-28 06:00:00'],['2008-05-08 10:00:00','2008-05-20 08:00:00']],
          },
          NWC:{//堰
            Dialog:false,
            station:"gate",
            Checked:['41814900','41814488'],
            date:['2001-01-01 01:00:00','2001-01-01 01:00:00','2001-01-01 01:00:00','2007-08-12 18:00:00','2010-09-14 06:00:00','2001-01-01 03:00:00'],
            //date:['2007-08-12 18:00:00','2010-09-14 06:00:00','2010-08-25 06:00:00','2008-05-08 10:00:00'],
            //date:[['2007-08-12 18:00:00','2007-08-13 14:30:00'],['2010-09-14 06:00:00','2010-09-22 06:00:00'],['2010-08-25 06:00:00','2010-08-28 06:00:00'],['2008-05-08 10:00:00','2008-05-20 08:00:00']],
          }
        },
      };
    },
    watch:{
      // 'Checked.Q.Checked':function (val) {
      //     // alert(val.length);
      //   console.log(this.$options.data().Checked['Q'])
      //   this.Form2.cd_pline.NQC=val.length;
      // }
    },
    // computed:{
    //   Checked:
    // },
    methods: {
      onSubmit() {
        console.log('submit!');
      },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
      closeDialog(item,key){
        //恢复默认值
        // alert(key)
        // this.Checked[key]=this.$options.data().Checked[key];

         item.Checked=this.onChecked[key].Checked;
        //恢复属性的值
        this.Form2.cd_pline[key]=item.Checked.length;
        item.date=this.onChecked[key].date;
        //关闭
        item.Dialog=false;
      },
      sureDialog(item,index){
        //这里是因为过程线数据的属性名与checked的属性名一致
        this.Form2.cd_pline[index]=item.Checked.length;
        //要将存储的原值改变
        this.onChecked[index].Checked=item.Checked;
        this.onChecked[index].date=item.date;
        //关闭对话框
        item.Dialog=false;
      },
      setData(checked,data){
        this.Form2.cd_pline=data;


        for(let item in checked){
          console.log(checked[item].Checked);


          this.Checked[item].Checked=checked[item].Checked;
          this.Checked[item].date=checked[item].date;
          this.onChecked[item].Checked=checked[item].Checked;
          this.onChecked[item].date=checked[item].date;

        }
        console.log(this.Checked);
      },


    }

  };
</script>

<style scoped>
  .mini-width{
    width: 200px;
  }
  .inline-input{
    display:inline-block;
    width: 240px
  }
  .timeSelect{
    /*width: 240px;*/
    /*position: absolute;*/
    /*right: 10px;*/
  }
  .checkBox{
    /*margin-top: 20px;*/
  }
  .form-mini-bottom > *{
    margin-bottom: 5px;
  }

</style>

