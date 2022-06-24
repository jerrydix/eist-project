<script>
import {login, logout} from "../../services/UserService.js";

export default {
  data() {
    return {
      ans: "null",
      user: {
        username: null,
        password: null
      }
    }
  },
  methods: {
    loginUser() {
      login(this.user.username, this.user.password).then((response) => {
        this.ans = response
      });
    }, logoutUser() {
      logout(this.user.username).then((response) => {
        this.ans = response
      });
    }
  }
}
</script>

<template>
  <label>Username:</label>
  <w-textarea v-model="user.username" placeholder="Enter your username"></w-textarea>
  <label>Password:</label>
  <w-textarea v-model="user.password" placeholder="Enter your password"></w-textarea>
  <w-button @click="loginUser">Login</w-button>

  <p>{{ ans }}</p>

  <w-button @click="logoutUser">Logout</w-button>
</template>


<style scoped>

</style>