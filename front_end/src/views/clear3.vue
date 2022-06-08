<template>
  <div >
        <el-steps :active="2" finish-status="success" simple style="margin-top: 20px">
  <el-step title="日期更新 1" ></el-step>
  <el-step title="行情导入 2" ></el-step>
  <el-step title="数据导出 3" ></el-step>
  <el-step title="处理确认 4" ></el-step>
</el-steps>

<el-button style="margin-top: 20px; font-size: medium;" type="primary"  @click="updatep()">导出清算结果</el-button> 
<router-link :to="{path: 'clear2'}">
    <el-button style="margin-left: 10px; margin-top: 20px;font-size: medium;" type="success" plain  icon="el-icon-back" round>上一步</el-button>
    </router-link> 
 <router-link :to="{path: 'clear4'}">
    <el-button style="float: right; margin-top: 20px;font-size: medium;" type="success" icon="el-icon-right" round>下一步</el-button>
    </router-link>    
<div>&nbsp;</div>
<el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
      <el-table-column prop="purchaseId" label="申购流水号" ></el-table-column>
      <el-table-column prop="fundCode" label="产品代码" ></el-table-column>
      <el-table-column prop="clientId" label="客户证件号" ></el-table-column>
      <el-table-column prop="purchaseCount" label="申购金额" ></el-table-column>
      <el-table-column prop="purchaseShare" label="持有份额" ></el-table-column>
      <el-table-column prop="purchaseStatus" label="交易状态" ></el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5,10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <el-table :data="tableData1" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
      <el-table-column prop="redemId" label="赎回流水号" ></el-table-column>
      <el-table-column prop="fundCode" label="产品代码" ></el-table-column>
      <el-table-column prop="clientId" label="客户证件号" ></el-table-column>
      <el-table-column prop="redemShare" label="赎回份额" ></el-table-column>
      <el-table-column prop="redemCount" label="实际收益" ></el-table-column>
      <el-table-column prop="redemStatus" label="交易状态" ></el-table-column>
       
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange1"
          @current-change="handleCurrentChange1"
          :current-page="pageNum1"
          :page-sizes="[5,10, 20]"
          :page-size="pageSize1"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total1">
      </el-pagination>
    </div>
    <el-table :data="tableData2" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
      <el-table-column prop="bankJourId" label="交易流水号" ></el-table-column>
      <el-table-column prop="bankAccount" label="银行卡号" ></el-table-column>
      <el-table-column prop="bankChange" label="变化金额" ></el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange2"
          @current-change="handleCurrentChange2"
          :current-page="pageNum2"
          :page-sizes="[5,10, 20]"
          :page-size="pageSize2"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total2">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
   name: "clear3",
   data() {
     return {
       tableData: [],
       tableData1: [],
        tableData2: [],
       total: 0,
       total1: 0,
       total2: 0,
       pageNum: 1,
       pageNum1: 1,
       pageNum2: 1,
       pageSize: 5,
       pageSize1: 5,
       pageSize2: 5,
     }
   },
    created() {
    // this.load()
  },
   methods: {
       load() {
         this.request.get("/settlement/purchasePage",{
          params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
        }
         }).then(res => {

        this.tableData = res.data
        this.total = res.total

      })
       },

       load1() {
         this.request.get("/settlement/redemPage",{
          params: {
          pageNum: this.pageNum1,
          pageSize: this.pageSize1,
        }
         }).then(res => {

        this.tableData1 = res.data
        this.total1 = res.total

      })
       },

       load2() {
         this.request.get("/settlement/bankPage",{
          params: {
          pageNum: this.pageNum2,
          pageSize: this.pageSize2,
        }
         }).then(res => {

        this.tableData2 = res.data
        this.total2 = res.total

      })
       },

       updatep() {
         this.request.put("/settlement/receivePurchase",{          
         })
         this.load()
         this.updater()
         this.load1()
         this.updateb()
         this.load2()
       },

        updater() {
         this.request.put("/settlement/receiveRedem",{          
         })
         this.load1()
       },

        updateb() {
         this.request.put("/settlement/bankGetCount",{          
         })
         this.load2()
       },
      handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
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
    
     handleSizeChange1(pageSize) {
      console.log(pageSize)
      this.pageSize1 = pageSize
      this.load1()
    },
    handleCurrentChange1(pageNum) {
      console.log(pageNum)
      this.pageNum1 = pageNum
      this.load1()
    },

     handleSizeChange2(pageSize) {
      console.log(pageSize)
      this.pageSize2 = pageSize
      this.load2()
    },
    handleCurrentChange2(pageNum) {
      console.log(pageNum)
      this.pageNum2 = pageNum
      this.load2()
    }

   }
   
}
</script>

