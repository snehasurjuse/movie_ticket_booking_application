package com.MovieTicketBookingApplication.demo.controller;

import com.MovieTicketBookingApplication.demo.model.FoodItem;
import com.MovieTicketBookingApplication.demo.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    // Create a new food item
    @PostMapping("/add")
    public ResponseEntity<FoodItem> addFoodItem(@RequestBody FoodItem foodItem) {
        FoodItem createdItem = foodItemService.addFoodItem(foodItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    // Get all food items
    @GetMapping("/getAll")
    public ResponseEntity<List<FoodItem>> getAllFoodItems() {
        List<FoodItem> foodItems = foodItemService.getAllFoodItems();
        return ResponseEntity.ok(foodItems);
    }

    // Get food item by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<FoodItem> getFoodItemById(@PathVariable int id) {
        FoodItem foodItem = foodItemService.getFoodItemById(id);
        return ResponseEntity.ok(foodItem);
    }

    // Update a food item
    @PutMapping("/update/{id}")
    public ResponseEntity<FoodItem> updateFoodItem(@PathVariable int id, @RequestBody FoodItem updatedItem) {
        FoodItem updatedFoodItem = foodItemService.updateFoodItem(id, updatedItem);
        return ResponseEntity.ok(updatedFoodItem);
    }

    // Delete a food item
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFoodItem(@PathVariable int id) {
        foodItemService.deleteFoodItem(id);
        return ResponseEntity.ok("Food item deleted successfully");
    }
}
