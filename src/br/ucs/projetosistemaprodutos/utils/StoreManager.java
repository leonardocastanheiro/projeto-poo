package br.ucs.projetosistemaprodutos.utils;

import br.ucs.projetosistemaprodutos.models.itens.Store;

import java.io.*;

public class StoreManager {
    public void save(Store store) throws Exception {
        try (FileOutputStream fileOutputStream = new FileOutputStream("projeto-poo-master/.idea/store.dat")){
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(store);
        } catch (IOException e) {
            throw new Exception("Erro ao salvar o objeto.");
        }
    }

    public Store read() throws Exception {
        try (FileInputStream fileInputStream = new FileInputStream("projeto-poo-master/.idea/store.dat")){
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            return (Store) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new Exception("Caminho não encontrado.");
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
