package intelligent_taxi.dispatchservice.controller;

import intelligent_taxi.dispatchservice.authentication.AuthenticationInfo;
import intelligent_taxi.dispatchservice.query.DispatchQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DispatchController {

    private final DispatchQueryService dispatchQueryService;
    private final AuthenticationInfo authenticationInfo;

    @GetMapping
}
