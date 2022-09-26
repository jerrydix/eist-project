<script>
import { getMovies } from "../services/MoviesService.js";
import TopBar from "../components/TopBar.vue";

export default {
  components: { TopBar },
  data() {
    return {
      showRickRoll: false,
      movies: null,
    };
  },
  methods: {
    setMovies() {
      getMovies().then((response) => {
        this.movies = response.movies;
      });
    },
  },
  beforeMount() {
    this.setMovies();
  },
};
</script>

<template>
  <w-app id="app">
    <TopBar />
    <h2 class="text-center" style="margin-top: 8vh">Movies</h2>

    <main class="grow">
      <w-overlay v-model="showRickRoll">
        <template v-if="$waveui.breakpoint.xs || $waveui.breakpoint.sm">
          <iframe
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            allowfullscreen
            frameborder="0"
            height="315"
            src="https://www.youtube.com/embed/dQw4w9WgXcQ"
            title="YouTube video player"
            width="560"
          ></iframe>
        </template>
        <template v-else-if="$waveui.breakpoint.md">
          <iframe
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            allowfullscreen
            frameborder="0"
            height="378"
            src="https://www.youtube.com/embed/dQw4w9WgXcQ"
            title="YouTube video player"
            width="672"
          ></iframe>
        </template>
        <template v-if="$waveui.breakpoint.lg">
          <iframe
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            allowfullscreen
            frameborder="0"
            height="600"
            src="https://www.youtube.com/embed/dQw4w9WgXcQ"
            title="YouTube video player"
            width="800"
          ></iframe>
        </template>
        <template v-else-if="$waveui.breakpoint.xl">
          <iframe
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            allowfullscreen
            frameborder="0"
            height="720"
            src="https://www.youtube.com/embed/dQw4w9WgXcQ"
            title="YouTube video player"
            width="1280"
          ></iframe>
        </template>
      </w-overlay>

      <w-flex class="text-center" wrap>
        <div class="xs12 md6 lg4 xl2 pa1">
          <div class="primary-light3--bg py3" @click="showRickRoll = true">
            <template v-if="$waveui.breakpoint.xs || $waveui.breakpoint.sm">{{
              this.movies[0].title
            }}</template>
            <template v-else-if="$waveui.breakpoint.md"
              ><img :src="this.movies[0].poster" height="200" /><br />{{
                this.movies[0].title
              }}
            </template>
            <template v-else-if="$waveui.breakpoint.lg"
              ><img :src="this.movies[0].poster" height="350" /><br />{{
                this.movies[0].title
              }}
            </template>
            <template v-else-if="$waveui.breakpoint.xl"
              ><img :src="this.movies[0].poster" height="450" /><br />{{
                this.movies[0].title
              }}
            </template>
          </div>
        </div>

        <div class="xs12 md6 lg4 xl2 pa1">
          <div class="primary-light3--bg py3" @click="showRickRoll = true">
            <template v-if="$waveui.breakpoint.xs || $waveui.breakpoint.sm">{{
              this.movies[1].title
            }}</template>
            <template v-else-if="$waveui.breakpoint.md"
              ><img :src="this.movies[1].poster" height="200" /><br />{{
                this.movies[1].title
              }}
            </template>
            <template v-else-if="$waveui.breakpoint.lg"
              ><img :src="this.movies[1].poster" height="350" /><br />{{
                this.movies[1].title
              }}
            </template>
            <template v-else-if="$waveui.breakpoint.xl"
              ><img :src="this.movies[1].poster" height="450" /><br />{{
                this.movies[1].title
              }}
            </template>
          </div>
        </div>

        <div class="xs12 md6 lg4 xl2 pa1">
          <div class="primary-light3--bg py3" @click="showRickRoll = true">
            <template v-if="$waveui.breakpoint.xs || $waveui.breakpoint.sm">{{
              this.movies[2].title
            }}</template>
            <template v-else-if="$waveui.breakpoint.md"
              ><img :src="this.movies[2].poster" height="200" /><br />{{
                this.movies[2].title
              }}
            </template>
            <template v-else-if="$waveui.breakpoint.lg"
              ><img :src="this.movies[2].poster" height="350" /><br />{{
                this.movies[2].title
              }}
            </template>
            <template v-else-if="$waveui.breakpoint.xl"
              ><img :src="this.movies[2].poster" height="450" /><br />{{
                this.movies[2].title
              }}
            </template>
          </div>
        </div>

        <div class="xs12 md6 lg4 xl2 pa1">
          <div class="primary-light3--bg py3" @click="showRickRoll = true">
            <template v-if="$waveui.breakpoint.xs || $waveui.breakpoint.sm">{{
              this.movies[3].title
            }}</template>
            <template v-else-if="$waveui.breakpoint.md"
              ><img :src="this.movies[3].poster" height="200" /><br />{{
                this.movies[3].title
              }}
            </template>
            <template v-else-if="$waveui.breakpoint.lg"
              ><img :src="this.movies[3].poster" height="350" /><br />{{
                this.movies[3].title
              }}
            </template>
            <template v-else-if="$waveui.breakpoint.xl"
              ><img :src="this.movies[3].poster" height="450" /><br />{{
                this.movies[3].title
              }}
            </template>
          </div>
        </div>

        <div class="xs12 md6 lg4 xl2 pa1">
          <div class="primary-light3--bg py3" @click="showRickRoll = true">
            <template v-if="$waveui.breakpoint.xs || $waveui.breakpoint.sm">{{
              this.movies[4].title
            }}</template>
            <template v-else-if="$waveui.breakpoint.md"
              ><img :src="this.movies[4].poster" height="200" /><br />{{
                this.movies[4].title
              }}
            </template>
            <template v-else-if="$waveui.breakpoint.lg"
              ><img :src="this.movies[4].poster" height="350" /><br />{{
                this.movies[4].title
              }}
            </template>
            <template v-else-if="$waveui.breakpoint.xl"
              ><img :src="this.movies[4].poster" height="450" /><br />{{
                this.movies[4].title
              }}
            </template>
          </div>
        </div>

        <div class="xs12 md6 lg4 xl2 pa1">
          <div class="primary-light3--bg py3" @click="showRickRoll = true">
            <template v-if="$waveui.breakpoint.xs || $waveui.breakpoint.sm">{{
              this.movies[5].title
            }}</template>
            <template v-else-if="$waveui.breakpoint.md"
              ><img :src="this.movies[5].poster" height="200" /><br />{{
                this.movies[5].title
              }}
            </template>
            <template v-else-if="$waveui.breakpoint.lg"
              ><img :src="this.movies[5].poster" height="350" /><br />{{
                this.movies[5].title
              }}
            </template>
            <template v-else-if="$waveui.breakpoint.xl"
              ><img :src="this.movies[5].poster" height="450" /><br />{{
                this.movies[5].title
              }}
            </template>
          </div>
        </div>
      </w-flex>
    </main>
  </w-app>
</template>

<style scoped>
.w-toolbar {
  background: inherit;
  background-color: rgba(255, 255, 255, 0.4);
  background-blend-mode: lighten;
  min-height: 60px;
  max-height: 8vh;
  backdrop-filter: blur(10);
}

.w-app {
  background: url("../assets/img/batman.jpg") center center fixed no-repeat !important;
  background-size: cover !important;
  text-align: center;
  height: 100%;
}
</style>
