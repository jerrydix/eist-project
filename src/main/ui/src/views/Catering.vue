<script setup>
import CateringItem from '../components/CateringItem.vue';
const menuItems = [
	{text: "NicNacs", price: 5.50},
	{text: "Champagne 0.2L", price: 7.50},
	{text: "Playboy Magazine Kids Edition", price: 6.90},
  {text: "Vodka Gorbatschow", price: 49.69},
  {text: "Borschtsch", price: 0.90},
  {text: "Augustiner", price: 3.00},
  {text: "Kräuterspekulatius", price: 5.60},
  {text: "Fountain Pew", price: 2.80},
  {text: "Caco Calo", price: 4.20},
]



</script>

<template>
	<h1>Menu</h1>

	<CateringItem v-for="menuItem in menuItems" :text="menuItem.text" :price="menuItem.price" />

</template>

<style>

</style>