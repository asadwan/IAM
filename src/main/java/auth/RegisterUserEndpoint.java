package auth;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("/register")
public class RegisterUserEndpoint {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response RegisterUser(@FormParam("username") String username, @FormParam("password") String password) {
        try {
            register(username, password);
            String token = issueToken(username);
            NewCookie cookie = new NewCookie("token", token, null, null, null, 0, true, true);
            return Response.ok("OK").cookie(cookie).build();
        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

    }

    private String issueToken(String username) {
        return String.valueOf(Math.PI);
    }

    private void register(String username, String password) {

    }

}
