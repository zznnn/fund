<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入客户证件号" suffix-icon="el-icon-search" v-model="clientId"></el-input>
      <el-input style="width: 200px" placeholder="请输入客户姓名" suffix-icon="el-icon-user" class="ml-5" v-model="clientName"></el-input>
      <el-select class="ml-5" v-model="clientRisk" clearable placeholder="请选择风险等级">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
  <el-select class="ml-5" v-model="clientOrganFlag" clearable placeholder="请选择机构标志">
    <el-option
      v-for="item in options1"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
      <el-button style="margin-left: 5px" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">客户开户 <i class="el-icon-circle-plus-outline"></i></el-button>
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

    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
       <el-table-column type="selection" width="55"></el-table-column>
       <el-table-column prop="clientId" label="证件号码" width="150"></el-table-column>
      <el-table-column prop="clientName" label="客户姓名" width="140"></el-table-column>
      <el-table-column prop="clientGender" label="客户性别" width="140"></el-table-column>
      <el-table-column prop="clientRisk" label="风险等级"></el-table-column>
      <el-table-column prop="clientOrganFlag" label="机构标志"></el-table-column>
      <el-table-column prop="clientPhnumber" label="联系电话"></el-table-column>
      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.clientId)"
          >
            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
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

    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="50%" >
      <el-form label-width="150px" size="small">        
        <el-form-item label="客户姓名">
          <el-input v-model="form.clientName" autocomplete="off" style="width:300px"></el-input>          
        </el-form-item>
        <el-form-item label="客户性别" >
            <label class="radio-inline sex">
            <input type="radio" name="sex" id="man" value="男"
                    v-model="form.clientGender"> 男
          </label>
          <label class="radio-inline" style="margin-left:10px">
            <input type="radio" name="sex" id="women" value="女"
                  v-model="form.clientGender"> 女
          </label>
        </el-form-item>
        <el-form-item label="机构标志">
          <el-select  v-model="form.clientOrganFlag" clearable placeholder="请选择机构标志">
    <el-option
      v-for="item in options1"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
        </el-form-item>
        <el-form-item label="客户年龄">
           <el-input-number v-model="form.clientAge" controls-position="right" @change="handleChange" :min="1" :max="100"></el-input-number>
        </el-form-item>
        <el-form-item label="证件类型">
         <el-select  v-model="form.clientIdKind" clearable placeholder="请选择证件类型">
    <el-option
      v-for="item in options2"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
        </el-form-item>
         <el-form-item label="证件号">
          <el-input v-model="form.clientId" autocomplete="off" style="width:500px"></el-input>
        </el-form-item>
         <el-form-item  label="客户风险等级">
         <el-select class="ml-5" v-model="form.clientRisk" clearable placeholder="请选择风险等级">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        </el-form-item>
         <el-form-item label="联系方式">
          <el-input v-model="form.clientPhnumber" autocomplete="off" style="width:400px"></el-input>
        </el-form-item>
       <el-form-item label="绑定银行卡号">
          <el-input v-model="form.bankAccount" autocomplete="off" style="width:400px"></el-input>
        </el-form-item>
          <el-form-item label="充值">
          <el-input v-model="form.bankCurrentBalance" autocomplete="off" style="width:400px"></el-input>
        </el-form-item>
      </el-form>


      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save" >确 定</el-button>
      </div>
    </el-dialog>



     <el-dialog title="用户信息" :visible.sync="dialogFormV" width="50%" >
      <el-form label-width="150px" size="small">        
        <el-form-item label="客户姓名">
          <el-input v-model="form.clientName" autocomplete="off" style="width:300px"></el-input>          
        </el-form-item>
        <el-form-item label="客户性别" >
            <label class="radio-inline sex">
            <input type="radio" name="sex" id="man" value="男"
                    v-model="form.clientGender"> 男
          </label>
          <label class="radio-inline" style="margin-left:10px">
            <input type="radio" name="sex" id="women" value="女"
                  v-model="form.clientGender"> 女
          </label>
        </el-form-item>
        <el-form-item label="机构标志">
          <el-select  v-model="form.clientOrganFlag" clearable placeholder="请选择机构标志">
    <el-option
      v-for="item in options1"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
        </el-form-item>
        <el-form-item label="客户年龄">
           <el-input-number v-model="form.clientAge" controls-position="right" @change="handleChange" :min="1" :max="100"></el-input-number>
        </el-form-item>
        <el-form-item label="证件类型">
         <el-select  v-model="form.clientIdKind" clearable placeholder="请选择证件类型">
    <el-option
      v-for="item in options2"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
        </el-form-item>
         <el-form-item label="证件号">
          <el-input v-model="form.clientId" autocomplete="off" style="width:400px"></el-input>
        </el-form-item>
         <el-form-item  label="客户风险等级">
         <el-select class="ml-5" v-model="form.clientRisk" clearable placeholder="请选择风险等级">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        </el-form-item>
         <el-form-item label="联系方式">
          <el-input v-model="form.clientPhnumber" autocomplete="off" style="width:400px"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormV = false">取 消</el-button>
        <el-button type="primary" @click="update" >确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "User",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      clientId: "",
      clientName: "",
      clientRisk: "",
      clientOrganFlag: "",
      form: {},
      dialogFormVisible: false,
      dialogFormV: false,
      radio: '1',
      num: 1,
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
        value: '',
        options1: [{
          value: '个人',
          label: '个人'
        }, {
          value: '企业',
          label: '企业'
        }, {
          value: '其它',
          label: '其它'
        }],
        value1: '',
         options2: [{
          value: '大陆居民身份证',
          label: '大陆居民身份证'
        }, {
          value: '港澳居民来往内地通行证',
          label: '港澳居民来往内地通行证'
        }, {
          value: '选台湾居民来往内地通行证',
          label: '台湾居民来往内地通行证'
        },
        {
          value: '其它法定有效证件',
          label: '其它法定有效证件'
        }],
        value2: '',
        value3: '',
        dynamicValidateForm: {
          domains: [{
            value: ''
          }],
          must: ''
        }
    }
  },
  created() {
    this.load()
  },
  methods: {

    load() {
      this.request.get("/user/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          clientId: this.clientId,
          clientName: this.clientName,
          clientRisk: this.clientRisk,
          clientOrganFlag:this.clientOrganFlag
        }
      }).then(res => {
        console.log(res)

        this.tableData = res.records
        this.total = res.total

      })
    },
    save() {
      this.request.post("/user/save", this.form).then(res => {
        if (res) {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },

    update() {
      this.request.post("/user/update", this.form).then(res => {
        if (res) {
          this.$message.success("更新成功")
          this.dialogFormV = false
          this.load()
        } else {
          this.$message.error("更新失败")
        }
      })
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row) {
      this.form = row
      this.dialogFormV = true
    },
    del(id) {
      this.request.delete("/user/" + id).then(res => {
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
      let ids = this.multipleSelection.map(v => v.clientId)  // [{}, {}, {}] => [1,2,3]
      this.request.post("/user/del/batch", ids).then(res => {
        if (res) {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    reset() {
      this.clientName = ""
      this.clientId = ""
      this.clientRisk = ""
      this.clientOrganFlag = ""
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
     handleChange(value) {
        console.log(value);
      }
    
  }
}
</script>


<style>
.headerBg {
  background: #eee!important;
}
</style>