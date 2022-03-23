package com.revature;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;

import java.sql.SQLException;

import com.revature.controllers.ItemController;

import io.javalin.Javalin;


public class Driver {
	
	public static void main(String[] args) throws SQLException {

		Javalin app = Javalin.create((config) -> {
			config.enableCorsForAllOrigins();
			config.defaultContentType = "application/json";
		});

		app.start(8080);

		app.routes(() -> {
			path("items", () -> {
				get(ItemController::getItems);
				post(ItemController::addItems);
							
				path("{itemId}", () -> {
					get(ItemController::getItemById);
					put(ItemController::updateItem);
					delete(ItemController::deleteItem);
				});
				});
			});
	}
}