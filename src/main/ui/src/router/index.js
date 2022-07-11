import {createRouter, createWebHashHistory} from "vue-router";
import Catering from "../views/Catering.vue";
import Flights from "../views/Flights.vue";
import Journey from "../views/Journey.vue";
import Home from "../views/Home.vue";
import POI from "../views/POI.vue";
import Survey from "../views/Survey.vue";
import Register from "../views/User/Register.vue";
import Login from "../views/User/Login.vue";
import Movies from "../views/Movies.vue"
import UserDashboard from "../views/User/UserDashboard.vue";

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
            component: Movies
        }, {
            path: "/dashboard",
            name: "dashboard",
            component: UserDashboard
        },
    ],
});

export default router;
