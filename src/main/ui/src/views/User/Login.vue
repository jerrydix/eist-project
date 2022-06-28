<script>
import {login} from "../../services/UserService.js";
import {userStore} from '../../userStore.js'

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
  setup() {
    const store = userStore()
    return {
      store
    }
  },
  methods: {
    loginUser() {
      login(this.user.username, this.user.password).then((response) => {
        if (
            response === "Wrong username or password" ||
            response === "Broke the system"
        ) {
          this.ans = response;
        } else {
          console.log(response);
          window.localStorage.setItem('user', response);
          this.store.username = response;
          this.ans = "Successfully logged in"
        }
      });
    }
  }
}
</script>

<template>
  <!-- <label>Username:</label>
  <w-textarea v-model="user.username" placeholder="Enter your username"></w-textarea>
  <label>Password:</label>
  <w-textarea v-model="user.password" placeholder="Enter your password"></w-textarea>
  <w-button @click="loginUser">Login</w-button> -->

  <label>Username:</label>
  <w-input v-model="user.username" class="mb4" placeholder="Enter your username"></w-input>
  <label>Password:</label>
  <w-input v-model="user.password" class="mb4" placeholder="Enter your password"></w-input>
  <w-button @click="loginUser">Login</w-button>

  <p>{{ ans }}</p>


</template>


<style scoped>

</style>