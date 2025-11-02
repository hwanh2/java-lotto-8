package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.Lottos;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###");

    public void printPurchaseResult(Lottos lottos) {
        System.out.println();
        System.out.println(lottos.size() + "개를 구매했습니다.");
        
        List<Lotto> lottoList = lottos.getLottos();
        for (Lotto lotto : lottoList) {
            printLotto(lotto);
        }
    }

    private void printLotto(Lotto lotto) {
        System.out.println(lotto.getNumbers());
    }

    public void printWinningStatistics(Map<LottoRank, Long> result) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        
        printRankResult(LottoRank.FIFTH, result);
        printRankResult(LottoRank.FOURTH, result);
        printRankResult(LottoRank.THIRD, result);
        printRankResult(LottoRank.SECOND, result);
        printRankResult(LottoRank.FIRST, result);
    }

    private void printRankResult(LottoRank rank, Map<LottoRank, Long> result) {
        long count = result.getOrDefault(rank, 0L);
        String message = createRankMessage(rank);
        System.out.println(message + " - " + count + "개");
    }

    private String createRankMessage(LottoRank rank) {
        int matchCount = rank.getMatchCount();
        String prize = DECIMAL_FORMAT.format(rank.getPrize());
        
        if (rank == LottoRank.SECOND) {
            return matchCount + "개 일치, 보너스 볼 일치 (" + prize + "원)";
        }
        return matchCount + "개 일치 (" + prize + "원)";
    }

    public void printProfitRate(double profitRate) {
        System.out.println("총 수익률은 " + String.format("%.1f", profitRate) + "%입니다.");
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
