<template>
  <w-app v-if="this.store.username" id="app">

    <div v-if="this.topbar">
      <TopBar/>
    </div>

    <w-flex grow>
      <aside>
        <w-flex align-center grow justify-center>
          <w-checkbox color="orange" style="padding: 5px" @input="topTen"
          >Show Top 10
          </w-checkbox
          >
          <w-checkbox
              style="padding: 5px"
              @input="
              table.activeFilter == 0
                ? (table.activeFilter = 1)
                : (table.activeFilter = 0)
            "
          >Show Favourites
          </w-checkbox>
        </w-flex>
        <w-table
            v-model:sort="table.sort"
            :filter="table.filters[table.activeFilter]"
            :force-selection="table.forceSelection"
            :headers="table.headers"
            :items="table.items"
            :selectable-rows="table.selectableRows"
            no-data="no-data"
            style="height: 96.5vh"
            @row-select="select"
        >
        </w-table>
      </aside>
      <main class="grow">
        <GoogleMap
            v-if="map"
            :api-key="key"
            :center="current"
            :zoom="16"
            style="width: 100%; height: 100%"
        >
          <MarkerCluster>
            <Marker
                v-for="(option, i) in this.table.items"
                :key="i"
                :options="option"
            >
              <InfoWindow>
                <h4>{{ option.title }}</h4>
                <div>{{ option.description }}</div>
                <w-rating
                    v-model="option.favourited"
                    color="yellow"
                    max="1"
                    md
                    readonly
                ></w-rating>
                <br/>
                <w-button
                    v-if="!option.favourited"
                    @click="save($event, option)"
                >Save to Favourites
                </w-button
                >
                <w-button
                    v-if="option.favourited"
                    @click="unsave($event, option)"
                >Delete from Favourites
                </w-button
                >
              </InfoWindow>
            </Marker>
          </MarkerCluster>
        </GoogleMap>
      </main>
    </w-flex>
  </w-app>

  <w-app v-if="!this.store.username">
    <w-flex align-center column justify-center>
      <h2>Please login to see details about your destination</h2>

      <RouterLink to="/">
        <w-button>Back to Home</w-button>
      </RouterLink>
    </w-flex>
  </w-app>
</template>

<script>
import {defineComponent} from "vue";
import {GoogleMap, InfoWindow, Marker} from "vue3-google-map";
import HomeButton from "../components/HomeButton.vue";
import {
  addPOIToFavourites,
  getPointsOfInterest,
  getTopPointsOfInterest,
  removePOIFromFavourites,
} from "../services/POIService.js";
import {userStore} from "../userStore";
import TopBar from "../components/TopBar.vue";

export default defineComponent({
  components: {TopBar, GoogleMap, Marker, InfoWindow, HomeButton},
  props: ["location"],
  setup() {
    const store = userStore();
    return {
      store,
    };
  },
  data() {
    return {
      topbar: false,
      key: import.meta.env.VITE_GOOGLE_API_KEY,
      map: true,
      current: {lat: 0, lng: 0},
      table: {
        headers: [
          {label: "Name", key: "title", align: "center"},
          {label: "Rating", key: "rating", align: "center"},
        ],
        items: [],
        selectableRows: 1,
        forceSelection: false,
        selectableRowsOption: {
          label: '<code class="mr2">:selectable-row="false"</code> (default)',
          value: false,
        },
        filters: [null, (item) => item.favourited === 1],
        activeFilter: 0,
        sort: "-rating",
      },
      selectionInfo: {},
      isTopTen: false,
      locationID: -1,
    };
  },
  mounted() {
    getPointsOfInterest(this.locationID).then((list) => {
      for (let i = 0; i < list.length; i++) {
        this.table.items.push(list[i]);
      }
      this.current = this.table.items[0].position;
      this.reRender();
    });
  },
  created() {
    if (this.$route.fullPath === "/poi") {
      this.locationID = this.$route.params.locationID;
      this.topbar = true;
    } else {
      this.locationID = this.location;
      this.topbar = false;
    }
  },
  methods: {
    save(event, option) {
      addPOIToFavourites(option.id, this.locationID).then((list) => {
        this.current = option.position;
        if (this.isTopTen) {
          getTopPointsOfInterest(this.locationID).then((list) => {
            this.table.items = [];
            for (let i = 0; i < list.length; i++) {
              this.table.items.push(list[i]);
            }
            this.reRender();
          });
        } else {
          this.table.items = [];
          for (let i = 0; i < list.length; i++) {
            this.table.items.push(list[i]);
          }
          this.reRender();
        }
      });
    },
    unsave(event, option) {
      removePOIFromFavourites(option.id, this.locationID).then((list) => {
        if (this.isTopTen) {
          getTopPointsOfInterest(this.locationID).then((list) => {
            this.current = list[0].position;
            this.table.items = [];
            for (let i = 0; i < list.length; i++) {
              this.table.items.push(list[i]);
            }
            this.reRender();
          });
        } else {
          this.current = list[0].position;
          this.table.items = [];
          for (let i = 0; i < list.length; i++) {
            this.table.items.push(list[i]);
          }
          this.reRender();
        }
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
    topTen() {
      if (!this.isTopTen) {
        getTopPointsOfInterest(this.locationID).then((list) => {
          this.current = list[0].position;
          this.table.items = [];
          for (let i = 0; i < list.length; i++) {
            this.table.items.push(list[i]);
          }
          this.reRender();
        });
      } else {
        getPointsOfInterest(this.locationID).then((list) => {
          this.current = list[0].position;
          this.table.items = [];
          for (let i = 0; i < list.length; i++) {
            this.table.items.push(list[i]);
          }
          this.current = this.table.items[0].position;
          this.reRender();
        });
      }
      this.isTopTen = !this.isTopTen;
    },
  },
});
</script>

<style scoped>
.box {
  background-color: #e2ecfc;
  border: 1px solid #b2c2f0;
  padding: 12px 0;
  text-align: center;
}

.w-toolbar {
  background-color: var(--color-background-mute-transparent);
  min-height: 60px;
  max-height: 8vh;
  backdrop-filter: blur(10);
}

.w-app {
  padding: 0px;
  background-color: #ffffff;
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

header,
footer {
  background-color: #ffffff;
  min-height: 4vh;
}

aside {
  background-color: #ffffff;
}

main {
  background-color: #ffffff;
}
</style>
