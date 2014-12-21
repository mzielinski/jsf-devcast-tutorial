package devcast.dao;

import devcast.entities.Category;
import devcast.entities.Order;
import devcast.entities.Product;
import devcast.entities.beans.Money;
import devcast.entities.builders.ProductBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import static devcast.entities.builders.CategoryBuilder.aCategory;
import static devcast.utils.Preconditions.greaterThenZero;
import static java.util.stream.Collectors.toList;

/**
 * @author mzielinski on 21.12.14.
 */
public enum GeneralDao {

    DAO_INSTANCE;

    public static final int PAGE_SIZE = 3;

    private List<Category> categories = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    GeneralDao() {
        buildProcessors();
        buildMemories();
    }

    private void buildMemories() {
        final Category memory = aCategory().withId(2).withName("Memory").build();
        categories.add(memory);
        products.add(ProductBuilder.aProduct()
                        .withId(1)
                        .withName("DDR 4GB")
                        .withAmount(new Money(120))
                        .withCategory(memory)
                        .withCount(5)
                        .withDescription("Description DDR Memeory")
                        .build()
        );
    }

    private void buildProcessors() {
        // Processors Category
        final Category processors = aCategory().withId(1).withName("Processors").build();
        products.add(ProductBuilder.aProduct()
                        .withId(1)
                        .withName("Processor Single")
                        .withAmount(new Money(350))
                        .withCategory(processors)
                        .withCount(10)
                        .withDescription("Description Single Processor")
                        .build()
        );
        products.add(ProductBuilder.aProduct()
                        .withId(2)
                        .withName("Processor Dual")
                        .withAmount(new Money(450))
                        .withCategory(processors)
                        .withCount(10)
                        .withDescription("Description Dual Processor")
                        .build()
        );
        products.add(ProductBuilder.aProduct()
                        .withId(3)
                        .withName("Processor Quad")
                        .withAmount(new Money(763))
                        .withCategory(processors)
                        .withCount(10)
                        .withDescription("Description Quad Processor")
                        .build()
        );
        categories.add(processors);
    }

    /**
     * @return all products
     */
    public List<Product> findAllProducts() {
        return findProductsByCategory(null, 0, products.size());
    }

    /**
     * @param category category
     * @return products for given category
     */
    public List<Product> findProductsByCategory(Category category) {
        return findProductsByCategory(category, 0, products.size());
    }

    /**
     * @param category category
     * @param offset   offset
     * @param limit    limit
     * @return products for given category, filtered by limit and offset
     */
    public List<Product> findProductsByCategory(Category category, int offset, int limit) {
        greaterThenZero(offset);
        greaterThenZero(limit);

        final Predicate<Product> predicate = buildCategoryFilter(category);
        return products.stream()
                .filter(predicate)
                .skip(calculateOffset(products, offset, limit))
                .limit(limit)
                .collect(toList());
    }

    private Predicate<Product> buildCategoryFilter(Category category) {
        if (category != null) {
            return product -> Objects.equals(product.getCategory(), category);
        }
        return product -> true;
    }

    private int calculateOffset(List list, int skip, int limit) {
        if (skip > list.size() || skip + limit > list.size()) {
            return list.size() - limit;
        }
        return skip;
    }

    /**
     * @param category category
     * @return number of production which are in given category
     */
    public int countProducts(Category category) {
        return findProductsByCategory(category).size();
    }

    /**
     * @return find all categories
     */
    public List<Category> findAllCategories() {
        return categories;
    }

    /**
     * @param id product identifier
     * @return find product with given id
     */
    public Optional<Product> findProductById(int id) {
        return products.stream()
                .filter(products -> id == products.getId())
                .findFirst();
    }

    /**
     * @return find top products
     */
    public List<Product> findTopProducts() {
        return products.stream()
                .limit(2)
                .collect(toList());
    }

    /**
     * Method adds order to list of all orders
     *
     * @param order order which will be added
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * @return find all orders
     */
    public List<Order> findAllOrders() {
        return orders;
    }

}
