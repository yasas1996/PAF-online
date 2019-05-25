package com.paf.n3ag6.services;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.paf.n3ag6.dao.ProductDao;
import com.paf.n3ag6.dao.UserDao;
import com.paf.n3ag6.models.Product;
import com.paf.n3ag6.models.ProductUpdateModel;


@Path("/product")
public class ProductService {

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Product> GetUserList() {
		ProductDao productDao = new ProductDao();
		try {
			ArrayList<Product> productList = new ArrayList<Product>();
			productList = productDao.getAllProducts();
			return productList;
		} finally {
			productDao.dispose();
		}
	}

	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product GetProduct(@PathParam("name") String name) {
		ProductDao userDao = new ProductDao();
		try {
			Product product = new Product();
			product = userDao.getProduct(name);
			return product;
		} finally {
			userDao.dispose();
			System.out.println("[Info][UserService][GetUser] - userDao disposed.");
		}
	}

	@DELETE
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean DeleteProduct(@PathParam("name") String name) {
		ProductDao productDao = new ProductDao();
		try {
			return productDao.deleteProduct(name);
		} finally {
			productDao.dispose();
			System.out.println("[Info][ProductService][DeleteProduct] - productDao disposed.");
		}
	}
	
	@POST
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean AddProduct(Product pro) {
		ProductDao userDao = new ProductDao();
		try {
			
			return userDao.addProduct(pro);
		} finally {
			userDao.dispose();
		}
	}

	@PUT
	@Path("/{name}")
	public Response EditUserStatus(@PathParam("name") String name) {

		String output = "Jersey say : ";

		return Response.status(200).entity(output).build();

	}

	@PUT
	@Path("")
	public boolean EditUser(ProductUpdateModel product) {
		ProductDao productDao = new ProductDao();
		try {
			
				return productDao.updateProduct(product);
			
		} finally {
			productDao.dispose();
		}
	}

	
	@POST
	@Path("/{name}/address")
	public Response AddAddressStatus(@PathParam("name") String name) {

		String output = "Jersey say : ";

		return Response.status(200).entity(output).build();

	}

	@DELETE
	@Path("/{name}/address/{id}")
	public Response DeleteAddressStatus(@PathParam("name") String name, @PathParam("id") int id) {

		String output = "Jersey say : ";

		return Response.status(200).entity(output).build();

	}

//	@POST
//	@Path("/login")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public AuthResponse Authenticate(AuthRequest authRequest) {
//
//		UserDao userDao = new UserDao();
//		try {
//			AuthResponse authResponse = new AuthResponse();
//			authResponse = userDao.authenticate(authRequest.getUsername(), authRequest.getPassword());
//			return authResponse;
//		} finally {
//			userDao.dispose();
//			System.out.println("[Info][UserService][Authenticate] - userDao disposed.");
//		}
//
//	}

}

