package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    // ObservableList data structures to store parts and products
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    // Method to add new part to the list
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    // Method to add new product to the list
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    // Method to look up a part by its ID
    public static Part lookupPart(int partId) {
        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    // Method to look up a product by its ID
    public static Product lookupProduct(int productId) {
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    // Method to look up a part by its name, returns a list of parts that match
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().equalsIgnoreCase(partName)) {
                namedParts.add(part);
            }
        }
        return namedParts;
    }

    // Method to look up a product by its name, returns a list of products that match
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> namedProducts = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().equalsIgnoreCase(productName)) {
                namedProducts.add(product);
            }
        }
        return namedProducts;
    }

    // Method to update a part at a specific index in the list
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    // Method to update a product at a specific index in the list
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    // Method to delete a specific part from the list
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    // Method to delete a specific product from the list
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    // Method to get a list of all parts in inventory
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    // Method to get a list of all products in inventory
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    //Method to automatically populate Part ID
    public static int getNextPartID() {
        if (allParts.isEmpty()) {
            return 1;
        } else {
            int maxID = 0;
            for (Part part : allParts) {
                if (part.getId() > maxID) {
                    maxID = part.getId();
                }
            }
            return maxID + 1;
        }
    }

    //Method to automatically populate Product ID
    public static int getNextProductID() {
        if (allProducts.isEmpty()) {
            return 1;
        } else {
            int maxID = 0;
            for (Product product : allProducts) {
                if (product.getId() > maxID) {
                    maxID = product.getId();
                }
            }
            return maxID + 1;
        }
    }

}

