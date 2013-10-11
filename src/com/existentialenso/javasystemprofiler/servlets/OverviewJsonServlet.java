/**
 * @author Thorne Melcher <tmelcher@portdusk.com>
 * @copyright 2013 Thorne Melcher
 * @license GPLv3 
 */

package com.existentialenso.javasystemprofiler.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.existentialenso.javasystemprofiler.models.Device;
import com.google.gson.Gson;

/**
 * Servlet for requesting a JSON overview of the device. Adding "refresh=true" as a GET or POST parameter will force the
 * data to be current, but it will also increase the response time by several seconds.
 */
@WebServlet("/overview.json")
public class OverviewJsonServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  /**
   * The device running this servlet.
   */
  private Device device;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OverviewJsonServlet() {
        super();
        
        // Gets and populates a Device object with info about this computer
        device = new Device();
    device.profileThisDevice();
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter writer = response.getWriter();
    
    // Force a refresh of the device data if requested
    if(request.getParameter("refresh") != null) {
      if(request.getParameter("refresh").equals("true")) {
        device.profileThisDevice();
      }
    }
    
    // Use Google's GSON library to convert the object to JSON
    Gson gson = new Gson();
    String json = gson.toJson(device);
    
    writer.print(json);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doGet(request, response); // Identical behavior for GET and POST
  }

}
