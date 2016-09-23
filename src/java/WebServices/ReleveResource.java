/*
 * 
 */
package WebServices;

import Actions.ReleveAction;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * REST Web Service
 *
 * @author nicolas
 */
@Path("releve")
public class ReleveResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ReleveResource
     */
    public ReleveResource() {
    }

    /**
     * Retrieves representation of an instance of WebServices.ReleveResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of ReleveResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes ("application/String")
    @Path("session/{session_id}/date/{datetime}")
    public Response getRelevesJson(@PathParam("session_id") int id, @PathParam("datetime") String date) throws JSONException {
        JSONArray relevesJson = (new ReleveAction()).getReleves(id,date);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(relevesJson.toString()).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes ("application/String")
    @Path("session/{session_id}")
    public Response getAllRelevesJson(@PathParam("session_id") int id) throws JSONException {
        JSONArray relevesJson = (new ReleveAction()).getAllReleves(id);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(relevesJson.toString()).build();
    }
    
    
}
