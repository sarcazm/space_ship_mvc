package com.ship.controller;

import com.ship.dto.ShipDtoForSearch;
import com.ship.entity.Ship;
import com.ship.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ShipController {

    private final ShipService shipService;

    @Autowired
    public ShipController(ShipService shipService) {
        this.shipService = shipService;

    }

    @GetMapping("/")
    public ModelAndView home(){
        List<Ship> result = shipService.getAll();
        ModelAndView mav = new ModelAndView("main-ships");
        mav.addObject("result", result);
        return mav;
    }

    @GetMapping("/new")
    public String newShipForm(Map<String, Object> model){
        Ship ship = new Ship();
        model.put("ship", ship);
        return "new_ship";
    }
    @PostMapping("/save")
    public String saveShip(@ModelAttribute("ship") Ship ship){
        shipService.add(ship);
        return "redirect:/";
    }
    @GetMapping("/edit")
    public ModelAndView editShipForm(@RequestParam Integer id){
        ModelAndView mav = new ModelAndView("edit_ship");
        Ship ship = shipService.getById(id);
        mav.addObject("ship", ship);
        return mav;
    }

    @GetMapping("/delete")
    public String saveShip(@RequestParam Integer id){
        shipService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String searchShipForm(Map<String, Object> model){
        ShipDtoForSearch shipDtoForSearch = new ShipDtoForSearch();
        model.put("shipDtoForSearch", shipDtoForSearch);
        return "search-ship";
    }
    @GetMapping("search-ship-by-param")
    public ModelAndView searchShip(@ModelAttribute("shipDtoForSearch") ShipDtoForSearch shipDtoForSearch){
        System.out.println(shipDtoForSearch);
        List<Ship> result = shipService.getCountBy(shipDtoForSearch.paramToMap());
        System.out.println("конечный list = " + result);
        ModelAndView mav = new ModelAndView("search-ship");
        mav.addObject("result", result);
        return mav;
    }




}
