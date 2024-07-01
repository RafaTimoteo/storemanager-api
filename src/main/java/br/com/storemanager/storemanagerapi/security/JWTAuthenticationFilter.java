package br.com.storemanager.storemanagerapi.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.storemanager.storemanagerapi.Exceptions.GlobalExceptionHandler;
import br.com.storemanager.storemanagerapi.models.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    
    private JWTUtil jwtUtil;

    // Construtor
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        setAuthenticationFailureHandler(new GlobalExceptionHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // Log para verificar se o método foi chamado
            System.out.println("Iniciando a tentativa de autenticação...");
            
            User userCredentials = new ObjectMapper().readValue(request.getInputStream(), User.class);

            // Log para verificar se as credenciais do usuário foram lidas corretamente
            System.out.println("Credenciais do usuário lidas: " + userCredentials.getUsername());

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userCredentials.getUsername(), 
                    userCredentials.getSenha(), 
                    new ArrayList<>()
            );

            // Log para verificar se o token de autenticação foi criado
            System.out.println("Token de autenticação criado: " + authToken);

            Authentication authentication = this.authenticationManager.authenticate(authToken);

            // Log para verificar se a autenticação foi bem-sucedida
            System.out.println("Autenticação bem-sucedida: " + authentication);

            return authentication;
        } catch (IOException e) {
            // Log para capturar a exceção
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
    FilterChain filterChain, Authentication authentication) throws IOException, ServletException {
        UserSpringSecurity userSpringSecurity = (UserSpringSecurity) authentication.getPrincipal();
        String username = userSpringSecurity.getUsername();
        String token = this.jwtUtil.generateToken(username);
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
    }

    // Getters e setters
    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public JWTUtil getJwtUtil() {
        return jwtUtil;
    }

    public void setJwtUtil(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
}
