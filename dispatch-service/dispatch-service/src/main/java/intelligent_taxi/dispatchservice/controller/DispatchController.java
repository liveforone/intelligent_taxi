package intelligent_taxi.dispatchservice.controller;

import intelligent_taxi.dispatchservice.authentication.AuthenticationInfo;
import intelligent_taxi.dispatchservice.command.DispatchCommandService;
import intelligent_taxi.dispatchservice.controller.constant.ControllerLog;
import intelligent_taxi.dispatchservice.controller.constant.DispatchParam;
import intelligent_taxi.dispatchservice.controller.restResponse.RestResponse;
import intelligent_taxi.dispatchservice.dto.dispatch.DispatchRequest;
import intelligent_taxi.dispatchservice.dto.dispatch.DispatchResponse;
import intelligent_taxi.dispatchservice.query.DispatchQueryService;
import intelligent_taxi.dispatchservice.validator.ControllerValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static intelligent_taxi.dispatchservice.controller.constant.DispatchUrl.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DispatchController {

    private final DispatchQueryService dispatchQueryService;
    private final DispatchCommandService dispatchCommandService;
    private final AuthenticationInfo authenticationInfo;
    private final ControllerValidator controllerValidator;

    @GetMapping(DISPATCH_MEMBER_HOME)
    public ResponseEntity<?> dispatchMemberHome(
            HttpServletRequest request
    ) {
        controllerValidator.validateAuthIsMember(authenticationInfo.getAuth(request));

        DispatchResponse dispatch = dispatchQueryService.getCurrentDispatchByUsername(authenticationInfo.getUsername(request));
        return ResponseEntity.ok(dispatch);
    }

    @PostMapping(CREATE_DISPATCH)
    public ResponseEntity<?> createDispatch(
            @RequestBody @Valid DispatchRequest requestDto,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        controllerValidator.validateAuthIsMember(authenticationInfo.getAuth(request));
        controllerValidator.validateBinding(bindingResult);

        dispatchCommandService.createDispatch(requestDto, authenticationInfo.getUsername(request));
        log.info(ControllerLog.CREATE_DISPATCH_SUCCESS.getLog());

        return RestResponse.createDispatchSuccess();
    }

    @DeleteMapping(REMOVE_DISPATCH)
    public ResponseEntity<?> removeDispatch(
            @PathVariable(DispatchParam.ID) Long id,
            HttpServletRequest request
    ) {
        dispatchCommandService.removeDispatch(id, authenticationInfo.getUsername(request));

        return RestResponse.removeDispatchSuccess();
    }
    
    //배차요청, auth check = taxi
}
