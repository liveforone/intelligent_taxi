package intelligent_taxi.taxiservice.controller;

import intelligent_taxi.taxiservice.authentication.AuthenticationInfo;
import intelligent_taxi.taxiservice.command.TaxiCommandService;
import intelligent_taxi.taxiservice.controller.constant.ControllerLog;
import intelligent_taxi.taxiservice.controller.restResponse.RestResponse;
import intelligent_taxi.taxiservice.dto.TaxiRequest;
import intelligent_taxi.taxiservice.validator.ControllerValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static intelligent_taxi.taxiservice.controller.constant.TaxiUrl.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TaxiController {

    private final TaxiCommandService taxiCommandService;
    private final ControllerValidator controllerValidator;
    private final AuthenticationInfo authenticationInfo;

    @PostMapping(CREATE)
    public ResponseEntity<?> createTaxi(
            @RequestBody @Valid TaxiRequest requestDto,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        controllerValidator.validateBinding(bindingResult);
        controllerValidator.validateAuth(authenticationInfo.getAuth(request));

        taxiCommandService.createTaxi(requestDto, authenticationInfo.getUsername(request));
        log.info(ControllerLog.CREATE_TAXI_SUCCESS.getLog());

        return RestResponse.createTaxiSuccess();
    }
}
