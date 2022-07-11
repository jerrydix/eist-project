<template>
  <p>{{ this.user.username }}</p>
  <p>{{ this.user.favouritePOIs }}</p>
  <p>{{ this.user.rewards }}</p>
  <p>{{ this.user.bookedFlightJourneys }}</p>
  <FlightInfo :flight="this.user.currentFlight"/>
</template>

<script>
import {userStore} from "../../userStore.js";
import {getUserData} from "../../services/UserService.js";
import FlightInfo from "../../components/FlightInfo.vue";

export default {
  components: {FlightInfo},
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
};
</script>


<style scoped></style>
