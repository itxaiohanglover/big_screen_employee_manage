<template>
    <div id="mychart">
        <dv-border-box-1>
            <div class="up">
                <div class="bg-color-black item ml-1 mt-2 pl-3" v-for="item in titleItem" :key="item.title">
                    <p class="ml-3 colorBlue fw-b fs-l">{{ item.title }}</p>
                    <div>
                        <dv-digital-flop class="dv-dig-flop" :config="item.number" />
                    </div>
                </div>
            </div>
        </dv-border-box-1>

        <div class="down">
            <userdeptVue ref="userdeptVue"></userdeptVue>
        </div>
    </div>
</template>

<script>
import userdeptVue from './userdept'
export default {
    components: { userdeptVue },
    data() {
        return {
            titleItem: [
                {
                    title: '子部门',
                    number: {
                        number: [12],
                        content: '{nt}个',
                        style: {
                            fontSize: 36
                        }
                    }
                },
                {
                    title: '部门成员',
                    number: {
                        number: [18],
                        content: '{nt}个',
                        style: {
                            fontSize: 36
                        }
                    }
                },
                {
                    title: '本月部员',
                    number: {
                        number: [2],
                        content: '{nt}个',
                        style: {
                            fontSize: 36
                        }
                    }
                }
            ],
            id: 0
        }
    },
    mounted() {
        this.init(this.id);
    },
    methods: {
        async init(id) {
            /**拿取后台数据*/
            const { data } = await this.$http({
                url: this.$http.adornUrl('/getCountNum'),
                method: 'get',
                params: this.$http.adornParams({
                    'id': id,
                })
            })
            if (data && data.code === 200) {
                let chartVos = data.data
                chartVos.map((item, index) => {
                    this.titleItem[index].title = item.title;
                    this.titleItem[index].number.number[0] = item.number;
                    /**
                    * 使用ES6拓展运算符生成新的props对象
                    * 组件侦知数据变化 自动刷新状态
                    */
                    this.titleItem[index].number = { ...this.titleItem[index].number }
                })
            } else {
                this.$message.error(data.msg)
                return;
            }
            this.$refs.userdeptVue.getDataList(id);
        }
    }
}
</script>

<style lang="scss" scoped>
#mychart {
    display: flex;
    flex-direction: column;

    .up {
        width: 100%;
        display: flex;
        flex-wrap: wrap;
        justify-content: space-around;

        .item {
            border-radius: 6px;
            padding-top: 8px;
            margin-top: 8px;
            width: 32%;
            height: 100px;

            .dv-dig-flop {
                width: 250px;
                height: 50px;
            }
        }
    }

    .down {
        padding-top: 20px;
    }
}
</style>