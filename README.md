# Product Managment Application Code Documentation

## Purpose of Application
This application is used to manage products and depots.

The user can create new products and depots, and say which products are in which depots.

## Technologies
- Java
- Spring Data si Web 
- Tomcat
   - Web Server that shows the HTML pages in Chrome on localhot:8080
- Thymleaf
  - Allows java variables to be displayed in HMTL pages
- HTML
- CSS
- Bootstrap
- SQL

## Java Code

### Controllers
Controller control what HTML pages the user sees and what information it contains.


#### DepotController

- Contains all controller methods for depot actions
  - viewDeports
    - Build list of all the depots in the database `List<DepotModel> depotModelList = depotService.getDepots();`
    - returns `return "viewdepots"`
  - deleteDepots
    - Deletes a deport with a specific ID - `depotService.deleteDepot(id);`
    - returns `return "redirect:/viewDepots";`
  - addDepot
    - creates a new DepotModel()
    - Returns `return "adddepot";`
  - addNewDepot
    - Adds a new depot to the database
    - Returns `return "redirect:/viewDepots";`
  - editDepotPage
    - Returns the data for a specific depot and populates the form
    - Returns ` return "editdepot";`
  - editDepot
   - Updates the Depot object in the database ```depotService.updateDepot(depotModel);```
   - Returns ```return "redirect:/viewDepots";```
  - searchDepot
    - Creates a new DepoModel()
    - Returns ```return "searchdepots";```
  - searchDepotName
    - Returns all the depots that start with a specific set of character
    - Returns ``` return "founddepots"; ```

#### NavigationController

- This contains methods for general navigation of application not related to Products or Depots
  - homePage
  - helpPage

#### ProductContoller

- Contains all the controller methods for prodcut actions
  - addProduct
  - addNewProduct
  - viewProducts
  - editProductPage
  - editProduct
  - deleteProduct
  - searchProduct
  - productsInDepot

### Entity
Contains the methods and properties of the Product and Depot classes. This also allows for tables and columns to be created in the databae.

#### DepotModel

- Defines the methods and properties for the Depot Class

#### ProductModel

- Defines the methods and properties for the Product Class

### Repository
Repository allows the application to get, add and modify data in the database.


#### DepotRepository

- Defines methods and Propertie associated with database access
- <code>public interface DepotRepository extends JpaRepository<DepotModel, Integer> </code>

#### ProductRepository

- Defines methods and Propertie associated with database access
- <code>public interface ProductRepository extends JpaRepository<ProductModel , Integer> </code>
- SQL Query - searchByDepotOD
  - This exeutes a SQL Native query that returns all the products where depot_id is equal to specified value
  - Used to list all the products in a specific depot

### Service

Contains the methods that control what information to get, add or modify in the database.

#### DepotService

- Contains all the methods the return data and creates objects
  - getDpots
    - Returns all the depots in the database
      - depotRepository.findAll()
  - deleteDepot
    - Deletes a deport with a specific ID
      - depotRepository.deleteById(id)
  - addDepot
    - Adds a new depot into the database
      - depotRepository.save(depot)
  - updateDepot
    - Updates the details of a speciifc depot
  - getDepot
    - Returns a depot with a specific ID
      - DepotModel depotModel = optionalDepotModel.get();
  - searchByDepotName
    - Returns 1 or more depots who's name starts with specific characters

#### ProductService

- Contains all the methods the return data and creates objects
  - addProduct
    - Adds a new product into the database
      - productRepository.save(product)
  - getProducts
    - Returns all the products in the database
      - productRepository.findAll()
  - getProduct
    - Returns a product with a specifiedID
      - ProductModel productModel = optionalProductModel.get()
  - updateProduct
    - Updates the details of a speciifc product
  - removeProduct
    - Removes a specific product from the database
      - productRepository.deleteById(id)
  - findProductsinDepot
    - Returns all the products in a specific depot
      - List<ProductModel> productModelList = productRepository.searchByDepotID(depotId)

#### Exceptions

##### ProdcutNotFoundException

- Used to handle instances where there is an error becuase a specific product cannot be found in the database

##### DeportNotFoundExcception

- Used to handle instances where there is an error becuase a specific depot cannot be found in the database

## Resources

### Static

#### CSS

- This folder contains an custom CSS files that are used in the application

##### main.css

- Contains all the custom and customised Bootstrap style overrides

## Templates

### adddepot.html

- Contains Add Depot html form
- The form has a post action to {/add-new-depot}

### addproduct.html

- Contains Add Product html form
- The form has a post action to {/add-new-product}

### base.html

- This is the base template that contains the navbar
- it contains the th:fragment property - `<div th:fragment="header">`
- This is then used in the other HTML pages via `<div th:replace="base :: header"> </div>` to insert the navbar
- This is done so the navbar menu can be udated and change in one location

### editdepot.html

- Contains Edit Depot html form
- The form has a post action to {/edit-depot}

### editproduct.html

- Contains Edit Product html form
- The form has a post action to {/edit-product}

### founddepots.html

- This contains all the depots that have been found via searchdepots.html

### foundproducts.html

- This contains all the products that have been found via searchproducts.html

### foundproductsindepot.html

- Contians a list of all the products that are in a specific depot

### index.html

- This is the homeepage for the application

### searchdepots.html

- Contains the Search Depots form
- The form has a post action to {/search-depot}

### searchproducts.html

- Contains the Search Product form
- The form has a post action to {/search-product}

### template.html

- HTML file to be used to create new HTML files

### viewdepots.html

- Contains a list of the depots in the database

### viewproducts.html

- Contains a list of all the products in all the depots in the database
