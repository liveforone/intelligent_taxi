# 배차 서비스

## 배차 서비스 소개
* 배차 서비스는 배차를 저장하고 자동 배차를 처리하는 서비스로
* member 회원과 taxi회원 모두가 사용합니다.

## 상세 요구사항
* 배차를 등록하고 회원이 자신의 배차 상태를 확인할때
* 배차 페이지는 username으로 리턴하는데 가장 id가 최신인 것 하나를 리턴하도록 한다.
* 배차서비스는 주문 서비스와 정산 서비스로 비동기 요청을 보낸다.
* 이에 대해 롤백 처리 또한 비동기로 처리한다.
* 배차를 등록하면 결제서비스를 통해서 잔액을 확인합니다.
* 금액은 미리 계산된 금액만큼 결제하고 다시 환불하여 처리합니다.
* 배차가 등록될때 금액은 계산이 됩니다. 이유는 배차 등록시 목적지도 같이 입력받기 때문입니다.
* 자동배차는 1km, 3km, 5km 내에서만 가능하며, 그 이상은 배차가 불가능하다.
* 자동배차는 가장 가까운 거리로 등록된 배차 요청에 배차시킨다.
* 배차가 완료된 경우 배차 취소는 불가능하다. 이는 택시 기사와 소비자 모두에게 적용된다.
* 배차가 완료되지 않은 경우 배차 취소는 가능합니다.
* 배차가 완료됨과 동시에 이용자는 주문과 결제가 체결되고,
* 기사는 정산이 완료됩니다.

## API 설계
### 내부 API
```
[GET] /dispatch/member/home : 회원 홈
[POST] /dispatch/create : 배차 등록(member 회원)
[DELETE] /dispatch/remove/{id} : 배차 취소
[POST] /dispatch/request : 배차 요청(taxi 회원)
```

## Json body 예시
```
{
  "presentLatitude": 37.582848,
  "presentLongitude": 127.010581,
  "destinationLatitude": 37.579369,
  "destinationLongitude": 127.015299
}

{
  "presentLatitude": 37.588374,
  "presentLongitude": 127.005907
}
```

## 서비스간 통신
### 주문 요청
* producer
* RequestOrder dto 사용
```
request-order
```
### 정산 요청
* producer
* RequestCalculate dto 사용
```
request-calculate
```
### 주문 실패로 배차 롤백 요청
* consumer
* dispatchId 사용
```
order-fail-rollback-dispatch
```
### 정산 실패로 배차 롤백 요청
* consumer
* dispatchId 사용
```
calculate-fail-rollback-dispatch
```
### 주문 실패로 정산 롤백 요청
* producer
* dispatchId 사용
```
order-fail-rollback-calculate
```
### 정산 실패로 주문 롤백 요청
* producer
* dispatchId 사용
```
calculate-fail-rollback-order
```

## 가장 최신 id로 조회 쿼리
* 사용자가 등록한 배차를 본인이 확인할때 가장 최신으로 등록된 배차를 username으로 찾아서 리턴한다.
* 이때 쿼리는 아래와 같다. 단 한개만 가져오기 위해서 limit을 건다.
```
SELECT *
FROM a
WHERE username = 'kim'
ORDER BY id DESC
LIMIT 1;
```

## 배차 프로세스
* 배차완료(주문 요청) -> 주문(결제 요청) -> 결제
* 배차완료(정산 요청) -> 운행(정산 요청) -> 정산

## 자동배차
* [자동배차](https://github.com/liveforone/intelligent_taxi/blob/master/Documents/AUTO_DISPATCH_ALGORITHM.md) 문서를 보시면 자동배차의 이유와 자동배차 알고리즘이 작성되어있습니다.