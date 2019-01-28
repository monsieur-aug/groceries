package com.heb.groceries.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.heb.groceries.model.ErrorResponse;

@Provider
public class UnsupportedQueryExceptionMapper implements ExceptionMapper<UnsupportedQueryException> {

	@Override
	public Response toResponse(final UnsupportedQueryException uqe) {
		final Status errorStatus = Status.BAD_REQUEST;
		final ErrorResponse errorResponse = new ErrorResponse(errorStatus.getStatusCode(), uqe.getMessage());

		return Response.status(errorStatus).type(MediaType.APPLICATION_JSON).entity(errorResponse).build();
	}

}
