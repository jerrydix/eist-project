<template>
  <GoogleMap
      :api-key="key"
      :center="center"
      :style=this.style
      :zoom="3"
  >
    <Marker
        v-for="(option, i) in this.locations"
        :key="i"
        :options="option"
    >
      <InfoWindow>
        <w-flex align-center column justify-center>
          <h2>{{ option.name }}</h2>
          <p>Current weather: {{ option.weather.description }}</p>
          <w-button @click="show(option.locationId)"
          >Show points of interest
          </w-button
          >
        </w-flex>
      </InfoWindow>
    </Marker>
    <Polyline :options="flightPath"/>
  </GoogleMap>
</template>

<script>
import {defineComponent} from "vue";
import {GoogleMap, InfoWindow, Marker, Polyline} from "vue3-google-map";

export default defineComponent({
  components: {GoogleMap, Marker, Polyline, InfoWindow},
  props: ["flightList", "width", "height"],
  data() {
    return {
      key: import.meta.env.VITE_GOOGLE_API_KEY,
      center: {lat: 0, lng: 0},

      style: "width:" + this.width + "; height: " + this.height + ";",

      locations: [],
      flightPlan: [],
      flightPath: {
        path: [],
        geodesic: true,
        strokeColor: "#FF0000",
        strokeOpacity: 1.0,
        strokeWeight: 2,
      },
    };
  },
  methods: {
    show(locationId) {
      this.$emit("show", locationId);
    },
  },
  mounted() {
    for (let i = 0; i < this.flightList.length; i++) {
      this.center = this.flightList[0].startLocation.position;
      this.locations.push(
          Object.assign(
              {label: (i + 1).toString()},
              this.flightList[i].startLocation
          )
      );
    }
    this.locations.push(
        Object.assign(
            {label: (this.flightList.length + 1).toString()},
            this.flightList[this.flightList.length - 1].endLocation
        )
    );

    for (let i = 0; i < this.locations.length; i++) {
      this.flightPlan.push(this.locations[i].position);
      this.flightPath.path = this.flightPlan;
    }
  },
});
</script>
