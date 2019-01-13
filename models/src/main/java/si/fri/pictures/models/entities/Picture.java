package si.fri.pictures.models.entities;

import javax.persistence.*;

@Entity(name = "picture")
@NamedQueries(value = {
        @NamedQuery(name = "Pictures.getByIdProfile", query = "SELECT p FROM picture p WHERE p.idProfila = :idProfila"),
        @NamedQuery(name = "Pictures.getById", query = "SELECT p FROM picture p WHERE p.id = :id"),
        @NamedQuery(name = "Pictures.getAll", query = "SELECT p FROM picture p")
})
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Byte[] pictureByte;

    private Integer idProfila;

    private String opis;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte[] getPictureByte() {
        return pictureByte;
    }

    public void setPictureByte(Byte[] pictureByte) {
        this.pictureByte = pictureByte;
    }

    public Integer getIdProfila() {
        return idProfila;
    }

    public void setIdProfila(Integer idProfila) {
        this.idProfila = idProfila;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
