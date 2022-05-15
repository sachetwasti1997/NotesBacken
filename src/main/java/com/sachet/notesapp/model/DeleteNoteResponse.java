package com.sachet.notesapp.model;

import lombok.Data;

@Data
public class DeleteNoteResponse {
    private String message;
    private Integer code;

    public DeleteNoteResponse(String message, Integer code) {
        this.message = message;
        this.code = code;
    }
}
