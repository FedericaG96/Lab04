package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

	private Model model;
	List<Corso> corsi;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Corso> bxCorsi;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;
    
    @FXML
    private Button btnAssocia;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;
    
    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doAssocia(ActionEvent event) {
    	
    	String nome = null;
    	String cognome = null;
    	
    	String matricolaInserita = txtMatricola.getText();
    	if(matricolaInserita.isEmpty()){
    		txtResult.setText("Errore! Nessuna matricola inserita");
    		return;
    	}
    	try {
    	nome = model.getNome((Integer.parseInt(txtMatricola.getText())));
    	cognome = model.getCognome((Integer.parseInt(txtMatricola.getText())));
    	} catch(NumberFormatException e) {
    		txtResult.setText("Inserire matricola valida!");
    		return;
    	}
    	
    	if(nome==null || cognome==null ) {
    		txtResult.setText("Studente inesistente!");
    	}
    	else {
    		txtNome.appendText(nome);
    		txtCognome.appendText(cognome);
    	}

    }

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	
    	String matricolaInserita = txtMatricola.getText();
    	if(matricolaInserita.isEmpty()){
    		txtResult.setText("Errore! Nessuna matricola inserita");
    		return;
    	}
    	
    	List<Corso> corsiSeguiti = null;
    	try {
    	Studente studente = model.getStudente(Integer.parseInt(matricolaInserita));
		if (studente == null) {
			txtResult.appendText("Nessun risultato: matricola inesistente");
			return;
		}
    	corsiSeguiti = model.getCorsiDatoStudente(studente);
    	}catch(NumberFormatException e) {
    		txtResult.setText("Inserire matricola valida!");
    		return;
    	}
    	if(corsiSeguiti == null) {
    		txtResult.setText("Lo studente non è iscritto ad alcun corso"); 
    	} 
    	else {

			StringBuilder sb = new StringBuilder();
    		for (Corso corso : corsi) {
				sb.append(String.format("%-8s ", corso.getCodins()));
				sb.append(String.format("%-4s ", corso.getCrediti()));
				sb.append(String.format("%-45s ", corso.getNome()));
				sb.append(String.format("%-4s ", corso.getPd()));
				sb.append("\n");
			}
			txtResult.appendText(sb.toString());
    	}
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	
    	Corso corsoScelto = bxCorsi.getValue();
        	
    	if(corsoScelto == null) {
    		txtResult.setText("Errore! Nessun corso selezionato");
    		return;
    	}
    	String studentiIscritti= model.getStudentiIscrittiAlCorso(corsoScelto);
    	
    	if(studentiIscritti == null) {
    		txtResult.setText("Nessun studente iscritto al corso scelto!");
    	}
    	else {
    		txtResult.setText(studentiIscritti);
    	}

    }
   
    @FXML
    void doIscrivi(ActionEvent event) {

    	Corso corsoScelto = bxCorsi.getValue();
    	if(corsoScelto == null) {
    		txtResult.setText("Errore! Nessun corso selezionato");
    		return;
    	}
    	
    	String matricolaInserita = txtMatricola.getText();
    	if(matricolaInserita.isEmpty()){
    		txtResult.setText("Errore! Nessuna matricola inserita");
    		return;
    	}
    	// Controllo se lo studente è già iscritto al corso
    	Studente studente = model.getStudente(Integer.parseInt(matricolaInserita));
    	if (model.isStudenteIscrittoACorso(studente, corsoScelto)) {
			txtResult.appendText("Studente già iscritto a questo corso");
			return;
		}

		// Iscrivo lo studente al corso.
		// Controllo che l'inserimento vada a buon fine
		if (!model.inscriviStudenteACorso(studente, corsoScelto)) {
			txtResult.appendText("Errore durante l'iscrizione al corso");
			return;
		} else {
			txtResult.appendText("Studente iscritto al corso!");
		}    	

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtResult.clear();
    	txtCognome.clear();
    	txtNome.clear();
    	txtMatricola.clear();
    	bxCorsi.getSelectionModel().clearSelection();

    }

    @FXML
    void initialize() {
        assert bxCorsi != null : "fx:id=\"bxCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnAssocia != null : "fx:id=\"btnAssocia\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

        
    }

	public void setModel(Model model) {
	this.model= model;
	//bxCorsi.getItems().addAll(model.getNomeCorsi());
	corsi = model.getTuttiICorsi();
	bxCorsi.getItems().addAll(corsi);

	}
}
