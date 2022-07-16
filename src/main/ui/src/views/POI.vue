<template>
  <w-app id="app">
    <header>
      <h2 class="text-center">About your location</h2>
    </header>
    <w-flex grow>
      <aside>
        <w-flex grow justify-center align-center>
          <w-checkbox @input="table.activeFilter == 0 ? table.activeFilter = 1 : table.activeFilter = 0">Favourites </w-checkbox>
          <div class="xs1"></div>
          <w-checkbox @input="topTen" color="orange">Top 10</w-checkbox>
        </w-flex>
        <w-table
            v-model:sort="table.sort"
            :filter="table.filters[table.activeFilter]"
            :force-selection="table.forceSelection"
            :headers="table.headers"
            :items="table.items"
            :selectable-rows="table.selectableRows"
            no-data="no-data"
            style="height: 96vh"
            @row-select="select">
        </w-table>
      </aside>
      <main class="grow">
        <GoogleMap v-if="map"
                   :api-key="key"
                   :center="current"
                   :zoom="16"
                   style="width: 100%; height: 100%"
        >
          <MarkerCluster>
            <Marker v-for="(option,i) in this.table.items" :key="i" :options="option">
              <InfoWindow>
                <h4>{{ option.title }}</h4>
                <div>{{ option.description }}</div>
                <w-rating v-model="option.favourited" color="yellow" max="1" md readonly></w-rating>
                <br>
                <w-button v-if="!option.favourited" @click="save($event,option.id)">Save to Favourites</w-button>
                <w-button v-if="option.favourited" @click="unsave($event,option.id)">Delete from Favourites</w-button>

              </InfoWindow>
            </Marker>
          </MarkerCluster>
        </GoogleMap>
      </main>
    </w-flex>
  </w-app>
</template>

<script>
import {defineComponent} from "vue";
import {GoogleMap, InfoWindow, Marker} from "vue3-google-map";
import {
  addPOIToFavourites,
  getPointsOfInterest,
  getTopPointsOfInterest,
  removePOIFromFavourites
} from "../services/POIService.js";
import {userStore} from "../userStore";

export default defineComponent({
  components: {GoogleMap, Marker, InfoWindow},
  setup() {
    const store = userStore();
    return {
      store,
    };
  },
  data() {
    return {
      key: import.meta.env.VITE_GOOGLE_API_KEY,
      map: true,
      current: {lat: 0, lng: 0},
      table: {
        headers: [
          {label: 'Name', key: 'title', align: 'center'},
          {label: 'Rating', key: 'rating', align: 'center'},
        ],
        items: [],
        selectableRows: 1,
        forceSelection: false,
        selectableRowsOption: {label: '<code class="mr2">:selectable-row="false"</code> (default)', value: false},
        filters: [
          null,
          item => item.favourited === 1,
        ],
        activeFilter: 0,
        sort: "-rating",
      },
      selectionInfo: {},
      isTopTen: false,
    }
  },
  mounted() {
    getPointsOfInterest(this.store.endLocationId).then((list) => {
      for (let i = 0; i < list.length; i++) {
        this.table.items.push(list[i]);
      }
      this.current = this.table.items[0].position;
      this.reRender();
    });
  },
  methods: {
    save(event, id) {
      addPOIToFavourites(id, this.store.endLocationId).then((list) => {

        if (this.isTopTen) {
          getTopPointsOfInterest(this.store.endLocationId).then((list) => {
            this.table.items = []
            for (let i = 0; i < list.length; i++) {
              this.table.items.push(list[i]);
            }
            this.reRender();
          });
        } else {
          this.table.items = []
          for (let i = 0; i < list.length; i++) {
            this.table.items.push(list[i]);
          }
          this.reRender();
        }
      });
    },
    unsave(event, id) {
      removePOIFromFavourites(id, this.store.endLocationId).then((list) => {
        if (this.isTopTen) {
          getTopPointsOfInterest(this.store.endLocationId).then((list) => {
            this.table.items = []
            for (let i = 0; i < list.length; i++) {
              this.table.items.push(list[i]);
            }
            this.reRender();
          });
        } else {
          this.table.items = []
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
        getTopPointsOfInterest(this.store.endLocationId).then((list) => {
          this.table.items = []
          for (let i = 0; i < list.length; i++) {
            this.table.items.push(list[i]);
          }
          this.reRender();
        });
      } else {
        getPointsOfInterest(this.store.endLocationId).then((list) => {
          this.table.items = []
          for (let i = 0; i < list.length; i++) {
            this.table.items.push(list[i]);
          }
          this.current = this.table.items[0].position;
          this.reRender();
        });
      }
      this.isTopTen = !this.isTopTen
    }
  }
});
</script>

<style>

.box {
  background-color: #e2ecfc;
  border: 1px solid #b2c2f0;
  padding: 12px 0;
  text-align: center;
}

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

header, footer {
  background-color: #ffffff;
  min-height: 4vh;
}

aside {
  background-color: #ffffff
}

main {
  background-color: #ffffff;
}

</style>
