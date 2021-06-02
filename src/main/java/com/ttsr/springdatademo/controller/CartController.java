package com.ttsr.springdatademo.controller;

import com.ttsr.springdatademo.dto.CartDto;
import com.ttsr.springdatademo.model.Cart;
import com.ttsr.springdatademo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cart")
public class CartController {

    private final Cart cart;
    private final ProductService productService;

    @GetMapping("/add")
    public void add(@RequestParam Long id){
        cart.add(productService.findById(id).orElseThrow(()->new NoSuchElementException("Product doesn't exist")));
    }

    @GetMapping("/show")
    public ResponseEntity<?> showCart(){
        CartDto cartDto = new CartDto(cart);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @GetMapping("/remove")
    public void removeById(@RequestParam Long id){
        cart.remove(id);
    }

    @GetMapping("/removeAll")
    public void removeAllFromCart(){
        cart.removeAll();
    }
}
