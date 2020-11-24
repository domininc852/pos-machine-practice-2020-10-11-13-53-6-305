package pos.machine;

public class ProductInfo {
    private String barcode;
    private String name;
    private int unitPrice;
    private int quantity;
    private int subtotal;

    public ProductInfo(String barcode, String name, int unitPrice, int quantity, int subtotal) {
        this.barcode = barcode;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSubtotal() {
        return subtotal;
    }
}
