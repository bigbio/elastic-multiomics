package io.github.bigbio.elastic.multiomics.repository;


import io.github.bigbio.elastic.multiomics.model.QuantGene;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuantGeneRepository extends ElasticsearchRepository<QuantGene, String> {
    List<QuantGene> findByGeneName(String pep);
    List<QuantGene> findByFactorValue(String factorValue, Pageable pageable);
}
