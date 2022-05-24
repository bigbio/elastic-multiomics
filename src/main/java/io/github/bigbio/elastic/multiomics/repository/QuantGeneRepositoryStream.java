package io.github.bigbio.elastic.multiomics.repository;

import io.github.bigbio.elastic.multiomics.model.QuantGene;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface QuantGeneRepositoryStream extends ElasticsearchRepository<QuantGene, String> {
//    Stream<QuantGene> findByPepSequenceLike(String pep);
//    Stream<QuantGene> findByPepSequenceContaining(String pep);
}
