<script>
import {userStore} from "../userStore.js";

export default {
  setup() {
    const store = userStore();
    return {
      store,
    };
  },
};
</script>

<template>
  <w-flex align-self-start class="xs11 pl10 pa4 welcome-wrapper">
    <h2 v-if="this.store.username">
      <em>Welcome back, {{ this.store.username }}</em>
    </h2>
  </w-flex>
</template>
