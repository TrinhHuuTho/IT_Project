<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>


<div class="header-mobile d-sm-block d-lg-none">
    <!-- Begin nav -->
    <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="home.jsp">
                <img src="${URL}assets/images/No-bg logo.png" alt="Logo Sach" class="img-fluid" style="width: 50px" />
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasDarkNavbar" aria-controls="offcanvasDarkNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="offcanvas offcanvas-end text-bg-dark w-75" tabindex="-1" id="offcanvasDarkNavbar" aria-labelledby="offcanvasDarkNavbarLabel">
                <div class="offcanvas-header">
                    <img src="${URL}assets/images/No-bg logo.png" alt="Logo Sach" class="img-fluid" style="width: 200px" />
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                </div>
                <div class="offcanvas-body">
                    <ul class="navbar-nav justify-content-end flex-grow-1 pe-3 mb-3">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Thể loại</a>
                            <ul class="dropdown-menu dropdown-menu-custom">
                                <c:forEach var="genre" items="${genres}">
                                    <li><a class="dropdown-item" href="#">${genre.genreName}</a></li>
                                </c:forEach>
                            </ul>
                        </li>
                    </ul>
                    <div class="form-check form-switch d-flex align-items-center mb-3 p-0">
                        <label class="form-check-label">
                            <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-brightness-high" viewBox="0 0 16 16" style="fill: #fff">
                                <path d="M8 11a3 3 0 1 1 0-6 3 3 0 0 1 0 6zm0 1a4 4 0 1 0 0-8 4 4 0 0 0 0 8zM8 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 0zm0 13a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 13zm8-5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2a.5.5 0 0 1 .5.5zM3 8a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2A.5.5 0 0 1 3 8zm10.657-5.657a.5.5 0 0 1 0 .707l-1.414 1.415a.5.5 0 1 1-.707-.708l1.414-1.414a.5.5 0 0 1 .707 0zm-9.193 9.193a.5.5 0 0 1 0 .707L3.05 13.657a.5.5 0 0 1-.707-.707l1.414-1.414a.5.5 0 0 1 .707 0zm9.193 2.121a.5.5 0 0 1-.707 0l-1.414-1.414a.5.5 0 0 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .707zM4.464 4.465a.5.5 0 0 1-.707 0L2.343 3.05a.5.5 0 1 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .708z"></path>
                            </svg>
                        </label>
                        <input class="form-check-input theme_mode" type="checkbox" style="transform: scale(1.3); margin-left: 12px; margin-right: 12px;" />
                        <label class="form-check-label">
                            <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" viewBox="0 0 384 512" style="fill: #fff">
                                <path d="M144.7 98.7c-21 34.1-33.1 74.3-33.1 117.3c0 98 62.8 181.4 150.4 211.7c-12.4 2.8-25.3 4.3-38.6 4.3C126.6 432 48 353.3 48 256c0-68.9 39.4-128.4 96.8-157.3zm62.1-66C91.1 41.2 0 137.9 0 256C0 379.7 100 480 223.5 480c47.8 0 92-15 128.4-40.6c1.9-1.3 3.7-2.7 5.5-4c4.8-3.6 9.4-7.4 13.9-11.4c2.7-2.4 5.3-4.8 7.9-7.3c5-4.9 6.3-12.5 3.1-18.7s-10.1-9.7-17-8.5c-3.7 .6-7.4 1.2-11.1 1.6c-5 .5-10.1 .9-15.3 1c-1.2 0-2.5 0-3.7 0c-.1 0-.2 0-.3 0c-96.8-.2-175.2-78.9-175.2-176c0-54.8 24.9-103.7 64.1-136c1-.9 2.1-1.7 3.2-2.6c4-3.2 8.2-6.2 12.5-9c3.1-2 6.3-4 9.6-5.8c6.1-3.5 9.2-10.5 7.7-17.3s-7.3-11.9-14.3-12.5c-3.6-.3-7.1-.5-10.7-.6c-2.7-.1-5.5-.1-8.2-.1c-3.3 0-6.5 .1-9.8 .2c-2.3 .1-4.6 .2-6.9 .4z"></path>
                            </svg>
                        </label>
                    </div>
                    <form class="d-flex header__form-search" action="" method="GET">
                        <input class="form-control search-story" type="text" placeholder="Tìm kiếm" name="key_word" value="" />
                        <div class="col-12 search-result shadow no-result d-none">
                            <div class="card text-white bg-light">
                                <div class="card-body p-0">
                                    <ul class="list-group list-group-flush d-none">
                                        <li class="list-group-item">
                                            <a href="#" class="text-dark hover-title">Tự cẩm</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <button class="btn" type="submit">
                            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                                <path d="M144.7 98.7c-21 34.1-33.1 74.3-33.1 117.3c0 98 62.8 181.4 150.4 211.7c-12.4 2.8-25.3 4.3-38.6 4.3C126.6 432 48 353.3 48 256c0-68.9 39.4-128.4 96.8-157.3zm62.1-66C91.1 41.2 0 137.9 0 256C0 379.7 100 480 223.5 480c47.8 0 92-15 128.4-40.6c1.9-1.3 3.7-2.7 5.5-4c4.8-3.6 9.4-7.4 13.9-11.4c2.7-2.4 5.3-4.8 7.9-7.3c5-4.9 6.3-12.5 3.1-18.7s-10.1-9.7-17-8.5c-3.7 .6-7.4 1.2-11.1 1.6c-5 .5-10.1 .9-15.3 1c-1.2 0-2.5 0-3.7 0c-.1 0-.2 0-.3 0c-96.8-.2-175.2-78.9-175.2-176c0-54.8 24.9-103.7 64.1-136c1-.9 2.1-1.7 3.2-2.6c4-3.2 8.2-6.2 12.5-9c3.1-2 6.3-4 9.6-5.8c6.1-3.5 9.2-10.5 7.7-17.3s-7.3-11.9-14.3-12.5c-3.6-.3-7.1-.5-10.7-.6c-2.7-.1-5.5-.1-8.2-.1c-3.3 0-6.5 .1-9.8 .2c-2.3 .1-4.6 .2-6.9 .4z"></path>
                            </svg>
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </nav>
    <!-- End nav -->
