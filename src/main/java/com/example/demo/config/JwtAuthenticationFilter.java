package com.example.demo.config;

import java.io.IOException;

//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.security.JwtUtil;
import com.example.demo.service.CustomUserDetailsService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		System.out.println("Auth Header : " +authHeader);
		String username = null;
		String jwtToken = null;
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			jwtToken = authHeader.substring(7);
			username = jwtUtil.getUsernameFromToken(jwtToken);
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
			if (jwtUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}

		// Specify the allowed origin domains
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		// Specify the allowed HTTP methods
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		// Specify the allowed headers
		response.setHeader("Access-Control-Allow-Headers", "*");
		// Enable support for credentials (e.g., cookies)
		response.setHeader("Access-Control-Allow-Credentials", "true");

		filterChain.doFilter(request, response);
	}

//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//
//
//		// get jwt
//		// Starts with Bearer
//		// validate the token
//
//		String requestTokenHeader = request.getHeader("Authorization");
//		// System.out.println("Authorization: " + authorization);
//		String username = null;
//		String jwtToken = null;
//
//		// Checking if Suhtorization header is null and format of it
//		// JWT Token is in the form "Bearer token". Remove Bearer word and get only the
//		// Token
//		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
//			jwtToken = requestTokenHeader.substring(7);
//
//			try {
//
//				username = jwtUtil.getUsernameFromToken(jwtToken);
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			// Once we get the token validate it.
//			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//				UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
//				// security
//
//				// if token is valid configure Spring Security to manually set authentication
//				if (jwtUtil.validateToken(jwtToken, userDetails)) {
//
//					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//							userDetails, null, userDetails.getAuthorities());
//					System.out.println("After token generated" + usernamePasswordAuthenticationToken);
//
//					usernamePasswordAuthenticationToken
//							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//					// After setting the Authentication in the context, we specify
//					// that the current user is authenticated. So it passes the
//					// Spring Security Configurations successfully.
//					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//
//				} else {
//					System.out.println("Token is not validated..");
//				}
//			}
//		}
//
//		filterChain.doFilter(request, response);
//
//	}


}
