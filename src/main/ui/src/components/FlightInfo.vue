<template>
	<w-card class="flight-info-card">
		<w-flex>
			<div class="xs8 data-wrapper">
				<p class="from-to-location">Berlin <font-awesome-icon icon="fa-solid fa-right-long" /> Garching</p>
				<p class="from-to-time">20:00 - 23:00</p>
				<p class="delay">+00:25</p>
				<p class="seat">Seat: 42</p>
				<p class="id">Flight No: GA2553</p>
				<p class="terminal">Terminal: 7</p>
				<p class="gate">Gate: A4</p> 
				<p class="plane">Plane: Airbus A320</p>
			</div>
			<div class="xs4 icon-wrapper">
				<p class="temperature">+26°C</p>
				<font-awesome-icon icon="fa-solid fa-cloud" />
				<p class="weather-text">Wolkig mit Aussicht auf Fleischbällchen</p>
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
