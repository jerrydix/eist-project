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
      logout().then((response) => {
        if (!(response === "Broke the system") && !(response === "Error")) {
          window.localStorage.removeItem("user");
          this.store.username = null;
        }
        console.log(response);
      });
    },
    switch() {
      this.showLoginDialog = false;
      this.showRegisterDialog = true;
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
  <div v-if="!this.store.username" style="margin-right: 8px">
    <w-button @click="showLoginDialog = true"> Login</w-button>
  </div>
  <w-dialog
    v-model="showRegisterDialog"
    :width="550"
    title="Register"
    title-class="titles"
  >
    <Register />
  </w-dialog>
  <w-dialog
    v-model="showLoginDialog"
    :width="550"
    title="Login"
    title-class="titles"
  >
    <Login @switchToRegister="this.switch" />
  </w-dialog>

  <RouterLink v-if="this.store.username" to="/">
    <w-button lg text @click="logoutUser"> Logout </w-button>
  </RouterLink>
</template>

<style>
.titles {
  justify-content: center;
}
</style>
