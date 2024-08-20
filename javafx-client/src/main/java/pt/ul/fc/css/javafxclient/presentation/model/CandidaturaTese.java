package pt.ul.fc.css.javafxclient.presentation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.beans.property.*;
import javafx.collections.FXCollections;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CandidaturaTese {

    private final LongProperty id = new SimpleLongProperty();
    private final ObjectProperty<Aluno> aluno = new SimpleObjectProperty<>();
    private final ListProperty<Tema> listaTemas = new SimpleListProperty<>();
    private final ObjectProperty<Tema> temaAtribuido = new SimpleObjectProperty<>();
    private final ObjectProperty<Tese> tese = new SimpleObjectProperty<>();


    public final LongProperty idProperty() {
        return this.id;
    }

    public final long getId() {
        return idProperty().get();
    }

    public final void setId(final long newId) {
        this.idProperty().set(newId);
    }

    public final ObjectProperty<Aluno> alunoProperty() {
        return this.aluno;
    }

    public final Aluno getAluno() {
        return alunoProperty().get();
    }

    public final void setAluno(final Aluno newAluno) {
        this.alunoProperty().set(newAluno);
    }

    public final ListProperty<Tema> listaTemasProperty() {
        return this.listaTemas;
    }

    public final List<Tema> getListaTemas() {
        return listaTemasProperty().get();
    }

    public final void setListaTemas(final List<Tema> newTemas) {
        this.listaTemasProperty().set(FXCollections.observableList(newTemas));
    }

    public final ObjectProperty<Tema> temaAtribuidoProperty() {
        return this.temaAtribuido;
    }

    public final Tema getTema() {
        return temaAtribuidoProperty().get();
    }

    public final void setTema(final Tema newTema) {
        this.temaAtribuidoProperty().set(newTema);
    }

    public final ObjectProperty<Tese> teseProperty() {
        return this.tese;
    }

    public final Tese getTese() {
        return teseProperty().get();
    }

    public final void setTese(final Tese newTese) {
        this.teseProperty().set(newTese);
    }


}
