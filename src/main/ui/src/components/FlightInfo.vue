<template>
	<w-card class="flight-info-card">
		<w-flex>
			<div class="xs4 icon-wrapper">
				<font-awesome-icon icon="fa-solid fa-cloud" />
				<p class="weather-text">Wolkig mit Aussicht auf Fleischbällchen</p>
			</div>
			<div class="xs8">
				<p>oida</p>
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
	height: 45%;
}

.weather-text {
	text-align: left;
}

</style>
