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
      locationId: -1,
      headers: [
        {label: "Number", key: "number", align: "center"},
        {label: "Airline", key: "airline", align: "center"},
        {label: "Origin", key: "startName", align: "center"},
        {label: "Destination", key: "endName", align: "center"},
        {label: "Date", key: "departureDate", align: "center"},
        {label: "Departure", key: "departureTime", align: "center"},
        {label: "Arrival", key: "arrivalTime", align: "center"},


      ],
      realmap: true,
      realflights: null,
      showDetails: false,
      selection: null,
    };
  },
  watch: {
    map(val, oldVal) {
      this.realmap = val;
      console.log(oldVal);
    },
    flights(val, oldval) {
      this.realflights = val;
      console.log(oldval);
    }
  },
  created() {
    if (this.$route.fullPath === "/dashboard/journey") {
      console.log(this.$route);
      this.realflights = JSON.parse(this.$route.params.flights);

    } else {
      this.realflights = this.flights;
      this.realmap = this.map;
    }
  },
  methods: {
    show(locationId) {
      this.showPOI = true;
      this.locationId = locationId;
    },
    showDets(selection) {
      this.selection = selection
      this.showDetails = true;
    }
  },
};
</script>

<template>
  <w-flex column>
    <FlightMap
        v-if="this.realmap"
        :flightList="this.realflights"
        height="50vh"
        width="100%"
        @show="show"
    />
    <div class="xs12 pa1">
      <w-table
          :force-selection="false"
          :headers="this.headers"
          :items="this.realflights"
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
    </div>
  </w-flex>
  <w-dialog v-model="this.showDetails" :width="580">
    <FlightInfo :flight="this.selection.item"/>
  </w-dialog>

  <w-dialog v-model="this.showPOI" :width="1000">
    <POI :location="this.locationId"/>
  </w-dialog>
</template>

<style scoped>
.w-table-wrap {
  width: 100%;
}
</style>
