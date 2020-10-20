package com.example.builderexample;

public class MyClass {

    public static void main(String[] args) {
//        User user1 = new User("Name", null, "Last name", "St", null, 30);
//        User user2 = new User("Name", "Sur", "Last name", "St", "123", 30);

        User user3 = new User.Builder()
                .setName("Name")
                .setAddress("Address")
                .setLastName("Last")
                .build();

        User.Builder builder = new User.Builder()
                .setAddress("")
                .setAge(90);

        if (args.length > 0) {
            builder.setName("");
        }

        User user4 = builder.build();
    }
}