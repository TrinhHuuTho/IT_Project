package vn.HiepKa.utils;

import com.azure.storage.blob.*;
import java.io.*;

public class AzureBlobUtils {

    // Kết nối tới Azure Blob Storage
    private static final String CONNECTION_STRING = "";
    private static final String CONTAINER_NAME = "images"; // Tên container trong Azure

    // Tải tệp lên Azure Blob Storage
    public static String uploadFileToBlob(String fileName, InputStream fileInputStream, long fileSize) {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(CONNECTION_STRING).buildClient();
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(CONTAINER_NAME);

        // Kiểm tra container có tồn tại không, nếu không thì tạo mới
        if (!containerClient.exists()) {
            containerClient.create();
        }

        // Lưu tệp lên blob storage
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        blobClient.upload(fileInputStream, fileSize, true);

        // Trả về URL của tệp đã tải lên
        return blobClient.getBlobUrl();
    }

    // Tải tệp từ Azure Blob Storage
    public static InputStream downloadFileFromBlob(String fileName) {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(CONNECTION_STRING).buildClient();
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(CONTAINER_NAME);
        BlobClient blobClient = containerClient.getBlobClient(fileName);

        // Kiểm tra tệp có tồn tại không, nếu có thì tải về
        if (blobClient.exists()) {
            return blobClient.openInputStream();
        } else {
            throw new RuntimeException("File not found in blob storage.");
        }
    }
}
