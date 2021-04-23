package io.github.bigbio.pgatk.elastic.multiomics.repository;

import io.github.bigbio.pgatk.elastic.multiomics.model.ElasticSpectrum;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface SpectrumRepositoryStream extends ElasticsearchRepository<ElasticSpectrum, String> {
    Stream<ElasticSpectrum> findByPepSequenceLike(String pep);
    Stream<ElasticSpectrum> findByPepSequenceContaining(String pep);
}
