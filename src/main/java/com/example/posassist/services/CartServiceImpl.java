/*
package com.example.posassist.services;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.posassist.dto.request.CartDTO;
import com.example.posassist.entities.Cart;
import com.example.posassist.exceptions.ResourceNotFoundException;
import com.example.posassist.repositories.CartRepository;
import com.example.posassist.services.interfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart saveCart(CartDTO cartDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Cart findCartById(Long id) throws ResourceNotFoundException {
        Optional<Cart> cart = cartRepository.findById(id);
        if(!cart.isPresent())
            throw new ResourceNotFoundException("This cart/table order does not exist");
        return cart.get();
    }

    @Override
    public Cart findCartByCustomer(String customerName) throws ResourceNotFoundException {
        Optional<Cart> cart = cartRepository.findByCustomerName(customerName);
        if(!cart.isPresent())
            throw new ResourceNotFoundException("Cart with customer name not found");
        return cart.get();
    }

    @Override
    public Cart findCartByStaff(String staffName) throws ResourceNotFoundException {
        Optional<Cart> cart = cartRepository.findByStaffName(staffName);
        if(!cart.isPresent())
            throw new ResourceNotFoundException("Cart with customer name not found");
        return cart.get();
    }

    @Override
    public List<Cart> findAllCartOrders() {
        return cartRepository.findAll();
    }

    @Override
    @Transactional
    public Cart updateCartDetails(Long id, CartDTO cartDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.delete(findCartById(id));
    }

}
*/
