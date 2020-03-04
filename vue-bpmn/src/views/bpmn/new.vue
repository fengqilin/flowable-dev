<template>
  <BpmnModeler v-model="modeler" />
</template>
<script>
export default {

  data() {
    return {
      diagramXML: '',
      modeler: {
        // 模型xml数据
        // model xml data
        xmlData: '',
        // svg图片数据
        // svg data
        svgImage: ''
      }
    }
  },
  watch: {
    diagramXML(val) {
      this.openDiagram(val)
    }
  },
  // 详细代码请参考源码
  // see source code for detail
  mounted() {},
  methods: {
    openDiagram(xml) {
      if (xml) {
        this.modeler.importXML(xml, function(err) {
          if (err) {
            console.error(err)
          } else {
          }
        })
        this.xmlData = xml
      } else {
        this.modeler.createDiagram()
        const _self = this
        setTimeout(() => {
          /**
           * 修改xml属性值 isExecutable = false => true
           * isExecutable = false 后端部署流程时 不会创建流程定义数据
           */
          const modelerCanvas = _self.modeler.get('canvas')
          const rootElement = modelerCanvas.getRootElement()
          const modeling = _self.modeler.get('modeling')
          // modeling.updateProperties(rootElement, {
          //   // isExecutable: true
          // });
          // 设定开始节点名称和结束节点名称
          // set StartEvent name 'start' and EndEvent name 'end'
          rootElement.children.forEach(n => {
            if (n.type === 'bpmn:StartEvent') {
              modeling.updateProperties(n, {
                name: '开始'
              })
            } else if (n.type === 'bpmn:EndEvent') {
              modeling.updateProperties(n, {
                name: '结束'
              })
            }
          })
        })
      }
    }
  }
}
</script>
