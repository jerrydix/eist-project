<template>
  <h3>{{ this.user.username }}</h3>
  <hr />
  <RouterLink to="/wallet">
    <w-button lg text>Wallet: </w-button>
  </RouterLink>
  <h5>{{ this.user.money.toFixed(2) }}</h5>
  <hr />
  <RouterLink to="/dashboard">
    <w-button lg text>Profile</w-button>
  </RouterLink>
  <hr />
  <RouterLink to="/settings">
    <w-button lg text>Settings</w-button>
  </RouterLink>
  <hr />
  <LogoutButton />
</template>

<script>
import { userStore } from "../userStore";
import { getUserData } from "../services/UserService";
import LogoutButton from "@/components/LogoutButton.vue";

export default {
  components: { LogoutButton },
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
