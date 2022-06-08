<template>
  <div style="line-height: 60px; display: flex">
    <div style="flex: 1;">
      <span :class="collapseBtnClass" style="cursor: pointer; font-size: 18px" @click="collapse"></span>

     <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px">
      <el-breadcrumb-item :to="'/home'">首页</el-breadcrumb-item>
      <el-breadcrumb-item>{{ currentPathName }}</el-breadcrumb-item>
  </el-breadcrumb>
    </div>
    <el-dropdown style="width: 100px; cursor: pointer">
      <span>永不爆栈组</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
      <router-link :to="{path: 'home'}"><el-dropdown-item style="font-size: 14px; padding: 5px 0">关于我们</el-dropdown-item></router-link>
      <router-link :to="{path: 'login'}"><el-dropdown-item style="font-size: 14px; padding: 5px 0">退出</el-dropdown-item></router-link>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "Header",
  props: {
    collapseBtnClass: String,
   
  },
  
  computed: {
    currentPathName () {
      return this.$store.state.currentPathName;　　//需要监听的数据
    }
  },
  methods:{
    collapse(){
      this.$emit("asideCollapse")
    }

  },
  watch: {
    currentPathName (newVal, oldVal) {
      console.log(newVal)
    }
  }
  
}

</script>



<style scoped>

</style>
