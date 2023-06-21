package alfarezyyd.pharmacy.filter;

import alfarezyyd.pharmacy.util.JSONUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;

public class AuthenticationFilter extends HttpFilter {
  @Override
  protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    if (request.getRequestURI().equals("/api/login")) {
      chain.doFilter(request, response);
    } else {
      HttpSession httpSession = request.getSession(true);
      String username = (String) httpSession.getAttribute("username");
      if (username == null) {
        response.setStatus(401);
        response.getWriter().println();
        response.setHeader("Content-Type", "application/json");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code", 401);
        hashMap.put("message", "You need to login first");
        response.getWriter().println(JSONUtil.getObjectMapper().writeValueAsString(hashMap));
      } else {
        chain.doFilter(request, response);
      }

    }
  }
}
