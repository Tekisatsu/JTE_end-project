import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XPathRSSTest {

    @Test
    void testMain() {
        String notRSSuri = "https://www.w3schools.com/xml/note.xml";
        Throwable empty = assertThrows(Exception.class, () -> {XPathRSS.main(new String[]{});});
        assertEquals("No arguments", empty.getMessage());
        Throwable notRss = assertThrows(Exception.class, () -> {XPathRSS.main(new String[] {notRSSuri});});
        assertEquals("Not RSS 2.0", notRss.getMessage());
        Throwable emptyChan = assertThrows(Exception.class, () -> {XPathRSS.main(new String[]{});});
        assertEquals("Empty channel", emptyChan.getMessage());
        Throwable noItems = assertThrows(Exception.class, () -> {XPathRSS.main(new String[]{});});
        assertEquals("No items", noItems.getMessage());
    }
}