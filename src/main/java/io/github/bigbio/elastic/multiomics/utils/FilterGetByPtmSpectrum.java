package io.github.bigbio.elastic.multiomics.utils;

import io.github.bigbio.elastic.multiomics.model.QuantGene;
import io.github.bigbio.elastic.multiomics.model.PtmKey;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class FilterGetByPtmSpectrum implements Function<List<QuantGene>, List<QuantGene>> {

//    final PtmKey ptmKey;
//    final String ptmValue;
//    final List<Integer> positions;

//    public FilterGetByPtmSpectrum(PtmKey ptmKey, String ptmValue, List<Integer> positions) {
//        this.ptmKey = ptmKey;
//        this.ptmValue = ptmValue;
//        this.positions = positions;
//    }
//
//    @Override
//    public List<QuantGene> apply(List<QuantGene> elasticSpectrums) {
//        String ptmKeyName = ptmKey.name();
//        List<QuantGene> elasticSpectrumsFiltered = new ArrayList<>();
//        elasticSpectrums.forEach(a -> {
//            a.getModifications().forEach(m -> {
//                if (positions.contains(m.getPosition())) {
//                    if ((ptmKeyName.equals("accession") && m.getAccession().equals(ptmValue)) ||
//                            (ptmKeyName.equals("name") && m.getModification().equals(ptmValue))) {
//                            elasticSpectrumsFiltered.add(a);
//                            return;
//                        }
//                    }
//            });
//        });
//        return elasticSpectrumsFiltered;
//    }
}
