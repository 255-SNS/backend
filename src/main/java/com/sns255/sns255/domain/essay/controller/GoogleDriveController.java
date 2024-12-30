package com.sns255.sns255.domain.essay.controller;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/drive")
@RequiredArgsConstructor
public class GoogleDriveController {

    private final GoogleAuthorizationCodeFlow flow;

    private Drive googleDrive;
    private static final String APPLICATION_NAME = "Google Drive API Integration";
    private static final NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * Step 1: OAuth 인증 URL 생성 및 반환
     */
    @GetMapping("/auth")
    public ResponseEntity<String> authorize() {
        String authorizationUrl = flow.newAuthorizationUrl()
                .setRedirectUri("http://localhost:8080/drive/callback")
                .build();
        return ResponseEntity.ok(authorizationUrl);
    }

    /**
     * Step 2: Callback 처리 및 Credential 생성
     */
    @GetMapping("/callback")
    public ResponseEntity<String> handleOAuthCallback(@RequestParam("code") String code) throws IOException {
        // Exchange code for GoogleTokenResponse
        GoogleTokenResponse tokenResponse = flow.newTokenRequest(code)
                .setRedirectUri("http://localhost:8080/drive/callback")
                .execute();

        // Convert GoogleTokenResponse to Credential
        Credential credential = flow.createAndStoreCredential(tokenResponse, "user");

        // Create Google Drive service
        googleDrive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();

        return ResponseEntity.ok("Authentication successful! You can now use the Google Drive API.");
    }

    /**
     * Step 3: Google Drive에서 파일 목록 조회
     */
    @GetMapping("/list")
    public ResponseEntity<List<File>> listFiles() throws IOException {
        if (googleDrive == null) {
            return ResponseEntity.status(401).body(Collections.emptyList());
        }

        FileList result = googleDrive.files().list()
                .setFields("files(id, name)")
                .execute();

        return ResponseEntity.ok(result.getFiles());
    }


    /**
     * Step 4: Google Drive에 파일 업로드
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (googleDrive == null) {
            return ResponseEntity.status(401).body("Unauthorized. Please authenticate first.");
        }

        File fileMetadata = new File();
        fileMetadata.setName(file.getOriginalFilename());

        java.io.File tempFile = java.io.File.createTempFile("upload-", file.getOriginalFilename());
        file.transferTo(tempFile);

        File uploadedFile = googleDrive.files().create(fileMetadata,
                        new FileContent(file.getContentType(), tempFile))
                .setFields("id")
                .execute();

        tempFile.delete(); // 임시 파일 삭제
        return ResponseEntity.ok("File uploaded with ID: " + uploadedFile.getId());
    }

    /**
     * Step 5: Google Drive에서 파일 삭제
     */
    @DeleteMapping("/delete/{fileId}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileId) throws IOException {
        if (googleDrive == null) {
            return ResponseEntity.status(401).body("Unauthorized. Please authenticate first.");
        }

        googleDrive.files().delete(fileId).execute();
        return ResponseEntity.ok("File deleted successfully.");
    }

}
