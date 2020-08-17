package samples.security.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;

/**
 * @author lolico
 */
@RequestMapping("user")
@RestController
public class UserController {
    @GetMapping("/read")
    public ResponseEntity<?> principal(Principal principal) {
        System.out.println(principal.getClass());
        return ResponseEntity.ok(Collections.singletonMap("name", principal.getName()));
    }
}
