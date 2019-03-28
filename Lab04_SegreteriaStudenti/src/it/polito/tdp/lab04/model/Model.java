package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDao;
	
	private StudenteDAO studenteDao;

	public Model() {
		super();
		corsoDao = new CorsoDAO();
		studenteDao = new StudenteDAO();
	}

	public List<String> getNomeCorsi() {
		
		List<Corso> corsi = new LinkedList<Corso>(corsoDao.getTuttiICorsi());
		List <String> nomeCorsi = new LinkedList<String>();
		for(Corso c : corsi) {
			nomeCorsi.add(c.getNome());
		}
		nomeCorsi.add(" ");
		return nomeCorsi ;
	}

	public String getNome(int matr) {
		return studenteDao.getStudente(matr).getNome();
		
	}

	public String getCognome(int matr) {
		return studenteDao.getStudente(matr).getCognome();
	}
	
	public Studente getStudente(int matricola) {

		return studenteDao.getStudente(matricola);
	}
	
	public List<Corso> getTuttiICorsi() {

		return corsoDao.getTuttiICorsi();
	}


	public String getStudentiIscrittiAlCorso(Corso corso) {
		
		String studentiIscritti ="";
		for(Studente s: corsoDao.getStudentiIscrittiAlCorso(corso)) {
			studentiIscritti += s.toString();
		}
		
		return studentiIscritti;
	}

	public List<Corso> getCorsiDatoStudente(Studente studente) {
		return studenteDao.getCorsiACuiIscritto(studente);
	}

	/*
	 * Ritorna TRUE se lo studente è iscritto al corso, FALSE altrimenti
	 */
	public boolean isStudenteIscrittoACorso(Studente studente, Corso corso) {

		return studenteDao.isStudenteIscrittoACorso(studente, corso);
	}

	/*
	 * Iscrivi una studente ad un corso. Ritorna TRUE se l'operazione va a buon fine.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {

		return corsoDao.inscriviStudenteACorso(studente, corso);
	}
	} 


