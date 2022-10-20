<script>
import { login } from "../../services/UserService.js";
import { userStore } from "../../userStore.js";

export default {
  emits: ["switchToRegister", "loggedIn"],
  data() {
    return {
      ans: null,
      user: {
        username: null,
        password: null,
      },
    };
  },
  setup() {
    const store = userStore();
    return {
      store,
    };
  },
  methods: {
    loginUser() {
      login(this.user.username, this.user.password).then((response) => {
        if (
          response === "User already logged in" ||
          response === "User doesn't exist, please register" ||
          response === "Wrong password"
        ) {
          this.ans = response;
        } else {
          window.localStorage.setItem("user", response);
          this.store.username = response;
          this.$emit("loggedIn");
          this.ans = "Successfully logged in";
        }
        this.$waveui.notify(this.ans);
      });
    },
    toRegister() {
      this.$emit("switchToRegister");
    },
  },
};
</script>

<template>
  <!-- <label>Username:</label>
  <w-textarea v-model="user.username" placeholder="Enter your username"></w-textarea>
  <label>Password:</label>
  <w-textarea v-model="user.password" placeholder="Enter your password"></w-textarea>
  <w-button @click="loginUser">Login</w-button> -->

  <label>Username:</label>
  <w-input
    v-model="user.username"
    class="mb4"
    placeholder="Enter your username"
  ></w-input>
  <label>Password:</label>
  <w-input
    v-model="user.password"
    class="mb4"
    placeholder="Enter your password"
    type="password"
  ></w-input>
  <w-button @click="loginUser">Login</w-button>
  <br />

  <small
    >Not a user yet?<a href="javascript:;" @click="this.toRegister"
      >Register here!</a
    ></small
  >
</template>

<style scoped></style>
