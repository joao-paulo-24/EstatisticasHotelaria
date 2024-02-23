package tp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.BeforeEach;

class AppTest {
    private static String file = "../Dataset_versao_testes.csv";
    GestaoClientes gestao = new GestaoClientes();
    boolean a = gestao.lerDados(file);

    @BeforeEach
    void setUp() {
        gestao.estruturarDados(file);
    }

    @Test
    void isReadingcorrectly() {

        assertTrue(gestao.lerDados(file), "A função deve retornar true");
    }

    @Test
    void firstPosIsWellStructed() {

        assertTrue(gestao.getClientes()[0].getID() == 1, "A primeira posição é completada de forma prevista");
    }

    @Test
    void secondPosIsWellStructed() {

        assertTrue(gestao.getClientes()[1].getID() == 2, "A segunda posição é completada de forma prevista");
    }

    @Test
    void LastPosIsWellStructed() {

        assertTrue(gestao.getClientes()[gestao.getCount_total() - 1].getID() == gestao.getCount_total(),
                "A última posição é completada de forma prevista");
    }

    @Test
    void PenultimatePosIsWellStructured() {

        assertTrue(gestao.getClientes()[gestao.getCount_total() - 2].getID() == gestao.getCount_total() - 1,
                "A penúltima posição é completada de forma prevista");
    }

    @Test
    void FirstPosIsWellOrganizedByLastPurchaseDate() {
        boolean var = false;
        gestao.organizeByMostDaysSinceLastStay();
        int i = 0;

        while (var == false && i < 19) {
            if (gestao.getClientes()[i].getDaysSinceLastStay() < gestao.getClientes()[i + 1].getDaysSinceLastStay()) {
                var = true;
            }
            i++;
        }
        assertTrue(var, "O inicio da ordenação está bem organizada");
    }

    @Test
    void LastPosIsWellOrganizedByLastPurchaseDate() {
        boolean var = false;
        gestao.organizeByMostDaysSinceLastStay();
        int i = gestao.getCount() - 1;

        while (var == false && i >= 0) {
            if (gestao.getClientes()[i].getDaysSinceLastStay() > gestao.getClientes()[i - 1].getDaysSinceLastStay()) {
                var = true;
            }
            i--;
        }
        assertTrue(var, "O fim da ordenação está bem organizada");
    }

    @Test
    void FirstPosIsWellOrganizedByTotalPurchases() {
        boolean var = false;
        gestao.organizeByMostShoppings();
        int i = 0;

        while (var == false && i < 19) {
            if (gestao.getClientes()[i].getTotalCompras() < gestao.getClientes()[i + 1].getTotalCompras()) {
                var = true;
            }
            i++;
        }
        assertTrue(var, "O início da ordenação está bem organizada");
    }

    @Test
    void LastPosIsWellOrganizedByTotalPurchases() {
        boolean var = false;
        gestao.organizeByMostShoppings();
        int i = gestao.getCount() - 1;

        while (var == false && i >= 0) {
            if (gestao.getClientes()[i].getTotalCompras() > gestao.getClientes()[i - 1].getTotalCompras()) {
                var = true;
            }
            i--;
        }
        assertTrue(var, "O fim da ordenação está bem organizada");
    }

    @Test
    void FirstPosIsWellOrganizedByTotalRevenue() {
        boolean var = false;
        gestao.organizeByMostMoneySpent();
        int i = 0;

        while (var == false && i < 19) {
            if (gestao.getClientes()[i].getTotalRevenue() < gestao.getClientes()[i + 1].getTotalRevenue()) {
                var = true;
            }
            i++;
        }
        assertTrue(var, "O inicio da ordenação está bem organizada");
    }

    @Test
    void LastPosIsWellOrganizedByTotalRevenue() {
        boolean var = false;
        gestao.organizeByMostMoneySpent();
        int i = gestao.getCount() - 1;

        while (var == false && i >= 0) {
            if (gestao.getClientes()[i].getTotalRevenue() > gestao.getClientes()[i - 1].getTotalRevenue()) {
                var = true;
            }
            i--;
        }
        assertTrue(var, "O fim da ordenação está bem organizada");
    }

    @Test
    void FirstPosIsWellScoredbyDays() {
        boolean var = false;
        gestao.organizeByMostDaysSinceLastStay();
        gestao.DaysSinceLastPurchaseScore();
        int i = 0;

        while (var == false && i < 19) {
            if (gestao.getClientes()[i].getScore_days() < gestao.getClientes()[i + 1].getScore_days()) {
                var = true;
            }
            i++;
        }
        assertTrue(var, "Os scores das primeiras posições estão bem atribuídos");
    }

    @Test
    void LastPosIsWellScoredbyDays() {
        boolean var = false;
        gestao.organizeByMostDaysSinceLastStay();
        gestao.DaysSinceLastPurchaseScore();
        int i = gestao.getCount() - 1;

        while (var == false && i >= 0) {
            if (gestao.getClientes()[i].getScore_days() > gestao.getClientes()[i - 1].getScore_days()) {
                var = true;
            }
            i--;
        }
        assertTrue(var, "Os scores das ultimas posições estão bem atribuídos");
    }

    @Test
    void FirstPosIsWellScoredbyTotalRevenue() {
        boolean var = false;
        gestao.organizeByMostMoneySpent();
        gestao.MoneyScore();
        int i = 0;

        while (var == false && i < 19) {
            if (gestao.getClientes()[i].getScore_value() < gestao.getClientes()[i + 1].getScore_value()) {
                var = true;
            }
            i++;
        }
        assertTrue(var, "Os scores das primeiras posições estão bem atribuídos");
    }

