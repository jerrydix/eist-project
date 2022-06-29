<script>
import { userStore } from "../userStore.js";
import { logout } from "../services/UserService.js";
import Register from "../views/User/Register.vue";
import Login from "../views/User/Login.vue";

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
	components: {
		Register,
		Login,
	},
	data: () => ({
		showLoginDialog: false,
		showRegisterDialog: false,
	}),
};
</script>

<template>
	<div v-if="!this.store.username" class="ml2">
		<w-button class="px4" @click="showRegisterDialog = true">
			Register
		</w-button>
	</div>
	<div v-if="!this.store.username" class="ml2">
		<w-button class="px4" @click="showLoginDialog = true"> Login </w-button>
	</div>
	<w-dialog v-model="showRegisterDialog" title="Register" :width="550">
		<Register />
	</w-dialog>
	<w-dialog v-model="showLoginDialog" title="Login" :width="550">
		<Login />
	</w-dialog>

	<div v-if="this.store.username" align-self-end class="xs1 pa1">
		<w-button class="nav-button" @click="logoutUser"> Logout</w-button>
	</div>
</template>
