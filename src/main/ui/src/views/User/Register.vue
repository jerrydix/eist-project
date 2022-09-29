<script>
import { register } from "../../services/UserService.js";

export default {
  data() {
    return {
      showSpinner: false,
      ans: null,
      user: {
        username: "",
        password: "",
        email: "",
        flight: "",
      },
    };
  },
  methods: {
    registerUser() {
      this.ans = null;
      this.showSpinner = true;
      register(
        this.user.username,
        this.user.password,
        this.user.email,
        this.user.flight
      ).then((response) => {
        this.ans = response;
        this.showSpinner = false;
        this.$waveui.notify(this.ans);
      });
    },
  },
};
</script>

<template>
  <label>Current Flight:</label>
  <w-input
    v-model="user.flight"
    placeholder="Enter the number of your current flight"
  ></w-input>
  <label>Email Address:</label>
  <w-input
    v-model="user.email"
    placeholder="Enter your email address"
  ></w-input>
  <label>Username:</label>
  <w-input v-model="user.username" placeholder="Enter your username"></w-input>
  <label>Password:</label>
  <w-input
    v-model="user.password"
    placeholder="Enter your password"
    type="password"
  ></w-input>
  <w-button @click="registerUser">Register</w-button>
  <br />
  <w-spinner :model-value="showSpinner" class="ma1" fade xs />
</template>

<style scoped></style>
