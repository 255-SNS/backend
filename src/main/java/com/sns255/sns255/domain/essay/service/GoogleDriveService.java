package com.sns255.sns255.domain.essay.service;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoogleDriveService {

    private final Drive googleDrive;
    private static final String PARENT_FOLDER_NAME = "255-SNS";

    /**
     * '255-SNS' 폴더 ID를 검색하는 메서드
     *
     * @return 폴더 ID
     * @throws IOException 폴더를 찾지 못했을 때 발생
     */
    private String getParentFolderId() throws IOException {
        FileList folderResult = googleDrive.files().list()
                .setQ("name = '" + PARENT_FOLDER_NAME + "' and mimeType = 'application/vnd.google-apps.folder' and trashed = false")
                .setFields("files(id, name)")
                .execute();

        if (folderResult == null || folderResult.getFiles().isEmpty()) {
            throw new IOException("Parent folder '" + PARENT_FOLDER_NAME + "' not found.");
        }

        return folderResult.getFiles().get(0).getId();
    }

    /**
     * 폴더 내 파일 목록 가져오기
     *
     * @return 폴더에 있는 파일 목록
     * @throws IOException 파일 목록을 가져올 수 없을 때 발생
     */
    public List<File> listFilesInFolder() throws IOException {
        try {
            String folderId = getParentFolderId();
            FileList result = googleDrive.files().list()
                    .setQ("'" + folderId + "' in parents and mimeType != 'application/vnd.google-apps.folder' and trashed = false")
                    .setFields("files(id, name, mimeType, webViewLink)")
                    .execute();

            return result != null ? result.getFiles() : Collections.emptyList();
        } catch (IOException e) {
            throw new IOException("Failed to list files in folder: " + e.getMessage(), e);
        }
    }

    /**
     * 파일 업로드
     *
     * @param fileName 파일 이름
     * @param filePath 업로드할 파일 경로
     * @param mimeType 파일 MIME 타입
     * @return 업로드된 파일의 웹 링크
     * @throws IOException 파일 업로드에 실패했을 때 발생
     */
    public String uploadFile(String fileName, java.io.File filePath, String mimeType) throws IOException {
        try {
            String folderId = getParentFolderId();

            File fileMetadata = new File();
            fileMetadata.setName(fileName);
            fileMetadata.setParents(Collections.singletonList(folderId));

            File uploadedFile = googleDrive.files().create(fileMetadata,
                            new com.google.api.client.http.FileContent(mimeType, filePath))
                    .setFields("id, webViewLink")
                    .execute();

            return uploadedFile.getWebViewLink();
        } catch (IOException e) {
            throw new IOException("Failed to upload file: " + e.getMessage(), e);
        }
    }

    /**
     * 파일 삭제
     *
     * @param fileId 삭제할 파일 ID
     * @throws IOException 파일 삭제에 실패했을 때 발생
     */
    public void deleteFile(String fileId) throws IOException {
        try {
            googleDrive.files().delete(fileId).execute();
        } catch (IOException e) {
            throw new IOException("Failed to delete file: " + e.getMessage(), e);
        }
    }
}
