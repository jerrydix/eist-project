<template>
  <br>
  <h3>{{ this.user.username }}</h3>
  <br>
  <h5>{{ this.user.money }}</h5>

  <LoginButtons/>
</template>


<script>
import {userStore} from "../userStore";
import {getUserData} from "../services/UserService";

import LoginButtons from "./LoginButtons.vue";

export default {
  components: {LoginButtons},
  data() {
    return {
      user: null
    };
  },
  setup() {
    const store = userStore();
    return {
      store,
    };
  },
  mounted() {
    getUserData().then((response) => {
      this.user = response
    });
  }
}
</script>

<style scoped>

</style>