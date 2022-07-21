<script>
export default {
	props: ["flight"],
	emits: ["select"],
	methods: {
		select() {
			this.$emit("select", this.flight);
		},
	},
};
</script>

<template>
	<w-card :title="this.flight.number + ' | ' + this.flight.airline">
		<w-flex>
			<w-flex class="xs8">
				<w-timeline
					:items="[
						{
							title: this.flight.departureTime,
							content: this.flight.startName,
						},
						{
							title: this.flight.arrivalTime,
							content: this.flight.endName,
						},
					]"
					color="primary"
				>
					<template #item="{ item }">
						<w-tag class="white blue--bg">
							{{ item.title }}
						</w-tag>
						<span class="mx2"> {{ item.content }} </span>
					</template>
				</w-timeline>
			</w-flex>
			<w-flex class="xs4">
				<w-button bg-color="success" @click="select">
					Add to journey
				</w-button>
			</w-flex>
		</w-flex>
	</w-card>
</template>

<style scoped>
.w-card {
	padding: 10px;
	margin: 15px;
	/* text-align: right; */
}

.w-button {
	display: inline-block;
}

.w-card__title {
	font-size: 1em;
	padding: 0;
}
</style>
