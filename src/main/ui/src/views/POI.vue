<template>
  <w-app id="app">
    <header>Your Favourite Points of Interest</header>
     <w-flex grow>
       <aside>
         <w-table
             :headers="table.headers"
             :items="table.items"
             no-data="no-data"
             :selectable-rows="table.selectableRows"
             :force-selection="table.forceSelection"
             @row-select="selectionInfo = $event"
             style="height: 95vh">
         </w-table>
       </aside>
       <main class="grow">
         <GoogleMap
           :api-key="key"
           style="width: 100vw; height: 95vh"
           :center="center"
           :zoom="15"
         >
         <Marker :options="{ position: center }" />
         <Marker :options="{ position: excellence }" />
        </GoogleMap>
       </main>
     </w-flex>
  </w-app>
</template>

<script>
import { defineComponent } from "vue";
import { GoogleMap, Marker } from "vue3-google-map";

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
header, footer {background-color: #ffffff; min-height: 5vh;}
aside {background-color: #ffffff}
main {background-color: #ffffff;}

</style>
