# spring cloud msa 게시판

## 아키텍쳐

![msa아키텍처구성최신](https://user-images.githubusercontent.com/68090443/144731013-a2d077d6-c00c-4e3f-a4f4-ded09d0773f7.PNG)


## 프로젝트 구성

|서비스|name|포트|
|------|---|---|
|Eureka Server|유레카 서버|8761|
|API gateway Service|spring cloud gateway 서비스|8080|
|User & Auth Service|유저,인증 서비스|8081|
|Board Service|게시판 서비스|8083|
|Reply Service|댓글 서비스|8084|
|Like Service|좋아요 서비스|8085|


## ERD

![게시판erd](https://user-images.githubusercontent.com/68090443/144731036-ac89c4e0-28d2-4f5d-8d32-47ed2f8cae9b.PNG)
