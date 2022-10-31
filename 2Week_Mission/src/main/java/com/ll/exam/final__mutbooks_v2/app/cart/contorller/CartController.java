package com.ll.exam.final__mutbooks_v2.app.cart.contorller;

import com.ll.exam.final__mutbooks_v2.app.base.rq.Rq;
import com.ll.exam.final__mutbooks_v2.app.cart.entity.CartItem;
import com.ll.exam.final__mutbooks_v2.app.cart.service.CartService;
import com.ll.exam.final__mutbooks_v2.app.member.entity.Member;
import com.ll.exam.final__mutbooks_v2.app.product.entity.Product;
import com.ll.exam.final__mutbooks_v2.app.product.service.ProductService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;
    private final Rq rq;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add/{productId}")
    public String addItem(@PathVariable long productId) {
        Member buyer = rq.getMember();

        Product product = productService.findById(productId).orElse(null);;

        if(cartService.hasItem(buyer, product)){
            return rq.redirectWithMsg("/product/list", "장바구니에 존재하는 상품입니다.");
        }

        CartItem cartItems = cartService.addItem(buyer, product);

        return rq.redirectWithMsg("/product/list", "장바구니에 추가되었습니다.");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/list")
    public String showItem(Model model) {
        Member buyer = rq.getMember();

        List<CartItem> cartItems = cartService.getItemsByBuyer(buyer);

        model.addAttribute("cartItems", cartItems);

        return "cart/list";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/remove/{productId}")
    public String removeItem(@PathVariable("productId") long id) {
        Member buyer = rq.getMember();

        CartItem cartItem = cartService.findItemById(id).orElse(null);

        if (cartService.actorCanDelete(buyer, cartItem)) {
            cartService.removeItem(cartItem);
        }

        return rq.redirectWithMsg("/cart/list", "상품을 삭제하였습니다.");
    }
}
