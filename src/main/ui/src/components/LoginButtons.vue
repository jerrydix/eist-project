<script>
import { userStore } from "../userStore.js";
import { getUserData } from "../services/UserService.js";
import Register from "../views/User/Register.vue";
import Login from "../views/User/Login.vue";
import CodeDialog from "../views/User/CodeDialog.vue";

export default {
  setup() {
    const store = userStore();
    return {
      store,
    };
  },
  mounted() {
    if (this.store.username != null) {
      getUserData().then((response) => {
        if (response.code == null) {
          this.showCodeDialog = false;
        }
      });
    } else {
      this.showCodeDialog = false;
    }
  },
  methods: {
    switchToRegister() {
      this.showLoginDialog = false;
      this.showRegisterDialog = true;
    },
    switchToCode() {
      this.showLoginDialog = false;
      this.showCodeDialog = true;
    },
  },
  components: {
    Register,
    Login,
    CodeDialog,
  },
  data: () => ({
    showLoginDialog: false,
    showRegisterDialog: false,
    showCodeDialog: true,
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
      @switchToCode="this.switchToCode"
      @switchToRegister="this.switchToRegister"
    />
  </w-dialog>

  <w-dialog
    v-model="showCodeDialog"
    :width="350"
    persistent
    title="Enter Confirmation Code"
    title-class="titles"
  >
    <CodeDialog />
  </w-dialog>
</template>

<style>
.titles {
  justify-content: center;
}
</style>
