package tp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    private int opcao;
    private String file;
    private String file2;

    public Menu() {
        this.opcao = 0;
        this.file = "../../Dataset_nova_versao.csv";
        this.file2 = ".\\estatisticas.json";
    }

    public void mainMenu() throws IOException {
        GestaoClientes gc = new GestaoClientes();

        do {
            System.out.println("1. Ler dados");
            System.out.println("\n2. Estruturar dados");
            System.out.println("\n3. Organizar por dias desde a última compra");
            System.out.println("\n4. Organizar por dinheiro gasto");
            System.out.println("\n5. Organizar por total de compras");
            System.out.println("\n6. Atribuir score por compras desde o ultimo dia");
            System.out.println("\n7. Atribuir score por dinheiro gasto");
            System.out.println("\n8. Atribuir score por total de compras");
            System.out.println("\n9. Exportar ficheiro dos dados dos clientes e estatisticas gerais");
            System.out.println("\n0. Sair");
            do {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                this.opcao = Integer.parseInt(br.readLine());
                if (this.opcao < 0 || this.opcao > 9) {
                    System.out.println("\n!! Escolha uma opcao valida !!\n");
                }
            } while (this.opcao < 0 && this.opcao > 9);

            switch (this.opcao) {
                case 1:
                    gc.lerDados(file);
                    System.out.println("- Dados lidos com sucesso -\n");
                    break;
                case 2:
                    gc.setClientes(gc.estruturarDados(file));
                    System.out.println("- Dados estruturados com sucesso -\n");
                    break;
                case 3:
                    gc.organizeByMostDaysSinceLastStay();
                    System.out.println("- Dados organizados com sucesso -\n");
                    break;
                case 4:
                    gc.organizeByMostMoneySpent();
                    System.out.println("- Dados organizados com sucesso -\n");
                    break;
                case 5:
                    gc.organizeByMostShoppings();
                    System.out.println("- Dados organizados com sucesso -\n");
                    break;
                case 6:
                    gc.DaysSinceLastPurchaseScore();
                    System.out.println("- Scores atribuídos com sucesso -\n");
                    break;
                case 7:
                    gc.MoneyScore();
                    System.out.println("- Scores atribuídos com sucesso -\n");
                    break;
                case 8:
                    gc.TotalPurchasesScore();
                    System.out.println("- Scores atribuídos com sucesso -\n");
                    break;
                case 9:
                    gc.export(file2);
                    System.out.println("\n- Ficheiro exportado com sucesso -");
            }
        } while (opcao != 0);
    }

}