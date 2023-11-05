package com.example.shinnytest.controller;

import com.example.shinnytest.Model.*;
import com.example.shinnytest.entity.User;
import com.example.shinnytest.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("shinny.vncash.vn")
public class Controller {
    @Autowired
    private DataService dataService;

    @GetMapping("/get")
    private Response getAdmin(){
        return dataService.getAdmin();
    }
//    @PostMapping("/add")


    @GetMapping("/GetPermissionStaff")
    public ResponseUser getPermissonByStaffId(@RequestParam("StaffId") long id){
        return dataService.getPermissonByStaffId(id);
    }
    @PostMapping("/addPermisson")
    public ResponseUser addPermisson(@RequestBody AddPermissonId addPermissonId){
        return dataService.addPermissonByStaffId(addPermissonId);
    }
    @GetMapping("/searchUser")
    public ResponseSearch SearchUsers(@RequestBody SearchRequest searchRequest){
         return dataService.searchUser(searchRequest);
    }
    @PostMapping("/addUser")
    public ResponseAddUser addUser(@RequestBody InUser inUser){
       return dataService.addUser(inUser);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id){
        dataService.deleteUser(id);
    }
    @PutMapping("/update/{id}")
    public ResponseUpdate changeInformation(@PathVariable long id, @RequestBody InUser user){
        return dataService.changInformation(id,user);
    }
    @PostMapping("/addCamp")
    public Response addCamp(@RequestBody RequestCamp requestCamp){
       return dataService.addCamp(requestCamp);
    }
    @PostMapping("/addMedia")
    public Response addMedia(@RequestBody InMedia inMedia){
        return dataService.addMedia(inMedia);
    }
    @GetMapping("/getCamp")
    public ResponseCamp getCamp(){
        return dataService.getCamp();
    }
    @PostMapping("/addProduct")
    public Response addProduct(@RequestBody AddProduct product){
        return dataService.addProduct(product);
    }
    @PostMapping("/addCapital")
    public Response addCapital(@RequestBody InCapital inCapital){
        return dataService.addCapital(inCapital);
    }


}
