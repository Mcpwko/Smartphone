import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HomeScreenTest {

    @Test
    void screenshot() throws IOException {
        int nombreImages = 5;

        String fileName = "5.jpg";
        String fileCreated = nombreImages + ".jpg";

        assertEquals ( fileName,fileCreated );
    }
}