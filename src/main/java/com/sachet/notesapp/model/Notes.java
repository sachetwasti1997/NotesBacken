package com.sachet.notesapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class Notes {

    @Id
    private String noteId;

    @NotNull(message = "Title cannot be null!")
    private String title;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "UserId cannot be null")
    private String userId;

    private Integer color;

    private String localDateTime;

}
