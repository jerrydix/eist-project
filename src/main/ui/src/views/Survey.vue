<script>
import SurveyBody from "../components/SurveyBody.vue";
import {submitSurvey} from "../services/SurveyService.js";

export default {
  props: {
    flightNumber: String
  },
  components: {
    SurveyBody,
  },
  data() {
    return {
      ans: null,
      reward: null
    };
  },
  methods: {
    submit(survey) {
      submitSurvey(survey).then((response) => {
        this.ans = response
        if (!this.ans === "Something went wrong") {
          this.$emit("haveSubmitted");
        }
      });
    },
  },
};
</script>

<template>
  <SurveyBody :flight-num="flightNumber" @submitted="submit"/>

  <br/>
  <br/>
  <p>{{ this.ans }}</p>
</template>
