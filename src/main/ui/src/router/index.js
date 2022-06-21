import { createRouter, createWebHistory } from "vue-router";
import Catering from "../views/Catering.vue";
import Flights from "../views/Flights.vue";
import Journey from "../views/Journey.vue";
import Home from "../views/Home.vue";
import POI from "../views/POI.vue";
import Survey from "../views/Survey.vue";


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: Home,
    },
    {
      path: "/catering",
      name: "catering",
      component: Catering,
    },
    {
      path: "/flights",
      name: "flights",
      component: Flights,
    },
    {
      path: "/journey",
      name: "journey",
      component: Journey,
    },
    {
      path: "/poi",
      name: "poi",
      component: POI,
    },
    {
      path: "/survey",
      name: "survey",
      component: Survey,
    },
  ],
});

export default router;
