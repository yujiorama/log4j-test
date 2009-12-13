package log4jtest.jdbcappender;

import java.io.IOException;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class DefaultServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        logging("request received", null);
        try {
            super.service(request, response);
        } catch (ServletException ex) {
            java.util.logging.Logger.getLogger(DefaultServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(DefaultServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }


    private static Logger log = Logger.getLogger(DefaultServlet.class);
    protected synchronized void logging(String message, Throwable t) {
        log.info(message, t);
    }

    @Override
    public void log(String msg) {
        super.log(msg);
        logging(msg, null);
    }

    @Override
    public void log(String message, Throwable t) {
        super.log(message, t);
        logging(message, t);
    }

}

