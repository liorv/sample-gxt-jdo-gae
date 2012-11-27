package sample.server;

import org.springframework.stereotype.Service;

import sample.client.GreetingService;
import sample.client.TestUtils;
import sample.shared.FieldVerifier;

/**
 * The server side implementation of the RPC service.
 */
@Service("greet")
public class GreetingServiceImpl implements GreetingService
{

  public String greetServer(String input) throws IllegalArgumentException {
    TestUtils.Test1();

    // Verify that the input is valid.
    if (!FieldVerifier.isValidName(input)) {
      // If the input is not valid, throw an IllegalArgumentException back to
      // the client.
      throw new IllegalArgumentException(
          "Name must be at least 4 characters long");
    }

    /*
     * ServletContext servletContext = (RemoteServiceServlet)this; String
     * serverInfo = servletContext.getServerInfo(); String userAgent =
     * getThreadLocalRequest().getHeader("User-Agent");
     * 
     * // Escape data from the client to avoid cross-site script
     * vulnerabilities. input = escapeHtml(input); userAgent =
     * escapeHtml(userAgent);
     * 
     * return "Hello, " + input + "!<br><br>I am running " + serverInfo +
     * ".<br><br>It looks like you are using:<br>" + userAgent;
     */
    return "Hello<br>";
  }

  /**
   * Escape an html string. Escaping data received from the client helps to
   * prevent cross-site script vulnerabilities.
   * 
   * @param html
   *          the html string to escape
   * @return the escaped string
   */
  @SuppressWarnings("unused")
  private String escapeHtml(String html) {
    if (html == null) { return null; }
    return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
        .replaceAll(">", "&gt;");
  }
}
