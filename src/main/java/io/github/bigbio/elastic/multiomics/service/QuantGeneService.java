package io.github.bigbio.elastic.multiomics.service;

import io.github.bigbio.elastic.multiomics.model.QuantGene;
import io.github.bigbio.elastic.multiomics.repository.QuantGeneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@Service
public class QuantGeneService {

    private final QuantGeneRepository quantGeneRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    public QuantGeneService(QuantGeneRepository quantGeneRepository, ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.quantGeneRepository = quantGeneRepository;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    public List<QuantGene> getGeneName(String geneName) {
        return quantGeneRepository.findByGeneName(geneName);
    }


//
//    public List<QuantGene> findByPepSequence(String pepSequence, Tuple<Integer, Integer> pageParams) {
//        PageRequest pageRequest = PageRequest.of(pageParams.getKey(), pageParams.getValue(),
//                Sort.by(Sort.Direction.ASC, Constants.USI_KEYWORD));
//        CriteriaQuery query = new CriteriaQuery(new Criteria("pepSequence")
//                .expression(pepSequence)).setPageable(pageRequest);
//        return getSpectrums(query);
//    }
//
//    public Long findByPepSequenceCount(String pepSequence) {
//        CriteriaQuery query = new CriteriaQuery(new Criteria("pepSequence").expression(pepSequence));
//        return elasticsearchRestTemplate.count(query, QuantGene.class, Constants.INDEX_COORDINATES);
//    }
//
//    public List<QuantGene> findByQuery(CriteriaQuery query, Tuple<Integer, Integer> pageParams) {
//        if (pageParams != null) {
//            PageRequest pageRequest = PageRequest.of(pageParams.getKey(), pageParams.getValue(),
//                    Sort.by(Sort.Direction.ASC, Constants.USI_KEYWORD));
//            query = query.setPageable(pageRequest);
//        }
//        return getSpectrums(query);
//    }
//
//    private List<QuantGene> getSpectrums(CriteriaQuery query) {
//        SearchHits<QuantGene> searches = elasticsearchRestTemplate.search(query, QuantGene.class,
//                Constants.INDEX_COORDINATES);
//        List<QuantGene> elasticSpectrums = searches.stream()
//                .map(SearchHit::getContent).collect(Collectors.toList());
//        return elasticSpectrums;
//    }
//
//    public Long getCountForQuery(CriteriaQuery query) {
//        return elasticsearchRestTemplate.count(query, QuantGene.class, Constants.INDEX_COORDINATES);
//    }
//
    /**
     * Save {@link QuantGene} in to Elastic indexes
     * @param gene
     */
    public void saveQuantGene(QuantGene gene) {
        Assert.notNull(gene, "questionLink is null !");
        quantGeneRepository.save(gene);
    }
//
    /**
     * Delete all the spectrum in the index
     */
    public void deleteAll() {
        quantGeneRepository.deleteAll();
    }
//
//    /**
//     * Delete by id
//     * @param id String accession of spectrum
//     */
//    public void deleteById(String id) {
//        quantGeneRepository.deleteById(id);
//    }

    /**
     * Find all the spectra in the index using some pagination
     * @param page Page to index
     * @param size Size.
     * @return
     */
    public List<QuantGene> findAllPage(int page, int size) {
        return quantGeneRepository.findAll(PageRequest.of(page, size)).toList();
    }

    /**
     * Save batch spectra List
     * @param genes List of @{@link QuantGene}
     */
    public void saveAll(List<QuantGene> genes){
        quantGeneRepository.saveAll(genes);
    }

}

