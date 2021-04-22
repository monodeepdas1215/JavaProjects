package com.example.restservice.controllers;

import com.example.restservice.ItemRepository;
import com.example.restservice.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/v1")
public class ItemController {

    @Autowired
    private ItemRepository itemsRepository;


    @GetMapping("/items")
    public Iterable<Item> getAllItems() {
        return itemsRepository.findAll();
    }

    @GetMapping("/items/{id}")
    public Optional<Item> getItem(@PathVariable("id") Integer itemId) {
        return itemsRepository.findById(itemId);
    }

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Item createItem(@RequestBody Item itemBody) {

        Item n = new Item();
        n.setName(itemBody.getName());
        itemsRepository.save(n);
        return n;
    }

    @PostMapping("/items/{id}")
    public @ResponseBody Item editItem(@PathVariable("id") Integer itemId, @RequestBody Item newBody) {

        System.out.println(String.format("itemId: %d",itemId));

        Item result = itemsRepository.findById(itemId).get();
        System.out.println("result: " + result);
        return newBody;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "request id not found")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badIdExceptionHandler() {
    }
}
