<script>
import { getMovies } from "../services/MoviesService.js";

export default {
  data() {
    return {
      showRickRoll: false,
      movies: null
    }
  },
  methods: {
    setMovies() {
      getMovies().then(response => {
        this.movies = response.movies
        });
      }
    },
  beforeMount() {
    this.setMovies()
  }
}
</script>

<template>
  <w-overlay v-model="showRickRoll">
    <template v-if="$waveui.breakpoint.xs || $waveui.breakpoint.sm">
      <iframe width="560" height="315" src="https://www.youtube.com/embed/dQw4w9WgXcQ" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
    </template>
    <template v-else-if="$waveui.breakpoint.md">
      <iframe width="672" height="378" src="https://www.youtube.com/embed/dQw4w9WgXcQ" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
    </template>
    <template v-if="$waveui.breakpoint.lg">
      <iframe width="800" height="600" src="https://www.youtube.com/embed/dQw4w9WgXcQ" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
    </template>
    <template v-else-if="$waveui.breakpoint.xl">
      <iframe width="1280" height="720" src="https://www.youtube.com/embed/dQw4w9WgXcQ" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
    </template>
  </w-overlay>

  <w-flex wrap class="text-center">
    <div class="xs12 md6 lg4 xl2 pa1">
      <div @click="showRickRoll = true" class="primary-light3--bg py3">
        <template v-if="$waveui.breakpoint.xs || $waveui.breakpoint.sm">{{ this.movies[0].title }}</template>
        <template v-else-if="$waveui.breakpoint.md"><img :src=this.movies[0].poster height="200"><br>{{ this.movies[0].title }}</template>
        <template v-else-if="$waveui.breakpoint.lg"><img :src=this.movies[0].poster height="350"><br>{{ this.movies[0].title }}</template>
        <template v-else-if="$waveui.breakpoint.xl"><img :src=this.movies[0].poster height="450"><br>{{ this.movies[0].title }}</template>
      </div>
    </div>

    <div class="xs12 md6 lg4 xl2 pa1">
      <div @click="showRickRoll = true" class="primary-light3--bg py3">
        <template v-if="$waveui.breakpoint.xs || $waveui.breakpoint.sm">{{ this.movies[1].title }}</template>
        <template v-else-if="$waveui.breakpoint.md"><img :src=this.movies[1].poster height="200"><br>{{ this.movies[1].title }}</template>
        <template v-else-if="$waveui.breakpoint.lg"><img :src=this.movies[1].poster height="350"><br>{{ this.movies[1].title }}</template>
        <template v-else-if="$waveui.breakpoint.xl"><img :src=this.movies[1].poster height="450"><br>{{ this.movies[1].title }}</template>
      </div>
    </div>

    <div class="xs12 md6 lg4 xl2 pa1">
      <div @click="showRickRoll = true" class="primary-light3--bg py3">
        <template v-if="$waveui.breakpoint.xs || $waveui.breakpoint.sm">{{ this.movies[2].title }}</template>
        <template v-else-if="$waveui.breakpoint.md"><img :src=this.movies[2].poster height="200"><br>{{ this.movies[2].title }}</template>
        <template v-else-if="$waveui.breakpoint.lg"><img :src=this.movies[2].poster height="350"><br>{{ this.movies[2].title }}</template>
        <template v-else-if="$waveui.breakpoint.xl"><img :src=this.movies[2].poster height="450"><br>{{ this.movies[2].title }}</template>
      </div>
    </div>

    <div class="xs12 md6 lg4 xl2 pa1">
      <div @click="showRickRoll = true" class="primary-light3--bg py3">
        <template v-if="$waveui.breakpoint.xs || $waveui.breakpoint.sm">{{ this.movies[3].title }}</template>
        <template v-else-if="$waveui.breakpoint.md"><img :src=this.movies[3].poster height="200"><br>{{ this.movies[3].title }}</template>
        <template v-else-if="$waveui.breakpoint.lg"><img :src=this.movies[3].poster height="350"><br>{{ this.movies[3].title }}</template>
        <template v-else-if="$waveui.breakpoint.xl"><img :src=this.movies[3].poster height="450"><br>{{ this.movies[3].title }}</template>
      </div>
    </div>

    <div class="xs12 md6 lg4 xl2 pa1">
      <div @click="showRickRoll = true" class="primary-light3--bg py3">
        <template v-if="$waveui.breakpoint.xs || $waveui.breakpoint.sm">{{ this.movies[4].title }}</template>
        <template v-else-if="$waveui.breakpoint.md"><img :src=this.movies[4].poster height="200"><br>{{ this.movies[4].title }}</template>
        <template v-else-if="$waveui.breakpoint.lg"><img :src=this.movies[4].poster height="350"><br>{{ this.movies[4].title }}</template>
        <template v-else-if="$waveui.breakpoint.xl"><img :src=this.movies[4].poster height="450"><br>{{ this.movies[4].title }}</template>
      </div>
    </div>

    <div class="xs12 md6 lg4 xl2 pa1">
      <div @click="showRickRoll = true" class="primary-light3--bg py3">
        <template v-if="$waveui.breakpoint.xs || $waveui.breakpoint.sm">{{ this.movies[5].title }}</template>
        <template v-else-if="$waveui.breakpoint.md"><img :src=this.movies[5].poster height="200"><br>{{ this.movies[5].title }}</template>
        <template v-else-if="$waveui.breakpoint.lg"><img :src=this.movies[5].poster height="350"><br>{{ this.movies[5].title }}</template>
        <template v-else-if="$waveui.breakpoint.xl"><img :src=this.movies[5].poster height="450"><br>{{ this.movies[5].title }}</template>
      </div>
    </div>
  </w-flex>
</template>
