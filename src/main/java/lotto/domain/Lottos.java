package lotto.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public static Lottos createLottos(List<Lotto> lottos){
        return new Lottos(lottos);
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }

    public Map<LottoRank, Long> calculateResult(WinningNumbers winningNumbers) {
        Map<LottoRank, Long> result = new HashMap<>();

        for (Lotto lotto : lottos) {
            LottoRank rank = winningNumbers.match(lotto);
            result.put(rank, result.getOrDefault(rank, 0L) + 1);
        }

        return result;
    }
}

