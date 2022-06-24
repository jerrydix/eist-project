<script>
import {register} from "../../services/UserService.js";

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
    registerUser() {
      register(this.user.username, this.user.password).then((response) => {
        this.ans = response
        console.log(response)
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
  <w-button @click="registerUser">Register</w-button>

  <p>{{ ans }}</p>
</template>


<style scoped>

</style>