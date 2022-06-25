<script>

import SurveyBody from "../components/SurveyBody.vue";
import {submitSurvey} from "../services/SurveyService.js";

export default {
  components: {
    SurveyBody
  },
  data() {
    return {
      ans: "a"
    }
  },
  methods: {
    submit(survey) {
      submitSurvey(survey).then(response => {
        this.ans = response
      });
    }
  }
}

</script>

<template>


  <SurveyBody @submitted="submit"/>

  <br>
  <p>{{ this.ans }}</p>
</template>