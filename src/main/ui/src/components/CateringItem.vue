<script setup>
const props = defineProps({
	text: String,
	price: Number,
});
</script>

<template>
	<w-card>
		<w-flex>
			<w-flex class="xs6">
				{{ text || "If you read this text, something went wrong" }}
			</w-flex>
			<w-flex class="xs6">
				<w-flex class="xs6">
					<p>{{ price.toFixed(2) + " €" || "It's free!" }}</p>
				</w-flex>
				<w-flex class="xs6">
					<w-button @click="showDialog = true"> Order </w-button>
					<w-button
						@click="$waveui.notify('Order placed: ' + text)"
						bg-color="info"
					>
						Order
					</w-button>
				</w-flex>
			</w-flex>
		</w-flex>
	</w-card>
</template>

<style scoped>
.w-card {
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
