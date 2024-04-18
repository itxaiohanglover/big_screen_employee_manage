<template>
  <div id="index" ref="appRef">
    <div class="bg">
      <dv-loading v-if="loading">Loading...</dv-loading>
      <div v-else class="host-body">
        <!-- 第一行 -->
        <div class="d-flex jc-center">
            <dv-decoration-10 class="dv-dec-10" />
            <div class="d-flex jc-center">
                <dv-decoration-8 class="dv-dec-8" :color="decorationColor" />
                <div class="title">
                    <span class="title-text fw-b">数智员工考勤系统</span>
                    <dv-decoration-6 class="dv-dec-6" :reverse="true" :color="['#50e3c2', '#67a1e5']" />
                </div>
                <dv-decoration-8 class="dv-dec-8" :reverse="true" :color="decorationColor" />
            </div>
            <dv-decoration-10 class="dv-dec-10-s" />
        </div>
        <!-- 第二行 -->
        <div class="d-flex jc-between px-2">
            <div class="d-flex aside-width">
                <div class="react-left ml-4 react-l-s">
                    <span class="react-left"></span>
                    <!-- <span class="text">部门管理</span> -->
                    <router-link to="/dept" class="text fw-b" tag="li">
                      <a style="color:#d3d6dd">部门管理</a>
                    </router-link>
                </div>
                <div class="react-left ml-3">
                    <!-- <span class="text">员工管理</span> -->
                    <router-link to="/user" class="text fw-b" tag="li">
                      <a style="color:#d3d6dd">员工管理</a>
                    </router-link>
                </div>
            </div>
            <div class="d-flex aside-width">
                <!-- <div class="react-right bg-color-blue mr-3"> -->
                <div class="react-right mr-3 bg-color-black">
                    <!-- <span class="text fw-b"></span> -->
                    <router-link to="/admin" class="text fw-b" tag="li">
                      <a style="color:#d3d6dd">系统设置</a>
                    </router-link>
                </div>
                <div class="react-right mr-4 react-l-s">
                    <span class="react-after"></span>
                    <span class="text fw-b">{{ dateYear }} {{ dateWeek }} {{ dateDay }}</span>
                </div>
            </div>
        </div>
        <div class="body-box">
          <router-view />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { formatTime } from '../utils/index.js'

export default {
  data() {
    return {
      timing: null,
      loading: true,
      dateDay: null,
      dateYear: null,
      dateWeek: null,
      weekday: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
      decorationColor: ['#568aea', '#000000']
    }
  },
  components: {},
  mounted() {
    this.timeFn()
    this.cancelLoading()
  },
  beforeDestroy() {
    clearInterval(this.timing)
  },
  methods: {
    timeFn() {
      this.timing = setInterval(() => {
        this.dateDay = formatTime(new Date(), 'HH: mm: ss')
        this.dateYear = formatTime(new Date(), 'yyyy-MM-dd')
        this.dateWeek = this.weekday[new Date().getDay()]
      }, 1000)
    },
    cancelLoading() {
      setTimeout(() => {
        this.loading = false
      }, 500)
    }
  }
}
</script>

<style lang="scss">
@import '../assets/scss/index.scss';
</style>
