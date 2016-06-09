/**
 * 
 */
package com.anand.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.anand.vo.Product;

/**
 * @author Anand
 *
 */
@Path("json/product")
public class JsonService {

	@GET
	@Path("/get")
	@Produces("application/json")
	public Product getProductInJson()
	{
		Product product = new Product();
		product.setName("iPad");
		product.setQty(3);

		return product;
	}

	@POST
	@Path("/post")
	@Consumes("application/json")
	@Produces("text/plain")
	public String createProductInJson(Product product)
	{
		String result = "Product created - Plain format - " + product;
		return result;
//		return Response.status(201).entity(result).build();
//		return Response.status(201).entity(true).build();
	}
}
