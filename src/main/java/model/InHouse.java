package model;

/**
 * Represents an In-House part in the inventory. An In-House part is a subclass of Part and contains
 * an additional property for the machine ID.
 */
public class InHouse extends Part {
    private int machineId;

    /**
     * Initializes a new instance of the InHouse class.
     *
     * @param id The ID of the part.
     * @param name The name of the part.
     * @param price The price of the part.
     * @param stock The stock level of the part.
     * @param max The maximum allowable quantity of the part.
     * @param min The minimum allowable quantity of the part.
     * @param machineId The machine ID of the part.
     */
    public InHouse(int id, String name, double price, int stock, int max, int min, int machineId) {
        super(id, name, price, stock, max, min);
        this.machineId = machineId;
    }

    /**
     * Gets the machine ID of the part.
     *
     * @return The machine ID of the part.
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * Sets the machine ID of the part.
     *
     * @param machineId The new machine ID of the part.
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
