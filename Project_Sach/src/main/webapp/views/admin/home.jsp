<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>
<div id="layoutSidenav_content">
	<main>
		<div class="container-fluid px-4">
			<div class="card mb-4">
				<div class="card-header">

					<strong style="font-size: 20px;">DANH SÁCH CÁC BỘ SÁCH ĐÃ
						ĐƯỢC DUYỆT</strong>
				</div>
				<div class="card-body">
					<table id="datatablesSimple">
						<thead>
							<tr>
								<th>Tên sách</th>
								<th>Tác giả</th>
								<th>Nội dung</th>
								<th>Ngày đăng</th>
								<th>Hình ảnh bìa sách</th>
								<th>Chức năng</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>Tên sách</th>
								<th>Tác giả</th>
								<th>Nội dung</th>
								<th>Ngày đăng</th>
								<th>Hình ảnh bìa sách</th>
								<th>Chức năng</th>
							</tr>
						</tfoot>
						<tbody>
							<c:forEach var="book" items="${books}">
								<tr>
									<td>${book.title}</td>
									<td>${book.authorname}</td>
									<td><a
										href="${pageContext.request.contextPath}/admin/read?id=${book.bookid}&pdfName=${book.content}">${book.content}
									</a></td>
									<td>${book.createdat}</td>
									<td>
										<!-- Nếu đường dẫn ảnh là từ URL (bắt đầu bằng https) --> <c:if
											test="${book.imagesbook != null && book.imagesbook.startsWith('https')}">
											<img src="${book.imagesbook}" alt="${book.title}"
												class="img-fluid w-100" width="50" height="100"
												loading="lazy" />
										</c:if> <!-- Nếu đường dẫn ảnh là từ thư mục cục bộ (không bắt đầu bằng https) -->
										<c:if
											test="${book.imagesbook != null && !book.imagesbook.startsWith('https')}">
											<c:url value="/image?fname=${book.imagesbook}" var="imgUrl"></c:url>
											<img src="${imgUrl}" alt="${book.title}"
												class="img-fluid w-100" width="50" height="100"
												loading="lazy" />
										</c:if>
									</td>
									<td><a
										href="${pageContext.request.contextPath }/admin/edit?id=${book.bookid}"
										class="btn btn-primary btn-sm"> <i class="fas fa-edit"></i>
									</a> <a
										href="${pageContext.request.contextPath }/admin/delete?id=${book.bookid}"
										class="btn btn-danger btn-sm"> <i class="fas fa-trash"></i>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</main>