<template>
  <w-input
    v-model="code"
    class="mb4"
    maxlength="6"
    placeholder="Enter code"
  ></w-input>

  <w-button text @click="confirmEmail">Confirm</w-button>
  <hr />
  <LogoutButton />
</template>

<script>
import { confirmEmail } from "@/services/UserService";
import { userStore } from "@/userStore";
import LogoutButton from "@/components/LogoutButton.vue";

export default {
  name: "CodeDialog",
  components: { LogoutButton },
  data() {
    return {
      ans: null,
      code: null,
    };
  },
  setup() {
    const store = userStore();
    return {
      store,
    };
  },
  methods: {
    confirmEmail() {
      confirmEmail(this.code).then((response) => {
        if (response === "Wrong code, please try again") {
          this.ans = response;
        } else {
          window.localStorage.setItem("user", response);
          this.store.username = response;
          history.go(0);
          this.ans = "Successfully logged in";
        }
        this.$waveui.notify(this.ans);
      });
    },
  },
};
</script>

<style scoped></style>
