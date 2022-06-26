<script>
import {userStore} from "../userStore.js";
import {logout} from "../services/UserService.js";
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
  <w-flex class="text-center">

    <w-flex align-self-start class="xs11 pa10">
      <h1 v-if="this.store.username">Welcome back, {{ this.store.username }}</h1>
    </w-flex>


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
