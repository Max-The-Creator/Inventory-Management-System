package model;

/**
 * The Outsourced class is a subclass of Part, representing parts that are outsourced from other companies.
 */
public class Outsourced extends Part {
    private String companyName;

    /**
     * Constructs an Outsourced object.
     *
     * @param id The ID of the part.
     * @param name The name of the part.
     * @param price The price of the part.
     * @param stock The stock level of the part.
     * @param max The maximum stock level of the part.
     * @param min The minimum stock level of the part.
     * @param companyName The name of the company from which the part is outsourced.
     */
    public Outsourced(int id, String name, double price, int stock, int max, int min, String companyName) {
        super(id, name, price, stock, max, min);
        this.companyName = companyName;
    }

    /**
     * Returns the company name from which the part is outsourced.
     *
     * @return The company name.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the company name from which the part is outsourced.
     *
     * @param companyName The company name.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
