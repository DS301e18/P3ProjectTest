package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.List;

public class Product {

    /**
     * Field
     **/

    private int id;
    private String name;
    private int batchSize;
    private BigDecimal price;
    //private final List<Batch> batches = new ArrayList<Batch>();
  
    public Product(String name, int batchSize, BigDecimal price){
        this.name = name;
        //this.productNumber = productNumber;
        this.batchSize = batchSize;
        this.price = price;

    }

    public Product() {
    }

    /** Methods **/

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBatchSize() {
        return batchSize;
    }

    //funktion til at tælle antal af vare op. negativt argument fjerner antal.
    public void setBatchSize(int factor) {
        this.batchSize += factor;
    }

    public BigDecimal priceOfAllBatches(Storage storage) {
        BigDecimal totalPrice = new BigDecimal(0);

        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (batchSize != product.batchSize) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        return price != null ? price.equals(product.price) : product.price == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + batchSize;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}

