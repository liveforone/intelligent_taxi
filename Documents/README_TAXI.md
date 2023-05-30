# 택시 서비스

## 택시 서비스 소개
* 택시 서비스는 택시정보를 관리하는 서비스입니다.
* TAXI 권한을 가진 회원만 접근 가능합니다.

## 상세 요구사항

## API 설계
### 내부 API
### 외부 API

## Json body 예시

## 서비스간 통신
### 회원 탈퇴시 택시 회원의 택시 정보 삭제
* kafka를 사용한다.
* user-service 가 produce 한다.
```
remove-taxi-belong-member
```

택시 회원 탈퇴시 택시 정보도삭제

배차 정보등 택시와 관련된건 회원이 아니라 taxi id를 사용한다.
즉 택시관련은 모두 taxiId 기반

지역분류, 차량 번호, 전화번호, 택시 등급, username

[지역분류(region)] - 특별시·광역시(종래 직할시)·도별
서울, 경기, 인천, 부산, 대구, 광주, 대전, 울산,
전북, 전남, 경북, 경남, 강원

[택시 등급(taxi_grade)]
NORMAL, PREMIUM, VAN

택시로 가입한 회원만 등록가능하며, 등록시 번호판과 전화번호를 등록한다.
헤더 체크하여 driver인지 확인
택시들은 택시 종류를 바꾸는 것이 가능하다.