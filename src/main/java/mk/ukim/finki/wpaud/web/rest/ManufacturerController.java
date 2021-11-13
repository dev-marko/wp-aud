package mk.ukim.finki.wpaud.web.rest;

import mk.ukim.finki.wpaud.model.Manufacturer;
import mk.ukim.finki.wpaud.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public List<Manufacturer> findAll() {
        return this.manufacturerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> findById (@PathVariable Long id) {
        return this.manufacturerService.findById(id).map(i -> ResponseEntity.ok().body(i)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Manufacturer> save(@RequestParam String name, @RequestParam String address) {
        return this.manufacturerService.save(name, address).map(i -> ResponseEntity.ok().body(i)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        if (this.manufacturerService.deleteById(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
