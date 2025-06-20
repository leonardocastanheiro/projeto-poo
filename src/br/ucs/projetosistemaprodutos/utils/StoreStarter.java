package br.ucs.projetosistemaprodutos.utils;

import br.ucs.projetosistemaprodutos.collections.DynamicSupplierArray;
import br.ucs.projetosistemaprodutos.controllers.AdminController;
import br.ucs.projetosistemaprodutos.controllers.ClientController;
import br.ucs.projetosistemaprodutos.controllers.ProductController;
import br.ucs.projetosistemaprodutos.controllers.SupplierController;
import br.ucs.projetosistemaprodutos.models.address.Address;
import br.ucs.projetosistemaprodutos.models.itens.Product;
import br.ucs.projetosistemaprodutos.models.itens.Stock;
import br.ucs.projetosistemaprodutos.models.itens.Store;
import br.ucs.projetosistemaprodutos.models.person.Admin;
import br.ucs.projetosistemaprodutos.models.person.Client;
import br.ucs.projetosistemaprodutos.models.person.Role;
import br.ucs.projetosistemaprodutos.models.person.Supplier;

public class StoreStarter {

    private static void setSupplierArray(Store store) {
        SupplierController supplierController = new SupplierController(store);

        Supplier[] suppliers = new Supplier[] {
                new Supplier(Role.SUPPLIER, "Paralela", "54996853348", "livrosparalela@contato.com", "Livros e produtos de papelaria",
                        new Address("Rua das Laranjeiras", "92", "Bloco 2", "Bairro Limoeiro", "95010-260", "Caxias do Sul", "RS")),
                new Supplier(Role.SUPPLIER, "Estrela", "54995856646", "brinquedosestrela@contato.com", "Brinquedos",
                        new Address("Rua das Mimosas", "89", "", "Bairro Moreira", "98651-880", "Porto Alegre", "RS"))
        };

        try {
            for(Supplier supplier : suppliers) {
                supplierController.create(supplier);
            }
        } catch (Exception e) {

        }
    }

    private static void setProductArray(Store store) {
        ProductController productController = new ProductController(store);
        DynamicSupplierArray supplierArray = store.getSupplierArray();
        Product[] products;

        try {
            products = new Product[] {
                    new Product("Lápis de cor", "Conjunto 40 lápis", new Stock(10, 49.9), supplierArray.getByIndex(0), "100"),
                    new Product("Boneca", "Boneca Baby Alive", new Stock(15, 120.9), supplierArray.getByIndex(1), "3042"),
                    new Product("Livro", "Livro infanto-juvenil", new Stock(15, 49.9), supplierArray.getByIndex(0), "423"),
                    new Product("Carrinho", "Hot Wheels", new Stock(100, 20.5), supplierArray.getByIndex(1), "3151"),
            };

        }catch(Exception e) {
            products = new Product[] {
                    new Product("Lápis de cor", "Conjunto 40 lápis"),
                    new Product("Boneca", "Boneca Baby Alive"),
                    new Product("Livro", "Livro infanto-juvenil"),
                    new Product("Carrinho", "Hot Wheels"),
            };
        }

        try {
            for(Product product : products) {
                productController.create(product);
            }
        } catch (Exception e) {

        }
    }

    private static void setUserArray(Store store) {
        AdminController adminController = new AdminController(store);
        ClientController clientController = new ClientController(store);

        Client[] clientUsers = new Client[] {
                new Client("joaosilva", "senha123", Role.CLIENT, "João Silva", "999999999", "joao@gmail.com", "12345",
                        new Address("Rua das Amoras", "10", "Apto 10", "Bairro Frutas", "12345-999", "Curitiba", "Paraná")),
                new Client("mariaSousa", "senha123", Role.CLIENT, "Maria Sousa", "777777777", "masousa@gmail.com", "8569556",
                        new Address("Rua das Bananas", "225", "", "Bairro Frutas", "78502-145", "Florianópolis", "Santa Catarina")),
                new Client("andressaCamargo", "senha123", Role.CLIENT, "Andressa Camargo", "865974485", "andressacamargo@gmail.com", "111111",
                        new Address("Rua das Flores", "880", "Apto 105", "Bairro Petrópolis", "95025-690", "Curitiba", "Paraná")),
        };

        Admin[] adminUsers = new Admin[] {
                new Admin("melissa", "123senha", Role.ADMIN, "Melissa", "556669987", "melissa@gmail.com",
                        new Address("Avenida Brasil", "120", "", "Bairro América", "58963-002", "Porto Alegre", "RS")),
                new Admin("carlos", "123senha", Role.ADMIN, "Calros", "666666666", "carlos@gmail.com",
                        new Address("Rua Canário", "1000", "", "Bairro Pássaro", "87510-260", "Caxias do Sul", "RS")),
                new Admin("admin","admin",Role.ADMIN,"admin","admin","admin", new Address("admin","admin","admin","admin","admin","admin","admin"))
        };
        try {
            for(Client user : clientUsers) {
                clientController.create(user);
            }
            for(Admin user : adminUsers) {
                adminController.create(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Store read() {
        Store store = new Store("Amazon");

        StoreStarter.setSupplierArray(store);
        StoreStarter.setProductArray(store);
        StoreStarter.setUserArray(store);

        return store;
    }

}
