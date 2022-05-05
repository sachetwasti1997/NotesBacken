package com.sachet.notesapp.controller;

import com.mongodb.client.result.DeleteResult;
import com.sachet.notesapp.model.Notes;
import com.sachet.notesapp.service.NotesService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/notes")
public class NotesController {

    private final NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @PostMapping("/save")
    public Mono<Notes> saveNotes(@RequestBody @Valid Mono<Notes> notesMono){
        return notesMono
                .flatMap(notesService::saveNotes);
    }

    @GetMapping("/single/{noteId}")
    public Mono<Notes> getNoteById(@PathVariable(name = "noteId") String noteId){
        return notesService.getNoteById(noteId).map(notes -> {
            System.out.println(notes);
            return notes;
        });
    }

    @GetMapping("/{userId}")
    public Flux<Notes> getAllNotesOfUser(@PathVariable(name = "userId") String userId){
        return notesService
                .getNotesOfUser(userId)
                .log();
    }

    @DeleteMapping("/{noteId}")
    public Mono<String> deleteNoteById(@PathVariable(name = "noteId") String noteId){
        return notesService
                .deleteNoteById(noteId);
    }
}
