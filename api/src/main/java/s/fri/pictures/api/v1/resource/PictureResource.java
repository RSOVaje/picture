package s.fri.pictures.api.v1.resource;

import si.fri.pictures.models.entities.Picture;
import si.fri.pictures.services.beans.PictureBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@RequestScoped
@Path("/picture")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PictureResource {

    @Inject
    private PictureBean pictureBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getPicture() {
        List<Picture> pictures = pictureBean.getPicture();
        if (pictures == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(pictures).build();
    }

    @POST
    public Response UploadAndSave(Picture picture) {


        picture = pictureBean.createPicture(picture);

        if (picture.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(picture).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(picture).build();
        }
    }

    @GET
    @Path("profil/{idProfila}")
    public Response getPictureByIdProfila(@PathParam("idProfila") Integer idProfila) {
        List<Picture> list = pictureBean.getPictureByPerson(idProfila);
        if (list == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(list).build();
    }


    @GET
    @Path("/{id}")
    public Response getPictureById(@PathParam("id") Integer id) {
        Picture picture = pictureBean.getPictureById(id);
        if (picture == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(picture).build();
    }

}
