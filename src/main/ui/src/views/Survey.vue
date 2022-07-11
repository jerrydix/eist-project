<script>
import SurveyBody from "../components/SurveyBody.vue";
import {submitSurvey} from "../services/SurveyService.js";
import {userStore} from "../userStore";

export default {
  props: {
    flightNumber: String
  },
  setup() {
    const store = userStore();
    return {
      store,
    };
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
        if (!(this.ans === "Something went wrong")) {
          this.store.completedSurvey = true
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
