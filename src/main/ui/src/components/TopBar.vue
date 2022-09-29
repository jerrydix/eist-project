<script>
import { userStore } from "../userStore";
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
  components: { UserButton, LoginButtons },
};
</script>
<template>
  <w-toolbar fixed>
    <w-flex align-center justify-space-between>
      <RouterLink to="/">
        <div class="mt2">
          <w-image
            :height="50.28"
            :src="'https://i.ibb.co/wdGCP9P/logo-small.png'"
            :width="45.68"
            @error="console.log('image loading failed')"
          ></w-image>
        </div>
      </RouterLink>

      <RouterLink v-if="this.store.username" to="/flights">
        <w-button color="primary" lg text>Book flights</w-button>
      </RouterLink>
      <RouterLink v-if="this.store.username" to="/dashboard">
        <w-button color="primary" lg text>Dashboard</w-button>
      </RouterLink>
      <RouterLink
        v-if="this.store.username"
        :to="{ name: 'poi', params: { locationID: this.store.endLocationId } }"
      >
        <w-button color="primary" lg text>About my destination</w-button>
      </RouterLink>
      <RouterLink to="/catering">
        <w-button color="primary" lg text>Catering</w-button>
      </RouterLink>
      <RouterLink to="/movies">
        <w-button color="primary" lg text>Movies</w-button>
      </RouterLink>
      <div v-if="!this.store.username">
        <w-button color="primary" lg text @click="showSafety"
          >Safety Video</w-button
        >
      </div>
      <UserButton v-if="this.store.username" />

      <LoginButtons />
    </w-flex>
  </w-toolbar>
</template>

<style>
.w-toolbar a {
  padding-left: 0.8rem;
  padding-right: 0.8rem;
}
</style>
