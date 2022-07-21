<template>
  <w-table :headers="this.headers" :items="this.flights" no-data="no-data" style="height: 41vh">
  </w-table>
  <FlightMap v-if="this.map" :flightList="this.flights" @show="show"/>
  <w-dialog v-model="this.showPOI" :width="1000">
    <FlightsPOI :location-i-d="this.locationID"/>
  </w-dialog>
</template>

<script>
import FlightMap from "./FlightMap.vue";
import FlightsPOI from "./FlightsPOI.vue";

export default {
  name: "Journey",
  props: ['flights', 'map'],
  components: {FlightMap, FlightsPOI},
  data() {
    return {
      showPOI: false,
      locationID: -1,
      headers: [
        {label: 'Number', key: 'number', align: 'center'},
        {label: 'Start', key: 'startName', align: 'center'},
        {label: 'End', key: 'endName', align: 'center'},
        {label: 'Departure', key: 'departureDate', align: 'center'},
      ],
    }
  },
  methods: {
    show(locationID) {
      this.showPOI = true;
      this.locationID = locationID;
    },
  }
}
</script>

<style scoped>

</style>