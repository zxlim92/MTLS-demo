//package com.app.server;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.security.cert.X509Certificate;
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Component
//public class ClientTrackingFilter extends OncePerRequestFilter {
//
//    private final Set<String> uniqueClients = ConcurrentHashMap.newKeySet();
//    private final AtomicInteger clientCount = new AtomicInteger(0);
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        X509Certificate[] certs = (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");
//        System.out.println("Hello from filter "+request);
//        if (certs != null && certs.length > 0) {
//            System.out.println("Has Cert");
//            String clientId = certs[0].getSerialNumber().toString();  // Use the certificate serial number as an identifier
//
//            // Only increment the counter if the client is new
//            if (uniqueClients.add(clientId)) {
//                clientCount.incrementAndGet();
//                System.out.println("Count of unique clients: "+clientCount);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    public int getUniqueClientCount() {
//        return clientCount.get();
//    }
//}