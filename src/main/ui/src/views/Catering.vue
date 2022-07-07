<script setup>
import CateringItem from '../components/CateringItem.vue';
const menuItems = [
	{text: "NicNacs", price: 5.50},
	{text: "Playboy Magazine", price: 6.90},
]



</script>

<template>
	<h1>catering</h1>

	<CateringItem v-for="menuItem in menuItems" :text="menuItem.text" :price="menuItem.price" />

</template>
