package es.mastercloudapps.security_example.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager; // We need it to check the users

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager; //If the login is OK, we go to the successfulAuth...
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //We obtain the data from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        //We use the data to login
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //We will use the response field to pass the token
        //User that comer from UserDetails!!!
        User user = (User) authResult.getPrincipal();
        //Let's proceed to create the JWT with the user information
        //Algorithm come from auth0 dependency.
        //We choose one algorithm to encrypt.
        //We will use this to sign the JWT and then, refresh the token given to the user.
        //In a production project "secret" should not been here. Maybe in a safer place, and probably, encrypted
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String justAuthenticatedToken = JWT.create()
                .withSubject(user.getUsername()) //Something unique that represents a single user. Could be a primary key too ofc.
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) //Expiration time for the JWT (10 minutes)
                .withIssuer(request.getRequestURL().toString()) //Who made the JWT
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())) //We are mapping the roles the field "roles"
                .sign(algorithm);

        String refreshedToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) //This should change, and it depends on the security and the application.
                .withIssuer(request.getRequestURL().toString()) //Who made the JWT
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())) //We are mapping the roles the field "roles"
                .sign(algorithm);

        response.setHeader("justAuthenticatedToken", justAuthenticatedToken);
        response.setHeader("refreshedToken", refreshedToken);
        //It could be sent in the body, but it is a little more difficult
    }
}
