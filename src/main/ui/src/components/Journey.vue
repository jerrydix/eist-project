<script>
import FlightMap from "./FlightMap.vue";
import POI from "../views/POI.vue";

export default {
	name: "Journey",
	props: ["flights", "map"],
	components: { FlightMap, POI },
	data() {
		return {
			showPOI: false,
			locationID: -1,
			headers: [
				{ label: "Number", key: "number", align: "center" },
				{ label: "Start", key: "startName", align: "center" },
				{ label: "End", key: "endName", align: "center" },
				{ label: "Departure", key: "departureDate", align: "center" },
			],
		};
	},
	methods: {
		show(locationID) {
			this.showPOI = true;
			this.locationID = locationID;
		},
	},
};
</script>

<template>
	<w-flex wrap class="column justify-space-between">
		<w-flex class="xs12 column justify-end">
			<FlightMap
				v-if="this.map"
				:flightList="this.flights"
				width="50vw"
				height="50vh"
				@show="show"
			/>
		</w-flex>
		<w-flex shrink class="xs12">
			<w-table
				:headers="this.headers"
				:items="this.flights"
				no-data="no-data"
				fixed-headers
			>
				<template #footer>
					<w-flex justify-start style="padding: 5px;">
						<slot>Here you should see a button</slot>
					</w-flex>
				</template>
			</w-table>
		</w-flex>
	</w-flex>

	<w-dialog v-model="this.showPOI" :width="1000">
		<POI :location="this.locationID" />
	</w-dialog>
</template>
<style scoped>
.w-table-wrap {
	width: 100%;
}
</style>