</div>

<div class="bg-light header-bottom">
    <div class="container py-1">
        <p class="mb-0">Đọc sách online. Tổng hợp đầy đủ và cập nhật liên tục.</p>
    </div>
</div>

<!-- Nội dung chính -->
<main>
  <!-- Thư viện sách -->
  <div class="section-stories-hot mb-3">
    <div class="container">
      <div class="row">
        <div class="head-title-global d-flex justify-content-between mb-2">
          <div class="col-6 col-md-4 col-lg-4 head-title-global__left d-flex align-items-center">
            <h2 class="me-2 mb-0 border-bottom border-secondary pb-1">
              <a href="#" class="d-block text-decoration-none text-dark fs-4 story-name" title="Sách Hot">Thư viện sách</a>
            </h2>
            <i class="fa-solid fa-fire-flame-curved"></i>
          </div>
          <div class="col-4 col-md-3 col-lg-2">
            <select class="form-select select-stories-hot" aria-label="Truyen hot" onchange="window.location.href='${pageContext.request.contextPath}/home?genreId=' + this.value">
              <option value="" ${empty selectedGenreId ? 'selected' : ''}>Tất cả</option>
              <c:forEach var="genre" items="${genres}">
                <option value="${genre.genreid}" ${genre.genreid == selectedGenreId ? 'selected' : ''}>${genre.genreName}</option>
              </c:forEach>
            </select>
          </div>
        </div>
      </div>
      
      <!-- Danh sách sách -->
      <div class="row">
        <div class="col-12">
          <div class="section-stories-hot__list">
            <c:forEach var="book" items="${hotBooks}">
              <div class="story-item">
                <a href="${pageContext.request.contextPath}/story?id=${book.bookid}" class="d-block text-decoration-none">
                  <div class="story-item__image">
                    <c:choose>
                      <c:when test="${book.imagesbook != null && book.imagesbook.startsWith('https')}">
                        <img src="${book.imagesbook}" alt="${book.title}" class="img-fluid" width="150" height="230" loading="lazy" />
                      </c:when>
                      <c:otherwise>
                        <c:url value="/image?fname=${book.imagesbook}" var="imgUrl" />
                        <img src="${imgUrl}" alt="${book.title}" class="img-fluid" width="150" height="230" loading="lazy" />
                      </c:otherwise>
                    </c:choose>
                  </div>
                  <h3 class="story-item__name text-one-row story-name">${book.title}</h3>
                  <div class="list-badge">
                    <span class="story-item__badge story-item__badge-hot badge text-bg-danger">Hot</span>
                    <c:if test="${book.isNew}">
                      <span class="story-item__badge story-item__badge-new badge text-bg-info text-light">New</span>
                    </c:if>
                  </div>
                </a>
              </div>
            </c:forEach>
          </div>
          <!-- Skeleton loading -->
          <div class="section-stories-hot__list wrapper-skeleton d-none">
            <c:forEach var="book" items="${books}">
              <div class="skeleton" style="max-width: 150px; width: 100%; height: 230px"></div>
            </c:forEach>
          </div>
        </div>
      </div>
    </div>
  </div>
  
  <!-- Sách mới -->
  <div class="container">
    <div class="row align-items-start">
      <div class="col-12 col-md-8 col-lg-9">
        <div class="section-stories-new mb-3">
          <div class="row">
            <div class="head-title-global d-flex justify-content-between mb-2">
              <div class="col-6 col-md-4 col-lg-4 head-title-global__left d-flex align-items-center">
                <h2 class="me-2 mb-0 border-bottom border-secondary pb-1">
                  <a href="#" class="d-block text-decoration-none text-dark fs-4 story-name" title="Sách Mới">Sách Mới</a>
                </h2>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-12">
              <div class="section-stories-new__list">
                <c:forEach var="book" items="${newBooks}">
                  <c:if test="${book.isNew}">
                    <div class="story-item-no-image">
                      <div class="story-item-no-image__name d-flex align-items-center">
                        <h3 class="me-1 mb-0 d-flex align-items-center">
                          <svg style="width: 10px; margin-right: 5px" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 320 512">
                            <path d="M278.6 233.4c12.5 12.5 12.5 32.8 0 45.3l-160 160c-12.5 12.5-32.8 12.5-45.3 0s-12.5-32.8 0-45.3L210.7 256 73.4 118.6c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0l160 160z"></path>
                          </svg>
                          <a href="${pageContext.request.contextPath}/story?id=${book.bookid}" class="text-decoration-none text-dark fs-6 hover-title text-one-row story-name">${book.title}</a>
                        </h3>
                        <span class="badge text-bg-info text-light me-1">New</span>
                      </div>
                      <div class="story-item-no-image__chapters ms-2">
                        <a href="${pageContext.request.contextPath}/story?id=${book.bookid}" class="hover-title text-decoration-none text-info">Ngày cập nhật ${book.getCreatedat()}</a>
                      </div>
                    </div>
                  </c:if>
                </c:forEach>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      <!-- Danh sách thể loại -->
      <div class="col-12 col-md-4 col-lg-3 sticky-md-top">
        <div class="row">
          <div class="col-12">
            <div class="section-list-category bg-light p-2 rounded card-custom">
              <div class="head-title-global mb-2">
                <div class="col-12 col-md-12 head-title-global__left">
                  <h2 class="mb-0 border-bottom border-secondary pb-1">
                    <span class="d-block text-decoration-none text-dark fs-4" title="Danh sách thể loại">Danh sách thể loại</span>
                  </h2>
                </div>
              </div>
              <div class="row">
                <ul class="list-category">
                  <c:forEach var="genre" items="${genres}">
                    <li>
                      <a class="text-decoration-none text-dark hover-title" href="${pageContext.request.contextPath}/genreDetails?genreId=${genre.genreid}">
                        ${genre.genreName}
                      </a>
                    </li>
                  </c:forEach>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</main>
<!-- Kết thúc nội dung chính -->
