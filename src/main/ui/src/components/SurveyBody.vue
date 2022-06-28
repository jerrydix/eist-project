<script>
export default {
  emits: ['submitted'],
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
    }
  },
  methods: {
    submitSurvey() {
      this.$emit("submitted", this.survey)
    }
  }
}
</script>

<template>
  <w-button @click="submitSurvey">Submit</w-button>
  <w-textarea v-model="this.survey.flightNumber" placeholder="flightNumber"></w-textarea>

  <w-divider></w-divider>
  <div>Flight Rating</div>
  <w-rating v-model="this.survey.flightRating" color="green"></w-rating>
  <br>
  <div>Catering Rating</div>
  <w-rating v-model="this.survey.cateringRating"></w-rating>
  <br>
  <div>Entertainment Rating</div>
  <w-rating v-model="this.survey.entertainmentRating"></w-rating>
  <br>
  <div>Comfort Rating</div>
  <w-rating v-model="this.survey.comfortRating"></w-rating>
  <br>
  <div>Service Rating</div>
  <w-rating v-model="this.survey.serviceRating"></w-rating>
  <br>
  <br>
  <div>Additional Feedback</div>
  <w-textarea v-model="this.survey.comment" placeholder="Your feedback"></w-textarea>

</template>


<style scoped>

</style>