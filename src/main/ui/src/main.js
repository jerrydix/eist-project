import {createApp} from "vue";
import {createPinia} from "pinia";
import App from "./App.vue";
import router from "./router";
import WaveUI from "wave-ui";
import "wave-ui/dist/wave-ui.css";
import {library} from "@fortawesome/fontawesome-svg-core";
import mdiVue from 'mdi-vue/v3'
import * as mdijs from '@mdi/js'
import {
	faCloud,
	faCloudBolt,
	faCloudRain,
	faCloudShowersHeavy,
	faCloudSun,
	faRightLong,
	faSmog,
	faSnowflake,
	faSun,
	faTornado,
	faWind
} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import "./assets/css/base.css";

/* import * as dotenv from 'dotenv' // see https://github.com/motdotla/dotenv#how-do-i-use-dotenv-with-import
dotenv.config()
 */

library.add(
    faCloud,
    faCloudShowersHeavy,
    faCloudBolt,
    faCloudRain,
    faSnowflake,
    faSmog,
    faCloudSun,
    faWind,
    faTornado,
    faSun,
    faRightLong
);

const pinia = createPinia();
const app = createApp(App);
new WaveUI(app, {
    notificationManager: {
        align: 'left',
        transition: 'default'
    }
});
app.component("font-awesome-icon", FontAwesomeIcon);
app.use(router);
app.use(pinia);
app.use(mdiVue, {icons: mdijs});
app.mount("#app");
