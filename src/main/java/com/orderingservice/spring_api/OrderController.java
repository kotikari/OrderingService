package com.orderingservice.spring_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController    // This means that this class is a Controller
@RequestMapping(path = "/order") // This means URL's start with /demo (after Application path)
public class OrderController {
    @Autowired // This means to get the bean called OrderRepository
    private OrderRepository orderRepository;

    @PostMapping(path = "/add") // Map ONLY POST Requests
    public @ResponseBody
    String addNewOrder(@RequestParam String tortilla
            , @RequestParam String protein, @RequestParam String vegetables, @RequestParam String salsa, @RequestParam String extras) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        OrderDAO order = new OrderDAO();
        order.setTortilla(tortilla);
        order.setProtein(protein);
        order.setVegetables(vegetables);
        order.setSalsa(salsa);
        order.setExtras(extras);
        orderRepository.save(order);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<OrderDAO> getAllOrders() {
        // This returns a JSON or XML with the Orders
        return orderRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    Optional<OrderDAO> getUserById(@PathVariable("id") Integer id) {
        // This returns a JSON or XML with the users
        return orderRepository.findById(id);
    }
}
