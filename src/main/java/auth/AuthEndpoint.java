package auth;

import model.User;
import model.UserDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.*;
import utility.Utility;

import java.util.Date;


@Path("/auth/login")
public class AuthEndpoint {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(@FormParam("username") String username, @FormParam("password") String password,
                                     @FormParam("user-type") String userType) {
        JSONObject response = new JSONObject();
        boolean isAuthentic = authenticate(username, password, userType);
        if (isAuthentic) {
            String token = issueToken(username);
            response.put("message", "OK");
            response.put("statusCode", 200);
            response.put("token", token);
            return Response.status(Response.Status.OK).entity(response.toString()).build();
        } else {
            response.put("message", "NOT FOUND");
            response.put("statusCode", 404);
            return Response.status(Response.Status.NOT_FOUND).entity(response.toString()).build();
        }
    }

    private boolean authenticate(String username, String password, String userType) {
        User user = UserDAO.getUser(username);
        if ((user == null) || !user.getPasswordHash().equals(Utility.sha160(password))) {
            return false;
        } else if (!user.getUserType().equalsIgnoreCase("admin")
                && !user.getUserType().equalsIgnoreCase(userType)) {
            return false;
        }
        return true;
    }

    private String issueToken(String username) {
        String token = Utility.sha160(username + new Date());
        User user = UserDAO.getUser(username);
        user.setToken(token);
        try {
            UserDAO.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
}