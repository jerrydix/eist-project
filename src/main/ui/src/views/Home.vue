<script setup>
import { RouterLink } from "vue-router";
//import "../assets/css/home.css";
</script>

<template>
  <w-app>
    <w-flex basis-zero grow wrap>
      <w-flex class="grow column align-center justify-center">

        <div class="spacer"></div>

        <h1>Welcome to Garching Airlines</h1>
        <h3>Flights of Excellence</h3>

        <w-grid class="card-wrapper" columns="4">
          <RouterLink to="/flights">
            <w-button class="nav-button">Flights</w-button>
          </RouterLink>
          <w-button>Catering</w-button>
          <w-button>About my destination</w-button>
          <w-button>About my trip</w-button>
          <RouterLink to="/survey">
            <w-button class="nav-button">Take Survey</w-button>
          </RouterLink>
          <RouterLink to="/register">
            <w-button class="nav-button">Register</w-button>
          </RouterLink>
          <RouterLink to="/login">
            <w-button class="nav-button">Login</w-button>
          </RouterLink>
        </w-grid>

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