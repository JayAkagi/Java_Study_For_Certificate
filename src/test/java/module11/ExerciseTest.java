package module11;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class ExerciseTest {

    @Test
    void testSingleton() {
        AppConfig c1 = AppConfig.getInstance();
        AppConfig c2 = AppConfig.getInstance();
        assertTrue(c1 != null && c1 == c2, "Singleton: same instance (c1 == c2)");

        if (c1 != null) {
            c1.set("app.name", "MyJavaApp");
            c1.set("debug", "true");
            assertEquals("MyJavaApp", c1.get("app.name"), "Singleton: get existing key");
            assertEquals("true", c1.get("debug"), "Singleton: get 'debug'");
            assertEquals("not found", c1.get("missing"), "Singleton: get missing key");

            c2.set("version", "1.0.0");
            assertEquals("1.0.0", c1.get("version"), "Singleton: shared state (set via c2, read via c1)");
        }
    }

    @Test
    void testFactory() {
        Shape circle = assertDoesNotThrow(() -> ShapeFactory.create("circle", 5.0));
        if (circle != null) {
            assertEquals("Drawing a circle", circle.draw(), "Factory: circle.draw()");
            assertEquals(Math.PI * 25, circle.area(), 0.01, "Factory: circle area ≈ 78.54");
            assertEquals(2 * Math.PI * 5, circle.perimeter(), 0.01, "Factory: circle perimeter ≈ 31.42");
        }

        Shape rect = assertDoesNotThrow(() -> ShapeFactory.create("rectangle", 4.0, 6.0));
        if (rect != null) {
            assertEquals("Drawing a rectangle", rect.draw(), "Factory: rectangle.draw()");
            assertEquals(24.0, rect.area(), 0.001, "Factory: rectangle area = 24.0");
            assertEquals(20.0, rect.perimeter(), 0.001, "Factory: rectangle perimeter = 20.0");
        }

        assertThrows(IllegalArgumentException.class, () -> ShapeFactory.create("hexagon", 5.0),
                "Factory: unknown shape should throw IllegalArgumentException");
    }

    @Test
    void testBuilder() {
        User u1 = new User.Builder("alice_dev", "alice@email.com")
                .firstName("Alice")
                .lastName("Smith")
                .age(28)
                .role("Developer")
                .build();

        assertEquals("alice_dev", u1.getUsername(), "Builder: username");
        assertEquals("alice@email.com", u1.getEmail(), "Builder: email");
        assertEquals("Alice", u1.getFirstName(), "Builder: firstName");
        assertEquals("Smith", u1.getLastName(), "Builder: lastName");
        assertEquals(28, u1.getAge(), "Builder: age");
        assertEquals("Developer", u1.getRole(), "Builder: role");

        User u2 = new User.Builder("bob99", "bob@email.com").build();
        assertEquals("bob99", u2.getUsername(), "Builder: minimal username");
        assertEquals("bob@email.com", u2.getEmail(), "Builder: minimal email");
    }

    @Test
    void testObserver() {
        StockMarket market = new StockMarket();
        CountingObserver obs1 = new CountingObserver();
        CountingObserver obs2 = new CountingObserver();

        market.subscribe(obs1);
        market.subscribe(obs2);
        assertEquals(2, market.observerCount(), "Observer: 2 subscribers");

        market.setPrice("AAPL", 175.00);
        assertEquals(1, obs1.callCount, "Observer: obs1 called once");
        assertEquals(1, obs2.callCount, "Observer: obs2 called once");
        assertEquals("AAPL", obs1.lastTicker, "Observer: obs1 lastTicker = AAPL");
        assertEquals(175.0, market.getPrice("AAPL"), 0.001, "Observer: AAPL price = 175.0");

        market.setPrice("AAPL", 182.50);
        assertEquals(2, obs1.callCount, "Observer: obs1 called twice");
        assertEquals(2, obs2.callCount, "Observer: obs2 called twice");

        market.unsubscribe(obs2);
        assertEquals(1, market.observerCount(), "Observer: 1 subscriber after unsubscribe");

        market.setPrice("AAPL", 178.00);
        assertEquals(3, obs1.callCount, "Observer: obs1 called 3x after price change");
        assertEquals(2, obs2.callCount, "Observer: obs2 NOT called (unsubscribed)");
    }

    @Test
    void testStrategy() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(29.99);
        cart.addItem(15.00);
        cart.addItem(49.99);

        assertEquals(94.98, cart.getTotal(), 0.01, "Strategy: no discount total ≈ 94.98");

        cart.setStrategy(new PercentageDiscount(20));
        assertEquals(75.984, cart.getTotal(), 0.01, "Strategy: 20% off total ≈ 75.98");
        assertEquals("20.0% off", new PercentageDiscount(20).getDescription(), "Strategy: 20% description");

        cart.setStrategy(new FlatDiscount(10.0));
        assertEquals(84.98, cart.getTotal(), 0.01, "Strategy: $10 off total ≈ 84.98");
        assertEquals("$10.0 off", new FlatDiscount(10.0).getDescription(), "Strategy: flat description");

        assertEquals("No discount", new NoDiscount().getDescription(), "Strategy: NoDiscount description");
    }

    @Test
    void testDecorator() {
        TextFormatter plain = new PlainText();
        assertEquals("hello world", plain.format("hello world"), "Decorator: plain text");

        TextFormatter upper = new UpperCaseDecorator(plain);
        assertEquals("HELLO WORLD", upper.format("hello world"), "Decorator: uppercase");

        TextFormatter bracketed = new BracketDecorator(plain);
        assertEquals("[hello world]", bracketed.format("hello world"), "Decorator: bracketed plain");

        TextFormatter upperBracketed = new BracketDecorator(upper);
        assertEquals("[HELLO WORLD]", upperBracketed.format("hello world"), "Decorator: [HELLO WORLD]");

        TextFormatter full = new StarDecorator(new BracketDecorator(new UpperCaseDecorator(plain)));
        assertEquals("***[HELLO WORLD]***", full.format("hello world"), "Decorator: ***[HELLO WORLD]***");
    }
}
