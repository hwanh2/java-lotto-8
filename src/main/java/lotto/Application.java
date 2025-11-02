package lotto;

import lotto.controller.LottoController;
import lotto.generator.RandomLottoGenerator;
import lotto.service.LottoShop;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        RandomLottoGenerator generator = new RandomLottoGenerator();
        LottoShop lottoShop = new LottoShop(generator);
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        LottoController controller = new LottoController(lottoShop, inputView, outputView);
        
        controller.run();
    }
}
