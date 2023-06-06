# 화면 설계
* 화면 설계의 api는 회원이 진입하는 api로,
* 백엔드에서 데이터를 제공하는 rest-api는 서비스별 문서에 기술되어있습니다.
* 화면 설계의 api는 통합 api를 의미합니다.
* 백엔드에서 제공하는 rest-api는 필요한 데이터에 맞게 지원되며
* 화면에 종속되는(화면 api에 맞는 모든 데이터를 한번에 전달) 형태의 api가 아닙니다.

## 회원 관련
### 회원가입
* 회원가입 창에 백엔드에서 제공하는 별다른 데이터가 필요없다.
```
/user/signup
```
### 로그인
* 로그인 창에 백엔드에서 제공하는 별다른 데이터가 필요없다.
```
/user/login
```
### 이메일 변경
```
/user/change/email
```
### 비밀번호 변경
```
/user/change/password
```
### 탈퇴
```
/user/withdraw
```
### 회원정보
```
/user/my-info
```

## 택시 정보
### 택시 등록
```
/taxi/create
```
### 택시 페이지
```
/taxi/info
```
### 택시 등록 해제
```
/taxi/delete
```

## 배차
### 배차 등록
```
/dispatch/create
```
### 배차 페이지
```
/dispatch/info
```