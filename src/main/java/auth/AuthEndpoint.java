package auth;



import org.apache.commons.net.ftp.FTPClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AuthEndpoint {


    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(@FormParam("username") String username, @FormParam("password") String password) {
        try {
            authenticate(username, password);
            String token = issueToken(username);
            NewCookie cookie = new NewCookie("token", token, null,null,null, 0,true,  true );
            return Response.ok("OK").cookie(cookie).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }


    private void authenticate(String username, String password) throws Exception {
        if ("asadwan".equalsIgnoreCase(username) && "aaaaaa".equalsIgnoreCase(password)) {
            System.out.println(username);
        } else {
            throw new Exception();
        }
    }

    private String issueToken(String username) {
        FTPClient ftpClient = new FTPClient();
        return String.valueOf(Math.PI);
    }
}