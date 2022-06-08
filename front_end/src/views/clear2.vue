<template>
  <div>
    <el-steps :active="1" finish-status="success" simple style="margin-top: 20px">
  <el-step title="日期更新 1" ></el-step>
  <el-step title="行情导入 2" ></el-step>
  <el-step title="数据导出 3" ></el-step>
  <el-step title="处理确认 4" ></el-step>
</el-steps>

<el-button style="margin-top: 20px;margin-left: 10px ; font-size: medium;" type="primary"  @click="download()">导入行情</el-button> 
 <router-link :to="{path: 'clear'}">
    <el-button style="margin-left: 10px; margin-top: 20px;font-size: medium;" type="success" plain  icon="el-icon-back" round>上一步</el-button>
    </router-link> 
 <router-link :to="{path: 'clear3'}">
    <el-button style="float: right; margin-top: 20px;font-size: medium;" type="success" icon="el-icon-right" round>下一步</el-button>
    </router-link>    
<div>&nbsp;</div>
 <el-col :span="3.5" style="padding-left:30px;">
 <el-table  :data="tableData" border stripe :header-cell-class-name="'headerBg'"  style="width:570px">
      
      <el-table-column prop="fundCode" label="产品代码" width="180"></el-table-column>
      <el-table-column prop="fundName" label="产品名称" width="180"></el-table-column>
      <el-table-column prop="fundNetValue" label="原净值" ></el-table-column>
    </el-table>
         <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[10,20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    </el-col>
     <el-col :span="3.5" style="padding-left:30px;">
    <el-table :data="tableData1" border stripe :header-cell-class-name="'headerBg'" style="width:570px" >
      
      <el-table-column prop="fundCode" label="产品代码" width="180"></el-table-column>
      <el-table-column prop="fundName" label="产品名称" width="180"></el-table-column>
      <el-table-column prop="fundNetValue" label="新净值"></el-table-column>
    </el-table>
     <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange1"
          @current-change="handleCurrentChange1"
          :current-page="pageNum1"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize1"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total1">
      </el-pagination>
    </div>
    </el-col>

  </div>
</template>

<script>
export default {
   name: "clear2",
   data() {
     return {
       tableData: [],
       tableData1: [],
       total: 0,
       total1: 0,
       pageNum: 1,
       pageNum1: 1,
       pageSize: 10,
       pageSize1: 10,
     }
   },
    created() {
    this.load()
  },
   methods: {
       load() {
         this.request.get("/fund/page",{
          params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
        }
         }).then(res => {

        this.tableData = res.data
        this.total = res.total

      })
       },
        download() {
         this.request.put("/fund/update",{          
         }).then(this.load1())
         this.load1()
       },

       load1() {
         this.request.get("/fund/page",{
          params: {
          pageNum: this.pageNum1,
          pageSize: this.pageSize1,
        }
         }).then(res => {

        this.tableData1 = res.data
        this.total1 = res.total

      })
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
    handleSelectionChange1(val) {
      console.log(val)
      this.multipleSelection = val
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
    }

   }
   
}
</script>

