package com.anand.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.anand.vo.Product;

@Path("/json/second")
public class SecondRestEasyService {

	@GET
	@Path("/product/{name}")
	@Produces("application/json")
	public Product getProduct(@PathParam("name") String name)
	{
		List<Product> prodList = addProducts();
		for (Product p : prodList) {
			if (p.getName().equalsIgnoreCase(name))
				return p;
		}

		return null;
	}

	@GET
	@Path("/getQuery")
	@Produces("text/plain")
	public String getQueryParam(@QueryParam("name") int name, @HeaderParam("Content-Type") MediaType contentType,
			@HeaderParam("Host") String agent,@Context HttpHeaders headers)
	{
		System.out.println("Host - "+headers.getRequestHeaders());
		Iterator it=headers.getRequestHeaders().entrySet().iterator();
		
		it=headers.getRequestHeaders().keySet().iterator();
		while(it.hasNext()){
			String key=(String)it.next();
			System.out.println(key+" - "+headers.getRequestHeaders().get(key));
		}
		System.out.println("Agent - " + agent);
		if (contentType != null) {
			System.out.println("Ffrom - " + contentType.toString());
		}
		return "We got the name as - " + name;
	}

	@GET
	@Path("/get")
	@Produces("application/json")
	public Product getDataFromSecondService()
	{
		Product p = new Product();
		p.setName("Samsung-second-??");
		p.setQty(600);

		return p;
	}

	@GET
	@Path("/getPlain")
	@Produces("text/plain")
	public Product getPlainData()
	{
		Product p = new Product();
		p.setName("Samsung in Plain");
		p.setQty(600);

		return p;
	}

	@GET
	@Path("/getXml")
	@Produces("application/xml")
	public Product getXmlData()
	{
		Product p = new Product();
		p.setName("Samsung in XML");
		p.setQty(600);

		return p;
	}

	// @FormParam can be used to get the value from the form

	private List<Product> addProducts()
	{
		List<Product> prodList = new ArrayList<Product>();
		Product e = new Product();
		e.setName("tv");
		e.setQty(56);
		prodList.add(e);
		e = new Product();
		e.setName("radio");
		e.setQty(34);
		prodList.add(e);
		e = new Product();
		e.setName("mouse");
		e.setQty(44);
		prodList.add(e);

		return prodList;
	}

}
