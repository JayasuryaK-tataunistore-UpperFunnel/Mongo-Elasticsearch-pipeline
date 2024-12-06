package com.example.elasticsearchandmongo.elasticpackage.repository;



import com.example.elasticsearchandmongo.elasticpackage.model.ProductElastic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;



public interface ProductElasticRepository extends ElasticsearchRepository<ProductElastic, String> {

    Page<ProductElastic> findAll(Pageable pageable);

}
