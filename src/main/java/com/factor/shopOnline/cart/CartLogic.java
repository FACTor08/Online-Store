package com.factor.shopOnline.cart;

import com.factor.shopOnline.product.ProductRepo;
import com.factor.shopOnline.product.Products;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class CartLogic {
    private final CartRepo cartRepo;
    private final ProductRepo productRepo;

    public CartResponse addCart(String user, CartRequest cartRequest) {
        log.info("Adding {} to {} cart", cartRequest.product(),user);
        if (cartRequest.quantity() == 0 || cartRequest.quantity() < 0 || cartRequest.product() == null) {
            throw new IllegalArgumentException("Add at least one product to a cart");
        }
        log.info("searching for {} in product DB", cartRequest.product());
        Products products = productRepo.findByProductIgnoreCase(cartRequest.product())
                .orElseThrow(() -> new IllegalArgumentException("Product not available"));
        log.info("searching {} and {} in cart DB", user, cartRequest.product());
        Cart items = cartRepo.findByUserAndProductId(user, products.getId())
                .orElseGet(() -> new Cart(0,user,products,0));

        int newQty = items.getQuantity() + cartRequest.quantity();
        if (products.getAvailableUnits() == 0) {
            throw new IllegalArgumentException("Quantity out of stock");
        }
        if (newQty > products.getAvailableUnits()){
            throw new IllegalArgumentException(products.getProduct() + " is out of stock");
        }
        items.setQuantity(newQty);
        log.info("{} was added to {} cart", cartRequest.product(), user);
        cartRepo.save(items);
        return getCart(user);
    }
    public CartResponse getCart(String user) {
        log.info("searching for {} in cart DB and generating Response", user);
        List<CartContent> cart = cartRepo.findByUser(user)
                .stream().map(i -> CartContent.builder()
                                .id(i.getProduct().getId())
                                .product(i.getProduct().getProduct())
                                .price(i.getProduct().getPrice())
                                .quantity(i.getQuantity())
                                .grandTotal(i.getProduct().getPrice() * i.getQuantity())
                                .build()
                        ).toList();
        double total = cart.stream().mapToDouble(CartContent::grandTotal).sum();
        return CartResponse.builder().items(cart).total(total).build();
    }
    public CartResponse removeFromCart(String user, CartRequest cartRequest) {
        log.info("removing {} from {} cart", cartRequest.product(),user);
        Products products = productRepo.findByProductIgnoreCase(cartRequest.product())
                .orElseThrow(() -> new IllegalArgumentException("Product not available"));

        Cart remove= cartRepo.findByUserAndProductId(user, products.getId())
                .orElseThrow(() -> new IllegalArgumentException("Cart is empty"));

        int newQty = remove.getQuantity() - cartRequest.quantity();
        remove.setQuantity(newQty);
        log.info("{} was removed from {} cart", cartRequest.product(), user);
                cartRepo.delete(remove);

        return getCart(user);
    }

    public CartResponse updateQuantity(String user, CartRequest cartRequest) {
        log.info("updating {} in {} cart", cartRequest.product(),user);
        Products products = productRepo.findByProductIgnoreCase(cartRequest.product())
                .orElseThrow(() -> new IllegalArgumentException("Product not available"));

        Cart update = cartRepo.findByUserAndProductId(user, products.getId())
                .orElseThrow(() -> new IllegalArgumentException("Cart is empty"));
    if (cartRequest.quantity() == 0 || cartRequest.quantity() < 0 || cartRequest.product() == null) {
        update.setQuantity(cartRequest.quantity());
    }
    cartRepo.save(update);
    return getCart(user);
    }
    public CartResponse emptyCart(String user) {
        Cart delete = cartRepo.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("User has no existing cart"));
        cartRepo.delete(delete);

        return getCart(user);
    }
}
