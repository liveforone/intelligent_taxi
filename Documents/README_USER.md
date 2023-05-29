# 회원 서비스

## 회원 서비스 소개
* 회원 정보관리를 담당하는 서비스 입니다.
* Member 엔티티와 inteliigent_taxi_user DB가 회원 서비스에 속해있습니다.

## 회원관리 기술
* 서버의 성능을 중요시 하는 전체적인 정책때문에 회원 관리 또한 세션처럼 서버에서 부담을 주는 방식이 아닌,
* Jwt 토큰을 활용해 stateless 하게 서버에 부담을 주지 않는 방식을 채택하였습니다.
* 토큰관리는 가장 보편적인 방식인 프론트엔드의 로컬스토리지에서 jwt토큰을 관리하는것을 전제로 합니다.
* 토큰의 만료시간은 2시간입니다.

## 상세 요구사항
* 회원은 일반회원(MEMBER)과 택시(TAXI)회원으로 나누어진다.
* 회원의 상태는 WORK, BLOCK 이 있고, block은 정지상태이다.
* 모든 회원은 uuid기반인 username으로 식별한다.
* username은 uuid + 문자4개의 형태이다. uuid의 중복을 완전히 없애기 위해 뒤에 문자를 붙였다.
* jwt에는 회원의 권한(role)과 username(식별자)가 있다.
* 회원가입시 이메일/비밀번호와 함께 실명도 입력받는다.
* 회원은 이메일과 비밀번호의 변경이 가능해야한다.
* 회원은 마이페이지에서 내 정보를 볼 수 있다.
* 회원은 탈퇴가 가능하다.
* 회원은 신고를 통해서 계정이 정지가 되며, 계정 정지시 택시를 배차 요청/배차 받기 등 모든 활동에 제한을 받는다.

## API 설계
### 내부 API
```
[GET] / : 홈(토큰 불필요)
[GET/POST] /signup/member : 회원가입(토큰 불필요), MemberRequest 형식 필요
[GET/POST] /signup/taxi : 회원가입(토큰 불필요), MemberRequest 형식 필요
[GET/POST] /login : 로그인(토큰 불필요)
[GET] /logout : 로그아웃, get으로 받아도 정상 작동(토큰 불필요)
[GET] /my-info : 마이페이지(토큰 필요)
[Put] /change/email : 이메일 변경(토큰 필요), ChangeEmailRequest 형식
[Put] /change/password : 비밀번호 변경(토큰 필요), ChangePasswordRequest 형식
[DELETE] /withdraw : 회원탈퇴(토큰 필요), text 형식 문자열 비밀번호 필요
[GET] /prohibition : 403 페이지(토큰 불필요)
```
### 외부 제공 API
```
[GET] /provide/my-bankbookNum/{username} : 결제시 계좌번호 리턴
```

## Json body 예시
```
[일반 유저/택시 기사]
{
    "email" : "yc1234@gmail.com",
    "password" : "1234",
    "realName" : "chan",
    "bankbookNum" : "1234567891234"
}

[이메일 변경]
{
    "email" : "yc1111@gmail.com"
}

[비밀번호 변경]
{
    "oldPassword" : "12345678
    "newPassword" : "13579345"
}
```

## 서비스간 통신
### 신고 접수로 신고 누적횟수 1회 증가
* kafka를 사용한다.
* report-service에서 produce한다.
```
increase-report
```
### 정지 해제 접수로 계정 정지 해제
* kafka를 사용한다.
* report-service에서 produce한다.
```
cancel-block
```

## 계정 정지 정책과 코드
* 10번의 신고를 받으면, 기사던 일반 사용자던 모두 계정이 정지됩니다.
* 정지된 계정은 6개월후에 정지가 풀리며 이는 사용자가 직접 정지 해제 신청하여 갱신해야합니다.
* 정지가 해제된 후에는 단 한번의 신고를 또 받게 되면 다시 6개월이 정지됩니다.
* 회원의 누적신고를 증가할때 누적 신고횟수가 9개라면
* 회원을 정지상태로 바꾸고, 누적신고를 + 1해준다.
* 아래는 도메인 로직이 담긴 도메인 객체의 함수이다.
```
public void increaseReport() {
    //block_check_report = 10이다.
    if (MemberBlockPolicy.BLOCK_CHECK_REPORT == this.getReports()) {
            this.memberState = MemberState.BLOCK;
    }
    this.reports += MemberConstant.ONE;
}
```

## 계정 정지 해제 정책과 코드
* 계정을 정지 해제는 정지된 후 6개월 뒤에 가능합니다.
* 신고 서비스에서 가장 최신의 신고 데이터를 받아서 
* 6개월이 지났을때 해제 요청이 수락됩니다.
* 정지를 해제하면서 누적신고횟수는 1개를 줄여줍니다.
* 누적 신고횟수는 절대 0으로 돌아가지 않습니다. 
* 아래는 도메인 로직이 담긴 도메인 객체의 함수이다.
```
public void cancelBlock() {
    this.memberState = MemberState.WORK;
    this.reports -= MemberConstant.ONE;
}
```