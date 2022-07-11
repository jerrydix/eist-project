<script>
export default {
	props: {
		flightNum: String,
	},
	emits: ["submitted"],
	data() {
		return {
			text: "",
			survey: {
				flightNumber: null,
				flightRating: 0,
				cateringRating: 0,
				entertainmentRating: 0,
				comfortRating: 0,
				serviceRating: 0,
				comment: null,
			},
		};
	},
	methods: {
		submitSurvey() {
			this.survey.flightNumber = this.flightNum;
			this.$emit("submitted", this.survey);
		},
	},
};
</script>

<template>
	<div>Your Flight:</div>
	<!-- <w-textarea :label="this.flightNum" readonly></w-textarea> -->
  <p>{{ this.flightNum }}</p>

	<w-divider></w-divider>
	<div>Flight Rating</div>
	<w-rating v-model="this.survey.flightRating"></w-rating>
	<br />
	<div>Catering Rating</div>
	<w-rating v-model="this.survey.cateringRating"></w-rating>
	<br />
	<div>Entertainment Rating</div>
	<w-rating v-model="this.survey.entertainmentRating"></w-rating>
	<br />
	<div>Comfort Rating</div>
	<w-rating v-model="this.survey.comfortRating"></w-rating>
	<br />
	<div>Service Rating</div>
	<w-rating v-model="this.survey.serviceRating"></w-rating>
	<br />
	<br />
	<div>Additional Feedback</div>
	<w-textarea
		v-model="this.survey.comment"
		outline
		placeholder="Your feedback"
		resizable
	></w-textarea>

	<w-divider></w-divider>

	<w-button @click="submitSurvey">Submit</w-button>
</template>

<style scoped></style>
