package accesscontrol;


import model.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/access-control/")
public class JournalAccessEndpoint {


    @Path("journal/{journal-id}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response grantJournalAccess(@PathParam("journal-id") String journalId, @QueryParam("username") String username) {
        License journalLicense = getJournalLicense(journalId);
        Set<License> userLicences = getUserLicences(username);
        if(journalLicense == null) return Response.status(Response.Status.NOT_FOUND).entity("NOT FOUND").build();
        Boolean doesUserHasLicenseToJournal = userLicences.stream().anyMatch(
                license -> license.getLicenseId().equalsIgnoreCase(journalLicense.getLicenseId()));
        if(doesUserHasLicenseToJournal || getUserType(username).equalsIgnoreCase("admin")) {
            return Response.status(Response.Status.OK).entity("Access Granted").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Access Denied").build();
        }
    }

    private Set<License> getUserLicences(String username) {
        User user = UserDAO.getUser(username);
        return user.getLicenses();
    }

    private String getUserType(String username) {
        User user = UserDAO.getUser(username);
        return user.getUserType();
    }

    private License getJournalLicense(String journalId) {
        PublicationDAO journalDAO = new JournalDAO();
        Journal journal = (Journal) journalDAO.retrieve(journalId);
        if(journal == null) return null;
        return journal.getLicense();
    }

}
