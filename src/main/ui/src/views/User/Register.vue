<script>
import {register} from "../../services/UserService.js";

export default {
  data() {
    return {
      ans: null,
      user: {
        username: null,
        password: null,
        flight: null,
      },
    };
  },
  methods: {
    registerUser() {
      register(this.user.username, this.user.password, this.user.flight).then(
          (response) => {
            this.ans = response;
          }
      );
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
  <label>Username:</label>
  <w-input
      v-model="user.username"
      placeholder="Enter your username"
  ></w-input>
  <label>Password:</label>
  <w-input
      v-model="user.password"
      placeholder="Enter your password"
  ></w-input>
  <w-button @click="registerUser">Register</w-button>
  <br/>
  <p>{{ ans }}</p>
</template>

<style scoped></style>
