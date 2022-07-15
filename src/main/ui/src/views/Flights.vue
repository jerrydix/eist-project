<script setup>
import POI from "./POI.vue";
import FlightSuggestionCard from "../components/FlightSuggestionCard.vue";
import FlightInfo from "../components/FlightInfo.vue";
import FlightMap from "../components/FlightMap.vue";
let departureSuggestions = null;
let selectedDeparture = null;
let departureCity = null;
let arrivalSuggestions = null;
let selectedArrival = null;
let arrivalCity = null;
let depClicked = null;
let arrClicked = null;
let date = "12.07.2022";
let flights = null;
let key = null;
let center = null;

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
		flights = response;
		console.log(response);
	});
}

</script>

<template>
	<w-flex>
		<w-flex class="xs6">
			<w-flex class="xs12">
                <h1>My trips</h1>
            </w-flex>

			<w-flex class="xs12">
				<w-textarea
					v-model="departureCity"
					placeholder="Departure City"
					@input="searchDep"
				></w-textarea>
				<w-list
					v-model="selectedDeparture"
					:items="departureSuggestions"
					:multiple="false"
					:no-unselect="true"
					class="mt6 mr4 grow"
					color="deep-purple"
					@item-click="depClicked = $event"
				>
				</w-list>
			</w-flex>

			<w-button @click="saveDep">Enter Departure Selection</w-button>
			<br />
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
			<w-button @click="getFlights">Show Flights</w-button>
			<FlightInfo v-if="flights" :flight="flights[0]" />

			<FlightSuggestionCard />

		</w-flex>
		<w-flex class="xs6">
			<FlightMap />
		</w-flex>
	</w-flex>
</template>
