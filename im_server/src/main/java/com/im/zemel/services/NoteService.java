package com.im.zemel.services;

import com.im.zemel.pojo.Note;
import com.im.zemel.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;


/**
 * \* Created with IntelliJ IDEA.
 * \* User: Vimmone
 * \* Date: 2018/2/22
 * \* Description:
 * \
 */

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

//    public List<Note> getAllNote() {
//        Page<Note> notes = noteRepository.findAll(new PageRequest(1, 4));
//        for(Note note : notes) {
//            System.out.println("=====>>>>"+note);
//        }
//
//        return noteRepository.findAll();
//    }

    public void newNote(Note note) {
        noteRepository.save(note);
    }
    @Transactional
    public void deleteNoteById(Integer noteId) {
        noteRepository.delete(noteId);
    }

    public List<Note> getNoteByPage(int page) {

        List<Note> notes = new LinkedList<Note>();
        Page<Note> all = noteRepository.findAll(new PageRequest(page, 10));
        for (Note note :all) {
            notes.add(note);
        }
        return notes;
    }

    public List<Note> getNoteByPageInPhone() {
        return noteRepository.findAll();
    }

    public List<Note> getNoteByUserId(Integer userId) {

        return noteRepository.findByAuthorId(userId);


    }

    public List<Note> getNoteByUserIdAndPage(int userId, int page) {
        return noteRepository.getNoteByUidAndPage(userId,page*9);
    }
}


