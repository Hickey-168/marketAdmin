<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <el-button type="primary" icon="plus" @click="showCreate" v-if="hasPerm('order:add')">添加
          </el-button>
        </el-form-item>
      </el-form>
    </div>


    <el-table :data="list" v-loading.body="listLoading" element-loading-text="拼命加载中" border fit
              highlight-current-row>
      <el-table-column align="center" label="序号" width="80">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"> </span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="content" label="订单客户" style="width: 60px;"></el-table-column>

      <el-table-column align="center" prop="goodsName" label="商品名" style="width: 60px;">
        <template slot-scope="scope">
          <span>{{scope.row.goodsName}}</span>
        </template>
      </el-table-column>


      <el-table-column align="center" label="创建时间" width="170">
        <template slot-scope="scope">
          <span>{{scope.row.createTime}}</span>
        </template>
      </el-table-column>



      <el-table-column align="center" label="管理" width="200" v-if="hasPerm('order:update')">
        <template slot-scope="scope">
          <el-button type="primary" icon="edit" @click="showUpdate(scope.$index)">修改</el-button>
        </template>
      </el-table-column>
    </el-table>


    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="listQuery.pageNum"
      :page-size="listQuery.pageRow"
      :total="totalCount"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>


<!--    添加订单模块-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="tempOrder" label-position="left" label-width="60px"
               style='width: 300px; margin-left:50px;'>
        <el-form-item label="客户名">
          <el-input type="text" v-model="tempOrder.content">
          </el-input>
        </el-form-item>

        <el-form-item label="商品名">
          <el-input type="text" v-model="tempOrder.goodsName">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button v-if="dialogStatus=='create'" type="success" @click="createOrder">创 建</el-button>
        <el-button type="primary" v-else @click="updateOrder">修 改</el-button>
      </div>
    </el-dialog>


  </div>
</template>
<script>
  export default {
    data() {
      return {
        totalCount: 0, //分页组件--数据总条数
        list: [],//表格的数据
        listLoading: false,//数据加载等待动画
        listQuery: {
          pageNum: 1,//页码
          pageRow: 50,//每页条数
          name: ''
        },
        dialogStatus: 'create',
        dialogFormVisible: false,
        textMap: {
          update: '编辑',
          create: '新建订单'
        },
        tempOrder: {
          id: "",
          content: "",
          goodsName: ""
        }
      }
    },
    created() {
      this.getList();
    },
    methods: {
      getList() {
        //查询列表
        if (!this.hasPerm('order:list')) {
          return
        }
        this.listLoading = true;
        this.api({
          url: "/order/listOrder",
          method: "get",
          params: this.listQuery
        }).then(data => {
          this.listLoading = false;
          this.list = data.list;
          this.totalCount = data.totalCount;
        })
      },
      handleSizeChange(val) {
        //改变每页数量
        this.listQuery.pageRow = val
        this.handleFilter();
      },
      handleCurrentChange(val) {
        //改变页码
        this.listQuery.pageNum = val
        this.getList();
      },
      handleFilter() {
        //改变了查询条件,从第一页开始查询
        this.listQuery.pageNum = 1
        this.getList()
      },
      getIndex($index) {
        //表格序号
        return (this.listQuery.pageNum - 1) * this.listQuery.pageRow + $index + 1
      },
      showCreate() {
        //显示新增对话框
        this.tempOrder.content = "";
        this.tempOrder.goodsName = "";
        this.dialogStatus = "create"
        this.dialogFormVisible = true
      },
      showUpdate($index) {
        //显示修改对话框
        this.tempOrder.id = this.list[$index].id;
        this.tempOrder.content = this.list[$index].content;
        this.tempOrder.goodsName = this.list[$index].goodsName;
        this.dialogStatus = "update"
        this.dialogFormVisible = true
      },
      createOrder() {
        //保存新订单
        this.api({
          url: "/order/addOrder",
          method: "post",
          data: this.tempOrder
        }).then(() => {
          this.getList();
          this.dialogFormVisible = false
        })
      },
      updateOrder() {
        //修改订单
        this.api({
          url: "/order/updateOrder",
          method: "post",
          data: this.tempOrder
        }).then(() => {
          this.getList();
          this.dialogFormVisible = false
        })
      },
    }
  }
</script>
