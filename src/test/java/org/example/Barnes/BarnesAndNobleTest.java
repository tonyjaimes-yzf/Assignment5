package org.example.Barnes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BarnesAndNobleTest {

    @Tag("Specification-Based")
    @Test
    @DisplayName("A null order")
    void nullOrder() {
        BookDatabase bookdb = mock(BookDatabase.class);
        BuyBookProcess process = mock(BuyBookProcess.class);
        BarnesAndNoble bn = new BarnesAndNoble(bookdb, process);
        assertThat(bn.getPriceForCart(null)).isNull();
    }

    @Tag("Specification-Based")
    @Test
    @DisplayName("A successful order")
    void successfulOrder(){
        BookDatabase bookdb = mock(BookDatabase.class);
        BuyBookProcess process = mock(BuyBookProcess.class);
        Book book1 = new Book("123456",15,4);
        when(bookdb.findByISBN("123456")).thenReturn(book1);

        BarnesAndNoble bn = new BarnesAndNoble(bookdb, process);
        PurchaseSummary checkout = bn.getPriceForCart(Map.of("123456", 4));
        assertThat(checkout.getTotalPrice()).isEqualTo(60);

        verify(process).buyBook(book1, 4);
    }

    @Tag("Specification-Based")
    @Test
    @DisplayName("Insufficient / Out of Stock order")
    void outOfStockOrder() {
        BookDatabase bookdb = mock(BookDatabase.class);
        BuyBookProcess process = mock(BuyBookProcess.class);
        Book book1 = new Book("123456",15,4);
        when(bookdb.findByISBN("123456")).thenReturn(book1);

        BarnesAndNoble bn = new BarnesAndNoble(bookdb, process);
        PurchaseSummary summary = bn.getPriceForCart(Map.of("123456", 5));

        assertThat(summary.getTotalPrice()).isEqualTo(60);
        assertThat(summary.getUnavailable().get(book1)).isEqualTo(1);
        verify(process).buyBook(book1, 4);
    }
    @Tag("Specification-Based")
    @Test
    @DisplayName("Order with multiple books")
    void multipleBookOrder(){
        BookDatabase bookdb = mock(BookDatabase.class);
        BuyBookProcess process = mock(BuyBookProcess.class);
        Book book1 = new Book("123456", 15, 3);
        Book book2 = new Book("654321", 25, 3);
        when(bookdb.findByISBN("123456")).thenReturn(book1);
        when(bookdb.findByISBN("654321")).thenReturn(book2);

        BarnesAndNoble bn = new BarnesAndNoble(bookdb, process);
        PurchaseSummary checkout = bn.getPriceForCart(Map.of("123456", 3, "654321", 3));
        assertThat(checkout.getTotalPrice()).isEqualTo(120);
        verify(process).buyBook(book1, 3);
        verify(process).buyBook(book2, 3);
    }
    @Tag("Structural-Based")
    @Test
    @DisplayName("o == null")
    void oEqualsNull() {
        BookDatabase bookdb = mock(BookDatabase.class);
        BuyBookProcess process = mock(BuyBookProcess.class);
        Book book = new Book("123456",15,2);

        boolean result = book.equals(null);

        assertThat(result).isFalse();
        verifyNoInteractions(bookdb, process);
    }

    @Tag("Structural-Based")
    @Test
    @DisplayName("Equals a different class")
    void equalsDifferentClasss() {
        BookDatabase bookdb = mock(BookDatabase.class);
        BuyBookProcess process = mock(BuyBookProcess.class);
        Book book = new Book("123456", 15, 2);
        Object other = mock(Object.class);

        boolean result;
        result = book.equals(other);

        assertThat(result).isFalse();
        verifyNoInteractions(bookdb, process);
    }
}