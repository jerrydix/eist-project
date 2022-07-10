<script>
import SurveyBody from "../components/SurveyBody.vue";
import {submitSurvey} from "../services/SurveyService.js";
import {getLatestReward} from "../services/UserService.js";

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
    };
  },
  methods: {
    submit(survey) {
      function getReward() {
        return getLatestReward()
      }

      submitSurvey(survey).then(() => {
        this.ans = "Thanks for your submission! Your reward is " + getReward();
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
