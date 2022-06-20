<script setup>
import { ref, computed } from 'vue'
import LandingPageVue from './LandingPage.vue';
import CateringVue from './components/Catering.vue';
import FlightsVue from './components/Flights.vue';
import JourneyVue from './components/Journey.vue';
import POIVue from './components/POI.vue';
import SurveyVue from './components/Survey.vue';

const routes = {
  '/': LandingPageVue,
  '/catering': CateringVue,
  '/flights': FlightsVue,
  '/journey': JourneyVue,
  '/poi': POIVue,
  '/survey': SurveyVue
}

const currentPath = ref(window.location.hash)

window.addEventListener('hashchange', () => {
  currentPath.value = window.location.hash
})

const currentView = computed(() => {
  return routes[currentPath.value.slice(1) || '/'] || NotFound
})

</script>

<template>

    <component :is="currentView" />

</template>