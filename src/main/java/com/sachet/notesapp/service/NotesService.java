package com.sachet.notesapp.service;

import com.mongodb.client.result.DeleteResult;
import com.sachet.notesapp.model.Notes;
import com.sachet.notesapp.repository.NotesRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class NotesService {

    private final NotesRepository notesRepository;

    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public Mono<Notes> saveNotes(Notes notes){

        return notesRepository.saveNotes(notes);

    }

    public Mono<Notes> getNoteById(String id){
        return notesRepository.getNoteById(id);
    }

    public Flux<Notes> getNotesOfUser(String userId){
        return notesRepository
                .findNotesOfUser(userId);
    }

    public Mono<String> deleteNoteById(String noteId){
        return notesRepository
                .deleteNoteById(noteId)
                .map(deleteResult -> noteId);
    }
}










