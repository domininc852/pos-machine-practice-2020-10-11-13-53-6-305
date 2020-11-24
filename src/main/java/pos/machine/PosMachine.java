package pos.machine;

import java.util.ArrayList;
import java.util.List;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        ArrayList<ProductInfo>itemsInformation=processBarcodes(barcodes);
        return generateReceipt(itemsInformation);
    }
    private ArrayList<ProductInfo> processBarcodes(List<String> barcodes){
        ArrayList<ProductInfo> itemsInformation=new ArrayList<ProductInfo>();
        for (String barcode:barcodes){
            ProductInfo itemInformation=retrieveItemDetail(barcode);
            boolean exist=false;
            for (ProductInfo item:itemsInformation){
                if (item.getBarcode()==itemInformation.getBarcode()){
                    exist=true;
                    break;
                }
            }
            if (exist==false)
                itemsInformation.add(itemInformation);

        }
        return findQuantityOfEachItem(barcodes,itemsInformation);
    }
    private ProductInfo retrieveItemDetail(String barcode){
        List<ItemInfo> itemInfos=ItemDataLoader.loadAllItemInfos();
        for (int i=0;i<itemInfos.size();i++){
            if (itemInfos.get(i).getBarcode()==barcode){
                return new ProductInfo(itemInfos.get(i).getBarcode(),itemInfos.get(i).getName(),itemInfos.get(i).getPrice(),0,0);
            }
        }
        return null;
    }
    private int calculateSubtotal(ProductInfo itemInformation){
        return itemInformation.getQuantity()*itemInformation.getUnitPrice();
    }
    private int calculateTotal(ArrayList<ProductInfo> itemsInformation){
        int total=0;
        for (ProductInfo itemInformation:itemsInformation){
            total+=itemInformation.getSubtotal();
        }
        return total;
    }
    private ArrayList<ProductInfo> findQuantityOfEachItem(List<String> barcodes, ArrayList<ProductInfo>itemsInformation){
        for (int i=0;i<barcodes.size();i++){
            for (int j=0;j<itemsInformation.size();j++){
                if (barcodes.get(i)==itemsInformation.get(j).getBarcode()){
                    itemsInformation.get(j).setQuantity(itemsInformation.get(j).getQuantity()+1);
                }
            }
        }
        return itemsInformation;
    }
    private String generateReceipt(ArrayList<ProductInfo> itemsInformation){
        for (ProductInfo itemInformation:itemsInformation){
            int subtotal=calculateSubtotal(itemInformation);
            itemInformation.setSubtotal(subtotal);
        }
        int total=calculateTotal(itemsInformation);
        String receipt="***<store earning no money>Receipt***\n";
        for(ProductInfo itemInformation:itemsInformation){
            receipt+="Name: "+itemInformation.getName()+", Quantity: "+itemInformation.getQuantity()+", Unit price: "+itemInformation.getUnitPrice()+" (yuan), Subtotal: "+itemInformation.getSubtotal()+" (yuan)\n";
        }
        receipt+="----------------------\n";
        receipt+="Total: "+total+" (yuan)\n";
        receipt+="**********************";
        return receipt;
    }
}

