class Notebook {
    private String model;
    private int ram;
    private int storage;
    private String operatingSystem;
    private String color;

    public Notebook(String model, int ram, int storage, String operatingSystem, String color) {
        this.model = model;
        this.ram = ram;
        this.storage = storage;
        this.operatingSystem = operatingSystem;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getColor() {
        return color;
    }

    public void displayInfo() {
        System.out.println("Модель: " + model);
        System.out.println("ОЗУ: " + ram + " ГБ");
        System.out.println("Объем ЖД: " + storage + " ГБ");
        System.out.println("Операционная система: " + operatingSystem);
        System.out.println("Цвет: " + color);
    }
}