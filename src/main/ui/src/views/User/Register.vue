<script>
import {register} from "../../services/UserService.js";


export default {
  data() {
    return {
      ans: null,
      user: {
        username: null,
        password: null
      }
    }
  },
  methods: {
    registerUser() {
      register(this.user.username, this.user.password).then((response) => {
        this.ans = response

      });
    }
  }
}
</script>

<template>
  <label>Username:</label>
  <w-input v-model="user.username" placeholder="Enter your username"></w-input>
  <label>Password:</label>
  <w-input v-model="user.password" placeholder="Enter your password"></w-input>
  <w-button @click="registerUser">Register</w-button>
  <br>
  <p>{{ ans }}</p>
</template>


<style scoped>

</style>