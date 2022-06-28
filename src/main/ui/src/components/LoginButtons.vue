<!-- <script setup>
/* export default {
	data: () => ({
		showLoginDialog: false,
		showRegisterDialog: false,
	}),
}; */

let showRegisterDialog = false;
let showLoginDialog = false;

const emit = defineEmits(['register', 'login'])

emit('register', showRegisterDialog)
emit('login', showLoginDialog)

</script>
<template>
	
</template>
 -->