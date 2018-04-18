package com.itmuch.cloud.study.controller;

import com.itmuch.cloud.study.entity.User;
import com.itmuch.cloud.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/{id}")
  public User findById(@PathVariable Long id) {
    User findOne = this.userRepository.findOne(id);
    return findOne;
  }

  @RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
  @ResponseBody
  public String list(@RequestParam String name) throws InterruptedException{
    return name.toUpperCase();
  }
}
