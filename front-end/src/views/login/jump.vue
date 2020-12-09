<template>
  <div class="dashboard-container">
  </div>
</template>

<script>
  import store from '../../store'
  export default{
    name: 'Jumpboard',
    created: function(){
      if(store.getters.role === '学生客户'){
        this.$router.replace('/maml')
      }
      else(
        this.$router.replace('/')
      )
    }
  }

</script>

<style>
</style>
