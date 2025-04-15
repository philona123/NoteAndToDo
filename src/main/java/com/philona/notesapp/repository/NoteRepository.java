package com.philona.notesapp.repository;

import com.philona.notesapp.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, String> {
}
