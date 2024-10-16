<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="/commons/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>

<!DOCTYPE html>
<!-- saved from url=(0021)https://suustore.com/ -->
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />

    <meta http-equiv="X-UA-Compatible" content="IE=edge" />

    <link rel="preconnect" href="https://fonts.googleapis.com/" />
    <link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin="" />

    <!-- Bootstrap CSS v5.2.1 -->

   <link href="${URL}assets/bootstrap.min.css" rel="stylesheet" />

    <link
      rel="shortcut icon"
      href="https://suustore.com/assets/frontend/images/favicon.ico"
      type="image/x-icon"
    />
    <link rel="stylesheet" href="${URL}assets/app.css" />

    <script>
      window.SuuTruyen = {
        baseUrl: "https://suustore.com",
        urlCurrent: "https://suustore.com",
        csrfToken: "4EebYu2rWivdRk1ET12dyuY0CJjpRERhJynPtvUy",
      };
    </script>

    <title>Demo Sách</title>
    <meta
      name="description"
      content="Đọc sách online, truyện hay. Demo sách luôn tổng hợp và cập nhật sách một cách nhanh nhất."
    />
  </head>

  <body>
    <!--Begin header-->
   <%@ include file="/commons/web/header.jsp"%>
    <!--End header-->
    
	<!--Nội dung chính-->
	<sitemesh:write property="body" />
    <!--Kết thúc nội dung chính-->

    <!--Begin footer-->
	<%@ include file="/commons/web/footer.jsp"%>
    <!--End footer-->

    <script src="${URL}assets/jquery.min.js"></script>

    <script src="${URL}assets/popper.min.js"></script>

    <script src="${URL}assets/bootstrap.min.js"></script>

    <script src="${URL}assets/app.js"></script>
    <script src="${URL}assets/common.js"></script>

    <div id="loadingPage" class="loading-full">
      <div class="loading-full_icon">
        <div class="spinner-grow">
          <span class="visually-hidden">Loading...</span>
        </div>
      </div>
    </div>
  </body>
</html>