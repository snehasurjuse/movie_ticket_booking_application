package com.MovieTicketBookingApplication.demo.service;

import com.MovieTicketBookingApplication.demo.exception.FoodItemAlreadyExistsException;
import com.MovieTicketBookingApplication.demo.exception.FoodItemNotFoundException;
import com.MovieTicketBookingApplication.demo.model.FoodItem;
import com.MovieTicketBookingApplication.demo.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    // Add a new food item
    public FoodItem addFoodItem(FoodItem foodItem) {
        Optional<FoodItem> existing = foodItemRepository.findByName(foodItem.getName());
        if(existing.isPresent()){
            throw new FoodItemAlreadyExistsException("Food item with name '" + foodItem.getName() + "' already exists.");
        }
        return foodItemRepository.save(foodItem);
    }

    // Get all food items
    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    // Get food item by id
    public FoodItem getFoodItemById(int id) {
        return foodItemRepository.findById(id)
                .orElseThrow(() -> new FoodItemNotFoundException("Food item not found with ID: " + id));
    }

    // Update a food item
    public FoodItem updateFoodItem(int id, FoodItem updatedItem) {
        FoodItem existing = getFoodItemById(id);
        existing.setName(updatedItem.getName());
        existing.setPrice(updatedItem.getPrice());
        return foodItemRepository.save(existing);
    }

    // Delete a food item
    public void deleteFoodItem(int id) {
        FoodItem existing = getFoodItemById(id);
        foodItemRepository.delete(existing);
    }
}
