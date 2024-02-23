package tp;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        /*String file = ".\\Dataset_nova_versao.csv";
        GestaoClientes gc = new GestaoClientes();
        gc.lerDados(file);

        Cliente[] clientes = null;
        clientes = gc.estruturarDados(file);
        gc.DaysSinceLastPurchaseScore();
        gc.MoneyScore();
        gc.TotalPurchasesScore();

        try {
            gc.export();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Menu menu = new Menu();
        try {
            menu.mainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
