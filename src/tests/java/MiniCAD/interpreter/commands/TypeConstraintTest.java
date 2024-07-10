package MiniCAD.interpreter.commands;


import MiniCAD.interpreter.utils.TokenType;
import MiniCAD.interpreter.utils.TypeConstructor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

public class TypeConstraintTest {
    static Stream<Object[]> provideParametersForInterpreta() {
        return Stream.of(
                new Object[] { TokenType.CIRCLE, 5.0, "Circle with radius: 5.0" },
                new Object[] { TokenType.RECTANGLE, "10x20", "Rectangle with width and height: 10x20" },
                new Object[] { TokenType.IMG, "./path/to/image.jpg", "Image with path: \"./path/to/image.jpg\"" }
        );
    }

    static Stream<Object[]> provideParametersForToString() {
        return Stream.of(
                new Object[] { TokenType.CIRCLE, 5.0, "Circle with radius: 5.0" },
                new Object[] { TokenType.RECTANGLE, "10x20", "Rectangle." },
                new Object[] { TokenType.IMG, "/path/to/image.jpg", "Image with path: /path/to/image.jpg" }
        );
    }

    /*
    @ParameterizedTest
    @MethodSource("provideParametersForInterpreta")
    public void testInterpreta(TokenType type, Object parameter, String expectedOutput) {
        TypeConstructor<Object> constraint = new TypeConstructor<>(type, parameter);
        assertEquals(expectedOutput, constraint.interpreta());
    }

     */

    /*
    @ParameterizedTest
    @MethodSource("provideParametersForToString")
    public void testToString(TokenType type, Object parameter, String expectedOutput) {
        TypeConstructor<Object> constraint = new TypeConstructor<>(type, parameter);
        assertEquals(expectedOutput, constraint.toString());
    }

     */


}
