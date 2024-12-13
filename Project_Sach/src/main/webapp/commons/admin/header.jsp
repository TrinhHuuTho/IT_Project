<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="jakarta.tags.core"  %>
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="${pageContext.request.contextPath }/authentication/logout">Trang chủ</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath }/authentication/logout">
                        <i class="fas fa-user fa-fw"></i> Đăng xuất
                    </a>
                </li>
            </ul>
            
            
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <a class="nav-link" href="${pageContext.request.contextPath }/admin/home">
                                <div class="sb-nav-link-icon"><i class="fa-solid fa-book"></i></div>
                                Danh sách các bộ sách
                            </a>
                            
                            <a class="nav-link" href="${pageContext.request.contextPath }/admin/review">
                                <div class="sb-nav-link-icon"><i class="fa-solid fa-comment"></i></i></div>
                                Danh sách các đánh giá
                            </a>
                            <a class="nav-link" href="${pageContext.request.contextPath }/admin/genre">
                                <div class="sb-nav-link-icon"><i class="fa-solid fa-layer-group"></i></i></div>
                                Danh sách các thể loại
                            </a>
                            
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Xin chào, </div>
                        ${sessionScope.username}
                    </div>
                </nav>
            </div>