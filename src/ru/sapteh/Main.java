package ru.sapteh;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length==0){
            return;
        }
        String fileName="product.txt";
        ArrayList<Product> productList=new ArrayList<>();
        int idList=0;
        try(BufferedReader buffer= new BufferedReader(new FileReader(fileName))) {
            while (buffer.ready()){
                Product product=prodCreate(buffer.readLine());
                productList.add(product);
            }
        }
        if ("-c".equals(args[0])){
            for (Product product:productList) {
                if (product.getId()>idList){
                    idList=product.getId();
                }
            }
            String name= args[args.length-3];
            if (name.length()>30){
                name = name.substring(0,30);
            }
            String price= args[args.length-2];
            if (price.length()>8){
                price =price.substring(0,8);
            }
            String quantity=args[args.length-1];
            if (quantity.length()>4){
                quantity=quantity.substring(0,4);
            }
            Product product1=new Product(++idList,name,price,quantity);
            productList.add(product1);
        }
       if ("-d".equals(args[0])){
           idList=Integer.parseInt(args[1]);
           for (int i = 0; i < productList.size(); i++) {
               int outId = productList.get(i).getId();
               if (idList==outId){
                   productList.remove(i);
               }
           }

       }
       if ("-u".equals(args[0])){
            idList=Integer.parseInt(args[1]);
           for (int i = 0; i < productList.size(); i++) {
               int id = productList.get(i).getId();
               if (id==idList){
                   productList.get(i).setName(String.format("%-30s",args[2]).substring(0,30));
                   productList.get(i).setPrice(String.format("%-8s",args[3]).substring(0,8));
                   productList.get(i).setQuantity(String.format("%-4s",args[4]).substring(0,4));
               }
           }
       }

        try(FileWriter fw=new FileWriter(fileName)) {
            for (Product product:productList) {
                fw.write(product.toString());
                fw.write("\n");
            }
        }
    }
    public static Product prodCreate(String readLine){
        int id;
        String name;
        String price;
        String quantity;
        id=Integer.parseInt(readLine.substring(0,8).trim());
        name=readLine.substring(8,38).trim();
        price=readLine.substring(38,46).trim();
        quantity=readLine.substring(46,50).trim();
        return new Product(id,name,price,quantity);
    }


}

