<template>
  <div>
    <el-row style="float:right">
      <el-button @click="createNew">新建</el-button>
    </el-row>
  </div>
</template>
<script>
export default {
  name: 'List',
  data() {
    return {
      a: 10
    }
  },
  methods: {
    createNew() {
      this.$router.push('/bpmn/new')
    }
  }
}
</script>
