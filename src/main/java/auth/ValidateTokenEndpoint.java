package auth;

import model.User;
import model.UserDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth/validate")
public class ValidateTokenEndpoint {

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response validateToken(@QueryParam("token") String token,
                                  @QueryParam("username") String username,
                                  @QueryParam("access-domain") String accessDomain) {
        User user;
        if((user = UserDAO.getUser(username)) == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("NOT FOUND").build();
        } else if (user.getToken() == null || !user.getToken().equalsIgnoreCase(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("UNAUTHORIZED").build();
        } else if(user.getUserType().equalsIgnoreCase("normal") && accessDomain.equalsIgnoreCase("wat")) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("UNAUTHORIZED").build();
        } else {
            return Response.status(Response.Status.OK).entity("OK").build();
        }
    }
}