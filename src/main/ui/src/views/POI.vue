<template>
  <w-app id="app">
    <header>
      <h2> Your Favourite Points of Interest</h2>
      <w-button @click="goNext">GoToNextPois</w-button>
    </header>
     <w-flex grow>
       <aside>
         <w-table
             :headers="table.headers"
             :items="table.items"
             no-data="no-data"
             :selectable-rows="table.selectableRows"
             :force-selection="table.forceSelection"
             @row-select="selectionInfo = $event"
             style="height: 96vh">
         </w-table>
       </aside>
       <main class="grow">
         <GoogleMap v-if="map"
           :api-key="key"
           style="width: 100vw; height: 96vh"
           :center="current"
           :zoom="15"
         >
           <MarkerCluster>
             <Marker v-for="(position,i) in positions" :key="i" :options="{ position: position }"/>
           </MarkerCluster>
           <Marker v-for="(favouritepos,i) in favouritepositions" :key="i" :options="{ position: favouritepos }"/>
        </GoogleMap>
       </main>
     </w-flex>
  </w-app>
</template>

<script>
import { defineComponent } from "vue";
import { GoogleMap, Marker, MarkerCluster } from "vue3-google-map";
import {getDestinationPOI, getPOIFavourites} from "../services/POIService.js";

export default defineComponent({
	components: { GoogleMap, Marker },
	setup() {

		const key = import.meta.env.VITE_GOOGLE_API_KEY;
		const center = { lat: 40.689247, lng: -74.044502 };
		const excellence = { lat: 48.2650433, lng: 11.6693806 };

		return { key, center, excellence };
	},
  data() {
    return {
      key: import.meta.env.VITE_GOOGLE_API_KEY,
      map: true,
      pois: [],
      positions: [],
      favourites: [],
      favouritepositions: [],
      current: {lat: 0, lng: 0},
      curr: 0,
      table: {
        headers: [
          {label: 'Name', key: 'name'},
          {label: 'Address', key: 'latitude'},
          {label: 'Rating', key: 'longitude'}
        ],
        items: [
          {name: 'Haus', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'Baum', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'Hotel', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'Platz', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'dwad', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'dwadwd', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'dwad', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'Hdwad', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'dwwwa', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'dwwwdfad', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'Haus', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'Baum', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'Hotel', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'Platz', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'dwad', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'dwadwd', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'dwad', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'Hdwad', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'dwwwa', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'dwwwdfad', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'Haus', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'Baum', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'Hotel', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'Platz', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'dwad', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'dwadwd', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'dwad', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'Hdwad', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'dwwwa', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'dwwwdfad', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'Haus', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'Baum', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'Hotel', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'Platz', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'dwad', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'dwadwd', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'dwad', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'Hdwad', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'dwwwa', latitude: 'Floretta', longitude: 'Sampson'},
          {name: 'dwwwdfad', latitude: 'Floretta', longitude: 'Sampson'},
        ],
        selectableRows: 1,
        forceSelection: false,
        selectableRowsOption: {label: '<code class="mr2">:selectable-row="false"</code> (default)', value: false},
      },
      selectionInfo: {}
    }
  },
  mounted() {
    getPOIFavourites().then((list) => {
      for (let i = 0; i < list.length; i++) {
        this.favourites.push(list[i]);
      }
      for (let i = 0; i < list.length; i++) {
        this.favouritepositions.push({lat: this.favourites[i].latitude, lng: this.favourites[i].longitude});
      }
      this.reRender();
    });
    getDestinationPOI().then((list) => {
      for (let i = 0; i < list.length; i++) {
        this.pois.push(list[i]);
        console.log(this.pois[i]);
      }
      for (let i = 0; i < list.length; i++) {
        this.positions.push({lat: this.pois[i].latitude, lng: this.pois[i].longitude});
      }
      if (this.pois.length > 0) {
        this.current = this.positions[0];
      }
      this.reRender();
    });
  },
  methods: {
    reRender() {
      this.map = false;
      this.$nextTick().then(() => {
        this.map = true;
      });
    }, goNext() {
      this.curr++;
      if (this.curr == this.positions.length) {
        this.curr = 0;
      }
      this.current = this.positions.at(this.curr);
      this.reRender();
    },
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

.w-app {padding: 0px;background-color: #ffffff;}
header, footer, aside, main {
  margin: 0px;
  padding: 0px;
  color: #000000;
  border: 0px solid rgba(0, 0, 0, 0.1);
}
header, footer {background-color: #ffffff; min-height: 4vh;}
aside {background-color: #ffffff}
main {background-color: #ffffff;}

</style>
