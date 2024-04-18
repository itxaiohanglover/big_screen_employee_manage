<template>
  <div>  
      <!-- 第一行 -->
        <div class="d-flex jc-center">
            <dv-decoration-10 class="dv-dec-10" />
            <div class="d-flex jc-center">
                <dv-decoration-8 class="dv-dec-8" :color="decorationColor" />
                <div class="title">
                    <span class="title-text">数智员工考勤系统</span>
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
                    <span class="text">部门管理</span>
                </div>
                <div class="react-left ml-3">
                    <span class="text">员工管理</span>
                </div>
            </div>
            <div class="d-flex aside-width">
                <div class="react-right bg-color-blue mr-3">
                    <span class="text fw-b">系统设置</span>
                </div>
                <div class="react-right mr-4 react-l-s">
                    <span class="react-after"></span>
                    <span class="text">{{ dateYear }} {{ dateWeek }} {{ dateDay }}</span>
                </div>
            </div>
        </div>
  </div>
</template>

<script>
export default {

}
</script>

<style lang="scss" scoped>

</style>