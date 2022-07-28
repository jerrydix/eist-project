<script>
import {userStore} from "../userStore";
import LoginButtons from "./LoginButtons.vue";

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
    }
  },
  components: {LoginButtons},
};
</script>
<template>
  <w-toolbar>
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

    <LoginButtons/>
  </w-toolbar>
</template>

<style>
.w-toolbar {
  background-color: var(--color-background-mute-transparent);
  min-height: 60px;
  max-height: 8vh;
  backdrop-filter: blur(10);
}

.w-toolbar a {
  padding-left: 0.5rem;
  padding-right: 0.5rem;
}
</style>
