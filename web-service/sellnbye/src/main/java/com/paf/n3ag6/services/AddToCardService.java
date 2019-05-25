package com.paf.n3ag6.services;

import java.awt.PageAttributes.MediaType;
import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.paf.n3ag6.dao.AddToCardDao;
import com.paf.n3ag6.dao.ItemDao;
import com.paf.n3ag6.models.AddToCard;
import com.paf.n3ag6.models.Item;

@Path("/card")
public class AddToCardService {
	
	@GET
	@Path("/getItemList")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Item> GetItemList() {
		ItemDao itemDao = new ItemDao();
		try {
			ArrayList<Item> itemList = new ArrayList<Item>();
			itemList = itemDao.getAllItems();
			return itemList;
		} finally {
			itemDao.dispose();
		}
	}
	
	@GET
	@Path("/getAllCardItems/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Item> GetAllCardItems(@PathParam("userId") int userId) {
		AddToCardDao addTocardDao=new AddToCardDao();
		ItemDao itemDao = new ItemDao();
		try {
			ArrayList<Item> itemList = new ArrayList<Item>();
			itemList = addTocardDao.getAllCardItems(userId);
			return itemList;
		} finally {
			itemDao.dispose();
		}
	}
	
	@POST
	@Path("/add/{item_id}/{user_id}/{quantity}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response AddItemToCard(@PathParam("item_id") int item_id, @PathParam("user_id") int user_id,@PathParam("quantity") int quantity) {
		
		AddToCardDao addTocardDao=new AddToCardDao();

		ItemDao itemDao=new ItemDao();
		Item item=itemDao.getItem(item_id);
		AddToCard addToCard=new AddToCard();
		addToCard.setItem_id(item_id);
		addToCard.setQuantity(quantity);
		addToCard.setUserId(user_id);
		addToCard.setTotal_price(0);

		if(addTocardDao.addItemToCard(addToCard)) {
			return Response.status(200).entity("Added successfully").build();
		}
		else {
			return Response.status(404).entity("Failure to add in card").build();
		}
	}
	
	@DELETE
	@Path("/removeItemFromCard/{item_id}/{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response RemoveItemFromCard(@PathParam("item_id") int item_id, @PathParam("user_id") int user_id) {
		
		AddToCardDao addTocard=new AddToCardDao();
		if(addTocard.removeItemFromCard(item_id, user_id)) {
			return Response.status(200).entity("Remove successfully").build();
		}
		else {
			return Response.status(404).entity("Item Id / user not available in add to card").build();
		}

	}

}
