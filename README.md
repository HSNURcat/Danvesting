# danvesting

### 프로젝트 설명
 * 주식 관련 웹 investing.com을 모티브로 한 웹 프로젝트 개발
 * 로그인 기능 & Robinhood API를 사용한 주식가격, 기업정보 조회 & 댓글기능

### 설계
 * UI기획
  https://ovenapp.io/project/UmMs7pVmuHIFhAWODSfPs42pEi0o5teg#RsKDE
 * DB & URL설계
  https://docs.google.com/spreadsheets/d/1TZ5owEAYrdZrynSVB5nclCb-YuPhLwtDK49aidd3Y_0/edit?usp=sharing
  
###주요기능
 * 회원가입, 로그인, 회원정보 수정
   * packages : [com.dandan.danvesting/user/*](https://github.com/HSNURcat/danvesting/tree/master/src/main/java/com/dandan/danvesting/user)
   * 회원가입View : [webapp/WEB-INF/jsp/user/signUp.jsp](https://github.com/HSNURcat/danvesting/blob/master/src/main/webapp/WEB-INF/jsp/user/signUp.jsp)
   * 로그인View : [webapp/WEB-INF/jsp/user/signIn.jsp](https://github.com/HSNURcat/danvesting/blob/master/src/main/webapp/WEB-INF/jsp/user/signIn.jsp)
   * 회원정보 수정 전 사용자확인View : [webapp/WEB-INF/jsp/user/checkMember.jsp](https://github.com/HSNURcat/danvesting/blob/master/src/main/webapp/WEB-INF/jsp/user/checkMember.jsp)
   * 회원정보 회원정보 수정View : [webapp/WEB-INF/jsp/user/rewriteUserInfo.jsp](https://github.com/HSNURcat/danvesting/blob/master/src/main/webapp/WEB-INF/jsp/user/rewriteUserInfo.jsp)
  
 * 자유게시판 게시물 
   * 이미지 첨부 글쓰기
     * package : 

