<template>
  <div id="app">
    <w-app>
      <header>
        <h2 class="text-center">Dashboard</h2>
      </header>
      <w-flex wrap class="text-center">
        <div class="xs2"></div>
        <div class="xs5">
          <h3 class="text-center">Your Favourite Points of Interest</h3>
          <w-table
              :headers="poiTable.headers"
              :items="this.user.favouritePOIs.filter(e => e.favourited == 1)"
              no-data="no-data"
              style="height: 41vh">
          </w-table>
        </div>
        <div class="xs3">
          <h3 class="text-center">Your Planned Journeys</h3>
          <w-table
              :headers="journeyTable.headers"
              :items="this.user.bookedFlightJourneys"
              no-data="no-data"
              style="height: 83vh">
          </w-table>
        </div>
        <div class="xs2"></div>
      </w-flex>
      <footer>
        <h3 class="text-center" v-if="this.user.latestReward != null">Reward: {{ this.user["latestReward"]["description"] }}</h3>
      </footer>
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
      poiTable: {
        headers: [
          {label: 'Name', key: 'title', align: 'center'},
          {label: 'Address', key: 'address', align: 'center'},
          {label: 'Type', key: 'formattedType', align: 'center'},
          {label: 'Rating', key: 'rating', align: 'center'}
        ],
        items: [],
        forceSelection: false,
        selectableRowsOption: {label: '<code class="mr2">:selectable-row="false"</code> (default)', value: false},
      },
      journeyTable: {
        headers: [
          {label: 'Origin', key: 'originName', align: 'center'},
          {label: 'Starting on', key: 'startDate', align: 'center'},
        ],
        items:  [],
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
header {background-color: #ffffff; min-height: 4vh; align-content: center}
footer {background-color: #ffffff; min-height: 8vh; align-content: center}
aside {background-color: #ffffff}
main {background-color: #ffffff;}

</style>

