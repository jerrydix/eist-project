<script setup>

</script>

<template>
<w-app>
    <w-flex grow basis-zero wrap>
        <w-flex class="grow column align-center justify-center">

            <div class="spacer"></div>

            <h1 style="text-align: center;">Welcome to Garching Airlines</h1>
            <h3 style="text-align: center;">Flights of Excellence</h3>

            <w-grid columns="4" class="card-wrapper">
                <w-button>Flights</w-button>
                <w-button>Catering</w-button>
                <w-button>About my destination</w-button>
                <w-button>About my trip</w-button>
            </w-grid>

            <div class="spacer"></div>

            <w-button class="survey-button align-self-end">
                We value your opinion
            </w-button>

        </w-flex>
    </w-flex>
</w-app>
</template>

<style>
@import "./assets/css/base.css";
@import "./assets/css/landing_page.css";

</style>