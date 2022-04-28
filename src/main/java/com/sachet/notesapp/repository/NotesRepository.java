package com.sachet.notesapp.repository;

import com.mongodb.client.result.DeleteResult;
import com.sachet.notesapp.model.Notes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public class NotesRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public NotesRepository(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    public Mono<Notes> getNoteById(String id){
        Query query = new Query(Criteria.where("noteId").is(id));
        return reactiveMongoTemplate.findOne(query, Notes.class);
    }

    public Mono<Notes> saveNotes(Notes notes){
        return reactiveMongoTemplate.save(notes);
    }

    public Flux<Notes> findNotesOfUser(String userId){
        Query query = new Query(Criteria.where("userId").is(userId));
        return reactiveMongoTemplate.find(query, Notes.class);
    }

    public Mono<DeleteResult> deleteNoteById(String noteId){
        Query query = new Query(Criteria.where("noteId").is(noteId));
        return reactiveMongoTemplate.remove(query, Notes.class);
    }
}


















