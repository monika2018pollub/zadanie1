import java.sql.*;
import java.util.Scanner;

class ClassSQL {
    public static void main(String args[]) {

        Statement statement;
        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:mysql://10.0.10.3:3306/baza", "MBolba", "password"
            );

            String createTable = "CREATE TABLE if not exists uzytkownik (imie VARCHAR(30), nazwisko VARCHAR(30), opis VARCHAR(30));";

            statement = connection.createStatement();

            statement.executeUpdate(createTable);

            Scanner in = new Scanner(System.in);
            while(true) {
                System.out.println("'d' dodaj uzytkownika, 'w' wyswietl wszystkich uzytkownikow");
		String wybor = "w";
		if(in.hasNextLine())
                	wybor = in.nextLine();
                if ("w".equals(wybor)) {
                    statement = connection.createStatement();
                    String zapytanie = "select * from uzytkownik;";
                    ResultSet rs = statement.executeQuery(zapytanie);
                    System.out.println("Uzytkownicy:");
                    while (rs.next()) {
                        String imie = rs.getString("imie");
                        String nazwisko = rs.getString("nazwisko");
                        String opis = rs.getString("opis");

                        System.out.print(imie);
                        System.out.print("\t");
                        System.out.print(nazwisko);
                        System.out.print("\t");
                        System.out.println(opis);
                    }
                }
                if ("d".equals(wybor)) {
                    System.out.print("Imie: ");
                    String druzynaA = in.nextLine();
                    System.out.print("Nazwisko: ");
                    String druzynaB = in.nextLine();
                    System.out.print("Opis: ");
                    String zwyciezca = in.nextLine();

                    statement = connection.createStatement();

                    String zapytanie = "INSERT INTO mecze VALUES('"+imie+"', '"+nazwisko+"', '"+opis+"');";

                    statement.executeUpdate(zapytanie);
                }
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
