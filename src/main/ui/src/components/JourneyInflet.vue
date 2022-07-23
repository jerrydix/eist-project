<template>
  <w-card>
    <p>
      {{ journey.startDate }} - {{ journey.endDate }}
    </p>
    <p>
      {{ journey.originName }} -> {{ journey.endName }}
    </p>
    <p> Journey length: {{ journey.flights.length }} </p>


    <RouterLink :to="{name:'journey', params:{flights:JSON.stringify(this.journey.flights)}}">
      <w-button>Show details</w-button>
    </RouterLink>


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