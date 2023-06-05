package intelligent_taxi.userservice.controller;

import intelligent_taxi.userservice.authentication.AuthenticationInfo;
import intelligent_taxi.userservice.command.MemberCommandService;
import intelligent_taxi.userservice.command.MemberProducer;
import intelligent_taxi.userservice.controller.constant.ControllerLog;
import intelligent_taxi.userservice.controller.constant.MemberParam;
import intelligent_taxi.userservice.controller.restResponse.RestResponse;
import intelligent_taxi.userservice.dto.changeInfo.ChangeEmailRequest;
import intelligent_taxi.userservice.dto.changeInfo.ChangePasswordRequest;
import intelligent_taxi.userservice.dto.changeInfo.WithdrawRequest;
import intelligent_taxi.userservice.dto.response.MemberResponse;
import intelligent_taxi.userservice.dto.signupAndLogin.MemberLoginRequest;
import intelligent_taxi.userservice.dto.signupAndLogin.MemberSignupRequest;
import intelligent_taxi.userservice.jwt.TokenInfo;
import intelligent_taxi.userservice.jwt.constant.JwtConstant;
import intelligent_taxi.userservice.query.MemberQueryService;
import intelligent_taxi.userservice.validator.ControllerValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static intelligent_taxi.userservice.controller.constant.MemberUrl.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    private final MemberProducer memberProducer;
    private final ControllerValidator controllerValidator;
    private final AuthenticationInfo authenticationInfo;

    @PostMapping(SIGNUP_MEMBER)
    public ResponseEntity<?> signupMember(
            @RequestBody @Valid MemberSignupRequest requestDto,
            BindingResult bindingResult
    ) {
        controllerValidator.validateBinding(bindingResult);

        memberCommandService.signupMember(requestDto);
        log.info(ControllerLog.SIGNUP_SUCCESS.getValue());

        return RestResponse.signupSuccess();
    }

    @PostMapping(SIGNUP_TAXI)
    public ResponseEntity<?> signupTaxi(
            @RequestBody @Valid MemberSignupRequest requestDto,
            BindingResult bindingResult
    ) {
        controllerValidator.validateBinding(bindingResult);

        memberCommandService.signupTaxi(requestDto);
        log.info(ControllerLog.SIGNUP_SUCCESS.getValue());

        return RestResponse.signupSuccess();
    }

    @PostMapping(LOGIN)
    public ResponseEntity<?> login(
            @RequestBody @Valid MemberLoginRequest requestDto,
            BindingResult bindingResult,
            HttpServletResponse response
    ) {
        controllerValidator.validateBinding(bindingResult);

        TokenInfo tokenInfo = memberCommandService.login(requestDto);
        log.info(ControllerLog.LOGIN_SUCCESS.getValue());

        response.addHeader(JwtConstant.ACCESS_TOKEN, tokenInfo.getAccessToken());
        response.addHeader(JwtConstant.REFRESH_TOKEN, tokenInfo.getRefreshToken());
        return RestResponse.loginSuccess();
    }

    @GetMapping(MY_INFO)
    public ResponseEntity<?> myInfo(HttpServletRequest request) {
        String username = authenticationInfo.getUsername(request);
        MemberResponse member = memberQueryService.getMemberByUsername(username);

        return ResponseEntity.ok(member);
    }

    @GetMapping(MY_BANKBOOK_NUM)
    public ResponseEntity<?> myBankbookNum(
            @PathVariable(MemberParam.USERNAME) String username
    ) {
        String bankbookNum = memberQueryService.getBankbookNumByUsername(username);
        return ResponseEntity.ok(bankbookNum);
    }

    @PutMapping(CHANGE_EMAIL)
    public ResponseEntity<?> changeEmail(
            @RequestBody @Valid ChangeEmailRequest requestDto,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        controllerValidator.validateBinding(bindingResult);

        String username = authenticationInfo.getUsername(request);
        memberCommandService.updateEmail(requestDto, username);
        log.info(ControllerLog.CHANGE_EMAIL_SUCCESS.getValue() + username);

        return RestResponse.changeEmailSuccess();
    }

    @PutMapping(CHANGE_PASSWORD)
    public ResponseEntity<?> changePassword(
            @RequestBody @Valid ChangePasswordRequest requestDto,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        controllerValidator.validateBinding(bindingResult);

        String username = authenticationInfo.getUsername(request);
        memberCommandService.updatePassword(requestDto, username);
        log.info(ControllerLog.CHANGE_PASSWORD_SUCCESS.getValue() + username);

        return RestResponse.changePasswordSuccess();
    }

    @DeleteMapping(WITHDRAW)
    public ResponseEntity<?> withdraw(
            @RequestBody @Valid WithdrawRequest requestDto,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        controllerValidator.validateBinding(bindingResult);

        String username = authenticationInfo.getUsername(request);
        memberCommandService.withdrawByUsername(requestDto, username);
        memberProducer.removeTaxi(username);
        log.info(ControllerLog.WITHDRAW_SUCCESS.getValue() + username);

        return RestResponse.withdrawSuccess();
    }

    @GetMapping(PROHIBITION)
    public ResponseEntity<?> prohibition() {
        return RestResponse.prohibition();
    }
}