package com.zsmart.accountingProject.ws.rest.provided;

import com.zsmart.accountingProject.service.facade.UtilisateurService;
import com.zsmart.accountingProject.ws.rest.vo.AuthResponse;
import com.zsmart.accountingProject.ws.rest.vo.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accountingProject/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationRest {
    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/signup/")
    public int signup(@RequestBody SignupRequest user) {
        return utilisateurService.signUp(user);
    }

    @PostMapping("signin/login/{login}/pass/{pass}")
    public AuthResponse signin(@PathVariable String login, @PathVariable String pass) {

        return utilisateurService.authentificate(login, pass);
    }

    @GetMapping("signin/{test}")
    public String signin(@PathVariable String test) {
        return test;
    }
}
