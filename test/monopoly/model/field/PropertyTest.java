package monopoly.model.field;

import monopoly.model.Owner;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class PropertyTest {

    @Test
    public void owner() {
        String name = "This is a test name for field.";
        final int price = 10;
        Owner owner = mock(Owner.class);

        Property property = new Property(name, 1, 1, 10, 1, 1, 1, 1, 1, 1, ColorGroup.Purple);
        assertFalse(property.hasOwner());

        property.setOwner(owner);
        assertTrue(property.hasOwner());
        assertEquals(owner, property.getOwner());

        Property property2 = new Property(owner, name, 1, 1, 10, 1, 1, 1, 1, 1, 1, ColorGroup.Purple);
        assertTrue(property2.hasOwner());
        assertEquals(owner, property2.getOwner());
    }

    @Test
    public void getPrice() {
        String name = "This is a test name for field.";
        final int price = 10;
        Owner owner = mock(Owner.class);

        Property property = new Property(owner, name, 1, 1, 10, 1, 1, 1, 1, 1, 1, ColorGroup.Purple);
        assertEquals(price, property.getPrice());
    }

    @Test
    public void getName() {
        String name = "This is a test name for field.";
        final int price = 10;
        Owner owner = mock(Owner.class);

        Property property = new Property(owner, name, 1, 1, 10, 1, 1, 1, 1, 1, 1, ColorGroup.Purple);
        assertEquals(name, property.getName());
    }

}