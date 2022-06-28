<script>

</script>
<template>
	<w-toolbar>
		<RouterLink to="/flights">
			<w-button>Flights</w-button>
		</RouterLink>
		<RouterLink to="/catering">
			<w-button>Catering</w-button>
		</RouterLink>
		<RouterLink to="/poi">
			<w-button>About my destination</w-button>
		</RouterLink>
		<div class="spacer"></div>
		<!-- <span class="ml2">Item 1</span> -->
		<LoginButtons />
	</w-toolbar>
</template>
