package alfarezyyd.pharmacy.filter;

import alfarezyyd.pharmacy.util.JSONUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.HashMap;

@WebFilter(value = "/*", filterName = "filter1")
public class AuthenticationFilter extends HttpFilter {
  @Override
  protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    if (request.getRequestURI().equals("/api/authentication/login")) {
      chain.doFilter(request, response);
    } else {
      HttpSession httpSession = request.getSession(true);
      String email = (String) httpSession.getAttribute("email");
      if (email == null) {
        response.setStatus(401);
        response.getWriter().println();
        response.setHeader("Content-Type", "application/json");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code", HttpStatus.UNAUTHORIZED);
        hashMap.put("message", "You need to login first");
        response.getWriter().println(JSONUtil.getObjectMapper().writeValueAsString(hashMap));
      } else {
        chain.doFilter(request, response);
      }

    }
  }
}
