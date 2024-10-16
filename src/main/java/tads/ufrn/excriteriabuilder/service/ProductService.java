package tads.ufrn.excriteriabuilder.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tads.ufrn.excriteriabuilder.domain.Product;
import tads.ufrn.excriteriabuilder.domain.dto.ProductFilterDTO;
import tads.ufrn.excriteriabuilder.domain.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    @PersistenceContext
    private EntityManager entityManager;
    private final ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public List<Product> searchByFilters(ProductFilterDTO filterDTO) {



        // Criamos um CriteriaBuilder, que é a API usada para construir consultas criteriosas de forma programática
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();



        // Criamos um CriteriaQuery para o tipo Product, ou seja, estamos preparando uma consulta que retornará produtos
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);



        // Definimos a entidade raiz (Root) da consulta, que neste caso é a tabela de produtos
        Root<Product> product = cq.from(Product.class);



        // Inicializamos uma lista de Predicates, que irá armazenar os filtros (condições) que serão aplicados na consulta
        List<Predicate> predicates = new ArrayList<>();



        // Verificamos se o nome do produto foi fornecido no filtro
        if (filterDTO.getName() != null && !filterDTO.getName().isEmpty()) {

            // Adicionamos um filtro para buscar produtos cujo nome contém o valor fornecido (ignora maiúsculas/minúsculas)
            predicates.add(cb.like(cb.lower(product.get("name")), "%" + filterDTO.getName().toLowerCase() + "%"));
        }


        // Verificamos se o filtro de preço mínimo foi fornecido
        if (filterDTO.getMinPrice() != null) {

            // Adicionamos um filtro para garantir que o preço do produto seja maior ou igual ao valor fornecido
            predicates.add(cb.greaterThanOrEqualTo(product.get("price"), filterDTO.getMinPrice()));
        }


        // Verificamos se o filtro de preço máximo foi fornecido
        if (filterDTO.getMaxPrice() != null) {

            // Adicionamos um filtro para garantir que o preço do produto seja menor ou igual ao valor fornecido
            predicates.add(cb.lessThanOrEqualTo(product.get("price"), filterDTO.getMaxPrice()));
        }


        // Verificamos se a categoria foi fornecida no filtro
        if (filterDTO.getCategory() != null && !filterDTO.getCategory().isEmpty()) {

            // Adicionamos um filtro para buscar produtos que pertencem à categoria especificada
            predicates.add(cb.equal(product.get("category"), filterDTO.getCategory()));
        }


        // Aplica os filtros (predicados) na consulta; a função where define as condições que serão usadas na consulta
        cq.where(predicates.toArray(new Predicate[0]));

        // Criamos uma consulta (TypedQuery) com base nos critérios definidos
        TypedQuery<Product> query = entityManager.createQuery(cq);

        // Executamos a consulta e retornamos os resultados como uma lista de produtos
        return query.getResultList();
    }


}
