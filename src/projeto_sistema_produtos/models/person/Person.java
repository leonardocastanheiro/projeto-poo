package projeto_sistema_produtos.models.person;

import projeto_sistema_produtos.models.address.Address;

public abstract class Person {
    private Role role;
    private String name;
    private String phone;
    private String email;
    private Address address;
    private Integer id;

    private static Integer lastId = 1;
    
    public Person() {
    }
    public Person(Role role, String name, String phone, String email, Address address) {
        this.id = lastId++;
        this.role = role;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getRoleString() {
    	return role.toString();
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

}
