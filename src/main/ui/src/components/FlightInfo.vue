
<script setup>
/* let date = new Date(); */


//var weatherImage;
const props = defineProps({
	flight: Object
  //weatherImage: String
});
/*console.log(flight);
switch (flight["weather"]["weatherType"]) {
  case "Clouds": weatherImage = "fa-clouds";
  break;
  case "Rain": weatherImage = "fa-cloud-showers";
  break;
  case "Thunderstorm": weatherImage = "fa-cloud-bolt";
  break;
  case "Drizzle": weatherImage = "fa-cloud-drizzle";
  break;
  case "Snow": weatherImage = "fa-cloud-snow";
  break;
  case "Mist": weatherImage = "fa-cloud-fog";
  break;
  case "Smoke": weatherImage = "fa-smoke";
  break;
  case "Haze": weatherImage = "fa-smog";
  break;
  case "Dust": weatherImage = "fa-sun-dust";
  break;
  case "Fog": weatherImage = "fa-cloud-fog";
  break;
  case "Sand": weatherImage = "fa-sun-dust";
  break;
  case "Ash": weatherImage = "fa-cloud-meatball";
  break;
  case "Squall": weatherImage = "fa-wind";
  break;
  case "Tornado": weatherImage = "fa-tornado";
  break;
  case "Clear": weatherImage = "fa-sun";
  break;
  default: weatherImage = "fa-cloud";
  break;
}*/

/* let flight = {
	from: "Berlin",
	to: "Garching",
	startTime: "20:00",
	endTime: "23:00",
	delay: "+00:25",
	seat: 42,
	id: "GA2553",
	terminal: 7,
	gate: "A4",
	plane: "Airbus A320",
	date: date.toISOString().split("T")[0],
}; */
</script>

<template>

	<w-card class="flight-info-card">
		<w-flex>
			<div class="xs8 data-wrapper">
				<p class="from-to-location">
					<!-- {{ flight.from }} -->
					{{ flight["startLocation"]["name"] }}
					<font-awesome-icon icon="fa-solid fa-right-long" />
					{{ flight["endLocation"]["name"] }}
				</p>
				<p class="from-to-time">
					{{ flight.startTime.substring(11, 16) }} - {{ flight.endTime.substring(11, 16) }}
				</p>
        <!--TODO fix delay-->
				<p v-if="delay">{{ flight.delayTime.substring(11, 16) + " - " + flight.delayedArrivalTime.substring(11, 16) + " (+" + flight.delayHours + ":" + flight.delayMinutes + ")" }}</p>
				<p class="seat">Seat: {{ flight.seat }}</p>
				<p class="id">Flight No: {{ flight.number }}</p>
				<p class="terminal">Terminal: {{ flight.terminal }}</p>
				<p class="gate">Gate: {{ flight.gate }}</p>
				<p class="plane">Plane: {{ flight.airplane }}</p>
			</div>
			<div class="xs4 icon-wrapper">
				<p class="temperature">{{ flight["endLocation"]["weather"]["degrees"] + "°C" }}</p>
				<font-awesome-icon icon="fa-solid fa-cloud" /> <!--{{ weatherImage }}-->
				<p class="weather-text">{{ flight["endLocation"]["weather"]["weatherType"] }}</p>
				<div class="spacer"></div>
				<p class="date">{{ flight.startTime.substring(8,10) + "/" + flight.startTime.substring(5,7) + "/" + flight.startTime.substring(0,4) }}</p>
			</div>
		</w-flex>
	</w-card>

</template>

<style scoped>
.w-flex {
  height: 100%;
}

.flight-info-card {
  height: 300px;
  width: 100%;
  margin-top: 50px;
  min-width: 550px;
  border-radius: 15px;
  background-color: var(--color-background-mute-transparent);
  box-shadow: 0px 0px 22px -3px rgba(0, 0, 0, 0.45);
  -webkit-box-shadow: 0px 0px 22px -3px rgba(0, 0, 0, 0.45);
  -moz-box-shadow: 0px 0px 22px -3px rgba(0, 0, 0, 0.45);
}

.icon-wrapper svg {
  height: 25%;
}

.weather-text {
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
  font-size: 3rem;
}
</style>
