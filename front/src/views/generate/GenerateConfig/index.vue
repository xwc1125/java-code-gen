<template>
  <div class="code-gen">
    <el-form ref="genForm" class="gen-form" :model="clientParam" size="mini" label-width="150px">
      <el-form-item label="选择数据源" prop="datasourceConfigId" :rules="{required: true, message: '请选择数据源'}">
        <el-select
          v-model="clientParam.datasourceConfigId"
          placeholder="选择数据源"
          @change="onDataSourceChange"
        >
          <el-option
            v-for="item in datasourceConfigList"
            :key="item.id"
            :label="getDatasourceLabel(item)"
            :value="item.id"
          >
            <span style="float: left">{{ getDatasourceLabel(item) }}</span>
            <span style="float: right; color: #8492a6; font-size: 13px">
              <el-tooltip placement="top" content="Duplicate">
                <el-link type="primary" icon="el-icon-document-copy" style="margin-right: 20px;" @click.stop="onDataSourceDuplicate(item)"></el-link>
              </el-tooltip>
              <el-link type="primary" icon="el-icon-edit" style="margin-right: 20px;" @click.stop="onDataSourceUpdate(item)"></el-link>
              <el-link type="danger" icon="el-icon-delete" @click.stop="onDataSourceDelete(item)"></el-link>
            </span>
          </el-option>
        </el-select>
        <el-button type="text" @click="onDataSourceAdd">新建数据源</el-button>
      </el-form-item>
      <el-form-item v-show="showTable" label="包名（package）">
        <el-input v-model="clientParam.packageName" placeholder="可选，如：cn.studyjava.xxx" show-word-limit maxlength="100" />
      </el-form-item>
      <el-form-item v-show="showTable" label="删除表前缀">
        <el-input v-model="clientParam.delTablePrefix" placeholder="可选，如：sys_user对应Java类为User(多前缀逗号隔开)" show-word-limit maxlength="100" />
      </el-form-item>
      <el-form-item v-show="showTable" label="删除字段前缀">
        <el-input v-model="clientParam.delFieldPrefix" placeholder="可选，如：c_user_info对应字段userInfo(多前缀逗号隔开)" show-word-limit maxlength="100" />
      </el-form-item>
      <el-form-item v-show="showTable" label="编辑者">
        <el-input v-model="clientParam.author" placeholder="可选" show-word-limit maxlength="20" />
      </el-form-item>
    </el-form>
    <el-row v-show="showTable" :gutter="20">
      <el-col :span="12">
        <h4>选择表</h4>
        <el-input
          v-model="tableSearch"
          prefix-icon="el-icon-search"
          clearable
          size="mini"
          placeholder="过滤表"
          style="margin-bottom: 10px;width: 100%;"
        />
        <el-table
          :data="tableListData"
          border
          :cell-style="cellStyleSmall()"
          :header-cell-style="headCellStyleSmall()"
          :row-class-name="tableRowClassName"
          @selection-change="onTableListSelect"
        >
          <el-table-column
            type="selection"
          />
          <el-table-column
            prop="tableName"
            label="表名"
          />
        </el-table>
      </el-col>
      <el-col :span="12" id="templateSelect">
        <h4>选择模板</h4>
        <el-select
          v-model="groupId"
          placeholder="选择模板所在组"
          size="mini"
          style="margin-bottom: 10px; width: 100%;"
        >
          <el-option
            v-for="item in groupData"
            :key="item.id"
            :label="item.groupName"
            :value="item.id"
          >
            {{ item.groupName }}
          </el-option>
        </el-select>
        <el-table
          :data="templateListData"
          border
          :cell-style="cellStyleSmall()"
          :header-cell-style="headCellStyleSmall()"
          :row-class-name="templateTableRowClassName"
          @selection-change="onTemplateListSelect"
        >
          <el-table-column
            type="selection"
          />
          <el-table-column
            prop="name"
            label="模板名称"
          >
            <span slot-scope="scope">
