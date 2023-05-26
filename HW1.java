import java.util.*;

public class HW1 {
    public static void main(String[] args) {
        Set<Notebook> notebooks = createNotebooks();

        try (Scanner scanner = new Scanner(System.in)) {
            boolean exit = false;
            Map<String, List<Object>> filters = new HashMap<>();

            do {
                System.out.println("Введите цифру, соответствующую необходимому критерию:");
                System.out.println("1 - ОЗУ");
                System.out.println("2 - Объем ЖД");
                System.out.println("3 - Операционная система");
                System.out.println("4 - Цвет");
                System.out.println("0 - Выход");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Введите минимальный объем ОЗУ (в гигабайтах):");
                        int ramFilter = scanner.nextInt();
                        addFilter(filters, "ram", ramFilter);
                        break;
                    case 2:
                        System.out.println("Введите минимальный объем ЖД (в гигабайтах):");
                        int storageFilter = scanner.nextInt();
                        addFilter(filters, "storage", storageFilter);
                        break;
                    case 3:
                        System.out.println("Введите требуемую операционную систему:");
                        String osFilter = scanner.nextLine();
                        addFilter(filters, "operatingSystem", osFilter);
                        break;
                    case 4:
                        System.out.println("Введите требуемый цвет ноутбука:");
                        String colorFilter = scanner.nextLine();
                        addFilter(filters, "color", colorFilter);
                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("Неверный выбор.");
                        break;
                }

                if (!exit && !filters.isEmpty()) {
                    Set<Notebook> filteredNotebooks = filterNotebooks(notebooks, filters);
                    if (filteredNotebooks.isEmpty()) {
                        System.out.println("Ноутбуки, удовлетворяющие заданным критериям, не найдены.");
                    } else {
                        System.out.println("Результаты фильтрации:");
                        for (Notebook notebook : filteredNotebooks) {
                            notebook.displayInfo();
                            System.out.println("--------------------");
                        }
                    }
                    filters.clear();
                }
            } while (!exit);
        }
    }

    private static void addFilter(Map<String, List<Object>> filters, String key, Object value) {
        filters.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }

    public static Set<Notebook> createNotebooks() {
        Set<Notebook> notebooks = new HashSet<>();

        notebooks.add(new Notebook("Model 1", 8, 256, "Windows", "Silver"));
        notebooks.add(new Notebook("Model 2", 16, 512, "MacOS", "Gray"));
        notebooks.add(new Notebook("Model 3", 8, 512, "Windows", "Black"));
        notebooks.add(new Notebook("Model 4", 12, 512, "Windows", "Silver"));
        notebooks.add(new Notebook("Model 5", 16, 1024, "Linux", "Blue"));
        notebooks.add(new Notebook("Model 6", 8, 512, "MacOS", "Silver"));
        notebooks.add(new Notebook("Model 7", 16, 512, "Windows", "Gray"));
        notebooks.add(new Notebook("Model 8", 8, 256, "Windows", "Black"));
        notebooks.add(new Notebook("Model 9", 12, 512, "Linux", "Silver"));
        notebooks.add(new Notebook("Model 10", 16, 1024, "MacOS", "Blue"));

        return notebooks;
    }

    public static Set<Notebook> filterNotebooks(Set<Notebook> notebooks, Map<String, List<Object>> filters) {
        Set<Notebook> filteredNotebooks = new HashSet<>();

        for (Notebook notebook : notebooks) {
            boolean passedFilter = true;

            for (Map.Entry<String, List<Object>> entry : filters.entrySet()) {
                String filterKey = entry.getKey();
                List<Object> filterValues = entry.getValue();
                boolean filterMatch = false;

                for (Object filterValue : filterValues) {
                    filterMatch = matchFilter(notebook, filterKey, filterValue);
                    if (filterMatch) {
                        break;
                    }
                }

                if (!filterMatch) {
                    passedFilter = false;
                    break;
                }
            }

            if (passedFilter) {
                filteredNotebooks.add(notebook);
            }
        }

        return filteredNotebooks;
    }

    private static boolean matchFilter(Notebook notebook, String filterKey, Object filterValue) {
        switch (filterKey) {
            case "ram":
                return notebook.getRam() >= (int) filterValue;
            case "storage":
                return notebook.getStorage() >= (int) filterValue;
            case "operatingSystem":
                return notebook.getOperatingSystem().equals(filterValue);
            case "color":
                return notebook.getColor().equals(filterValue);
            default:
                return false;
        }
    }
}
