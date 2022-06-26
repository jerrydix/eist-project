<template>
	<w-grid class="card-wrapper" columns="3">
		<RouterLink to="/flights">
			<w-button class="nav-button">Flights</w-button>
		</RouterLink>
		<RouterLink to="/catering">
			<w-button>Catering</w-button>
		</RouterLink>
		<RouterLink to="/poi">
			<w-button>About my destination</w-button>
		</RouterLink>
	</w-grid>
</template>

<style scoped>
.card-wrapper {
	background-color: var(--color-background-mute-transparent);
	margin-top: 12px;
  width: 32vw;
  border-radius: 15px;
  margin-bottom: 20px;
	box-shadow: 0px 0px 22px -3px rgba(0, 0, 0, 0.45);
	-webkit-box-shadow: 0px 0px 22px -3px rgba(0, 0, 0, 0.45);
	-moz-box-shadow: 0px 0px 22px -3px rgba(0, 0, 0, 0.45);
}
</style>
