<template>
  <div id="app">
    <w-app>
      <header>Dashboard</header>
      <p>{{ this.user.username }}</p>
      <p>{{ this.user.favouritePOIs }}</p>
      <p>{{ this.user.rewards }}</p>
      <p>{{ this.user.bookedFlightJourneys }}</p>

      <w-flex grow>
        <main class="grow">
          <p class="reward">Reward: {{ this.user["latestReward"]["description"] }}</p>
        </main>
      </w-flex>
    </w-app>
  </div>
</template>

<script>
import {userStore} from "../../userStore.js";
import {getUserData} from "../../services/UserService.js";
import FlightInfo from "../../components/FlightInfo.vue";

export default {
  components: {FlightInfo},
  data() {
    return {
      user: null,
      key: import.meta.env.VITE_GOOGLE_API_KEY,
      map: true,
      pois: [],
      positions: [],
      favourites: [],
      favouritepositions: [],
      current: {lat: 0, lng: 0},
      curr: 0,
      rewardTable: {
        headers: [
          {label: 'Reward', key: 'rewardType'},
          {label: 'Reward', key: 'description'},
        ],
        items: [

        ],
        selectableRows: 1,
        forceSelection: false,
        selectableRowsOption: {label: '<code class="mr2">:selectable-row="false"</code> (default)', value: false},
      },
      selectionInfo: {}
    }
  },
  setup() {
    const store = userStore();
    return {
      store,
    };
  },
  mounted() {
    getUserData().then((response) => {
      this.user = response
    });
  }
};
</script>


<style scoped>

.w-app {padding: 0px;background-color: #ffffff;}
header, footer, aside, main {
  margin: 0px;
  padding: 0px;
  color: #000000;
  border: 0px solid rgba(0, 0, 0, 0.1);
}
header, footer {background-color: #ffffff; min-height: 4vh; align-content: center}
aside {background-color: #ffffff}
main {background-color: #ffffff;}

</style>

