import {createApp} from "vue";
import {createPinia} from "pinia";
import App from "./App.vue";
import router from "./router"

import WaveUI from "wave-ui";
import "wave-ui/dist/wave-ui.css";
import { library } from '@fortawesome/fontawesome-svg-core'
import { 
    faCloud, faSun, faCloudSun, faCloudBolt, faCloudRain, faCloudSunRain, 
    faWind, faSnowflake, faRightLong, 
} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

/* import * as dotenv from 'dotenv' // see https://github.com/motdotla/dotenv#how-do-i-use-dotenv-with-import
dotenv.config()
 */

library.add(
    faCloud, faSun, faCloudSun, faCloudBolt, faCloudRain, faCloudSunRain, 
    faWind, faSnowflake, faRightLong,
    )

import "./assets/css/base.css";

const pinia = createPinia();
const app = createApp(App);
new WaveUI(app, {});
app.component('font-awesome-icon', FontAwesomeIcon)
app.use(router)
app.use(pinia)

app.mount("#app");