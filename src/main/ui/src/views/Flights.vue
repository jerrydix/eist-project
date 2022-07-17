<script setup>
import FlightSuggestionCard from "../components/FlightSuggestionCard.vue";
import FlightMap from "../components/FlightMap.vue";
import {getSuggestions} from "../services/FlightService";

let departureSuggestions = null;
let selectedDeparture = null;
let departureCity = null;
let arrivalSuggestions = null;
let selectedArrival = null;
let arrivalCity = null;
let depClicked = null;
let arrClicked = null;
let date = "12.07.2022";
//let flights = null;
let key = null;
let center = null;

let table = {
  headers: [
    {label: 'Number', key: 'number', align: 'center'},
    {label: 'Start', key: 'startName', align: 'center'},
    {label: 'End', key: 'endName', align: 'center'},
    {label: 'Departure', key: 'departureDate', align: 'center'},
  ],
  flights: [{number: "ala", startName: "lel", endName: "lol", departureDate: "10/10/10"}],
}

function searchDep(city) {
  if (city.length > 2) {
    getSuggestions(city).then((response) => {
      departureSuggestions = response;
    });
  }
}

function searchArr(city) {
  if (city.length > 2) {
    getSuggestions(city).then((response) => {
      arrivalSuggestions = response;
      console.log(arrivalSuggestions);
    });
  }
}

function saveDep() {
  departureCity = selectedDeparture;
  departureSuggestions = null;
}

function saveArr() {
  arrivalCity = selectedArrival;
  arrivalSuggestions = null;
}

function getFlights() {
  getFlights(departureCity, arrivalCity, date).then((response) => {
    this.table.flights = response;
    console.log(response);
  });
}

</script>

<template>
  <w-app id="app">
    <header>
      <h2 class="text-center">Flights</h2>
    </header>
    <main grow>
      <w-flex grow>
        <div class="xs6">
          <div class="xs6">
            <w-textarea v-model="departureCity"
                        placeholder="Departure City"
                        @input="searchDep"
            ></w-textarea>
            <w-list v-model="selectedDeparture"
                    :items="departureSuggestions"
                    :multiple="false"
                    :no-unselect="true"
                    class="mt6 mr4 grow"
                    color="deep-purple"
                    @item-click="depClicked = $event"
            >
            </w-list>
            <w-button @click="saveDep">Enter Departure Selection</w-button>
          </div>
          <div class="xs6">
            <w-textarea
                v-model="arrivalCity"
                placeholder="Arrival City"
                @input="searchArr"
            ></w-textarea>
            <w-list
                v-model="selectedArrival"
                :items="arrivalSuggestions"
                :multiple="false"
                :no-unselect="true"
                class="mt6 mr4 grow"
                color="deep-purple"
                @item-click="arrClicked = $event"
            >
            </w-list>
            <w-button @click="saveArr">Enter Destination Selection</w-button>
          </div>
          <FlightSuggestionCard/>
        </div>
        <div class="xs6">
          <w-table :headers="table.headers" :items="table.flights" no-data="no-data">
          </w-table>
          <FlightMap/>
        </div>
      </w-flex>
    </main>
  </w-app>
</template>
