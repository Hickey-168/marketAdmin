import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import user from './modules/user'
import permission from './modules/permission'
import getters from './getters'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    barket:[]
  },
  mutations:{
    newBarket(state,msg){
      state.barket.push({name:msg})
    }
  },
  modules: {
    app,
    user,
    permission
  },
  getters
})

export default store
