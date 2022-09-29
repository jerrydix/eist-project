<script>
import FlightInfo from "../components/FlightInfo.vue";

import WelcomeMessage from "../components/WelcomeMessage.vue";

import TopBar from "../components/TopBar.vue";
import Survey from "./Survey.vue";
import { getLoggedInUser } from "../services/UserService.js";
import { userStore } from "../userStore";
import { getCurrentFlight } from "../services/FlightService";

export default {
  components: {
    Survey,
    FlightInfo,
    WelcomeMessage,
    TopBar,
  },
  data: () => ({
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
      this.showSurveyDialog = this.showSurveyDialog && this.store.username;
    });
    if (this.store.username != null) {
      getCurrentFlight().then((response) => {
        this.flight = response;
        this.store.endLocationId = this.flight["endLocation"]["locationId"];
        let a = this.store.username;
        this.store.username = null;
        this.$nextTick().then(() => {
          this.store.username = a;
        });
      });
    }
  },
};
</script>

<template>
  <w-app>
    <TopBar @safety="showSafetyVideo = true" />
    <w-flex basis-zero grow style="margin-top: 8vh" wrap>
      <w-flex class="grow column align-center justify-center">
        <div class="top-wrapper">
          <WelcomeMessage />
        </div>
        <div class="xs6">
          <div v-if="!this.store.username">
            <div style="padding: 30px">
              <w-image
                :height="125.6"
                :src="'https://i.ibb.co/MStfzmL/logo-full.png'"
                :width="253"
              ></w-image>
            </div>
            <h1>Welcome to</h1>
            <h1>Garching Airlines</h1>
            <h3><em>Flights of Excellence</em></h3>
          </div>
          <FlightInfo
            v-if="this.store.username"
            :flight="this.flight"
            class="flight-info-card"
          />

          <!-- <FlightMap v-if="this.store.username" :flight="this.flight" /> -->
        </div>
        <!-- <w-dialog
             v-model="showRegisterDialog"
             :width="550"
             title="Register"
         >
           <Register/>
         </w-dialog>
         <w-dialog v-model="showLoginDialog" :width="550" title="Login">
           <Login/>
         </w-dialog> 
 -->

        <w-dialog
          v-model="showSurveyDialog"
          :persistent="true"
          :persistent-no-animation="true"
          :width="550"
        >
          <template #title>
            Rate us!
            <w-button
              absolute
              icon="wi-cross"
              right
              @click="showSurveyDialog = false"
            ></w-button>
          </template>
          <Survey :flight-number="this.flight.number" />
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

        <w-flex justify-center style="margin-right: auto">
          <!--<div
              class="justify-self-start"
              style="margin-top: auto"
          >
            <w-button
                bg-color="error"
                class="bottom-button"
                @click="showSafetyVideo = true"
            >
              Watch safety video
            </w-button>
          </div>-->

          <div class="spacer"></div>

          <div class="justify-self-center" style="margin-top: auto">
            <w-button
              bg-color="info"
              class="bottom-button"
              @click="
                $waveui.notify(
                  'The flight crew has been notified. Please stay put.'
                )
              "
            >
              Call flight assistant
            </w-button>
          </div>
        </w-flex>
      </w-flex>
    </w-flex>
  </w-app>
</template>

<style scoped>
@import "../assets/css/home.css";

.w-app {
  background: url("../assets/img/above_clouds.jpg") center center fixed
    no-repeat !important;
  background-size: cover !important;
  text-align: center;
  height: 100%;
}

.w-toolbar {
  background: inherit;
  background-color: rgba(255, 255, 255, 0.4);
  background-blend-mode: lighten;
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

.top-wrapper .welcome-wrapper {
  float: left;
}

.flight-info-card {
  height: 310px;
  width: 100%;

  min-width: 430px;
  border-radius: 15px;
  background-color: var(--color-background-mute-transparent);
  box-shadow: 0px 0px 22px -3px rgba(0, 0, 0, 0.45);
  -webkit-box-shadow: 0px 0px 22px -3px rgba(0, 0, 0, 0.45);
  -moz-box-shadow: 0px 0px 22px -3px rgba(0, 0, 0, 0.45);
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
