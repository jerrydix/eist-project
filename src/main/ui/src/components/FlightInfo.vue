<script>

export default {
  props: ["flight"],
  data() {
    return {
      weatherImage: ""
    }
  },
  created() {
    switch (this.flight["endLocation"]["weather"]["weatherType"]) {
      case "Clouds":
        this.weatherImage = "fa-solid fa-cloud";
        break;
      case "Rain":
        this.weatherImage = "fa-solid fa-cloud-showers-heavy";
        break;
      case "Thunderstorm":
        this.weatherImage = "fa-solid fa-cloud-bolt";
        break;
      case "Drizzle":
        this.weatherImage = "fa-solid fa-cloud-rain";
        break;
      case "Snow":
        this.weatherImage = "fa-solid fa-snowflake";
        break;
      case "Mist":
        this.weatherImage = "fa-solid fa-smog";
        break;
      case "Smoke":
        this.weatherImage = "fa-solid fa-smog";
        break;
      case "Haze":
        this.weatherImage = "fa-solid fa-smog";
        break;
      case "Dust":
        this.weatherImage = "fa-solid fa-smog";
        break;
      case "Fog":
        this.weatherImage = "fa-solid fa-smog";
        break;
      case "Sand":
        this.weatherImage = "fa-solid fa-wind";
        break;
      case "Ash":
        this.weatherImage = "fa-solid fa-smog";
        break;
      case "Squall":
        this.weatherImage = "fa-solid fa-wind";
        break;
      case "Tornado":
        this.weatherImage = "fa-solid fa-tornado";
        break;
      case "Clear":
        this.weatherImage = "fa-solid fa-sun";
        break;
      default:
        this.weatherImage = "fa-solid fa-cloud";
        break;
    }
  }
}


</script>

<template>
  <w-card class="flight-info-card">
    <w-flex>
      <div class="xs8 data-wrapper">
        <p class="airline">{{ flight.airline }}</p>
        <p class="from-to-location">
          <!-- {{ flight.from }} -->
          {{ flight.startLocation.name }}
          <font-awesome-icon icon="fa-solid fa-right-long"/>
          {{ flight.endLocation.name }}
        </p>
        <p class="from-to-time">
          {{ flight.startTime.substring(11, 16) }} -
          {{ flight.endTime.substring(11, 16) }}
        </p>
        <!--TODO fix delay-->
        <p v-if="flight.delayTime" class="delay">
          {{
            flight.delayTime.substring(11, 16) +
            " - " +
            flight.delayedArrivalTime.substring(11, 16) +
            " (+" +
            flight.delayHours +
            ":" +
            flight.delayMinutes +
            ")"
          }}
        </p>
        <p class="id">Flight No: {{ flight.number }}</p>
        <p class="seat">Seat: {{ flight.seat }}</p>
        <p class="terminal">Terminal: {{ flight.terminal }}</p>
        <p class="gate">Gate: {{ flight.gate }}</p>
        <p class="plane">Plane: {{ flight.airplane }}</p>
      </div>
      <div class="xs4 icon-wrapper">
        <p class="temperature">
          {{ flight["endLocation"]["weather"]["degrees"] + "°C" }}
        </p>
        <div>
          <font-awesome-icon :icon="weatherImage"/>
        </div>
        <p>
          {{ flight["endLocation"]["weather"]["weatherType"] }}
        </p>
        <p>
          in {{ flight.endLocation.name }}
        </p>
        <div class="spacer"></div>
        <p>
          {{
            flight.startTime.substring(8, 10) +
            "/" +
            flight.startTime.substring(5, 7) +
            "/" +
            flight.startTime.substring(0, 4)
          }}
        </p>
      </div>
    </w-flex>
  </w-card>
</template>

<style scoped>
.w-flex {
  height: 100%;
}

.flight-info-card {
  height: 320px;
  width: 100%;
  margin-top: 0px;
  min-width: 550px;
  border-radius: 0px;
  background-color: var(--color-background-mute-transparent);
  box-shadow: 0px 0px 22px -3px rgba(0, 0, 0, 0.45);
  -webkit-box-shadow: 0px 0px 22px -3px rgba(0, 0, 0, 0.45);
  -moz-box-shadow: 0px 0px 22px -3px rgba(0, 0, 0, 0.45);
}

.icon-wrapper svg {
  height: 100px;
}

.icon-wrapper {
  text-align: center;
}

.center-text {
  text-align: center;
}

.data-wrapper {
  text-align: left;
  font-size: 1.2rem;

  padding: 10px;
}

.delay {
  color: red;
}

.temperature {
  font-size: 2.5rem;
  text-align: center;
  padding-bottom: 20px;
}
</style>
