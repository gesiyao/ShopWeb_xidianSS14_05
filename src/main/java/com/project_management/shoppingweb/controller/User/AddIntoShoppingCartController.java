package com.project_management.shoppingweb.controller.User;


import com.project_management.shoppingweb.domain.ShoppingCart;
import com.project_management.shoppingweb.service.User.User_ShoppingCartService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class AddIntoShoppingCartController {
    @Autowired
    private User_ShoppingCartService userShoppingCartService;
    @RequestMapping(value = "/AddIntoShoppingCart", method = RequestMethod.GET)

    public String AddIntoShoppingCart(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        String ProductID = request.getParameter("ProductID");
        String ShopID = request.getParameter("ShopID");
        String UnitPrice = request.getParameter("UnitPrice");
        if(UserID.equals("")){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UnitPrice", UnitPrice);
            System.out.println("no");
            return "/User/Product";
        }

        List<ShoppingCart> GlobalShoppingCart = new ArrayList<ShoppingCart>();
        GlobalShoppingCart = userShoppingCartService.findAllByUserId(Long.parseLong(UserID));
        for(int i = 0; i < GlobalShoppingCart.size(); i++){
            if(GlobalShoppingCart.get(i).getProductId() == Long.parseLong(ProductID)){
                model.addAttribute("UserID", UserID);
                model.addAttribute("ProductID", ProductID);
                model.addAttribute("ShopID", ShopID);
                System.out.println("no");
                model.addAttribute("UnitPrice", UnitPrice);
                return "/User/Product";
            }
        }

        String ProductAmount = request.getParameter("ProductAmount");
        int ProductNumber = Integer.parseInt(ProductAmount);
        if(ProductNumber <= 0){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            System.out.println("no");
            model.addAttribute("UnitPrice", UnitPrice);
            return "/User/Product";
        }


        Date now = new Date();
        ShoppingCart shoppingCart = new ShoppingCart();
        //shoppingCart.setShoppingcartId((long)1);
        shoppingCart.setCreatetime(now);
        shoppingCart.setProductAmount(ProductNumber);
        shoppingCart.setProductId(Long.parseLong(ProductID));
        shoppingCart.setUserId(Long.parseLong(UserID));

       userShoppingCartService.save(shoppingCart);

       System.out.println("ok");
        model.addAttribute("UserID", UserID);
        model.addAttribute("ProductID", ProductID);
        model.addAttribute("ShopID", ShopID);
        model.addAttribute("UnitPrice", UnitPrice);
        return "/User/Product";

    }
}
