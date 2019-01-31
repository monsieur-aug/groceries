package com.heb.groceries.cors;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 * Configures cross-origin resource sharing (CORS) settings for the service. In
 * particular, the <code>Access-Control-Allow-Origin</code> property specifies
 * which domain, protocol, and port have permission to access the service.
 */
@Provider
public class CorsFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		responseContext.getHeaders().add("Access-Control-Allow-Credentials", "false");
		responseContext.getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Accept");
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET,OPTIONS");
	}

}
