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
@Document(indexName = "multiomics-proteins",  shards = 4)
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuantProtein {

    @Id
    private String id;

    @Field(name = "protein_name", store = true, type = FieldType.Keyword)
    private String proteinName;

    @Field(name = "gene_name", store = true, type = FieldType.Keyword)
    private String geneName;

    @Field( name = "synonyms", store = true, type = FieldType.Keyword)
    private String synonyms;

    @Field( name = "ibaq_ppb", store = true)
    private Double ibaqPPB;

    @Field( name = "sample_accession", store = true, type = FieldType.Keyword)
    private String sampleAccession;

    @Field( name = "factor_value", store = true, type = FieldType.Keyword)
    private Tuple<String, String> factorValue;

    @Field( name = "text", store = false)
    private String text;

    @Field( name = "project_id")
    private String projectID;
}
