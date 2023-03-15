import DAO.ProductsDAO;
import DAO.BrandDAO;
import Model.Brands;
import Model.Products;

import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        int choice = 0;
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = new Scanner(System.in).nextInt();
            choose(choice);
        }while (choice != 0);

    }
    public static void menu(){
        System.out.println("1. Danh sách sản phẩm");
        System.out.println("2. Xóa sản phẩm theo mã");
        System.out.println("3. Cập nhật thông tin sản phẩm");
        System.out.println("4. Số lượng sản phẩm theo hãng");
        System.out.println("5. Thêm sản phẩm");
        System.out.println("6. 5 sản phẩm có giá cao nhất");
        System.out.println("7. Danh sách hãng sản xuất");
        System.out.println("8. Thêm hãng sản xuất");
        System.out.println("9. Xóa hãng sản xuất theo mã");
        System.out.println("0. Thoát");
    }

    public static void choose(int choice){
        switch (choice) {
            case 1:
                showProduct();
                System.out.println();
                break;
            case 2:
                boolean checkDelete = deleteProduct();
                if (checkDelete) {
                    System.out.println("Xóa sản phẩm thành công");
                } else {
                    System.out.println("Xóa sản phẩm thất bại");
                }
                break;
            case 3:
                updateProduct();
                break;
            case 4:
                break;
            case 5:
                boolean check = addProduct();
                if (check) {
                    System.out.println("Thêm sản phẩm thành công");
                } else {
                    System.out.println("Thêm sản phẩm thất bại");
                }
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ");
                break;
        }
    }

    // show product
    public static void showProduct(){
        ProductsDAO productsDAO = new ProductsDAO();
        List<Products> products = productsDAO.getAllProducts();
        System.out.printf("%-5s %-20s %-10s %-10s", "STT", "Product Name", "Price", "Màu sắc");
        System.out.println();
        for(int i = 0; i < products.size(); i++){
            System.out.println();
            System.out.printf("%-5d %-20s %-10d %-10s", i+1, products.get(i).getProduct_name(), products.get(i).getProduct_price(), products.get(i).getProduct_color());
        }
    }

    public static boolean addProduct(){
        List<Brands> lstBrand = new BrandDAO().getAll();
        System.out.println("Nhập tên sản phẩm: ");
        String product_name = new Scanner(System.in).nextLine();
        System.out.println("Nhập giá sản phẩm: ");
        int product_price = new Scanner(System.in).nextInt();
        if(product_price < 0){
            System.out.println("Giá sản phẩm không hợp lệ");
            return false;
        }
        System.out.println("Nhập kích thước sản phẩm: ");
        String product_size = new Scanner(System.in).nextLine();
        System.out.println("Nhập màu sắc sản phẩm: ");
        String product_color = new Scanner(System.in).nextLine();

        System.out.println("Nhập mã hãng sản xuất trong danh sách ID: ");
        for (Brands br: lstBrand
             ) {
            System.out.print("ID: " + br.getId() + " - " + "Name : " + br.getBrand_name());
            System.out.println();
        }
        int brand_id = new Scanner(System.in).nextInt();
        if(lstBrand.stream().noneMatch(brand -> brand.getId() == brand_id)){
            System.out.println("Mã hãng sản xuất không hợp lệ");
            return false;
        }
        Products products = new Products(product_name, product_price, product_size, product_color, brand_id);
        ProductsDAO productsDAO = new ProductsDAO();
        return productsDAO.addProduct(products);
    }

    // delete product

    public  static boolean deleteProduct(){
        System.out.println("Nhập mã sản phẩm cần xóa: ");
        int product_id = new Scanner(System.in).nextInt();
        if(product_id < 0){
            System.out.println("Mã sản phẩm không hợp lệ");
            return false;
        }else if(new ProductsDAO().getProductById(product_id) == null){
            System.out.println("Mã sản phẩm không tồn tại");
            return false;
        }
        ProductsDAO productsDAO = new ProductsDAO();
        return productsDAO.deleteProduct(product_id);
    }

    // update product
    public static boolean updateProduct(){
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("Nhập mã sản phẩm cần cập nhật: ");
            int product_id = new Scanner(System.in).nextInt();
            if(product_id < 0){
                System.out.println("Mã sản phẩm không hợp lệ");
                return false;
            }else if(new ProductsDAO().getProductById(product_id) == null){
                System.out.println("Mã sản phẩm không tồn tại");
                return false;
            }
            Products oldProducts = new ProductsDAO().getProductById(product_id);
            System.out.println("Nhập tên sản phẩm: ");
            String product_name = new Scanner(System.in).nextLine();
            if(!product_name.trim().equals("")){
                oldProducts.setProduct_name(product_name);
            }else {

            }
            // Gía sản phẩm
            System.out.println("Nhập giá sản phẩm: ");
            if(sc.hasNextInt()){
                int product_price =Integer.parseInt(sc.nextLine());
                if(product_price > 0){
                    oldProducts.setProduct_price(product_price);
                }else {
                    System.out.println("Mã hãng sản xuất không hợp lệ");
                    return false;
                }
            }else {
                System.out.println("Gias k ddusng");
            }

            System.out.println("Nhập kích thước sản phẩm: ");
            String product_size = new Scanner(System.in).nextLine();
            if(!product_size.trim().equals("")){
                oldProducts.setProduct_size(product_size);
            }
            System.out.println("Nhập màu sắc sản phẩm: ");
            String product_color = new Scanner(System.in).nextLine();
            if(!product_color.trim().equals("")){
                oldProducts.setProduct_color(product_color);
            }
            System.out.println("Nhập mã hãng sản xuất: ");
            if(sc.hasNextInt()){
                int brand_id = Integer.parseInt(sc.nextLine());
                if(brand_id > 0 && new BrandDAO().getBrandByID(brand_id) != null){
                    oldProducts.setBrand_id(brand_id);
                }else {
                    System.out.println("Mã hãng sản xuất không hợp lệ");
                    return false;
                }
            }else {
                System.out.println("Mã hãng sản xuất không hợp lệ");
                return false;
            }
            ProductsDAO productsDAO = new ProductsDAO();
            return productsDAO.updateProduct(oldProducts);
        }catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return false;
    }
    public  static void show5limit

    // Brand
    public List<Brands> getAll (){
        BrandDAO brandDAO = new BrandDAO();
        return brandDAO.getAll();
    }

    /
}
