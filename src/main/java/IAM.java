import accesscontrol.JournalAccessEndpoint;
import auth.AuthEndpoint;
import auth.ValidateTokenEndpoint;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarOutputStream;

@ApplicationPath("/")
public class IAM extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(AuthEndpoint.class );
        h.add(ValidateTokenEndpoint.class);
        h.add(JournalAccessEndpoint.class);
        return h;
    }

}
