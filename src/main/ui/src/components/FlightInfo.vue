<script setup>
/* let date = new Date(); */

const props = defineProps({
	flight: Object
});

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
					{{ flight.airline }}
					<font-awesome-icon icon="fa-solid fa-right-long" />
					{{ flight.to }}
				</p>
				<p class="from-to-time">
					{{ flight.startTime }} - {{ flight.endTime }}
				</p>
				<p class="delay">{{ flight.delay }}</p>
				<p class="seat">Seat: {{ flight.seat }}</p>
				<p class="id">Flight No: {{ flight.id }}</p>
				<p class="terminal">Terminal: {{ flight.terminal }}</p>
				<p class="gate">Gate: {{ flight.gate }}</p>
				<p class="plane">Plane: {{ flight.airplane }}</p>
			</div>
			<div class="xs4 icon-wrapper">
				<p class="temperature">+26°C</p>
				<font-awesome-icon icon="fa-solid fa-cloud" />
				<p class="weather-text">
					Wolkig mit Aussicht auf Fleischbällchen
				</p>
				<div class="spacer"></div>
				<p class="date">{{ flight.date }}</p>
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
