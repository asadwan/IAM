package auth;

import com.mysql.cj.x.protobuf.Mysqlx;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import model.User;
import model.UserDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.security.Key;
import org.json.*;


@Path("/auth")
public class AuthEndpoint {


    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(@FormParam("username") String username, @FormParam("password") String password) {
        JSONObject resposeJson = new JSONObject();
        try {
            authenticate(username, password);
            String token = issueToken(username);
            resposeJson.put("message", "OK");
            resposeJson.put("statusCode", 200);
            NewCookie cookie = new NewCookie("token", token, null, null, null, 0, true, true);
            return Response.ok(resposeJson.toString()).cookie(cookie).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            resposeJson.put("message", "UNAUTHORIZED");
            resposeJson.put("statusCode", 401);
            return Response.status(Response.Status.UNAUTHORIZED).entity(resposeJson.toString()).build();
        }
    }


    private void authenticate(String username, String password) throws Exception {

        User user = UserDAO.getUser(username);
        System.out.println(user.getName());
        if (!user.getPassword().equals(password)) {
            throw new Exception();
        }
    }

    private String issueToken(String username) {

        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        //String jws = Jwts.builder().setSubject(username).setExpiration(new Date() + 1800)
        return "";
    }
}