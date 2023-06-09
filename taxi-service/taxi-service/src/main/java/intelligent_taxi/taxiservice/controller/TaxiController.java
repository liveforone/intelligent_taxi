package intelligent_taxi.taxiservice.controller;

import intelligent_taxi.taxiservice.authentication.AuthenticationInfo;
import intelligent_taxi.taxiservice.command.TaxiCommandService;
import intelligent_taxi.taxiservice.controller.constant.ControllerLog;
import intelligent_taxi.taxiservice.controller.constant.TaxiParam;
import intelligent_taxi.taxiservice.controller.restResponse.RestResponse;
import intelligent_taxi.taxiservice.dto.TaxiRequest;
import intelligent_taxi.taxiservice.dto.TaxiResponse;
import intelligent_taxi.taxiservice.dto.UpdateGradeRequest;
import intelligent_taxi.taxiservice.dto.UpdateRegionRequest;
import intelligent_taxi.taxiservice.query.TaxiQueryService;
import intelligent_taxi.taxiservice.validator.ControllerValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static intelligent_taxi.taxiservice.controller.constant.TaxiUrl.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TaxiController {

    private final TaxiCommandService taxiCommandService;
    private final TaxiQueryService taxiQueryService;
    private final ControllerValidator controllerValidator;
    private final AuthenticationInfo authenticationInfo;

    @GetMapping(MY_PAGE)
    public ResponseEntity<?> myTaxiPage(
            HttpServletRequest request
    ) {
        controllerValidator.validateAuth(authenticationInfo.getAuth(request));

        TaxiResponse taxi = taxiQueryService.getTaxiByUsername(authenticationInfo.getUsername(request));
        return ResponseEntity.ok(taxi);
    }

    @GetMapping(TAXI_INFO_BY_ID)
    public ResponseEntity<?> provideTaxiInfoId(
            @PathVariable(TaxiParam.ID) Long id
    ) {
        TaxiResponse taxi = taxiQueryService.getTaxiById(id);
        return ResponseEntity.ok(taxi);
    }

    @GetMapping(TAXI_INFO_BY_USERNAME)
    public ResponseEntity<?> provideTaxiInfoUsername(
            @PathVariable(TaxiParam.USERNAME) String username
    ) {
        TaxiResponse taxi = taxiQueryService.getTaxiByUsername(username);
        return ResponseEntity.ok(taxi);
    }

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

    @PutMapping(UPDATE_REGION)
    public ResponseEntity<?> updateRegion(
            @PathVariable(TaxiParam.ID) Long id,
            @RequestBody @Valid UpdateRegionRequest requestDto,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        controllerValidator.validateBinding(bindingResult);

        taxiCommandService.updateRegion(
                requestDto, authenticationInfo.getUsername(request), id
        );
        log.info(ControllerLog.UPDATE_REGION_SUCCESS.getLog() + id);

        return RestResponse.updateRegionSuccess();
    }

    @PutMapping(UPDATE_GRADE)
    public ResponseEntity<?> updateGrade(
            @PathVariable(TaxiParam.ID) Long id,
            @RequestBody @Valid UpdateGradeRequest requestDto,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        controllerValidator.validateBinding(bindingResult);

        taxiCommandService.updateGrade(
                requestDto, authenticationInfo.getUsername(request), id
        );
        log.info(ControllerLog.UPDATE_GRADE_SUCCESS.getLog() + id);

        return RestResponse.updateGradeSuccess();
    }

    @DeleteMapping(DELETE_TAXI)
    public ResponseEntity<?> deleteTaxi(
            @PathVariable(TaxiParam.ID) Long id,
            HttpServletRequest request
    ) {
        taxiCommandService.deleteOneById(id, authenticationInfo.getUsername(request));
        log.info(ControllerLog.DELETE_TAXI_SUCCESS.getLog() + id);

        return RestResponse.deleteTaxiSuccess();
    }
}
