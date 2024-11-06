package com.project.public_safety_app.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO(LocalDateTime timestamp,
                               int status,
                               String error,
                               String message,
                               String path
) {
}
