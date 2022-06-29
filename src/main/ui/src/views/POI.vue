<template>
    <p>{{key}}</p>
	<GoogleMap
		api-key="AIzaSyBKiScI4WumTVipTbFuC6KPHic3dC66tvM"
		style="width: 100%; height: 600px"
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
    const center = { lat: 40.689247, lng: -74.044502 };
    const excellence = { lat: 48.2650433, lng: 11.6693806};
    return { center, excellence };
  },
  data: () => ({
		key: import.meta.VUE_APP_GOOGLE_API_KEY,
	}),
});
</script>
