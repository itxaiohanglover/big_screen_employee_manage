<template>
  <div id="center">
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
      <div class="ranking bg-color-black">
        <span>
          <icon name="chart-pie" class="text-icon"></icon>
        </span>
        <span class="fs-xl text mx-2 mb-1 pl-3">部门排行榜</span>
        <dv-scroll-ranking-board class="dv-scr-rank-board mt-1" :config="ranking" />
      </div>

    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      titleItem: [
        {
          title: '子部门个数',
          number: {
            number: [120],
            textAlign: 'left',
            content: '{nt}',
            style: {
              fontSize: 36
            }
          }
        },
        {
          title: '部门成员个数',
          number: {
            number: [18],
            textAlign: 'left',
            content: '{nt}',
            style: {
              fontSize: 36
            }
          }
        },
        {
          title: '本月部员新加入个数',
          number: {
            number: [21],
            textAlign: 'left',
            content: '{nt}',
            style: {
              fontSize: 36
            }
          }
        }
      ],
      ranking: {
        data: [
          {
            name: '周口',
            value: 55
          },
          {
            name: '南阳',
            value: 120
          },
          {
            name: '西峡',
            value: 78
          },
          {
            name: '驻马店',
            value: 66
          },
          {
            name: '新乡',
            value: 80
          },
          {
            name: '新乡2',
            value: 80
          },
          {
            name: '新乡3',
            value: 80
          },
          {
            name: '新乡4',
            value: 80
          },
          {
            name: '新乡5',
            value: 80
          },
          {
            name: '新乡6',
            value: 80
          }
        ],
        carousel: 'single',
        unit: '人'
      }
    }
  },
}
</script>

<style lang="scss" scoped>
#center {
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
    padding: 6px 4px;
    padding-bottom: 0;
    width: 100%;
    display: flex;
    height: 255px;
    justify-content: space-between;

    .bg-color-black {
      border-radius: 5px;
    }

    .ranking {
      padding: 10px;
      width: 100%;

      .dv-scr-rank-board {
        height: 225px;
      }
    }
  }
}
</style>
