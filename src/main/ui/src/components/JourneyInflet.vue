<template>
  <p>
    {{ journey.originName }}
    {{ journey.endName }}
    {{ journey.startDate }}
    <w-button @click="show">Show details</w-button>
  </p>

</template>

<script>
export default {
  name: "JourneyInflet",
  props: ['journey'],
  emits: ['show'],
  methods: {
    show() {
      this.$emit("show", this.journey.flights);
    }
  }
}
</script>

<style scoped>

</style>