<!--              {{scope.row.groupName}}-{{scope.row.name}}-->
              {{scope.row.name}}
            </span>
          </el-table-column>
        </el-table>
        <el-button v-show="showTable" type="primary" @click="onGenerate">生成代码</el-button>
      </el-col>
    </el-row>

    <el-dialog
      :title="datasourceTitle"
      :visible.sync="datasourceDlgShow"
    >
      <el-form
        ref="datasourceForm"
        :model="datasourceFormData"
        :rules="datasourceRule"
        size="mini"
        label-width="120px"
      >
        <el-form-item label="数据库类型">
          <el-select
            v-model="datasourceFormData.dbType"
            filterable
            default-first-option
          >
            <el-option
              v-for="item in dbTypeConfig"
              :key="item.dbType"
              :label="item.label"
              :value="item.dbType"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="Host" prop="host">
          <el-input v-model="datasourceFormData.host" placeholder="地址" show-word-limit maxlength="100" />
        </el-form-item>
        <el-form-item label="Port" prop="port">
          <el-input v-model="datasourceFormData.port" placeholder="端口" show-word-limit maxlength="10" />
        </el-form-item>
        <el-form-item label="Database" prop="dbName">
          <el-input v-model="datasourceFormData.dbName" placeholder="数据库" show-word-limit maxlength="64" />
        </el-form-item>
        <el-form-item label="Username" prop="username">
          <el-input v-model="datasourceFormData.username" placeholder="用户名" show-word-limit maxlength="100" />
        </el-form-item>
        <el-form-item label="Password" prop="password">
          <el-input v-model="datasourceFormData.password" type="password" placeholder="密码" show-word-limit maxlength="100" />
        </el-form-item>
        <el-form-item label="包名" prop="packageName">
          <el-input v-model="datasourceFormData.packageName" placeholder="包名（package）" show-word-limit maxlength="100" />
        </el-form-item>
        <el-form-item label="删除表前缀" prop="delTablePrefix">
          <el-input v-model="datasourceFormData.delTablePrefix" placeholder="删除表前缀（表名sys_user删除前缀sys_对应bean为User）多前缀逗号隔开" show-word-limit maxlength="200" />
        </el-form-item>
        <el-form-item label="删除字段前缀" prop="delFieldPrefix">
          <el-input v-model="datasourceFormData.delFieldPrefix" placeholder="删除字段前缀（表名c_user_info删除前缀c_对应userInfos）多前缀逗号隔开" show-word-limit maxlength="200" />
        </el-form-item>
        <el-form-item label="代码生成器模板" prop="delTablePrefix">
          <el-select
            v-model="datasourceFormData.groupId"
            placeholder="选择模板所在组"
            size="mini"
            style="margin-bottom: 10px; width: 100%;"
          >
            <el-option
              v-for="item in groupData"
              :key="item.id"
              :label="item.groupName"
              :value="item.id"
            >
              {{ item.groupName }}
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="success" @click="onDatasourceTest">测试连接</el-button>
          <el-button type="primary" @click="onDatasourceSave">保存</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>
<style lang="scss">
  .code-gen {
    margin: 0 auto;
    width: 70%;
      .el-input { width: 450px;}
      .el-row h4 {
        text-align: center;
      }
      .el-row .el-button {
        margin-top: 20px;
      }
  }
  .el-table .hidden-row {
    display: none;
  }
  #templateSelect {
    .el-input { width: 100%;}
  }
