package com.heb.groceries.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.heb.groceries.model.ErrorResponse;

/**
 * Maps a {@link com.heb.groceries.exception.DataNotFoundException} to a
 * response.
 */
@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(final DataNotFoundException dnfe) {
		final Status errorStatus = Status.NOT_FOUND;
		final ErrorResponse errorResponse = new ErrorResponse(errorStatus.getStatusCode(), dnfe.getMessage());

		return Response.status(errorStatus).type(MediaType.APPLICATION_JSON).entity(errorResponse).build();
	}

}
