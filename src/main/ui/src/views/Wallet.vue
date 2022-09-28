<template>
  <w-app>
    <TopBar />

    <h2 style="margin: 10vh">Your balance: {{ this.user.money }}</h2>
    <w-flex>
      <div class="xs3"></div>

      <div class="xs6">
        <w-tabs :fill-bar="true" :items="this.tabs">
          <template #item-content.1="{ item }">
            <w-flex column gap="6">
              <div>Add funds: {{ this.money }}</div>
              <w-flex justify-space-evenly no-grow>
                <w-button
                  height="150"
                  shadow
                  text
                  width="150"
                  @click="money = 20"
                  >$20</w-button
                >
                <w-button
                  height="150"
                  shadow
                  text
                  width="150"
                  @click="money = 50"
                  >$50</w-button
                >
                <w-button
                  height="150"
                  shadow
                  text
                  width="150"
                  @click="money = 100"
                  >$100</w-button
                >
              </w-flex>
              <w-flex justify-space-evenly no-grow>
                <w-button
                  height="150"
                  shadow
                  text
                  width="150"
                  @click="money = 200"
                  >$200</w-button
                >
                <w-button
                  height="150"
                  shadow
                  text
                  width="150"
                  @click="money = 500"
                  >$500</w-button
                >
                <w-button
                  height="150"
                  shadow
                  text
                  width="150"
                  @click="money = 1000"
                  >$1000</w-button
                >
              </w-flex>

              <w-button @click="addMoney(this.money)">Add money</w-button>
            </w-flex>
          </template>

          <template #item-content.2="{ item }">
            <w-flex column gap="6">
              <p>
                Selected reward: {{ this.selectionInfo.item.amount }} of
                {{ this.selectionInfo.item.description }}
              </p>

              <w-table
                :force-selection="table.forceSelection"
                :headers="table.headers"
                :items="table.items"
                :selectable-rows="table.selectableRows"
                no-data="no-data"
                style="height: 322px"
                @row-select="select"
              >
              </w-table>

              <w-button @click="exchange">Exchange Reward!</w-button>
            </w-flex>
          </template>
        </w-tabs>
      </div>
      <div class="xs3"></div>
    </w-flex>
  </w-app>
</template>

<script>
import TopBar from "../components/TopBar.vue";
import { userStore } from "../userStore";
import { addMoney, exchangeReward, getUserData } from "../services/UserService";

export default {
  name: "Wallet",
  components: { TopBar },
  data() {
    return {
      user: null,
      money: 0,
      tabs: [
        { title: "Add funds", content: "Tab 1 content." },
        { title: "Exchange Rewards", content: "Tab 2 content." },
      ],
      table: {
        headers: [
          { label: "Type", key: "type", align: "center" },
          { label: "Amount", key: "amount", align: "center" },
          { label: "Description", key: "description", align: "center" },
          { label: "Value", key: "value", align: "center" },
        ],
        items: [],
        selectableRows: 1,
        forceSelection: false,
        selectableRowsOption: {
          label: '<code class="mr2">:selectable-row="false"</code> (default)',
          value: false,
        },
      },
      defaultSelection: { amount: 0, description: "None" },
      selectionInfo: {},
    };
  },
  setup() {
    const store = userStore();
    return {
      store,
    };
  },
  mounted() {
    this.selectionInfo.item = this.defaultSelection;
    getUserData().then((response) => {
      this.user = response;
      this.table.items = this.user.rewards;
    });
  },
  methods: {
    addMoney(money) {
      addMoney(money).then((response) => {
        this.user = response;
        this.money = 0;
      });
    },
    select(event) {
      this.selectionInfo = event;
    },
    exchange() {
      exchangeReward(this.selectionInfo.item).then((response) => {
        this.user = response;
        this.table.items = this.user.rewards;
        this.selectionInfo.item = this.defaultSelection;
      });
    },
  },
};
</script>

<style scoped>
.w-app {
  background: url("../assets/img/above_clouds.jpg") center center fixed
    no-repeat !important;
  background-size: cover !important;
  text-align: center;
  height: 100%;
}

.w-toolbar {
  background: inherit;
  background-color: rgba(255, 255, 255, 0.4);
  background-blend-mode: lighten;
  min-height: 60px;
  max-height: 8vh;
  backdrop-filter: blur(10);
}
</style>
