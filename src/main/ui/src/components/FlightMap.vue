<template>
	<GoogleMap
		:api-key="key"
		style="width: 100%; height: 100%"
		:center="center"
		:zoom="10"
	>
		<Marker :options="{ position: center }" />
		<Marker :options="{ position: excellence }" />
	</GoogleMap>
</template>

<script setup>
import { GoogleMap, Marker } from "vue3-google-map";

const key = import.meta.env.VITE_GOOGLE_API_KEY;
const center = { lat: 40.689247, lng: -74.044502 };
const excellence = { lat: 48.2650433, lng: 11.6693806 };

const props = defineProps({
	flights: Array,
});
</script>
