<script>
import FlightMap from "./FlightMap.vue";
import POI from "../views/POI.vue";
import FlightInfo from "./FlightInfo.vue";

export default {
  name: "Journey",
  props: ["flights", "map"],
  components: {FlightInfo, FlightMap, POI},
  data() {
    return {
      showPOI: false,
      locationID: -1,
      headers: [
        {label: "Number", key: "number", align: "center"},
        {label: "Airline", key: "airline", align: "center"},
        {label: "Origin", key: "startName", align: "center"},
        {label: "Destination", key: "endName", align: "center"},
        {label: "Date", key: "departureDate", align: "center"},
        {label: "Departure", key: "departureTime", align: "center"},
        {label: "Arrival", key: "arrivalTime", align: "center"},


      ],

      showDetails: false,
      selection: null,
    };
  },
  methods: {
    show(locationID) {
      this.showPOI = true;
      this.locationID = locationID;
    },
    showDets(selection) {
      this.selection = selection
      this.showDetails = true;
    }
  },
};
</script>

<template>
  <w-flex class="column justify-space-between" wrap>
    <w-flex class="xs12 column justify-end">
      <FlightMap
          v-if="this.map"
          :flightList="this.flights"
          height="50vh"
          width="100%"
          @show="show"
      />
    </w-flex>
    <w-flex class="xs12" shrink>
      <w-table
          :force-selection="false"
          :headers="this.headers"
          :items="this.flights"
          :selectable-rows="1"
          fixed-headers
          no-data="no-data"
          @row-select="showDets"
      >
        <template #footer>
          <w-flex justify-start style="padding: 5px;">
            <slot></slot>
          </w-flex>
        </template>
      </w-table>
    </w-flex>
  </w-flex>
  <w-dialog v-model="this.showDetails">
    <FlightInfo :flight="this.selection.item"/>
  </w-dialog>
  <w-dialog v-model="this.showPOI" :width="1000">
    <POI :location="this.locationID"/>
  </w-dialog>
</template>
<style scoped>
.w-table-wrap {
  width: 100%;
}
</style>
