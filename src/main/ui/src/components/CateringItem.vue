<script>
import { userStore } from "@/userStore";
import { spendMoney } from "@/services/UserService";

export default {
  props: { text: String, price: Number },
  emits: ["bought"],
  setup() {
    const store = userStore();
    return {
      store,
    };
  },
  methods: {
    buy() {
      this.$waveui.notify("Order placed: " + this.text);
      spendMoney(this.price);
    },
  },
};
</script>

<template>
  <w-card style="padding: 8px; margin: 5px">
    <w-flex>
      <div class="xs6" style="text-align: left; font-weight: bold">
        {{ text || "If you read this text, something went wrong" }}
      </div>

      <w-flex class="xs6" justify-space-between>
        <div style="font-weight: bold">
          {{ price.toFixed(2) + " €" || "It's free!" }}
        </div>
        <div>
          <w-button
            :disabled="!this.store.username"
            bg-color="black"
            color="white"
            @click="this.buy"
          >
            Order
          </w-button>
        </div>
      </w-flex>
    </w-flex>
  </w-card>
</template>

<style scoped>
.w-card {
  background: rgba(255, 255, 255, 0.9);
  backdrop-filter: blur(10);
  width: 100%;
  max-width: 40vw;
  min-width: 400px;
  align-self: center;
  margin-bottom: 10px;
}

.w-button {
  float: right;
}
</style>
