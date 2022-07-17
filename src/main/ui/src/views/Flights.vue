<script>
import {getFlights, getSuggestions} from "../services/FlightService.js";
import FlightMap from "../components/FlightMap.vue";
import FlightSuggestionCard from "../components/FlightSuggestionCard.vue";
import {userStore} from "../userStore";

export default {
  components: {FlightMap, FlightSuggestionCard},
  setup() {
    const store = userStore();
    return {
      store,
    };
  },
  data() {
    return {
      key: import.meta.env.VITE_GOOGLE_API_KEY,
      map: true,
      departureSuggestions: null,
      selectedDeparture: null,
      departureCity: null,
      arrivalSuggestions: null,
      selectedArrival: null,
      arrivalCity: null,

      requestedFlights: false,

      depClicked: null,
      arrClicked: null,
      date: "12.07.2022",
      center: null,


      card: {
        flights: []
      },


      table: {
        headers: [
          {label: 'Number', key: 'number', align: 'center'},
          {label: 'Start', key: 'startName', align: 'center'},
          {label: 'End', key: 'endName', align: 'center'},
          {label: 'Departure', key: 'departureDate', align: 'center'},
        ],
        flights: [],
      },
    }
  },
  methods: {
    searchDep(city) {
      if (city.length > 2) {
        getSuggestions(city).then((response) => {
          this.departureSuggestions = response;
        });
      }
    },
    searchArr(city) {
      if (city.length > 2) {
        getSuggestions(city).then((response) => {
          this.arrivalSuggestions = response;
        });
      }
    },
    saveDep() {
      this.departureCity = this.selectedDeparture;
      this.departureSuggestions = null;
    },
    saveArr() {
      this.arrivalCity = this.selectedArrival;
      this.arrivalSuggestions = null;
    },
    getFlights() {
      this.requestedFlights = true;
      getFlights(this.departureCity, this.arrivalCity, this.date).then((response) => {
        this.card.flights = response;
        this.reRender();
      });
    },
    addToJourney(flight) {
      this.requestedFlights = false;
      this.table.flights.push(flight);
      this.card.flights = [];
      this.reRender();
    },
    reRender() {
      this.map = false;
      this.$nextTick().then(() => {
        this.map = true;
      });
    },
  }
}

</script>

<template>
  <w-app v-if="this.store.username" id="app">
    <header>
      <h2 class="text-center">Flights</h2>
    </header>
    <main grow>
      <w-flex grow>
        <div class="xs6">
          <w-flex grow>
            <div class="xs1"></div>
            <div class="xs3">
              <w-input
                  v-model="this.departureCity"
                  @input="searchDep"
                  outline
                  placeholder="Enter a city"
              >From</w-input>
              <w-list v-model="this.selectedDeparture"
                      :items="this.departureSuggestions"
                      :multiple="false"
                      :no-unselect="true"
                      class="mt6 mr4 grow"
                      color="deep-purple"
                      @item-click="this.depClicked = $event"
              >
              </w-list>
              <w-button @click="saveDep">Enter Departure Selection</w-button>
            </div>
            <div class="xs1"></div>
            <div class="x3">
              <w-input
                  v-model="this.arrivalCity"
                  @input="searchArr"
                  outline
                  placeholder="Enter a city"
              >To</w-input>
              <w-list
                  v-model="this.selectedArrival"
                  :items="this.arrivalSuggestions"
                  :multiple="false"
                  :no-unselect="true"
                  class="mt6 mr4 grow"
                  color="deep-purple"
                  @item-click="this.arrClicked = $event"
              >
              </w-list>
              <w-button @click="saveArr">Enter Destination Selection</w-button>
            </div>
            <div class="xs1"></div>
            <div class="xs3">
              <input type="date" id="startDate" name="start">
            </div>
          </w-flex>
          <w-flex grow>
            <div class="xs1"></div>
            <div class="xs11">
              <w-button :disabled="this.requestedFlights" @click="getFlights">Show Flights</w-button>
            </div>
          </w-flex>
          <FlightSuggestionCard v-for="(option,i) in this.card.flights" :key="i"
                                :flight="option" @select="addToJourney"/>
        </div>
        <div class="xs6">
          <w-table :headers="this.table.headers" :items="this.table.flights" no-data="no-data">
          </w-table>
          <FlightMap v-if="this.map" :flightList="this.table.flights"/>
        </div>
      </w-flex>
    </main>
  </w-app>


  <w-app v-if="!this.store.username">
    <w-flex align-center column justify-center>
      <h2>Please login to able to book flights</h2>

      <RouterLink to="/">
        <w-button>Back to Home</w-button>
      </RouterLink>

    </w-flex>
  </w-app>
</template>
