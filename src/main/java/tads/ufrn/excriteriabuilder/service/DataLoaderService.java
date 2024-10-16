package tads.ufrn.excriteriabuilder.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tads.ufrn.excriteriabuilder.domain.Product;
import tads.ufrn.excriteriabuilder.domain.repository.ProductRepository;

@Service
@AllArgsConstructor
public class DataLoaderService {

    private final ProductRepository repository;
    
    
    @PostConstruct
    public void init() {
        Product p1 = new Product();
        p1.setName("TV Samsung 55 polegadas");
        p1.setPrice(2500.00);
        p1.setCategory("Eletrônicos");

        Product p2 = new Product();
        p2.setName("Geladeira Brastemp");
        p2.setPrice(1800.00);
        p2.setCategory("Eletrodomésticos");

        Product p3 = new Product();
        p3.setName("Notebook Dell");
        p3.setPrice(3500.00);
        p3.setCategory("Informática");

        Product p4 = new Product();
        p4.setName("Smartphone Motorola");
        p4.setPrice(1200.00);
        p4.setCategory("Eletrônicos");

        Product p5 = new Product();
        p5.setName("Fogão 4 bocas");
        p5.setPrice(850.00);
        p5.setCategory("Eletrodomésticos");

        // Salvando no repositório
        repository.save(p1);
        repository.save(p2);
        repository.save(p3);
        repository.save(p4);
        repository.save(p5);
    }
}