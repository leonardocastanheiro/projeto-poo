package projeto_sistema_produtos.classes.person;

import projeto_sistema_produtos.classes.address.Address;

public abstract class Person {
    private Role role;
    private String name;
    private String phone;
    private String email;
    private Address address;


    public Person(Role role, String name, String phone, String email, Address address) {
        this.role = role;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
