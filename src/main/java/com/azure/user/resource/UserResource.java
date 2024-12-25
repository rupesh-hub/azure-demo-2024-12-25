package com.azure.user.resource;

import com.azure.user.entity.User;
import com.azure.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid User user){
        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getAll(
            @PathVariable(name="email") String email
    ){
        return new ResponseEntity<>(userService.getByEmail(email), HttpStatus.OK);
    }

}
