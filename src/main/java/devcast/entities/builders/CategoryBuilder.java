package devcast.entities.builders;

import devcast.entities.Category;

/**
 * @author mzielinski on 15.12.14.
 */
public class CategoryBuilder {

    private long id;
    private String name;

    private CategoryBuilder() {
    }

    public static CategoryBuilder aCategory() {
        return new CategoryBuilder();
    }

    public CategoryBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public CategoryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder but() {
        return aCategory().withId(id).withName(name);
    }

    public Category build() {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        return category;
    }
}
