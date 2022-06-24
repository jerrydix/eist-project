<script setup>
import {RouterLink} from "vue-router";
import "../assets/css/home.css";</script>

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

        <w-button class="survey-button align-self-end">
          We value your opinion
        </w-button>

      </w-flex>
    </w-flex>
  </w-app>
</template>

<style scoped>

</style>