package pt.ul.fc.css.javafxclient.presentation.control;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pt.ul.fc.css.javafxclient.presentation.model.Aluno;

public class SubmissionController {
	
	private Aluno aluno;

	@FXML
	private TextField proposalDocumentField;

	@FXML
	private TextField finalDocumentField;

	@FXML
	private void handleSubmitButtonAction(ActionEvent event) {
		String proposalDocument = proposalDocumentField.getText();
		String finalDocument = finalDocumentField.getText();

		System.out.println("Proposal Document: " + proposalDocument);
		System.out.println("Final Document: " + finalDocument);

		//check if input is empty
		submitDocuments(proposalDocument, finalDocument);
	}

	private void submitDocuments(String proposalDocument, String finalDocument) {

		if(proposalDocument.isEmpty() && finalDocument.isEmpty()) {
			System.out.println("Nenhum document selecionado");
			return;
		}

		if(!proposalDocument.isEmpty()) {
			try {
				String content = new String(Files.readAllBytes(Paths.get(proposalDocument)), StandardCharsets.UTF_8);

				//aluno.setPropostaTese(content);
				sendAlunoData(aluno, "/propostatese");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(!finalDocument.isEmpty()) {
			try {
				String content = new String(Files.readAllBytes(Paths.get(finalDocument)), StandardCharsets.UTF_8);

				aluno.getTese().getDefesaTese().setDocument(content);
				sendAlunoData(aluno, "/teser");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

	}
	

	public void sendAlunoData(Aluno aluno,String modo) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8080/api/submission/" + modo);

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(aluno);

			StringEntity entity = new StringEntity(json);
			httpPost.setEntity(entity);
			httpPost.setHeader("Aluno", "application/json");

			CloseableHttpResponse response = client.execute(httpPost);
			try {
				System.out.println(EntityUtils.toString(response.getEntity()));
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void setAluno(Aluno novoAluno) {
		aluno = novoAluno;
	}
}
