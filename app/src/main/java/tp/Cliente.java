package tp;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Cliente {
    private int ID;
    private String Nationality;
    private String Age;
    private int DaysSinceCreation;
    private String NameHash;
    private String DocIDHash;
    private int AverageLeadTime;
    private Double LodgingRevenue;
    private Double OtherRevenue;
    private int BookingsCanceled;
    private int BookingsNoShowed;
    private int BookingsCheckedIn;
    private int PersonsNights;
    private int RoomNights;
    private int DaysSinceLastStay;
    private int DaysSinceFirstStay;
    private String DistributionChannel;
    private String MarketSegment;
    private int SRHighFloor;
    private int SRLowFloor;
    private int SRAccessibleRoom;
    private int SRMediumFloor;
    private int SRBathtub;
    private int SRShower;
    private int SRCrib;
    private int SRKingSizeBed;
    private int SRTwinBed;
    private int SRNearElevator;
    private int SRAwayFromElevator;
    private int SRNoAlcoholInMiniBar;
    private int SRQuietRoom;
    private Date PurchaseDate;
    private int PaymentMethod;
    private int score_shoppings;
    private int score_days;
    private int score_value;

    public Cliente(int iD, String nationality, String age, int daysSinceCreation, String nameHash, String docIDHash,
            int averageLeadTime, Double lodgingRevenue, Double otherRevenue, int bookingsCanceled, int bookingsNoShowed,
            int bookingsCheckedIn, int personsNights, int roomNights, int daysSinceLastStay, int daysSinceFirstStay,
            String distributionChannel, String marketSegment, int sRHighFloor, int sRLowFloor, int sRAccessibleRoom,
            int sRMediumFloor, int sRBathtub, int sRShower, int sRCrib, int sRKingSizeBed, int sRTwinBed,
            int sRNearElevator, int sRAwayFromElevator, int sRNoAlcoholInMiniBar, int sRQuietRoom, Date purchaseDate,
            int paymentMethod) {
        ID = iD;
        Nationality = nationality;
        Age = age;
        DaysSinceCreation = daysSinceCreation;
        NameHash = nameHash;
        DocIDHash = docIDHash;
        AverageLeadTime = averageLeadTime;
        LodgingRevenue = lodgingRevenue;
        OtherRevenue = otherRevenue;
        BookingsCanceled = bookingsCanceled;
        BookingsNoShowed = bookingsNoShowed;
        BookingsCheckedIn = bookingsCheckedIn;
        PersonsNights = personsNights;
        RoomNights = roomNights;
        DaysSinceLastStay = daysSinceLastStay;
        DaysSinceFirstStay = daysSinceFirstStay;
        DistributionChannel = distributionChannel;
        MarketSegment = marketSegment;
        SRHighFloor = sRHighFloor;
        SRLowFloor = sRLowFloor;
        SRAccessibleRoom = sRAccessibleRoom;
        SRMediumFloor = sRMediumFloor;
        SRBathtub = sRBathtub;
        SRShower = sRShower;
        SRCrib = sRCrib;
        SRKingSizeBed = sRKingSizeBed;
        SRTwinBed = sRTwinBed;
        SRNearElevator = sRNearElevator;
        SRAwayFromElevator = sRAwayFromElevator;
        SRNoAlcoholInMiniBar = sRNoAlcoholInMiniBar;
        SRQuietRoom = sRQuietRoom;
        PurchaseDate = purchaseDate;
        PaymentMethod = paymentMethod;
        score_shoppings = 0;
        score_days = 0;
        score_value = 0;
    }
    
    
    /**
     * Método que serve como auxiliar para os testes unitários
     * @param iD id do cliente
     */
    public Cliente(int iD) {
        ID = iD;
    }
    

    /**
     * Método que serve como auxiliar para os testes unitários
     * @param score_shoppings score do total de compras
     * @param score_days score do número de dias desde a última compra
     * @param score_value score do dinheiro total gasto em compras
     */
    public Cliente(int score_shoppings, int score_days, int score_value) {
        this.score_shoppings = score_shoppings;
        this.score_days = score_days;
        this.score_value = score_value;
    }

    /**
     * Método que serve como auxiliar para os testes unitários
     * @param pd data da compra
     */
    public Cliente(Date pd) {
        PurchaseDate = pd;
    }

    /**
     * Função que retorna o id do cliente
     * 
     * @return retorna um inteiro
     */
    public int getID() {
        return ID;
    }

    /**
     * função que modifica o valor da variável id
     * 
     * @param ID id do cliente
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String Nationality) {
        this.Nationality = Nationality;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String Age) {
        this.Age = Age;
    }

    public int getDaysSinceCreation() {
        return DaysSinceCreation;
    }

    public void setDaysSinceCreation(int DaysSinceCreation) {
        this.DaysSinceCreation = DaysSinceCreation;
    }

    public String getNameHash() {
        return NameHash;
    }

    public void setNameHash(String NameHash) {
        this.NameHash = NameHash;
    }

    public String getDocIDHash() {
        return DocIDHash;
    }

    public void setDocIDHash(String DocIDHash) {
        this.DocIDHash = DocIDHash;
    }

    public int getAverageLeadTime() {
        return AverageLeadTime;
    }

    public void setAverageLeadTime(int AverageLeadTime) {
        this.AverageLeadTime = AverageLeadTime;
    }

    public Double getLodgingRevenue() {
        return LodgingRevenue;
    }

    public void setLodgingRevenue(Double LodgingRevenue) {
        this.LodgingRevenue = LodgingRevenue;
    }

    public Double getOtherRevenue() {
        return OtherRevenue;
    }

    public void setOtherRevenue(Double OtherRevenue) {
        this.OtherRevenue = OtherRevenue;
    }

    public int getBookingsCanceled() {
        return BookingsCanceled;
    }

    public void setBookingsCanceled(int BookingsCanceled) {
        this.BookingsCanceled = BookingsCanceled;
    }

    public int getBookingsNoShowed() {
        return BookingsNoShowed;
    }

    public void setBookingsNoShowed(int BookingsNoShowed) {
        this.BookingsNoShowed = BookingsNoShowed;
    }

    public int getBookingsCheckedIn() {
        return BookingsCheckedIn;
    }

    public void setBookingsCheckedIn(int BookingsCheckedIn) {
        this.BookingsCheckedIn = BookingsCheckedIn;
    }

    public int getPersonsNights() {
        return PersonsNights;
    }

    public void setPersonsNights(int PersonsNights) {
        this.PersonsNights = PersonsNights;
    }

    public int getRoomNights() {
        return RoomNights;
    }

    public void setRoomNights(int RoomNights) {
        this.RoomNights = RoomNights;
    }

    public int getDaysSinceLastStay() {
        return DaysSinceLastStay;
    }

    public void setDaysSinceLastStay(int DaysSinceLastStay) {
        this.DaysSinceLastStay = DaysSinceLastStay;
    }

    public int getDaysSinceFirstStay() {
        return DaysSinceFirstStay;
    }

    public void setDaysSinceFirstStay(int DaysSinceFirstStay) {
        this.DaysSinceFirstStay = DaysSinceFirstStay;
    }

    public String getDistributionChannel() {
        return DistributionChannel;
    }

    public void setDistributionChannel(String DistributionChannel) {
        this.DistributionChannel = DistributionChannel;
    }

    public String getMarketSegment() {
        return MarketSegment;
    }

    public void setMarketSegment(String MarketSegment) {
        this.MarketSegment = MarketSegment;
    }

    public int getSRHighFloor() {
        return SRHighFloor;
    }

    public void setSRHighFloor(int sRHighFloor) {
        SRHighFloor = sRHighFloor;
    }

    public int getSRLowFloor() {
        return SRLowFloor;
    }

    public void setSRLowFloor(int sRLowFloor) {
        SRLowFloor = sRLowFloor;
    }

    public int getSRAccessibleRoom() {
        return SRAccessibleRoom;
    }

    public void setSRAccessibleRoom(int sRAccessibleRoom) {
        SRAccessibleRoom = sRAccessibleRoom;
    }

    public int getSRMediumFloor() {
        return SRMediumFloor;
    }

    public void setSRMediumFloor(int sRMediumFloor) {
        SRMediumFloor = sRMediumFloor;
    }

    public int getSRBathtub() {
        return SRBathtub;
    }

    public void setSRBathtub(int sRBathtub) {
        SRBathtub = sRBathtub;
    }

    public int getSRShower() {
        return SRShower;
    }

    public void setSRShower(int sRShower) {
        SRShower = sRShower;
    }

    public int getSRCrib() {
        return SRCrib;
    }

    public void setSRCrib(int sRCrib) {
        SRCrib = sRCrib;
    }

    public int getSRKingSizeBed() {
        return SRKingSizeBed;
    }

    public void setSRKingSizeBed(int sRKingSizeBed) {
        SRKingSizeBed = sRKingSizeBed;
    }

    public int getSRTwinBed() {
        return SRTwinBed;
    }

    public void setSRTwinBed(int sRTwinBed) {
        SRTwinBed = sRTwinBed;
    }

    public int getSRNearElevator() {
        return SRNearElevator;
    }

    public void setSRNearElevator(int sRNearElevator) {
        SRNearElevator = sRNearElevator;
    }

    public int getSRAwayFromElevator() {
        return SRAwayFromElevator;
    }

    public void setSRAwayFromElevator(int sRAwayFromElevator) {
        SRAwayFromElevator = sRAwayFromElevator;
    }

    public int getSRNoAlcoholInMiniBar() {
        return SRNoAlcoholInMiniBar;
    }

    public void setSRNoAlcoholInMiniBar(int sRNoAlcoholInMiniBar) {
        SRNoAlcoholInMiniBar = sRNoAlcoholInMiniBar;
    }

    public int getSRQuietRoom() {
        return SRQuietRoom;
    }

    public void setSRQuietRoom(int sRQuietRoom) {
        SRQuietRoom = sRQuietRoom;
    }

    public Date getPurchaseDate() {
        return PurchaseDate;
    }

    public void setPurchaseDate(Date PurchaseDate) {
        this.PurchaseDate = PurchaseDate;
    }

    public int getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(int PaymentMethod) {
        this.PaymentMethod = PaymentMethod;
    }

    
    public int getScore_shoppings() {
        return score_shoppings;
    }

    public void setScore_shoppings(int score_shoppings) {
        this.score_shoppings = score_shoppings;
    }

    public int getScore_days() {
        return score_days;
    }

    public void setScore_days(int score_days) {
        this.score_days = score_days;
    }

    public int getScore_value() {
        return score_value;
    }

    public void setScore_value(int score_value) {
        this.score_value = score_value;
    }

    /**
     * Método para obter a soma de todas as compras realizadas por cliente
     * 
     * @return a soma de todas as compras realizadas por cliente
     */
    public int getTotalCompras() {
        return this.SRAccessibleRoom + this.SRAwayFromElevator + this.SRBathtub + this.SRCrib
                + this.SRHighFloor + this.SRKingSizeBed + this.SRLowFloor + this.SRMediumFloor
                + this.SRNearElevator + this.SRNoAlcoholInMiniBar + this.SRQuietRoom + this.SRShower + this.SRTwinBed;
    }

    /**
     * Método para obter o total monetário gasto em todas as compras
     * 
     * @return o total monetário gasto em todas as compras
     */
    public double getTotalRevenue() {
        return this.OtherRevenue + this.LodgingRevenue;
    }

    /**
     * Método para obter a média do score de cada cliente
     * @return a média do score
     */
    public double getMean_score() {
        return (this.score_days + this.score_shoppings + this.score_value) / 3;
    }

    /**
     * Método para obter a estação do ano que realizou a compra
     * @return a string correspondente à estação do ano
     */
    public String getSeason() {
        int a = 0, b = 0, c = 0, d = 0;

        LocalDate localDate = PurchaseDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (localDate.getDayOfYear() >= 79 && localDate.getDayOfYear() < 172) {
            a++;
        } else if (localDate.getDayOfYear() >= 172 && localDate.getDayOfYear() < 266) {
            b++;
        } else if (localDate.getDayOfYear() >= 266 && localDate.getDayOfYear() < 355) {
            c++;
        } else if (localDate.getDayOfYear() >= 355 || localDate.getDayOfYear() < 79) {
            d++;
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
    
}