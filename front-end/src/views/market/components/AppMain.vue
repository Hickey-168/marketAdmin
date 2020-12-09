<template>
	<div class="app-container">
		<div class="filter-container">
			<el-form>
				<el-form-item>
					<el-button type="primary" icon="plus" @click="buy">下单</el-button>
				</el-form-item>
			</el-form>
		</div>
		<el-table :data="list" v-loading.body="listLoading" element-loading-text="拼命加载中" border fit highlight-current-row>
			<el-table-column align="center"  prop="id" label="序号" width="80"></el-table-column>
			<el-table-column align="center" prop="goodsName" label="商品" style="width: 60px;"></el-table-column>
			<el-table-column align="center" label="管理" width="200">
				<template slot-scope="scope">
					<el-button type="primary" icon="edit" @click="setBarket(scope.$index)">加入购物车</el-button>
				</template>
			</el-table-column>
		</el-table>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				totalCount: 100, //分页组件--数据总条数
				list: [
					{id:'1',goodsName:'牛奶'},
					{id:'2',goodsName:'面包'},
					{id:'3',goodsName:'可乐'},
					{id:'4',goodsName:'咖啡'},
					{id:'5',goodsName:'洗面奶'},
					{id:'6',goodsName:'苹果'},
          {id:'7',goodsName:'笔记本'},
          {id:'8',goodsName:'中性笔'},
          {id:'9',goodsName:'水杯'},

				], //表格的数据
				listLoading: false, //数据加载等待动画
			}
		},
		created() {
			this.getList();
		},
		methods: {
			getList() {
				//查询列表
				this.listLoading = true;
				this.listLoading = false;
			},
			buy() {
				//购买
				//购物篮列表为this.$store.state.barket
        if(this.$store.state.barket.length !== 0 ){
          alert('下单成功')
          for(var i = 0; i < this.$store.state.barket.length ; i++)
          {

            this.api({
              url: "/order/addOrder",
              method: "post",
              data: {id: "1" ,content:this.$store.getters.nickname, goodsName: this.$store.state.barket[i].name}
            })
          }
        }else {
          alert('你的购物车为空，请添加商品后再下订单')
        }
			},
			setBarket($index) {
				//加入购物车
				alert('添加商品成功')
				this.$store.commit('newBarket', this.list[$index].goodsName)
			},
		}
	}
</script>
