package com.ll.exam.final__mutbooks_v2.app.order.service;

import com.ll.exam.final__mutbooks_v2.app.cart.entity.CartItem;
import com.ll.exam.final__mutbooks_v2.app.cart.service.CartService;
import com.ll.exam.final__mutbooks_v2.app.member.entity.Member;
import com.ll.exam.final__mutbooks_v2.app.member.service.MemberService;
import com.ll.exam.final__mutbooks_v2.app.order.entity.Order;
import com.ll.exam.final__mutbooks_v2.app.order.entity.OrderItem;
import com.ll.exam.final__mutbooks_v2.app.order.repository.OrderRepository;
import com.ll.exam.final__mutbooks_v2.app.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final MemberService memberService;
    private final CartService cartService;
    private final OrderRepository orderRepository;

    public Optional<Order> findForPrintById(long id) {
        return findById(id);
    }

    private Optional<Order> findById(long id) {
        return orderRepository.findById(id);
    }

    public boolean actorCanSee(Member actor, Order order) {
        return actor.getId().equals(order.getBuyer().getId());
    }

    @Transactional
    public Order createFromCart(Member buyer) {
        List<CartItem> cartItems = cartService.getItemsByBuyer(buyer);

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();

            if (product.isOrderable()) {
                orderItems.add(new OrderItem(product));
            }

            cartService.removeItem(cartItem);
        }

        return create(buyer, orderItems);
    }

    @Transactional
    public Order create(Member buyer, List<OrderItem> orderItems) {
        Order order = Order
                .builder()
                .buyer(buyer)
                .build();

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        orderRepository.save(order);

        return order;
    }
}
