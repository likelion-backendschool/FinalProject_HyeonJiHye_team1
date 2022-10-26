package com.ll.exam.final__mutbooks_v2.app.order.contorller;

import com.ll.exam.final__mutbooks_v2.app.base.rq.Rq;
import com.ll.exam.final__mutbooks_v2.app.member.entity.Member;
import com.ll.exam.final__mutbooks_v2.app.order.entity.Order;
import com.ll.exam.final__mutbooks_v2.app.order.exception.ActorCanNotSeeOrderException;
import com.ll.exam.final__mutbooks_v2.app.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final Rq rq;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public String showDetail(@PathVariable long id, Model model) {
        Order order = orderService.findForPrintById(id).get();

        Member actor = rq.getMember();

        if (orderService.actorCanSee(actor, order) == false) {
            throw new ActorCanNotSeeOrderException();
        }

        model.addAttribute("order", order);

        return "order/detail";
    }

}
