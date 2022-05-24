package io.github.bigbio.elastic.multiomics.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Builder
@Document(indexName = "multiomics-peptides",  shards = 4)
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuantPeptide {

    @Id
    private String id;

    @Field(name = "peptide", store = true, type = FieldType.Keyword)
    private String peptide;

    @Field(name = "peptidoform", store = true, type = FieldType.Keyword)
    private String peptidoform;

    @Field(name = "best_search_engine", store = true, type = FieldType.Keyword)
    private String bestSearchEngine;

    @Field(name = "usi", store = true, type = FieldType.Keyword)
    private String usi;

    @Field(name = "protein_name", store = true, type = FieldType.Keyword)
    private String proteinName;

    @Field(name = "gene_name", store = true, type = FieldType.Keyword)
    private String geneName;

    @Field( name = "synonyms", store = true, type = FieldType.Keyword)
    private String synonyms;

    @Field( name = "intensity", store = true)
    private Double intensity;

    @Field( name = "sample_accession", store = true, type = FieldType.Keyword)
    private String sampleAccession;

    @Field( name = "factor_value", store = true, type = FieldType.Keyword)
    private Tuple<String, String> factorValue;

    @Field( name = "text", store = false)
    private String text;
}
