<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="/commons/taglib.jsp"%>

<div id="layoutSidenav_content">
                <main>
        <div class="chapter-wrapper container my-5">
            <a href="#" class="text-decoration-none">
                <h1 class="text-center text-success">${book.title}</h1>
            </a>
            
            <div class="pdf-container"
                    style="
                width: 100%; 
                max-width: 800px; 
                height: 600px; 
                margin: 20px auto; 
                border: 1px solid #ddd; 
                overflow: hidden; 
                position: relative; 
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); 
                border-radius: 8px;">

                <%
                String pdfFileName = request.getParameter("pdfName");
                if (pdfFileName != null && !pdfFileName.isEmpty()) {
                %>

                <!-- NÃºt má» toÃ n mÃ n hÃ¬nh -->
                <button 
                class="fullscreen-btn" 
                onclick="openFullScreen('<%= request.getContextPath() + "/readPdf?pdfName=" + pdfFileName %>')"
                style="
                position: absolute; 
                top: 10px; 
                right: 10px; 
                background-color: #007bff; 
                color: white; 
                padding: 5px 10px; 
                border: none; 
                cursor: pointer; 
                border-radius: 5px; 
                opacity: 0.8;">
                Mở trong tab mới
                </button>

                <!-- Khung chứa PDF -->
                <iframe 
				    class="pdf-frame" 
				    src="${pageContext.request.contextPath}/readPdf?pdfName=${book.content}"
				    title="PDF Viewer" 
				    style="width: 100%; height: 100%; border: none;">
				</iframe>

                <% } else { %>
                <p style="text-align: center; color: #888;">Không tìm thấy tệp PDF.</p>
                <% } %>
            </div>
            
            <script>
                // HÃ m má» PDF á» trang má»i toÃ n mÃ n hÃ¬nh
                function openFullScreen(pdfUrl) {
                  window.open(pdfUrl, "_blank");
                }
              </script>
            
        </div>
      
    </main>