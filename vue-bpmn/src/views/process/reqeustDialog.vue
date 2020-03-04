<template>

  <el-dialog v-bind="$attrs" title="请个假" v-on="$listeners" @open="onOpen" @close="onClose">
    <el-row :gutter="15">
      <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
        <el-col :span="13">
          <el-form-item label="请假天数" prop="days">
            <el-input-number v-model="formData.days" placeholder="请假天数" />
          </el-form-item>
        </el-col>
        <el-col :span="13">
          <el-form-item label="请假人" prop="requestUserId">
            <el-select
              v-model="formData.requestUserId"
              placeholder="选择请请假人"
              clearable
              :style="{width: '100%'}"
            >
              <el-option
                v-for="(item, index) in requestUserIdOptions"
                :key="index"
                :label="item.label"
                :value="item.value"
                :disabled="item.disabled"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="13">
          <el-form-item label="请假原因" prop="reason">
            <el-input
              v-model="formData.reason"
              type="textarea"
              placeholder="输入请请假原因"
              :autosize="{minRows: 4, maxRows: 4}"
              :style="{width: '100%'}"
            />

          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
    <div slot="footer">
      <el-button @click="close">取消</el-button>
      <el-button type="primary" @click="handelConfirm">确定</el-button>

    </div>
  </el-dialog>

</template>
<script>

export default {
  components: { },
  inheritAttrs: false,
  props: [],
  data() {
    return {

      formData: {

        days: undefined,
        requestUserId: undefined,
        reason: undefined
      },
      rules: {
        days: [{
          required: true,
          message: '请假天数',
          trigger: 'blur'
        }],
        requestUserId: [{
          required: true,
          message: '选择请请假人',
          trigger: 'change'
        }],
        reason: [{
          required: true,
          message: '输入请请假原因',
          trigger: 'blur'
        }]
      },
      requestUserIdOptions: [{
        'label': '100',
        'value': 100
      }, {
        'label': '101',
        'value': 102
      }, {
        'label': '103',
        'value': 103
      }, {
        'label': '104',
        'value': 104
      }, {
        'label': '105',
        'value': 105
      }]
    }
  },
  computed: {},
  watch: {},
  created() {
  },

  mounted() {},
  methods: {

    onOpen() {},
    onClose() {
      this.$refs['elForm'].resetFields()
    },
    close() {
      console.log('update:visible:false')
      this.$emit('update:visible', false)
    },
    handelConfirm() {
      console.log(this.$listeners)
      this.$emit('test', '00996565')
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
