package auth;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth/validate")
public class ValidateTokenEndpoint {

    @GET
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateToken() {
        return Response.ok("OK").build();
    }
}