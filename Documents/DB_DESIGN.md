# DB 설계

## DB 생성
```
CREATE DATABASE intelligent_taxi_user;
CREATE DATABASE intelligent_taxi_taxi;
```

## DB별 테이블(인덱스 포함) 모델링
### USER DB
```
CREATE TABLE member (
    id bigint not null auto_increment,
    reports bigint,
    auth varchar(255) not null,
    member_state varchar(255) not null,
    email varchar(255) UNIQUE not null,
    password varchar(100) not null,
    real_name varchar(255) not null,
    username varchar(255) UNIQUE not null,
    primary key (id)
);
CREATE UNIQUE INDEX email_idx ON member (email);
CREATE UNIQUE INDEX username_idx ON member (username);
```
### TAXI DB
```
create table taxi (
    id bigint not null auto_increment,
    license_num varchar(255) UNIQUE not null,
    phone_num varchar(255) UNIQUE not null,
    region varchar(255) not null,
    taxi_grade varchar(255) not null,
    username varchar(255) UNIQUE not null,
    primary key (id)
);
CREATE UNIQUE INDEX username_idx ON taxi (username);
```
### DISPATCH DB
### ORDERS DB
### DRIVE DB
### CALCULATE DB
### REPORT DB
### REVIEW DB


## DB 설계 원칙
* **역할**과 **책임**에 따라 DB를 분리하라.
* 이는 마이크로서비스를 나누는 원칙과도 같다.
* DB에 최소한의 필요한 데이터만 둔다.
* 일례로 상품이 있다면 상품의 가격을 담당하는 부분과 별점과 리뷰를 담당하는 부분 등 여려가지 부분이 있다.
* 이것을 상품 DB에 모두 저장하고, 읽기 쓰기 작업을하게되면 부하가 한곳에 집중된다.
* 읽기를 할때 모든 데이터가 필요한것도 아니고, 쓰기를 할때도 마찬가지이다.
* 따라서 이를 나누어서 분산시켜, 필요할때 필요한 데이터만 리턴하도록 틀을 만든다.
* 쿠팡이 대표적인 예시로 제품의 이미지와 이름은 catalog팀에서,
* 가격은 pricing 팀에서 재고 정보는 fulfillment 팀에서 제공하는 방식으로 하여 유연성을 높였다.
* 이러한 설계는향후 거대한 시스템과 대규모 트래픽 대응, 대용량 데이터처리에 훨씬 용이해질 것이다.
* 인덱스는 유니크 컬럼의 경우 유니크 인덱스를 사용한다.