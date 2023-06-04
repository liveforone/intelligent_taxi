package intelligent_taxi.taxiservice.controller;

import intelligent_taxi.taxiservice.authentication.AuthenticationInfo;
import intelligent_taxi.taxiservice.command.TaxiCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TaxiController {

    private final TaxiCommandService taxiCommandService;
    private final AuthenticationInfo authenticationInfo;
}