</style>
<script>
const current_datasource_id_key = "gen-datasource-id"
export default {
  name: 'GenerateConfig',
  data() {
    return {
      groupId: '',
      groupData: [],
      showTable: false,
      clientParam: {
        datasourceConfigId: '',
        tableNames: [],
        templateConfigIdList: [],
        packageName: '',
        delTablePrefix: '',
        delFieldPrefix: '',
        author: '',
        groupId: ''
      },
      tableSearch: '',
      datasourceConfigList: [],
      tableListData: [],
      templateListData: [],
      // add datasource
      datasourceTitle: '新建连接',
      datasourceDlgShow: false,
      datasourceFormData: {
        id: 0,
        dbType: 1,
        host: '',
        port: '',
        username: '',
        password: '',
        dbName: '',
        packageName: '',
        delTablePrefix: '',
        delFieldPrefix: '',
        author: '',
        groupId: ''
      },
      dbTypeConfig: [],
      datasourceRule: {
        host: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        port: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        username: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        dbName: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  watch: {
    // 监听属性变化
    'clientParam.datasourceConfigId': function (id) {
      for (const item of this.datasourceConfigList) {
        if (item.id === id) {
          this.groupId = item.groupId
          Object.assign(this.clientParam, {
            packageName: item.packageName,
            delTablePrefix: item.delTablePrefix,
            delFieldPrefix: item.delFieldPrefix,
            groupId: item.groupId
          })
          break
        }
      }
    }
  },
  created() {
    this.loadDataSource()
    this.loadTemplate()
    this.loadDbType()
    this.loadGroups()
  },
  methods: {
    tableRowClassName: function ({row, index}) {
      // console.log("tablerow="+row.tableName+","+index)
      row.hidden = false
      if (this.tableSearch.length === 0) {
        return ''
      }
      // console.log("tablerow="+row.tableName +","+ row.tableName.indexOf(this.tableName)+","+(!(row.tableName && row.tableName.indexOf(this.tableSearch) > -1)))
      if (!(row.tableName && row.tableName.toLowerCase().indexOf(this.tableSearch.toLowerCase()) > -1)) {
        row.hidden = true
        return 'hidden-row';
      }
      return ''
    },
    templateTableRowClassName: function ({row, index}) {
      // console.log("temprow="+row.id+",rowGroupId="+row.groupId+", this.groupId="+ this.groupId)
      row.hidden = false
      if (this.groupId == '' || this.groupId <= 0) {
        return ''
      }
      // console.log("temprow="+row.groupId +","+(row.groupId && row.groupId == this.groupId))
      if (row.groupId && row.groupId == this.groupId) {
        return ''
      }
      row.hidden = true
      return 'hidden-row';
    },
    getDatasourceLabel(item) {
      return `${item.dbName} (${item.host}) - ${item.username}`
    },
    loadGroups() {
      this.post(`/group/list/`, {}, function(resp) {
        this.groupData = resp.data
      })
    },
    loadDataSource() {
      const cacheId = this.getCurrentDataSourceId()
      this.post('/datasource/list', {}, resp => {
        let id
        const list = resp.data
        this.datasourceConfigList = list
        for (const item of list) {
          // 缓存id是否有效
          if (item.id === cacheId) {
            id = item.id
            break
          }
        }
        if (!id && list.length > 0) {
          id = list[0].id
        }
        if (id) {
          this.onDataSourceChange(parseInt(id))
        }
      })
    },
    loadTemplate() {
      this.post('/template/list', {}, resp => {
        this.templateListData = resp.data
      })
    },
    loadDbType() {
      this.post('/datasource/dbtype', {}, resp => {
        this.dbTypeConfig = resp.data
      })
    },
    setCurrentDataSourceId(id) {
      this.setAttr(current_datasource_id_key, id)
    },
    getCurrentDataSourceId() {
      const id = this.getAttr(current_datasource_id_key);
      return parseInt(id) || ''
    },
    onDataSourceAdd() {
      this.datasourceTitle = '新建连接'
      Object.keys(this.datasourceFormData).forEach(key=>{this.datasourceFormData[key]=''})
      this.datasourceFormData.id = 0
      this.datasourceDlgShow = true
      this.$nextTick(() => {
        if (this.groupData.length > 0) {
          this.datasourceFormData.groupId = this.groupData[0].id
        }
      })
    },
    onTableListSelect(selectedRows) {
      this.clientParam.tableNames = selectedRows
        .filter(row => row.hidden === undefined || row.hidden === false)
        .map(row => row.tableName)
    },
    onTemplateListSelect(selectedRows) {
      this.clientParam.templateConfigIdList = selectedRows
        .filter(row => row.hidden === undefined || row.hidden === false)
        .map(row => row.id)
    },
    onDataSourceChange(datasourceConfigId) {
      this.setCurrentDataSourceId(datasourceConfigId)
      this.clientParam.datasourceConfigId = datasourceConfigId
      this.post(`/datasource/table/${datasourceConfigId}`, {}, resp => {
        this.showTable = true
        this.tableListData = resp.data
      })
    },
    onDataSourceUpdate(item) {
      this.datasourceTitle = '修改连接'
      Object.assign(this.datasourceFormData, item)
      this.datasourceDlgShow = true
    },
    onDataSourceDuplicate(item) {
      this.datasourceTitle = `${item.host} Copy`
      Object.assign(this.datasourceFormData, item)
      this.datasourceFormData.id = 0
      this.datasourceDlgShow = true
    },
    onDataSourceDelete(row) {
      this.confirm(`确认要删除 ${row.dbName} 吗？`, function(done) {
        const data = {
          id: row.id
        }
        this.post('/datasource/del', data, function() {
          done()
          location.reload()
        })
      })
    },
    onGenerate() {
      this.$refs.genForm.validate((valid) => {
        if (valid) {
          if (this.clientParam.tableNames.length === 0) {
            this.tip('请勾选表', 'error')
            return
          }
          if (this.clientParam.templateConfigIdList.length === 0) {
            this.tip('请勾选模板', 'error')
            return
          }
          const config = JSON.stringify(this.clientParam)
          this.goRoute(`result/${config}`)
        }
      })
    },
    onDatasourceTest() {
      this.$refs.datasourceForm.validate((valid) => {
        if (valid) {
          this.post('/datasource/test', this.datasourceFormData, resp => {
            this.tip('连接成功')
          })
        }
      })
    },
    onDatasourceSave() {
      this.$refs.datasourceForm.validate((valid) => {
        if (valid) {
          this.post('/datasource/test', this.datasourceFormData, resp => {
            if (this.datasourceFormData.id) {
              this.post('/datasource/update', this.datasourceFormData, resp => {
                location.reload()
              })
            } else {
              this.post('/datasource/add', this.datasourceFormData, resp => {
                this.tip('添加成功')
                this.loadDataSource()
                this.datasourceDlgShow = false
              })
            }
          })
        }
      })
    }
  }
}
</script>

