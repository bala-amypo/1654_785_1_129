package com.example.demo.service;
import org.springframework.stereotype.Service;
import com.example.demo.model.CartItem;
import java.util.List;
@Service
public interface CartItemService {
    CartItem addItemToCart(CartItem item);
    List<CartItem> getItemsForCart(Long cartId);
}
