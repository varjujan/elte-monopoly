package monopoly.model.field;

import monopoly.model.Owner;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class PropertyTest {

    @Test
    public void owner() {
        String name = "This is a test name for field.";
        final int price = 10;
        Owner owner = mock(Owner.class);

        Property property = new Property(name, 10);
        assertFalse(property.hasOwner());

        property.setOwner(owner);
        assertTrue(property.hasOwner());
        assertEquals(owner, property.getOwner());
    }

    @Test
    public void getPrice() {
        String name = "This is a test name for field.";
        final int price = 10;

        Property property = new Property(name, 10);
        assertEquals(price, property.getDefaultPrice());
    }

    @Test
    public void getName() {
        String name = "This is a test name for field.";
        final int price = 10;

        Property property = new Property(name, 10);
        assertEquals(name, property.getName());
    }

}