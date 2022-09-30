<template>
  <RouterLink to="/">
    <w-button lg text @click="logoutUser"> Logout </w-button>
  </RouterLink>
</template>

<script>
import { logout } from "@/services/UserService";
import { userStore } from "@/userStore";

export default {
  name: "LogoutButton",
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
  methods: {
    logoutUser() {
      logout().then((response) => {
        if (!(response === "Broke the system") && !(response === "Error")) {
          window.localStorage.removeItem("user");
          this.store.username = null;
          history.go(0);
        }
      });
    },
  },
};
</script>

<style scoped></style>
