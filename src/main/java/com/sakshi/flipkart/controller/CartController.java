package com.sakshi.flipkart.controller;

import com.sakshi.flipkart.dto.CartSummaryDto;
import com.sakshi.flipkart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/{customerId}")
    public ResponseEntity<CartSummaryDto> addToCart(@PathVariable Long customerId, @RequestBody CartSummaryDto cartSummaryDto){
        return new ResponseEntity<CartSummaryDto>(cartService.addToCart(customerId,cartSummaryDto), HttpStatus.CREATED);
    }

    @GetMapping("/remove")
    public ResponseEntity<Boolean> removeFromCart(@PathParam("cartId") Long cartId, @PathParam("productId") Long productId){
        return new ResponseEntity<Boolean>(cartService.removeFromCart(cartId,productId),HttpStatus.OK);
    }

    @GetMapping("/update")
    public ResponseEntity<Boolean> increaseOrDecreaseCount(@PathParam("cartId") Long cartId, @PathParam("productId") Long productId, @PathParam("updatedCount") Integer updatedCount){
        return new ResponseEntity<Boolean>(cartService.increaseOrDecreaseCount(cartId,productId,updatedCount),HttpStatus.OK);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<List<CartSummaryDto>> getActiveCart(@PathVariable Long cartId){
        return new ResponseEntity<List<CartSummaryDto>>(cartService.getActiveCart(cartId),HttpStatus.OK);
    }

    @GetMapping("/customer")
    public ResponseEntity<List<CartSummaryDto>> getActiveCartByCustomerId(@PathVariable Long customerId){
        return new ResponseEntity<List<CartSummaryDto>>(cartService.getActiveCartByCustomerId(customerId),HttpStatus.OK);
    }

}
