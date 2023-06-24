package alfarezyyd.pharmacy.filter;

import alfarezyyd.pharmacy.util.JSONUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;

@WebFilter(value = "/*", filterName = "filter2")
public class MultiLevelLogin extends HttpFilter {
  @Override
  protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    if (request.getRequestURI().equals("/api/users") || request.getRequestURI().equals("/api/employees")) {
      HttpSession httpSession = request.getSession(true);
      String position = (String) httpSession.getAttribute("position");
      if (!position.equals("Programmer")) {
        response.setStatus(401);
        response.getWriter().println();
        response.setHeader("Content-Type", "application/json");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code", 401);
        hashMap.put("message", "Unauthorized! You don't have permission");
        response.getWriter().println(JSONUtil.getObjectMapper().writeValueAsString(hashMap));
      } else {
        chain.doFilter(request, response);
      }
    } else {
      chain.doFilter(request, response);
    }
  }
}
