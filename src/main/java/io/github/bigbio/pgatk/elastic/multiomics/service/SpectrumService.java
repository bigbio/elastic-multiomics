package io.github.bigbio.pgatk.elastic.multiomics.service;

import io.github.bigbio.pgatk.elastic.multiomics.model.ElasticSpectrum;
import io.github.bigbio.pgatk.elastic.multiomics.repository.SpectrumRepository;
import io.github.bigbio.pgatk.elastic.multiomics.utils.Constants;
import io.github.bigbio.pgatk.io.utils.Tuple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SpectrumService {

    private final SpectrumRepository spectrumRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    public SpectrumService(SpectrumRepository spectrumRepository, ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.spectrumRepository = spectrumRepository;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    public Optional<ElasticSpectrum> getById(String usi) {
        return spectrumRepository.findById(usi);
    }

    public List<ElasticSpectrum> getByIds(List<String> usis, Tuple<Integer, Integer> pageParams) {
        PageRequest pageRequest = PageRequest.of(pageParams.getKey(), pageParams.getValue());
        CriteriaQuery query = new CriteriaQuery(new Criteria("_id").in(usis))
                .addSort(Sort.by(Sort.Direction.ASC, Constants.USI_KEYWORD))
                .setPageable(pageRequest);

        return getSpectrums(query);
    }

    public List<ElasticSpectrum> findByPepSequence(String pepSequence, Tuple<Integer, Integer> pageParams) {
        PageRequest pageRequest = PageRequest.of(pageParams.getKey(), pageParams.getValue(), Sort.by(Sort.Direction.ASC, Constants.USI_KEYWORD));
        CriteriaQuery query = new CriteriaQuery(new Criteria("pepSequence").expression(pepSequence)).setPageable(pageRequest);
        return getSpectrums(query);
    }

    public Long findByPepSequenceCount(String pepSequence) {
        CriteriaQuery query = new CriteriaQuery(new Criteria("pepSequence").expression(pepSequence));
        return elasticsearchRestTemplate.count(query, ElasticSpectrum.class, Constants.INDEX_COORDINATES);
    }

    public List<ElasticSpectrum> findByQuery(CriteriaQuery query, Tuple<Integer, Integer> pageParams) {
        if (pageParams != null) {
            PageRequest pageRequest = PageRequest.of(pageParams.getKey(), pageParams.getValue(), Sort.by(Sort.Direction.ASC, Constants.USI_KEYWORD));
            query = query.setPageable(pageRequest);
        }
        return getSpectrums(query);
    }

    private List<ElasticSpectrum> getSpectrums(CriteriaQuery query) {
        SearchHits<ElasticSpectrum> searches = elasticsearchRestTemplate.search(query, ElasticSpectrum.class, Constants.INDEX_COORDINATES);
        List<ElasticSpectrum> elasticSpectrums = searches.stream().map(SearchHit::getContent).collect(Collectors.toList());
        return elasticSpectrums;
    }

    public Long getCountForQuery(CriteriaQuery query) {
        return elasticsearchRestTemplate.count(query, ElasticSpectrum.class, Constants.INDEX_COORDINATES);
    }

    /**
     * Save {@link ElasticSpectrum} in to Elastic indexes
     * @param spectrum
     */
    public void saveSpectrum(ElasticSpectrum spectrum) {
        Assert.notNull(spectrum, "questionLink is null !");
        spectrumRepository.save(spectrum);
    }

    /**
     * Delete all the spectrum in the index
     */
    public void deleteAll() {
        spectrumRepository.deleteAll();
    }

    /**
     * Delete by id
     * @param id String accession of spectrum
     */
    public void deleteById(String id) {
        spectrumRepository.deleteById(id);
    }

    /**
     * Find all the spectra in the index using some pagination
     * @param page Page to index
     * @param size Size.
     * @return
     */
    public List<ElasticSpectrum> findAllPage(int page, int size) {
        return spectrumRepository.findAll(PageRequest.of(page, size)).toList();
    }

    /**
     * Save batch spectra List
     * @param spectra List of @{@link ElasticSpectrum}
     */
    public void saveAll(List<ElasticSpectrum> spectra){
        spectrumRepository.saveAll(spectra);
    }

}

