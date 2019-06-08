package Contact;

import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.validation.Validator;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class ContactPanelTest {

    @Test
    void validateEmail() {
        //Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,4}$");
        Pattern p = Pattern.compile ("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$" );

        String email = "kevin.coppey@hotmail.com";
        Matcher m = p.matcher(email.toUpperCase());
        assertTrue ( m.matches ());

        String email2 = "kevin@cooooooo";
        Matcher m2 = p.matcher ( email2.toUpperCase () );
        assertFalse ( m2.matches () );

        String email3 = "kevin123.com";
        Matcher m3 = p.matcher ( email3.toUpperCase () );
        assertFalse ( m3.matches () );

        String email4 = "kevin123.comar";
        Matcher m4 = p.matcher ( email4.toUpperCase () );
        assertFalse ( m4.matches () );



    }
}