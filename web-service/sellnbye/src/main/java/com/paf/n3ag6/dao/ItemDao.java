package com.paf.n3ag6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.paf.n3ag6.database.DbConnection;
import com.paf.n3ag6.models.User;
import com.paf.n3ag6.models.Enums.UserType;
import com.paf.n3ag6.models.Item;

public class ItemDao {
	private Connection _dbConnection;
	public ItemDao() {
		this._dbConnection = new DbConnection().getConnection();
	}
	public ArrayList<Item> getAllItems() {
		ArrayList<Item> itemList = new ArrayList<Item>();

		try {
			Statement stmt = this._dbConnection.createStatement();
			String sql;
			sql = "SELECT * FROM item_info";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Item item = new Item();
				item.setCategory_name(rs.getString("category_name"));
				item.setImage_path(rs.getString("image_path"));
				item.setPrice(rs.getDouble("price"));
				item.setId(rs.getInt("id"));
				item.setQuantity(rs.getInt("quantity"));


				itemList.add(item);
			}

		} catch (Exception ex) {
			System.out.println("[Error][UserDao][getAllUsers] - " + ex.getMessage());
		}
		return itemList;
	}
	
	public Item getItem(int item_id) {
		Item item = new Item();

		try {
			String sql;
			sql = "SELECT * FROM item_info Where id=?";
			PreparedStatement stmt = this._dbConnection.prepareStatement(sql);
			stmt.setInt(1,item_id);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				item.setCategory_name(rs.getString("category_name"));
				item.setImage_path(rs.getString("image_path"));
				item.setPrice(rs.getDouble("price"));
				item.setId(rs.getInt("id"));
				item.setQuantity(rs.getInt("quantity"));
			}
			

		} catch (Exception ex) {
			System.out.println("[Error][UserDao][getAllUsers] - " + ex.getMessage());
		}
		return item;
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
