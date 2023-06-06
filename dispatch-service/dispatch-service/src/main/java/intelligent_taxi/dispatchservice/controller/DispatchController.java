package intelligent_taxi.dispatchservice.controller;

import intelligent_taxi.dispatchservice.authentication.AuthenticationInfo;
import intelligent_taxi.dispatchservice.dto.dispatch.DispatchResponse;
import intelligent_taxi.dispatchservice.query.DispatchQueryService;
import intelligent_taxi.dispatchservice.validator.ControllerValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static intelligent_taxi.dispatchservice.controller.constant.DispatchUrl.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DispatchController {

    private final DispatchQueryService dispatchQueryService;
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
}
