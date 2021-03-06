package com.sachet.notesapp.controller;

import com.mongodb.client.result.DeleteResult;
import com.sachet.notesapp.errors.NoteNotFoundException;
import com.sachet.notesapp.model.DeleteNoteResponse;
import com.sachet.notesapp.model.GetNotesResponse;
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
        return notesService
                .getNoteById(noteId)
                .switchIfEmpty(Mono.error(new NoteNotFoundException("No Notes Found Exception")));
    }

    @GetMapping("/{userId}")
    public Mono<GetNotesResponse> getAllNotesOfUser(@PathVariable(name = "userId") String userId){
        return notesService
                .getNotesOfUser(userId)
                .collectList()
                .filter(notes -> !notes.isEmpty())
                .map(notes -> new GetNotesResponse(notes, "Successfully fetched notes", 200))
                .switchIfEmpty(Mono.error(new NoteNotFoundException("No Notes Found For the User")));
    }

    @DeleteMapping("/{noteId}")
    public Mono<DeleteNoteResponse> deleteNoteById(@PathVariable(name = "noteId") String noteId){
        return notesService
                .deleteNoteById(noteId)
                .map(s -> new DeleteNoteResponse(s, 200))
                .switchIfEmpty(Mono.error(new NoteNotFoundException("No Notes Found Exception")));
    }
}
