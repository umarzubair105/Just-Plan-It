package com.uz.justplan.ai;

import com.uz.justplan.config.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/AI")
public class AIController {
    private AIService service;


    @Autowired
    public AIController(final AIService service) {
        this.service = service;
    }

    /*
        @GetMapping("/me")
        public String getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
            return userDetails.getUsername();
        }*/
    @GetMapping("/me")
    public String getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails,
                                 @RequestParam String route) {

        return userDetails.getUsername();
    }

    @GetMapping()
    public List<AIBaseResponse> getUISuggestions(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                 @RequestParam(required = false) String route, @RequestParam(required = false) Long productId) {
        if (route != null && route.startsWith("/")) {
            route = route.replace("/", "");
            if (route.equalsIgnoreCase("")) {
                route = "home";
            }
        }
        return service.getUISuggestions(userDetails.getId(), productId,
                route != null ? AIRoute.valueOf(route) : null);
    }
}
