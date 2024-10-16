package tads.ufrn.excriteriabuilder.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tads.ufrn.excriteriabuilder.domain.Product;
import tads.ufrn.excriteriabuilder.domain.dto.ProductFilterDTO;
import tads.ufrn.excriteriabuilder.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("api/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping("/search")
    public ResponseEntity<List<Product>> searchPoducts(@RequestBody ProductFilterDTO filterDTO) {
        return ResponseEntity.ok().body(service.searchByFilters(filterDTO));
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

}
