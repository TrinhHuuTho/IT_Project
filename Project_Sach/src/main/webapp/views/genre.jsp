<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="/commons/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>

<main>
  <div class="container">
    <div class="row align-items-start">
      <div class="col-12 col-md-8 col-lg-9 mb-3">
        <div class="head-title-global d-flex justify-content-between mb-2">
          <div
            class="col-12 col-md-12 col-lg-12 head-title-global__left d-flex"
          >
            <h2 class="me-2 mb-0 border-bottom border-secondary pb-1">
              <span
                href="#"
                class="d-block text-decoration-none text-dark fs-4 category-name"
                title="Ngôn Tình"
                >${genre.genreName}</span
              >
            </h2>
          </div>
        </div>

        <div class="list-story-in-category section-stories-hot__list">
          <c:forEach var="book" items="${books}">
            <div class="story-item">
              <!-- Sửa href để đảm bảo có đường dẫn chính xác cho mỗi sách -->
              <a
                href="${pageContext.request.contextPath}/story?id=${book.bookid}"
                class="d-block text-decoration-none"
              >
                <div class="story-item__image">
                  <!-- Nếu đường dẫn ảnh là từ URL (bắt đầu bằng https) -->
                  <c:if
                    test="${book.imagesbook != null && book.imagesbook.startsWith('https')}"
                  >
                    <img
                      src="${book.imagesbook}"
                      alt="${book.title}"
                      class="img-fluid"
                      width="150"
                      height="230"
                      loading="lazy"
                    />
                  </c:if>

                  <!-- Nếu đường dẫn ảnh là từ thư mục cục bộ (không bắt đầu bằng https) -->
                  <c:if
                    test="${book.imagesbook != null && !book.imagesbook.startsWith('https')}"
                  >
                    <c:url
                      value="/image?fname=${book.imagesbook}"
                      var="imgUrl"
                    ></c:url>
                    <img
                      src="${imgUrl}"
                      alt="${book.title}"
                      class="img-fluid"
                      width="150"
                      height="230"
                      loading="lazy"
                    />
                  </c:if>
                </div>
                <h3 class="story-item__name text-one-row story-name">
                  ${book.title}
                </h3>

             	<div class="list-badge">
				    <!-- Hiển thị nhãn Hot nếu sách đáp ứng điều kiện -->
				    <c:if test="${book.isHot}">
				        <span class="story-item__badge story-item__badge-hot badge text-bg-danger">Hot</span>
				    </c:if>
				    <!-- Hiển thị nhãn New nếu sách mới -->
				    <c:if test="${book.isNew}">
				        <span class="story-item__badge story-item__badge-new badge text-bg-info text-light">New</span>
				    </c:if>
				</div>
                
              </a>
            </div>
          </c:forEach>
        </div>
      </div>

      <div class="col-12 col-md-4 col-lg-3 sticky-md-top">
        <div class="category-description bg-light p-2 rounded mb-3 card-custom">
          <p class="mb-0 text-secondary"></p>
          <p>${genre.describeGenre}</p>
          <p></p>
        </div>
      </div>
    </div>
  </div>
</main>
