package KotlinExercises

import java.util.*

class Product (var name : String, var description : String, var count : Int)

fun menu (printType : Int) {

    when (printType) {
        1 -> {
            print(  "===========================================\n" +
                    "|       Inventory Management System       |\n" +
                    "===========================================\n" +
                    "| Input for different transaction:        |\n" +
                    "| View Product/s ...................... 1 |\n" +
                    "| Add Product ......................... 2 |\n" +
                    "| Edit Product/s ...................... 3 |\n" +
                    "| Delete Product/s .................... 4 |\n" +
                    "===========================================\n" +
                    "Transaction Desired: "
            )
        }
        2 -> {
            print(  "===========================================\n" +
                    "|       Product Information to Edit       |\n" +
                    "===========================================\n" +
                    "| Input for different information:        |\n" +
                    "| Name ................................ 1 |\n" +
                    "| Description ......................... 2 |\n" +
                    "| Count ............................... 3 |\n" +
                    "===========================================\n" +
                    "Information to Edit: "
            )
        }
    }


}

fun continueTransaction (reader : Scanner, type: Int = 1) : Boolean {

    when (type) {
        1 -> print("Do you want to have another transaction (Y/N)?: ")
        2 -> print("Is this the only information you want to edit (Y/N)? ")
    }

    var cont = reader.next()

    return cont.equals("y", ignoreCase = true)

}

fun printProducts (products : ArrayList<Product>, transactionDesired : Int) {
    when (transactionDesired) {
        1 -> {
            for ((nthElement, product) in products.withIndex()){
                println("=============================================\n" +
                        "Product ID: $nthElement \n" +
                        "Name: ${product.name} \n" +
                        "Description: ${product.description} \n" +
                        "Count Available: ${product.count}\n" +
                        "=============================================")
            }
        }

        3, 4 -> {
            println("LIST OF AVAILABLE PRODUCTS\n" +
                    "=============================================\n")
            for ((nthElement, product) in products.withIndex()){
                println("Product ($nthElement) Name: ${product.name} Count: ${product.count}")
            }
            println("=============================================\n")
        }

    }

}

fun inputProducts (reader : Scanner) : Product {

    print("Product Name: ")
    var pName : String = reader.next()

    print("Product Description: ")
    var pDes : String = reader.next()

    print("Product Count: ")
    var pCount : Int = reader.nextInt()

    println("=====================================\n" +
            "! $pCount of Products $pName added. !\n" +
            "=====================================\n")

    return Product(pName,pDes,pCount)
}


fun main () {

    // Variables
    val reader = Scanner(System.`in`)
    var transactionDesired : Int = 1

    var products = arrayListOf<Product>()

    do {

        menu(1)
        transactionDesired = reader.nextInt()

        when (transactionDesired) {
            1 -> {

//                println("Transaction Desired 1")
                if (products.size == 0){
                    println("No Products Available")
                } else {
                    println("\n\nPRINTING PRODUCTS\n\n")
                    printProducts(products, transactionDesired)
                }

                }
            2 -> {
                println("Adding Product")

                print("Are you adding a product of a same type / name?(Y/N): ")
                var typeB : String = reader.next()

                if (typeB.equals("y", ignoreCase = true)) {
                    products.add(inputProducts(reader))
                } else {
                    print("How many types of product you want to add?: ")
                    var type = reader.nextInt()
                    var ctr : Int = 1
                    while (ctr <= type){
                        println("Kindly input product $ctr / $type information")
                        products.add(inputProducts(reader))
                        ctr++
                    }
                }

                }
            3 -> {
                var infoChange : String
                var productID : Int
                var toEdit : Int

                printProducts(products, transactionDesired)

                if (products.size == 0) {
                    println("No products available, add product first, kindly redo your transaction.")
                    continue
                } else print("What product do you want to edit? (input product ID): ")
                productID = reader.nextInt()

                if (productID >= products.size) {
                    println("No product $productID found, kindly redo your transaction")
                    continue
                } else {
                    do {
                        menu(2)
                        toEdit = reader.nextInt()

                        print("Kindly input the new information to include: ")
                        infoChange = reader.next()

                        when (toEdit) {
                            1 -> {products[productID].name = infoChange}
                            2 -> {products[productID].description = infoChange}
                            3 -> {products[productID].count = infoChange.toInt()}
                        }

                        println("\nThe Information has been saved...")

                    } while (!continueTransaction(reader, 2))
                }

                }
            4 -> {
                printProducts(products, transactionDesired)

                if (products.size == 0) {
                    println("No products available, add product first, kindly redo your transaction.")
                    continue
                } else print("What product do you want to delete? (input product ID): ")

                var productID = reader.nextInt()
                var pName = products[productID].name

                if (productID >= products.size) {
                    println("No product $productID found, kindly redo your transaction")
                    continue
                } else {
                    products.removeAt(productID)
                    println("Product $pName ($productID) has been deleted")
                }

                }

            else -> {
                println("\nINVALID TRANSACTION! \n")
            }
        }

    } while (continueTransaction(reader))

    println("\n====================================================\n" +
            "| Thank you for using Inventory Management System! |\n" +
            "====================================================\n")

}


