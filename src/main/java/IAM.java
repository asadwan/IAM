import auth.AuthEndpoint;
import auth.AuthenticationFilter;
import auth.ValidateTokenEndpoint;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class IAM extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(RegisterUserEndPoint.class);
        h.add( AuthEndpoint.class );
        h.add(ValidateTokenEndpoint.class);
        h.add(AuthenticationFilter.class);
        return h;
    }
}
