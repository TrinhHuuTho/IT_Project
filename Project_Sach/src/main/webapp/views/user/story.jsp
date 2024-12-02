<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="/commons/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>

<main>
  <input type="hidden" id="story_slug" value="${book.title}" />
  <div class="container">
    <div class="row align-items-start">
      <div class="col-12 col-md-7 col-lg-8">
        <div class="head-title-global d-flex justify-content-between mb-4">
          <div class="col-12 col-md-12 col-lg-4 head-title-global__left d-flex">
            <h2 class="me-2 mb-0 border-bottom border-secondary pb-1">
              <span
                class="d-block text-decoration-none text-dark fs-4 title-head-name"
                title="Thông tin truyện"
                >Thông tin sách</span
              >
            </h2>
          </div>
        </div>

        <div class="story-detail">
          <div class="story-detail__top d-flex align-items-start">
            <div class="row align-items-start">
              <div class="col-12 col-md-12 col-lg-3 story-detail__top--image">
                <div class="book-3d">
                  <!-- Nếu đường dẫn ảnh là từ URL (bắt đầu bằng https) -->
                  <c:if
                    test="${book.imagesbook != null && book.imagesbook.startsWith('https')}"
                  >
                    <img
                      src="${book.imagesbook}"
                      alt="${book.title}"
                      class="img-fluid w-100"
                      width="200"
                      height="300"
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
                      class="img-fluid w-100"
                      width="200"
                      height="300"
                      loading="lazy"
                    />
                  </c:if>
                </div>
              </div>
              <div class="col-12 col-md-12 col-lg-9">
                <h3 class="text-center story-name">${book.title}</h3>
                <div class="rate-story mb-2">
                  <c:url
                    value="/user/review?bookId=${book.bookid}"
                    var="ratingUrl"
                  ></c:url>

                  <div class="rate-story__holder" data-score="${averageRating}">
                    <c:forEach begin="1" end="5" var="i">
                      <c:choose>
                        <c:when test="${i <= averageRating}">
                          <img
                            alt="${i}"
                            src="${URL}assets/images/star-on.png"
                          />
                        </c:when>

                        <c:when test="${i - averageRating <= 0.5}">
                          <img
                            alt="${i}"
                            src="${URL}assets/images/star-half.png"
                          />
                        </c:when>

                        <c:otherwise>
                          <img
                            alt="${i}"
                            src="${URL}assets/images/star-off.png"
                          />
                        </c:otherwise>
                      </c:choose>
                    </c:forEach>
                  </div>
                  <div class="rate-story__value">
                    <em>
                      Đánh giá: <strong>${averageRating}</strong> /
                      <span>5</span> từ <strong>${totalReviews} lượt</strong>
                    </em>
                  </div>
                </div>

                <div
                  class="story-detail__top--desc px-3"
                  style="max-height: 285px; overflow: hidden"
                >
                  Phần mô tả hiện đang được cập nhật. Chúng tôi sẽ sớm mang đến
                  thông tin chi tiết nhất cho bạn. Cảm ơn bạn đã kiên nhẫn chờ
                  đợi!
                </div>

                <div class="info-more">
                  <div class="info-more--more active" id="info_more">
                    <span class="me-1 text-dark">Xem thêm</span>
                    <svg
                      width="14"
                      height="8"
                      viewBox="0 0 14 8"
                      fill="none"
                      xmlns="http://www.w3.org/2000/svg"
                    >
                      <path
                        d="M7.70749 7.70718L13.7059 1.71002C14.336 1.08008 13.8899 0.00283241 12.9989 0.00283241L1.002 0.00283241C0.111048 0.00283241 -0.335095 1.08008 0.294974 1.71002L6.29343 7.70718C6.68394 8.09761 7.31699 8.09761 7.70749 7.70718Z"
                        fill="#2C2C37"
                      ></path>
                    </svg>
                  </div>

                  <a class="info-more--collapse text-decoration-none">
                    <span class="me-1 text-dark">Thu gọn</span>
                    <svg
                      width="14"
                      height="8"
                      viewBox="0 0 14 8"
                      fill="none"
                      xmlns="http://www.w3.org/2000/svg"
                    >
                      <path
                        d="M7.70749 0.292817L13.7059 6.28998C14.336 6.91992 13.8899 7.99717 12.9989 7.99717L1.002 7.99717C0.111048 7.99717 -0.335095 6.91992 0.294974 6.28998L6.29343 0.292817C6.68394 -0.097606 7.31699 -0.0976055 7.70749 0.292817Z"
                        fill="#2C2C37"
                      ></path>
                    </svg>
                  </a>
                </div>
              </div>
            </div>
          </div>

          <div class="story-detail__bottom mb-3">
            <div class="row">
              <div class="col-12 col-md-12 col-lg-3 story-detail__bottom--info">
                <br />
                <p class="mb-1">
                  <strong>Tác giả:</strong>
                  <a href="#" class="text-decoration-none text-dark hover-title"
                    >${book.authorname}</a
                  >
                </p>
                <div class="d-flex align-items-center mb-1 flex-wrap">
                  <strong class="me-1">Thể loại:</strong>
                  <div class="d-flex align-items-center flex-wrap">
                    <c:forEach var="genre" items="${genres}">
                      <a
                        href="${pageContext.request.contextPath}/user/genreDetails?genreId=${genre.genreid}"
                        class="text-decoration-none text-dark hover-title me-1"
                        style="width: max-content"
                      >
                        ${genre.genreName}
                      </a>
                    </c:forEach>
                  </div>
                </div>
                <p class="mb-1">
                  <strong>Số trang:</strong>
                  <a
                    href="#"
                    class="text-decoration-none text-dark hover-title"
                  ></a>
                </p>
              </div>
            </div>
          </div>

		
          <div class="story-detail__list-chapter">
            <div class="head-title-global d-flex justify-content-between mb-4">
              <div
                class="col-6 col-md-12 col-lg-4 head-title-global__left d-flex"
              >
                <h2 class="me-2 mb-0 border-bottom border-secondary pb-1">
                  <span
                    href="#"
                    class="d-block text-decoration-none text-dark fs-4 title-head-name"
                    title="Truyện hot"
                    >Danh sách chương</span
                  >
                </h2>
              </div>
            </div>
            <div class="story-detail__list-chapter--list">
              <div class="row">
                <div
                  class="col-12 col-sm-6 col-lg-6 story-detail__list-chapter--list__item"
                >
                <!--
                  <ul>
                    <li>
                      <a
                        href="#"
                        class="text-decoration-none text-dark hover-title"
                        >Chương 1: Nàng không tin Yến Đình lại lừa nàng chuyện
                        lớn đến vậy!</a
                      >
                    </li>
                  </ul>
                -->
                </div>
                <div
                  class="col-12 col-sm-6 col-lg-6 story-detail__list-chapter--list__item"
                >
                <!--
                  <ul>
                    <li>
                      <a
                        href="#"
                        class="text-decoration-none text-dark hover-title"
                        >Chương 26: Mỹ nhân!</a
                      >
                    </li>
                  </ul>
                -->
                </div>
              </div>
            </div>
          </div>

          <div class="pagination" style="justify-content: center">
            <ul>
              <!--
              <li class="pagination__item page-current">
                <a
                  class="page-link story-ajax-paginate"
                  data-url="https://suustore.com/truyen/nang-khong-muon-lam-hoang-hau?page=1"
                  style="cursor: pointer"
                  >1</a
                >
              </li>
              <li class="pagination__item">
                <a
                  class="page-link story-ajax-paginate"
                  data-url="https://suustore.com/truyen/nang-khong-muon-lam-hoang-hau?page=2"
                  style="cursor: pointer"
                  >2</a
                >
              </li>

              <div class="dropup-center dropup choose-paginate me-1">
                <button
                  class="btn btn-success dropdown-toggle"
                  type="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  Chọn chương
                </button>
                <div class="dropdown-menu">
                  <input
                    type="number"
                    class="form-control input-paginate me-1"
                    value=""
                  />
                  <button class="btn btn-success btn-go-paginate">Đi</button>
                </div>
              </div>

              <li class="pagination__arrow pagination__item">
                <a
                  data-url="https://suustore.com/truyen/nang-khong-muon-lam-hoang-hau?page=2"
                  style="cursor: pointer"
                  class="text-decoration-none w-100 h-100 d-flex justify-content-center align-items-center story-ajax-paginate"
                >
                  &gt;&gt;
                </a>
              </li>
              -->
              <!-- Nút Đọc sách -->
              <a
                href="${pageContext.request.contextPath}/read?id=${book.bookid}&pdfName=${book.content}"
                class="btn btn-primary"
                >Đọc sách</a
              >
            </ul>
          </div>
        </div>
      </div>

      <div class="col-12 col-md-5 col-lg-4 sticky-md-top">
		<div class="row top-ratings">
		  <div class="col-12 top-ratings__tab mb-2">
			<div class="list-group d-flex flex-row" id="list-tab" role="tablist">
			  <a class="list-group-item list-group-item-action ${timePeriod == 'month' ? 'active' : ''}"
				 id="top-day-list" data-bs-toggle="tab"
				 data-bs-target="#top-day" role="tab"
				 aria-controls="top-day" aria-selected="${timePeriod == 'month'}">Tháng</a>
			  <a class="list-group-item list-group-item-action ${timePeriod == 'year' ? 'active' : ''}"
				 id="top-month-list" data-bs-toggle="tab"
				 data-bs-target="#top-month" role="tab"
				 aria-controls="top-month" aria-selected="${timePeriod == 'year'}" tabindex="-1">Năm</a>
			  <a class="list-group-item list-group-item-action ${timePeriod == 'all' ? 'active' : ''}"
				 id="top-all-time-list" data-bs-toggle="tab"
				 data-bs-target="#top-all-time" role="tab"
				 aria-controls="top-all-time" aria-selected="${timePeriod == 'all'}" tabindex="-1">Bất hủ</a>
			</div>
		  </div>
	  
		  <div class="col-12 top-ratings__content">
			<div class="tab-content" id="nav-tabContent">
			  <!-- Tab "Tháng" -->
			  <div class="tab-pane fade ${timePeriod == 'month' ? 'show active' : ''}" id="top-day" role="tabpanel" aria-labelledby="top-day-list">
				<ul>
				  <!-- Lặp qua danh sách top-rated books của tháng -->
				  <c:forEach var="review" items="${topRatedBooksMonth}" varStatus="status">
					<li>
					  <div class="rating-item d-flex align-items-center">
						<div class="rating-item__number 
						  <c:choose>
							<c:when test="${status.index == 0}">bg-danger</c:when>
							<c:when test="${status.index == 1}">bg-success</c:when>
							<c:when test="${status.index == 2}">bg-info</c:when>
							<c:otherwise>bg-light border</c:otherwise>
						  </c:choose>
						  rounded-circle">
						  <span class="<c:if test='${status.index > 2}'>text-dark</c:if><c:if test='${status.index <= 2}'>text-light</c:if>">
							${status.index + 1}
						  </span>
						</div>
						<div class="rating-item__story">
						  <a href="${pageContext.request.contextPath}/user/story?id=${review.bookId}"
							 class="text-decoration-none hover-title">
							${review.bookTitle}
						  </a>
						  <div class="d-flex flex-wrap rating-item__story--categories">
							<c:forEach var="genre" items="${review.genres}">
							  <a href="#/genreDetails?genreId=${genre}"
								 class="text-decoration-none text-dark"> ${genre}, </a>
							</c:forEach>
						  </div>
						</div>
					  </div>
					</li>
				  </c:forEach>
				</ul>
			  </div>
	  
			  <!-- Tab "Năm" -->
			  <div class="tab-pane fade ${timePeriod == 'year' ? 'show active' : ''}" id="top-month" role="tabpanel" aria-labelledby="top-month-list">
				<ul>
				  <!-- Lặp qua danh sách top-rated books của năm -->
				  <c:forEach var="review" items="${topRatedBooksYear}" varStatus="status">
					<li>
					  <div class="rating-item d-flex align-items-center">
						<div class="rating-item__number 
						  <c:choose>
							<c:when test="${status.index == 0}">bg-danger</c:when>
							<c:when test="${status.index == 1}">bg-success</c:when>
							<c:when test="${status.index == 2}">bg-info</c:when>
							<c:otherwise>bg-light border</c:otherwise>
						  </c:choose>
						  rounded-circle">
						  <span class="<c:if test='${status.index > 2}'>text-dark</c:if><c:if test='${status.index <= 2}'>text-light</c:if>">
							${status.index + 1}
						  </span>
						</div>
						<div class="rating-item__story">
						  <a href="${pageContext.request.contextPath}/user/story?id=${review.bookId}"
							 class="text-decoration-none hover-title">
							${review.bookTitle}
						  </a>
						  <div class="d-flex flex-wrap rating-item__story--categories">
							<c:forEach var="genre" items="${review.genres}">
							  <a href="#/genreDetails?genreId=${genre}"
								 class="text-decoration-none text-dark"> ${genre}, </a>
							</c:forEach>
						  </div>
						</div>
					  </div>
					</li>
				  </c:forEach>
				</ul>
			  </div>
	  
			  <!-- Tab "Bất hủ" -->
			  <div class="tab-pane fade ${timePeriod == 'all' ? 'show active' : ''}" id="top-all-time" role="tabpanel" aria-labelledby="top-all-time-list">
				<ul>
				  <!-- Lặp qua danh sách top-rated books bất hủ -->
				  <c:forEach var="review" items="${topRatedBooksAll}" varStatus="status">
					<li>
					  <div class="rating-item d-flex align-items-center">
						<div class="rating-item__number 
						  <c:choose>
							<c:when test="${status.index == 0}">bg-danger</c:when>
							<c:when test="${status.index == 1}">bg-success</c:when>
							<c:when test="${status.index == 2}">bg-info</c:when>
							<c:otherwise>bg-light border</c:otherwise>
						  </c:choose>
						  rounded-circle">
						  <span class="<c:if test='${status.index > 2}'>text-dark</c:if><c:if test='${status.index <= 2}'>text-light</c:if>">
							${status.index + 1}
						  </span>
						</div>
						<div class="rating-item__story">
						  <a href="${pageContext.request.contextPath}/user/story?id=${review.bookId}"
							 class="text-decoration-none hover-title">
							${review.bookTitle}
						  </a>
						  <div class="d-flex flex-wrap rating-item__story--categories">
							<c:forEach var="genre" items="${review.genres}">
							  <a href="#/genreDetails?genreId=${genre}"
								 class="text-decoration-none text-dark"> ${genre}, </a>
							</c:forEach>
						  </div>
						</div>
					  </div>
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
