<template>
  <div id="app">
    <w-app>
      <header>
        <h2> Dashboard</h2>
      </header>
      <w-flex wrap class="text-center">
        <div class="xs4">
          <p class="reward">Reward: {{ this.user["latestReward"]["description"] }}</p>
        </div>
        <div class="xs4">
          <w-table
              :headers="poiTable.headers"
              :items="this.user.favouritePOIs"
              no-data="no-data"
              style="height: 96vh">
          </w-table>
        </div>
        <div class="xs4">
          <w-table
              :headers="journeyTable.headers"
              :items="this.user.bookedFlightJourneys.map(e => e.origin.name)"
              no-data="no-data"
              style="height: 96vh">
          </w-table>
        </div>
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
      poiTable: {
        headers: [
          {label: 'Name', key: 'name'},
          {label: 'Address', key: 'address'},
          {label: 'Type', key: 'pointOfInterestType'},
          {label: 'Rating', key: 'rating'}
        ],
        items: [],
        forceSelection: false,
        selectableRowsOption: {label: '<code class="mr2">:selectable-row="false"</code> (default)', value: false},
      },
      journeyTable: {
        headers: [
          {label: 'Origin', key: 'name'}
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
header, footer {background-color: #ffffff; min-height: 4vh; align-content: center}
aside {background-color: #ffffff}
main {background-color: #ffffff;}

</style>

