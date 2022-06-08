<template>
  <div >
   <div class="demo-input-suffix" style="margin: 20px 0">
  客户证件号：
  <el-input style="width:250px;margin-right: 30px;"
    placeholder="请输入客户证件号"
    v-model="clientId">
  </el-input>
  产品代码：
  <el-input style="width:250px"
    placeholder="请输入产品代码"
    v-model="fundCode">
  </el-input>
  <el-button style="margin-left: 10px" type="primary" @click="load" round>查询</el-button>
  <el-button type="warning" @click="reset" round>重置</el-button>
</div>
<el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
      <el-table-column prop="redemId" label="交易流水号" ></el-table-column>
      <el-table-column prop="fundName" label="产品名称" ></el-table-column>
      <el-table-column prop="clientName" label="客户姓名" ></el-table-column>
      <el-table-column prop="redemShare" label="赎回份额" ></el-table-column>
      <el-table-column prop="redemCount" label="实际收益" ></el-table-column>
      <el-table-column prop="redemStatus" label="交易状态" ></el-table-column>
      <el-table-column label="操作" width="250px" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleDetail(scope.row)">详情<i class="el-icon-document"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="您确定撤单吗？"
              @confirm="del(scope.row.redemId)"
          >
            <el-button type="danger" :disabled="scope.row.redemStatus == '申请中' ? false : true" slot="reference">撤单 <i class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[ 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <el-dialog title="交易详情" :visible.sync="dialogFormVisible" width="40%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="交易流水号" label-width="150px">
          <el-input v-model="form.redemId" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="交易客户证件号" label-width="150px">
          <el-input v-model="form.clientId"  style="width:300px" autocomplete="off" ></el-input>
        </el-form-item>
         <el-form-item label="交易客户证姓名" label-width="150px">
          <el-input v-model="form.clientName" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="交易基金产品代码" label-width="150px">
          <el-input v-model="form.fundCode" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="交易基金产品名称" label-width="150px">
          <el-input v-model="form.fundName" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="交易银行卡号" label-width="150px">
          <el-input v-model="form.bankAccount" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
         <el-form-item label="赎回份额" label-width="150px">
          <el-input v-model="form.redemShare" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
         <el-form-item label="剩余份额" label-width="150px">
          <el-input v-model="form.redemCurrentShare" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
         <el-form-item label="实际收益" label-width="150px">
          <el-input v-model="form.redemCount" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
         <el-form-item label="收益到帐日期" label-width="150px">
          <el-input v-model="form.redemEstimatedDate" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
         <el-form-item label="交易状态" label-width="150px">
          <el-input v-model="form.redemStatus" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="交易日期" label-width="150px">
          <el-input v-model="form.redemDate" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false" type="primary">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
    name: "redem_query",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      clientId: "",
      fundCode: "",
      form: {},
      dialogFormVisible: false,
    }
  },
  created() {
    this.load()
  },
   methods: {
       load() {
      this.request.post("queries/redem", {
        
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          clientId: this.clientId,
          fundCode: this.fundCode,
        
      }).then(res => {
        console.log(res)

        this.tableData = res.data
        this.total = res.total

      })
    },
    reset() {
      this.clientId = ""
      this.fundCode = ""
      this.load()
    },
    del(id) {
      this.request.post("/delete/redem",{
       serial:id
      }).then(res => {
        console.log(res.res)
        if (res.res=="success") {
          this.$message.success("撤单成功")
          this.load()
        } else {
          this.$message.error("撤单失败")
        }
      })
    },
    handleDetail(row) {
      this.form = row
      this.dialogFormVisible = true
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
    }
   }
}
</script>
<style>
.headerBg {
  background: #eee!important;
}
</style>