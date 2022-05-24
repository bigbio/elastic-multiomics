package io.github.bigbio.elastic.multiomics.model;

import io.github.bigbio.elastic.multiomics.utils.Constants;

public enum PtmKey {

    name(Constants.PTM_MODIFICATION_NAME),
    accession(Constants.PTM_MODIFICATION_ACCESSION),
    mass(Constants.PTM_MODIFICATION_VALUE);

    private String elastname;

    PtmKey(String name) {
        this.elastname = name;
    }

    public String getElastname() {
        return elastname;
    }
}
