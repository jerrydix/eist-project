<template>
  <div id="app">
    <w-app>
      <header>
        <h2 class="text-center">Dashboard</h2>
      </header>
      <w-flex class="text-center" wrap>
        <div class="xs2"></div>
        <div class="xs5">
          <h3 class="text-center">Your Favourite Points of Interest</h3>
          <w-table
              :force-selection="poiTable.forceSelection"
              :headers="poiTable.headers"
              :items="poiTable.items"
              :selectable-rows="poiTable.selectableRows"
              no-data="no-data"
              style="height: 41vh"
              @row-select="select">
          </w-table>
          <FavPOI v-if="this.map"
                  :current="this.current"
                  :selection-info="this.selectionInfo"
                  :table="this.poiTable"
                  @unsave="unsave"
          />
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
        <h3 v-if="this.user.latestReward != null" class="text-center">Reward:
          {{ this.user["latestReward"]["description"] }}</h3>
      </footer>
    </w-app>
  </div>
</template>

<script>
import {userStore} from "../../userStore.js";
import {getUserData} from "../../services/UserService.js";
import FlightInfo from "../../components/FlightInfo.vue";
import {removePOIFromFavourites} from "../../services/POIService.js";
import FavPOI from "../../components/FavPOI.vue";

export default {
  components: {FavPOI, FlightInfo},
  data() {
    return {
      user: null,
      key: import.meta.env.VITE_GOOGLE_API_KEY,
      map: true,
      current: {lat: 0, lng: 0},
      poiTable: {
        headers: [
          {label: 'Name', key: 'title', align: 'center'},
          {label: 'Address', key: 'address', align: 'center'},
          {label: 'Type', key: 'formattedType', align: 'center'},
          {label: 'Rating', key: 'rating', align: 'center'}
        ],
        items: [],
        selectableRows: 1,
        forceSelection: false,
        selectableRowsOption: {label: '<code class="mr2">:selectable-row="false"</code> (default)', value: false},
      },
      selectionInfo: {},

      journeyTable: {
        headers: [
          {label: 'Origin', key: 'originName', align: 'center'},
          {label: 'Destination', key: 'endName', align: 'center'},
          {label: 'Starting on', key: 'startDate', align: 'center'},
        ],
        items: [],
        forceSelection: false,
        selectableRowsOption: {label: '<code class="mr2">:selectable-row="false"</code> (default)', value: false},
      },
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
      this.poiTable.items = this.user.favouritePOIs;
      if (this.poiTable.items.length > 0) {
        this.current = this.poiTable.items[0].position;
      }
    });
  },
  methods: {
    unsave(id) {
      removePOIFromFavourites(id, -1).then((list) => {
        this.poiTable.items = []
        for (let i = 0; i < list.length; i++) {
          this.poiTable.items.push(list[i]);
        }
        if (this.poiTable.items.length == 0) {
          this.current = {lat: 0, lng: 0}
        }
        this.reRender();
      });
    },
    reRender() {
      this.map = false;
      this.$nextTick().then(() => {
        this.map = true;
      });
    },
    select(event) {
      this.selectionInfo = event;
      this.current = this.selectionInfo.item.position;
      this.reRender();
    },
  },
};
</script>


<style scoped>

.w-app {
  padding: 0px;
  background-color: #ffffff;
}

header, footer, aside, main {
  margin: 0px;
  padding: 0px;
  color: #000000;
  border: 0px solid rgba(0, 0, 0, 0.1);
}

header {
  background-color: #ffffff;
  min-height: 4vh;
  align-content: center
}

footer {
  background-color: #ffffff;
  min-height: 8vh;
  align-content: center
}

aside {
  background-color: #ffffff
}

main {
  background-color: #ffffff;
}

</style>

