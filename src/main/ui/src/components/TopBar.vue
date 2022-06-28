<script setup>
/* import LoginButtons from "./LoginButtons.vue";
 */
const emit = defineEmits(["register", "login"]);

let showRegisterDialog = false;
let showLoginDialog = false;

emit("register", showRegisterDialog);
emit("login", showLoginDialog);
</script>
<template>
	<w-toolbar>
		<RouterLink to="/flights">
			<w-button>Flights</w-button>
		</RouterLink>
		<RouterLink to="/catering">
			<w-button>Catering</w-button>
		</RouterLink>
		<RouterLink to="/poi">
			<w-button>About my destination</w-button>
		</RouterLink>
		<div class="spacer"></div>
		<!-- <span class="ml2">Item 1</span> -->
		<div class="ml2">
			<w-button class="px4" @click="showRegisterDialog = true">
				Register
			</w-button>
		</div>
		<div class="ml2">
			<w-button class="px4" @click="showLoginDialog = true">
				Login
			</w-button>
		</div>
	</w-toolbar>
</template>

<style>
.w-toolbar {
	background-color: var(--color-background-mute-transparent);
	min-height: 60px;
	max-height: 8vh;
	backdrop-filter: blur(10);
}
</style>
