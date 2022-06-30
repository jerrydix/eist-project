<template>
	<GoogleMap
		:api-key="key"
		style="width: 100vw; height: 100vh"
		:center="center"
		:zoom="15"
	>
		<Marker :options="{ position: center }" />
		<Marker :options="{ position: excellence }" />
	</GoogleMap>
</template>

<script>
import { defineComponent } from "vue";
import { GoogleMap, Marker } from "vue3-google-map";

export default defineComponent({
	components: { GoogleMap, Marker },
	setup() {

		const key = import.meta.env.VITE_GOOGLE_API_KEY;
		const center = { lat: 40.689247, lng: -74.044502 };
		const excellence = { lat: 48.2650433, lng: 11.6693806 };

		return { key, center, excellence };
	},
});
</script>
