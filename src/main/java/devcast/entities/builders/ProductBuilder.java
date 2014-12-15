package devcast.entities.builders;

import devcast.entities.Category;
import devcast.entities.Product;
import devcast.entities.beans.Money;

/**
 * @author mzielinski on 15.12.14.
 */
public class ProductBuilder {

    private long id;
    private Category category;
    private Money amount;
    private String description;
    private int count;

    private ProductBuilder() {
    }

    public static ProductBuilder aProduct() {
        return new ProductBuilder();
    }

    public ProductBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public ProductBuilder withCategory(Category category) {
        this.category = category;
        return this;
    }

    public ProductBuilder withAmount(Money amount) {
        this.amount = amount;
        return this;
    }

    public ProductBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder withCount(int count) {
        this.count = count;
        return this;
    }

    public ProductBuilder but() {
        return aProduct().withId(id).withCategory(category).withAmount(amount).withDescription(description).withCount(count);
    }

    public Product build() {
        Product product = new Product();
        product.setId(id);
        product.setCategory(category);
        product.setAmount(amount);
        product.setDescription(description);
        product.setCount(count);
        return product;
    }

}
