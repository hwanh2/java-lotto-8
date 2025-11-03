package lotto.controller;

import lotto.domain.LottoRank;
import lotto.domain.Lottos;
import lotto.domain.WinningNumbers;
import lotto.parser.Parser;
import lotto.service.LottoShop;
import lotto.util.RetryHandler;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Map;

public class LottoController {
    private final LottoShop lottoShop;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(LottoShop lottoShop, InputView inputView, OutputView outputView) {
        this.lottoShop = lottoShop;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Lottos lottos = purchaseLottos();
        outputView.printPurchaseResult(lottos);

        WinningNumbers winningNumbers = getWinningNumbers();
        
        Map<LottoRank,Long> result = lottos.calculateResult(winningNumbers);
        outputView.printWinningStatistics(result);

        int purchaseAmount = lottos.size() * LottoShop.LOTTO_PRICE;
        double profitRate = lottos.calculateProfitRate(winningNumbers, purchaseAmount);
        outputView.printProfitRate(profitRate);
    }

    private Lottos purchaseLottos() {
        return RetryHandler.retry(() -> {
            String input = inputView.inputPurchaseAmount();
            int amount = Parser.parseAmount(input);
            return lottoShop.purchaseLottos(amount);
        });
    }

    private WinningNumbers getWinningNumbers() {
        return RetryHandler.retry(() -> {
            List<Integer> winningNumbersList = getWinningNumbersList();
            int bonusNumber = getBonusNumber();
            return WinningNumbers.createWithBonus(winningNumbersList, bonusNumber);
        });
    }

    private List<Integer> getWinningNumbersList() {
        return RetryHandler.retry(() -> {
            String input = inputView.inputWinningNumbers();
            return Parser.parseLottoNumbers(input);
        });
    }

    private int getBonusNumber() {
        return RetryHandler.retry(() -> {
            String input = inputView.inputBonusNumber();
            return Parser.parseBonusNumber(input);
        });
    }
}

