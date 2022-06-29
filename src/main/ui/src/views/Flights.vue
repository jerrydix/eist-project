<template>
  <h1>flights</h1>


  <Autocomplete :debounce-time="500" :search="search" auto-select placeholder="Departure City"
                @submit="saveDep"></Autocomplete>


  <w-textarea placeholder="Arrival City"></w-textarea>
  <br/>


  <p>{{ this.departureCity }}</p>
</template>

<script>
import Autocomplete from "@trevoreyre/autocomplete-vue";
import {getSuggestions} from "../services/FlightService.js";

export default {
  components: {
    Autocomplete
  },
  data() {
    return {
      departureCity: null,
      destinationCity: null
    }
  },
  methods: {
    search(city) {
      getSuggestions(city).then((response) => {
        return response;
      });
    },
    saveDep(city) {
      this.departureCity = city;
    }
  }
}</script>

<style>


</style>