<script>
import { userStore } from "../userStore.js";
import { logout } from "../services/UserService.js";
/* import Login from "./User/Login.vue";
 */ //import "../assets/css/home.css";
export default {
	setup() {
		const store = userStore();
		return {
			store,
		};
	},
	methods: {
		logoutUser() {
			logout(this.store.username).then((response) => {
				if (
					!(response === "Broke the system") &&
					!(response === "Error")
				) {
					window.localStorage.removeItem("user");
					this.store.username = null;
				}
				console.log(response);
			});
		},
	},
	data: () => ({
		showDialog: false,
	}),
};
</script>

<template>
	<div v-if="!this.store.username" class="align-self-end">
		<RouterLink to="/register">
			<w-button class="nav-button">Register</w-button>
		</RouterLink>
		<RouterLink to="/login">
			<w-button class="nav-button">Login</w-button>
		</RouterLink>
	</div>
	<div v-if="this.store.username" class="align-self-end">
		<w-button class="nav-button" @click="logoutUser"> Logout </w-button>
	</div>
</template>
