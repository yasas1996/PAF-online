package com.paf.n3ag6.dao;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.paf.n3ag6.database.DbConnection;
import com.paf.n3ag6.models.AddToCard;
import com.paf.n3ag6.models.Item;
import com.paf.n3ag6.models.User;

public class AddToCardDao {
	
	private Connection _dbConnection;
	public AddToCardDao() {
		this._dbConnection = new DbConnection().getConnection();
	}
	
	public boolean addItemToCard(AddToCard addToCard) {
		boolean isSuccess;
		try {

			String sql;
			sql = "INSERT INTO add_to_card (item_id,userId,quantity,total_price) VALUES (?,?,?,?)";

			PreparedStatement stmt = this._dbConnection.prepareStatement(sql);

			stmt.setInt(1, addToCard.getItem_id());
			stmt.setInt(2, addToCard.getUserId());
			stmt.setInt(3, addToCard.getQuantity());
			stmt.setDouble(4, addToCard.getTotal_price());
			isSuccess = stmt.executeUpdate() > 0;

		} catch (Exception ex) {
			System.out.println("[Error][UserDao][addUser] - " + ex.toString());
			isSuccess = false;
		}
		return isSuccess;
	}
	
	public boolean removeItemFromCard(int itemId,int userId) {
		boolean isSuccess;
		try {

			String sql;
			sql = "DELETE FROM add_to_card WHERE item_id=? AND userId=?";

			PreparedStatement stmt = this._dbConnection.prepareStatement(sql);

			stmt.setInt(1,itemId);
			stmt.setInt(2,userId);
			isSuccess = stmt.executeUpdate() > 0;

		} catch (Exception ex) {
			System.out.println("[Error][UserDao][addUser] - " + ex.toString());
			isSuccess = false;
		}
		return isSuccess;
	}
	
	public ArrayList<Item> getAllCardItems(int userid) {
		ArrayList<Item> itemList = new ArrayList<Item>();

		try {
			String sql;
			sql = "SELECT A.item_id,B.category_name,A.quantity,B.price,B.image_path" + 
					" FROM add_to_card A INNER JOIN item_info B ON A.userId ="+userid+" AND A.item_id = B.id";
			
			Statement stmt = this._dbConnection.createStatement();
			//PreparedStatement stmt = this._dbConnection.prepareStatement(sql);
			//stmt.setInt(1, userid);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Item item = new Item();
				item.setCategory_name(rs.getString("category_name"));
				item.setImage_path(rs.getString("image_path"));
				item.setPrice(rs.getDouble("price"));
				item.setId(rs.getInt("item_id"));
				item.setQuantity(rs.getInt("quantity"));


				itemList.add(item);
			}

		} catch (Exception ex) {
			System.out.println("[Error][UserDao][getAllUsers] - " + ex.getMessage());
		}
		return itemList;
	}
	
	public void dispose() {
		try {
			if (!this._dbConnection.isClosed()) {
				System.out.println("[Info][UserDao][dispose] - Closing DB connection");
				this._dbConnection.close();
			}
		} catch (Exception e) {
			System.out.println("[Error][UserDao][dispose] - " + e.getMessage());
		}
	}

}
