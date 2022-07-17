<script>

export default {
  data() {
    return {
      flight : {
        number : "PW1234",
        startName : "Berlin",
        endName : "Amsterdam",
        departureTime : "12:00",
        arrivalTime : "14:00",
        departureDate : "12/12/2012"
      },
      items : {
        {
      }
    }
  }
}
</script>



<template>

  <w-card :title="this.flight.number">
    <w-timeline :items="[{title: this.flight.departureTime, content: this.flight.startName}, {title: this.flight.arrivalTime, content: this.flight.endName}]" color="midnightblue"></w-timeline>
    <template #actions>
      <div class="spacer"></div>
      <w-button bg-color="success">
        Add to journey
      </w-button>
    </template>

  </w-card>

</template>