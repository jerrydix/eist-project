<script>
import {userStore} from "../userStore";
import LoginButtons from "./LoginButtons.vue";
import UserButton from "./UserButton.vue";

export default {
  emits: ["safety"],
  setup() {
    const store = userStore();
    return {
      store,
    };
  },
  methods: {
    showSafety() {
      this.$emit("safety");
    },
  },
  components: {UserButton, LoginButtons},
};
</script>
<template>
  <w-toolbar>
    <RouterLink to="/">
      <div style="padding-top: 10%;">
        <w-image :height="50.28"
                 :src="'https://i.ibb.co/wdGCP9P/logo-small.png'"
                 :width="45.68"
                 @error="console.log('image loading failed')"></w-image>
      </div>
    </RouterLink>
    <RouterLink v-if="this.store.username" to="/flights">
      <w-button class="ma1" color="primary" lg text>Book flights</w-button>
    </RouterLink>
    <RouterLink v-if="this.store.username" to="/dashboard">
      <w-button class="ma1" color="primary" lg text>Dashboard</w-button>
    </RouterLink>
    <RouterLink v-if="this.store.username" :to="{name:'poi', params:{locationID:this.store.endLocationId}}">
      <w-button class="ma1" color="primary" lg text>About my destination</w-button>
    </RouterLink>
    <RouterLink to="/catering">
      <w-button class="ma1" color="primary" lg text>Catering</w-button>
    </RouterLink>
    <RouterLink to="/movies">
      <w-button class="ma1" color="primary" lg text>Movies</w-button>
    </RouterLink>
    <div v-if="!this.store.username">
      <w-button class="ma1" color="primary" lg text @click="showSafety">Safety Video</w-button>
    </div>
    <div class="spacer"></div>


    <UserButton v-if="this.store.username"/>

    <LoginButtons v-if="!this.store.username"/>

  </w-toolbar>
</template>

<style>


.w-toolbar a {
  padding-left: 0.8rem;
  padding-right: 0.8rem;
}
</style>
