<script>
import HomeNav from "../components/HomeNav.vue";
import HomeLoginButton from "../components/HomeLoginButton.vue";
import FlightInfo from "../components/FlightInfo.vue";
import Login from "./User/Login.vue";
import WelcomeMessage from "../components/WelcomeMessage.vue";
import Register from "./User/Register.vue";
import TopBar from "../components/TopBar.vue";
import Survey from "./Survey.vue";

export default {
  components: {
    Survey,
    HomeNav,
    HomeLoginButton,
    FlightInfo,
    Login,
    WelcomeMessage,
    Register,
	TopBar
  },
  data: () => ({
    showLoginDialog: false,
    showRegisterDialog: false,
    showSurveyDialog: false
  }),
};
</script>

<template>
<<<<<<< HEAD
  <w-app>
    <w-toolbar>
      <RouterLink to="/flights">
        <w-button>Flights</w-button>
      </RouterLink>
      <RouterLink to="/catering">
        <w-button>Catering</w-button>
      </RouterLink>
      <RouterLink to="/poi">
        <w-button>About my destination</w-button>
      </RouterLink>
      <div class="spacer"></div>
      <!-- <span class="ml2">Item 1</span> -->
      <div class="ml2">
        <w-button class="px4" @click="showRegisterDialog = true">
          Register
        </w-button>
      </div>
      <div class="ml2">
        <w-button class="px4" @click="showLoginDialog = true">
          Login
        </w-button>
      </div>
    </w-toolbar>
    <w-flex basis-zero grow wrap>
      <w-flex class="grow column align-center justify-center">
        <div class="top-wrapper">
          <WelcomeMessage/>
        </div>
=======
	<w-app>
		<!-- <w-toolbar>
			<RouterLink to="/flights">
				<w-button>Flights</w-button>
			</RouterLink>
			<RouterLink to="/catering">
				<w-button>Catering</w-button>
			</RouterLink>
			<RouterLink to="/poi">
				<w-button>About my destination</w-button>
			</RouterLink>

			<div class="spacer"></div>

			<div class="ml2">
				<w-button class="px4" @click="showRegisterDialog = true">
					Register
				</w-button>
			</div>
			<div class="ml2">
				<w-button class="px4" @click="showLoginDialog = true">
					Login
				</w-button>
			</div>
		</w-toolbar> -->
		<TopBar />
		<w-flex basis-zero grow wrap>
			<w-flex class="grow column align-center justify-center">
				<div class="top-wrapper">
					<WelcomeMessage />
				</div>
>>>>>>> 914ed0b (made own component for loginbuttons)


        

<<<<<<< HEAD
        <div class="xs4">
          <h1>Welcome to Garching Airlines</h1>
          <h3><em>Flights of Excellence</em></h3>
         
          <FlightInfo/>
        </div>

=======
				<!-- <w-dialog
					v-model="showRegisterDialog"
					title="Register"
					:width="550"
				>
					<Register />
				</w-dialog>

				<w-dialog v-model="showLoginDialog" title="Login" :width="550">
					<Login />
				</w-dialog> -->
>>>>>>> 914ed0b (made own component for loginbuttons)

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

        <w-dialog v-model="showSurveyDialog" :width="550" title="Survey">
          <Survey/>
        </w-dialog>

        <div class="spacer"></div>

        <div class="align-self-end">

          <w-button class="survey-button" @click="showSurveyDialog = true">
            We value your opinion
          </w-button>

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

/* .top-wrapper .login-wrapper {
	float: right;
} */

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
</style>
