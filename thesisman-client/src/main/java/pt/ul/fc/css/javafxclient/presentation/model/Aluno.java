package pt.ul.fc.css.javafxclient.presentation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.beans.property.*;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Aluno {

    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty username = new SimpleStringProperty();
    private final DoubleProperty media = new SimpleDoubleProperty();
    private final ObjectProperty<Mestrado> mestrado = new SimpleObjectProperty<>();
    private final ObjectProperty<Tese> tese = new SimpleObjectProperty<>();
    private final StringProperty token = new SimpleStringProperty();

    public final LongProperty idProperty() {
        return this.id;
    }

    public final long getId() {
        return idProperty().get();
    }

    public final void setId(final long newId) {
        this.idProperty().set(newId);
    }

    public final StringProperty usernameProperty() {
        return this.username;
    }

    public final String getUsername() {
        return usernameProperty().get();
    }

    public final void setUsername(final String newName) {
        this.usernameProperty().set(newName);
    }

    public final DoubleProperty mediaProperty() {
        return this.media;
    }

    public final double getMedia() {
        return mediaProperty().get();
    }

    public final void setMedia(final double newMedia) {
        this.mediaProperty().set(newMedia);
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

    public final StringProperty tokenProperty() {
        return this.token;
    }

    public final ObjectProperty<Mestrado> mestradoProperty() {
        return this.mestrado;
    }

    public final Mestrado getMestrado() {
        return mestradoProperty().get();
    }

    public final void setMestrado(final Mestrado newMestrado) {
        this.mestradoProperty().set(newMestrado);
    }

    public final String getToken() {
        return tokenProperty().get();
    }

    public final void setToken(final String newToken) {
        this.tokenProperty().set(newToken);
    }

}
