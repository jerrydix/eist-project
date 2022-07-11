<template>
  <w-flex class="xs6">
    <h1>flights</h1>
    <w-textarea v-model="this.departureCity" placeholder="Departure City" @input="searchDep"></w-textarea>
    <w-list
        v-model="selectedDeparture"
        :items="departureSuggestions"
        :multiple=false
        :no-unselect=true
        class="mt6 mr4 grow"
        color="deep-purple"
        @item-click="depClicked = $event">
    </w-list>
    <w-button @click="saveDep">Enter Departure Selection</w-button>
    <br/>
    <w-textarea v-model="this.arrivalCity" placeholder="Arrival City" @input="searchArr"></w-textarea>
    <w-list
        v-model="selectedArrival"
        :items="arrivalSuggestions"
        :multiple=false
        :no-unselect=true
        class="mt6 mr4 grow"
        color="deep-purple"
        @item-click="arrClicked = $event">
    </w-list>
    <w-button @click="saveArr">Enter Destination Selection</w-button>
    <w-button @click="getFlights">Show Flights</w-button>
    <FlightInfo v-if="this.flights"
                :flight="flights[0]"
    />
  </w-flex>

  <w-flex class="xs6">

    <!-- <FlightMap></FlightMap> -->

  </w-flex>

</template>

<script>
import {getFlights, getSuggestions} from "../services/FlightService.js";
import FlightInfo from "../components/FlightInfo.vue";

export default {
  components: { FlightInfo, FlightMap },
  data() {
    return {
      departureSuggestions: null,
      selectedDeparture: null,
      departureCity: null,
      arrivalSuggestions: null,
      selectedArrival: null,
      arrivalCity: null,
      depClicked: null,
      arrClicked: null,
      date: "12.07.2022",
      flights: null
    }
  },
  methods: {
    searchDep(city) {
      if (city.length > 2) {
        getSuggestions(city).then((response) => {
          this.departureSuggestions = response
        });
      }
    },
    searchArr(city) {
      if (city.length > 2) {
        getSuggestions(city).then((response) => {
          this.arrivalSuggestions = response
        });
      }
    },
    saveDep() {
      this.departureCity = this.selectedDeparture;
      this.departureSuggestions = null
    },
    saveArr() {
      this.arrivalCity = this.selectedArrival;
      this.arrivalSuggestions = null
    },
    getFlights() {
      getFlights(this.departureCity, this.arrivalCity, this.date).then((response) => {
        this.flights = response
        console.log(response)
      });
    }
  }
}</script>

<style>


</style>