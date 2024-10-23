package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Category;

public class TestJson {
    protected void checkCategory(String name, double limit, Category category) {
        assertEquals(name, category.getName());
        assertEquals(limit, category.getLimit());
    }

}
