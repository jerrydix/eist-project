<template>
  <GoogleMap
      :api-key="key"
      :center="center"
      :zoom="3"
      style="width: 50vw; height: 100vh"
  >
    <Marker v-for="(option,i) in this.locations" :key="i" :options="option"></Marker>
    <Polyline :options="flightPath"/>
  </GoogleMap>
</template>

<script>
import {defineComponent} from "vue";
import {GoogleMap, Marker, Polyline} from "vue3-google-map";

export default defineComponent({
  components: {GoogleMap, Marker, Polyline},
  props: ['flightList'],
  data() {
    return {
      key: import.meta.env.VITE_GOOGLE_API_KEY,
      center: {lat: 0, lng: 0},

      locations: [],
      flightPlan: [],
      flightPath: {
        path: [],
        geodesic: true,
        strokeColor: "#FF0000",
        strokeOpacity: 1.0,
        strokeWeight: 2,
      },
    }
  },
  mounted() {
    for (let i = 0; i < this.flightList.length; i++) {
      this.center = this.flightList[0].startLocation.position;
      this.locations.push({position: this.flightList[i].startLocation.position, label: (i + 1).toString()});
    }
    this.locations.push({
      position: this.flightList[this.flightList.length - 1].endLocation.position,
      label: (this.flightList.length + 1).toString()
    })

    for (let i = 0; i < this.locations.length; i++) {
      this.flightPlan.push(this.locations[i].position);
      this.flightPath.path = this.flightPlan;
    }
  }
});
</script>
