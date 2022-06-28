<script>
import {userStore} from "../userStore.js";
import {logout} from "../services/UserService.js";

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
};
</script>

<template>
	<w-flex align-self-start class="xs11 pa10 welcome-wrapper">
		<h2 v-if="this.store.username">
			<em>Welcome back, {{ this.store.username }}</em>
		</h2>
	</w-flex>
</template>
