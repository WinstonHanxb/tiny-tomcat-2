package com.winston.valves;

import com.winston.Contained;
import com.winston.Valve;
import com.winston.ValveContext;
import org.apache.catalina.*;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import java.io.IOException;


public class ClientIPLoggerValve implements Valve, Contained {
  
  protected Container container;

  public void invoke(Request request, Response response, ValveContext valveContext)
    throws IOException, ServletException {

    // Pass this request on to the next valve in our pipeline
    valveContext.invokeNext(request, response);
    System.out.println("Client IP Logger Valve");
    ServletRequest sreq = request.getRequest();
    System.out.println(sreq.getRemoteAddr());
    System.out.println("------------------------------------");
  }

  public String getInfo() {
    return null;
  }

  public Container getContainer() {
    return container;
  }

  public void setContainer(Container container) {
    this.container = container;
  }
}