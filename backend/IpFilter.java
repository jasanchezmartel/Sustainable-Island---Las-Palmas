package com.sustainableisland.seazen.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class IpFilter implements Filter {

    @Value("${frontend.ip.allowed:fabd49ef-40eb-4f32-a89d.1e1a7c6f6a1a.sites.escritorios.ieselrincon.es}")
    private String allowedFrontendIp;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String clientIp = getClientIp(req);

        if (clientIp.equals(allowedFrontendIp) ||
                clientIp.equals("fabd49ef-40eb-4f32-a89d.1e1a7c6f6a1a.sites.escritorios.ieselrincon.es") ||
                clientIp.equals("0:0:0:0:0:0:0:1")) {

            chain.doFilter(request, response);
        } else {
            res.sendError(HttpServletResponse.SC_FORBIDDEN,
                    "Access denied: Unauthorized IP");
        }
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }
}