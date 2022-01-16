package com.example.reactiveprogrammingtutorial.services;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class FluxAndMonoServicesTest {

    FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

    @Test
    void fruitsFlux() {
        var fruitsFlux = fluxAndMonoServices.fruitsFlux();

        StepVerifier.create(fruitsFlux)
                .expectNext("Banana","Watermelon","Melon")
                .verifyComplete();
    }

    @Test
    void fruitMono() {

        var fruitsMono = fluxAndMonoServices.fruitMono();

        StepVerifier.create(fruitsMono)
                .expectNext("Watermelon")
                .verifyComplete();
    }

    @Test
    void fruitsMap() {

        var fruitsMap = fluxAndMonoServices.fruitsFluxMap();

        StepVerifier.create(fruitsMap)
                .expectNext("BANANA","WATERMELON","MELON")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilter() {

        var fruitsMap = fluxAndMonoServices.fruitsFluxFilter(6);

        StepVerifier.create(fruitsMap)
                .expectNext("Watermelon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterMap() {

        var fruitsMap = fluxAndMonoServices.fruitsFluxFilterMap(6);

        StepVerifier.create(fruitsMap)
                .expectNext("WATERMELON")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMap() {

        var fruitsMap = fluxAndMonoServices.fruitsFluxFlatMap();

        StepVerifier.create(fruitsMap)
                .expectNextCount(21)
                .verifyComplete();
    }


    @Test
    void fruitsFluxFlatMapAsync() {

        var fruitsMap = fluxAndMonoServices.fruitsFluxFlatMapAsync();

        StepVerifier.create(fruitsMap)
                .expectNextCount(21)
                .verifyComplete();
    }

    @Test
    void fruitsFluxContactMap() {

        var fruitsMap = fluxAndMonoServices.fruitsFluxFlatConcatMap();

        StepVerifier.create(fruitsMap)
                .expectNextCount(21)
                .verifyComplete();
    }

    @Test
    void fruitMonoFlatMap() {

        var fruitsMap = fluxAndMonoServices.fruitMonoFlatMap();

        StepVerifier.create(fruitsMap)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void fruitMonoFlatMapAny() {
        var fruitsMap = fluxAndMonoServices.fruitMonoFlatMapMany();

        StepVerifier.create(fruitsMap)
                .expectNextCount(10)
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransform() {

        var fruitsMap = fluxAndMonoServices.fruitsFluxTransform(8);

        StepVerifier.create(fruitsMap)
                .expectNext("Watermelon")
                .verifyComplete();

    }

    @Test
    void fruitsFluxTransformDefaultIfEmpty() {

        var fruitsMap = fluxAndMonoServices.fruitsFluxTransformDefaultIfEmpty(11);

        StepVerifier.create(fruitsMap)
                .expectNext("default")
                .verifyComplete();
    }


    @Test
    void fruitsFluxTransformSwitchIfEmpty() {

        var fruitsMap = fluxAndMonoServices.fruitsFluxTransformSwitchIfEmpty(10);

        StepVerifier.create(fruitsMap)
                .expectNext("superstarfruit")
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcat() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxConcat();
        StepVerifier.create(fruitsFlux)
                .expectNext("Mango","Orange","Lettuce","Tomato")
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcaWith() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxConcatWith();
        StepVerifier.create(fruitsFlux)
                .expectNext("Mango","Orange","Lettuce","Tomato")
                .verifyComplete();
    }

    @Test
    void fruitsMonoConcatWith() {

        var fruitsFlux = fluxAndMonoServices.fruitsMonoConcatWith();
        StepVerifier.create(fruitsFlux)
                .expectNext("Mango","Lettuce")
                .verifyComplete();
    }
}