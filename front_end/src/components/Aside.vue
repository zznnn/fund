<template>
  <el-menu :default-openeds="['1', '5']" style="min-height: 100%;  overflow-x: hidden"
           background-color="rgb(48, 65, 86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
           :default-active="$route.path"
  >
    <div style="height: 60px; line-height: 60px; text-align: center">
      <img src="../assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px; right: 5px">
      <b style="color: white" v-show="logoTextShow">理财柜台交易系统</b>
    </div>
    <el-menu-item index="/home">
      
        <i class="el-icon-s-home"></i>
        <span slot="title">主页</span>
      
    </el-menu-item>
    <el-menu-item index="/product">
      
        <i class="el-icon-goods"></i>
        <span slot="title">产品管理</span>
      
    </el-menu-item>
    <el-menu-item index="/user">
      
         <i class="el-icon-s-custom"></i>
        <span slot="title" >客户管理</span>
      
    </el-menu-item >
     <el-menu-item index="/purchase">
      
        <i class="el-icon-shopping-cart-2"></i>
        <span slot="title">产品申购</span>
      
    </el-menu-item>
    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span slot="title">业务查询</span>
      </template>
      <el-menu-item index="/purchase_query">
        <i class="el-icon-sell"></i>
        <span slot="title">申购业务查询</span>
      </el-menu-item>
      <el-menu-item index="/redeem_query">
        <i class="el-icon-sold-out"></i>
        <span slot="title">赎回业务查询</span>
      </el-menu-item>
      <el-menu-item index="/bankcard_query">
        <i class="el-icon-data-line"></i>
        <span slot="title">银行卡流水查询</span>
      </el-menu-item>
    </el-submenu>
     <el-menu-item index="/clear">
         <i class="el-icon-finished"></i>
        <span slot="title" >业务清算</span>
      
    </el-menu-item >

  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props: {
    isCollapse: Boolean,
    logoTextShow: Boolean
  }
}
</script>

<style scoped>

</style>