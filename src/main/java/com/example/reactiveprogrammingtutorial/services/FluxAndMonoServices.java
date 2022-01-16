package com.example.reactiveprogrammingtutorial.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoServices {

    public Flux<String> fruitsFlux(){
        return Flux.fromIterable(List.of("Banana","Watermelon","Melon")).log();
    }

    public Flux<String> fruitsFluxMap(){
        return Flux.fromIterable(List.of("Banana","Watermelon","Melon"))
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fruitsFluxFilter(int minLetters){
        return Flux.fromIterable(List.of("Banana","Watermelon","Melon"))
                .filter(s-> s.length() > minLetters)
                .log();
    }

    public Flux<String> fruitsFluxFilterMap(int minLetters){
        return Flux.fromIterable(List.of("Banana","Watermelon","Melon"))
                .filter(s-> s.length() > minLetters)
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fruitsFluxFlatMap(){
        return Flux.fromIterable(List.of("Banana","Watermelon","Melon"))
                .flatMap(s -> Flux.just(s.split("")))
                .log();
    }

    public Flux<String> fruitsFluxFlatMapAsync(){
        return Flux.fromIterable(List.of("Banana","Watermelon","Melon"))
                .flatMap(s -> Flux.just(s.split(""))
                .delayElements(Duration.ofMillis(
                        new Random().nextInt(1000)
                )))
                .log();
    }

    public Flux<String> fruitsFluxFlatConcatMap(){
        return Flux.fromIterable(List.of("Banana","Watermelon","Melon"))
                .concatMap(s -> Flux.just(s.split(""))
                        .delayElements(Duration.ofMillis(
                                new Random().nextInt(1000)
                        )))
                .log();
    }

    public Flux<String> fruitsFluxTransform(int number){

        Function<Flux<String>,Flux<String>> filterData
                = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Banana","Watermelon","Melon"))
                .transform(filterData)
                .log();
    }

    public Flux<String> fruitsFluxTransformDefaultIfEmpty(int number){

        Function<Flux<String>,Flux<String>> filterData
                = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Banana","Watermelon","Melon"))
                .transform(filterData)
                .defaultIfEmpty("default")
                .log();
    }

    public Flux<String> fruitsFluxTransformSwitchIfEmpty(int number){

        Function<Flux<String>,Flux<String>> filterData
                = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Banana","Watermelon","Melon"))
                .transform(filterData)
                .switchIfEmpty(Flux.just("pear","superstarfruit","strawberry"))
                    .transform(filterData)
                .log();
    }

    public Flux<String> fruitsFluxConcat(){
        var fruits = Flux.just("Mango","Orange");
        var veggies = Flux.just("Lettuce","Tomatoe");

        return Flux.concat(fruits,veggies);
    }

    public Flux<String> fruitsFluxConcatWith(){
        var fruits = Flux.just("Mango","Orange");
        var veggies = Flux.just("Lettuce","Tomato");

        return fruits.concatWith(veggies);
    }


    // MONO


    public Mono<String> fruitMono(){
        return Mono.just("Watermelon").log();
    }

    public Mono<List<String>> fruitMonoFlatMap(){
        return Mono.just("Watermelon")
                .flatMap(s -> Mono.just(List.of(s.split(""))))
                .log();
    }

    public Flux<String> fruitMonoFlatMapMany(){
        return Mono.just("Watermelon")
                .flatMapMany(s -> Flux.just(s.split("")))
                .log();
    }


    public static void main(String[] args) {
        FluxAndMonoServices fluxAndMonoService = new FluxAndMonoServices();

        fluxAndMonoService.fruitsFlux()
                .subscribe(System.out::println);

        fluxAndMonoService.fruitMono()
                .subscribe(System.out::println);
    }

    public Flux<String> fruitsMonoConcatWith(){
        var fruits = Mono.just("Mango");
        var veggies = Mono.just("Lettuce");

        return fruits.concatWith(veggies);
    }


}
