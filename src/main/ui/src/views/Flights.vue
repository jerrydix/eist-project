<script>
import {
	constructJourney,
	getFlights,
	getSuggestions,
} from "../services/FlightService.js";
import FlightSuggestionCard from "../components/FlightSuggestionCard.vue";
import Journey from "../components/Journey.vue";
import { userStore } from "../userStore";
export default {
	components: { FlightSuggestionCard, Journey },
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
			date: "2022-08-01",
			center: null,
			flight: null,
			card: {
				flights: [],
			},
			showPOI: false,
			locationID: -1,
			table: {
				flights: [],
			},
		};
	},
	mounted() {
		const formatted_date = new Date().toJSON().slice(0, 10);
		this.date = formatted_date;
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
			this.selectedDeparture = null;
			this.departureSuggestions = null;
		},
		saveArr() {
			this.arrivalCity = this.selectedArrival;
			this.selectedArrival = null;
			this.arrivalSuggestions = null;
		},
		getFlights() {
			this.requestedFlights = true;
			getFlights(this.departureCity, this.arrivalCity, this.date).then(
				(response) => {
					console.log(response);
					this.card.flights = response;
					this.reRender();
				}
			);
		},
		addToJourney(flight) {
			this.requestedFlights = false;
			this.table.flights.push(flight);
			this.card.flights = [];
			this.departureCity = this.arrivalCity;
			this.arrivalCity = "";
			this.reRender();
		},
		deleteLast() {
			this.table.flights.pop();
			if (this.table.flights.length > 0) {
				this.departureCity =
					this.table.flights[
						this.table.flights.length - 1
					].fullEndName;
			} else {
				this.departureCity = "";
			}
			this.reRender();
		},
		saveJourney() {
			constructJourney(JSON.stringify(this.table.flights)).then(() => {
				this.$waveui.notify("Saved journey");
				this.table.flights = [];
				this.card.flights = [];
				this.departureCity = "";
				this.arrivalCity = "";
				this.selectedArrival = "";
				this.selectedDeparture = "";
				this.reRender();
			});
		},
		reRender() {
			this.map = false;
			this.$nextTick().then(() => {
				this.map = true;
			});
		},
	},
};
</script>

<template>
	<w-app v-if="this.store.username" id="app">
		<w-flex grow>
			<div class="xs6">
				<div class="flightSearch">
					<h2 style="text-align: center; padding-top: 10px; padding-bottom: 10px">Book flights</h2>
					<w-flex
						grow
						justify-space-between
						style="padding-left: 40px; padding-right: 40px"
					>
						<div class="xs3" style="position: relative">
							<label>From</label>
							<w-input
								v-model="this.departureCity"
								:readonly="this.table.flights.length > 0"
								outline
								placeholder="Enter a city"
								@input="searchDep"
							></w-input>
							<w-list
								v-model="this.selectedDeparture"
								:items="this.departureSuggestions"
								:multiple="false"
								:no-unselect="true"
								class="mt6 mr4 grow"
								color="deep-purple"
								@item-click="saveDep"
							>
							</w-list>
						</div>
						<div class="xs3">
							<label>To</label>
							<w-input
								v-model="this.arrivalCity"
								outline
								placeholder="Enter a city"
								@input="searchArr"
							></w-input>
							<w-list
								v-model="this.selectedArrival"
								:items="this.arrivalSuggestions"
								:multiple="false"
								:no-unselect="true"
								class="mt6 mr4 grow"
								color="deep-purple"
								@item-click="saveArr"
							>
							</w-list>
						</div>

						<div class="xs3">
							<label>Date</label>
							<br />
							<w-input
								v-model="this.date"
								outline
								type="date"
							></w-input>
							<w-button
								class="showFlights"
								:disabled="
									this.requestedFlights ||
									!this.arrivalCity ||
									!this.departureCity
								"
								@click="getFlights"
								>Show Flights</w-button
							>
						</div>
					</w-flex>
					<w-card class="note" bg-color="warning">
						Watch out for overlapping departure and arrival times
						when booking your flights!
					</w-card>
				</div>
				<div class="suggestions">
					<FlightSuggestionCard
						v-for="(option, i) in this.card.flights"
						:key="i"
						:flight="option"
						@select="addToJourney"
					/>
				</div>
			</div>
			<div class="xs6">
				<Journey :flights="this.table.flights" :map="this.map">
					<w-button
						bg-color="error"
						:disabled="this.table.flights.length === 0"
						@click="deleteLast"
						>Delete last flight</w-button
					>
				</Journey>
				<w-button
					class="save"
					bg-color="secondary"
					:disabled="this.table.flights.length === 0"
					@click="saveJourney"
					style="margin-right: 10px"
					>Save Journey</w-button
				>
			</div>
		</w-flex>
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

<style scoped>
.flightSearch {
	height: 20vh;
	background-color: rgba(75, 169, 246, 0.162);
}

.suggestions {
	overflow-x: hidden;
	overflow-y: auto;
	height: 80vh;
}

.w-button.save {
	width: 100%;
	height: 35px;
	margin-top: 3px;
}

.w-card.note {
	max-width: 300px;
	font-size: 85%;
	padding: 2px;
	margin-top: -20px;
	margin-left: 20px;
}

.w-card.showFlights {
	display: inline-block;
	text-align: right;
}

ul.w-list {
	position: absolute;
	z-index: 2;
	background-color: #fff;
	margin: 0;
	width: 100%;
}

ul.w-list > li {
	padding-left: 5px;
}
</style>
