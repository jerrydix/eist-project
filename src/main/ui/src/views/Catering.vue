<script setup>
import CateringItem from '../components/CateringItem.vue';
const menuItems = [
	{text: "NicNacs", price: 5.50},
	{text: "Champagne 0.2L", price: 7.50},
	{text: "Playboy Magazine Kids Edition", price: 6.90},
	{text: "Nutella Jar plus Spoon", price: 2.49},
	{text: "Leftovers from your neighbour", price: 0.99},
	{text: "Water 0.5L", price: 2.35}
]



</script>

<template>
	<h1>Menu</h1>

	<CateringItem v-for="menuItem in menuItems" :text="menuItem.text" :price="menuItem.price" />

</template>

<style>

</style>