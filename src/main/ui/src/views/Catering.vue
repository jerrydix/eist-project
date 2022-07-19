<script setup>
import CateringItem from "../components/CateringItem.vue";

const menuItems = [
	{ text: "NicNacs", price: 5.5 },
	{ text: "Champagne 0.2L", price: 7.5 },
	{ text: "Playboy Magazine Kids Edition", price: 6.9 },
	{ text: "Nutella Jar plus Spoon", price: 2.49 },
	{ text: "Leftovers from your neighbour", price: 0.99 },
	{ text: "Water 0.5L", price: 2.35 },
	{ text: "Vodka Gaydar", price: 69.42 },
	{ text: "Borschtsch", price: 0.9 },
	{ text: "Augustiner", price: 3.0 },
	{ text: "Kräuterspekulatius", price: 5.6 },
	{ text: "Fountain Pew", price: 2.8 },
	{ text: "Caco Calo", price: 4.2 },
	{ text: "Gösser Naturradler", price: 3.5 },
	{ text: "Turbobier", price: 2.5 },
	{ text: "Pferdeleberkäs", price: 4.5 },
	{ text: "Loukoumas", price: 2.3 },
	{ text: "Baklava", price: 1.5 },
	{ text: "Souvlaki", price: 5.5 },
];

let testNote = () => {
    setTimeout(function () {
		this.$waveui.notify("5 seconds have passed");
	}, 5000);
};

/* testNote(); */
</script>

<template>
  <w-app>
    <h2 class="text-center">Food and Drinks</h2>
    <CateringItem v-for="menuItem in menuItems" :price="menuItem.price" :text="menuItem.text"/>
  </w-app>
</template>

<style scoped>
.w-app {
	background: url("../assets/img/kirsten-drew-n49BjsFf5dI-unsplash.jpg")
		center center fixed no-repeat !important;
	background-size: cover !important;
	text-align: center;
	height: 100%;
}
</style>
