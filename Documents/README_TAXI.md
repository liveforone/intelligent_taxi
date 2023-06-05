# 택시 서비스

## 택시 서비스 소개
* 택시 서비스는 택시정보를 관리하는 서비스입니다.
* TAXI 권한을 가진 회원만 접근 가능합니다.

## 상세 요구사항
* jwt 토큰을 파싱해서 auth가 TAXI인 유저만 택시 서비스에 접근 가능하다.
* 택시의 지역분류는 아래의 지역분류 정책을 따른다.
* 택시 등급은 아래의 택시 등급표를 따르며, 택시 등급은 택시기사의 차량이 변경되면 택시기사의 요청아래 변경이 가능하다.
* 택시기사의 경우 전화번호를 기입한다. 배차후에도 비정상적으로 고객에서 도착하지 않을때 전화해야하기 때문이다.
* 회원이 탈퇴할때 권한이 TAXI라면 택시 정보도 같이 삭제된다.
* 서비스내 택시 식별 번호는 id(pk)이다.
* 전화번호나 차량 번호를 변경하려면 택시 정보를 삭제하고 다시 만들어야한다. 이에 대한 변경 api를 제공하지 않는다.
* 이는 잘 변경되지 않는 특징을 가진 두 컬럼에 대해 unique 제약을 걸어놓았기 때문이다. (unique는 업데이트 불가하다)
* 이에 대한 해결책은 [중복된 값을 가져서는 안되는 Unique 컬럼의 업데이트](https://github.com/liveforone/intelligent_taxi/blob/master/Documents/UNIQUE_COLUMN_UPDATE_PROBLEM.md)에서 볼 수 있다.

## API 설계
### 내부 API
```
[GET] /taxi/my-page : 마이페이지, username으로 조회
[POST] /create/taxi : 택시 등록페이지
[PUT] /update/region/{id} : 지역 변경 페이지
[PUT] /update/grade/{id} : 택시 등급 변경 페이지
[DELETE] /delete/taxi/{id} : 택시 등록 해제
```
### 외부 API
```
[GET] /provide/taxi/info/{id} : 택시 정보 조회 외부 리턴 api
```

## Json body 예시
```
[생성]
{
  "region": "서울",
  "taxiGrade": "NORMAL",
  "licenseNum": "00아0000",
  "phoneNum": "01012345678"
}

[등급 변경]
{
  "taxiGrade": "PREMIUM"
}

[지역 변경]
{
  "region": "경기"
}
```

## 서비스간 통신
### 회원 탈퇴시 택시 회원의 택시 정보 삭제
* kafka를 사용한다.
* user-service 가 produce 한다.
```
remove-taxi-belong-member
```

## 지역분류 정책 - 표준 지역분류
* 지역은 특별시·광역시(종래 직할시)·도별로 분류한다.
* 프론트에서 백으로 요청시에도 동일은 분류를 따른다.
* 해당 분류는 서비스내 표준 분류이다.
* 분류는 다음과 같다.
* 아래의 어떠한 값도 입력이 없을시 에러발생한다.
```
[문자] - 프론트 입력 가능 값
서울 경기 인천
강원 
충북 충남 대전
경북 경남 대구 울산 부산
전북 전남 광주
제주

[코드] - 백엔드 
//==수도권==//
SEOUL, GYEONGGI, INCHEON,
//==강원==//
GANGWON,
//==충청==//
CHUNGBUK, CHUNGNAM, DAEJEON,
//==경상==//
GYEONGBUK, GYEONGNAM, DAEGU, ULSAN, BUSAN,
//==전라==//
JEOLLABUK, JEOLLANAM, GWANGJU,
//==제주==//
JEJU;
```

## 택시 등급 표
* 택시 등급은 택시의 종류에 따라 분류한다.
* NORMAL, PREMIUM, VAN 로 분류하며
* NORMAL은 일반 택시
* PREMIUM은 고급 택시
* VAN은 승합 택시를 의미한다.
* 아래의 어떠한 값도 입력이 없을시 에러발생한다.
```
NORMAL, PREMIUM, VAN
```