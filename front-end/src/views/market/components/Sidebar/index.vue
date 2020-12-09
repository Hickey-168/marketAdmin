<template>
	<scroll-bar>
		<el-menu mode="vertical" unique-opened :default-active="$route.path" background-color="#304156"
		 text-color="#fff" active-text-color="#409EFF">
			<el-table :data="this.$store.state.barket" stripe style="width: 100%" background-color="#304156"
		 text-color="#fff" active-text-color="#409EFF">
				<el-table-column prop="name" label="购物篮" width="180" background-color="#304156">
				</el-table-column>
			</el-table>
		</el-menu>
	</scroll-bar>
</template>
<script>
	import {mapGetters} from 'vuex'
	import ScrollBar from '@/components/ScrollBar'

	export default {
		data() {
			return {
			}
		},
		components: {
			ScrollBar
		},
	}
</script>
