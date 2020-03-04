<template>
  <div>
    <el-dialog v-bind="$attrs" title="配置" v-on="$listeners" @open="onOpen" @close="onClose">
      <el-row :gutter="15">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="要素配置" name="first">用户管理</el-tab-pane>
          <el-tab-pane label="环节配置" name="second">配置管理</el-tab-pane>
        </el-tabs>
      </el-row>
      <div slot="footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  components: {},
  inheritAttrs: false,
  props: [],
  data() {
    return {
      formData: {},
      rules: {},
      activeName: 'first'
    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {},
  methods: {
    handleClick() {

    },
    onOpen() {},
    onClose() {
    //   this.$refs['elForm'].resetFields()
    },
    close() {
      this.$emit('update:visible', false)
    },
    handelConfirm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        this.close()
      })
    }
  }
}

</script>
<style>
</style>
