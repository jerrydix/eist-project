<script>
import SurveyBody from "../components/SurveyBody.vue";
import { submitSurvey } from "../services/SurveyService.js";
import { userStore } from "../userStore";

export default {
	components: {
		SurveyBody,
	},
	data() {
		return {
			ans: null,
		};
	},
	setup() {
		const store = userStore();
		return {
			store,
		};
	},
	methods: {
		submit(survey) {
			submitSurvey(this.store.username, survey).then((response) => {
				this.ans = response;
			});
		},
	},
};
</script>

<template>
	<SurveyBody @submitted="submit" />

	<br />
	<br />
	<p>{{ this.ans }}</p>
</template>
