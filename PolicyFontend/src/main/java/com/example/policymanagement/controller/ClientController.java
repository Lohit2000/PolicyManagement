package com.example.policymanagement.controller;

import com.example.policymanagement.model.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestClient;

@Controller
public class ClientController {

    private final RestClient restClient;

    public ClientController(RestClient restClient) {
        this.restClient = restClient;
    }

    @GetMapping("/home")
    public String homepage()
    {
        return "home";
    }

    @GetMapping("/about")
    public String aboutpage()
    {
        return "about";
    }

    @GetMapping("/service")
    public String servvicepage()
    {
        return "service";
    }

    @GetMapping("/contact")
    public String contactpage()
    {
        return "contact";
    }

    @GetMapping("/register")
    public String registerpage(Model model)
    {
        model.addAttribute("user",new UserInfo());

        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserInfo usr , Model model)
    {
        ResponseEntity<UserInfo> res=restClient.post()
                .uri("/user/register")
                .body(usr).retrieve()
                .toEntity(UserInfo.class);

       model.addAttribute("user",res.getBody());
       return "UserHomePage";

    }

    @GetMapping("/login")
    public String loginpage(Model model)
    {
        model.addAttribute("user",new UserInfo());
        return "login";
    }
    @PostMapping("/login")
    public String loginpage(@ModelAttribute UserInfo usr , Model model)
    {
        ResponseEntity<String> response = restClient.post()
                .uri("/user/login")
                .body(usr)
                .retrieve()
                .toEntity(String.class);

        model.addAttribute("user",response.getBody());
        return "UserHomePage";
    }

}
