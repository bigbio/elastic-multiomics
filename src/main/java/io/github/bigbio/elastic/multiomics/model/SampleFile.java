package io.github.bigbio.elastic.multiomics.model;


import java.util.List;

public class SampleFile {
    String accession;
    String name;
    String collectionName;
    List<Tuple<String, String>> comments;
}
