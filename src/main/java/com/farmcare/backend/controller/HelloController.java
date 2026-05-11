package com.farmcare.backend.controller;

import com.farmcare.backend.entity.Crop;
import com.farmcare.backend.repository.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class HelloController {

    @Autowired
    private CropRepository cropRepository;

    @GetMapping("/")
    public String home() {
        return "FarmCare Backend is Running 🚀";
    }

    @GetMapping("/test")
    public String test() {
        return "API is working successfully!";
    }

    @PostMapping("/add")
    public Crop addCrop(@RequestBody Crop crop) {
        return cropRepository.save(crop);
    }
    @GetMapping("/all")
    public java.util.List<Crop> getAll() {
        return cropRepository.findAll();
    }
    @GetMapping("/crop/{id}")
    public Crop getById(@PathVariable Long id) {
        return cropRepository.findById(id).orElse(null);
    }
    @PutMapping("/update/{id}")
    public Crop updateCrop(@PathVariable Long id, @RequestBody Crop newCrop) {
        return cropRepository.findById(id).map(crop -> {
            crop.setName(newCrop.getName());
            crop.setType(newCrop.getType());
            crop.setDisease(newCrop.getDisease());
            return cropRepository.save(crop);
        }).orElse(null);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteCrop(@PathVariable Long id) {
        cropRepository.deleteById(id);
        return "Crop deleted successfully";
    }
}