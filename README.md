# Intelligent Taxi
* 택시 승차 서비스 입니다.
* 자동 배차 시스템으로 배차를 처리합니다.
* 가상회사인 intelligent의 두번째 서비스입니다.
* 결제 시스템은 [intelligent pay](https://github.com/liveforone/intelligent_pay)을 사용합니다.
* **"위도, 경도 좌표를 활용한 거리계산 알고리즘"**
* **"비동기 분산 트랜잭션 관리, 비동기 이벤트 처리**" 
* **"자동배차 알고리즘"**
* **"enum 활용"**
* 위의 내용들을 중심적으로 다루었습니다.

# 0. 목차
1. [프로젝트 소개](#1-프로젝트-소개)
2. [프로젝트 고민점](#2-프로젝트-고민점)
3. [서비스별 문서](#3-서비스별-문서)
4. [프로젝트 설계 문서](#4-프로젝트-설계-문서)
5. [새롭게 알게된 점](#5-새롭게-알게된-점)
6. [이슈](#6-이슈)

# 1. 프로젝트 소개
## 프로젝트 소개 문서
* [프로젝트 소개](https://github.com/liveforone/intelligent_taxi/blob/master/Documents/INTRODUCE.md)
## 기술스택
* Framework : Spring Boot 3.1.0 & Spring Cloud(2022.0.3)
* Lang : Java17
* Data : Spring Data Jpa & Query Dsl & MySql
* Security : Spring Security & Jwt
* Service Communication : Apache Kafka(Async), Open Feign Client(Sync)
* Container : Docker & Docker-compose
* Test : Junit5
* Util : Apache Commons Lang3, LomBok

# 2. 프로젝트 고민점
* [중복된 값을 가져서는 안되는 Unique 컬럼의 업데이트](https://github.com/liveforone/intelligent_taxi/blob/master/Documents/UNIQUE_COLUMN_UPDATE_PROBLEM.md)
* [자동배차 알고리즘](https://github.com/liveforone/intelligent_taxi/blob/master/Documents/AUTO_DISPATCH_ALGORITHM.md)

# 3. 서비스별 문서
* [회원 서비스](https://github.com/liveforone/intelligent_taxi/blob/master/Documents/README_USER.md)
* [택시 서비스](https://github.com/liveforone/intelligent_taxi/blob/master/Documents/README_TAXI.md)
* [배차 서비스](https://github.com/liveforone/intelligent_taxi/blob/master/Documents/README_DISPATCH.md)

# 4. 프로젝트 설계 문서
* [수익 모델](https://github.com/liveforone/intelligent_taxi/blob/master/Documents/PROFIT.md)
* [전반 설계](https://github.com/liveforone/intelligent_taxi/blob/master/Documents/DESIGN.md)
* [DB 설계](https://github.com/liveforone/intelligent_taxi/blob/master/Documents/DB_DESIGN.md)
* [화면 설계](https://github.com/liveforone/intelligent_taxi/blob/master/Documents/INTERFACE_DESIGN.md)

# 5. 새롭게 알게된 점
* [enum과 입력값 대조하기](https://github.com/liveforone/intelligent_taxi/blob/master/Documents/ENUM_INPUT_MATCH.md)

# 6. 이슈
* [enum 저장 이슈](https://github.com/liveforone/intelligent_taxi/blob/master/Documents/JPA_ENUM_ISSUE.md)