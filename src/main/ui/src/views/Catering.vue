<script>
import TopBar from "@/components/TopBar.vue";
import CateringItem from "@/components/CateringItem.vue";

export default {
  components: { TopBar, CateringItem },
  data() {
    return {
      menuItems: [
        { text: "NicNacs", price: 5.5 },
        { text: "Champagne 0.2L", price: 7.5 },
        { text: "Nutella Jar plus Spoon", price: 2.49 },
        { text: "Water 0.5L", price: 2.35 },
        { text: "Vodka Gaydar", price: 62.42 },
        { text: "Borschtsch", price: 0.9 },
        { text: "Augustiner", price: 3.0 },
        { text: "Kräuterspekulatius", price: 5.6 },
        { text: "Fountain Pew", price: 2.8 },
        { text: "Caco Calo", price: 2.2 },
        { text: "Gösser Naturradler", price: 3.5 },
        { text: "Turbobier", price: 2.5 },
        { text: "Pferdeleberkäs", price: 4.5 },
        { text: "Loukoumas", price: 4.3 },
        { text: "Baklava", price: 1.5 },
        { text: "Souvlaki", price: 6.5 },
      ],
    };
  },
};
</script>

<template>
  <w-app>
    <TopBar />

    <h1 class="text-center" style="margin-top: 8vh; margin-bottom: 10px">
      Food and Drinks
    </h1>
    <CateringItem
      v-for="menuItem in menuItems"
      :price="menuItem.price"
      :text="menuItem.text"
    />
  </w-app>
</template>

<style scoped>
.w-app {
  background: url("../assets/img/kirsten-drew-n49BjsFf5dI-unsplash.jpg") center
    center fixed no-repeat !important;
  background-size: cover !important;
  text-align: center;
  height: 100%;
}

.w-toolbar {
  background: inherit;
  background-color: rgba(255, 255, 255, 0.4);
  background-blend-mode: lighten;
  min-height: 60px;
  max-height: 8vh;
  backdrop-filter: blur(10);
}
</style>
