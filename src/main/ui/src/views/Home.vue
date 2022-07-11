<script>
import HomeNav from "../components/HomeNav.vue";
import HomeLoginButton from "../components/HomeLoginButton.vue";
import FlightInfo from "../components/FlightInfo.vue";
import Login from "./User/Login.vue";
import WelcomeMessage from "../components/WelcomeMessage.vue";
import Register from "./User/Register.vue";
import TopBar from "../components/TopBar.vue";
import Survey from "./Survey.vue";
import {getLoggedInUser} from "../services/UserService.js";
import {userStore} from "../userStore";
import {getCurrentFlight} from "../services/FlightService";

export default {
  components: {
    Survey,
    HomeNav,
    HomeLoginButton,
    FlightInfo,
    Login,
    WelcomeMessage,
    Register,
    TopBar,
  },
  data: () => ({
    takenSurvey: false,
    showLoginDialog: false,
    showRegisterDialog: false,
    showSurveyDialog: false,
    showSafetyVideo: false,
    flight: null,
  }),
  setup() {
    const store = userStore();
    return {
      store,
    };
  },
  mounted() {
    getLoggedInUser().then((response) => {
      if (response === "null") {
        this.store.username = null;
        window.localStorage.removeItem("user");
      } else {
        this.store.username = response;
        window.localStorage.setItem("user", response);
      }
    });
    if (this.store.username != null) {
      getCurrentFlight().then((response) => {
        this.flight = response;
        console.log(response);
      });
    }
  },
};
</script>

<template>
  <w-app>
    <TopBar/>
    <w-flex basis-zero grow wrap>
      <w-flex class="grow column align-center justify-center">
        <div class="top-wrapper">
          <WelcomeMessage/>
        </div>
        <div class="xs4">
          <h1>Welcome to Garching Airlines</h1>
          <h3><em>Flights of Excellence</em></h3>
          <FlightInfo
              v-if="this.store.username"
              :flight="this.flight"
          />

          <!-- <FlightMap v-if="this.store.username" :flight="this.flight" /> -->
        </div>
        <w-dialog
            v-model="showRegisterDialog"
            :width="550"
            title="Register"
        >
          <Register/>
        </w-dialog>
        <w-dialog v-model="showLoginDialog" :width="550" title="Login">
          <Login/>
        </w-dialog>

        <w-dialog
            v-model="showSurveyDialog"
            :width="550"
            title="Survey"
        >
          <Survey :flight-number="this.flight.number" @haveSubmitted="this.takenSurvey = true"/>
        </w-dialog>

        <w-dialog v-model="showSafetyVideo" :width="980">
          <iframe
              allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
              allowfullscreen
              frameborder="0"
              height="534"
              src="https://www.youtube-nocookie.com/embed/YCoQwZ9BQ9Q?start=40"
              title="YouTube video player"
              width="950"
          ></iframe>
        </w-dialog>

        <div class="spacer"></div>

        <w-flex class="justify-end" style="margin-right: auto;">
          <w-flex class="xs4">

            <div class="justify-self-start" style="margin-top: auto;">
              <w-button
                  bg-color="error"
                  class="bottom-button"
                  @click="showSafetyVideo = true"
              >
                Watch safety video
              </w-button>
            </div>

            <div class="spacer"></div>

            <div class="justify-self-end" style="margin-top: auto;">
              <w-button
                  v-if="this.store.username && !this.takenSurvey"
                  class="bottom-button"
                  @click="showSurveyDialog = true"
              >
                We value your opinion
              </w-button>
            </div>

          </w-flex>

          <w-flex class="xs8"></w-flex>
        </w-flex>

      </w-flex>
    </w-flex>
  </w-app>
</template>

<style scoped>
@import "../assets/css/home.css";

.w-app {
  background: url("../assets/img/above_clouds.jpg") center center fixed no-repeat !important;
  background-size: cover !important;
  text-align: center;
  height: 100%;
}

.w-toolbar {
  background-color: var(--color-background-mute-transparent);
  min-height: 60px;
  max-height: 8vh;
  backdrop-filter: blur(10);
}

.w-toolbar > * {
  z-index: 2;
}

.top-wrapper {
  width: 100%;
}

.w-toolbar {
  background-color: var(--color-background-mute-transparent);
  min-height: 60px;
  max-height: 8vh;
  backdrop-filter: blur(10);
}

.top-wrapper .welcome-wrapper {
  float: left;
}

h1 {
  line-height: 3rem;
  padding-bottom: 1.5rem;
}

.bottom-button {
  margin: 24px;
  padding: 24px;
}
</style>
