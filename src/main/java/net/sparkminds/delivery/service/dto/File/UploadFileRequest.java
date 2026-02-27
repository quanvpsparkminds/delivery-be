package net.sparkminds.delivery.service.dto.File;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadFileRequest {
    @NotNull
    private MultipartFile file;
}
