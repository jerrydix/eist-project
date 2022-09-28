<template>
  <div style="width: 50px; margin-right: 20px">
    <a @click="is_open = true">
      <mdicon name="account-circle" size="50" />
    </a>
  </div>
  <w-drawer v-model="is_open" fit-content overlay-color="transparent">
    <w-card>
      <template #title>
        <div style="width: 200px; height: 24px">
          <w-button
            fixed
            icon="wi-cross"
            outline
            right
            round
            @click="is_open = false"
          >
          </w-button>
        </div>
      </template>
      <div style="width: 200px">
        <UserBox />
      </div>
    </w-card>
  </w-drawer>
</template>

<script>
import UserBox from "./UserBox.vue";

export default {
  name: "UserButton",
  components: { UserBox },
  data() {
    return {
      is_open: false,
    };
  },
  methods: {
    openDrawer() {
      this.isopen = true;
    },
  },
};
</script>

<style scoped></style>
