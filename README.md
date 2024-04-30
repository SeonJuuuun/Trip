## 🖥 프로젝트 소개
여행 동행 웹 사이트로 동행자에 대한 구체적인 정보가 부족하더라도
해당 사용자의 신뢰 점수를 통해 동행자의 안전성을 확인할 수 있습니다.
동행자의 신뢰도에 대한 서비스를 도입하여 웹 사이트 이용자들이 더욱 안심하고 여행을 즐길 수 있도록 만들었습니다.


## ⏰ 개발 기간
+ 23.04 ~ 23.11

## 👥 멤버 구성
+ 유선준 : [Github](https://github.com/SeonJuuuun)
+ 김주형 : [Github](https://github.com/Joohyeng)
+ 한동연 : [Github](https://github.com/DamonHan5349)
+ 박재현 : [Github](https://github.com/harizuma1)

## ⚙️ 개발 환경
+ `Java 17`
+ `IDE : IntelliJ`
+ `FrameWork : Spring Boot (3.x), Thymeleaf`
+ `Database : MySQL, H2`
+ `ORM : JPA`
+  `Cloud : AWS EC2`
+  `Management: Github`

## 📌 주요 기능
|![image](https://github.com/SeonJuuuun/Trip/assets/110665247/b929b534-6719-4b83-86d3-5853e36a6908)|<img width="1506" alt="image" src="https://github.com/SeonJuuuun/Trip/assets/110665247/6815a6bc-48ec-4af5-bb01-9eee4729363f">|
|:---:|:---:|
|메인 페이지|마이페이지 화면|

+ 홈페이지의 메인 화면을 볼 수 있고, 로그인 여부에 상관없이 게시글을 볼 수 있습니다. 또한 위의 헤더 바를 통해 후기 게시판 및 마이페이지, 글쓰기 기능을 사용할 수 있습니다.
+ 마이페이지는 내 정보, 자기가 작성한 글, 동행 신청 목록, 로그아웃 기능이 있고 회원 수정 기능이 있습니다.

#### 회원가입, 로그인
|<img width="668" alt="image" src="https://github.com/SeonJuuuun/Trip/assets/110665247/a8298ec3-6d56-4917-ac7e-35cb57132242">|<img width="540" alt="image" src="https://github.com/SeonJuuuun/Trip/assets/110665247/a99f59b7-2b5f-4d96-9f47-6365cc79b38a">|
|:---:|:---:|
|회원가입 화면|로그인 화면|

+ 아이디, 비밀번호, 이메일, 닉네임, 성별, 생년월일을 입력받아 MySQL DB에 저장하고, DB에 저장된 데이터를 기반으로 로그인을 수행합니다.
+ 로그인 시도 시 소셜 로그인 기능을 통하여 구글 및 네이버 소셜로그인이 가능하게 구현하였습니다.

#### 글쓰기, 글상세, 댓글
|![image](https://github.com/SeonJuuuun/Trip/assets/110665247/9164ad4a-2399-48b5-866a-0d446292a985)|![image](https://github.com/SeonJuuuun/Trip/assets/110665247/2bb4e2aa-179c-4190-9b5f-b1f1bfcf6e74)|![image](https://github.com/SeonJuuuun/Trip/assets/110665247/a7b091ac-85cb-4272-bcbb-565566cc8d01)|
|:---:|:---:|:---:|
|글쓰기 화면|글상세 화면|댓글 화면|
+ 전반적인 게시판 글쓰기 기능을 나타냈습니다. 여행할 도시, 날짜, 모집인원, 사진, 제목, 내용을 기재하여 글쓰기를 완료할 수 있습니다.
+ 게시판에 게시한 글의 상세 정보를 보여줍니다. 제목, 내용을 비롯하여 게시글에 달린 댓글 또한 볼 수 있으며, 우측 동행 신청, 현황을 클릭하여 게시글에 대해 동행을 신청할 수 있고, 게시글의 주인은 신청 현황을 볼 수 있습니다.

#### 동행 신청, 동행 내역
|<img width="428" alt="image" src="https://github.com/SeonJuuuun/Trip/assets/110665247/8e17c210-e74b-40c0-bc52-51f99ef422f7">|![image](https://github.com/SeonJuuuun/Trip/assets/110665247/0b515a03-d23d-4947-b248-a15f6209b5af)|![image](https://github.com/SeonJuuuun/Trip/assets/110665247/3b9580d2-14e8-4d49-801f-5451f13ec1c3)|![image](https://github.com/SeonJuuuun/Trip/assets/110665247/b704c3b2-0e3b-4a32-b9dc-1e132461114f)|
|:---:|:---:|:---:|:---:|
|동행 신청 버튼|동행 신청 목록|동행 내역|동행 확정 내역|
+ 게시글 동행 신청 현황을 볼 수 있습니다. 게시글 주인은 수락, 거절 여부를 결정할 수 있습니다.
+ 동행확정자의 앞선 동행내역들을 확인할 수 있습니다.
+ 동행 신청을 수락하여 동행이 확정된 사용자의 현황을 보여줍니다.


#### 별점 및 후기
![image](https://github.com/SeonJuuuun/Trip/assets/110665247/8af7be08-5a09-45ea-a4d1-ef594dd90147)
+ 동행이 끝난 후, 별점 및 후기를 작성할 수 있습니다.

## 흐름도
![image](https://github.com/SeonJuuuun/Trip/assets/110665247/7ab4c806-2fcc-4053-93fe-2221ad0333c9)






