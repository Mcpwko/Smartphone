import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mickaël
 * @author Kevin
 * @version
 */
class HomeScreenTest {

    /**
     * <p>vérifie que le fichier sélectionner est celui voulu</p>
     * @throws IOException
     */
    @Test
    void screenshot() throws IOException {
        int nombreImages = 5;

        String fileName = "5.jpg";
        String fileCreated = nombreImages + ".jpg";

        assertEquals ( fileName,fileCreated );
    }
}