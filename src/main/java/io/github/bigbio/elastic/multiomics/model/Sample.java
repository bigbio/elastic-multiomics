package io.github.bigbio.elastic.multiomics.model;

import java.util.List;

public class Sample {
    String accession;
    List<Tuple<String, String>> characteristics;
    List<SampleFile> files;

}
