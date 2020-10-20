package com.example.builderexample;

public class User {
    private String name;
    private String surname;
    private String lastName;
    private String address;
    private String phone;
    private int age;

    private User(String name, String surname, String lastName, String address, String phone, int age) {
        this.name = name;
        this.surname = surname;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    static class Builder {
        private String name;
        private String surname;
        private String lastName;
        private String address;
        private String phone;
        private int age;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public User build() {
            return new User(name, surname, lastName, address, phone, age);
        }
    }
}
