package org.example.Amazon;

import org.example.Amazon.Cost.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AmazonIntegrationTest {

    Database db;
    ShoppingCartAdaptor cart;
    Amazon amazon;
    @BeforeEach
    void database() {
        db = new Database();
        cart = new ShoppingCartAdaptor(db);
        amazon = new Amazon(cart, java.util.List.of(new RegularCost(), new DeliveryPrice(), new ExtraCostForElectronics()));
        db.resetDatabase();
    }
    @AfterEach
    void cleanup() {
        db.close();
    }

    @Tag("Specification-Based")
    @Test
    @DisplayName("Empty cart test")
    void emptyCart() {
        double total = amazon.calculate();
        assertThat(total).isEqualTo(0.0);
    }

    @Tag("Specification-Based")
    @Test
    @DisplayName("A successful order")
    void successfulOrder() {
        cart.add(new Item(ItemType.OTHER,"Legos",2,55.0));
        double total = amazon.calculate();
        assertThat(total).isEqualTo(115.0);
    }

    @Tag("Specification-Based")
    @Test
    @DisplayName("Electronic and Other Order")
    void mixedItemOrder() {
        cart.add(new Item(ItemType.OTHER,"Pens",5,1.0));
        cart.add(new Item(ItemType.ELECTRONIC,"Laptop",1,500.0));
        double total = amazon.calculate();
        assertThat(total).isEqualTo(517.5);
    }

    @Tag("Structural-Based")
    @Test
    @DisplayName("Number Of Items test")
    void numberOfItemsTest() {
        ShoppingCartAdaptor cart = new ShoppingCartAdaptor(db);
        int before = cart.numberOfItems();
        assertThat(before).isGreaterThanOrEqualTo(0);
        cart.add(new Item(ItemType.OTHER,"Notebook",1,5.0));

        int after = cart.numberOfItems();
        assertThat(after).isGreaterThanOrEqualTo(0);
    }
}
