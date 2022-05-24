package io.github.bigbio.elastic.multiomics.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;
import java.util.Objects;

@Data
@Builder
@Document(indexName = "multiomics-projects",  shards = 4)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {
    @Id
    String id;
    @Field( name = "reanalysis_type", store = false)
    String reanalysisType;
    String accession;
    String pubmed;
    String title;
    String description;
    List<Sample> samples;
    @Field( name = "text", store = false)
    String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (!Objects.equals(reanalysisType, project.reanalysisType))
            return false;
        if (!Objects.equals(accession, project.accession)) return false;
        if (!Objects.equals(pubmed, project.pubmed)) return false;
        if (!Objects.equals(title, project.title)) return false;
        if (!Objects.equals(description, project.description)) return false;
        return Objects.equals(samples, project.samples);
    }

    @Override
    public int hashCode() {
        int result = reanalysisType != null ? reanalysisType.hashCode() : 0;
        result = 31 * result + (accession != null ? accession.hashCode() : 0);
        result = 31 * result + (pubmed != null ? pubmed.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (samples != null ? samples.hashCode() : 0);
        return result;
    }
}
