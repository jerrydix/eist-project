<template>
  <GoogleMap
      :api-key="key"
      :center="current"
      :zoom="this.table.items.length === 0 ? 2 : 16"
      style="width: 100%; height: 100%"
  >
    <Marker v-for="(option,i) in this.table.items" :key="i" :options="option">
      <InfoWindow>
        <h4>{{ option.title }}</h4>
        <div>{{ option.description }}</div>
        <w-rating v-model="option.favourited" color="yellow" max="1" md readonly></w-rating>
        <br>
        <w-button v-if="option.favourited" @click="unsave($event,option.id)">Delete from Favourites</w-button>

      </InfoWindow>
    </Marker>

  </GoogleMap>
</template>

<script>
import {defineComponent} from "vue";
import {GoogleMap, InfoWindow, Marker} from "vue3-google-map";

export default defineComponent({
  components: {GoogleMap, Marker, InfoWindow},
  emits: ['unsave'],
  props: ['current', 'table', 'selectionInfo'],
  data() {
    return {
      key: import.meta.env.VITE_GOOGLE_API_KEY,
    }
  },
  mounted() {

  },
  methods: {
    unsave(event, id) {
      this.$emit("unsave", id);
    },
  }
});
</script>

<style>

.box {
  background-color: #e2ecfc;
  border: 1px solid #b2c2f0;
  padding: 12px 0;
  text-align: center;
}

.w-app {
  padding: 0px;
  background-color: #ffffff;
}

header, footer, aside, main {
  margin: 0px;
  padding: 0px;
  color: #000000;
  border: 0px solid rgba(0, 0, 0, 0.1);
}

header, footer {
  background-color: #ffffff;
  min-height: 4vh;
}

aside {
  background-color: #ffffff
}

main {
  background-color: #ffffff;
}

</style>
