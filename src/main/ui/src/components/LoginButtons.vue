<script>
import { userStore } from "../userStore.js";
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
    switchToRegister() {
      this.showLoginDialog = false;
      this.showRegisterDialog = true;
    },
    loggedIn() {
      this.showLoginDialog = false;
      history.go(0);
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
    <Login
      @loggedIn="this.loggedIn"
      @switchToRegister="this.switchToRegister"
    />
  </w-dialog>
</template>

<style>
.titles {
  justify-content: center;
}
</style>
