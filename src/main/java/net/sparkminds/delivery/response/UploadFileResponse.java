package net.sparkminds.delivery.response;

import lombok.Data;

@Data
public class UploadFileResponse {
    private String key;

    public UploadFileResponse() {
    }

    public UploadFileResponse(String key) {
        this.key = key;
    }
}
