package io.github.bigbio.pgatk.elastic.multiomics.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.bigbio.pgatk.io.pride.*;
import io.github.bigbio.pgatk.io.utils.Tuple;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
import java.util.Set;

@Data
@Builder
@Document(indexName = "spectra-documents",  shards = 4)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ElasticSpectrum {

    /**
     * The id will be also the USI.
     */
    @Id
    private String id;

    /**
     * USI of the spectra. The USI is the accession of the project that originate the dataset, the collection (RAW file)
     * that contains the spectrum and the scan number of the spectra.
     */
    @Field(name = "usi", store = true, type = FieldType.Keyword)
    private String usi;

    /**
     * The peptide sequence.
     */
    @Field( name = "pepSequence", store = true, type = FieldType.Keyword)
    private String pepSequence;

    /**
     * The peptidoform information. The combination of peptide Sequence + modification in position.
     */
    @Field( name = "peptidoform", store = true, type = FieldType.Keyword)
    private String peptidoform;

    // Sample information

    /**
     * Protein accessions. A {@link Set} of protein accessions from Uniprot, ENSEMBL, or other major databases.
     */
    @Field( name = "proteinAccessions", store = true)
    private List<String> proteinAccessions;

    /**
     * Gene related accessions, including: Transcript, Exon Accessions from multiple databases.
     */
    @Field(name = "geneAccessions", store = true)
    private List<String> geneAccessions;

    /**
     * Protein localization including:
     *  - Accession of the protein
     *  - start: start in the sequence 1-based
     *  - end: end in the sequence (start + length (peptideSequence))
     */
    @Field( name = "proteinLocalizations", store = true, type = FieldType.Nested, includeInParent = true)
    private List<AccessionLocalization> proteinLocalizations;

    /**
     * Gene Coordinates are more complex than Protein localizations, it contains, Transcript position, Gene
     * positions and exon information.
     */
    @Field( name = "geneLocalizations", store = true, type = FieldType.Nested, includeInParent = true)
    private List<GeneCoordinates> geneLocalizations;

    /**
     * The organism where the peptide has been found/identified. In PRIDE some peptides are associated to more than one
     * species, a unique species/organism should be selected.
     */
    @Field( name = "organism", store = true, type = FieldType.Keyword)
    private String organism;

    /**
     * Sample accession for peptide
     */
    @Field( name = "sampleAccession", store = true, type = FieldType.Keyword)
    String sampleAccession;

    /**
     * Sample information from SDRF, the sample information a a list of key value pairs from samples, for example:
     *  - organism:homo sapiens
     *  - organism part: brain
     */
    @Field( name = "sample", store = true, type = FieldType.Nested, includeInParent = true)
    private List<AvroTuple> sample;

    /**
     * Additional biological annotations for the peptide as keywords, for example:
     *  - non-canonical
     *  - unique peptide
     *  - variant
     */
    @Field(name = "biologicalAnnotations", store = true)
    List<AvroTuple> biologicalAnnotations;

    // Information about the Mass spectrometry (Spectrum)

    /**
     * Precursor Mz
     */
    @Field( name = "precursorMz", store = true)
    private double precursorMz;

    /**
     * Precursor charge
     */
    @Field( name = "precursorCharge", store = true)
    private int precursorCharge;

    /**
     * Structure of Post-translational modifications as position+name-modification+score of the quality of PTM.
     */
    @Field( name = "modifications", store = true, type = FieldType.Nested, includeInParent = true)
    private List<AvroModification> modifications;

    /**
     * The modification names, a Set of modification Strings for better searching.
     */
    @Field( name = "modificationNames", store = true)
    private List<String> modificationNames;

    /**
     * The modification accessions.
     */
    @Field( name = "modificationAccessions", store = true)
    private List<String> modificationAccessions;

    /**
     * Spectrum masses, a list of doubles where each value correspond to a peak mass value.
     */
    @Field( name = "masses", store = true, index = false)
    private List<Double> masses;

    /**
     * Spectrum intensities, a list of doubles where each value correspond to a peak intensity value.
     */
    @Field( name = "intensities", store = true, index = false)
    private List<Double> intensities;

    /**
     * Spectrum retention time.
     */
    @Field( name = "retentionTime", store = true)
    Double retentionTime;

    /**
     * Spectrum retention time.
     */
    @Field( name = "msLevel", store = true)
    int msLevel;

    /**
     * Number of missclevages for the peptide, this is related with the cleavage agent used to generate the peptide
     */
    @Field( name = "missedCleavages", store = true)
    Integer missedCleavages;

    /**
     * List of spectrum identification scores and statistical assessment scores such as q-value, posterior error
     * probabilities, or p-values.
     *
     */
    @Field(name = "qualityScores", store = true, type = FieldType.Nested, includeInParent = true)
    private List<AvroTerm> qualityScores;

    /**
     * A list of String values that to characterize the MS information, example:
     * - pass FDR threshold
     * - Orbitrap LTQ
     */
    @Field( name = "msAnnotations", store = true)
    List<AvroTuple> msAnnotations;

    /**
     * A list of ProteomeXchange projects that has been used to generate the following peptide.
     */
    @Field( name = "pxAccession", store = true)
    private String pxAccession;

    /**
     * A list of ProteomeXchange projects that has been used to generate the following peptide.
     */
    @Field( name = "isDecoy", store = true)
    private Boolean isDecoy;

    /**
     * Raw peptide intensity in the sample
     */
    @Field(name = "peptideIntensity", store = true)
    private Double peptideIntensity;

    /**
     * A general Text that contains all the information of all Text in fields for general search.
     */
    @Field( name = "text", store = true)
    private List<String> text;

}
