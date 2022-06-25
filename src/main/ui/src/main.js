import {createApp} from "vue";
import {createPinia} from "pinia";
import App from "./App.vue";
import router from "./router"

import WaveUI from "wave-ui";
import "wave-ui/dist/wave-ui.css";

import "./assets/css/base.css";

const pinia = createPinia();
const app = createApp(App);
new WaveUI(app, {});
app.use(router).use(pinia).mount("#app");