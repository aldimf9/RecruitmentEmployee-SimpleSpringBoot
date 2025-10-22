package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    
    // private final UserService userService;
    // private final RoleService roleService;

    // @Autowired
    // public UserController(
    //     UserService userService,
    //     RoleService roleService){
    //     this.userService = userService;
    //     this.roleService = roleService;
    // }

    // @GetMapping
    // public String index(Model model){
    //     model.addAttribute("users", userService.getAllData());
    //     return "user/index";
    // }

    // @GetMapping(value={"form","form/{id}"})
    // public String showUpdateForm(@PathVariable(required=false) Integer id, Model model) {
    //     model.addAttribute("roles", roleService.getAll());
    //     if (id != null) {
    //         model.addAttribute("userDto", userService.getWithId(id));
    //     }else{
    //         model.addAttribute("userDto", new UserDto());
    //     }
    //     return "user/form";
    // }
    // @PostMapping("save")
    // public String save(UserDto userDto) {
    //     Boolean result = userService.save(userDto);
    //     if (result) {
    //         return "redirect:/user";
    //     }
    //     return (userDto.getId() != null) ? "redirect:/user/form/"+ userDto.getId() : "user/form";
    // }
}