    @Test
    void LastPosIsWellScoredbyTotalRevenue() {
        boolean var = false;
        gestao.organizeByMostMoneySpent();
        gestao.MoneyScore();
        int i = gestao.getCount() - 1;

        while (var == false && i >= 0) {
            if (gestao.getClientes()[i].getScore_value() > gestao.getClientes()[i - 1].getScore_value()) {
                var = true;
            }
            i--;
        }
        assertTrue(var, "Os scores das ultimas posições estão bem atribuídos");
    }

    @Test
    void FirstPosIsWellScoredbyTotalPurchases() {
        boolean var = false;
        gestao.organizeByMostShoppings();
        gestao.TotalPurchasesScore();
        int i = 0;

        while (var == false && i < 19) {
            if (gestao.getClientes()[i].getScore_shoppings() < gestao.getClientes()[i + 1].getScore_shoppings()) {
                var = true;
            }
            i++;
        }
        assertTrue(var, "Os scores das primeiras posições estão bem atribuídos");
    }

    @Test
    void LastPosIsWellScoredbyTotalPurchases() {
        boolean var = false;
        gestao.organizeByMostShoppings();
        gestao.TotalPurchasesScore();
        int i = gestao.getCount() - 1;

        while (var == false && i >= 0) {
            if (gestao.getClientes()[i].getScore_shoppings() > gestao.getClientes()[i - 1].getScore_shoppings()) {
                var = true;
            }
            i--;
        }
        assertTrue(var, "Os scores das ultimas posições estão bem atribuídos");
    }

    @Test
    void verifyDistributionChannel() {
        String a = new String(gestao.MostCommonDistributionChannel());
        assertEquals(a, "Travel Agent/Operator", "O canal de distribuição está corretamente avaliado.");
    }

    @Test
    void verifyMinTotalRevenue() {
        gestao.organizeByMostMoneySpent();
        boolean var = false;

        if (gestao.MinTotalRevenue() == this.gestao.getClientes()[0].getTotalRevenue()) {
            var = true;
        }

        assertTrue(var, "O total de dinheiro gasto está organizado corretamente.");
    }

    @Test
    void verifyMaxTotalRevenue() {
        gestao.organizeByMostMoneySpent();
        boolean var = false;

        if (gestao.MaxTotalRevenue() == this.gestao.getClientes()[19].getTotalRevenue()) {
            var = true;
        }

        assertTrue(var, "O total de dinheiro gasto está organizado corretamente.");
    }

    @Test
    void verifyMeanTotalRevenue() {
        boolean var = false;

        if (this.gestao.MeanTotalRevenue() == 207.0) {
            var = true;
        }

        assertTrue(var, "A média do total de dinheiro gasto está corretamente calculada.");
    }

    @Test
    void verifyFavorableSeasonOfTheYear() {
        String a = new String(gestao.MostFavorableSeasonOfTheYear());
        assertEquals(a, "Primavera", "A estação do ano está bem avaliada.");
    }

    @Test
    void noValidClients() {
        GestaoClientes nova = new GestaoClientes();
        nova.lerDados(".\\emptyFile.csv");
        assertEquals(0, nova.getCount_total(), "Ficheiro vazio!");
    }

    @Test
    void verifyMinDaysBetweenPurchases() {
        boolean var = false;
        int a = gestao.MaxDaysBetweenPurchases();

        if (a == (this.gestao.getClientes()[0].getDaysSinceFirstStay()
                - this.gestao.getClientes()[0].getDaysSinceLastStay())) {
            var = true;
        }

        assertTrue(var, "O mínimo de dias desde a última compra estão bem avaliados.");
    }

    @Test
    void verifyMaxDaysBetweenPurchases() {
        boolean var = false;
        int a = gestao.MinDaysBetweenPurchases();

        if (a == (this.gestao.getClientes()[19].getDaysSinceFirstStay()
                - this.gestao.getClientes()[19].getDaysSinceLastStay())) {
            var = true;
        }

        assertTrue(var, "O máximo de dias desde a última compra estão bem avaliados.");
    }

    @Test
    void verifyMeanDaysBetweenPurchases() {
        boolean var = false;
        Double a = this.gestao.meanDaysBetweenPurchases();

        if (a == 46.15) {
            var = true;
        }

        assertTrue(var, "A média do número de dias desde a última compra estão bem avaliados.");
    }

    @Test
    void verifyMostCommonPayment() {
        boolean var = false;
        String a = this.gestao.MostCommonPaymentMethod();

        if (a == "Numerário") {
            var = true;
        }
        assertTrue(var, "O pagamento utilizado está corretamente atribuído.");

    }

    @Test
    void isExporting() {
        JSONParser jsonParser = new JSONParser();
        String file_name = ".\\estatisticas_testes.json";
        try {
            gestao.export(file_name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileReader ficheiro = new FileReader(".\\estatisticas_testes.json")) {
            JSONObject obj = (JSONObject) jsonParser.parse(ficheiro);
            JSONArray jsonStatistics = (JSONArray) obj.get("array de estatisticas");

            assertEquals(jsonStatistics.size(), gestao.getCount(), "O ficheiro deve ler o número de clientes");
            
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void verifyFileExistance() {
        File ficheiro = new File("../estatisticas.json");

        assertTrue(ficheiro.exists(), "O ficheiro existe!");
    }

    @Test
    void CorrectMeanScore() {
        Cliente c = new Cliente(2, 3, 4);
        assertEquals(c.getMean_score(), (c.getScore_shoppings() + c.getScore_days() + c.getScore_value()) / 3,
                "O score médio deve estar correto.");
    }

    @Test
    void CorrectSeason() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String a = "21/01/2018";
        Cliente c;
        try {
            c = new Cliente(formatter.parse(a));
            assertEquals("Inverno", c.getSeason());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}