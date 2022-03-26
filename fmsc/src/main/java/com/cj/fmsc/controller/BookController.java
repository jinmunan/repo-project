package com.cj.fmsc.controller;

import com.cj.fmsc.entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;


/**
 * Created by Jinmunan
 * 2022/3/26
 * 21:37
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @RequestMapping("/query")
    public String queryBook(int bookId, Model model) {
        Book book = new Book(1,"海底两万里",22);
        //简单类型
        model.addAttribute("price",22);
        //字符串类型
        model.addAttribute("str","asdasdasedasdasd");
        //对象
        model.addAttribute("book",book);
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1,"海底两万里1",30));
        books.add(new Book(1,"海底两万里2",40));
        books.add(new Book(1,"海底两万里3",40));
        //集合
        model.addAttribute("books", books);
        //css内敛
        model.addAttribute("color","orange");

        return "test";
    }
}
