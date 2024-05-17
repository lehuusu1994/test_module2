
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductManagementApp {
    private List<Product> products;
    private Scanner scanner;


    public ProductManagementApp() {
        products = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("---- MENU ----");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới sản phẩm");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Sắp xếp");
            System.out.println("6. Tìm kiếm sản phẩm có giá đắt nhất");
            System.out.println("7. Đọc từ file");
            System.out.println("8. Ghi vào file");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // discard the newline character

            switch (choice) {
                case 1:
                    viewProductList();
                    break;
                case 2:
                    addNewProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    sortProducts();
                    break;
                case 6:
                    findMostExpensiveProduct();
                    break;

                case 0:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }

            System.out.println();
        }

        System.out.println("Ứng dụng đã thoát.");
    }

    private void viewProductList() {
        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống.");
        } else {
            System.out.println("Danh sách sản phẩm:");
            for (Product product : products) {
                System.out.println(product.getName());
            }
        }
    }

    private void addNewProduct() {
        System.out.print("Nhập mã sản phẩm: ");
        String code = scanner.nextLine();

        System.out.print("Nhập tên sản phẩm: ");
        String name = scanner.nextLine();

        System.out.print("Nhập mô tả: ");
        String description = scanner.nextLine();

        System.out.print("Nhập danh mục: ");
        String category = scanner.nextLine();

        System.out.print("Nhập giá: ");
        double price = scanner.nextDouble();

        System.out.print("Nhập số lượng: ");
        int quantity = scanner.nextInt();

        Product newProduct = new Product();
        newProduct.setCode(code);
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setCategory(category);
        newProduct.setPrice(price);
        newProduct.setQuantity(quantity);

        products.add(newProduct);

        System.out.println("Thêm mới sản phẩm thành công.");
    }

    private void updateProduct() {
        System.out.print("Nhập mã sản phẩm cần cập nhật: ");
        String code = scanner.nextLine();

        Product existingProduct = findProductByCode(code);

        if (existingProduct == null) {
            System.out.println("Không tìm thấy sản phẩm có mã " + code + ".");
            return;
        }

        System.out.print("Nhập tên sản phẩm mới: ");
        String name = scanner.nextLine();

        System.out.print("Nhập mô tả mới: ");
        String description = scanner.nextLine();

        System.out.print("Nhập danh mục mới: ");
        String category = scanner.nextLine();

        System.out.print("Nhập giá mới: ");
        double price = scanner.nextDouble();

        System.out.print("Nhập số lượng mới: ");
        int quantity = scanner.nextInt();

        existingProduct.setName(name);
        existingProduct.setDescription(description);
        existingProduct.setCategory(category);
        existingProduct.setPrice(price);
        existingProduct.setQuantity(quantity);

        System.out.println("Cập nhật sản phẩm thành công.");
    }

    private void deleteProduct() {
        System.out.print("Nhập mã sản phẩm cần xóa: ");
        String code = scanner.nextLine();

        Product existingProduct = findProductByCode(code);

        if (existingProduct == null) {
            System.out.println("Không tìm thấy sản phẩm có mã " + code + ".");
            return;
        }

        System.out.print("Bạn có chắc chắn muốn xóa sản phẩm này? (Y/N): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            products.remove(existingProduct);
            System.out.println("Xóa sản phẩm thành công.");
        } else {
            System.out.println("Hủy xóa sản phẩm.");
        }
    }

    private void sortProducts() {
        products.sort((p1, p2) -> (int) p1.getPrice());
        System.out.println("Sắp xếp sản phẩm theo giá thành công.");
        viewProductList();
    }

    private void findMostExpensiveProduct() {
        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm rỗng.");
        } else {
            Product mostExpensiveProduct = products.get(0);
            for (Product product : products) {
                if (product.getPrice() > mostExpensiveProduct.getPrice()) {
                    mostExpensiveProduct = product;
                }
            }
            System.out.println("Sản phẩm có giá đắt nhất:");
            System.out.println(mostExpensiveProduct);
        }


    }

    private Product findProductByCode(String code) {
        for (Product product : products) {
            if (product.getCode().equals(code)) {
                return product;
            }
        }

        return null;
    }
}

