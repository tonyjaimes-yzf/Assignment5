package org.example.Amazon;

import org.example.Amazon.Cost.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class AmazonUnitTest {
    @Tag("Specification-Based")
    @Test
    @DisplayName("A null order")
    void nullOrder() {
        ShoppingCart cart = mock(ShoppingCart.class);
        Amazon amazon = new Amazon(cart, List.of());
        assertThat(amazon.calculate()).isEqualTo(0);
        verifyNoInteractions(cart);
    }

    @Tag("Specification-Based")
    @Test
    @DisplayName("A successful order")
    void successfulOrder() {
        ShoppingCart cart = mock(ShoppingCart.class);
        Item item = new Item(ItemType.OTHER, "Book", 2, 10.0);
        when(cart.getItems()).thenReturn(List.of(item));

        List<PriceRule> rules = List.of(new RegularCost());
        Amazon amazon = new Amazon(cart, rules);
        double total = amazon.calculate();

        assertThat(total).isEqualTo(20.0);
        verify(cart, times(1)).getItems();
    }

    @Tag("Specification-Based")
    @Test
    @DisplayName("Order with multiple items")
    void orderWithMultipleItems() {
        ShoppingCart cart = mock(ShoppingCart.class);
        Item item1 = new Item(ItemType.OTHER, "Book", 2, 15.0);
        Item item2 = new Item(ItemType.ELECTRONIC, "Phone", 1, 500.0);
        when(cart.getItems()).thenReturn(List.of(item1, item2));

        List<PriceRule> rules = List.of(new RegularCost(),new ExtraCostForElectronics(),new DeliveryPrice());
        Amazon amazon = new Amazon(cart,rules);
        double total = amazon.calculate();

        assertThat(total).isGreaterThan(0);
        verify(cart, times(3)).getItems();
    }
    @Tag("Specification-Based")
    @Test
    @DisplayName("Delivery Price for 0 items")
    void deliveryPrice1() {
        DeliveryPrice delivery = new DeliveryPrice();
        List<Item> cart = List.of();
        double result = delivery.priceToAggregate(cart);
        assertThat(result).isEqualTo(0.0);
    }

    @Tag("Specification-Based")
    @Test
    @DisplayName("Delivery Price for 1-3 items")
    void deliveryPrice2() {
        DeliveryPrice delivery = new DeliveryPrice();
        List<Item> cart = List.of(new Item(ItemType.OTHER,"Apple",1,2.0),new Item(ItemType.OTHER,"Pear",1,2.0));

        double result = delivery.priceToAggregate(cart);
        assertThat(result).isEqualTo(5.0);
    }

    @Tag("Specification-Based")
    @Test
    @DisplayName("Delivery Price for 4-10 Items")
    void deliveryPrice3() {
        DeliveryPrice delivery = new DeliveryPrice();
        List<Item> cart = List.of(
                new Item(ItemType.OTHER,"Bed",1,1.0),
                new Item(ItemType.OTHER,"Blanket",1,1.0),
                new Item(ItemType.OTHER,"Pillow",1,1.0),
                new Item(ItemType.OTHER,"Eyemask",1,1.0),
                new Item(ItemType.OTHER,"PJs",1,1.0)
        );
        double result = delivery.priceToAggregate(cart);
        assertThat(result).isEqualTo(12.5);
    }

    @Tag("Specification-Based")
    @Test
    @DisplayName("Delivery Price for 11+ Items")
    void deliveryPrice4() {
        DeliveryPrice delivery = new DeliveryPrice();
        List<Item> cart = List.of(
                new Item(ItemType.OTHER,"Shirt",1, 1.0),
                new Item(ItemType.OTHER,"Pants",1, 1.0),
                new Item(ItemType.OTHER,"Shoes",1, 1.0),
                new Item(ItemType.OTHER,"Jacket",1, 1.0),
                new Item(ItemType.OTHER,"Sweater",1, 1.0),
                new Item(ItemType.OTHER,"Hat",1, 1.0),
                new Item(ItemType.OTHER,"Cap",1, 1.0),
                new Item(ItemType.OTHER,"Beanie",1, 1.0),
                new Item(ItemType.OTHER,"Underwear",1, 1.0),
                new Item(ItemType.OTHER,"Socks",1, 1.0),
                new Item(ItemType.OTHER,"Cologne",1, 1.0));

        double result = delivery.priceToAggregate(cart);
        assertThat(result).isEqualTo(20.0);
    }

    @Tag("Structural-Based")
    @Test
    @DisplayName("Simple name getter")
    void nameGetter() {
        Item item = new Item(ItemType.OTHER,"ps5",2, 500.0);
        String result = item.getName();
        assertThat(result).isEqualTo("ps5");
    }

    @Tag("Structural-Based")
    @Test
    @DisplayName("Properly adds to shopping cart")
    void addToCartTest() {
        ShoppingCart cart = mock(ShoppingCart.class);
        Amazon amazon = new Amazon(cart, java.util.List.of());

        Item item = new Item(ItemType.OTHER, "bananas", 2, 1.0);
        amazon.addToCart(item);
        verify(cart, times(1)).add(item);
    }

    @Tag("Structural-Based")
    @Test
    @DisplayName("No extra costs for electronics")
    void noExtraCostForElectronics() {
        ExtraCostForElectronics price_rule = new ExtraCostForElectronics();
        List<Item> cart = List.of(new Item(ItemType.OTHER,"pokemon cards",1,20),new Item(ItemType.OTHER,"bread",2,5));
        double result = price_rule.priceToAggregate(cart);
        assertThat(result).isEqualTo(0.0);
    }
}