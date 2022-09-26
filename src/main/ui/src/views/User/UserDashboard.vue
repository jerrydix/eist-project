<template>
  <w-app v-if="this.store.username">
    <TopBar />
    <h2 class="text-center" style="margin-top: 8vh">Dashboard</h2>

    <w-flex class="text-center" wrap>
      <div class="xs1"></div>
      <div class="xs6">
        <h3 class="text-center">Your Favourite Points of Interest</h3>
        <w-table
          :force-selection="poiTable.forceSelection"
          :headers="poiTable.headers"
          :items="poiTable.items"
          :selectable-rows="poiTable.selectableRows"
          no-data="no-data"
          style="height: 41vh"
          @row-select="select"
        >
        </w-table>
        <FavPOI
          v-if="this.map"
          :current="this.current"
          :selection-info="this.selectionInfo"
          :table="this.poiTable"
          @unsave="unsave"
        />
      </div>
      <div class="xs4">
        <h3 class="text-center">Your Planned Journeys</h3>

        <JourneyInflet
          v-for="(option, i) in this.user.bookedFlightJourneys"
          :key="i"
          :journey="option"
          @show="showJourneyDetails"
        />
        <w-dialog v-model="this.showJourney">
          <Journey :flights="this.flightList" :map="true" />
        </w-dialog>
      </div>
      <div class="xs1"></div>
    </w-flex>

    <h3 v-if="this.user.latestReward != null" class="text-center">
      Reward: {{ this.user["latestReward"]["description"] }}
    </h3>
  </w-app>

  <w-app v-if="!this.store.username">
    <w-flex align-center column justify-center>
      <h2>Please login to see your dashboard</h2>

      <RouterLink to="/">
        <w-button>Back to Home</w-button>
      </RouterLink>
    </w-flex>
  </w-app>
</template>

<script>
import { userStore } from "../../userStore.js";
import { getUserData } from "../../services/UserService.js";
import FlightInfo from "../../components/FlightInfo.vue";
import { removePOIFromFavourites } from "../../services/POIService.js";
import FavPOI from "../../components/FavPOI.vue";
import JourneyInflet from "../../components/JourneyInflet.vue";
import Journey from "../../components/Journey.vue";
import TopBar from "../../components/TopBar.vue";

export default {
  components: { Journey, JourneyInflet, FavPOI, FlightInfo, TopBar },
  data() {
    return {
      user: null,
      key: import.meta.env.VITE_GOOGLE_API_KEY,
      map: true,
      current: { lat: 0, lng: 0 },
      poiTable: {
        headers: [
          { label: "Name", key: "title", align: "center" },
          { label: "Address", key: "address", align: "center" },
          { label: "Type", key: "formattedType", align: "center" },
          { label: "Rating", key: "rating", align: "center" },
        ],
        items: [],
        selectableRows: 1,
        forceSelection: false,
        selectableRowsOption: {
          label: '<code class="mr2">:selectable-row="false"</code> (default)',
          value: false,
        },
      },
      selectionInfo: {},

      showJourney: false,
      flightList: [],
    };
  },
  setup() {
    const store = userStore();
    return {
      store,
    };
  },
  mounted() {
    getUserData().then((response) => {
      this.user = response;
      this.poiTable.items = this.user.favouritePOIList;
      if (this.poiTable.items.length > 0) {
        this.current = this.poiTable.items[0].position;
      }
    });
  },
  methods: {
    unsave(id) {
      removePOIFromFavourites(id, -1).then((list) => {
        this.poiTable.items = [];
        for (let i = 0; i < list.length; i++) {
          this.poiTable.items.push(list[i]);
        }
        if (this.poiTable.items.length == 0) {
          this.current = { lat: 0, lng: 0 };
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
    showJourneyDetails(flights) {
      this.showJourney = true;
      this.flightList = flights;
    },
  },
};
</script>

<style scoped>
.w-app {
  background: url("../../assets/img/above_clouds.jpg") center center fixed
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

header,
footer,
aside,
main {
  margin: 0px;
  padding: 0px;
  color: #000000;
  border: 0px solid rgba(0, 0, 0, 0.1);
}

header {
  background-color: #ffffff;
  min-height: 4vh;
  align-content: center;
}

footer {
  background-color: #ffffff;
  min-height: 8vh;
  align-content: center;
}

aside {
  background-color: #ffffff;
}

main {
  background-color: #ffffff;
}
</style>
