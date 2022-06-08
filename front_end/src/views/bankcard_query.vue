<template>
  <div>
    <div class="demo-input-suffix" style="margin: 20px 0">
  银行卡号：
  <el-input style="width:250px;margin-right: 30px;"
    placeholder="请输入银行卡号"
    v-model="bankAccount">
  </el-input>
  日期：
<el-input style="width:250px;margin-right: 30px;"
    placeholder="请输入yyyymmdd"
    v-model="serialNumber">
  </el-input>
  <el-button style="margin-left: 10px" type="primary" @click="load" round>查询</el-button>
  <el-button type="warning" @click="reset" round>重置</el-button>
</div>
<el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'" @selection-change="handleSelectionChange">
      <el-table-column prop="bankJourId" label="交易流水号" ></el-table-column>
      <el-table-column prop="bankAccount" label="银行卡号" ></el-table-column>
       <el-table-column prop="clientId" label="持卡人证件号" ></el-table-column>
      <el-table-column prop="bankChange" label="交易金额" ></el-table-column>    
      <el-table-column prop="bankCurrentBalance" label="卡内余额" ></el-table-column> 
      <el-table-column label="操作" width="250px" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleDetail(scope.row)">详情<i class="el-icon-document"></i></el-button>
          <el-button type="warning" slot="reference" @click="charge(scope.row)" >充值 <i class="el-icon-circle-plus-outline"></i></el-button>
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
    <el-dialog title="账户交易详情" :visible.sync="dialogFormVisible" width="40%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="交易流水号" label-width="150px">
          <el-input v-model="form.bankJourId" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="银行卡号" label-width="150px">
          <el-input v-model="form.bankAccount"  style="width:300px" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="持卡人证件号" label-width="150px">
          <el-input v-model="form.clientId" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="交易金额" label-width="150px">
          <el-input v-model="form.bankChange" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
         <el-form-item label="当前账户余额" label-width="150px">
          <el-input v-model="form.bankCurrentBalance" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false" type="primary">确 定</el-button>
      </div>
    </el-dialog>


     <el-dialog title="银行卡充值" :visible.sync="dialogFormV" width="40%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="银行卡号" label-width="150px">
          <el-input v-model="form.bankAccount" :disabled="true" style="width:300px" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="持卡人证件号" label-width="150px">
          <el-input v-model="form.clientId" :disabled="true" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
         <el-form-item label="当前账户余额" label-width="150px">
          <el-input v-model="form.bankCurrentBalance" :disabled="true" style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
         <el-form-item label="充值金额" label-width="150px">
          <el-input v-model="numeric"  style="width:300px" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="recharge(form,numeric)" type="primary" >确 定 充 值</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    data() {
      return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      bankAccount: "",
      serialNumber: '',
      form: {},
      numeric:"",
      dialogFormVisible: false,
      dialogFormV: false,
        
      };
    },
    created() {
    this.load()
  },
    methods: {

      load() {
      this.request.post("queries/bank", {

          pageNum: this.pageNum,
          pageSize: this.pageSize,
          bankAccount: this.bankAccount,
          serialNumber: this.serialNumber,
        
        
      }).then(res => {
        console.log(res)

        this.tableData = res.data
        this.total = res.total

      })
    },
    charge(row){
       this.form=row
       this.dialogFormV = true
    },
     recharge(form,numeric) {
      this.form = form,
      this.numeric=numeric,
      this.request.post("bank/topup", {
        bankAccount:this.form.bankAccount,
        numeric:this.numeric,
      }).then(res => {
         if (res.res=="success") {
          this.$message.success("充值成功")
          this.load()
        } else {
          this.$message.error("充值失败")
        }       
      })
      this.dialogFormV = false
    },

    reset() {
      this.bankAccount = ""
      this.serialNumber = ""
      this.load()
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
  };
</script>
<style>
.headerBg {
  background: #eee!important;
}
</style>