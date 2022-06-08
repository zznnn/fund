<template>
<div class="demo-input-suffix" style="margin: 20px 0">
  申购客户证件号：
  <el-input style="width:250px;margin-right: 30px;"
    placeholder="请输入客户证件号"
    v-model="clientId">
  </el-input>
  申购产品代码：
  <el-input style="width:250px"
    placeholder="请输入产品代码"
    v-model="fundCode">
  </el-input>
  <el-button style="margin-left: 10px" type="primary" @click="load" round>确认</el-button>
  <el-button type="warning" @click="reset" round>重置</el-button>

  <el-card class="box-card" style="margin:30px;width: 95%;" >
  <div slot="header" style="font-size: 18px;font-weight: 550;">
    <span>请填写申购交易订单</span>
    <!-- <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button> -->
  </div>
  <div  class="text item">
    <el-form :model="form" :data="formData" :inline="true" :label-position="labelPosition"  label-width="80px" >
        <el-form-item label="交易客户证件号"  label-width="150px" style="margin-left: 60px;">
          <el-input v-model="form.clientId"  :disabled="true" style="width:450px" autocomplete="off" ></el-input>
        </el-form-item>
         <el-form-item label="交易客户姓名" label-width="150px" style="margin-left: 100px;">
          <el-input v-model="form.clientName"  :disabled="true"   style="width:450px" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="交易基金产品代码" label-width="150px" style="margin-left: 60px;">
          <el-input v-model="form.fundCode"  :disabled="true"   style="width:450px" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="交易基金产品名称" label-width="150px" style="margin-left: 100px;">
          <el-input v-model="form.fundName"  :disabled="true"   style="width:450px" autocomplete="off"></el-input>
        </el-form-item>
       <el-form-item label="客户风险等级" label-width="150px" style="margin-left: 60px;">
          <el-input v-model="form.clientRisk"  :disabled="true"   style="width:450px" autocomplete="off"></el-input>
        </el-form-item>
         <el-form-item label="产品风险等级" label-width="150px" style="margin-left: 100px;">
          <el-input v-model="form.fundRisk"  :disabled="true"   style="width:450px" autocomplete="off"></el-input>
        </el-form-item>
         <!-- <el-form-item  label="交易银行卡" style="margin-left: 60px;">
         <el-select v-model="form.bankAccount" placeholder="请选择" style="width:450px">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        </el-form-item> -->
         <el-form-item label="交易银行卡" label-width="150px" style="margin-left: 60px;">
          <el-input v-model="form.bankAccount"  :disabled="true"   style="width:450px" autocomplete="off"></el-input>
        </el-form-item>
         <el-form-item label="申购金额"  label-width="150px" style="margin-left: 100px;">
          <el-input-number v-model="form.purchaseCount" :precision="1" :step="50" :max.sync="form.bankCurrentBalance" :min="0" style="width:300px"></el-input-number>
          <span class="demonstration" style="margin-left:20px">余额:</span><el-input v-model="form.bankCurrentBalance" :disabled="true" class="ml-5" style="width:70px"></el-input>
        </el-form-item>
        <el-form-item label="交易日期" label-width="150px" style="margin-left: 60px;">
          <el-input v-model="form.purchaseDate"  :disabled="true"   style="width:450px" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="" label-width="150px" style="margin-left: 420px; margin-top:45px;">
          <el-button type="success" plain  style="font-size: 15px;font-weight: 700;padding: 15px;" @click="confirm" round>提交申购申请</el-button>
        </el-form-item>
        

</el-form> 
  </div>
</el-card>


      
</div>

</template>

<script>

  export default {
    data() {
      return {
        formData: [],
        labelPosition: 'top',
        clientId: '',
         fundCode: '',
          value:'',
          form: {},
          
        }
    },
    created() {
      // this.load()
  },
  methods: {
      load() {
      this.request.post("/purchase", {
        
          clientId: this.clientId,
          fundCode: this.fundCode,
        
      }).then(res => {
        console.log(res.data[0])
        if(res.data[0]!=undefined){
          console.log(res.data[0].clientId)
          this.$set(this.form,"clientId",res.data[0].clientId);
          this.$set(this.form,"clientName",res.data[0].clientName);
          this.$set(this.form,"fundCode",res.data[0].fundCode);
          this.$set(this.form,"fundName",res.data[0].fundName);
          this.$set(this.form,"clientRisk",res.data[0].clientRisk);
          this.$set(this.form,"fundRisk",res.data[0].fundRisk);
          this.$set(this.form,"bankAccount",res.data[0].bankAccount);
          this.$set(this.form,"bankCurrentBalance",res.data[0].bankCurrentBalance);
          this.$set(this.form,"purchaseDate",res.data[0].purchaseDate);
          this.bankCurrentBalance=res.data[0].bankCurrentBalance;
        }              
      })
    
    },
  
    submit1(){
          this.request.post("/purchase/submit", {
        
          clientId: this.form.clientId,
          fundCode: this.form.fundCode,
          bankAccount:this.form.bankAccount,
          purchaseDate:this.form.purchaseDate,
          purchaseCount:this.form.purchaseCount,
        
        
      })

    },
     submit2(){

       this.request.post("/purchase/serialnum", {
        
          clientId: this.form.clientId,
          fundCode: this.form.fundCode,
          bankAccount:this.form.bankAccount,
          purchaseDate:this.form.purchaseDate,
          purchaseCount:this.form.purchaseCount
        
      }).then(res => {
         if (res) {
          // this.$message.success("申购成功")
          this.reset()
        } else {
          this.$message.error("申购失败")
        }
      })
      

    },
    open() {
         var date = new Date(this.form.purchaseDate.replace(/-/g,"/"))
         date.setDate(date.getDate()+1);  
        this.$alert('您的申购确认日期为：'+date.toLocaleString(), '申购成功提示', {
          confirmButtonText: '确定',
          // callback: action => {
          //   this.$message({
          //     type: 'info',
          //     message: `action: ${ action }`
          //   });
          // }
        });
      },
    reset() {
      this.clientId = ""
      this.fundCode = ""
      this.form={}
    },
     confirm() {
      if(this.form.clientRisk!=this.form.fundRisk){
        this.$confirm('检测到您的风险等级与产品风险等级不匹配, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '我再想想',
          type: 'warning'
        }).then(() => {
          this.submit1();
          this.submit2();
          this.open();
          this.$message({
            type: 'success',
            message: '申购成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消申购'
          });          
        });
        }
        else{
          this.submit1(),
          this.submit2(),
          this.open()
        }
      },
   
          //选择下拉框
      // getOptions(){
      //   axios.get("/car/cartypelist").then(res => {
      //     console.log(res)
      //     this.options = res.data.data.cartypeList;
      //   });
      // },


  }
    
  }
</script>

<style>
.el-card__header {
    background-color: #eee;
  } 
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 480px;
  }
</style>