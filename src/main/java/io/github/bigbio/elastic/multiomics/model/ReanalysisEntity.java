package io.github.bigbio.elastic.multiomics.model;

public interface ReanalysisEntity {

    /**
     * Each molecule, gene, protein or peptide is associated with a Project reanalysis
     * @return
     */
    public String getReanalysisProjectId();
}
