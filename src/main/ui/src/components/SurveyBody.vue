<script>
export default {
  emits: ['submitted'],
  data() {
    return {
      text: "",
      survey: {

        user: {
          username: "lala",
          password: "alal"
        },
        flightNumber: null,
        flightRating: null,
        cateringRating: null,
        entertainmentRating: null,
        comfortRating: null,
        serviceRating: null,
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
  <w-textarea v-model="this.survey.flightRating" placeholder="flightRating"></w-textarea>
  <w-textarea v-model="this.survey.cateringRating" placeholder="cateringRating"></w-textarea>
  <w-textarea v-model="this.survey.entertainmentRating" placeholder="entertainmentRating"></w-textarea>
  <w-textarea v-model="this.survey.comfortRating" placeholder="comfortRating"></w-textarea>
  <w-textarea v-model="this.survey.serviceRating" placeholder="serviceRating"></w-textarea>
  <w-textarea v-model="this.survey.comment" placeholder="comment"></w-textarea>

</template>


<style scoped>

</style>