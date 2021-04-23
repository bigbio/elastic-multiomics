package io.github.bigbio.pgatk.elastic.multiomics.repository;


import io.github.bigbio.pgatk.elastic.multiomics.model.ElasticSpectrum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpectrumRepository extends ElasticsearchRepository<ElasticSpectrum, String> {
    List<ElasticSpectrum> findByPepSequenceLike(String pep, Pageable pageable);
    List<ElasticSpectrum> findByUsiIn(List<String> usi, Pageable pageable);
    ElasticSpectrum findByUsi(String usi);
}
