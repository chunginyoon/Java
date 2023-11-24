import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";
import piniaPluginPersistedstate from "pinia-plugin-persistedstate"; //새로고침시 데이터 유지를 위한 설정

// Vuetify
import "@mdi/font/css/materialdesignicons.css";
import "@fortawesome/fontawesome-free/css/all.css"; // Ensure your project is capable of
// import "font-awesome/css/font-awesome.min.css"; // Ensure your project is capable of handling
//import "material-design-icons-iconfont/dist/material-design-icons.css"; // Ensure your project is capable of handling css files
import "vuetify/styles";
import { createVuetify } from "vuetify";
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";
import { aliases, fa } from "vuetify/iconsets/fa";
import { mdi } from "vuetify/iconsets/mdi";
// import { VuetifyDateAdapter } from "vuetify/date/adapters/vuetify";

export default createVuetify({
  icons: {
    defaultSet: "fa",
    aliases,
    sets: {
      fa,
      mdi,
    },
  },
  // date: {
  //   adapter: VuetifyDateAdapter,
  // },
});
const vuetify = createVuetify({
  components,
  directives,
});

const app = createApp(App);

const pinia = createPinia();
pinia.use(piniaPluginPersistedstate); //새로고침시 데이터 유지를 위한 설정

app.use(pinia);
app.use(router);
app.use(vuetify);

app.mount("#app");
