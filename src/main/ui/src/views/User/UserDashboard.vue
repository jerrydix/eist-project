<template>

</template>
  
<script>
import {userStore} from "../../userStore";

export default {
  setup() {
    const store = userStore();
    return {
      store,
    };
  }
}
</script>

<style scoped>

</style>