package intelligent_taxi.gatewayservice.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(FallbackUrl.BASE)
public class FallbackController {

    @GetMapping(FallbackUrl.USER)
    public Mono<String> fallbackUserGet() {
        return Mono.just(FallbackMessage.USER_LOG);
    }

    @PostMapping(FallbackUrl.USER)
    public Mono<String> fallbackUserPost() {
        return Mono.just(FallbackMessage.USER_LOG);
    }

    @PutMapping(FallbackUrl.USER)
    public Mono<String> fallbackUserPut() {
        return Mono.just(FallbackMessage.USER_LOG);
    }

    @DeleteMapping(FallbackUrl.USER)
    public Mono<String> fallbackUserDelete() {
        return Mono.just(FallbackMessage.USER_LOG);
    }

    @GetMapping(FallbackUrl.TAXI)
    public Mono<String> fallbackTaxiGet() {
        return Mono.just(FallbackMessage.TAXI_LOG);
    }

    @PostMapping(FallbackUrl.TAXI)
    public Mono<String> fallbackTaxiPost() {
        return Mono.just(FallbackMessage.TAXI_LOG);
    }

    @PutMapping(FallbackUrl.TAXI)
    public Mono<String> fallbackTaxiPut() {
        return Mono.just(FallbackMessage.TAXI_LOG);
    }
}
