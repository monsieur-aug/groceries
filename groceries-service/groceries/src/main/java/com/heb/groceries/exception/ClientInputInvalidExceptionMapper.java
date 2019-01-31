package com.heb.groceries.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.heb.groceries.model.ErrorResponse;

/**
 * Maps a {@link com.heb.groceries.exception.ClientInputInvalidException} to a
 * response.
 */
@Provider
public class ClientInputInvalidExceptionMapper implements ExceptionMapper<ClientInputInvalidException> {

	@Override
	public Response toResponse(final ClientInputInvalidException ciie) {
		final Status errorStatus = Status.BAD_REQUEST;
		final ErrorResponse errorResponse = new ErrorResponse(errorStatus.getStatusCode(), ciie.getMessage());

		return Response.status(errorStatus).type(MediaType.APPLICATION_JSON).entity(errorResponse).build();
	}

}
