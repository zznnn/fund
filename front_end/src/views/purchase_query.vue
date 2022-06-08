<template>
<div>
<div class="demo-input-suffix" style="margin: 20px 0">
  客户证件号：
  <el-input style="width:250px;margin-right: 30px;"
    placeholder="请输入客户证件号"
    v-model="client_id">
  </el-input>
  产品代码：
  <el-input style="width:250px"
    placeholder="请输入产品代码"
    v-model="fund_code">
  </el-input>
  <el-button style="margin-left: 10px" type="primary" @click="load" round>查询</el-button>
  <el-button type="warning" @click="reset" round>重置</el-button>
</div>
<el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
      <el-table-column prop="purchaseId" label="交易流水号" ></el-table-column>
      <el-table-column prop="fundCode" label="产品代码" ></el-table-column>
      <el-table-column prop="clientId" label="客户证件号" ></el-table-column>
      <el-table-column prop="purchaseCount" label="申购金额" ></el-table-column>
      <el-table-column prop="purchaseShare" label="持有份额" ></el-table-column>
      <el-table-column prop="purchaseStatus" label="交易状态" ></el-table-column>
      <el-table-column label="操作" width="300px" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleDetail(scope.row)">详情<i class="el-icon-document"></i></el-button>
          <el-button type="primary" :disabled="scope.row.purchaseStatus == '申请中' ? true : false" @click="handleRedeem(scope.row)"> 赎回 <i class="el-icon-remove-outline"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="您确定撤单吗？"
              @confirm="del(scope.row.purchaseId)"
          >
            <el-button type="danger" :disabled="scope.row.purchaseStatus == '申请中' ? false : true" slot="reference" >撤单 <i class="el-icon-remove-outline"></i></el-button>
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
          <el-input v-model="form.purchaseId" style="width:300px" autocomplete="off"></el-input>
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
         <el-form-item label="申购金额" label-width="150px">
          <el-input v-model="form.purchaseCount" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
         <el-form-item label="申购份额" label-width="150px">
          <el-input v-model="form.purchaseShare" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
         <el-form-item label="交易状态" label-width="150px">
          <el-input v-model="form.purchaseStatus" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="交易日期" label-width="150px">
          <el-input v-model="form.purchaseDate" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false" type="primary">确 定</el-button>
      </div>
    </el-dialog>




     <el-dialog title="填写赎回申请" :visible.sync="dialogFormV" width="40%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="交易流水号" label-width="150px">
          <el-input v-model="form.purchaseId" style="width:300px" autocomplete="off"></el-input>
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
         <el-form-item label="申购金额" label-width="150px">
          <el-input v-model="form.purchaseCount" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
         <el-form-item label="申购份额" label-width="150px">
          <el-input v-model="form.purchaseShare" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
         <el-form-item label="交易状态" label-width="150px">
          <el-input v-model="form.purchaseStatus" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
         <el-form-item label="赎回份额" label-width="150px">
          <el-input-number v-model="form.redemShare" :precision="4" :step="10" :max.sync="form.purchaseShare" :min="0" style="width:300px"></el-input-number>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="Redem(form),Redem1(form),open()" type="primary">确 定</el-button>
      </div>
    </el-dialog>
    

</div>
</template>

<script>

export default {
    name: "purchase_query",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      client_id: "",
      fund_code: "",
      form: {},
      redemDate :"",
      dialogFormVisible: false,
      dialogFormV: false,
    }
  },
  created() {
    this.load()
  },
   methods: {
       load() {
      this.request.post("/queries/purchase", {
        
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          clientId: this.client_id,
          fundCode: this.fund_code,
        
      }).then(res => {
        console.log(res)

        this.tableData = res.data
        this.total = res.total

      })
    },
    reset() {
      this.client_id = ""
      this.fund_code = ""
      this.load()
    },
    handleDetail(row) {
      this.form = row
      this.dialogFormVisible = true
    },
    handleRedeem(row) {
      this.form = row
      this.dialogFormV = true
    },

    Redem(form){
      this.form = form
      this.request.post("/redem/submit",{
       clientId:this.form.clientId,
       fundCode:this.form.fundCode,
       bankAccount:this.form.bankAccount,
       redemShare:this.form.redemShare,
       
      })     
     
    },

    
    Redem1(form){
      this.form = form
      this.request.post("/redem/serialnum",{
       clientId:this.form.clientId,
       fundCode:this.form.fundCode,
       bankAccount:this.form.bankAccount,
       redemShare:this.form.redemShare,
       purchaseShare:this.form.purchaseShare
      }).then(res => {
        if (res) {
          this.$message.success("赎回申请成功")
          this.load()
        } else {
          this.$message.error("赎回申请失败")
        }
      })

      this.dialogFormV = false
     
    },
     open() {
       var date = new Date();
       console.log("here")
       this.request.post("/getDate",{}).then(res => {
         console.log(res.data[0].redemDate)
         date=new Date((res.data[0].redemDate).replace(/-/g,"/"))
         date.setDate(date.getDate()+2);  
         this.$alert('您的收益到账日期为：'+date.toLocaleString(), '赎回成功提示', {
         confirmButtonText: '确定',
      })
     
        });
      },
    del(id) {
      this.request.post("/delete/purchase",{
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