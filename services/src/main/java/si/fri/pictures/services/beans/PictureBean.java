package si.fri.pictures.services.beans;

import si.fri.pictures.models.entities.Picture;
import si.fri.pictures.services.configuration.AppProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.client.Client;
import javax.
        ws.rs.client.ClientBuilder;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class PictureBean {

    private Logger log = Logger.getLogger(PictureBean.class.getName());

    @Inject
    private EntityManager em;

    @Inject
    private AppProperties appProperties;

    @Inject
    private PictureBean pictureBean;

    private Client httpClient;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
//        baseUrl = "http://localhost:8081"; // only for demonstration
    }

    public List<Picture> getPictureByPerson(Integer idProfila) {

        TypedQuery<Picture> query = em.createNamedQuery("Picture.getByIdProfile", Picture.class).setParameter("idProfila", idProfila);

        return query.getResultList();

    }
    public List<Picture> getPicture() {

        TypedQuery<Picture> query = em.createNamedQuery("Picture.getAll", Picture.class);

        return query.getResultList();

    }

    public Picture getPictureById(Integer id) {

        TypedQuery<Picture> query = em.createNamedQuery("Picture.getById", Picture.class).setParameter("id", id);

        return query.getSingleResult();

    }

    public Picture createPicture(Picture picture) {

        try {
            beginTx();
            em.persist(picture);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return picture;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void commitTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }


}
