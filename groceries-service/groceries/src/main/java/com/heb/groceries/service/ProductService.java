package com.heb.groceries.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.heb.groceries.exception.ClientInputInvalidException;
import com.heb.groceries.exception.DataNotFoundException;
import com.heb.groceries.model.Product;
import com.heb.groceries.model.ProductBuilder;

public class ProductService {

	public List<Product> getAllProducts() {
		final Product banana = new ProductBuilder().id(753542L).description("banana").lastSold(LocalDate.of(2017, 9, 5)).shelfLifeDays(4).department("Produce").price(new BigDecimal("2.99")).unit("lb").xFor(1).cost(new BigDecimal("0.44")).build();
		final Product apples = new ProductBuilder().id(321654L).description("apples").lastSold(LocalDate.of(2017, 9, 6)).shelfLifeDays(7).department("Produce").price(new BigDecimal("1.49")).unit("lb").xFor(1).cost(new BigDecimal("0.20")).build();
		final Product strawberry = new ProductBuilder().id(95175L).description("Strawberry").lastSold(LocalDate.of(2017, 9, 7)).shelfLifeDays(3).department("Produce").price(new BigDecimal("2.49")).unit("lb").xFor(1).cost(new BigDecimal("0.10")).build();
		final Product onion = new ProductBuilder().id(321753L).description("onion").lastSold(LocalDate.of(2017, 9, 8)).shelfLifeDays(9).department("Produce").price(new BigDecimal("1.00")).unit("Each").xFor(1).cost(new BigDecimal("0.05")).build();
		final Product tomato = new ProductBuilder().id(987654L).description("Tomato").lastSold(LocalDate.of(2017, 9, 9)).shelfLifeDays(4).department("Produce").price(new BigDecimal("2.35")).unit("lb").xFor(1).cost(new BigDecimal("0.20")).build();
		final Product grapes = new ProductBuilder().id(11122L).description("grapes").lastSold(LocalDate.of(2017, 9, 10)).shelfLifeDays(7).department("Produce").price(new BigDecimal("4.00")).unit("lb").xFor(1).cost(new BigDecimal("1.20")).build();
		final Product lettuce = new ProductBuilder().id(124872L).description("Lettuce").lastSold(LocalDate.of(2017, 9, 11)).shelfLifeDays(5).department("Produce").price(new BigDecimal("0.79")).unit("lb").xFor(1).cost(new BigDecimal("0.10")).build();
		final Product bread = new ProductBuilder().id(113L).description("bread").lastSold(LocalDate.of(2017, 9, 12)).shelfLifeDays(14).department("Grocery").price(new BigDecimal("1.50")).unit("Each").xFor(1).cost(new BigDecimal("0.12")).build();
		final Product hamburgerBuns = new ProductBuilder().id(1189L).description("hamburger buns").lastSold(LocalDate.of(2017, 9, 13)).shelfLifeDays(14).department("Grocery").price(new BigDecimal("1.75")).unit("Each").xFor(1).cost(new BigDecimal("0.14")).build();
		final Product pastaSauce = new ProductBuilder().id(12221L).description("pasta sauce").lastSold(LocalDate.of(2017, 9, 14)).shelfLifeDays(23).department("Grocery").price(new BigDecimal("3.75")).unit("Each").xFor(1).cost(new BigDecimal("1.00")).build();
		final Product cheeseSlices = new ProductBuilder().id(1111L).description("cheese slices").lastSold(LocalDate.of(2017, 9, 15)).shelfLifeDays(20).department("Grocery").price(new BigDecimal("2.68")).unit("Each").xFor(1).cost(new BigDecimal("0.40")).build();
		final Product slicedTurkey = new ProductBuilder().id(18939L).description("sliced turkey").lastSold(LocalDate.of(2017, 9, 16)).shelfLifeDays(20).department("Grocery").price(new BigDecimal("3.29")).unit("Each").xFor(1).cost(new BigDecimal("0.67")).build();
		final Product tortillaChips = new ProductBuilder().id(90879L).description("tortilla chips").lastSold(LocalDate.of(2017, 9, 17)).shelfLifeDays(45).department("Grocery").price(new BigDecimal("1.99")).unit("Each").xFor(1).cost(new BigDecimal("0.14")).build();
		final Product cereal = new ProductBuilder().id(119290L).description("cereal").lastSold(LocalDate.of(2017, 9, 18)).shelfLifeDays(40).department("Grocery").price(new BigDecimal("3.19")).unit("Each").xFor(1).cost(new BigDecimal("0.19")).build();
		final Product cannedVegetables = new ProductBuilder().id(4629464L).description("canned vegtables").lastSold(LocalDate.of(2017, 9, 19)).shelfLifeDays(200).department("Grocery").price(new BigDecimal("1.89")).unit("Each").xFor(1).cost(new BigDecimal("0.19")).build();
		final Product headacheMedicine = new ProductBuilder().id(9000001L).description("headache medicine").lastSold(LocalDate.of(2017, 9, 20)).shelfLifeDays(365).department("Pharmacy").price(new BigDecimal("4.89")).unit("Each").xFor(1).cost(new BigDecimal("1.90")).build();
		final Product migraineMedicine = new ProductBuilder().id(9000002L).description("Migraine Medicine").lastSold(LocalDate.of(2017, 9, 21)).shelfLifeDays(365).department("Pharmacy").price(new BigDecimal("5.89")).unit("Each").xFor(1).cost(new BigDecimal("1.90")).build();
		final Product coldAndFlu = new ProductBuilder().id(9000003L).description("Cold and Flu").lastSold(LocalDate.of(2017, 9, 22)).shelfLifeDays(365).department("Pharmacy").price(new BigDecimal("3.29")).unit("Each").xFor(1).cost(new BigDecimal("1.90")).build();
		final Product allergyMedicine = new ProductBuilder().id(9000004L).description("Allegry Medicine").lastSold(LocalDate.of(2017, 9, 23)).shelfLifeDays(365).department("Pharmacy").price(new BigDecimal("3.00")).unit("Each").xFor(1).cost(new BigDecimal("1.25")).build();
		final Product pain = new ProductBuilder().id(9000005L).description("Pain").lastSold(LocalDate.of(2017, 9, 24)).shelfLifeDays(365).department("Pharmacy").price(new BigDecimal("2.89")).unit("Each").xFor(1).cost(new BigDecimal("1.00")).build();

		final List<Product> allProducts = new ArrayList<>();
		allProducts.add(banana);
		allProducts.add(apples);
		allProducts.add(strawberry);
		allProducts.add(onion);
		allProducts.add(tomato);
		allProducts.add(grapes);
		allProducts.add(lettuce);
		allProducts.add(bread);
		allProducts.add(hamburgerBuns);
		allProducts.add(pastaSauce);
		allProducts.add(cheeseSlices);
		allProducts.add(slicedTurkey);
		allProducts.add(tortillaChips);
		allProducts.add(cereal);
		allProducts.add(cannedVegetables);
		allProducts.add(headacheMedicine);
		allProducts.add(migraineMedicine);
		allProducts.add(coldAndFlu);
		allProducts.add(allergyMedicine);
		allProducts.add(pain);

		return allProducts;
	}

	public Product findProductWithId(final long id) {
		final List<Product> allProducts = this.getAllProducts();
		Product retrievedProduct = null;

		throwClientInputInvalidExceptionIfInvalidId(id);

		for (final Product product : allProducts) {
			if (product.getId() == id) {
				retrievedProduct = product;
			}
		}

		if (retrievedProduct == null) {
			throw new DataNotFoundException("Product with id " + id + " was not found.");
		}

		return retrievedProduct;
	}

	private void throwClientInputInvalidExceptionIfInvalidId(final long id) {
		try {
			final Product idValidationProduct = new Product();
			idValidationProduct.setId(id);
		} catch (IllegalArgumentException iae) {
			throw new ClientInputInvalidException(iae.getMessage());
		}
	}

}
