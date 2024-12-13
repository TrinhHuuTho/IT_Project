<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>
<div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <div class="card mb-4">
                        	<div class="card-header d-flex justify-content-between align-items-center">
                                <strong style="font-size: 20px;">DANH SÁCH CÁC THỂ LOẠI</strong> 
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
							    <thead>
							        <tr>
							            <th>Thể loại</th>
							            <th>Tên sách</th>
							           <!--  <th>Chức năng</th> --> <!-- Cột để chứa nút sửa -->
							        </tr>
							    </thead>
							    <tfoot>
							        <tr>
							            <th>Thể loại</th>
							            <th>Tên sách</th>
							           <!--  <th>Chức năng</th> -->
							        </tr>
							    </tfoot>
							    <tbody>
							        <c:forEach var="genreWithBooks" items="${genreWithBooks}">
							            <tr>
							                <!-- Hiển thị tên thể loại -->
							                <td>${genreWithBooks.genreName}</td>
							                <td>
							                    <!-- Lặp qua các sách của thể loại và ngăn cách bằng dấu xuống dòng -->
							                    <c:forEach var="book" items="${genreWithBooks.books}">
							                        <div class="book-title">
							                            ${book.title}
							                        </div>
							                        <c:if test="${not empty book}">
							                        </c:if>
							                    </c:forEach>
							                </td>
							                <!-- Cột chức năng chứa nút sửa -->
							                <%-- <td>
							                    <c:forEach var="book" items="${genreWithBooks.books}">
							                        <c:if test="${not empty book}">
							                            <div class="action-buttons">
							                                <a href="${pageContext.request.contextPath}/admin/genre/editBook?bookId=${book.bookid}&genreId=${genreWithBooks.genreId}" class="btn btn-warning btn-sm">
							                                    <i class="fas fa-edit"></i> Sửa
							                                </a>
							                            </div> 
							                        </c:if>
							                    </c:forEach>
							                </td> --%>
							            </tr>
							        </c:forEach>
							    </tbody>
							</table>

							<!-- CSS để căn chỉnh các nút sửa -->
							<!-- <style>
							    .book-title {
							        display: block; /* Hiển thị tên sách trên một dòng riêng */
							        padding-right: 20px; /* Đảm bảo nút "Sửa" không bị tràn ra ngoài */
							    }
							
							    .action-buttons a {
							        display: block; /* Các nút sửa sẽ chiếm toàn bộ chiều rộng của cột */
							        width: 50%;
							        text-align: center;
							        padding: 5px;
							        margin: 5px 0;
							    }
							
							    .action-buttons {
							        display: flex;
							        flex-direction: column; /* Đặt các nút sửa theo chiều dọc */
							        align-items: center;
							    }
							</style> -->
                            </div>
                        </div>
                    </div>
                </main>
                
