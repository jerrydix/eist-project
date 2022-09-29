<template>
  <h3>{{ this.user.username }}</h3>
  <hr />
  <RouterLink to="/wallet">
    <w-button lg text>Wallet: </w-button>
  </RouterLink>
  <h5>{{ this.user.money }}</h5>
  <hr />
  <RouterLink to="/dashboard">
    <w-button lg text>Profile</w-button>
  </RouterLink>
  <hr />
  <RouterLink to="/settings">
    <w-button lg text>Settings</w-button>
  </RouterLink>
  <hr />
  <RouterLink v-if="this.store.username" to="/">
    <w-button lg text @click="logoutUser"> Logout </w-button>
  </RouterLink>
</template>

<script>
import { userStore } from "../userStore";
import { getUserData, logout } from "../services/UserService";

export default {
  data() {
    return {
      user: null,
    };
  },
  setup() {
    const store = userStore();
    return {
      store,
    };
  },
  mounted() {
    getUserData().then((response) => {
      this.user = response;
    });
  },
  methods: {
    logoutUser() {
      logout().then((response) => {
        if (!(response === "Broke the system") && !(response === "Error")) {
          window.localStorage.removeItem("user");
          this.store.username = null;
        }
      });
    },
  },
};
</script>

<style scoped></style>
