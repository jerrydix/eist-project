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
  <LoginButtons />
</template>

<script>
import { userStore } from "../userStore";
import { getUserData } from "../services/UserService";

import LoginButtons from "./LoginButtons.vue";

export default {
  components: { LoginButtons },
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
};
</script>

<style scoped></style>
