/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.82
 * Generated at: 2018-03-05 18:03:08 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.juddi.adminconsole.hub.UddiAdminHub;
import org.apache.juddi.adminconsole.resources.ResourceLoader;
import org.apache.commons.lang.StringEscapeUtils;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"navbar-form pull-right\">\r\n");
      out.write("\r\n");
      out.write("    ");

        if (session.getAttribute("username") != null && session.getAttribute("password") != null
                && ((String) session.getAttribute("username")).length() > 0 && ((String) session.getAttribute("password")).length() > 0) {
            //we're probably logged in

    
      out.write("\r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("        loggedin = true;\r\n");
      out.write("    </script>\r\n");
      out.write("    <a class=\"btn\" title=\"");
      out.print(ResourceLoader.GetResource(session, "navbar.login.logout"));
      out.write("\" href=\"javascript:logout();\">\r\n");
      out.write("        ");

                if (!request.isSecure()) {
            
      out.write("\r\n");
      out.write("            <i class=\"icon-warning-sign\" title=\"");
      out.print(ResourceLoader.GetResource(session, "warning.ssl"));
      out.write("\"></i>\r\n");
      out.write("            ");

                }
            
      out.write("\r\n");
      out.write("        \r\n");
      out.write("        <i class=\"icon-user\"></i>\r\n");
      out.write("        ");

            out.write(ResourceLoader.GetResource(session, "items.welcome") + " " + StringEscapeUtils.escapeHtml((String) session.getAttribute("username")) + "</a>");

        } else {
        
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <script type=\"text/javascript\">\r\n");
      out.write("            loggedin = false;\r\n");
      out.write("        </script>\r\n");
      out.write("\r\n");
      out.write("        <input class=\"span2\" type=\"text\" placeholder=\"");
      out.print(ResourceLoader.GetResource(session, "navbar.login.username"));
      out.write("\" name=\"username\" id=\"username\">\r\n");
      out.write("        <input class=\"span2\" type=\"password\" placeholder=\"");
      out.print(ResourceLoader.GetResource(session, "navbar.login.password"));
      out.write("\" name=\"password\" id=\"password\">\r\n");
      out.write("        <button type=\"button\" onclick=\"javascript:Login();\" class=\"btn\" id=\"loginbutton\">\r\n");
      out.write("            ");

                if (!request.isSecure() ||  !UddiAdminHub.getInstance(application, session).isSecure()) {
            
      out.write("\r\n");
      out.write("            <i class=\"icon-warning-sign\" title=\"");
      out.print(ResourceLoader.GetResource(session, "warning.ssl"));
      out.write("\"></i>\r\n");
      out.write("            ");

                }
            
      out.write("\r\n");
      out.write("            ");
      out.print(ResourceLoader.GetResource(session, "navbar.login.button"));
      out.write("\r\n");
      out.write("        </button>\r\n");
      out.write("        ");

            }
        
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("        \r\n");
      out.write("            \r\n");
      out.write("<div class=\"modal hide fade container\" id=\"loginfailure\">\r\n");
      out.write("    <div class=\"modal-header\">\r\n");
      out.write("        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\r\n");
      out.write("        <h3>");
      out.print(ResourceLoader.GetResource(session, "errors.generic"));
      out.write("</h3>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"modal-body\">\r\n");
      out.write("            <i class=\"icon-4x icon-thumbs-down\"></i><br>\r\n");
      out.write("            <div id=\"loginfailuredetails\"></div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"modal-footer\">\r\n");
      out.write("            <button type=\"button\" class=\"btn\" data-dismiss=\"modal\" >");
      out.print(ResourceLoader.GetResource(session, "modal.close"));
      out.write("</button>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
