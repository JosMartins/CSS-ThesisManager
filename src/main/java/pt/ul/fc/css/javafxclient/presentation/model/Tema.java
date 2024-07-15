package pt.ul.fc.css.javafxclient.presentation.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Tema {

    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty titulo = new SimpleStringProperty();
    private final StringProperty descricao = new SimpleStringProperty();
    private final ListProperty<Mestrado> mestradosCompativeis = new SimpleListProperty<>();
    private final DoubleProperty remuneracaoMensal = new SimpleDoubleProperty();

    public final LongProperty idProperty() {
        return this.id;
    }

    public final long getId() {
        return idProperty().get();
    }

    public final void setId(final long novoId) {
        this.idProperty().set(novoId);
    }

    public final StringProperty tituloProperty() {
        return this.titulo;
    }

    public final String getTitulo() {
        return tituloProperty().get();
    }

    public final void setTitulo(final String novoTitulo) {
        this.tituloProperty().set(novoTitulo);
    }

    public final StringProperty descricaoProperty() {
        return this.descricao;
    }

    public final String getDescricao() {
        return descricaoProperty().get();
    }

    public final void setDescricao(final String novaDescricao) {
        this.descricaoProperty().set(novaDescricao);
    }

    public final ListProperty<Mestrado> mestradosCompativeisProperty() {
        return this.mestradosCompativeis;
    }

    public final List<Mestrado> getMestradosCompativeis() {
        return mestradosCompativeisProperty().get();
    }

    public final void setMestradosCompativeis(final List<Mestrado> novosMestradosCompativeis) {
        ObservableList<Mestrado> observableAlunosList = FXCollections.observableList(novosMestradosCompativeis);
        this.mestradosCompativeisProperty().set(observableAlunosList);
    }

    public final DoubleProperty remuneracaoMensalProperty() {
        return this.remuneracaoMensal;
    }

    public final double getRemuneracaoMensal() {
        return remuneracaoMensalProperty().get();
    }

    public final void setRemuneracaoMensal(final double novaRemuneracaoMensal) {
        this.remuneracaoMensalProperty().set(novaRemuneracaoMensal);
    }
}
