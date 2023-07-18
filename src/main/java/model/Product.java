package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Product class
 *
 * @author Maximillian
 */
public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private ObservableList<Part> associatedParts;

    public Product(int id, String name, double price, int stock, int min, int max, ObservableList<Part> associatedParts) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.associatedParts = FXCollections.observableArrayList(associatedParts);

    }


    // getters and setters
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Returns a copy of the list of associated parts of this product.
     *
     * @return The list of associated parts.
     */
    public List<Part> getAssociatedParts() {
        return this.associatedParts;
    }

    /**
     * Adds a part to the list of associated parts of this product if it's not already in the list.
     *
     * @param part The part to be added.
     */
    public void addAssociatedPart(Part part) {
        if (part != null && !associatedParts.contains(part)) {
            associatedParts.add(part);
        }
    }

    /**
     * Removes a part from the list of associated parts of this product.
     *
     * @param part The part to be removed.
     * @return True if the part was successfully removed, false otherwise.
     */
    public boolean removeAssociatedPart(Part part) {
        if (part != null) {
            return associatedParts.remove(part);
        }
        return false;
    }
}
