<template>
  <w-grid class="card-wrapper" columns="4">
    <RouterLink to="/flights">
      <w-button class="nav-button">Flights</w-button>
    </RouterLink>
    <RouterLink to="/catering">
      <w-button>Catering</w-button>
    </RouterLink>
    <RouterLink to="/poi">
      <w-button>About my destination</w-button>
    </RouterLink>
    <RouterLink to="/survey">
      <w-button class="nav-button">Take Survey</w-button>
    </RouterLink>
    <RouterLink to="/register">
      <w-button class="nav-button">Register</w-button>
    </RouterLink>
    <RouterLink to="/login">
      <w-button class="nav-button">Login</w-button>
    </RouterLink>
  </w-grid>
</template>

<style scoped>

.card-wrapper {
  background-color: var(--color-background-mute-transparent);
  margin-top: 12px;
  box-shadow: 0px 0px 22px -3px rgba(0, 0, 0, 0.45);
  -webkit-box-shadow: 0px 0px 22px -3px rgba(0, 0, 0, 0.45);
  -moz-box-shadow: 0px 0px 22px -3px rgba(0, 0, 0, 0.45);
}

.card-wrapper > a > .w-button {
  margin: 12px;
  padding: 12px;
  height: 50px;
  width: 150px !important;
  background-color: var(--color-background-soft);
  color: var(--color-text);
  text-align: center;
}

</style>
