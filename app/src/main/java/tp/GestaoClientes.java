package tp;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.opencsv.*;

public class GestaoClientes {
    /**
     * Array de clientes
     */
    private Cliente[] clientes;

    /**
     * Contador para o array de clientes
     */
    private int count;

    /**
     * Contador que guarda o total de clientes no ficheiro .csv
     */
    private int count_total;

    /**
     * Método construtor da classe GestaoClientes
     */
    public GestaoClientes() {
        this.clientes = new Cliente[0];
        this.count = 0;
        this.count_total = 0;
    }

    public Cliente[] getClientes() {
        return clientes;
    }

    public void setClientes(Cliente[] clientes) {
        this.clientes = clientes;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount_total() {
        return count_total;
    }

    public void setCount_total(int count_total) {
        this.count_total = count_total;
    }

    /**
     * Método responsável por ler os dados e atribuir o valor total de clientes à
     * variável count_total
     * 
     * @param file caminho que o método recebe para aceder ao ficheiro .csv dos
     *             clientes
     * @return true se a leitura for bem sucedida
     *         false se a leitura não for bem sucedida
     */
    public boolean lerDados(String file) {

        try {

            FileReader filereader = new FileReader(file);

            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            csvReader.skip(1);

            while ((nextRecord = csvReader.readNext()) != null) {
                count_total++;
            }
            this.clientes = new Cliente[count_total];

            filereader.close();
            csvReader.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Método responsável por estruturar os dados nas váriaveis correspondentes
     * 
     * @param file caminho que o método recebe para aceder ao ficheiro .csv dos
     *             clientes
     * @return o array de clientes que vai ser armazenado cada um
     *         null caso a estruturação não seja bem sucedida
     */
    public Cliente[] estruturarDados(String file) {

        try {
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build();

            csvReader.skip(1);

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String[] clientArray = new String[33];

            while ((clientArray = csvReader.readNext()) != null) {

                Cliente cliente = new Cliente(Integer.parseInt(clientArray[0]),
                        clientArray[1],
                        clientArray[2], Integer.parseInt(clientArray[3]),
                        clientArray[4],
                        clientArray[5],
                        Integer.parseInt(clientArray[6]), Double.parseDouble(clientArray[7].replaceAll(",", ".")),
                        Double.parseDouble(clientArray[8].replaceAll(",", ".")), Integer.parseInt(clientArray[9]),
                        Integer.parseInt(clientArray[10]), Integer.parseInt(clientArray[11]),
                        Integer.parseInt(clientArray[12]), Integer.parseInt(clientArray[13]),
                        Integer.parseInt(clientArray[14]),
                        Integer.parseInt(clientArray[15]), clientArray[16], clientArray[17],
                        Integer.parseInt(clientArray[18]), Integer.parseInt(clientArray[19]),
                        Integer.parseInt(clientArray[20]), Integer.parseInt(clientArray[21]),
                        Integer.parseInt(clientArray[22]), Integer.parseInt(clientArray[23]),
                        Integer.parseInt(clientArray[24]), Integer.parseInt(clientArray[25]),
                        Integer.parseInt(clientArray[26]), Integer.parseInt(clientArray[27]),
                        Integer.parseInt(clientArray[28]), Integer.parseInt(clientArray[29]),
                        Integer.parseInt(clientArray[30]), formatter.parse(clientArray[31]),
                        Integer.parseInt(clientArray[32]));
                this.clientes[count] = cliente;
                count++;
            }

            filereader.close();
            csvReader.close();
            return this.clientes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Método responsável por organizar os dados de acordo com o tempo que se passou
     * desde a última compra do cliente
     */
    public void organizeByMostDaysSinceLastStay() {
        Arrays.sort(clientes, new Comparator<Cliente>() {
            @Override
            public int compare(Cliente o1, Cliente o2) {
                Integer i = new Integer(o1.getDaysSinceLastStay());
                return i.compareTo(o2.getDaysSinceLastStay());
            }
        });
    }

    /**
     * Método responsável por organizar os dados de acordo com o número de compras
     * realizadas por cliente
     */
    public void organizeByMostShoppings() {
        Arrays.sort(clientes, new Comparator<Cliente>() {
            @Override
            public int compare(Cliente o1, Cliente o2) {
                Integer i = new Integer(o1.getTotalCompras());
                return i.compareTo(o2.getTotalCompras());
            }
        });
    }

    /**
     * Método responsável por organizar os dados de acordo com o total de dinheiro
     * gasto por cliente
     */
    public void organizeByMostMoneySpent() {
        Arrays.sort(clientes, new Comparator<Cliente>() {
            @Override
            public int compare(Cliente o1, Cliente o2) {
                if (o1.getTotalRevenue() < o2.getTotalRevenue()) {
                    return -1;
                } else if (o1.getTotalRevenue() > o2.getTotalRevenue()) {
                    return 1;
                }
                return 0;
            }
        });
    }

    /**
     * Método responsável por atribuir o score a cada cliente de acordo com os dias
     * desde a sua última compra
     */
    public void DaysSinceLastPurchaseScore() {
        organizeByMostDaysSinceLastStay();
        for (int i = 0; i < this.count; i++) {
            if (i < count / 4) {
                this.clientes[i].setScore_days(1);
            } else if (i > ((count / 4) - 1) && i < count / 2) {
                this.clientes[i].setScore_days(2);
            } else if (i > ((count / 2) - 1) && i < ((count / 2) + (count / 4))) {
                this.clientes[i].setScore_days(3);
            } else if (i > (count - (count / 4) - 2) && i < count) {
                this.clientes[i].setScore_days(4);
            }
        }
    }

    /**
     * Método responsável por atribuir o score a cada cliente de acordo com o seu
     * dinheiro gasto
     */
    public void MoneyScore() {
        organizeByMostMoneySpent();
        for (int i = 0; i < this.count; i++) {
            if (i < count / 4) {
                this.clientes[i].setScore_value(1);
            } else if (i > ((count / 4) - 1) && i < count / 2) {
                this.clientes[i].setScore_value(2);
            } else if (i > ((count / 2) - 1) && i < ((count / 2) + (count / 4))) {
                this.clientes[i].setScore_value(3);
            } else if (i > (count - (count / 4) - 2) && i < count) {
                this.clientes[i].setScore_value(4);
            }
        }
    }

    /**
     * Método responsável por atribuir o score a cada cliente de acordo com o seu
     * total de compras
     */
    public void TotalPurchasesScore() {
        organizeByMostShoppings();
        for (int i = 0; i < this.count; i++) {
            if (i < count / 4) {
                this.clientes[i].setScore_shoppings(1);
            } else if (i > ((count / 4) - 1) && i < count / 2) {
                this.clientes[i].setScore_shoppings(2);
            } else if (i > ((count / 2) - 1) && i < ((count / 2) + (count / 4))) {
                this.clientes[i].setScore_shoppings(3);
            } else if (i > (count - (count / 4) - 2) && i < count) {
                this.clientes[i].setScore_shoppings(4);
            }
        }
    }

    /**
     * Método responsável por obter o canal de distribuição predominante
     * 
     * @return uma strin com o canal de distribuição predominante
     */
    public String MostCommonDistributionChannel() {
        int corp = 0, direct = 0, electronicDis = 0, travelAgentOp = 0;
        for (int i = 0; i < this.count; i++) {
            if (this.clientes[i].getDistributionChannel().equals("Corporate")) {
                corp++;
            } else if (this.clientes[i].getDistributionChannel().equals("Direct")) {
                direct++;
            } else if (this.clientes[i].getDistributionChannel().equals("Electronic Distribution")) {
                electronicDis++;
            } else if (this.clientes[i].getDistributionChannel().equals("Travel Agent/Operator")) {
                travelAgentOp++;
            }
        }

        if (corp > direct && corp > electronicDis && corp > travelAgentOp) {
            return "Corporate";
        }
        if (direct > corp && direct > electronicDis && direct > travelAgentOp) {
            return "Direct";
        }
        if (electronicDis > corp && electronicDis > direct && electronicDis > travelAgentOp) {
            return "Electronic Distribution";
        }
        if (travelAgentOp > corp && travelAgentOp > electronicDis && travelAgentOp > direct) {
            return "Travel Agent/Operator";
        }
        return null;
    }

    /**
     * Método responsável por obter o valor mínimo das compras no total
     * 
     * @return o valor mínimo das compras no total
     */
    public double MinTotalRevenue() {
        this.organizeByMostMoneySpent();
        return this.clientes[0].getTotalRevenue();
    }

    /**
     * Método responsável por obter o valor máximo das compras no total
     * 
     * @return o valor máximo das compras no total
     */
    public double MaxTotalRevenue() {
        this.organizeByMostMoneySpent();
        return this.clientes[this.count - 1].getTotalRevenue();
    }

    /**
     * Método responsável por obter a média do valor das compras no total
     * 
     * @return a média do valor das compras no total
     */
    public double MeanTotalRevenue() {
        double mean = 0;
        for (int i = 0; i < this.count; i++) {
            mean += this.clientes[i].getTotalRevenue();
        }
        return mean / this.count;
    }

    /**
     * Método responsável por obter a estação do ano predominante nas compras
     * 
     * @return uma string referente à estação do ano predominante nas compras
     */
    public String MostFavorableSeasonOfTheYear() {
        int a = 0, b = 0, c = 0, d = 0;

        for (int i = 0; i < this.count; i++) {
            LocalDate localDate = this.clientes[i].getPurchaseDate().toInstant().atZone(ZoneId.systemDefault())
                    .toLocalDate();
            if (localDate.getDayOfYear() >= 79 && localDate.getDayOfYear() < 172) {
                a++;
            } else if (localDate.getDayOfYear() >= 172 && localDate.getDayOfYear() < 266) {
                b++;
            } else if (localDate.getDayOfYear() >= 266 && localDate.getDayOfYear() < 355) {
                c++;
            } else if (localDate.getDayOfYear() >= 355 || localDate.getDayOfYear() < 79) {
                d++;
            }
        }

        if (a > b && a > c && a > d) {
            return "Primavera";
        }
        if (b > a && b > c && b > d) {
            return "Verão";
        }
        if (c > a && c > b && c > d) {
            return "Outono";
        }
        if (d > b && d > c && d > a) {
            return "Inverno";
        }
        return null;
    }

    /**
     * Método responsável por obter o valor mínimo de tempo entre compras por
     * cliente
     * 
     * @return o valor mínimo de tempo entre compras por cliente
     */
    public int MinDaysBetweenPurchases() {
        int min = this.MaxDaysBetweenPurchases();

        for (int i = 0; i < this.count; i++) {
            if (this.clientes[i].getDaysSinceFirstStay() - this.clientes[i].getDaysSinceLastStay() < min) {
                min = this.clientes[i].getDaysSinceFirstStay() - this.clientes[i].getDaysSinceLastStay();
            }
        }

        return min;
    }

    /**
     * Método responsável por obter o valor máximo de tempo entre compras por
     * cliente
     * 
     * @return o valor máximo de tempo entre compras por cliente
     */
    public int MaxDaysBetweenPurchases() {
        int max = 0;

        for (int i = 0; i < this.count; i++) {
            if ((this.clientes[i].getDaysSinceFirstStay() - this.clientes[i].getDaysSinceLastStay()) > max) {
                max = this.clientes[i].getDaysSinceFirstStay() - this.clientes[i].getDaysSinceLastStay();
            }
        }

        return max;
    }

    /**
     * Método responsável por calcular o intervalo médio de tempo entre compras por
     * cliente
     * 
     * @return o intervalo médio de tempo entre compras por cliente
     */
    public double meanDaysBetweenPurchases() {
        double mean = 0;

        for (int i = 0; i < this.count; i++) {
            mean += this.clientes[i].getDaysSinceFirstStay() - this.clientes[i].getDaysSinceLastStay();
        }

        return mean / this.count;
    }

    /**
     * Método responsável por obter o método de pagamento predominante
     * 
     * @return uma string referente ao método de pagamento predominante
     */
    public String MostCommonPaymentMethod() {
        int a = 0, b = 0, c = 0, d = 0;
        for (int i = 0; i < this.count; i++) {
            if (this.clientes[i].getPaymentMethod() == 1) {
                a++;
            } else if (this.clientes[i].getPaymentMethod() == 2) {
                b++;
            } else if (this.clientes[i].getPaymentMethod() == 3) {
                c++;
            } else if (this.clientes[i].getPaymentMethod() == 4) {
                d++;
            }
        }

        if (a > b && a > c && a > d) {
            return "via: PaymentProviders";
        }
        if (b > a && b > c && b > d) {
            return "Numerário";
        }
        if (c > a && c > b && c > d) {
            return "Cartão de crédito";
        }
        if (d > b && d > c && d > a) {
            return "Transferência bancária";
        }
        return null;
    }

    /**
     * Método auxiliar responsável por organizar o score do tempo que se passou
     * desde a última compra do cliente
     */
    private void organizeByMostDaysSinceLastStayScore() {
        Arrays.sort(clientes, new Comparator<Cliente>() {
            @Override
            public int compare(Cliente o1, Cliente o2) {
                if (o1.getScore_days() < o2.getScore_days()) {
                    return -1;
                } else if (o1.getScore_days() > o2.getScore_days()) {
                    return 1;
                }
                return 0;
            }
        });
    }

    /**
     * Método auxiliar responsável por organizar os scores do número de compras
     * realizadas por cliente
     */
    private void organizeByMostShoppingsScore() {
        Arrays.sort(clientes, new Comparator<Cliente>() {
            @Override
            public int compare(Cliente o1, Cliente o2) {
                if (o1.getScore_shoppings() < o2.getScore_shoppings()) {
                    return -1;
                } else if (o1.getScore_shoppings() > o2.getScore_shoppings()) {
                    return 1;
                }
                return 0;
            }
        });
    }

    /**
     * Método auxiliar responsável por organizar o score do total de dinheiro
     * gasto por cliente
     */
    private void organizeByMostMoneySpentScore() {
        Arrays.sort(clientes, new Comparator<Cliente>() {
            @Override
            public int compare(Cliente o1, Cliente o2) {
                if (o1.getScore_value() < o2.getScore_value()) {
                    return -1;
                } else if (o1.getScore_value() > o2.getScore_value()) {
                    return 1;
                }
                return 0;
            }
        });
    }

    /**
     * Método responsável por organizar os dados de acordo com o total de dinheiro
     * gasto por cliente
     */
    private void organizeByMeanScore() {
        Arrays.sort(clientes, new Comparator<Cliente>() {
            @Override
            public int compare(Cliente o1, Cliente o2) {
                if (o1.getMean_score() < o2.getMean_score()) {
                    return -1;
                } else if (o1.getMean_score() > o2.getMean_score()) {
                    return 1;
                }
                return 0;
            }
        });
    }

    /**
     * Método responsável por exportar alguns dados dos clientes e estatísticas
     * gerais para um json file
     * 
     * @throws IOException se a criação do ficheiro não for válida
     */
    public void export(String file) throws IOException {
        JSONObject obj = new JSONObject();
        JSONArray arrayClientes = new JSONArray();

        for (int i = 0; i < this.count; i++) {
            JSONArray arraySpecif = new JSONArray();
            JSONObject client_statistics = new JSONObject();
            JSONObject score_compras = new JSONObject();
            JSONObject score_dias = new JSONObject();
            JSONObject score_dinheiro = new JSONObject();
            JSONObject score_media = new JSONObject();
            JSONObject season_purchase = new JSONObject();
            JSONObject payment_method = new JSONObject();

            payment_method.put("payment_method", this.clientes[i].getPaymentMethod());
            season_purchase.put("season_purchase", this.clientes[i].getSeason());
            score_compras.put("score_shoppings", this.clientes[i].getScore_shoppings());
            score_dias.put("score_days", this.clientes[i].getScore_days());
            score_dinheiro.put("score_value", this.clientes[i].getScore_value());
            score_media.put("mean_score", this.clientes[i].getMean_score());
            arraySpecif.put(score_compras);
            arraySpecif.put(score_dias);
            arraySpecif.put(score_dinheiro);
            arraySpecif.put(score_media);
            arraySpecif.put(season_purchase);

            client_statistics.put(Integer.toString(this.clientes[i].getID()), arraySpecif);
            arrayClientes.put(client_statistics);
            System.out.println("  - Cliente com id " + this.clientes[i].getID() + ": ");
            System.out.println("\n  - Altura do ano mais interessante: " + this.clientes[i].getSeason());
            System.out.println("\n  - Meio de pagamento: " + this.clientes[i].getPaymentMethod());
            System.out.println("\n  - Score por total de compras: " + this.clientes[i].getScore_shoppings());
            System.out.println("\n  - Score por dias desde a última compra: " + this.clientes[i].getScore_days());
            System.out.println("\n  - Score por total de dinheiro gasto: " + this.clientes[i].getScore_value());
            System.out.println("\n  - Score médio do cliente: " + this.clientes[i].getMean_score());
            System.out.println("\n------------------------------------------\n");
        }

        JSONArray estatisticas_gerais = new JSONArray();
        JSONObject most_favorable_season_year = new JSONObject();
        JSONObject most_commom_distribution_channel = new JSONObject();
        JSONObject purchases_minimum = new JSONObject();
        JSONObject purchases_maximum = new JSONObject();
        JSONObject purchases_mean = new JSONObject();
        JSONObject min_days_between_purchases = new JSONObject();
        JSONObject max_days_between_purchases = new JSONObject();
        JSONObject mean_days_between_purchases = new JSONObject();
        JSONObject most_commom_payment_method = new JSONObject();
        JSONObject least_days_since_last_stay_score_client_id = new JSONObject();
        JSONObject most_days_since_last_stay_score_client_id = new JSONObject();
        JSONObject least_money_spent_score_client_id = new JSONObject();
        JSONObject most_money_spent_score_client_id = new JSONObject();
        JSONObject least_shoppings_score_client_id = new JSONObject();
        JSONObject most_shoppings_score_client_id = new JSONObject();
        JSONObject least_score_mean_client_id = new JSONObject();
        JSONObject most_score_mean_client_id = new JSONObject();

        most_favorable_season_year.put("most_favorable_season_year", MostFavorableSeasonOfTheYear());
        most_commom_distribution_channel.put("most_commom_distribution_channel", MostCommonDistributionChannel());
        purchases_minimum.put("purchases_minimum", MinTotalRevenue());
        purchases_maximum.put("purchases_maximum", MaxTotalRevenue());
        purchases_mean.put("purchases_mean", MeanTotalRevenue());
        min_days_between_purchases.put("min_days_between_purchases", MinDaysBetweenPurchases());
        max_days_between_purchases.put("max_days_between_purchases", MaxDaysBetweenPurchases());
        mean_days_between_purchases.put("mean_days_between_purchases", meanDaysBetweenPurchases());
        most_commom_payment_method.put("most_commom_payment_method", MostCommonPaymentMethod());
        organizeByMostDaysSinceLastStayScore();
        least_days_since_last_stay_score_client_id.put("least_days_since_last_stay_score_client_id",
                this.clientes[0].getID());
        most_days_since_last_stay_score_client_id.put("most_days_since_last_stay_score_client_id",
                this.clientes[this.count - 1].getID());
        organizeByMostMoneySpentScore();
        least_money_spent_score_client_id.put("least_money_spent_score_client_id", this.clientes[0].getID());
        most_money_spent_score_client_id.put("most_money_spent_score_client_id", this.clientes[this.count - 1].getID());
        organizeByMostShoppingsScore();
        least_shoppings_score_client_id.put("least_shoppings_score_client_id", this.clientes[0].getID());
        most_shoppings_score_client_id.put("most_shoppings_score_client_id", this.clientes[this.count - 1].getID());
        organizeByMeanScore();
        least_score_mean_client_id.put("least_score_mean_client_id", this.clientes[0].getID());
        most_score_mean_client_id.put("most_score_mean_client_id", this.clientes[this.count - 1].getID());
        estatisticas_gerais.put(most_favorable_season_year);
        estatisticas_gerais.put(most_commom_distribution_channel);
        estatisticas_gerais.put(purchases_minimum);
        estatisticas_gerais.put(purchases_maximum);
        estatisticas_gerais.put(purchases_mean);
        estatisticas_gerais.put(min_days_between_purchases);
        estatisticas_gerais.put(max_days_between_purchases);
        estatisticas_gerais.put(mean_days_between_purchases);
        estatisticas_gerais.put(most_commom_payment_method);
        estatisticas_gerais.put(least_days_since_last_stay_score_client_id);
        estatisticas_gerais.put(most_days_since_last_stay_score_client_id);
        estatisticas_gerais.put(least_money_spent_score_client_id);
        estatisticas_gerais.put(most_money_spent_score_client_id);
        estatisticas_gerais.put(least_shoppings_score_client_id);
        estatisticas_gerais.put(most_shoppings_score_client_id);
        estatisticas_gerais.put(least_score_mean_client_id);
        estatisticas_gerais.put(most_score_mean_client_id);

        obj.put("array de estatisticas", arrayClientes);
        obj.put("estatisticas gerais", estatisticas_gerais);

        FileWriter ficheiro = new FileWriter(file);
        ficheiro.write(obj.toString());
        ficheiro.close();
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("ESTATISTICAS GERAIS:");
        System.out.println("\n  - Altura do ano mais favorável: " + MostFavorableSeasonOfTheYear());
        System.out.println("\n  - Canal de comunicação predominante: " + MostCommonDistributionChannel());
        System.out.println("\n  - Valor médio das compras: " + df.format(MeanTotalRevenue()));
        System.out.println("\n  - Valor mínimo das compras: " + df.format(MinTotalRevenue()));
        System.out.println("\n  - Valor máximo das compras: " + df.format(MaxTotalRevenue()));
        System.out.println("\n  - Intervalo médio de tempo entre compras: " + meanDaysBetweenPurchases());
        System.out.println("\n  - Intervalo mínimo de tempo entre compras: " + MinDaysBetweenPurchases());
        System.out.println("\n  - Intervalo máximo de tempo entre compras: " + MaxDaysBetweenPurchases());
        System.out.println("\n  - Meio de pagamento predominante: " + MostCommonPaymentMethod());
        organizeByMostDaysSinceLastStayScore();
        System.out.println("\n  - ID do cliente com menos score referente aos dias desde a última compra: "
                + this.clientes[0].getID());
        System.out.println("\n  - ID do cliente com mais score referente aos dias desde a última compra: "
                + this.clientes[this.count - 1].getID());
        organizeByMostMoneySpentScore();
        System.out.println("\n  - ID do cliente com menos score referente ao total de dinheiro gasto: "
                + this.clientes[0].getID());
        System.out.println("\n  - ID do cliente com mais score referente ao total de dinheiro gasto: "
                + this.clientes[this.count - 1].getID());
        organizeByMostShoppingsScore();
        System.out.println(
                "\n  - ID do cliente com menos score referente ao total de compras: " + this.clientes[0].getID());
        System.out.println("\n  - ID do cliente com mais score referente ao total de compras: "
                + this.clientes[this.count - 1].getID());
        organizeByMeanScore();
        System.out.println("\n  - ID do cliente com maior média de score: " + this.clientes[0].getID());
        System.out.println("\n  - ID do cliente com menor média de score: " + this.clientes[this.count - 1].getID());
    }
}
