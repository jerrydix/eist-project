<script>
import { userStore } from "../userStore";
import LoginButtons from "./LoginButtons.vue";

export default {
	setup() {
		const store = userStore();
		return {
			store,
		};
	},
	components: { LoginButtons },
};
</script>
<template>
	<w-toolbar>
		<RouterLink to="/flights">
			<w-button class="ma1" color="primary" text lg>Flights</w-button>
		</RouterLink>
		<RouterLink to="/catering">
			<w-button class="ma1" color="primary" text lg>Catering</w-button>
		</RouterLink>
		<RouterLink to="/movies">
			<w-button class="ma1" color="primary" text lg>Movies</w-button>
		</RouterLink>
		<RouterLink to="/poi">
			<w-button class="ma1" color="primary" text lg
				>About my destination</w-button>
		</RouterLink>

		<div class="spacer"></div>

		<LoginButtons />
	</w-toolbar>
</template>

<style>
.w-toolbar {
	background-color: var(--color-background-mute-transparent);
	min-height: 60px;
	max-height: 8vh;
	backdrop-filter: blur(10);
}

.w-toolbar a {
	padding-left: 0.5rem;
	padding-right: 0.5rem;
}
</style>
