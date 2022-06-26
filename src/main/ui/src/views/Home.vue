<script>
import HomeNav from "../components/HomeNav.vue";
import HomeLoginButton from "../components/HomeLoginButton.vue";
import FlightInfo from "../components/FlightInfo.vue";

import Login from "./User/Login.vue";
//import "../assets/css/home.css";
export default {
	components: {
		HomeNav,
		HomeLoginButton,
		FlightInfo,
		Login,
	},

	data: () => ({
		showLoginDialog: false,
	}),
};
</script>

<template>
	<w-app>
		<w-flex basis-zero grow wrap>
			<w-flex class="grow column align-center justify-center">
				
        <HomeLoginButton />

				<div class="spacer"></div>

				<h1>Welcome to Garching Airlines</h1>
				<h3>Flights of Excellence</h3>

				<HomeNav />

				<FlightInfo />

				<w-button class="px4" @click="showLoginDialog = true">
					Open dialog
				</w-button>

				<w-dialog v-model="showLoginDialog" title="Login">
					<Login />
				</w-dialog>

				<div class="spacer"></div>

				<div class="align-self-end">
					<RouterLink to="/survey">
						<w-button class="survey-button">
							We value your opinion
						</w-button>
					</RouterLink>
				</div>
			</w-flex>
		</w-flex>
	</w-app>
</template>

<style scoped>
@import "../assets/css/home.css";

@media (prefers-color-scheme: light) {
	.w-app {
		background: url("../assets/img/above_clouds.jpg") center center fixed
			no-repeat !important;
	}
}

@media (prefers-color-scheme: dark) {
	.w-app {
		background: url("../assets/img/above_clouds_dark.jpg") center center
			fixed no-repeat !important;
	}
}

.w-app {
	background-size: cover !important;
	text-align: center;
}
</style>
