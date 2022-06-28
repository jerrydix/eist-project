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
  data: () => ({
    showDialog: false,
  }),
};
</script>

<template>
  <w-flex class="login-wrapper text-center">

    <w-flex v-if="!this.store.username" align-self-end class="xs1 pa0">
      <RouterLink to="/register">
        <w-button class="nav-button">Register</w-button>
      </RouterLink>
      <RouterLink to="/login">
        <w-button class="nav-button">Login</w-button>
      </RouterLink>
    </w-flex>

    <w-flex v-if="this.store.username" align-self-end class="xs1 pa1">
      <w-button class="nav-button" @click="logoutUser"> Logout</w-button>
    </w-flex>


  </w-flex>
</template>

<style scoped>
.login-wrapper {
  margin: 5px;
}
.login-wrapper a {
  margin: 5px;
}
</style>
