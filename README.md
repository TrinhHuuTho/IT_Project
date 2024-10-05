# IT_Project

## Hướng dẫn sử dụng Repository cho Dự án Nhóm

### Giới thiệu
Chào mừng bạn đến với dự án nhóm của chúng ta! Trong tài liệu này sẽ hướng dẫn cách làm việc với repository này một cách hiệu quả.

---
```bash
1. Cấu hình thông tin người dùng
git config --global user.name "Tên của bạn"
git config --global user.email "email@example.com"

2. Clone Repository:
git clone https://github.com/TrinhHuuTho/IT_Project.git
cd IT_Project

3. Tạo Branch Mới:
git checkout -b <ten_branch_cua_ban>

4. Chuyển đổi giữa các nhánh làm việc:
git checkout <ten_branch_cua_ban>

5. Thêm các thay đổi:
git add .

6. Commit thay đổi:
git commit -m "Mô tả ngắn gọn về thay đổi"

7. Push lên repository:
git push origin <ten_branch_cua_ban>
Tạo Pull Request: Sau khi đã push branch, hãy tạo một Pull Request trên GitHub để kết hợp các thay đổi của bạn vào nhánh chính (main). Điều này sẽ giúp mọi người trong nhóm có thể xem xét và phản hồi.

8. Pull Các Thay đổi Mới Nhất:
git checkout main
git pull origin main
