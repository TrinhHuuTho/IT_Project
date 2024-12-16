<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>
<div id="layoutSidenav_content">
    <main>
        <div class="container-fluid px-4">
            <div class="card mb-4">
                <div class="card-header">
                    <strong style="font-size: 20px;">SỬA SÁCH</strong>
                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/admin/update" method="POST" enctype="multipart/form-data">
                        <!-- Input ẩn cho ID sách -->
                        <input type="hidden" id="bookid" name="bookid" value="${book.bookid}">                   
                        <!-- Tiêu đề sách -->
                        <label for="title">Tiêu đề:</label>
                        <input type="text" id="title" name="title" value="${book.title}" placeholder="Nhập tiêu đề sách" required>

                        <!-- Tên tác giả -->
                        <label for="authorname">Tên tác giả:</label>
                        <input type="text" id="authorname" name="authorname" value="${book.authorname}" placeholder="Nhập tên tác giả" required>

						<!-- Chọn thể loại -->
						<label for="genre_name_select">Thể loại:</label>
						<select id="genre_name_select" name="genre_name" required>
						    <option value="" disabled selected>Chọn thể loại</option>
						    <!-- Lặp qua các thể loại đã có sẵn trong database -->
						    <c:forEach var="genre" items="${genres}">
						        <option value="${genre.genreName}" 
						            <c:if test="${genre.genreName == book.genreName}">selected</c:if>>
						            ${genre.genreName}
						        </option>
						    </c:forEach>
						</select>
						<!-- Hoặc cho phép người dùng nhập thể loại mới -->
						<label for="genre_name_input">Hoặc nhập thể loại mới:</label>
						<input type="text" id="genre_name_input" name="genre_name" placeholder="Nhập thể loại mới (nếu có)" oninput="checkGenreInput()">

                        <!-- Chọn file PDF -->
                        <label for="content">File PDF:</label>
                        <c:if test="${book.content != null}">
                            <p>File hiện tại: ${book.content}</p>
                        </c:if>
                        <input type="file" id="content" name="content" accept="application/pdf">

                        <!-- Ngày tạo -->
                        <label for="createdAt">Ngày tạo:</label>
                        <input type="date" id="createdAt" name="createdAt" value="${book.createdat}" required>

                        <!-- Hình ảnh sách -->
                        <label for="imageBook">Hình ảnh sách:</label>
                        <div class="image-container">
                            <c:choose>
                                <c:when test="${book.imagesbook != null && book.imagesbook.startsWith('https')}">
                                    <img id="imagePreview" src="${book.imagesbook}" alt="" class="img-fluid" />
                                </c:when>
                                <c:when test="${book.imagesbook != null && !book.imagesbook.startsWith('https')}">
                                    <c:url value="/image?fname=${book.imagesbook}" var="imgUrl"></c:url>
                                    <img id="imagePreview" src="${imgUrl}" alt="" class="img-fluid" />
                                </c:when>
                                <c:otherwise>
                                    <p>Không có hình ảnh</p>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <input type="file" id="imageBook" name="imageBook" accept="image/*">
                        <label for="imageUrl">Hoặc nhập URL ảnh:</label>
                        <input type="url" id="imageUrl" name="imageUrl" placeholder="Nhập URL ảnh">

                        <!-- Trạng thái -->
                        <label for="status">Trạng thái:</label>
                        <input type="radio" id="active" name="status" value="1" <c:if test="${book.status == true}">checked</c:if>> Hoạt động
                        <input type="radio" id="inactive" name="status" value="0" <c:if test="${book.status == false}">checked</c:if>> Khóa
                        <br>
                        <br>
                        <button type="submit" class="btn btn-primary btn-sm">Lưu Thay Đổi</button>
                    </form>
                </div>
            </div>
        </div>
    </main>
</div>

<script>
    // Hiển thị xem trước hình ảnh mới nếu người dùng chọn file
    document.getElementById('imageBook').addEventListener('change', function(event) {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                const preview = document.getElementById('imagePreview');
                preview.src = e.target.result;
            };
            reader.readAsDataURL(file);
        }
    });

    // Hiển thị xem trước hình ảnh từ URL nếu người dùng nhập
    document.getElementById('imageUrl').addEventListener('input', function(event) {
        const url = event.target.value;
        const preview = document.getElementById('imagePreview');
        if (url) {
            preview.src = url;
        }
    });
    
    function checkGenreInput() {
        const genreSelect = document.getElementById('genre_name_select');
        const genreInput = document.getElementById('genre_name_input');

        // Kiểm tra nếu người dùng nhập thể loại mới
        if (genreInput.value.trim() !== "") {
            genreSelect.removeAttribute('required');  // Loại bỏ yêu cầu chọn thể loại
        } else {
            genreSelect.setAttribute('required', 'required');  // Đặt lại yêu cầu chọn thể loại nếu input trống
        }
    }
    
</script>

<style>
    /* Giữ lại CSS của bạn, và chỉ điều chỉnh phần ảnh nhỏ */
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
    }
    #imageUrl {
    width: 100%;  /* Đảm bảo trường Tiêu đề chiếm toàn bộ chiều rộng của form */
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}
    #title {
    width: 100%;  /* Đảm bảo trường Tiêu đề chiếm toàn bộ chiều rộng của form */
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}
  #authorName {
    width: 100%;  /* Đảm bảo trường Tiêu đề chiếm toàn bộ chiều rộng của form */
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}
    form {
        max-width: 500px;
        margin: auto;
        border: 1px solid #ddd;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }
    
    label {
        font-weight: bold;
        display: block;
        margin-bottom: 5px;
    }
    button {
        background-color: #007bff;
        color: white;
        border: none;
        cursor: pointer;
    }
    button:hover {
        background-color: #0056b3;
    }
    

    /* Thay đổi kích thước khung ảnh */
    .image-container {
        text-align: center;
        margin-bottom: 15px;
        width: 250px;  /* Tăng kích thước khung ảnh */
        height: 300px; /* Tăng chiều cao khung ảnh */
        overflow: hidden;
        border: 1px solid #ddd;
        border-radius: 8px;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    
    .image-container img {
        max-width: 100%;    /* Đảm bảo ảnh không vượt quá chiều rộng khung */
        max-height: 100%;   /* Đảm bảo ảnh không vượt quá chiều cao khung */
        object-fit: contain; /* Đảm bảo ảnh giữ tỷ lệ và không bị bóp méo */
    }
</style>
