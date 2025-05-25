package lt.esdc.shape.app;

import lt.esdc.shape.action.impl.TetrahedronCalculatorImpl;
import lt.esdc.shape.comparator.TetrahedronByAreaComparator;
import lt.esdc.shape.comparator.TetrahedronByIdComparator;
import lt.esdc.shape.comparator.TetrahedronByPerimeterComparator;
import lt.esdc.shape.comparator.TetrahedronByVolumeComparator;
import lt.esdc.shape.entity.Point;
import lt.esdc.shape.entity.Tetrahedron;
import lt.esdc.shape.exception.FileReadException;
import lt.esdc.shape.factory.TetrahedronFactoryAbstract;
import lt.esdc.shape.reader.ShapeCoordinateReader;
import lt.esdc.shape.repository.TetrahedronRepository;
import lt.esdc.shape.specification.TetrahedronSpecification;
import lt.esdc.shape.specification.impl.AreaRangeTetrahedronSpecification;
import lt.esdc.shape.specification.impl.IdTetrahedronSpecification;
import lt.esdc.shape.specification.impl.PerimeterRangeTetrahedronSpecification;
import lt.esdc.shape.specification.impl.VolumeRangeTetrahedronSpecification;
import lt.esdc.shape.validator.TetrahedronValidatorImpl;
import lt.esdc.shape.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);
    private static final Scanner scanner = new Scanner(System.in);
    private static TetrahedronRepository repository;
    private static TetrahedronCalculatorImpl calculator;
    private static ShapeCoordinateReader reader;
    private static TetrahedronFactoryAbstract factory;
    private static Warehouse warehouse;

    public static void main(String[] args) {
        init();
        showMenu();
    }

    private static void init() {
        calculator = new TetrahedronCalculatorImpl();
        repository = TetrahedronRepository.getInstance();
        reader = new ShapeCoordinateReader();
        factory = new TetrahedronFactoryAbstract();
        warehouse = Warehouse.getInstance();
    }

    private static void showMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Tetrahedron Management System ---");
            System.out.println("1. Load tetrahedrons from file");
            System.out.println("2. Display all tetrahedrons");
            System.out.println("3. Calculate area, perimeter and volume");
            System.out.println("4. Search tetrahedrons");
            System.out.println("5. Sort tetrahedrons");
            System.out.println("6. Display warehouse information");
            System.out.println("7. Edit a tetrahedron");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();
            switch (choice) {
                case 1 -> loadFromFile();
                case 2 -> displayAllTetrahedrons();
                case 3 -> calculateShapeProperties();
                case 4 -> searchTetrahedrons();
                case 5 -> sortTetrahedrons();
                case 6 -> displayWarehouseInfo();
                case 7 -> editTetrahedron();
                case 0 -> exit = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        logger.info("Application terminated");
    }

    private static void loadFromFile() {
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        try {
            List<Tetrahedron> tetrahedrons = reader.readShapesFromFile(filePath, factory)
                    .stream()
                    .map(shape -> (Tetrahedron) shape)
                    .toList();

            for (Tetrahedron tetrahedron : tetrahedrons) {
                repository.add(tetrahedron);
            }

            System.out.println("Successfully loaded " + tetrahedrons.size() + " tetrahedrons");
            logger.info("Loaded {} tetrahedrons from file: {}", tetrahedrons.size(), filePath);
        } catch (FileReadException e) {
            System.out.println("Error reading file: " + e.getMessage());
            logger.error("Error reading file: {}", e.getMessage());
        }
    }

    private static Point readPoint() {
        String[] coordinates = scanner.nextLine().split("\\s+");
        if (coordinates.length != 3) {
            throw new NumberFormatException("Expected 3 coordinates");
        }

        double x = Double.parseDouble(coordinates[0]);
        double y = Double.parseDouble(coordinates[1]);
        double z = Double.parseDouble(coordinates[2]);

        return new Point(x, y, z);
    }

    private static void displayAllTetrahedrons() {
        List<Tetrahedron> tetrahedrons = repository.getAllTetrahedrons();

        if (tetrahedrons.isEmpty()) {
            System.out.println("No tetrahedrons in the repository.");
            return;
        }

        System.out.println("\n--- All Tetrahedrons ---");
        for (Tetrahedron tetrahedron : tetrahedrons) {
            System.out.println(tetrahedron);
        }
        System.out.println("Total: " + tetrahedrons.size() + " tetrahedrons");
    }

    private static void calculateShapeProperties() {
        System.out.println("Enter tetrahedron ID:");
        String id = scanner.nextLine();

        TetrahedronSpecification specification = new IdTetrahedronSpecification(id);
        List<Tetrahedron> results = repository.query(specification);

        if (results.isEmpty()) {
            System.out.println("No tetrahedron found with ID: " + id);
            return;
        }

        Tetrahedron tetrahedron = results.get(0);

        double area = calculator.computeArea(tetrahedron);
        double perimeter = calculator.computePerimeter(tetrahedron);
        double volume = calculator.computeVolume(tetrahedron);

        System.out.println("\n--- Tetrahedron Properties ---");
        System.out.println("ID: " + tetrahedron.getId());
        System.out.println("Area: " + area);
        System.out.println("Perimeter: " + perimeter);
        System.out.println("Volume: " + volume);

    }

    private static void searchTetrahedrons() {
        System.out.println("\n--- Search Tetrahedrons ---");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by area range");
        System.out.println("3. Search by perimeter range");
        System.out.println("4. Search by volume range");
        System.out.print("Enter your choice: ");

        int choice = getIntInput();
        TetrahedronSpecification specification;

        switch (choice) {
            case 1 -> {
                System.out.println("Enter ID:");
                String id = scanner.nextLine();
                specification = new IdTetrahedronSpecification(id);
            }
            case 2 -> {
                System.out.println("Enter minimum area:");
                double minArea = getDoubleInput();
                System.out.println("Enter maximum area:");
                double maxArea = getDoubleInput();
                specification = new AreaRangeTetrahedronSpecification(calculator, maxArea, minArea);
            }
            case 3 -> {
                System.out.println("Enter minimum perimeter:");
                double minPerimeter = getDoubleInput();
                System.out.println("Enter maximum perimeter:");
                double maxPerimeter = getDoubleInput();
                specification = new PerimeterRangeTetrahedronSpecification(calculator, minPerimeter, maxPerimeter);
            }
            case 4 -> {
                System.out.println("Enter minimum volume:");
                double minVolume = getDoubleInput();
                System.out.println("Enter maximum volume:");
                double maxVolume = getDoubleInput();
                specification = new VolumeRangeTetrahedronSpecification(calculator, minVolume, maxVolume);
            }
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }

        List<Tetrahedron> results = repository.query(specification);

        if (results.isEmpty()) {
            System.out.println("No tetrahedrons match the criteria.");
            return;
        }

        System.out.println("\n--- Search Results ---");
        for (Tetrahedron tetrahedron : results) {
            System.out.println(tetrahedron);
        }
        System.out.println("Total: " + results.size() + " tetrahedrons found");
    }

    private static void sortTetrahedrons() {
        System.out.println("\n--- Sort Tetrahedrons ---");
        System.out.println("1. Sort by ID");
        System.out.println("2. Sort by area");
        System.out.println("3. Sort by perimeter");
        System.out.println("4. Sort by volume");
        System.out.print("Enter your choice: ");

        int choice = getIntInput();

        switch (choice) {
            case 1 -> repository.sortBy(new TetrahedronByIdComparator());
            case 2 -> repository.sortBy(new TetrahedronByAreaComparator());
            case 3 -> repository.sortBy(new TetrahedronByPerimeterComparator());
            case 4 -> repository.sortBy(new TetrahedronByVolumeComparator());
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }

        System.out.println("Tetrahedrons sorted successfully.");
        displayAllTetrahedrons();
    }

    private static void displayWarehouseInfo() {
        System.out.println("Enter tetrahedron ID:");
        String id = scanner.nextLine();

        Warehouse.ShapeParameters parameters = warehouse.getParameters(id);

        System.out.println("\n--- Warehouse Information ---");
        System.out.println("ID: " + id);
        System.out.println("Area: " + parameters.area());
        System.out.println("Perimeter: " + parameters.perimeter());
        System.out.println("Volume: " + parameters.volume());
    }

    private static void editTetrahedron() {
        System.out.print("Enter Tetrahedron ID to update: ");
        String id = scanner.nextLine();
        TetrahedronSpecification specification = new IdTetrahedronSpecification(id);

        List<Tetrahedron> result = repository.query(specification);
        if (result.isEmpty()) {
            System.out.println("Tetrahedron not found.");
            return;
        }
        Tetrahedron tetrahedron = result.get(0);
        if (tetrahedron == null) {
            System.out.println("Tetrahedron not found.");
            logger.warn("Tetrahedron with ID {} not found.", id);
            return;
        }

        System.out.print("Which point do you want to update? (A, B, C, D): ");
        String labelInput = scanner.nextLine().trim().toUpperCase();
        if (labelInput.length() != 1 || !"ABCD".contains(labelInput)) {
            System.out.println("Invalid point label. Use A, B, C, or D.");
            return;
        }
        char pointLabel = labelInput.charAt(0);

        System.out.println("Enter new coordinates for point " + pointLabel + " (x y z):");
        Point newPoint;
        try {
            newPoint = readPoint();
        } catch (NumberFormatException e) {
            System.out.println("Invalid coordinates format.");
            return;
        }

        Point originalPoint;
        switch (pointLabel) {
            case 'A' -> {
                originalPoint = tetrahedron.getPointA();
                tetrahedron.setPointA(newPoint);
            }
            case 'B' -> {
                originalPoint = tetrahedron.getPointB();
                tetrahedron.setPointB(newPoint);
            }
            case 'C' -> {
                originalPoint = tetrahedron.getPointC();
                tetrahedron.setPointC(newPoint);
            }
            case 'D' -> {
                originalPoint = tetrahedron.getPointD();
                tetrahedron.setPointD(newPoint);
            }
            default -> {
                System.out.println("Invalid point label.");
                return;
            }
        }

        TetrahedronValidatorImpl validator = new TetrahedronValidatorImpl();
        if (!validator.isValid(tetrahedron)) {
            // Rollback
            switch (pointLabel) {
                case 'A' -> tetrahedron.setPointA(originalPoint);
                case 'B' -> tetrahedron.setPointB(originalPoint);
                case 'C' -> tetrahedron.setPointC(originalPoint);
                case 'D' -> tetrahedron.setPointD(originalPoint);
            }
            System.out.println("Update failed: resulting tetrahedron is invalid.");
            logger.warn("Rollback: invalid tetrahedron after updating point {}", pointLabel);
        }
    }

    private static int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static double getDoubleInput() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}