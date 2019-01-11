package com.unitedremote.shops.security.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,authorization");
        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, DELETE");

        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else if(request.getRequestURI().equals("/v1/users/login")) {
            filterChain.doFilter(request, response);
            return;
        }else {
            try {
                String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
                if (token != null && jwtTokenProvider.validateToken(token)) {
                    Authentication auth = jwtTokenProvider.getAuthentication(token);

                    if (auth != null) {
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                }
                filterChain.doFilter(request, response);
            } catch (InvalidJwtAuthenticationException e) {
                //response.getWriter().println(e.getMessage());
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
//        throws IOException, ServletException {
//
//        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
//        try {
//            if (token != null && jwtTokenProvider.validateToken(token)) {
//                Authentication auth = jwtTokenProvider.getAuthentication(token);
//
//                if (auth != null) {
//                    SecurityContextHolder.getContext().setAuthentication(auth);
//                }
//            }
//            filterChain.doFilter(req, res);
//        } catch (InvalidJwtAuthenticationException e) {
//            new ObjectMapper().writeValue(res.getWriter(), e.getMessage());
//        }
//     }

}
