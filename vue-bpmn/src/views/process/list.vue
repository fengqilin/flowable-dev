<template>
  <div>
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="name" label="日期" width="150" />
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="config(scope.row)">配置</el-button>
          <el-button type="text" size="small" @click="startProcess(scope.row)">发起测试</el-button>
        </template>
      </el-table-column>
    </el-table>

    <reqestDialog :visible.sync="requestDialogVisible" @test="test" />
    <configure :visible.sync="configureVisible" />

  </div>
</template>
<script>
import * as process from '@/api/process'
import reqestDialog from './reqeustDialog'
import configure from './configure'
export default {
  components: {
    reqestDialog,
    configure

  },
  data() {
    return {
      requestDialogVisible: false,
      configureVisible: false,
      tableData: []
    }
  },
  async  created() {
    const res = await process.getList()
    this.tableData = res.data
  },
  methods: {
    config(row) {
      this.configureVisible = true
      console.log(row)
    },
    startProcess(row) {
      this.requestDialogVisible = true
    //   this.$refs['reqestDialog'].show()
    //   console.log('start', row)
    },
    test(a) {
      console.log('list ----------', a)
    }
  }
}
</script>
