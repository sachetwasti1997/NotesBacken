package com.sachet.notesapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class GetNotesResponse {
    private List<Notes> notes;
    private String message;
    private Integer code;

    public GetNotesResponse(List<Notes> notes, String message, Integer code){
        this.notes = notes;
        this.message = message;
        this.code = code;
    }

}
