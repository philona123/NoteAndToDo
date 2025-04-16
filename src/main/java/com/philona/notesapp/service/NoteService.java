package com.philona.notesapp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.philona.notesapp.model.Note;
import com.philona.notesapp.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class NoteService {
    private final Cloudinary cloudinary;
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository, Cloudinary cloudinary) {
        this.noteRepository = noteRepository;
        this.cloudinary = cloudinary;
    }

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteById(String id) {
        return noteRepository.findById(id).orElse(null);
    }

    public void deleteNoteById(String id) {
        noteRepository.deleteById(id);
    }

    public Note uploadNoteWithImage(Note note, MultipartFile image) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
        note.setImageUrl((String) uploadResult.get("url"));
        note.setCreatedAt(LocalDateTime.now());
        return noteRepository.save(note);
    }
}
