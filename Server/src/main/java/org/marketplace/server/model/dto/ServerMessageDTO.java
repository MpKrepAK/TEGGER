package org.marketplace.server.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServerMessageDTO {
    private int statusCode;
    private String message;

    public ServerMessageDTO(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
