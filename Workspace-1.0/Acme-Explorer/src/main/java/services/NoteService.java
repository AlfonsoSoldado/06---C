package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NoteRepository;
import domain.Auditor;
import domain.Manager;
import domain.Note;

@Service
@Transactional
public class NoteService {

	// Managed repository

	@Autowired
	private NoteRepository noteRepository;

	// Supporting services
	
	@Autowired
	private AuditorService auditorService;

	// Constructors

	public NoteService() {
		super();
	}

	// Simple CRUD methods

	// 33.1
	
	public Note create() {
		Note res = new Note();
		Auditor a = new Auditor();
		Date d = new Date();
		a = auditorService.findByPrincipal();
		Assert.notNull(a);
		res.setMoment(d);
		res.setAuditor(a);
		res.setReply(null);
		return res;
	}

	public Collection<Note> findAll() {
		Collection<Note> res;
		res = this.noteRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Note findOne(int note) {
		Assert.isTrue(note != 0);
		Note res;
		res = this.noteRepository.findOne(note);
		Assert.notNull(res);
		return res;
	}

	public Note save(Note note) {
		Assert.notNull(note);
		if (note.getId() == 0) {
			note.setReply(null);
		}
		Note res;
		res = this.noteRepository.save(note);
		if (note.getId() == 0) {
			Date fechaActual = new Date();
			res.setMomentReply(fechaActual);
		}
		
		return res;
	}

	// Other business methods

	// 33.1
	
	public Collection<Note> findNotesByAuditor(int id) {
		Collection<Note> res = new ArrayList<Note>();

		res.addAll(noteRepository.findNotesByAuditor(id));
		Assert.notNull(res);
		return res;
	}
	
	//32.1
	
	public Collection<Note> findNotesByManager(Manager manager){
		Collection<Note> res = new ArrayList<Note>();
		
		res.addAll(noteRepository.findNotesByManager(manager.getId()));
		
		return res;
	}
}
