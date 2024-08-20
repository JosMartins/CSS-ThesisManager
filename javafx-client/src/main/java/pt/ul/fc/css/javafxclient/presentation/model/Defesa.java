package pt.ul.fc.css.javafxclient.presentation.model;

import javafx.beans.property.*;

import java.util.Calendar;

public class Defesa {

    private final LongProperty id = new SimpleLongProperty();
    private final ObjectProperty<Calendar> horaInicio = new SimpleObjectProperty<>();
    private final ObjectProperty<Calendar> horaFim = new SimpleObjectProperty<>();
    private final StringProperty sala = new SimpleStringProperty();
    private final DoubleProperty nota = new SimpleDoubleProperty();
    private final StringProperty docente = new SimpleStringProperty();
    private final StringProperty document = new SimpleStringProperty();


    public final LongProperty idProperty() {
        return this.id;
    }

    public final long getId() {
        return idProperty().get();
    }

    public final void setId(final long newId) {
        this.idProperty().set(newId);
    }

    public final ObjectProperty<Calendar> horaInicioProperty() {
        return this.horaInicio;
    }

    public final Calendar getHoraInicio() {
        return horaInicioProperty().get();
    }

    public final void setHoraInicio(final Calendar newHoraInicio) {
        this.horaInicioProperty().set(newHoraInicio);
    }

    public final ObjectProperty<Calendar> horaFimProperty() {
        return this.horaFim;
    }

    public final Calendar getHoraFim() {
        return horaFimProperty().get();
    }

    public final void setHoraFim(final Calendar newHoraFim) {
        this.horaFimProperty().set(newHoraFim);
    }

    public final StringProperty salaProperty() {
        return this.sala;
    }

    public final String getSala() {
        return salaProperty().get();
    }

    public final void setSala(final String newSala) {
        this.salaProperty().set(newSala);
    }

    public final DoubleProperty notaProperty() {
        return this.nota;
    }

    public final double getNota() {
        return notaProperty().get();
    }

    public final void setNota(final double newNota) {
        this.notaProperty().set(newNota);
    }

    public final StringProperty docenteProperty() {
        return this.docente;
    }

    public final String getDocente() {
        return docenteProperty().get();
    }

    public final void setDocente(final String newDocente) {
        this.docenteProperty().set(newDocente);
    }

    public final StringProperty documentProperty() {
        return this.document;
    }

    public final String getDocument() {
        return documentProperty().get();
    }

    public final void setDocument(final String newDocument) {
        this.documentProperty().set(newDocument);
    }


}
