<script>
export default {
  props: ["flight"],
  emits: ["select"],
  methods: {
    select() {
      this.$emit("select", this.flight);
    }
  }
}
</script>


<template>
  <w-card :title="this.flight.number">
    <w-timeline
        :items="[{title: this.flight.departureTime, content: this.flight.startName}, {title: this.flight.arrivalTime, content: this.flight.endName}]"
        color="primary" icon="wi-info-circle"></w-timeline>
    <template #actions>
      <div class="spacer"></div>
      <w-button bg-color="success" @click="select">
        Add to journey
      </w-button>
    </template>
  </w-card>
</template>