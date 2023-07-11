package com.mock.exotourista.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mock.exotourista.Model.Hotel;
import com.mock.exotourista.Repository.HotelRepo;

@CrossOrigin("*")
@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelRepo hotelrepo;
    
    @GetMapping("/getall")
    public List<Hotel> getallhotels(){
        return hotelrepo.findAll();
    }
    
    @PostMapping("/add")
    public String addHotel(@RequestBody Hotel hotels){
            hotelrepo.save(hotels);
            return "Hotel added !";
    }

    @GetMapping("/add/{id}")
    public Hotel getHotel(@PathVariable Long id){
        
       return hotelrepo.findById(id).orElse(null);

    }

    @DeleteMapping("/deleteall")
    public String deleteAllHotels(){
        hotelrepo.deleteAll();
        return "All hotels deleted";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteHotel(@PathVariable Long id){
        hotelrepo.deleteById(id);
        return "hotel deleted Successfully";
    }
}
