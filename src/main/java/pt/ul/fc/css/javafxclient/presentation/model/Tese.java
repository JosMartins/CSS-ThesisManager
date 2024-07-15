package pt.ul.fc.css.javafxclient.presentation.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;

public class Tese {

    private final LongProperty id = new SimpleLongProperty();
    private final ObjectProperty<Tema> tema = new SimpleObjectProperty<>();
    private final ObjectProperty<Aluno> aluno = new SimpleObjectProperty<>();
    private final ListProperty<ObjectProperty<Defesa>> defesaPropostasTese = new SimpleListProperty<>();
    private final ObjectProperty<Defesa> defesaTese = new SimpleObjectProperty<>();

    public final LongProperty idProperty() {
        return this.id;
    }

    public final long getId() {
        return idProperty().get();
    }

    public final void setId(final long newId) {
        this.idProperty().set(newId);
    }

    public final ObjectProperty<Tema> temaProperty() {
        return this.tema;
    }

    public final Tema getTema() {
        return temaProperty().get();
    }

    public final void setTema(final Tema newTema) {
        this.temaProperty().set(newTema);
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

    public final ListProperty<ObjectProperty<Defesa>> defesaPropostasTeseProperty() {
        return this.defesaPropostasTese;
    }

    public final java.util.List<Defesa> getDefesaPropostasTese() {
        return defesaPropostasTeseProperty().stream().map(ObjectProperty::get).collect(java.util.stream.Collectors.toList());
    }

    public final void setDefesaPropostasTese(final java.util.List<Defesa> newDefesaPropostasTese) {
        java.util.List<ObjectProperty<Defesa>> newDefesaPropostasTeseList = newDefesaPropostasTese.stream().map(SimpleObjectProperty::new).collect(java.util.stream.Collectors.toList());
        this.defesaPropostasTeseProperty().set(FXCollections.observableList(newDefesaPropostasTeseList));
    }

    public final ObjectProperty<Defesa> defesaTeseProperty() {
        return this.defesaTese;
    }

    public final Defesa getDefesaTese() {
        return defesaTeseProperty().get();
    }

    public final void setDefesaTese(final Defesa newDefesaTese) {
        this.defesaTeseProperty().set(newDefesaTese);
    }


}
