package net.sparkminds.delivery.service;

import net.sparkminds.delivery.exception.BaseException;
import net.sparkminds.delivery.response.UploadFileResponse;
import net.sparkminds.delivery.service.dto.File.UploadFileRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Service
public class FileStorageService {

    private final String UPLOAD_DIR =
            System.getProperty("user.dir") + "/uploads/";

    public UploadFileResponse uploadFile(UploadFileRequest request) throws IOException {
        if (request.getFile().isEmpty()) {
            throw new BaseException("FILE_EMPTY", "File empty", HttpStatus.BAD_REQUEST);
        }

        File uploadPath = new File(UPLOAD_DIR);

        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        String fileName = UUID.randomUUID() + "_" + request.getFile().getOriginalFilename();

        File saveFile = new File(uploadPath, fileName);

        request.getFile().transferTo(saveFile);

        return new UploadFileResponse(fileName);
    }
}
