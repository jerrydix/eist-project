<template>
  <GoogleMap
      :api-key="key"
      :center="center"
      :zoom="15"
      style="width: 50vw; height: 100vh"
  >
    <Marker v-for="(option,i) in this.locations" :key="i" :options="option"></Marker>
  </GoogleMap>
</template>

<script>
import {defineComponent} from "vue";
import {GoogleMap, Marker} from "vue3-google-map";

export default defineComponent({
  components: {GoogleMap, Marker},
  props: ['flightList'],
  data() {
    return {
      key: import.meta.env.VITE_GOOGLE_API_KEY,
      map: true,
      center: {lat: 0, lng: 0},

      selectionInfo: {},
      isTopTen: false,

      locations: [],
    }
  },
  mounted() {
    for (let i = 0; i < this.flightList.length; i++) {
      location.push({position: this.flightList[i].startLocation.position,});
    }
    location.push({position: this.flightList[this.flightList.length - 1].endLocation.position})
  }
});
</script>
