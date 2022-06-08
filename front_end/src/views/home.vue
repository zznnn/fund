<template>
  <!-- <div class="about">
    <h1>这是首页</h1>
  </div> -->
<div class="background">
    <img :src="imgSrc" width="100%" height="100%" alt="" />
</div>
</template>

<script>
  export default {
    data() {
      return {
        imgSrc:require('../assets/bg.png')
      }
    }
  }
  </script>
<style>
  .background{
    position: relative;
    width:100%;  
    height:100%;  /**宽高100%是为了图片铺满屏幕 */
    background-size: cover;

}
 
</style>