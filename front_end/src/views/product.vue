<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入产品名称" suffix-icon="el-icon-search" v-model="fundName"></el-input>
      <el-input style="width: 200px" placeholder="请输入产品代码" suffix-icon="el-icon-search" class="ml-5" v-model="fundCode"></el-input>
      <el-input style="width: 200px" placeholder="请输入基金经理" suffix-icon="el-icon-user" class="ml-5" v-model="fundManager"></el-input>
     <el-select class="ml-5" v-model="fundRisk" clearable placeholder="请选择风险等级">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
      <el-button style="margin-left: 5px" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增产品<i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          icon-color="red"
          title="您确定批量删除这些数据吗？"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
      <!-- <el-button type="primary" style="margin-left: 5px">导入 <i class="el-icon-bottom"></i></el-button>
      <el-button type="primary">导出 <i class="el-icon-top"></i></el-button> -->
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="fundCode" label="产品代码" width="140"></el-table-column>
      <el-table-column prop="fundName" label="产品名称" width="140"></el-table-column>
      <el-table-column prop="fundRisk" label="风险等级" width="120"></el-table-column>
      <el-table-column prop="fundNetvalue" label="实时净值"></el-table-column>
       <el-table-column prop="fundManager" label="基金经理"></el-table-column>
       <el-table-column prop="fundTrustee" label="基金托管人"></el-table-column>
      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>      
          <el-button type="warning" slot="reference" @click="handelDetail(scope.row)">详情 <i class="el-icon-s-data"></i></el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog title="新建基金产品" :visible.sync="dialogFormVisible" width="50%" >
      <el-form label-width="150px" size="small">
        <el-form-item label="产品代码*">
          <el-input v-model="form.fundCode" autocomplete="off" style="width:300px"></el-input>
        </el-form-item>
        <el-form-item label="产品名称*">
          <el-input v-model="form.fundName" autocomplete="off" style="width:300px"></el-input>
        </el-form-item>
        <el-form-item label="产品净值*">
         <el-input-number v-model="form.fundNetvalue" :precision="2" :step="0.01" :max="100"></el-input-number>
        </el-form-item>
        <el-form-item label="产品发行日期*">
         <el-date-picker
          v-model="form.fundBirth"
          type="date"
          placeholder="选择日期"
          :picker-options="pickerOptions">
         </el-date-picker>
        </el-form-item>
        <el-form-item label="产品风险等级*">
         <el-select class="ml-5" v-model="form.fundRisk" clearable placeholder="请选择风险等级">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
         <el-form-item label="基金经理*">
          <el-input v-model="form.fundManager" autocomplete="off" style="width:300px"></el-input>
        </el-form-item>
         <el-form-item label="基金托管人*">
          <el-input v-model="form.fundTrustee" autocomplete="off" style="width:300px"></el-input>
        </el-form-item>
         <el-form-item label="交易币种*">
          <el-select class="ml-5" v-model="form.fundCurrency" clearable placeholder="请选择交易币种">
    <el-option
      v-for="item in options1"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
        </el-form-item>
         <el-form-item label="备注">
          <el-input  
           type="textarea"
          :rows="6" v-model="form.fundOthers" autocomplete="off" style="width:500px"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>


     <el-dialog title="基金产品净值走势图"  @open="open()" :visible.sync="dialogFormV" width="50%" >

      <div style="width:600px;height:500px;">
      <div id="myChart2" style="width:100%;height:100%;float:center;margin-left: 50px;"></div>
      </div>
      
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogFormV = false">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
//引入 echarts
import * as echarts from 'echarts'
export default {
  name: "Product",
  data() {
    return {
      myChart2: '',
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      fundName: "",
      fundCode: "",
      fundManager: "",
      fundRisk:"",
      form: {},
       num: 1,
       date: '',
      dialogFormVisible: false,
      dialogFormV: false,
      multipleSelection: [],
      options: [{
          value: '低风险',
          label: '低风险'
        }, {
          value: '中风险',
          label: '中风险'
        }, {
          value: '中高风险',
          label: '中高风险'
        },{
          value: '高风险',
          label: '高风险'
        }],
        options1: [{
          value: '人民币',
          label: '人民币'
        }, {
          value: '港币',
          label: '港币'
        }, {
          value: '美元',
          label: '美元'
        }, {
          value: '欧元',
          label: '欧元'
        },
        {
          value: '英镑',
          label: '英镑'
        },{
          value: '其它',
          label: '其它'
        }],
        value: '',
        value1: '',
        value2: '',
                pickerOptions: {
          disabledDate(time) {
            return time.getTime() > Date.now();
          },
          shortcuts: [{
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date());
            }
          }, {
            text: '昨天',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit('pick', date);
            }
          }, {
            text: '一周前',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', date);
            }
          }]
        },

    }
  },
  mounted: function () {
    this.load()
    // this.drawLine1()
  },
  // created() {
  //   this.load()
  // },
  methods: {
    load() {
      this.request.get("/product/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          fundName: this.fundName,
          fundCode: this.fundCode,
          fundManager: this.fundManager,
          fundRisk: this.fundRisk
        }
      }).then(res => {
        console.log(res)

        this.tableData = res.records
        this.total = res.total

      })
    },
    save() {
      this.request.post("/product", this.form).then(res => {
        if (res) {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row) {
      this.form = row
      this.dialogFormVisible = true
    },
    handelDetail(row){
       this.form = row
       this.dialogFormV = true
         var netV=[];
         var dateT=[];
       console.log(this.form.fundCode) 
       this.request.get("/trend/getTrend",{
          params: {
          fundCode: this.form.fundCode
        }       
       }).then(res => {

          for(var i=0;i<res.length;i++){
                       netV.push(res[i].fundNetValue);
                     }
                    for(var i=0;i<res.length;i++){
                        dateT.push(res[i].dateTime);
                      }
          // console.log(res[0].fundCode)
          this.myChart2.setOption({
          xAxis: {
          type: 'category', // 还有其他的type，可以去官网喵两眼哦
          data: dateT, // x轴数据
          name: '日期' // x轴名称
          // x轴名称样式
          // nameTextStyle: {
          //   fontWeight: 600,
          //   fontSize: 18
          // }
        },
            series: [
          {
            name: '基金净值',
            // data: [50, 48, 52, 45, 53],
            data:netV,
            type: 'line'
          }
        ]
          })
      })

    },
    del(id) {
      this.request.delete("/product/" + id).then(res => {
        if (res) {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.fundCode)  // [{}, {}, {}] => [1,2,3]
      this.request.post("/product/del/batch", ids).then(res => {
        if (res) {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    reset() {
      this.fundName = ""
      this.fundCode = ""
      this.fundManager = ""
      this.fundRisk = ""
      this.load()
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },

    drawLine1 () {
      var echarts = require('echarts');
      // 基于准备好的dom，初始化echarts实例
      this.myChart2 = this.$echarts.init(document.getElementById('myChart2'))
      // 绘制图表
      this.myChart2.setOption({
        title: {
          text: '', // 主标题
          subtext: '', // 副标题
          x: 'left' // x轴方向对齐方式
        },
        tooltip: {
          trigger: 'axis' // axis   item   none三个值
        },
        xAxis: {
          type: 'category', // 还有其他的type，可以去官网喵两眼哦
          data: [], // x轴数据
          name: '日期', // x轴名称
           axisLine: {
            lineStyle:{
                color:'#9A9BA2',//x轴的颜色
                width:3,//轴线的宽度
            }
        },
          // x轴名称样式
          // nameTextStyle: {
          //   fontWeight: 600,
          //   fontSize: 18
          // }
        },
        yAxis: {
          type: 'value',
          name: '基金净值', // y轴名称
          axisLine: {
            lineStyle:{
            color:'#9A9BA2',// y轴的颜色
            width:3,//y轴线的宽度
    }
        },
          // y轴名称样式
          // nameTextStyle: {
          //   fontWeight: 600,
          //   fontSize: 18
          // }
        },
        legend: {
          orient: 'vertical',
          x: 'center',
          y: 'top',
          data: ['基金净值']
        },
        series: [
          {
            name: '基金净值',
            // data: [50, 48, 52, 45, 53],
            data:[],
            type: 'line',
             areaStyle: {  //覆盖区域的渐变色
              normal: {
                // color: '#091e3b', //改变区域颜色
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0, color: '#0F89D7' // 0% 处的颜色
                  }, {
                    offset: 0.5, color: '#3FCAFF' // 100% 处的颜色
                  }, {
                    offset: 1, color: '#fff' // 100% 处的颜色
                  }]
                ),  //背景渐变色
              }
            },

            // symbol: 'circle',//折线点设置为实心点
            symbolSize: 6,   //折线点的大小
            itemStyle: {
              normal: {
          //  color: "#386db3",//折线点的颜色
           lineStyle: {
           color: "#386db3"//折线的颜色
          }

          }
      }
          
          }
        ]
      });

    },

       open(){
        this.$nextTick(() => {
        //  执行echarts方法
          this.drawLine1 ()
        })
      }
   
  }
}
</script>


<style>
.headerBg {
  background: #eee!important;
}
</style>