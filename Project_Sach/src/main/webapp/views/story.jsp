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
                  <div class="rate-story__holder" data-score="7.0">
                    <img alt="1" src="${URL}assets/images/star-on.png" />

                    <img alt="2" src="${URL}assets/images/star-on.png" />

                    <img alt="3" src="${URL}assets/images/star-on.png" />

                    <img alt="4" src="${URL}assets/images/star-on.png" />

                    <img alt="5" src="${URL}assets/images/star-on.png" />

                    <img alt="6" src="${URL}assets/images/star-on.png" />

                    <img alt="7" src="${URL}assets/images/star-half.png" />

                    <img alt="8" src="${URL}assets/images/star-off.png" />

                    <img alt="9" src="${URL}assets/images/star-off.png" />

                    <img alt="10" src="${URL}assets/images/star-off.png" />
                  </div>
                  <em class="rate-story__text"></em>
                  <div
                    class="rate-story__value"
                    itemprop="aggregateRating"
                    itemscope=""
                    itemtype="https://schema.org/AggregateRating"
                  >
                    <em
                      >Đánh giá:
                      <strong>
                        <span itemprop="ratingValue">7.0</span>
                      </strong>
                      /
                      <span class="" itemprop="bestRating">10</span>
                      từ
                      <strong>
                        <span itemprop="ratingCount">415</span>
                        lượt
                      </strong>
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
                  <div class="d-flex align-items-center flex-warp">
                    <a
                      href="#"
                      class="text-decoration-none text-dark hover-title me-1"
                      style="width: max-content"
                      >Ngôn Tình ,
                    </a>

                    <a
                      href="#"
                      class="text-decoration-none text-dark hover-title"
                      style="width: max-content"
                      >Khác
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- Nút Đọc sách -->
          <a
            href="${pageContext.request.contextPath}/read?id=${book.bookid}&pdfName=${book.content}"
            class="btn btn-primary"
            >Đọc sách</a
          >
        </div>
      </div>

      <div class="col-12 col-md-5 col-lg-4 sticky-md-top">
        <div class="row top-ratings">
          <div class="col-12 top-ratings__tab mb-2">
            <div
              class="list-group d-flex flex-row"
              id="list-tab"
              role="tablist"
            >
              <a
                class="list-group-item list-group-item-action active"
                id="top-day-list"
                data-bs-toggle="list"
                href="https://suustore.com/truyen/nang-khong-muon-lam-hoang-hau#top-day"
                role="tab"
                aria-controls="top-day"
                aria-selected="true"
                >Tháng</a
              >
              <a
                class="list-group-item list-group-item-action"
                id="top-month-list"
                data-bs-toggle="list"
                href="https://suustore.com/truyen/nang-khong-muon-lam-hoang-hau#top-month"
                role="tab"
                aria-controls="top-month"
                aria-selected="false"
                tabindex="-1"
                >Năm</a
              >
              <a
                class="list-group-item list-group-item-action"
                id="top-all-time-list"
                data-bs-toggle="list"
                href="https://suustore.com/truyen/nang-khong-muon-lam-hoang-hau#top-all-time"
                role="tab"
                aria-controls="top-all-time"
                aria-selected="false"
                tabindex="-1"
                >Bất hủ</a
              >
            </div>
          </div>
          <div class="col-12 top-ratings__content">
            <div class="tab-content" id="nav-tabContent">
              <div
                class="tab-pane fade show active"
                id="top-day"
                role="tabpanel"
                aria-labelledby="top-day-list"
              >
                <ul>
                  <li>
                    <div class="rating-item d-flex align-items-center">
                      <div class="rating-item__number bg-danger rounded-circle">
                        <span class="text-light">1</span>
                      </div>
                      <div class="rating-item__story">
                        <a
                          href="https://suustore.com/truyen/linh-vu-thien-ha"
                          class="text-decoration-none hover-title rating-item__story--name text-one-row"
                          >Linh Vũ Thiên Hạ</a
                        >
                        <div
                          class="d-flex flex-wrap rating-item__story--categories"
                        >
                          <a
                            href="https://suustore.com/the-loai/tien-hiep"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Tiên Hiệp ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/di-gioi"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Dị Giới ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/huyen-huyen"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Huyền Huyễn ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/xuyen-khong"
                            class="text-decoration-none text-dark hover-title"
                            >Xuyên Không
                          </a>
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="rating-item d-flex align-items-center">
                      <div
                        class="rating-item__number bg-success rounded-circle"
                      >
                        <span class="text-light">2</span>
                      </div>
                      <div class="rating-item__story">
                        <a
                          href="https://suustore.com/truyen/than-dao-dan-ton"
                          class="text-decoration-none hover-title rating-item__story--name text-one-row"
                          >Thần Đạo Đan Tôn</a
                        >
                        <div
                          class="d-flex flex-wrap rating-item__story--categories"
                        >
                          <a
                            href="https://suustore.com/the-loai/tien-hiep"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Tiên Hiệp ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/huyen-huyen"
                            class="text-decoration-none text-dark hover-title"
                            >Huyền Huyễn
                          </a>
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="rating-item d-flex align-items-center">
                      <div class="rating-item__number bg-info rounded-circle">
                        <span class="text-light">3</span>
                      </div>
                      <div class="rating-item__story">
                        <a
                          href="https://suustore.com/truyen/me-vo-khong-loi-ve"
                          class="text-decoration-none hover-title rating-item__story--name text-one-row"
                          >Mê Vợ Không Lối Về</a
                        >
                        <div
                          class="d-flex flex-wrap rating-item__story--categories"
                        >
                          <a
                            href="https://suustore.com/the-loai/ngon-tinh"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Ngôn Tình ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/nguoc"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Ngược ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/sung"
                            class="text-decoration-none text-dark hover-title"
                            >Sủng
                          </a>
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="rating-item d-flex align-items-center">
                      <div
                        class="rating-item__number bg-light border rounded-circle"
                      >
                        <span class="text-dark">4</span>
                      </div>
                      <div class="rating-item__story">
                        <a
                          href="https://suustore.com/truyen/dau-pha-thuong-khung"
                          class="text-decoration-none hover-title rating-item__story--name text-one-row"
                          >Đấu Phá Thương Khung</a
                        >
                        <div
                          class="d-flex flex-wrap rating-item__story--categories"
                        >
                          <a
                            href="https://suustore.com/the-loai/tien-hiep"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Tiên Hiệp ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/di-gioi"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Dị Giới ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/huyen-huyen"
                            class="text-decoration-none text-dark hover-title"
                            >Huyền Huyễn
                          </a>
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="rating-item d-flex align-items-center">
                      <div
                        class="rating-item__number bg-light border rounded-circle"
                      >
                        <span class="text-dark">5</span>
                      </div>
                      <div class="rating-item__story">
                        <a
                          href="https://suustore.com/truyen/the-gioi-hoan-my"
                          class="text-decoration-none hover-title rating-item__story--name text-one-row"
                          >Thế Giới Hoàn Mỹ</a
                        >
                        <div
                          class="d-flex flex-wrap rating-item__story--categories"
                        >
                          <a
                            href="https://suustore.com/the-loai/tien-hiep"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Tiên Hiệp ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/kiem-hiep"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Kiếm Hiệp ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/huyen-huyen"
                            class="text-decoration-none text-dark hover-title"
                            >Huyền Huyễn
                          </a>
                        </div>
                      </div>
                    </div>
                  </li>
                </ul>
              </div>

              <div
                class="tab-pane fade"
                id="top-month"
                role="tabpanel"
                aria-labelledby="top-month-list"
              >
                <ul>
                  <li>
                    <div class="rating-item d-flex align-items-center">
                      <div class="rating-item__number bg-danger rounded-circle">
                        <span class="text-light">1</span>
                      </div>
                      <div class="rating-item__story">
                        <a
                          href="https://suustore.com/truyen/linh-vu-thien-ha"
                          class="text-decoration-none hover-title rating-item__story--name text-one-row"
                          >Linh Vũ Thiên Hạ</a
                        >
                        <div
                          class="d-flex flex-wrap rating-item__story--categories"
                        >
                          <a
                            href="https://suustore.com/the-loai/tien-hiep"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Tiên Hiệp ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/di-gioi"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Dị Giới ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/huyen-huyen"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Huyền Huyễn ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/xuyen-khong"
                            class="text-decoration-none text-dark hover-title"
                            >Xuyên Không
                          </a>
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="rating-item d-flex align-items-center">
                      <div
                        class="rating-item__number bg-success rounded-circle"
                      >
                        <span class="text-light">2</span>
                      </div>
                      <div class="rating-item__story">
                        <a
                          href="https://suustore.com/truyen/than-dao-dan-ton"
                          class="text-decoration-none hover-title rating-item__story--name text-one-row"
                          >Thần Đạo Đan Tôn</a
                        >
                        <div
                          class="d-flex flex-wrap rating-item__story--categories"
                        >
                          <a
                            href="https://suustore.com/the-loai/tien-hiep"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Tiên Hiệp ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/huyen-huyen"
                            class="text-decoration-none text-dark hover-title"
                            >Huyền Huyễn
                          </a>
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="rating-item d-flex align-items-center">
                      <div class="rating-item__number bg-info rounded-circle">
                        <span class="text-light">3</span>
                      </div>
                      <div class="rating-item__story">
                        <a
                          href="https://suustore.com/truyen/me-vo-khong-loi-ve"
                          class="text-decoration-none hover-title rating-item__story--name text-one-row"
                          >Mê Vợ Không Lối Về</a
                        >
                        <div
                          class="d-flex flex-wrap rating-item__story--categories"
                        >
                          <a
                            href="https://suustore.com/the-loai/ngon-tinh"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Ngôn Tình ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/nguoc"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Ngược ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/sung"
                            class="text-decoration-none text-dark hover-title"
                            >Sủng
                          </a>
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="rating-item d-flex align-items-center">
                      <div
                        class="rating-item__number bg-light border rounded-circle"
                      >
                        <span class="text-dark">4</span>
                      </div>
                      <div class="rating-item__story">
                        <a
                          href="https://suustore.com/truyen/dau-pha-thuong-khung"
                          class="text-decoration-none hover-title rating-item__story--name text-one-row"
                          >Đấu Phá Thương Khung</a
                        >
                        <div
                          class="d-flex flex-wrap rating-item__story--categories"
                        >
                          <a
                            href="https://suustore.com/the-loai/tien-hiep"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Tiên Hiệp ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/di-gioi"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Dị Giới ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/huyen-huyen"
                            class="text-decoration-none text-dark hover-title"
                            >Huyền Huyễn
                          </a>
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="rating-item d-flex align-items-center">
                      <div
                        class="rating-item__number bg-light border rounded-circle"
                      >
                        <span class="text-dark">5</span>
                      </div>
                      <div class="rating-item__story">
                        <a
                          href="https://suustore.com/truyen/the-gioi-hoan-my"
                          class="text-decoration-none hover-title rating-item__story--name text-one-row"
                          >Thế Giới Hoàn Mỹ</a
                        >
                        <div
                          class="d-flex flex-wrap rating-item__story--categories"
                        >
                          <a
                            href="https://suustore.com/the-loai/tien-hiep"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Tiên Hiệp ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/kiem-hiep"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Kiếm Hiệp ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/huyen-huyen"
                            class="text-decoration-none text-dark hover-title"
                            >Huyền Huyễn
                          </a>
                        </div>
                      </div>
                    </div>
                  </li>
                </ul>
              </div>

              <div
                class="tab-pane fade"
                id="top-all-time"
                role="tabpanel"
                aria-labelledby="top-all-time-list"
              >
                <ul>
                  <li>
                    <div class="rating-item d-flex align-items-center">
                      <div class="rating-item__number bg-danger rounded-circle">
                        <span class="text-light">1</span>
                      </div>
                      <div class="rating-item__story">
                        <a
                          href="https://suustore.com/truyen/linh-vu-thien-ha"
                          class="text-decoration-none hover-title rating-item__story--name text-one-row"
                          >Linh Vũ Thiên Hạ</a
                        >
                        <div
                          class="d-flex flex-wrap rating-item__story--categories"
                        >
                          <a
                            href="https://suustore.com/the-loai/tien-hiep"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Tiên Hiệp ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/di-gioi"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Dị Giới ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/huyen-huyen"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Huyền Huyễn ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/xuyen-khong"
                            class="text-decoration-none text-dark hover-title"
                            >Xuyên Không
                          </a>
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="rating-item d-flex align-items-center">
                      <div
                        class="rating-item__number bg-success rounded-circle"
                      >
                        <span class="text-light">2</span>
                      </div>
                      <div class="rating-item__story">
                        <a
                          href="https://suustore.com/truyen/than-dao-dan-ton"
                          class="text-decoration-none hover-title rating-item__story--name text-one-row"
                          >Thần Đạo Đan Tôn</a
                        >
                        <div
                          class="d-flex flex-wrap rating-item__story--categories"
                        >
                          <a
                            href="https://suustore.com/the-loai/tien-hiep"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Tiên Hiệp ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/huyen-huyen"
                            class="text-decoration-none text-dark hover-title"
                            >Huyền Huyễn
                          </a>
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="rating-item d-flex align-items-center">
                      <div class="rating-item__number bg-info rounded-circle">
                        <span class="text-light">3</span>
                      </div>
                      <div class="rating-item__story">
                        <a
                          href="https://suustore.com/truyen/dau-pha-thuong-khung"
                          class="text-decoration-none hover-title rating-item__story--name text-one-row"
                          >Đấu Phá Thương Khung</a
                        >
                        <div
                          class="d-flex flex-wrap rating-item__story--categories"
                        >
                          <a
                            href="https://suustore.com/the-loai/tien-hiep"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Tiên Hiệp ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/di-gioi"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Dị Giới ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/huyen-huyen"
                            class="text-decoration-none text-dark hover-title"
                            >Huyền Huyễn
                          </a>
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="rating-item d-flex align-items-center">
                      <div
                        class="rating-item__number bg-light border rounded-circle"
                      >
                        <span class="text-dark">4</span>
                      </div>
                      <div class="rating-item__story">
                        <a
                          href="https://suustore.com/truyen/co-vo-ngot-ngao-co-chut-bat-luong-vo-moi-bat-luong-co-chut-ngot"
                          class="text-decoration-none hover-title rating-item__story--name text-one-row"
                          >Cô Vợ Ngọt Ngào Có Chút Bất Lương (Vợ Mới Bất Lương
                          Có Chút Ngọt)</a
                        >
                        <div
                          class="d-flex flex-wrap rating-item__story--categories"
                        >
                          <a
                            href="https://suustore.com/the-loai/ngon-tinh"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Ngôn Tình ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/trong-sinh"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Trọng Sinh ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/sung"
                            class="text-decoration-none text-dark hover-title"
                            >Sủng
                          </a>
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="rating-item d-flex align-items-center">
                      <div
                        class="rating-item__number bg-light border rounded-circle"
                      >
                        <span class="text-dark">5</span>
                      </div>
                      <div class="rating-item__story">
                        <a
                          href="https://suustore.com/truyen/pham-nhan-tu-tien"
                          class="text-decoration-none hover-title rating-item__story--name text-one-row"
                          >Phàm Nhân Tu Tiên</a
                        >
                        <div
                          class="d-flex flex-wrap rating-item__story--categories"
                        >
                          <a
                            href="https://suustore.com/the-loai/tien-hiep"
                            class="text-decoration-none text-dark hover-title me-1"
                            >Tiên Hiệp ,
                          </a>
                          <a
                            href="https://suustore.com/the-loai/kiem-hiep"
                            class="text-decoration-none text-dark hover-title"
                            >Kiếm Hiệp
                          </a>
                        </div>
                      </div>
                    </div>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</main>
