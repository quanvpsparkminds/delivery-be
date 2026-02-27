package net.sparkminds.delivery.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.response.ApiResponse;
import net.sparkminds.delivery.response.UploadFileResponse;
import net.sparkminds.delivery.service.FileStorageService;
import net.sparkminds.delivery.service.dto.File.UploadFileRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileController {

    private final FileStorageService fileStorageService;

    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<ApiResponse<UploadFileResponse>> uploadImage(
            @Valid @ModelAttribute UploadFileRequest request
    ) throws IOException {

        UploadFileResponse rp = fileStorageService.uploadFile(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(rp));
    }
}
