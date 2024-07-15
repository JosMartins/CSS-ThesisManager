package pt.ul.fc.css.javafxclient.presentation.model;


import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Mestrado {

    private LongProperty id = new SimpleLongProperty();
    private StringProperty nome = new SimpleStringProperty();

    public LongProperty idProperty() {
        return id;
    }

    public long getId() {
        return idProperty().get();
    }

    public void setId(long novoId) {
        this.id.set(novoId);
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public String getNome() {
        return nomeProperty().get();
    }

    public void setNome(String novoNome) {
        this.nome.set(novoNome);
    }

}
