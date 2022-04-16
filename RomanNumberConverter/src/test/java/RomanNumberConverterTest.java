import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("While Testing Roman Number Converter")
class RomanNumberConverterTest {

    RomanNumberConverter converter;

    @BeforeEach
    void init() {
        this.converter = new RomanNumberConverter();
    }

    @Nested
    @DisplayName("Arabic To Roman Convert Method")
    class  convertArabicToRomanNumber {
        @Test
        @Tag("UnitTest")
        @DisplayName("Testing Conversion From Arabic To Roman for Correct Results")
        void convertArabicToRomanNumberResults() {
            assertAll(
                    () -> assertEquals("IV", converter.convertArabicToRomanNumber(4), "Should Return IV For Parameter 4"),
                    () -> assertEquals("XIII", converter.convertArabicToRomanNumber(13), "Should Return XIII For Parameter 13"),
                    () -> assertEquals("CCC", converter.convertArabicToRomanNumber(300), "Should Return CCC For Parameter 300"),
                    () -> assertEquals("M", converter.convertArabicToRomanNumber(1000), "Should Return M For Parameter 100"),
                    () -> assertEquals("DCCLXV", converter.convertArabicToRomanNumber(765), "Should Return DCCLXV For Parameter 765"),
                    () -> assertEquals("CMLXXXVIII", converter.convertArabicToRomanNumber(988), "Should Return CMLXXXVIII For Parameter 988"),
                    () -> assertEquals("", converter.convertArabicToRomanNumber(0), "Should Return Empty String For Parameter 0")
            );
        }

        @Test
        @Tag("UnitTest")
        @DisplayName("Testing Conversion From Arabic To Roman For Exceptions")
        void convertArabicToRomanNumberExceptions() {
            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, () -> converter.convertArabicToRomanNumber(-1), "Should Throw Illegal Argument Exception With Negative Parameter"),
                    () -> assertThrows(IllegalArgumentException.class, () -> converter.convertArabicToRomanNumber(1031), "Should Throw Illegal Argument Exception Parameter Above 1000")
            );
        }
    }

    @Nested
    @DisplayName("Roman To Arabic Convert Method")
    class  convertRomanToArabicNumberTest {
        @Test
        @Tag("UnitTest")
        @DisplayName("Testing Conversion From Roman To Arabic for Correct Results")
        void convertArabicToRomanNumber() {
            assertAll(
                    () -> assertEquals(4, converter.convertRomanToArabicNumber("IV"), "Should Return 4 For Parameter IV"),
                    () -> assertEquals(13, converter.convertRomanToArabicNumber("XIII"), "Should Return 13 For Parameter XIII"),
                    () -> assertEquals(300, converter.convertRomanToArabicNumber("CCC"), "Should Return 300 For Parameter CCC"),
                    () -> assertEquals(1000, converter.convertRomanToArabicNumber("M"), "Should Return 1000 For Parameter M"),
                    () -> assertEquals(765, converter.convertRomanToArabicNumber("DCCLXV"), "Should Return 765 For Parameter DCCLXV"),
                    () -> assertEquals(988, converter.convertRomanToArabicNumber("CMLXXXVIII"), "Should Return 988 For Parameter CMLXXXVIII"),
                    () -> assertEquals(0, converter.convertRomanToArabicNumber(""), "Should Return 0 For Parameter Empty String")
                    );
        }

        @Test
        @Tag("UnitTest")
        @DisplayName("Testing Conversion From Roman To Arabic For Exceptions")
        void convertArabicToRomanNumberExceptions() {
            assertAll(
//                    () -> assertThrows(IllegalArgumentException.class, () -> converter.convertRomanToArabicNumber("XIIII"), "Should Throw Illegal Argument Exception If Parameter Is Invalid Roman Number"),
//                    () -> assertThrows(IllegalArgumentException.class, () -> converter.convertRomanToArabicNumber("CCM"), "Should Throw Illegal Argument Exception If Parameter Is Invalid Roman Number"),
                    () -> assertThrows(IllegalArgumentException.class, () -> converter.convertRomanToArabicNumber("123as"), "Should Throw Illegal Argument Exception If Parameter Contains Not Roman Number"),
                    () -> assertThrows(IllegalArgumentException.class, () -> converter.convertRomanToArabicNumber("XIIa"), "Should Throw Illegal Argument Exception If Parameter Contains Not Roman Number"),
                    () -> assertThrows(IllegalArgumentException.class, () -> converter.convertRomanToArabicNumber("MII"), "Should Throw Illegal Argument Exception For Parameter Above M")
            );
        }
    }
}