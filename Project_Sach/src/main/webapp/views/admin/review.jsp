<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>
<div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <div class="card mb-4">
                        	<div class="card-header d-flex justify-content-between align-items-center">
                                <strong style="font-size: 20px;">DANH SÁCH CÁC ĐÁNH GIÁ</strong>
                                
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                        	<th>Tên người dùng</th>
                                        	<th>Tên sách</th>
                                        	<th>Điểm đánh giá</th>
                                        	<th>Đánh giá </th>
                                        	<th>Ngày đánh giá</th>
                                        	<th>Chức năng</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Tên người dùng</th>
                                        	<th>Tên sách</th>
                                        	<th>Điểm đánh giá</th>
                                        	<th>Đánh giá </th>
                                        	<th>Ngày đánh giá</th>
                                        	<th>Chức năng</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                    <c:forEach var="review" items="${review}">
                                        <tr>
                                            <td>${review.username}</td>
                                            <td>${review.bookTitle}</td>
                                            <td>${review.rating}</td>
                                            <td>${review.comment}</td>
                                            <td>${review.createdAtReview}</td>
                                            <td style="text-align: center;">
											    <a href="${pageContext.request.contextPath }/admin/review/delete?id=${review.reviewId}" class="btn btn-danger btn-sm">
											        <i class="fa-solid fa-ban"></i>
											    </a>
											</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </main>
                
