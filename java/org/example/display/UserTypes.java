package org.example.display;

public enum UserTypes {
    READER(1),
    LIBRARIAN(2),
    MANAGER(3);

    private final int id;

    UserTypes(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static UserTypes fromId(int id) {
        for (UserTypes userType : UserTypes.values()) {
            if (userType.getId() == id) {
                return userType;
            }
        }
        throw new IllegalArgumentException("ID inválido: " + id);
    }

    public static int[] getValidRange() {
        int[] values = new int[2];

        values[0] = Integer.MAX_VALUE;
        values[1] = Integer.MIN_VALUE;

        int id;
        for (UserTypes userType : UserTypes.values()) {
            id = userType.getId();
            if (values[0] > id) values[0] = id;
            if (values[1] < id) values[1] = id;
        }

        return values;
    }
}