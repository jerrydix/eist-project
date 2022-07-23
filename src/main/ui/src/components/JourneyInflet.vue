<template>
  <w-card>
    <p>
      {{ journey.startDate }} | {{ journey.originName }} -> {{ journey.endName }} | {{ journey.endDate }} |
      {{ journey.flights.length }}

      <RouterLink :to="{name:'journey', params:{flights:JSON.stringify(this.journey.flights)}}">
        <w-button>Show details</w-button>
      </RouterLink>

    </p>
  </w-card>
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