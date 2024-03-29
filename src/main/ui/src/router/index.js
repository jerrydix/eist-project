import { createRouter, createWebHashHistory } from "vue-router";
import Catering from "../views/Catering.vue";
import Flights from "../views/Flights.vue";
import Home from "../views/Home.vue";
import POI from "../views/POI.vue";
import Survey from "../views/Survey.vue";
import Register from "../views/User/Register.vue";
import Login from "../views/User/Login.vue";
import Movies from "../views/Movies.vue";
import UserDashboard from "../views/User/UserDashboard.vue";
import Journey from "../components/Journey.vue";
import Settings from "../views/Settings.vue";
import Wallet from "../views/Wallet.vue";

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
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
      path: "/poi",
      name: "poi",
      component: POI,
    },
    {
      path: "/dashboard/journey",
      name: "journey",
      component: Journey,
    },
    {
      path: "/survey",
      name: "survey",
      component: Survey,
    },
    {
      path: "/register",
      name: "register",
      component: Register,
    },
    {
      path: "/login",
      name: "login",
      component: Login,
    },
    {
      path: "/movies",
      name: "movies",
      component: Movies,
    },
    {
      path: "/dashboard",
      name: "dashboard",
      component: UserDashboard,
    },
    {
      path: "/settings",
      name: "settings",
      component: Settings,
    },
    {
      path: "/wallet",
      name: "wallet",
      component: Wallet,
    },
  ],
});

export default router;
