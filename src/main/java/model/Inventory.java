package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Inventory class is responsible for storing and managing all the parts and products.
 */
public class Inventory {
    // ObservableList data structures to store parts and products
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Adds a new part to the parts list.
     *
     * @param newPart The part to be added.
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Adds a new product to the product list.
     *
     * @param newProduct The product to be added.
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Looks up a part by its ID.
     *
     * @param partId The ID of the part.
     * @return The part if found, null otherwise.
     */
    public static Part lookupPart(int partId) {
        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    /**
     * Looks up a product by its ID.
     *
     * @param productId The ID of the product.
     * @return The product if found, null otherwise.
     */
    public static Product lookupProduct(int productId) {
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    /**
     * Looks up a part by its name and returns a list of parts that match.
     *
     * @param partName The name of the part.
     * @return The list of matching parts.
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().equalsIgnoreCase(partName)) {
                namedParts.add(part);
            }
        }
        return namedParts;
    }

    /**
     * Looks up a product by its name and returns a list of products that match.
     *
     * @param productName The name of the product.
     * @return The list of matching products.
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> namedProducts = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().equalsIgnoreCase(productName)) {
                namedProducts.add(product);
            }
        }
        return namedProducts;
    }

    /**
     * Updates a part at a specific index in the list.
     *
     * @param index The index of the part to be updated.
     * @param selectedPart The new part to replace the existing part.
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * Updates a product at a specific index in the list.
     *
     * @param index The index of the product to be updated.
     * @param newProduct The new product to replace the existing product.
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**
     * Deletes a specific part from the list.
     *
     * @param selectedPart The part to be deleted.
     * @return true if the part was deleted, false otherwise.
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * Deletes a specific product from the list.
     *
     * @param selectedProduct The product to be deleted.
     * @return true if the product was deleted, false otherwise.
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /**
     * Gets a list of all parts in inventory.
     *
     * @return An ObservableList of all parts.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Gets a list of all products in inventory.
     *
     * @return An ObservableList of all products.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * Automatically generates the next ID for a part.
     *
     * @return The next part ID.
     */
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

    /**
     * Automatically generates the next ID for a product.
     *
     * @return The next product ID.
     */
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
