<script>
import { getMovies } from "../services/MoviesService.js";

export default {
  data() {
    return {
      movies: null
    }
  },
  methods: {
    generateMovieHTML() {
      getMovies().then(response => {
        response.movies.forEach(movie => {
          let outer_div = document.createElement('div');
          outer_div.classList.add("xs12", "md6", "lg4", "xl2", "pa1");
          let inner_div = document.createElement('div');
          inner_div.classList.add("primary-light4--bg", "py3");
          const image = document.createElement('img');
          const text = document.createElement('h2');

          text.innerHTML = `${movie.title}`;
          image.src = movie.poster;
          outer_div.appendChild(inner_div);
          inner_div.appendChild(image);
          inner_div.appendChild(text);
          movs.appendChild(outer_div);
        });
      });
    }
  },
  beforeMount() {
    this.generateMovieHTML()
  }
}
</script>

<template>
<w-flex wrap class="text-center" id="movs"></w-flex>
</template>