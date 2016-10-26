package monopoly.model.field;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StandardFieldTest {

    @Test
    public void getName() {
        String name = "This is a test name for field.";

        Field stdField = new StandardField(name);
        assertEquals(name, stdField.getName());
    }

}
