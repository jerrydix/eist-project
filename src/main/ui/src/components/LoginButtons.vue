<script>
export default {
	data: () => ({
		showLoginDialog: false,
		showRegisterDialog: false,
	}),
};
</script>
<template>
	<div class="ml2">
		<w-button class="px4" @click="showRegisterDialog = true">
			Register
		</w-button>
	</div>
	<div class="ml2">
		<w-button class="px4" @click="showLoginDialog = true"> Login </w-button>
	</div>
</template>
