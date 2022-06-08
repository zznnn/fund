<template>
  <div>
  <el-steps :active="0" finish-status="success" simple style="margin-top: 20px">
  <el-step title="日期更新 1" ></el-step>
  <el-step title="行情导入 2" ></el-step>
  <el-step title="数据导出 3" ></el-step>
  <el-step title="处理确认 4" ></el-step>
</el-steps>

<div style="padding:50px">
<el-card class="box-card" style="width:100%;" >
  <div slot="header" class="clearfix" >
    <span>更新交易日</span>
    <router-link :to="{path: 'clear2'}">
    <el-button style="float: right; " type="success" icon="el-icon-right" round>下一步</el-button>
    </router-link>
  </div>
  <div>
   当前交易日日期：
    <el-input v-model="dateTime"  style="width:250px" autocomplete="off"></el-input>
    <el-button style="margin-left: 15px" type="primary" @click="update" round>更新交易日</el-button>
  </div>
</el-card>
</div>

  </div>
</template>

<script>


  export default {
    data() {
      return {
        dateTime: '',

      };
    },
    created() {
    this.load()
  },
   methods: {
       load() {
         this.request.get("/datetime", {

         }).then(res => {
        this.dateTime = res.dateTime

      })
       },
       update() {
         this.request.put("/datetime/addDay", {

        }).then(this.request.get("/datetime",{}).then(res => {
        this.dateTime = res.dateTime
        this.load()
      }))
       }

   }
  };
</script>