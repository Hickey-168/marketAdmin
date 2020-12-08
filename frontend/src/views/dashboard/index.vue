<!--<template>-->
<!--  <div class="dashboard-container">-->
<!--    -->
<!--  </div>-->
<!--</template>-->
<!--<script>-->
<!--  export default {-->
<!--    name: 'dashboard',-->
<!--    data() {-->
<!--      return {}-->
<!--    },-->
<!--  }-->
<!--</script>-->
<template>
  <div class="dashboard-container">
      <div class="line"></div>
      <el-menu
        :default-active="activeIndex2"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b">
        <el-menu-item index="1">处理中心</el-menu-item>
        <el-submenu index="2">
          <template slot="title">我的工作台</template>
          <el-menu-item index="2-1">我的办公桌</el-menu-item>
          <el-menu-item index="2-2">员工管理</el-menu-item>
          <el-menu-item index="2-3">物流商列表</el-menu-item>
          <el-submenu index="2-4">
            <template slot="title">项目管理</template>
            <el-menu-item index="2-4-1">查看日程</el-menu-item>
            <el-menu-item index="2-4-2">查看项目资金调度</el-menu-item>
            <el-menu-item index="2-4-3">查看项目整体规划</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-menu-item index="3" disabled>消息中心</el-menu-item>
        <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>
      </el-menu>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1'
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>
