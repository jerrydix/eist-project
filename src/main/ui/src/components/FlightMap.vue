<template>
  <GoogleMap
      :api-key="key"
      :center="center"
      :zoom="15"
      style="width: 50vw; height: 100vh"
  >

  </GoogleMap>
</template>

<script>
import {defineComponent} from "vue";
import {GoogleMap, Marker} from "vue3-google-map";

export default defineComponent({
  components: {GoogleMap, Marker},
  props: ['flightList'],
  data() {
    return {
      key: import.meta.env.VITE_GOOGLE_API_KEY,
      map: true,
      center: {lat: 0, lng: 0},
      
      selectionInfo: {},
      isTopTen: false,
    }
  },
});
</script>
