import {createApp} from "vue";
import {createPinia} from "pinia";
import App from "./App.vue";
import router from "./router"

import WaveUI from "wave-ui";
import "wave-ui/dist/wave-ui.css";
import { library } from '@fortawesome/fontawesome-svg-core'
import { 
    faCloud, faSun, faCloudSun, faCloudBolt, faCloudRain, faCloudSunRain, faWind, faSnowflake 
} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

library.add(
    faCloud, faSun, faCloudSun, faCloudBolt, faCloudRain, faCloudSunRain, faWind, faSnowflake
    )

import "./assets/css/base.css";

const pinia = createPinia();
const app = createApp(App);
new WaveUI(app, {});
app.component('font-awesome-icon', FontAwesomeIcon)
app.use(router).use(pinia).mount("#app");