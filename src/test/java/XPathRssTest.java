import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class XPathRssTest {

    @Test
    void noArgs() {
        Throwable empty = assertThrows(Exception.class, () -> {XPathRSS.main(new String[]{});});
        assertEquals("No arguments", empty.getMessage());
    }
    @Test
    void notRss () {
        String notRSSuri = "https://www.w3schools.com/xml/note.xml";
        Throwable notRss = assertThrows(Exception.class, () -> {XPathRSS.main(new String[]{notRSSuri});});
        assertEquals("Not RSS 2.0", notRss.getMessage());
    }
    @Test
    void noChannel () {
        File file = new File("src/test/resources/no-channel.xml");
        String fileUri = file.toURI().toString();
        Throwable noChan = assertThrows(Exception.class, () -> {XPathRSS.main(new String[] {fileUri});});
        assertEquals("No channel", noChan.getMessage());
    }
    @Test
    void noItems() {
        File file = new File("src/test/resources/no-items.xml");
        String fileUri = file.toURI().toString();
        Throwable noItems = assertThrows(Exception.class, () -> {XPathRSS.main(new String[] {fileUri});});
        assertEquals("No items", noItems.getMessage());
    }
}