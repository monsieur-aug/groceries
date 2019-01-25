package com.heb.groceries.resource;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.heb.groceries.model.Product;

@Path("products")
public class ProductResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProducts() {
		final Product p = new Product();
		p.setId(753542);
		p.setDescription("banana");
		p.setLastSold(LocalDate.of(2017, 9, 5));
		p.setShelfLifeDays(4);
		p.setDepartment("Produce");
		p.setPrice(new BigDecimal("2.99"));
		p.setUnit("lb");
		p.setXFor(1);
		p.setCost(new BigDecimal("0.44"));
		
		System.out.println(p);

		return p;
	}
}
