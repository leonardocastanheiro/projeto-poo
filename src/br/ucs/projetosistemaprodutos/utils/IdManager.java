package br.ucs.projetosistemaprodutos.utils;

import java.io.File;
import java.io.PrintWriter;
import java.io.Serial;
import java.io.Serializable;
import java.util.Scanner;

public class IdManager implements Serializable {

    @Serial
    private static final long serialVersionUID = 1;

    private final String filePath;

    public IdManager(String string) {
        filePath = "last-id-"+string+".txt";
    }

    public int loadLastId() {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            return scanner.nextInt();
        } catch (Exception e) {
            return 100;
        }
    }

    public void saveLastId(int lastId) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(lastId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

