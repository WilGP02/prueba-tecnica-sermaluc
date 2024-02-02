package com.reto.tecnico.wilmer.sermaluc.security.filter;

import com.reto.tecnico.wilmer.sermaluc.security.services.JwtUtilService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtUtilService jwtUtilService;
  private String typeMedia = "application/json";
  private List<String> pathsToSkipAuthorization = Arrays.asList("/actuator/health", "/favicon", "/swagger-ui/index.html", "/swagger-ui", "/v3");
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
          throws ServletException, IOException {
    try {
      String authorizationHeader = request.getHeader("Authorization");
      String email = null;
      String jwt = null;

      String requestURI = request.getRequestURI();

      if (pathsToSkipAuthorization.stream().anyMatch(requestURI::startsWith)) {
        chain.doFilter(request, response);
        return;
      }

      if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
        jwt = authorizationHeader.substring(7);
        email = jwtUtilService.extractUsername(jwt);
      }

      if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
        if (jwtUtilService.validateToken(jwt, userDetails)) {
          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                  userDetails, null, userDetails.getAuthorities());
          authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
      }
    } catch (JwtException ex) {
      response.setContentType(typeMedia);
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.getWriter().println("{ \"message\": \"" + ex.getMessage() + "\" }");
      return;
    }

    chain.doFilter(request, response);

  }
}
