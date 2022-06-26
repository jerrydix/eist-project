<script>
import HomeNav from "../components/HomeNav.vue";
import HomeLoginButton from "../components/HomeLoginButton.vue"
import FlightInfo from "../components/FlightInfo.vue";
//import "../assets/css/home.css";
export default {
  components: {
    HomeNav,
    HomeLoginButton,
    FlightInfo
},
  setup() {
    const store = userStore()
    return {
      store
    }
  },
  methods: {
    logoutUser() {
      logout(this.store.username).then((response) => {
        if (!(response === "Broke the system") && !(response === "Error")) {
          window.localStorage.removeItem('user');
          this.store.username = null;
        }
        console.log(response);
      });
    }
  }
}

</script>

<template>
  <w-app>
    <w-flex basis-zero grow wrap>
      <w-flex class="grow column align-center justify-center">

        <div v-if="!this.store.username" class="align-self-end">
          <HomeLoginButton/>
        </div>
        <div v-if="this.store.username" class="align-self-end">
          <w-button class="nav-button" @click="logoutUser">Logout</w-button>
        </div>


        <div class="spacer"></div>

        <h1>Welcome to Garching Airlines</h1>
        <h3>Flights of Excellence</h3>

        <HomeNav/>

        <FlightInfo />

        <div class="spacer"></div>

        <div class="align-self-end">
          <RouterLink to="/survey">
            <w-button class="survey-button">We value your opinion</w-button>
          </RouterLink>
        </div>       

      </w-flex>
    </w-flex>
  </w-app>
</template>

<style scoped>
@import "../assets/css/home.css";

@media (prefers-color-scheme: light) {
  .w-app {
    background: url("../assets/img/above_clouds.jpg") center center fixed no-repeat !important;
  }
}

@media (prefers-color-scheme: dark) {
  .w-app {
    background: url("../assets/img/above_clouds_dark.jpg") center center fixed no-repeat !important;
  }
}

.w-app {
  background-size: cover !important;
  text-align: center;
}

</style>
